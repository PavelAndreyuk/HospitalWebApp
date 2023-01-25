package com.testtask.hospitalwebapp.services;

import com.testtask.hospitalwebapp.models.Recipe;

import java.util.List;

public interface RecipeService {

    Recipe save(Recipe recipe);

    void delete(Recipe recipe);

    List<Recipe> getAll();
}
