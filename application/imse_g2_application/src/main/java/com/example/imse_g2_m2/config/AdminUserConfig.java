package com.example.imse_g2_m2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * The in-memmory declaration of an admin user
 */
@Configuration
public class AdminUserConfig {
    @Bean
	public UserDetailsService inMemoryUserDetailsService() {
		UserDetails adminUser = User.withUsername("admin")
									.password("admin") // password in plain text for simplicity
									.roles("ADMIN")
									.build();

		return new InMemoryUserDetailsManager(adminUser);
	}
}
