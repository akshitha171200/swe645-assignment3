<template>
  <div class="container">
    <div class="row">
      <div class="col-sm-2"></div>
      <div class="col-sm-8 shadow p-3 mb-5 bg-body rounded">
        <form @submit.prevent="submitForm">
          <br />
          <h2 class="text-center">Student Survey Form</h2>
          <hr />

          <!-- Form Fields -->
          <div class="form-group text-start">
            <label for="firstName">First Name<span class="text-danger">*</span></label>
            <input type="text" class="form-control" v-model="firstName" placeholder="Enter first name" required />
          </div>
          <br />
          <div class="form-group text-start">
            <label for="lastName">Last Name<span class="text-danger">*</span></label>
            <input type="text" class="form-control" v-model="lastName" placeholder="Enter last name" required />
          </div>
          <br />
          <div class="form-group text-start">
            <label for="streetAddress">Street Address<span class="text-danger">*</span></label>
            <input type="text" class="form-control" v-model="streetAddress" placeholder="Enter street address" required />
          </div>
          <br />
          <div class="form-group text-start">
            <label for="city">City<span class="text-danger">*</span></label>
            <input type="text" class="form-control" v-model="city" placeholder="Enter city" required />
          </div>
          <br />
          <div class="form-group text-start">
            <label for="state">State<span class="text-danger">*</span></label>
            <input type="text" class="form-control" v-model="state" placeholder="Enter state" required />
          </div>
          <br />
          <div class="form-group text-start">
            <label for="zip">Zip<span class="text-danger">*</span></label>
            <input type="text" class="form-control" v-model="zip" placeholder="Enter zipcode" required />
          </div>
          <br />
          <div class="form-group text-start">
            <label for="telephoneNumber">Telephone Number<span class="text-danger">*</span></label>
            <input type="text" class="form-control" v-model="telephoneNumber" placeholder="Enter telephone number" required />
          </div>
          <br />
          <div class="form-group text-start">
            <label for="email">E-mail<span class="text-danger">*</span></label>
            <input type="email" class="form-control" v-model="email" placeholder="Enter email" required />
          </div>
          <br />
          <div class="form-group text-start">
            <label for="date">Survey Date<span class="text-danger">*</span></label>
            <input type="date" class="form-control" v-model="date" placeholder="Select a date" required />
          </div>
          <br />
          <div class="form-group text-start">
            <label>What do you like most about the campus?</label>
            <div class="form-check" v-for="(option, index) in campusLikes" :key="index">
              <input class="form-check-input" type="checkbox" v-model="selectedCampusLikes" :value="option" />
              <label class="form-check-label">{{ option }}</label>
            </div>
          </div>
          <br />
          <div class="form-group text-start">
            <label>How did you become interested in the University?</label>
            <div class="form-check" v-for="(option, index) in universityInterestOptions" :key="index">
              <input class="form-check-input" type="radio" v-model="universityInterest" :value="option" />
              <label class="form-check-label">{{ option }}</label>
            </div>
          </div>
          <br />
          <div class="form-group text-start">
            <label>How much would you like to recommend this university?</label>
            <select class="form-select" v-model="recommendUniversity">
              <option value="" disabled selected>Select an option</option>
              <option value="1">Very Likely</option>
              <option value="2">Likely</option>
              <option value="3">Unlikely</option>
            </select>
          </div>
          <br />
          <div class="form-group text-start">
            <label for="raffle">Raffle - Enter at least ten comma-separated numbers ranging from 1 through 100</label>
            <input type="text" class="form-control" v-model="raffle" placeholder="enter at least ten comma-separated numbers" />
            <small class="text-muted">Example: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10</small>
          </div>
          <br />
          <div class="form-group text-start">
            <label>Comments</label>
            <textarea class="form-control" v-model="comments" rows="3"></textarea>
          </div>
          <br />
          <div class="row text-center">
            <div class="col-12">
              <button type="button" class="btn btn-danger mx-2" @click="cancelForm">Cancel</button>
              <button type="submit" class="btn btn-success mx-2">Submit</button>
            </div>
          </div>
        </form>
      </div>
      <div class="col-sm-2"></div>
    </div>
  </div>
  <footer class="footer mt-auto py-3">
    <div class="container">
      <div class="row text-center">
        <div class="col-12">
          <span class="text-white">Copyright &copy;</span>
          <span class="text-white">2024 Shruthi Pachava | </span>
          <span class="text-white">spachava@gmu.edu </span>
        </div>
      </div>
    </div>
  </footer>
</template>

<script>
import api from '@/api';  // Import the Axios instance from api.js

export default {
  name: "SurveyPage",
  data() {
    return {
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
      comments: "",
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
    this.fetchSurveyData(); // Fetch survey data if editing
  },
  methods: {
    cancelForm() {
      this.$router.push("/"); // Navigate back to the home page or other page
    },
    async fetchSurveyData() {
      const surveyId = this.$route.params.id; // Get the ID from the route params
      try {
        const response = await api.get(`/surveys/${surveyId}`);  // Use the API instance
        const survey = response.data;
        this.firstName = survey.firstName;
        this.lastName = survey.lastName;
        this.streetAddress = survey.streetAddress;
        this.city = survey.city;
        this.state = survey.state;
        this.zip = survey.zip;
        this.telephoneNumber = survey.telephoneNumber;
        this.email = survey.email;
        this.date = survey.date;
        this.selectedCampusLikes = survey.selectedCampusLikes || [];
        this.universityInterest = survey.universityInterest;
        this.recommendUniversity = survey.recommendUniversity;
        this.raffle = survey.raffle;
        this.comments = survey.comments;
      } catch (error) {
        console.error("Error fetching survey data:", error);
      }
    },
    async submitForm() {
      if (!this.isValidRaffle(this.raffle)) {
        alert("Please enter at least 10 numbers between 1 and 100 in the raffle field.");
        return;
      }

      try {
        const surveyData = {
          firstName: this.firstName,
          lastName: this.lastName,
          streetAddress: this.streetAddress,
          city: this.city,
          state: this.state,
          zip: this.zip,
          telephoneNumber: this.telephoneNumber,
          email: this.email,
          date: this.date,
          selectedCampusLikes: this.selectedCampusLikes,
          universityInterest: this.universityInterest,
          recommendUniversity: this.recommendUniversity,
          raffle: this.raffle,
          comments: this.comments
        };

        const surveyId = this.$route.params.id;
        const response = await api.put(`/surveys/${surveyId}`, surveyData);
        console.log("Survey updated:", response.data);
      } catch (error) {
        console.error("Error submitting survey:", error);
      }
    },
    isValidRaffle(raffleInput) {
      const numbers = raffleInput.split(',').map(num => parseInt(num.trim(), 10));
      return numbers.length >= 10 && numbers.every(num => num >= 1 && num <= 100);
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
