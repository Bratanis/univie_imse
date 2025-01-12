package com.example.imse_g2_m2.model.reports;

import java.net.URL;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * Which beginner video tutorial has been saved the most? 
 * What are its name, url, what is the age range of the members,
 * who have saved it and how many times has it been saved?
 */
@Data
@AllArgsConstructor
public class BratanovReportDTO {

	String tutName;
	String tutUrl;
	int lowerAgeRange;
	int upperAgeRange;
	long timesSaved;
	
}
