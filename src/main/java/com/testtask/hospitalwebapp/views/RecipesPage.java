package com.testtask.hospitalwebapp.views;

import com.testtask.hospitalwebapp.annotations.Permissions;
import com.testtask.hospitalwebapp.models.Doctor;
import com.testtask.hospitalwebapp.models.Patient;
import com.testtask.hospitalwebapp.models.Priority;
import com.testtask.hospitalwebapp.models.Recipe;
import com.testtask.hospitalwebapp.services.BaseService;
import com.testtask.hospitalwebapp.services.DoctorService;
import com.testtask.hospitalwebapp.services.PatientService;
import com.testtask.hospitalwebapp.services.RecipeService;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Permissions({"recipes"})
@Route(value = "recipes", layout = RootLayout.class)
public class RecipesPage extends AbstractGridPage<Recipe> {

    private final RecipeService recipeService;
    private final PatientService patientService;
    private final DoctorService doctorService;

    private Binder<Recipe> binder;

    private TextField descriptionField;
    private Select<Patient> patientField;
    private Select<Doctor> doctorField;
    private DatePicker dateOfCreationField;
    private Select<Priority> priorityField;


    @Autowired
    public RecipesPage(RecipeService recipeService,
                       PatientService patientService,
                       DoctorService doctorService) {
        this.recipeService = recipeService;
        this.patientService = patientService;
        this.doctorService = doctorService;

        patientField.setItems(patientService.getAll());
        patientField.setItemLabelGenerator(patient -> patient.getSurname() + " " + patient.getName() + " " + patient.getPatronymic());

        doctorField.setItems(doctorService.getAll());
        doctorField.setItemLabelGenerator(doctor -> doctor.getSurname() + " " + doctor.getName() + " " + doctor.getPatronymic());

        priorityField.setItems(Priority.values());
    }

    @Override
    protected void createGrid() {
        addColumn(Recipe::getDescription, "Description");
        addColumn(Recipe::getPatient, "Patient");
        addColumn(Recipe::getDoctor, "Doctor");
        addColumn(Recipe::getDateOfCreation, "Date of creation");
        addColumn(Recipe::getPriority, "Priority");
    }

    @Override
    protected void addFieldsToEditorForm() {
        getEditorFormLayout().add(descriptionField, patientField, doctorField, dateOfCreationField, priorityField);
    }

    @Override
    protected void initializeFormFields() {
        descriptionField = new TextField();
        patientField = new Select<>();
        doctorField = new Select<>();
        dateOfCreationField = new DatePicker();
        priorityField = new Select<>();

        configureField(descriptionField, "description");
        configureField(patientField, "patient");
        configureField(doctorField, "doctor");
        configureField(dateOfCreationField, "dateOfCreation");
        configureField(priorityField, "priority");
    }

    @Override
    protected Binder<Recipe> getBinder() {
        if (binder == null) {
            binder = new BeanValidationBinder<>(Recipe.class);
        }
        return binder;
    }

    @Override
    protected BaseService<Recipe> getService() {
        return recipeService;
    }

    @Override
    protected Recipe createBlankEntity() {
        return new Recipe();
    }
}
