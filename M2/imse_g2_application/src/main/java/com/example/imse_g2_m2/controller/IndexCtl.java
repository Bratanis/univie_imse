package com.example.imse_g2_m2.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.imse_g2_m2.model.Location;
import com.example.imse_g2_m2.model.Member;
import com.example.imse_g2_m2.model.Tutorial;
import com.example.imse_g2_m2.service.LocationService;
import com.example.imse_g2_m2.service.MemberService;
import com.example.imse_g2_m2.service.TutorialService;

import lombok.AllArgsConstructor;

/**
 * This controller will handle communication between the client and the server
 * For the index page. The default mapping will be to a specific member account 
 * Or to the login account. 
 * 
 *  !!! May need to be refactored and changed when implementing the login-functionallity !!!
 *  !!! For now all endpoints will connect to here, before separating concerns
 *  
 */
@RestController
@AllArgsConstructor
// @RequestMapping ("") // Set this to the endpoint of a certain user that is logged in
public class IndexCtl {

	private LocationService locationService;
	private MemberService memberService;
	private TutorialService tutorialService;

//	public IndexCtl(LocationService locationService) {
//		super();
//		this.locationService = locationService;
//	}

	@GetMapping("/")
	public String initialGreeting() {
		return "Hello World";
	}
	
	@GetMapping("/login")
	public String login() {
		return "This is the login screen";
	}
	
	@GetMapping("/locations")
	public List<Location> getAllLocations () {
		
		return locationService.getAllLocations();
	}
	
	@GetMapping("/members")
	public List <Member> getAllMembers (){
		return memberService.getAllMembers();
	}
	
	@GetMapping("/tutorials")
	public List<Tutorial> getAllTutorials (){
		return tutorialService.getAllTutorials();
	}
}
