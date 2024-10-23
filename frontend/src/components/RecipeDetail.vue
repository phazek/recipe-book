<template>
  <div>
    <h1>{{ recipe.title }}</h1>
    <ul>
      <li v-for="(ingredient, index) in recipe.ingredients" :key="index">
        {{ ingredient.name }} - {{ ingredient.quantity }} {{ ingredient.unitOfMeasure }}
      </li>
    </ul>
    <ol>
      <li v-for="(instruction, index) in recipe.instructions" :key="index">{{ instruction }}</li>
    </ol>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      recipe: {
        title: '',
        ingredients: [],
        instructions: []
      }
    };
  },
  created() {
    const recipeId = this.$route.params.id; // assuming you have a route parameter
    axios.get(`http://localhost:8080/api/recipes/${recipeId}`)
      .then(response => {
        this.recipe = response.data;
      })
      .catch(error => {
        console.error("There was an error fetching the recipe!", error);
      });
  }
};
</script>
