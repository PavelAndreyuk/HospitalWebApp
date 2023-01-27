package com.testtask.hospitalwebapp.views;

import com.testtask.hospitalwebapp.annotations.Permissions;
import com.testtask.hospitalwebapp.models.Patient;
import com.testtask.hospitalwebapp.services.BaseService;
import com.testtask.hospitalwebapp.services.PatientService;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Permissions({"patients"})
@Route(value = "patients", layout = RootLayout.class)
public class PatientsPage extends AbstractGridPage<Patient> {

    private final PatientService patientService;

    private Binder<Patient> binder;

    private TextField nameField;
    private TextField surnameField;
    private TextField patronymicField;
    private TextField phoneNumberField;

    @Autowired
    public PatientsPage(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    protected void createGrid() {
        addColumn(Patient::getSurname, "Surname");
        addColumn(Patient::getName, "Name");
        addColumn(Patient::getPatronymic, "Patronymic");
        addColumn(Patient::getPhoneNumber, "Phone number");
    }

    @Override
    protected void addFieldsToEditorForm() {
        getEditorFormLayout().add(surnameField, nameField, patronymicField, phoneNumberField);
    }

    protected void initializeFormFields() {
        nameField = new TextField("Name");
        surnameField = new TextField("Surname");
        patronymicField = new TextField("Patronymic");
        phoneNumberField = new TextField("Phone number");

        configureField(surnameField, "surname");
        configureField(nameField, "name");
        configureField(patronymicField, "patronymic");
        configureField(phoneNumberField, "phoneNumber");

    }

    @Override
    public Binder<Patient> getBinder() {
        if (binder == null) {
            binder = new BeanValidationBinder<>(Patient.class);
        }
        return binder;
    }

    @Override
    protected BaseService<Patient> getService() {
        return patientService;
    }

    @Override
    protected Patient createBlankEntity() {
        return new Patient();
    }
}
