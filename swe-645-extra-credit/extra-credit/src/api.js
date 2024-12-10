import axios from 'axios';

// Create Axios instance with the backend URL pointing to your Spring Boot backend
const api = axios.create({
  baseURL: 'http://localhost:8081', // backend URL
  headers: {
    'Content-Type': 'application/json',
  },
});

export default api;
