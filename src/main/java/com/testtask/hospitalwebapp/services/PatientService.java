package com.testtask.hospitalwebapp.services;

import com.testtask.hospitalwebapp.models.Patient;

import java.util.List;

public interface PatientService {

    Patient save(Patient patient);

    void delete(Patient patient);

    List<Patient> getAll();
}
