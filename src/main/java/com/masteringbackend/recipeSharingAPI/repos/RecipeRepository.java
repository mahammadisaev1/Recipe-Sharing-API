package com.masteringbackend.recipeSharingAPI.repos;

import com.masteringbackend.recipeSharingAPI.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByTitleContainingIgnoreCase(String title);
    List<Recipe> findByOwnerId(Long ownerId);
}
