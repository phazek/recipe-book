<template>
  <div>
    <h1>Recipe List</h1>
    <ul>
      <li v-for="recipe in recipes" :key="recipe.id">
        <router-link :to="`recipes/${recipe.id}`">{{ recipe.title }}</router-link>
        <button @click="deleteRecipe(recipe.id)">Delete</button>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      recipes: []
    };
  },
  created() {
    this.fetchRecipes();
  },
  methods: {
    fetchRecipes() {
      axios.get('http://localhost:8080/api/recipes')
        .then(response => {
          this.recipes = response.data;
        })
        .catch(error => {
          console.error("There was an error fetching the recipes!", error);
        });
    },
    deleteRecipe(recipeId) {
      try {
        axios.delete(`http://localhost:8080/api/recipes/${recipeId}`); // Adjust the URL based on your API
        this.recipes = this.recipes.filter(recipe => recipe.id !== recipeId); // Remove the deleted recipe from the list
      } catch (error) {
        console.error('There was an error deleting the recipe!', error);
      }
    }
  }
};
</script>
