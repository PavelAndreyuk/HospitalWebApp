package com.testtask.hospitalwebapp.repositories;

import com.testtask.hospitalwebapp.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findById(long id);
}
