package com.testtask.hospitalwebapp.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "patients")
@Data
public class Patient {
    @Id
    @GeneratedValue
    @Column(name = "patient_id")
    private Long id;

    @NotBlank
    @NotNull
    private String surname;

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String patronymic;

    @NotBlank
    @NotNull
    private String phoneNumber;
}
