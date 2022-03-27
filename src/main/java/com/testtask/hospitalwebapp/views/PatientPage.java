package com.testtask.hospitalwebapp.views;

import com.testtask.hospitalwebapp.models.Patient;
import com.testtask.hospitalwebapp.services.PatientService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Route(value = "patients", layout = RootLayout.class)
public class PatientPage extends AppBar implements BeforeEnterObserver {
    private final PatientService patientService;

    private final Grid<Patient> patientsGrid = new Grid<>();
    private final List<Patient> patients = new ArrayList<>();
    private final ListDataProvider<Patient> patientsProvider = new ListDataProvider<>(patients);
    private final Binder<Patient> binder = new BeanValidationBinder<>(Patient.class);

    private final HorizontalLayout mainLayout = new HorizontalLayout();
    private final VerticalLayout patientsGridLayout = new VerticalLayout();
    private final VerticalLayout editorFormLayout = new VerticalLayout();
    private final HorizontalLayout formToolbar = new HorizontalLayout();
    private final HorizontalLayout bottomToolbar = new HorizontalLayout();

    private final Span headerSpan = new Span();

    private final TextField nameField = new TextField();
    private final TextField surnameField = new TextField();
    private final TextField patronymicField = new TextField();
    private final TextField phoneNumberField = new TextField();

    private final Button removeButton = new Button("Remove");
    private final Button cancelButton = new Button("Cancel");
    private final Button saveButton = new Button("Save");
    private final Button addButton = new Button("Add");

    private Patient selectedPatient;

    @Autowired
    public PatientPage(PatientService patientService) {
        this.patientService = patientService;

        createGrid();
        createEditorForm();
        createFormToolbar();
        createBottomToolbar();

        hideEditorForm();

        patientsGridLayout.add(patientsGrid);
        mainLayout.setHeightFull();
        mainLayout.setWidthFull();
        mainLayout.add(patientsGridLayout, editorFormLayout);
        add(mainLayout, bottomToolbar);
    }

    private void createGrid() {
        patientsGrid.addColumn(Patient::getSurname).setHeader("Surname").setSortable(true);
        patientsGrid.addColumn(Patient::getName).setHeader("Name").setSortable(true);
        patientsGrid.addColumn(Patient::getPatronymic).setHeader("Patronymic").setSortable(true);
        patientsGrid.addColumn(Patient::getPhoneNumber).setHeader("Phone number").setSortable(true);

        patientsGrid.addSelectionListener(event -> {
            Set<Patient> selectedItems = event.getAllSelectedItems();
            for (Patient selectedItem : selectedItems) {
                selectedPatient = selectedItem;
            }
            if (selectedItems.isEmpty()) {
                hideEditorForm();
            } else {
                showEditorForm();
            }
        });

        patientsGrid.setDataProvider(patientsProvider);
    }

    private void createEditorForm() {
        initializeFormFields();

        editorFormLayout.setWidth("500px");
        editorFormLayout.setAlignItems(Alignment.CENTER);
        editorFormLayout.add(headerSpan, surnameField, nameField, patronymicField, phoneNumberField, formToolbar);
        editorFormLayout.getChildren().forEach(component -> component.getElement().getStyle().set("width", "100%"));
    }

    private void initializeFormFields() {
        surnameField.setLabel("Surname");
        binder.forField(surnameField).bind("surname");

        nameField.setLabel("Name");
        binder.forField(nameField).bind("name");

        patronymicField.setLabel("Patronymic");
        binder.forField(patronymicField).bind("patronymic");

        phoneNumberField.setLabel("Phone number");
        binder.forField(phoneNumberField).bind("phoneNumber");
    }

    private void displayData() {
        patients.clear();
        patients.addAll(patientService.getAll());
        patientsGrid.getDataProvider().refreshAll();
    }

    private void createFormToolbar() {
        saveButton.addClickListener(event -> save());
        cancelButton.addClickListener(event -> binder.readBean(selectedPatient));
        removeButton.addClickListener(event -> {
            patientService.delete(selectedPatient);
            displayData();
            selectedPatient = new Patient();
            hideEditorForm();
        });
        formToolbar.add(removeButton, cancelButton, saveButton);
    }

    private void save() {
        if (binder.writeBeanIfValid(selectedPatient)) {
             patientService.save(selectedPatient);
        }
        displayData();
    }

    private void createBottomToolbar() {
        addButton.addClickListener(event -> {
            selectedPatient = new Patient();
            patientsGrid.deselectAll();
            showEditorForm();
        });
        bottomToolbar.add(addButton);
    }

    private void showEditorForm() {
        binder.readBean(selectedPatient);
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
