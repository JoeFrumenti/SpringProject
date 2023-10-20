package com.example.demo;

import com.example.demo.student.Student;
import com.example.demo.student.StudentController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;


    private MockMvc mvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }


	@Test
	void checkControllerBean(){
        StudentController studentController = webApplicationContext.getBean(StudentController.class);
        Assert.notNull(studentController, "message");
    }

    @Test
    void getTest() throws Exception{
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/sayHello"))
                .andExpect((MockMvcResultMatchers.status().isOk()))
                .andExpect(MockMvcResultMatchers.content().string("Hello world!"));
    }


    @Test
    void testStudentPost() throws Exception{
        String url = "/student";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Student gus = new Student("Gus", LocalDate.of(1998, Month.MAY, 30), "gus@hotmail.com");
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(gus);

        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}


