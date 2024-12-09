<template>
  <div class="container mt-4">
    <div class="row">
      <div class="col-sm-1"></div>
      <div class="col-sm-10 shadow p-3 mb-5 bg-body rounded">
        <div class="mb-3 text-center position-relative">
          <h2 class="text-center">Survey Management</h2>
          <hr />
          <button class="btn btn-success position-absolute end-0" @click="$router.push(`/survey/add`)">Add Survey</button>
          <br /><br />
        </div>

        <!-- Error Message if Something Goes Wrong -->
        <div v-if="errorMessage" class="alert alert-danger">
          {{ errorMessage }}
        </div>

        <!-- Loading Spinner -->
        <div v-if="loading" class="text-center">
          <div class="spinner-border" role="status">
            <span class="visually-hidden">Loading...</span>
          </div>
        </div>

        <!-- Surveys Table -->
        <table v-if="!loading && surveys.length > 0" class="table table-bordered table-striped">
          <thead class="custom-header">
            <tr>
              <th>#</th>
              <th>Survey Name</th>
              <th>Created Date</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(survey, index) in surveys" :key="survey.surveyId">
              <td>{{ index + 1 }}</td>
              <td>{{ survey.firstName }} {{ survey.lastName }}</td>
              <td>{{ survey.dateOfSurvey }}</td>
              <td class="text-center">
  <button
    class="btn btn-sm btn-success me-2"
    @click="$router.push(`/survey/view/${survey.surveyId}`)"
  >
    View
  </button>
  <button
    class="btn btn-sm btn-primary me-2"
    @click="$router.push(`/survey/edit/${survey.surveyId}`)"
  >
    Edit
  </button>
  <button
    class="btn btn-sm btn-danger"
    @click="deleteSurvey(survey.surveyId)"
  >
    Delete
  </button>
</td>


            </tr>
          </tbody>
        </table>

        <!-- No surveys message -->
        <div v-else class="text-center">
          <p>No surveys found</p>
        </div>
      </div>
      <div class="col-sm-1"></div>
    </div>
  </div>
</template>

<script>
import api from '@/api';  // Import the Axios instance from api.js

export default {
  data() {
    return {
      surveys: [],            // Surveys array to store fetched data
      errorMessage: '',       // To store error messages
      loading: true,          // To handle loading state
    };
  },
  methods: {
    // Fetch surveys from the backend API using the configured Axios instance
    async fetchSurveys() {
  this.loading = true;
  try {
    const response = await api.get('/api/surveys'); // Use proxy if configured
    console.log('Fetched surveys:', response.data);

    if (Array.isArray(response.data) && response.data.length > 0) {
      this.surveys = response.data;
    } else {
      this.errorMessage = 'No surveys found';
    }
  } catch (error) {
    this.errorMessage = 'Error fetching surveys. Please try again later.';
    console.error('Error fetching surveys:', error.message, error.response || error);
  } finally {
    this.loading = false;
  }
},
    addSurvey() {
      this.$router.push('api/survey');
    },
    // Delete survey with confirmation and remove it from the local list
    async deleteSurvey(surveyId) {
      if (confirm('Are you sure you want to delete this survey?')) {
        try {
          await api.delete(`api/surveys/${surveyId}`);  // Delete survey from the backend
          this.surveys = this.surveys.filter(survey => survey.surveyId !== surveyId);  // Remove deleted survey from list
          alert('Survey deleted successfully.');
        } catch (error) {
          console.error('Error deleting survey:', error);
          alert('Failed to delete survey.');
        }
      }
    },
  },
  mounted() {
    this.fetchSurveys();  // Fetch surveys when the component is mounted
  },
};
</script>

<style scoped>
.custom-header {
  background-color: #006633;
  color: white;
}
.action-buttons {
  display: flex;
  justify-content: center;
  gap: 0.5rem;
}
</style>
