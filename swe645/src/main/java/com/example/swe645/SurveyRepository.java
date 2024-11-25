package com.example.swe645;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Shruthi Pachava - G01459580
//Harika Gaddam - G01449863
//Akshitha Theretupally - G01472457
//Vamsi Krishna Maram - G01478991

@Repository
public interface SurveyRepository extends JpaRepository<SurveyEntity, Long> {

}
