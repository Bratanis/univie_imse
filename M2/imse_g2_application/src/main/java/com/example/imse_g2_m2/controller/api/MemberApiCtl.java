package com.example.imse_g2_m2.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.imse_g2_m2.model.Location;
import com.example.imse_g2_m2.model.Member;
import com.example.imse_g2_m2.model.Tutorial;
import com.example.imse_g2_m2.service.LocationService;
import com.example.imse_g2_m2.service.SingleMemberService;
import com.example.imse_g2_m2.service.TutorialService;

import lombok.AllArgsConstructor;
@RestController
@CrossOrigin // Will allow the front-end to access the back-end
@AllArgsConstructor
//@PreAuthorize("hasRole('USER')")  // authorization in SecurityConifg
@RequestMapping ("/api/member") // Set this to the endpoint of a certain user that is logged in
public class MemberApiCtl {

	private SingleMemberService memberService;
	private LocationService locationService;
	private TutorialService tutorialService;
	
//	private Member member;
	
	@GetMapping("")
	public String initialMemberGreeting() {
		return "Hello, Member!!!"; // change to greet with name
	}
	
//	@PostMapping("/login")
//	public String login(@RequestBody Member member) {
//		System.out.println(member);
//		return "Not implemented";
//	}
	
	
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
		return memberService.getAuthenticatedMember();
	}
	
	@PostMapping("/save_tutorial")
	public String saveTutorial(@RequestBody Tutorial tutorial) {
		return memberService.saveTutorial(tutorial);
	}
	
	@PostMapping("/remove_tutorial")
	public String removeSavedTutorial(@RequestBody Tutorial tutorial) {
		return memberService.removeTutorial(tutorial);
	}
}




