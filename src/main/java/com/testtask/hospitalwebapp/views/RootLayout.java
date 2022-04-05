package com.testtask.hospitalwebapp.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;

public class RootLayout extends AppLayout implements RouterLayout {
    public RootLayout() {
        DrawerToggle toggle = new DrawerToggle();
        VerticalLayout tabs = new VerticalLayout();

        tabs.add(new RouterLink("Home", HomePage.class));
        tabs.add(new RouterLink("Patients", PatientsPage.class));
        tabs.add(new RouterLink("Doctors", DoctorsPage.class));
        tabs.add(new RouterLink("Recipes", RecipesPage.class));

        addToDrawer(tabs);
        addToNavbar(toggle);
    }
}
