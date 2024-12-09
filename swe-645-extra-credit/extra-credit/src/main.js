import { createApp } from 'vue';
import App from './App.vue';
import { createRouter, createWebHistory } from 'vue-router'; // Import correct Vue Router 3 methods
import HomePage from './components/HomePage.vue';
import SurveyPage from './components/SurveyPage.vue';
import ViewSurvey from './components/ViewSurvey.vue';
import AddSurvey from './components/AddSurvey.vue';
import EditSurvey from './components/EditSurvey.vue';
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'

// Define the routes
const routes = [
  { path: '/', component: HomePage },
  { path: '/survey', component: SurveyPage },
  { path: '/survey/add', component: AddSurvey },
  { path: '/survey/view/:id', component: ViewSurvey },
  { path: '/survey/edit/:id', component: EditSurvey } // Add the dynamic ':id' parameter
];


// Create the router instance using the new Vue Router 3 syntax
const router = createRouter({
  history: createWebHistory(),
  routes
});

// Create the Vue app
const app = createApp(App);

// Use router with the app
app.use(router);

// Mount the app
app.mount('#app');