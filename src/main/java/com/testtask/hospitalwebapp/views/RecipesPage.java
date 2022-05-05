package com.testtask.hospitalwebapp.views;

import com.testtask.hospitalwebapp.annotations.Permissions;
import com.vaadin.flow.router.Route;

@Permissions({"recipes"})
@Route(value = "recipes", layout = RootLayout.class)
public class RecipesPage extends AppBar {
    public RecipesPage() {
    }
}
