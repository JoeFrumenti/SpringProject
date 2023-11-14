package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {

        return userRepository.findAll();
    }

    public ResponseEntity<?> addNewUser(User user) {
        System.out.println("THERE ARE " + userRepository.count() + " USERS");
        Optional<User> existingUser = userRepository.findByUsername("mariam");


        if(existingUser.isPresent())
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);

    }

    @Transactional

    public ResponseEntity<?> deleteUser(String username)
    {
        userRepository.deleteByUsername(username);
        return ResponseEntity.ok("Deleted " + username);
    }
}
