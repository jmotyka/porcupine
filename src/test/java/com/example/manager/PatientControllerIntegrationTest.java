package com.example.manager;

import java.util.Map;
import java.util.HashMap;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testGetPatient() {
        webTestClient.get()
                .uri("/patients/1", 1L)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testCreatePatient() {
        Map<String, Object> newPatient = new HashMap<>();
        

        LocalDateTime dateOfBirth = LocalDateTime.parse("1946-03-06T00:00:00");
        LocalDateTime nextAppointment = LocalDateTime.parse("2024-03-06T00:00:00");

        newPatient.put("name", "David Gilmore");
        newPatient.put("email", "dgilmore@gmail.com");
        newPatient.put("dateOfBirth", dateOfBirth.toString());
        newPatient.put("nextAppointment", nextAppointment.toString());

        webTestClient.post()
                .uri("/patient")
                .bodyValue(newPatient)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    public void testUpdatePatient() {
        Map<String, Object> updatedPatient = new HashMap<>();
        

        LocalDateTime dateOfBirth = LocalDateTime.parse("1900-01-01T00:00:00");
        LocalDateTime nextAppointment = LocalDateTime.parse("2025-01-01T00:00:00");

        updatedPatient.put("name", "John Smith");
        updatedPatient.put("email", "jsmith1900@gmail.com");
        updatedPatient.put("dateOfBirth", dateOfBirth.toString());
        updatedPatient.put("nextAppointment", nextAppointment.toString());

        webTestClient.put()
                .uri("/patient/1", 1L)
                .bodyValue(updatedPatient)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    public void testUniqueEmail() {
        Map<String, Object> newPatient = new HashMap<>();
        

        LocalDateTime dateOfBirth = LocalDateTime.parse("1946-03-06T00:00:00");
        LocalDateTime nextAppointment = LocalDateTime.parse("2024-03-06T00:00:00");

        newPatient.put("name", "David Gilmore");
        newPatient.put("email", "dgilmore@gmail.com");
        newPatient.put("dateOfBirth", dateOfBirth.toString());
        newPatient.put("nextAppointment", nextAppointment.toString());

        webTestClient.post()
            .uri("/patient")
            .bodyValue(newPatient)
            .exchange()
            .expectStatus().isBadRequest();
    }   
}
