package com.example.imse_g2_m2.service.login;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginService {

	private AuthenticationManager authManager;
	 
	private JwtService jwtService;


	public String verify(LoginRequest loginRequest) {
		Authentication authentication = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getUsername(), loginRequest.getPassword()));
		if(authentication.isAuthenticated())
			return jwtService.generateToken(loginRequest.getUsername());
		else 
			return "Fail!";
	}
}
