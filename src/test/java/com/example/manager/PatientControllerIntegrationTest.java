package com.example.manager;

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
        Patient newPatient = new Patient(
            "David Gilmore",
            "dgilmore@gmail.com",
            LocalDateTime.of(1946, 3, 6, 0, 0, 0),
            LocalDateTime.of(2024, 1, 1, 0, 0, 0)
        );
        webTestClient.post()
                .uri("/patient")
                .bodyValue(newPatient)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    public void testUpdatePatient() {
        Patient updatedPatient = new Patient(
            "John Smith",
            "johnsmith1900@gmail.com",
            LocalDateTime.of(1900, 1, 1, 0, 0, 0),
            LocalDateTime.of(2025, 1, 1, 0, 0, 0)
        );
        webTestClient.put()
                .uri("/patient/1", 1L)
                .bodyValue(updatedPatient)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    public void testUniqueEmail() {
        Patient david = new Patient(
            "David Gilmore",
            "dgilmore@gmail.com",
            LocalDateTime.of(1946, 3, 6, 0, 0, 0),
            LocalDateTime.of(2024, 1, 1, 0, 0, 0)
        );
        Patient roger = new Patient(
            "Roger Waters",
            "dgilmore@gmail.com",
            LocalDateTime.of(1943, 9, 6, 0, 0, 0),
            LocalDateTime.of(2024, 1, 1, 0, 0, 0)
        );
        webTestClient.post()
            .uri("/patient")
            .bodyValue(david)
            .exchange()
            .expectStatus().isCreated();
        
        // this will throw an error b/c of duplicated email
        webTestClient.post()
            .uri("/patient")
            .bodyValue(roger)
            .exchange()
            .expectStatus().isBadRequest();
    }
}
