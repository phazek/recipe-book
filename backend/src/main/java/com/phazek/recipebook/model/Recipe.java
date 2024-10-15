package com.phazek.recipebook.model;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;

	@Lob
	private String ingredients;
	@Lob
	private String instructions;

	// Constructors, getters, and setters
	public Recipe() {
	}

	public Recipe(String title, String ingredients, String instructions) {
		this.title = title;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	// Helper method to convert list of ingredients to JSON string
	@JsonSetter("ingredients")
	public void setIngredientsFromList(List<Ingredient> ingredientList) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		this.ingredients = objectMapper.writeValueAsString(ingredientList);
	}

	// Helper method to get ingredients as list from JSON string
	@JsonGetter("ingredients")
	public List<Ingredient> getIngredientsAsList() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(this.ingredients,
				objectMapper.getTypeFactory().constructCollectionType(List.class, Ingredient.class));
	}

	// Helper method to convert list of instructions to JSON string
	@JsonSetter("instructions")
	public void setInstructionsFromList(List<String> instructionList) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		this.instructions = objectMapper.writeValueAsString(instructionList);
	}

	// Helper method to get instructions as list from JSON string
	@JsonGetter("instructions")
	public List<String> getInstructionsAsList() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(this.instructions,
				objectMapper.getTypeFactory().constructCollectionType(List.class, String.class));
	}
}
