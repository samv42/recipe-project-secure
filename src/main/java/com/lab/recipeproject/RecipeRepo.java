package com.lab.recipeproject;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepo extends JpaRepository<Recipe, Long> {

    List<Recipe> findByNameContainingIgnoreCase(String name);
    Recipe findByNameAndDifficultyRating (String name, Integer difficultyRating);
    List<Recipe> findByUsername(String username);
}