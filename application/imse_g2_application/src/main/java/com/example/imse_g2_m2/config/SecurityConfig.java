package com.example.imse_g2_m2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.imse_g2_m2.service.login.CompositeUserDetailsService;
import com.example.imse_g2_m2.service.login.JwtFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
	
	private final CompositeUserDetailsService compositeUserDetailsService;

//	private JwtFilter jwtFilter;
	
	/**
	 * Uses JWT but has no redirections after login
	 */
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity httpSec) throws Exception {
//		
//		// disable csrf for simplicity
//		httpSec.csrf(customizer -> customizer.disable())
//			   .authorizeHttpRequests(request -> 
//			   		request
//			   				.requestMatchers("/", "/login").permitAll() // Everyone can login
//			   				.requestMatchers("/admin/**", "/api/admin/**").hasRole("ADMIN") // Protect admin endpoints
//			   				.requestMatchers("/member/**", "/api/user/**").hasRole("USER")
//			   				.anyRequest().authenticated()
////			   				.anyRequest().hasAnyRole("ADMIN", "USER")
//			   	)
//			   .formLogin(Customizer.withDefaults()) // For browser login
//			   .httpBasic(Customizer.withDefaults()) // For postman api testing
//			   .sessionManagement(session -> 
//			    	session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//			   .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//		
//		return httpSec.build();
//	}
	/**
	 * Uses JWT, but we probably dont need it
	 */
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity httpSec) throws Exception {
//	    httpSec.csrf(csrf -> csrf.disable())
//	           .authorizeHttpRequests(auth -> auth
//	               .requestMatchers("/", "/login").permitAll()
//	               .requestMatchers("/admin/**", "/api/admin/**").hasRole("ADMIN")
//	               .requestMatchers("/member/**", "/api/user/**").hasRole("USER")
//	               .anyRequest().authenticated()
//	           )
//	           .formLogin(form -> form
////	               .loginPage("/login") // Specify your custom login page if needed
//	               .defaultSuccessUrl("/", true) // Redirect to role-specific URL after login
//	               .successHandler((request, response, authentication) -> {
//	                   String role = authentication.getAuthorities().iterator().next().getAuthority();
//	                   if (role.equals("ROLE_ADMIN")) {
//	                       response.sendRedirect("/admin.html");
//	                   } else if (role.equals("ROLE_USER")) {
//	                       response.sendRedirect("/member.html");
//	                   } else {
//	                	   System.out.println("User has no role!");
//	                       response.sendRedirect("/login?error"); // Handle unexpected roles gracefully
//	                   }
//	               })
//	           )
//	           .httpBasic(Customizer.withDefaults())
//	           .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//	           .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
//	    return httpSec.build();
//	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSec) throws Exception {
	    httpSec.csrf(csrf -> csrf.disable())
	          .authorizeHttpRequests(auth -> auth
	              .requestMatchers("/admin/**").hasRole("ADMIN") // Admin endpoints
	              .requestMatchers("/member/**").hasRole("USER") // Member endpoints
	              .anyRequest().authenticated()
	          )
	          .formLogin(form -> form
//	               .loginPage("/login") // Specify your custom login page if needed
	               .defaultSuccessUrl("/", true) // Redirect to role-specific URL after login
	               .successHandler((request, response, authentication) -> {
	                   String role = authentication.getAuthorities().iterator().next().getAuthority();
	                   System.out.println("SFC role: " + role);
	                   if (role.equals("ROLE_ADMIN")) {
	                       response.sendRedirect("/admin/");
	                   } else {
	                       response.sendRedirect("/member/");
	                   } 
	               })
	           )
	          .httpBasic(Customizer.withDefaults());

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
	
	@Bean 
	public AuthenticationManager authentificationManager(AuthenticationConfiguration config) throws Exception {
		
		return config.getAuthenticationManager();
	}
	
	
}
