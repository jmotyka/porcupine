package com.example.manager;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.CollectionModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
public class PatientController {
    
    private final PatientRepository repository;
    private final PatientModelAssembler assembler;

    PatientController(PatientRepository repository, PatientModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/patients")
    CollectionModel<EntityModel<Patient>> all() {

        List<EntityModel<Patient>> patients = this.repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
      
        return CollectionModel.of(patients, linkTo(methodOn(PatientController.class).all()).withSelfRel());
    }

    @GetMapping("/patients/{id}")
    EntityModel<Patient> one(@PathVariable Long id) {
        Patient patient = this.repository.findById(id)
          .orElseThrow(() -> new PatientNotFoundException(id));
        
        return this.assembler.toModel(patient);
    }

    @PostMapping("/patient")
    ResponseEntity<?> newPatient(@RequestBody Patient newPatient) {
        if (this.repository.existsByEmail(newPatient.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate email address");
        }

        EntityModel<Patient> entityModel = assembler.toModel(this.repository.save(newPatient));
        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @PutMapping("/patient/{id}")
    ResponseEntity<?> updatedPatient(@RequestBody Patient updatedPatient, @PathVariable Long id) {
        if (this.repository.existsByEmail(updatedPatient.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate email address");
        }
        
        Patient patient = this.repository.findById(id)
            .map(existingPatient -> {
                existingPatient.setName(updatedPatient.getName());
                existingPatient.setEmail(updatedPatient.getEmail());
                existingPatient.setDateOfBirth(updatedPatient.getDateOfBirth());
                existingPatient.setNextAppointment(updatedPatient.getNextAppointment()); 
                return this.repository.save(existingPatient);
            })
            .orElseGet(() -> {
                updatedPatient.setId(id);
                return this.repository.save(updatedPatient);
            });

        EntityModel<Patient> entityModel = assembler.toModel(patient);

        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }
}
