package com.example.manager;

public class PatientNotFoundException extends RuntimeException{

    PatientNotFoundException(Long id) {
        super("Could not find patient: " + id);
    }
}
