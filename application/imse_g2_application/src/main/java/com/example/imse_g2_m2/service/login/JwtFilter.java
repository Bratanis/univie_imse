package com.example.imse_g2_m2.service.login;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtService loginService;
	
//	@Autowired
//	private ApplicationContext context;
	
	@Autowired
    ApplicationContext context;
	
//	@Autowired
//	private CompositeUserDetailsService compUDS; // May cause cyclic dependency

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// We receive "Bearer <<Token>>"!
		String headerBegining = "Bearer ";
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String username = null;
		
		if(authHeader != null && authHeader.startsWith(headerBegining)) {
			token = authHeader.substring(headerBegining.length());
			username = loginService.extractUsername(token);
		}
		
		if (username != null && !isAlreadyAuthenticated()) {
			
			UserDetails userDetails = context.getBean(CompositeUserDetailsService.class).loadUserByUsername(username);
			
			if (loginService.validateToken(token, userDetails)) {
				
				UsernamePasswordAuthenticationToken authToken =
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	private boolean isAlreadyAuthenticated() {
		return SecurityContextHolder.getContext().getAuthentication() != null;
	}
}
