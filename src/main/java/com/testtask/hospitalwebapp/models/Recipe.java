package com.testtask.hospitalwebapp.models;

import com.testtask.hospitalwebapp.PostgreSQLEnumType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "recipes")
@Data
@TypeDefs({
        @TypeDef(
                name = "pgsql_enum",
                typeClass = PostgreSQLEnumType.class
        )
})
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

    @Column(name = "creation_date")
    private LocalDate dateOfCreation;

    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private Priority priority;
}
