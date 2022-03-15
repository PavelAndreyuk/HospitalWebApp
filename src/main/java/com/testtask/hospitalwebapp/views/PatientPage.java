package com.testtask.hospitalwebapp.views;

import com.testtask.hospitalwebapp.models.Patient;
import com.testtask.hospitalwebapp.services.PatientService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "patients")
public class PatientPage extends HorizontalLayout {
    private final PatientService patientService;

    private void insertPatients() {
        patientService.save(new Patient("Pavel", "Andreyuk", "Alekseevich", "89171221876"));
        patientService.save(new Patient("Daria", "Evdokimova", "Anatolevna", "89297126421"));
    }

    @Autowired
    public PatientPage(PatientService patientService) {
        this.patientService = patientService;

        insertPatients();

        Grid<Patient> patientsGrid = new Grid<>();
        patientsGrid.addColumn(Patient::getName).setHeader("Name");
        patientsGrid.addColumn(Patient::getSurname).setHeader("Surname");
        patientsGrid.addColumn(Patient::getPatronymic).setHeader("Patronymic");
        patientsGrid.addColumn(Patient::getPhoneNumber).setHeader("Phone number");

        patientsGrid.setItems(patientService.getAll());

        add(patientsGrid);
    }
}
