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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.imse_g2_m2.service.CompositeUserDetailsService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
	
	private final CompositeUserDetailsService compositeUserDetailsService;
//	private final AdminUserConfig adminConfig;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSec) throws Exception {
		
		// disable csrf for simplicity
		httpSec.csrf(customizer -> customizer.disable())
			   .authorizeHttpRequests(request -> 
			   		request
			   				.requestMatchers("/admin/**").hasRole("ADMIN") // Protect admin endpoints
			   				.anyRequest().authenticated()
//			   				.anyRequest().hasAnyRole("ADMIN", "USER")
			   	)
//			   .formLogin(Customizer.withDefaults()) // For browser login
			   .httpBasic(Customizer.withDefaults()) // For postman api testing
			   .sessionManagement(session -> 
			    	session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return httpSec.build();
	}
	
	@SuppressWarnings("deprecation")
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); // Passwords not encoded for simplicity + app not in production
		provider.setUserDetailsService(compositeUserDetailsService);
//		provider.setUserDetailsService(adminConfig.inMemoryUserDetailsService());
		
		return provider;
	}
	
	
	
	/**DefaultPasswordEncoder()
									.
	 * User management using hard-coded users for the sake of simpler testing
	 */
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		@SuppressWarnings("deprecation") // We are using this for simplicity (not a production app anyways)
//		UserDetails user1 = User.withDefaultPasswordEncoder()
//								.username("ivan")
//								.password("ivan")
//								.roles("USER")
//								.build();
//		
//		@SuppressWarnings("deprecation") // We are using this for simplicity (not a production app anyways)
//		UserDetails user2 = User.withDefaultPasswordEncoder()
//								.username("boss")
//								.password("boss")
//								.roles("ADMIN")
//								.build();
//
//		return new InMemoryUserDetailsManager(user1, user2);
//	}
	
}
