package com.example.imse_g2_m2.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.imse_g2_m2.model.Member;
import com.example.imse_g2_m2.model.UserPrincipal;
import com.example.imse_g2_m2.repo.MemberRepo;

@Service
public class MemberUserDetailsService implements UserDetailsService{

	@Autowired
	private MemberRepo userRepo;
	
//	private AuthenticationManager authManager;
	

	@Override
	public UserDetails loadUserByUsername(String memberIdString) throws UsernameNotFoundException {
		
		int memberId = -1; // Default value
		
		try {
			memberId = Integer.valueOf(memberIdString);
		} catch (NumberFormatException e) {
			// If the method is called with a string that isn't a number (memberId), leave
			// the default value of -1 (will throw UsernameNotFoundException anyway)
		}
		
		Member user = userRepo.findById(memberId) 
							  .orElseThrow(() -> new UsernameNotFoundException("Member with ID " + memberIdString + " not found"));;
		
		
		return new UserPrincipal(user);
	}
	

}
