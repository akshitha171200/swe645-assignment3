<template>
  <div class="container mt-4">
    <div class="row">
      <div class="col-sm-1"></div>
      <div class="col-sm-10 shadow p-3 mb-5 bg-body rounded">
        <form @submit.prevent="submitSurvey">
          <h2 class="text-center">Edit Survey</h2>
          <hr />

          <!-- Input Fields -->
          <div class="form-group text-start">
            <label for="firstName">First Name<span class="text-danger">*</span></label>
            <input type="text" class="form-control" v-model="survey.firstName" placeholder="Enter first name" required />
          </div>
          <br />
          <div class="form-group text-start">
            <label for="lastName">Last Name<span class="text-danger">*</span></label>
            <input type="text" class="form-control" v-model="survey.lastName" placeholder="Enter last name" required />
          </div>
          <br />
          <div class="form-group text-start">
            <label for="streetAddress">Street Address<span class="text-danger">*</span></label>
            <input type="text" class="form-control" v-model="survey.streetAddress" placeholder="Enter street address" required />
          </div>
          <br />
          <div class="form-group text-start">
            <label for="city">City<span class="text-danger">*</span></label>
            <input type="text" class="form-control" v-model="survey.city" placeholder="Enter city" required />
          </div>
          <br />
          <div class="form-group text-start">
            <label for="state">State<span class="text-danger">*</span></label>
            <input type="text" class="form-control" v-model="survey.state" placeholder="Enter state" required />
          </div>
          <br />
          <div class="form-group text-start">
            <label for="zip">Zip<span class="text-danger">*</span></label>
            <input type="text" class="form-control" v-model="survey.zip" placeholder="Enter zip" required />
          </div>
          <br />
          <div class="form-group text-start">
            <label for="telephoneNumber">Telephone Number<span class="text-danger">*</span></label>
            <input type="text" class="form-control" v-model="survey.telephoneNumber" placeholder="Enter telephone number" required />
          </div>
          <br />
          <div class="form-group text-start">
            <label for="email">E-mail<span class="text-danger">*</span></label>
            <input type="email" class="form-control" v-model="survey.email" placeholder="Enter email" required />
          </div>
          <br />
          <div class="form-group text-start">
            <label for="date">Survey Date<span class="text-danger">*</span></label>
            <input type="date" class="form-control" v-model="survey.dateOfSurvey" required />
          </div>
          <br />
<div class="form-group text-start">
  <label>What do you like most about the campus?</label>
  <div class="form-check" v-for="(option, index) in campusLikes" :key="index">
    <input 
      class="form-check-input" 
      type="checkbox" 
      v-model="survey.selectedCampusLikes" 
      :value="option" />
    <label class="form-check-label">{{ option }}</label>
  </div>
</div>



          <br />
          <div class="form-group text-start">
  <label>How did you become interested in the University?</label>
  <div class="form-check" v-for="(option, index) in universityInterestOptions" :key="index">
    <input
      class="form-check-input"
      type="radio"
      v-model="survey.sourceOfInterest"
      :value="option"
    />
    <label class="form-check-label">{{ option }}</label>
  </div>
</div>
<br />
          <div class="form-group text-start">
  <label for="recommendUniversity">How much would you recommend this university?</label>
  <select class="form-select" v-model="survey.likelihood">
    <option value="" disabled>Select an option</option>
    <option value="Very Likely">Very Likely</option>
    <option value="Likely">Likely</option>
    <option value="Unlikely">Unlikely</option>
  </select>
</div>
<br/>
          

          <!-- Submit and Cancel Buttons -->
          <div class="row text-center">
            <div class="col-12">
              <button type="button" class="btn btn-success mx-2" @click="cancelEdit">Cancel</button>
              <button type="submit" class="btn btn-success mx-2">Save Changes</button>
            </div>
          </div>
        </form>
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
import axios from 'axios';

export default {
  name: 'EditSurvey',
  data() {
    return {
      survey: {
        firstName: "",
        lastName: "",
        streetAddress: "",
        city: "",
        state: "",
        zip: "",
        telephoneNumber: "",
        email: "",
        date: "",
        selectedCampusLikes: [],
        universityInterest: "",
        recommendUniversity: "",
        raffle: "",
        comments: ""
      },
      campusLikes: [
        "Students",
        "Location",
        "Campus",
        "Atmosphere",
        "Dorm rooms",
        "Sports"
      ],
      universityInterestOptions: ["Friends", "Television", "Internet", "Other"]
    };
  },
  mounted() {
    this.fetchSurveyData(); // Fetch survey data when the component is mounted
  },
  methods: {
    // Fetch survey data from the backend based on the survey ID
    async fetchSurveyData() {
  const surveyId = this.$route.params.id; // Get the ID from the route params
  try {
    const response = await axios.get(`/api/surveys/${surveyId}`); // Make absolute path to API
    this.survey = response.data; // Populate the survey data with the fetched data

    // Convert the 'likedMost' string into an array and assign it to selectedCampusLikes
    if (this.survey.likedMost) {
      this.survey.selectedCampusLikes = this.survey.likedMost.split(',').map(item => item.trim());
    }

    console.log("Fetched survey data:", this.survey);
  } catch (error) {
    console.error("Error fetching survey data:", error);
  }
},
formatDate(date) {
  const d = new Date(date);
  d.setDate(d.getDate() + 1); 
  const year = d.getFullYear();
  const month = (d.getMonth() + 1).toString().padStart(2, '0'); // Ensure two digits
  const day = d.getDate().toString().padStart(2, '0'); // Ensure two digits
  return `${year}-${month}-${day}`;
},

    async submitSurvey() {


  try {
    const surveyId = this.$route.params.id; // Get the survey ID from the route params
    const formattedDate = this.formatDate(this.survey.dateOfSurvey);
    // Prepare the survey data for submission
    const surveyData = {
      firstName: this.survey.firstName,
      lastName: this.survey.lastName,
      streetAddress: this.survey.streetAddress,
      city: this.survey.city,
      state: this.survey.state,
      zip: this.survey.zip,
      telephoneNumber: this.survey.telephoneNumber,
      email: this.survey.email,
      likedMost: this.survey.selectedCampusLikes,
      sourceOfInterest: this.survey.sourceOfInterest,
      likelihood: this.survey.likelihood,
      dateOfSurvey: formattedDate
    };

    // Send the updated data via PUT request
    const response = await axios.put(`/api/surveys/${surveyId}`, surveyData);
    
    // Log the successful response
    console.log("Survey updated:", response.data);
    
    // Redirect to homepage or another page after successful submission
    this.$router.push('/');
    
  } catch (error) {
    console.error("Error submitting survey:", error);
  }
}
,
    // Cancel the edit operation
    cancelEdit() {
      this.$router.push('/'); // Redirect to the homepage or another page
    }
  }
};
</script>

<style scoped>
.footer {
  background-color: #006633;
  width: 100%;
  bottom: 0;
}
</style>
