package com.testtask.hospitalwebapp.views;

import com.testtask.hospitalwebapp.models.Doctor;
import com.testtask.hospitalwebapp.services.DoctorService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
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

@Route(value = "doctors", layout = RootLayout.class)
public class DoctorsPage extends AppBar implements BeforeEnterObserver {
    private final DoctorService doctorService;

    private final Grid<Doctor> patientsGrid = new Grid<>();
    private final List<Doctor> doctors = new ArrayList<>();
    private final Binder<Doctor> binder = new BeanValidationBinder<>(Doctor.class);

    private final HorizontalLayout mainLayout = new HorizontalLayout();
    private final VerticalLayout doctorsGridLayout = new VerticalLayout();
    private final VerticalLayout editorFormLayout = new VerticalLayout();
    private final HorizontalLayout formToolbar = new HorizontalLayout();
    private final HorizontalLayout bottomToolbar = new HorizontalLayout();

    private final Span headerSpan = new Span();

    private final TextField nameField = new TextField();
    private final TextField surnameField = new TextField();
    private final TextField patronymicField = new TextField();
    private final TextField specialisationField = new TextField();

    private final Button removeButton = new Button("Remove");
    private final Button cancelButton = new Button("Cancel");
    private final Button saveButton = new Button("Save");
    private final Button addButton = new Button("Add");

    private Doctor selectedDoctor;

    @Autowired
    public DoctorsPage(DoctorService doctorService) {
        this.doctorService = doctorService;

        createGrid();
        createEditorForm();
        createFormToolbar();
        createBottomToolbar();

        hideEditorForm();

        doctorsGridLayout.add(patientsGrid);
        mainLayout.setHeightFull();
        mainLayout.setWidthFull();
        mainLayout.add(doctorsGridLayout, editorFormLayout);
        add(mainLayout, bottomToolbar);
    }

    private void createGrid() {
        patientsGrid.addColumn(Doctor::getSurname).setHeader("Surname").setSortable(true);
        patientsGrid.addColumn(Doctor::getName).setHeader("Name").setSortable(true);
        patientsGrid.addColumn(Doctor::getPatronymic).setHeader("Patronymic").setSortable(true);
        patientsGrid.addColumn(Doctor::getSpecialisation).setHeader("Specialisation").setSortable(true);

        patientsGrid.addSelectionListener(event -> {
            Set<Doctor> selectedItems = event.getAllSelectedItems();
            for (Doctor selectedItem : selectedItems) {
                selectedDoctor = selectedItem;
            }
            if (selectedItems.isEmpty()) {
                hideEditorForm();
            } else {
                showEditorForm();
            }
        });

    }

    private void createEditorForm() {
        initializeFormFields();

        editorFormLayout.setWidth("500px");
        editorFormLayout.setAlignItems(Alignment.CENTER);
        editorFormLayout.add(headerSpan, surnameField, nameField, patronymicField, specialisationField, formToolbar);
        editorFormLayout.getChildren().forEach(component -> component.getElement().getStyle().set("width", "100%"));
    }

    private void initializeFormFields() {
        surnameField.setLabel("Surname");
        binder.forField(surnameField).bind("surname");

        nameField.setLabel("Name");
        binder.forField(nameField).bind("name");

        patronymicField.setLabel("Patronymic");
        binder.forField(patronymicField).bind("patronymic");

        specialisationField.setLabel("Specialisation");
        binder.forField(specialisationField).bind("specialisation");
    }

    private void displayData() {
        doctors.clear();
        doctors.addAll(doctorService.getAll());
        patientsGrid.setItems(doctors);
    }

    private void createFormToolbar() {
        saveButton.addClickListener(event -> save());
        cancelButton.addClickListener(event -> binder.readBean(selectedDoctor));
        removeButton.addClickListener(event -> {
            doctorService.delete(selectedDoctor);
            displayData();
            selectedDoctor = new Doctor();
            hideEditorForm();
        });
        formToolbar.add(removeButton, cancelButton, saveButton);
    }

    private void save() {
        if (binder.writeBeanIfValid(selectedDoctor)) {
            selectedDoctor = doctorService.save(selectedDoctor);
        }
        displayData();
        patientsGrid.select(selectedDoctor);
    }

    private void createBottomToolbar() {
        addButton.addClickListener(event -> {
            selectedDoctor = new Doctor();
            patientsGrid.deselectAll();
            showEditorForm();
        });
        bottomToolbar.add(addButton);
    }

    private void showEditorForm() {
        binder.readBean(selectedDoctor);
        editorFormLayout.setVisible(true);
    }

    private void hideEditorForm() {
        editorFormLayout.setVisible(false);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        displayData();
    }
}
