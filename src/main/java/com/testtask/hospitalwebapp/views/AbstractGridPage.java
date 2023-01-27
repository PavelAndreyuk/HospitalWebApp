package com.testtask.hospitalwebapp.views;

import com.testtask.hospitalwebapp.services.BaseService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class AbstractGridPage<ROW> extends AppBar implements BeforeEnterObserver {

    private final Grid<ROW> grid = new Grid<>();
    private final List<ROW> items = new ArrayList<>();

    private final HorizontalLayout mainLayout = new HorizontalLayout();
    private final VerticalLayout gridLayout = new VerticalLayout();
    private final HorizontalLayout bottomToolbar = new HorizontalLayout();
    private final HorizontalLayout formToolbar = new HorizontalLayout();

    private VerticalLayout editorFormLayout;

    private final Button deleteButton = new Button("Delete");
    private final Button cancelButton = new Button("Cancel");
    private final Button saveButton = new Button("Save");
    private final Button addButton = new Button("Add");

    private ROW selectedRow;

    public AbstractGridPage() {
        createGrid();
        initialiseEditorForm();
        createEditorForm();
        createFormToolbar();
        createBottomToolbar();

        hideEditorForm();

        addSelectorListenerToGrid();

        gridLayout.add(grid);
        mainLayout.setSizeFull();
        mainLayout.add(gridLayout, editorFormLayout);

        add(mainLayout, bottomToolbar);
    }

    protected abstract void createGrid();

    private void createEditorForm() {
        addFieldsToEditorForm();
        editorFormLayout.add(formToolbar);
    }

    protected abstract void addFieldsToEditorForm();

    private void addSelectorListenerToGrid() {
        grid.addSelectionListener(event -> {
            Set<ROW> selectedItems = event.getAllSelectedItems();
            for (ROW selectedItem : selectedItems) {
                selectedRow = selectedItem;
            }
            if (selectedItems.isEmpty()) {
                hideEditorForm();
            } else {
                showEditorForm();
                deleteButton.setVisible(true);
            }
        });
    }

    private void initialiseEditorForm() {
        initializeFormFields();

        editorFormLayout = new VerticalLayout();

        editorFormLayout.setWidth("500px");
        editorFormLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        editorFormLayout.getChildren().forEach(component -> component.getElement().getStyle().set("width", "100%"));
    }

    protected abstract void initializeFormFields();

    private void displayData() {
        items.clear();
        items.addAll(getService().getAll());
        grid.setItems(items);
    }

    private void createFormToolbar() {
        saveButton.addClickListener(event -> save());
        cancelButton.addClickListener(event -> getBinder().readBean(selectedRow));
        deleteButton.addClickListener(event -> {
            getService().delete(selectedRow);
            displayData();
            selectedRow = createBlankEntity();
            hideEditorForm();
        });

        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        saveButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        formToolbar.add(deleteButton, cancelButton, saveButton);
        formToolbar.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
    }

    private void save() {
        if (getBinder().writeBeanIfValid(selectedRow)) {
            selectedRow = getService().save(selectedRow);
            Notification.show("Data has been saved");
            displayData();
            items.stream()
                    .filter(patient -> patient.equals(selectedRow))
                    .findFirst()
                    .ifPresent(selectedPatient -> {
                        this.selectedRow = selectedPatient;
                        grid.select(selectedPatient);
                    });
        }
    }

    protected void addColumn(ValueProvider<ROW, ?> valueProvider, String header) {
        grid.addColumn(valueProvider).setHeader(header).setSortable(true);
    }

    private void createBottomToolbar() {
        addButton.addClickListener(event -> {
            selectedRow = createBlankEntity();
            grid.deselectAll();
            showEditorForm();
            deleteButton.setVisible(false);
        });
        bottomToolbar.add(addButton);
    }

    private void showEditorForm() {
        getBinder().readBean(selectedRow);
        editorFormLayout.setVisible(true);
    }

    private void hideEditorForm() {
        editorFormLayout.setVisible(false);
    }

    // TODO get rid of this getter
    public VerticalLayout getEditorFormLayout() {
        return editorFormLayout;
    }

    // TODO get rid of lazy initialisation
    protected abstract Binder<ROW> getBinder();

    protected abstract BaseService<ROW> getService();

    protected abstract ROW createBlankEntity();

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        displayData();
    }
}
