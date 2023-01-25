package com.testtask.hospitalwebapp.models;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "recipes")
@Data
public class Recipe {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    private String description;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    //    private Date dateOfCreation;
//    private int validity;

    private Priority priority;
}
