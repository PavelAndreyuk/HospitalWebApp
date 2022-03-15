package com.testtask.hospitalwebapp.repositories;

import com.testtask.hospitalwebapp.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
