package com.example.imse_g2_m2.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.imse_g2_m2.model.Location;
import com.example.imse_g2_m2.model.Member;
import com.example.imse_g2_m2.model.Tutorial;
import com.example.imse_g2_m2.service.LocationService;
import com.example.imse_g2_m2.service.MemberService;
import com.example.imse_g2_m2.service.TutorialService;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin // Will allow the front-end to access the back-end
@AllArgsConstructor
//@PreAuthorize("hasRole('ADMIN')") // authorization in SecurityConifg
@RequestMapping ("/api/admin") // Set this to the endpoint of a certain user that is logged in
public class AdminApiCtl {

	private LocationService locationService;
	private MemberService memberService;
	private TutorialService tutorialService;
	
	@GetMapping("")
	public String initialAdminGreeting() {
		return "Hello, admin!!!"; 
	}
	
	@GetMapping("/locations")
	public List<Location> getAllLocations (){
		
		return locationService.getAllLocations();
	}
	
	@GetMapping("/members")
	public List <Member> getAllMembers (){
		return memberService.getAllMembers();
	}
	
	@GetMapping("/members/{memberId}")
	public Member getMemberById (@PathVariable int memberId){
		return memberService.getMemberById(memberId);
	}
	
	@GetMapping("/tutorials")
	public List<Tutorial> getAllTutorials (){
		return tutorialService.getAllTutorials();
	}
}
