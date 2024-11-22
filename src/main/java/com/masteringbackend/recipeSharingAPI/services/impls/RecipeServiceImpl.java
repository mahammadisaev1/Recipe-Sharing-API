package com.masteringbackend.recipeSharingAPI.services.impls;

import com.masteringbackend.recipeSharingAPI.entities.Recipe;
import com.masteringbackend.recipeSharingAPI.repos.RecipeRepository;
import com.masteringbackend.recipeSharingAPI.services.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepo;



    @Override
    public Recipe createRecipe(Recipe recipe) {
        return recipeRepo.save(recipe);
    }

    @Override
    public Recipe getRecipeById(Long id) {
        return recipeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found with id: " + id));
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepo.findAll();
    }

    @Override
    public List<Recipe> searchRecipes(String title) {
        return recipeRepo.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Recipe> getRecipesByUser(Long userId) {
        return recipeRepo.findByOwnerId(userId);
    }

    @Override
    public Recipe updateRecipe(Long id, Recipe updatedRecipe) {
        Recipe existingRecipe = getRecipeById(id);
        existingRecipe.setTitle(updatedRecipe.getTitle());
        existingRecipe.setDescription(updatedRecipe.getDescription());
        existingRecipe.setIngredients(updatedRecipe.getIngredients());
        existingRecipe.setCookingInstructions(updatedRecipe.getCookingInstructions());
        existingRecipe.setPhotoUrl(updatedRecipe.getPhotoUrl());
        return recipeRepo.save(existingRecipe);
    }

    @Override
    public void deleteRecipe(Long id) {
        recipeRepo.deleteById(id);
    }
}
