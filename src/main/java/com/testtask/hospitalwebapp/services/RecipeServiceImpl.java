package com.testtask.hospitalwebapp.services;

import com.testtask.hospitalwebapp.models.Recipe;
import com.testtask.hospitalwebapp.repositories.RecipeRepository;
import com.testtask.hospitalwebapp.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Profile("prod")
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public Recipe save(@NotNull Recipe recipe) {
        if (recipe == null) throw new NullPointerException("Recipe required");

        return recipeRepository.save(recipe);
    }

    @Override
    public void delete(Recipe recipe) {
        if (recipe == null) throw new NullPointerException("Recipe required");

        recipeRepository.delete(recipe);
    }

    @Override
    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }
}
