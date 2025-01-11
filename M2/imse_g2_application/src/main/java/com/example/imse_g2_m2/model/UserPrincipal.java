package com.example.imse_g2_m2.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails{

	private Member memberData;
	

	public UserPrincipal(Member memberData) {
		this.memberData = memberData;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton( new SimpleGrantedAuthority("USER")); // not optimal but works for now
	}

	@Override
	public String getPassword() {
		return memberData.getPassword();
	}

	@Override
	public String getUsername() {
		return memberData.getName();
	}

}
