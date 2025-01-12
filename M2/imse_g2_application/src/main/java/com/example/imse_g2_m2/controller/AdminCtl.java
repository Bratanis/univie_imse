package com.example.imse_g2_m2.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin // Will allow the front-end to access the back-end
@AllArgsConstructor
//@PreAuthorize("hasRole('ADMIN')") // authorization in SecurityConifg
@RequestMapping ("/admin") // Set this to the endpoint of a certain user that is logged in
public class AdminCtl {
	@GetMapping("/")
    public ResponseEntity<Resource> adminPage() {
        Resource resource = new ClassPathResource("/static/admin.html");
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(resource);
    }	

/**
 * To be refactored into a dataPopulator service, 
 * Controller should only handle api communication!
 */
	//@GetMapping("/populate")
	//public void populateMariaDb () {
	//	dataPopulator.insertData();
	//}


//	@GetMapping
//	getLocations(){
//		
//	}

}
