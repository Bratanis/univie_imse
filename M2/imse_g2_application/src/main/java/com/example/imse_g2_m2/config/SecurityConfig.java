package com.example.imse_g2_m2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSec) throws Exception {
		
//		// disable csrf for simplicity
//		httpSec.csrf(customizer -> customizer.disable())
//			   .authorizeHttpRequests(request -> request.anyRequest().authenticated())
//			   //.formLogin(Customizer.withDefaults())
//			   .httpBasic(Customizer.withDefaults())
//			   .sessionManagement(session -> 
//			    	session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//		
		return httpSec.build();
	}
	
//	@SuppressWarnings("deprecation")
//	@Bean
//	public AuthenticationProvider authenticationProvider() {
//		
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); // Passwords not encoded for simplicity + app not in production
//		provider.setUserDetailsService(userDetailsService);
//		
//		return provider;
//	}

	/**
	 * User management using hard-coded users for the sake of simpler testing
	 */
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		@SuppressWarnings("deprecation") // We are using this for simplicity (not a production app anyways)
//		UserDetails user1 = User.withDefaultPasswordEncoder()
//								.username("ivan")
//								.password("ivanb")
//								.roles("USER")
//								.build();
//		
//		@SuppressWarnings("deprecation") // We are using this for simplicity (not a production app anyways)
//		UserDetails user2 = User.withDefaultPasswordEncoder()
//								.username("boss")
//								.password("boss_passwd")
//								.roles("ADMIN")
//								.build();
//
//		return new InMemoryUserDetailsManager(user1, user2);
//	}
	
}
