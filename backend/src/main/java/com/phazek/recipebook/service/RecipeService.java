package com.phazek.recipebook.service;

import com.phazek.recipebook.model.Recipe;
import com.phazek.recipebook.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;

	// Fetch all recipes
	public List<Recipe> getAllRecipes() {
		return recipeRepository.findAll();
	}

	// Fetch a recipe by ID
	public Optional<Recipe> getRecipeById(Long id) {
		return recipeRepository.findById(id);
	}

	// Fetch a recipe by ID
	public List<Recipe> getRecipeByTitle(String title) {
		return recipeRepository.findByTitleContainingIgnoreCase(title);
	}

	// Save a new or updated recipe
	public Recipe saveRecipe(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	// Delete a recipe by ID
	public void deleteRecipe(Long id) {
		recipeRepository.deleteById(id);
	}
}
