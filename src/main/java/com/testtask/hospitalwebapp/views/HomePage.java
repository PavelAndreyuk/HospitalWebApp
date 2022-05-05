package com.testtask.hospitalwebapp.views;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = RootLayout.class)
public class HomePage extends AppBar {

    public HomePage() {
        add(new Span("Welcome to Hospital Web App"));
    }
}
