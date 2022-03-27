package com.testtask.hospitalwebapp.services;

import com.testtask.hospitalwebapp.models.Patient;
import com.testtask.hospitalwebapp.repositories.PatientRepository;
import com.testtask.hospitalwebapp.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Profile("prod")
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Nullable
    @Override
    public Patient save(@NotNull Patient patient) {
        if (patient == null) throw new NullPointerException("Patient required");

        return patientRepository.save(patient);
    }

    @Override
    public void delete(@NotNull Patient patient) {
        if (patient == null) throw new NullPointerException("Patient required");

        patientRepository.delete(patient);
    }

    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }
}
