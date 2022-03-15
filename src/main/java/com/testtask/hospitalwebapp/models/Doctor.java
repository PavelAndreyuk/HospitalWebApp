package com.testtask.hospitalwebapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "specialisation")
    private String specialisation;

    public Doctor() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return name.equals(doctor.name) && surname.equals(doctor.surname) && patronymic.equals(doctor.patronymic) && specialisation.equals(doctor.specialisation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, patronymic, specialisation);
    }
}
