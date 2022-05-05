package com.testtask.hospitalwebapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity(name = "patients")
@Setter
@Getter
@ToString
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue
    @Column(name = "patient_id")
    private Long id;

    @NotBlank
    @Column(name = "surname")
    private String surname;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "patronymic")
    private String patronymic;

    @NotBlank
    @Column(name = "phone_number")
    private String phoneNumber;

    public Patient() {
    }

    public Patient(String name, String surname, String patronymic, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id) && Objects.equals(surname, patient.surname) && Objects.equals(name, patient.name) && Objects.equals(patronymic, patient.patronymic) && Objects.equals(phoneNumber, patient.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, patronymic, phoneNumber);
    }
}
