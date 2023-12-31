package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
public class AppController {

    @Autowired
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);


    public AppController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/sayHello")
    public ResponseEntity<String> greeting()
    {
        return ResponseEntity.ok("Hello world!");
    }

    @PostMapping("/user")
    public ResponseEntity<?> addUser(@RequestBody User user)
    {
        return userService.addNewUser(user);
    }

    @GetMapping("/")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @DeleteMapping("/user/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username)
    {
        return userService.deleteUser(username);
    }

    @GetMapping("/user/login")
    public ResponseEntity<?> login(@RequestParam String param1, @RequestParam String param2)
    {
        //return ResponseEntity.ok(user.getUsername() + user.getPassword())
        return userService.login(param1, param2);
    }
}
