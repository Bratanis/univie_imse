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
//@PreAuthorize("hasRole('USER')")  // authorization in SecurityConifg
@RequestMapping ("/member") // Set this to the endpoint of a certain user that is logged in
public class MemberCtl {

	@GetMapping("/")
	public ResponseEntity<Resource> memberPage() {
		Resource resource = new ClassPathResource("/static/member.html");
		return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(resource);
	}

	@GetMapping("/account")
	public ResponseEntity<Resource> myDataPage() {
		Resource resource = new ClassPathResource("/static/account_data.html");
		return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(resource);
	}

	@GetMapping("/locations")
	public ResponseEntity<Resource> locationsPage() {
		Resource resource = new ClassPathResource("/static/locations.html");
		return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(resource);
	}

	@GetMapping("/tutorials")
	public ResponseEntity<Resource> tutorialsPage() {
        Resource resource = new ClassPathResource("/static/savable_tutorials.html");
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(resource);
    }	
}








