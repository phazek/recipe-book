package com.phazek.recipebook.repository;

import com.phazek.recipebook.model.Recipe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	List<Recipe> findByTitleContainingIgnoreCase(String title);
}
