package com.example.manager;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Table(name = "patient", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Patient {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @Column(name = "name", nullable = false, updatable = true)
    private String name;

    @NotEmpty
    @Column(name = "email", unique = true, nullable = false, updatable = true)
    private String email;

    @NotEmpty
    @Column(name = "date_of_birth", nullable = false, updatable = true)
    private LocalDateTime dateOfBirth;

    @Column(name = "next_appointment", nullable = true, updatable = true)
    private LocalDateTime nextAppointment;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at", nullable = true, updatable = true)
    private LocalDateTime modifiedAt;

    // Constructors
    Patient() {}

    Patient(
        String name,
        String email,
        LocalDateTime dateOfBirth,
        LocalDateTime nextAppointment
    ) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.nextAppointment = nextAppointment;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = null;
    }
    
    // Getters
    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public LocalDateTime getDateOfBirth() {
        return this.dateOfBirth;
    }

    public LocalDateTime getNextAppointment() {
        return this.nextAppointment;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return this.modifiedAt;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setNextAppointment(LocalDateTime nextAppointment) {
        this.nextAppointment = nextAppointment;
    }

    @PreUpdate
    public void preUpdate() {
        this.modifiedAt = LocalDateTime.now();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id) &&
                Objects.equals(name, patient.name) &&
                Objects.equals(email, patient.email) &&
                Objects.equals(dateOfBirth, patient.dateOfBirth) &&
                Objects.equals(nextAppointment, patient.nextAppointment) &&
                Objects.equals(createdAt, patient.createdAt) &&
                Objects.equals(modifiedAt, patient.modifiedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, dateOfBirth, nextAppointment, createdAt, modifiedAt);
    }
}
