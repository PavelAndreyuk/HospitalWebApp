package com.testtask.hospitalwebapp.services.inmemory;

import com.testtask.hospitalwebapp.models.Patient;
import com.testtask.hospitalwebapp.services.PatientService;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Profile("dev")
public class InMemoryPatientService implements PatientService {
    private final Map<Long, Patient> patients = new HashMap<>();

    @Nullable
    @Override
    public Patient save(@NotNull Patient patient) {
        if (patient == null) throw new NullPointerException("Patient required");

        return patients.put(patient.getId(), patient);
    }

    @Override
    public void delete(@NotNull Patient patient) {
        if (patient == null) throw new NullPointerException("Patient required");

        patients.remove(patient.getId());
    }

    @Override
    public List<Patient> getAll() {
        return new ArrayList<>(patients.values());
    }
}
