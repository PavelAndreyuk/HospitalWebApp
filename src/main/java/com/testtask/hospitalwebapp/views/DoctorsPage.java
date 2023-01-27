package com.testtask.hospitalwebapp.views;

import com.testtask.hospitalwebapp.annotations.Permissions;
import com.testtask.hospitalwebapp.models.Doctor;
import com.testtask.hospitalwebapp.services.BaseService;
import com.testtask.hospitalwebapp.services.DoctorService;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Permissions({"doctors"})
@Route(value = "doctors", layout = RootLayout.class)
public class DoctorsPage extends AbstractGridPage<Doctor> {

    private final DoctorService doctorService;

    private Binder<Doctor> binder;

    private TextField nameField;
    private TextField surnameField;
    private TextField patronymicField;
    private TextField specialisationField;

    @Autowired
    public DoctorsPage(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Override
    protected void createGrid() {
        addColumn(Doctor::getSurname, "Surname");
        addColumn(Doctor::getName, "Name");
        addColumn(Doctor::getPatronymic, "Patronymic");
        addColumn(Doctor::getSpecialisation, "Specialisation");
    }

    @Override
    protected void addFieldsToEditorForm() {
        getEditorFormLayout().add(surnameField, nameField, patronymicField, specialisationField);
    }

    @Override
    protected void initializeFormFields() {
        nameField = new TextField("Surname");
        surnameField = new TextField("Name");
        patronymicField = new TextField("Patronymic");
        specialisationField = new TextField("Specialisation");

        configureField(surnameField, "surname");
        configureField(nameField, "name");
        configureField(patronymicField, "patronymic");
        configureField(specialisationField, "specialisation");
    }

    @Override
    public Binder<Doctor> getBinder() {
        if (binder == null) {
            binder = new BeanValidationBinder<>(Doctor.class);
        }
        return binder;
    }

    @Override
    protected BaseService<Doctor> getService() {
        return doctorService;
    }

    @Override
    protected Doctor createBlankEntity() {
        return new Doctor();
    }
}
