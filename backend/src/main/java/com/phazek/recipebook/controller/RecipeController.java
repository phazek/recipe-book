package com.phazek.recipebook.controller;

import com.phazek.recipebook.model.Recipe;
import com.phazek.recipebook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;

	// Get all recipes
	@GetMapping
	public List<Recipe> getAllRecipes() {
		return recipeService.getAllRecipes();
	}

	// Get a recipe by ID
	@GetMapping("/{id}")
	public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
		Optional<Recipe> recipe = recipeService.getRecipeById(id);
		return recipe.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Add a new recipe
	@PostMapping
	public Recipe createRecipe(@RequestBody Recipe recipe) {
		return recipeService.saveRecipe(recipe);
	}

	// Update an existing recipe
	@PutMapping("/{id}")
	public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody Recipe recipeDetails) {
		Optional<Recipe> recipeOptional = recipeService.getRecipeById(id);
		if (recipeOptional.isPresent()) {
			Recipe recipe = recipeOptional.get();
			recipe.setTitle(recipeDetails.getTitle());
			recipe.setInstructions(recipeDetails.getInstructions());
			recipe.setIngredients(recipeDetails.getIngredients());
			Recipe updatedRecipe = recipeService.saveRecipe(recipe);
			return ResponseEntity.ok(updatedRecipe);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Delete a recipe
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
		if (recipeService.getRecipeById(id).isPresent()) {
			recipeService.deleteRecipe(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/search")
	public List<Recipe> searchRecipes(@RequestParam String title) {
		return recipeService.getRecipeByTitle(title);
	}
}
