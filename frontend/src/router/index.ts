import { createRouter, createWebHistory } from 'vue-router';
import Home from '@/views/Home.vue';
import RecipeDetail from '@/components/RecipeDetail.vue';
import RecipeForm from '@/components/RecipeForm.vue';

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/recipes/:id',
    name: 'RecipeDetail',
    component: RecipeDetail
  },
  {
    path: '/add-recipe',
    name: 'AddRecipe',
    component: RecipeForm
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
