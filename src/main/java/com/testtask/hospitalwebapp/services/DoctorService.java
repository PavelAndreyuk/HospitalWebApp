package com.testtask.hospitalwebapp.services;

import com.testtask.hospitalwebapp.models.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor save(Doctor doctor);

    void delete(Doctor doctor);

    List<Doctor> getAll();
}
