package com.masteringbackend.recipeSharingAPI.services;

import com.masteringbackend.recipeSharingAPI.entities.Recipe;

import java.util.List;

public interface RecipeService {
    Recipe createRecipe(Recipe recipe);
    Recipe getRecipeById(Long id);
    List<Recipe> getAllRecipes();
    List<Recipe> searchRecipes(String title);
    List<Recipe> getRecipesByUser(Long userId);
    Recipe updateRecipe(Long id, Recipe updatedRecipe);
    void deleteRecipe(Long id);
}
