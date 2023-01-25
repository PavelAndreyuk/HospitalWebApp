package com.testtask.hospitalwebapp.views;

import com.testtask.hospitalwebapp.annotations.Permissions;
import com.testtask.hospitalwebapp.models.Doctor;
import com.testtask.hospitalwebapp.services.BaseService;
import com.testtask.hospitalwebapp.services.DoctorService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEnterEvent;
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
    protected void createEditorForm() {
        editorFormLayout.add(headerSpan, surnameField, nameField, patronymicField, specialisationField, formToolbar);
    }

    @Override
    protected void initializeFormFields() {
        nameField = new TextField();
        surnameField = new TextField();
        patronymicField = new TextField();
        specialisationField = new TextField();

        surnameField.setLabel("Surname");
        getBinder().forField(surnameField).bind("surname");

        nameField.setLabel("Name");
        getBinder().forField(nameField).bind("name");

        patronymicField.setLabel("Patronymic");
        getBinder().forField(patronymicField).bind("patronymic");

        specialisationField.setLabel("Specialisation");
        getBinder().forField(specialisationField).bind("specialisation");
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
