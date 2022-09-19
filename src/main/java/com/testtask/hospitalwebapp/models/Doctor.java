package com.testtask.hospitalwebapp.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity(name = "doctors")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue
    @Column(name = "doctor_id")
    private Long id;

    @NotBlank
    @NotNull
    @Column(name = "name")
    private String name;

    @NotBlank
    @NotNull
    @Column(name = "surname")
    private String surname;

    @NotBlank
    @NotNull
    @Column(name = "patronymic")
    private String patronymic;

    @NotBlank
    @NotNull
    @Column(name = "specialisation")
    private String specialisation;

    public Doctor(String name, String surname, String patronymic, String specialisation) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.specialisation = specialisation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id.equals(doctor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
