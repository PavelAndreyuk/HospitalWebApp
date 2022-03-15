package com.testtask.hospitalwebapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "recipes")
@Setter
@Getter
@ToString
@AllArgsConstructor
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

    public Recipe() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return description.equals(recipe.description) && patient.equals(recipe.patient) && doctor.equals(recipe.doctor) && priority == recipe.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, patient, doctor, priority);
    }
}
