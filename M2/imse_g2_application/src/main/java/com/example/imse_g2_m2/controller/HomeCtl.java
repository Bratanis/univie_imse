package com.example.imse_g2_m2.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.imse_g2_m2.service.login.LoginRequest;
import com.example.imse_g2_m2.service.login.LoginService;

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
@CrossOrigin // Will allow the front-end to access the back-end
@AllArgsConstructor
// @RequestMapping ("") // Set this to the endpoint of a certain user that is logged in
public class HomeCtl {

	private LoginService loginService;
	
	private MariaDBDatabasePopulator dataPopulator;

	@GetMapping("/api")
	public String initialGreeting() {
	    return "Please login!";
	}
	
	
//	@PostMapping("/login")
//	public String login(@RequestBody LoginRequest loginRequest) {
////		System.out.println(loginRequest);
////		compUserService.loadUserByUsername(loginRequest.getUsername());
//		return loginService.verify(loginRequest);
//	}
	
	
// Moved to AdminCtl!	
//	@GetMapping("/populate")
//	public void populateMariaDb () {
//		dataPopulator.insertData();
//	}
	
>>>>>>> ff476a2d60f7dcb98294464e776c085c26e8323f
}
