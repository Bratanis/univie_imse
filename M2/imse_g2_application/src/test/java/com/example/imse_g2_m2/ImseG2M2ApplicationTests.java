package com.example.imse_g2_m2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.imse_g2_m2.service.CompositeUserDetailsService;

@SpringBootTest
class ImseG2M2ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private UserDetailsService inMemoryUserDetailsService;
	
	@Autowired
	private CompositeUserDetailsService compositeUDS;

	@Test
	void testAdminLogin() {
	    UserDetails userDetails = inMemoryUserDetailsService.loadUserByUsername("admin");
	    assertNotNull(userDetails);
	    assertEquals("admin", userDetails.getUsername());

	    System.out.println("Username: " + userDetails.getUsername());
	    System.out.println("Authorities: " + userDetails.getAuthorities());

	}
	
	@Test
	void testCompositeUserServices() {
		List<UserDetailsService> services = compositeUDS.getServices();
		assertTrue(services.contains(inMemoryUserDetailsService)); // has the admin
		assertNotNull(compositeUDS.loadUserByUsername("admin"));
//		assertTrue(services.contains()); // has the users
	}

}
