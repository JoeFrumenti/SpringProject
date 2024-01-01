package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    public void deleteByUsername(String username);

    @Query
    public Optional<User> findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

}
