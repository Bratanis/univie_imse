package com.example.imse_g2_m2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller will handle communication between the client and the server
 * For the index page
 */
@RestController
public class IndexCtl {

	@GetMapping("/")
	public String initialGreeting() {
		return "Hello World";
	}
}
