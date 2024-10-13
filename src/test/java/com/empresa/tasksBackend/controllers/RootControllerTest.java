package com.empresa.tasksBackend.controllers;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RootControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void deveRetorarHelloWord() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/", String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
        Assertions.assertThat(response.getBody()).isEqualTo("Hello World!");
    }
}
