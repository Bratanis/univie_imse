package com.example.imse_g2_m2.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.imse_g2_m2.model.Location;
import com.example.imse_g2_m2.model.Tutorial;
import com.example.imse_g2_m2.service.LocationService;
import com.example.imse_g2_m2.service.TutorialService;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin // Will allow the front-end to access the back-end
@AllArgsConstructor
//@PreAuthorize("hasRole('ADMIN')") // authorization in SecurityConifg
@RequestMapping ("/api/public") 
public class PublicApiCtl {

	private LocationService locationService;

	private TutorialService tutorialService;

	@GetMapping("/locations")
	public List<Location> getAllLocations (){
		
		return locationService.getAllLocations();
	}

	// Tutorials html page doesnt display URLs to videos!!!
	@GetMapping("/tutorials")
	public List<Tutorial> getAllTutorials (){
		return tutorialService.getAllTutorials();
	}
}
