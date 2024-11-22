package com.masteringbackend.recipeSharingAPI.controllers;

import com.masteringbackend.recipeSharingAPI.entities.Recipe;
import com.masteringbackend.recipeSharingAPI.entities.User;
import com.masteringbackend.recipeSharingAPI.services.RecipeService;
import com.masteringbackend.recipeSharingAPI.services.UserService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;
    private final UserService userService;


    @PostMapping("/register")
    @PermitAll
    public Recipe createRecipe(@RequestBody Recipe recipe) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Kullanıcı adı (JWT'den geliyor)

        // Find user in db
        User owner = userService.getUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // set that user as an owner of recipe
        recipe.setOwner(owner);

        return recipeService.createRecipe(recipe);
    }

    @GetMapping("/{id}")
    public Recipe getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/search")
    public List<Recipe> searchRecipes(@RequestParam String title) {
        return recipeService.searchRecipes(title);
    }

    @GetMapping("/user/{userId}")
    public List<Recipe> getRecipesByUser(@PathVariable Long userId) {
        return recipeService.getRecipesByUser(userId);
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@PathVariable Long id, @RequestBody Recipe updatedRecipe) {
        return recipeService.updateRecipe(id, updatedRecipe);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }
}
