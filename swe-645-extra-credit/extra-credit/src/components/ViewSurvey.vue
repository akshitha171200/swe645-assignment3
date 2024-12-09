<template>
  <div class="container mt-4">
    <div class="row">
      <div class="col-sm-1"></div>
      <div class="col-sm-10 shadow p-3 mb-5 bg-body rounded">
        <h2 class="text-center">View Survey Details</h2>
        <hr />

        <!-- Error Message -->
        <div v-if="errorMessage" class="alert alert-danger">
          {{ errorMessage }}
        </div>

        <!-- Loading State -->
        <div v-if="!survey && !errorMessage" class="text-center">
          <p>Loading survey details...</p>
        </div>

        <!-- Survey Details -->
        <div v-else>
          <p><strong>First Name:</strong> {{ survey.firstName }}</p>
          <p><strong>Last Name:</strong> {{ survey.lastName }}</p>
          <p><strong>Street Address:</strong> {{ survey.streetAddress }}</p>
          <p><strong>City:</strong> {{ survey.city }}</p>
          <p><strong>State:</strong> {{ survey.state }}</p>
          <p><strong>Zip:</strong> {{ survey.zip }}</p>
          <p><strong>Telephone Number:</strong> {{ survey.telephoneNumber }}</p>
          <p><strong>E-mail:</strong> {{ survey.email }}</p>
          <p><strong>Survey Date:</strong> {{ survey.dateOfSurvey }}</p>
          
          
          <!-- Handle array fields like selectedCampusLikes -->
          <p><strong>What do you like most about the campus?</strong>
              {{ survey.likedMost }}
          </p>
          <p><strong>How did you become interested in the University?</strong> {{ survey.sourceOfInterest }}</p>
          <p><strong>How much would you recommend this university?</strong> {{ survey.likelihood }}</p>

        </div>

        <!-- Back Button -->
        <div class="text-center mt-4">
          <button class="btn btn-success" @click="goBack">Back</button>
        </div>
      </div>
      <div class="col-sm-1"></div>
    </div>
  </div>

  <!-- Footer -->
  <footer class="footer mt-auto py-3">
    <div class="container">
      <div class="row text-center">
        <div class="col-12">
          <span class="text-white">Copyright &copy; 2024 Shruthi Pachava | </span>
          <span class="text-white">spachava@gmu.edu </span>
        </div>
      </div>
    </div>
  </footer>
</template>

<script>
import api from '../api.js'; // Assuming you have an api.js file set up for API calls

export default {
  name: 'ViewSurvey',
  data() {
    return {
      survey: null,  // Initialize as null, then populate with API data
      errorMessage: ''  // Add an error message in case something goes wrong
    };
  },
  mounted() {
    this.fetchSurvey();
  },
  methods: {
    // Fetch survey details based on survey ID
    async fetchSurvey() {
      const surveyId = this.$route.params.id; // Assuming the survey ID is in the route parameters
      try {
  const response = await api.get(`/api/surveys/${surveyId}`);
  this.survey = response.data;
} catch (error) {
  this.errorMessage = 'Failed to load survey details. Please try again.';
  console.error('Error fetching survey:', error);
}

    },
    // Go back to the home page or previous page
    goBack() {
      this.$router.push('/'); 
    }
  }
};
</script>

<style scoped>
/* Styles for Footer */
.footer {
  background-color: #006633;
  color: white;
  width: 100%;
}
</style>
