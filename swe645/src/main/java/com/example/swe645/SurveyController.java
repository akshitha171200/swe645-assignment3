package com.example.swe645;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;


//Shruthi Pachava - G01459580
//Harika Gaddam - G01449863
//Akshitha Theretupally - G01472457
//Vamsi Krishna Maram - G01478991

@RestController
@RequestMapping("/api/surveys")
@Validated
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepository;

    // End point to create a new survey
    @PostMapping
    public ResponseEntity<String> createSurvey(@Valid @RequestBody SurveyModel survey) {
    	
    	SurveyEntity surveyObject = new SurveyEntity();
    	surveyObject.setCity(survey.getCity());
    	surveyObject.setDateOfSurvey(survey.getDateOfSurvey());
    	surveyObject.setFirstName(survey.getFirstName());
    	surveyObject.setLastName(survey.getLastName());
    	surveyObject.setEmail(survey.getEmail());
    	surveyObject.setLikelihood(survey.getLikelihood());
    	surveyObject.setSourceOfInterest(survey.getSourceOfInterest());
    	surveyObject.setState(survey.getState());
    	surveyObject.setStreetAddress(survey.getStreetAddress());
    	surveyObject.setTelephoneNumber(survey.getTelephoneNumber());
    	surveyObject.setZip(survey.getZip());
    	
    	if (survey.getLikedMost() != null) {
            String likedMostString = String.join(",", survey.getLikedMost());  // Convert List to String
            surveyObject.setLikedMost(likedMostString);  // Set the comma-separated string to the entity
        }
        surveyRepository.save(surveyObject);
        return ResponseEntity.status(HttpStatus.CREATED).body("Survey created successfully");
    }
    
    // End point to view all surveys
    @GetMapping
    public List<SurveyEntity> getAllSurveys() {
        return surveyRepository.findAll();
    }
    
    //End point to view individual survey
    @GetMapping("/{id}")
    public SurveyEntity getSurveyById(@PathVariable Long id) {
        Optional<SurveyEntity> survey = surveyRepository.findById(id);
        if (survey.isPresent()) {
            return survey.get();
        } else {
            throw new SurveyNotFoundException("Survey not found for id: " + id);
        }
    }
    
    // End point to update a survey
    @PutMapping("/{id}")
    public ResponseEntity<String> updateSurvey(@PathVariable Long id, @RequestBody SurveyModel survey) {
        // Find the survey by id
        Optional<SurveyEntity> existingSurvey = surveyRepository.findById(id);
        
        if (existingSurvey.isPresent()) {
            SurveyEntity surveyObject = existingSurvey.get();
            
            // Update fields from the incoming request
            surveyObject.setCity(survey.getCity());
            surveyObject.setDateOfSurvey(survey.getDateOfSurvey());
            surveyObject.setFirstName(survey.getFirstName());
            surveyObject.setLastName(survey.getLastName());
            surveyObject.setEmail(survey.getEmail());
            surveyObject.setLikelihood(survey.getLikelihood());
            surveyObject.setSourceOfInterest(survey.getSourceOfInterest());
            surveyObject.setState(survey.getState());
            surveyObject.setStreetAddress(survey.getStreetAddress());
            surveyObject.setTelephoneNumber(survey.getTelephoneNumber());
            surveyObject.setZip(survey.getZip());

            // Update likedMost list if not null
            if (survey.getLikedMost() != null) {
                String likedMostString = String.join(",", survey.getLikedMost());  // Convert List to String
                surveyObject.setLikedMost(likedMostString);  // Set the comma-separated string to the entity
            }

            // Save the updated survey entity
            surveyRepository.save(surveyObject);
            
            return ResponseEntity.status(HttpStatus.OK).body("Survey updated successfully");
        } else {
            // If the survey with the given ID doesn't exist
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Survey not found with ID: " + id);
        }
    }
    
    //End point to delete a survey
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSurvey(@PathVariable Long id) {
        // Check if the survey with the given ID exists
        Optional<SurveyEntity> existingSurvey = surveyRepository.findById(id);
        
        if (existingSurvey.isPresent()) {
            // Delete the survey entity
            surveyRepository.deleteById(id);
            
            return ResponseEntity.status(HttpStatus.OK).body("Survey deleted successfully");
        } else {
            // If the survey with the given ID doesn't exist
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Survey not found with ID: " + id);
        }
    }


    
}
