package com.testtask.hospitalwebapp.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "recipes")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    //    private Date dateOfCreation;
//    private int validity;
    @Column(name = "priority")
    private Priority priority;

    public Recipe(String description, Patient patient, Doctor doctor, Priority priority) {
        this.description = description;
        this.patient = patient;
        this.doctor = doctor;
        this.priority = priority;
    }


}
