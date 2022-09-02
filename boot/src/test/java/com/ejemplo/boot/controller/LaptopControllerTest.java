package com.ejemplo.boot.controller;

import com.ejemplo.boot.entity.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/laptops",Laptop[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void findOneById() {
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/laptop/5",Laptop.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void createLaptop() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String json = "{\n" +
                "    \"name\": \"XPS\",\n" +
                "    \"brand\": \"Dell\",\n" +
                "    \"manufacturingDate\": \"2022-08-08\"\n" +
                "}";

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/laptops",HttpMethod.POST,request,Laptop.class);

        Laptop laptop = response.getBody();
        assertEquals(5L, laptop.getId());
        assertEquals("XPS", laptop.getName());
    }

    @Test
    void updateLaptopOk(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String json = "{\n" +
                "    \"id\": 2,\n" +
                "    \"name\": \"Zephyrus\",\n" +
                "    \"brand\": \"Asus\",\n" +
                "    \"manufacturingDate\": \"2022-08-26\"\n" +
                "}";

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/laptops",HttpMethod.PUT,request,Laptop.class);

        Laptop laptop = response.getBody();
        assertEquals(2L, laptop.getId());
        assertEquals("Zephyrus", laptop.getName());
        assertEquals("Asus", laptop.getBrand());
    }

    @Test
    void updateLaptopWithoutId(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String json = "{\n" +
                "    \"name\": \"Zephyrus\",\n" +
                "    \"brand\": \"Asus\",\n" +
                "    \"manufacturingDate\": \"2022-08-26\"\n" +
                "}";

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/laptops",HttpMethod.PUT,request,Laptop.class);

        Laptop laptop = response.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void updateLaptopWithNotExistId(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String json = "{\n" +
                "    \"id\": 8,\n" +
                "    \"name\": \"Zephyrus\",\n" +
                "    \"brand\": \"Asus\",\n" +
                "    \"manufacturingDate\": \"2022-08-26\"\n" +
                "}";

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/laptops",HttpMethod.PUT,request,Laptop.class);

        Laptop laptop = response.getBody();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}