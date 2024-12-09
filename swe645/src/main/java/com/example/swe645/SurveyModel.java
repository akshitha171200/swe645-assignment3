package com.example.swe645;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

//Shruthi Pachava - G01459580
//Harika Gaddam - G01449863
//Akshitha Theretupally - G01472457
//Vamsi Krishna Maram - G01478991

public class SurveyModel {
	
	@NotEmpty(message = "First Name is required")
    private String firstName;

    @NotEmpty(message = "Last Name is required")
    private String lastName;

    @NotEmpty(message = "Street Address is required")
    private String streetAddress;

    @NotEmpty(message = "City is required")
    private String city;

    @NotEmpty(message = "State is required")
    private String state;

    @NotNull(message = "Zip code is required")
    private Integer zip;

    @NotNull(message = "Telephone number is required")
    private Long telephoneNumber;

    @NotEmpty(message = "Email is required")
    private String email;

    private List<String> likedMost;
    private String sourceOfInterest;
    private String likelihood;

    @NotNull(message = "Date of Survey is required")
    private String dateOfSurvey;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getZip() {
		return zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
	}

	public Long getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(Long telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getLikedMost() {
		return likedMost;
	}

	public void setLikedMost(List<String> likedMost) {
		this.likedMost = likedMost;
	}

	public String getSourceOfInterest() {
		return sourceOfInterest;
	}

	public void setSourceOfInterest(String sourceOfInterest) {
		this.sourceOfInterest = sourceOfInterest;
	}

	public String getLikelihood() {
		return likelihood;
	}

	public void setLikelihood(String likelihood) {
		this.likelihood = likelihood;
	}

	public String getDateOfSurvey() {
		return dateOfSurvey;
	}

	public void setDateOfSurvey(String dateOfSurvey) {
		this.dateOfSurvey = dateOfSurvey;
	}

    
}
