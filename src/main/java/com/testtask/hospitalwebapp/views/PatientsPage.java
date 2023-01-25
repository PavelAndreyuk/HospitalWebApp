package com.testtask.hospitalwebapp.views;

import com.testtask.hospitalwebapp.annotations.Permissions;
import com.testtask.hospitalwebapp.models.Doctor;
import com.testtask.hospitalwebapp.models.Patient;
import com.testtask.hospitalwebapp.services.BaseService;
import com.testtask.hospitalwebapp.services.PatientService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    protected void createEditorForm() {
        editorFormLayout.add(headerSpan, surnameField, nameField, patronymicField, phoneNumberField, formToolbar);
    }

    protected void initializeFormFields() {
        nameField = new TextField();
        surnameField = new TextField();
        patronymicField = new TextField();
        phoneNumberField = new TextField();

        surnameField.setLabel("Surname");
        getBinder().forField(surnameField).bind("surname");

        nameField.setLabel("Name");
        getBinder().forField(nameField).bind("name");

        patronymicField.setLabel("Patronymic");
        getBinder().forField(patronymicField).bind("patronymic");

        phoneNumberField.setLabel("Phone number");
        getBinder().forField(phoneNumberField).bind("phoneNumber");
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
