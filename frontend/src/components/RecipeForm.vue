<template>
  <form @submit.prevent="saveRecipe">
    <div>
      <label>Title:</label>
      <input v-model="recipe.title" />
    </div>
    <div>
      <label>Ingredients:</label>
      <textarea v-model="recipe.ingredients"></textarea>
    </div>
    <div>
      <label>Instructions:</label>
      <textarea v-model="recipe.instructions"></textarea>
    </div>
    <button type="submit">Save</button>
  </form>
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
  methods: {
    saveRecipe() {
      axios.post('http://localhost:8080/api/recipes', this.recipe)
        .then(response => {
          alert('Recipe saved!');
        })
        .catch(error => {
          console.error("There was an error saving the recipe!", error);
        });
    }
  }
};
</script>
