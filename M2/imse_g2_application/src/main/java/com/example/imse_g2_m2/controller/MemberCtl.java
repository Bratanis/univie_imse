package com.example.imse_g2_m2.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import com.example.imse_g2_m2.model.Location;
import com.example.imse_g2_m2.model.Member;
import com.example.imse_g2_m2.model.Tutorial;
import com.example.imse_g2_m2.service.LocationService;
import com.example.imse_g2_m2.service.TutorialService;

public class MemberCtl {

	private LocationService locationService;
	private TutorialService tutorialService;
	
//	private Member member;
	
	
	@GetMapping("/locations")
	public List<Location> getAllLocations () {
		
		return locationService.getAllLocations();
	}
	
	
	@GetMapping("/tutorials")
	public List<Tutorial> getAllTutorials (){
		return tutorialService.getAllTutorials();
	}
	
	@GetMapping("/account")
	public Member getMyMemberData () {
		return null;
	}
}
