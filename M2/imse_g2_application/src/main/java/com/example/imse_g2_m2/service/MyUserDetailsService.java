package com.example.imse_g2_m2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.imse_g2_m2.exceptions.MemberNotFoundException;
import com.example.imse_g2_m2.model.Member;
import com.example.imse_g2_m2.model.UserPrincipal;
import com.example.imse_g2_m2.repo.MemberRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private MemberRepo userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {

		Member user = userRepo.findById(Integer.valueOf(memberId)) 
							  .orElseThrow(() -> new UsernameNotFoundException("Member with ID " + memberId + " not found"));;
		
		
		return new UserPrincipal(user);
	}

}
