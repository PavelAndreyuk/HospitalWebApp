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

@Entity(name = "doctors")
@Setter
@Getter
@ToString
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue
    @Column(name = "doctor_id")
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "surname")
    private String surname;

    @NotBlank
    @Column(name = "patronymic")
    private String patronymic;

    @NotBlank
    @Column(name = "specialisation")
    private String specialisation;

    public Doctor() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id) && Objects.equals(name, doctor.name) && Objects.equals(surname, doctor.surname) && Objects.equals(patronymic, doctor.patronymic) && Objects.equals(specialisation, doctor.specialisation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, patronymic, specialisation);
    }
}
