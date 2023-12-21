package com.example.manager;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    boolean existsByEmail(String email);
}


