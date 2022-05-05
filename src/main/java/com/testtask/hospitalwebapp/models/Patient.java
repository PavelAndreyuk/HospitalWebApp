package com.testtask.hospitalwebapp.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity(name = "patients")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue
    @Column(name = "patient_id")
    private Long id;

    @NotBlank
    @NotNull
    @Column(name = "surname")
    private String surname;

    @NotBlank
    @NotNull
    @Column(name = "name")
    private String name;

    @NotBlank
    @NotNull
    @Column(name = "patronymic")
    private String patronymic;

    @NotBlank
    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;

    public Patient(String name, String surname, String patronymic, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
    }
}
