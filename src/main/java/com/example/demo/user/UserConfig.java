package com.example.demo.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class UserConfig
{
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            User mariam = new User(1, "mariam", "iLoveAlex53");
            User alex = new User(2, "alex", "MariamSmellsLikeOldCheese");

            userRepository.saveAll(List.of(mariam, alex));
        };
    }
}
