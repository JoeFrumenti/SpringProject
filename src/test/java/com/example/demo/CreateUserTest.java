package com.example.demo;

import com.example.demo.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDate;
import java.time.Month;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest
public class CreateUserTest {



    @Test
    void testUserPost() throws Exception{
        String url = "http://localhost:8080/user";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        User gus = new User(1, "gusbaby", "gusNOMBERone1");
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        String json = om.writeValueAsString(gus);

        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


}
