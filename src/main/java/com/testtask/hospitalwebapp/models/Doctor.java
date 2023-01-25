package com.testtask.hospitalwebapp.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "doctors")
@Data
public class Doctor {
    @Id
    @GeneratedValue
    @Column(name = "doctor_id")
    private Long id;

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String surname;

    @NotBlank
    @NotNull
    private String patronymic;

    @NotBlank
    @NotNull
    private String specialisation;
}
