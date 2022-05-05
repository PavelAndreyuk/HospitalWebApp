package com.testtask.hospitalwebapp.services;

import com.testtask.hospitalwebapp.models.Doctor;
import com.testtask.hospitalwebapp.repositories.DoctorRepository;
import com.testtask.hospitalwebapp.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Profile("prod")
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Nullable
    @Override
    @Transactional
    public Doctor save(@NotNull Doctor doctor) {
        if (doctor == null) throw new NullPointerException("Doctor required");

        return doctorRepository.save(doctor);
    }

    @Override
    public void delete(@NotNull Doctor doctor) {
        if (doctor == null) throw new NullPointerException("Doctor required");

        doctorRepository.delete(doctor);
    }

    @Override
    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }
}
