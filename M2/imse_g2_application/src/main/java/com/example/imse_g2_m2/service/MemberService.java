package com.example.imse_g2_m2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.imse_g2_m2.exceptions.MemberNotFoundException;
import com.example.imse_g2_m2.model.Member;
import com.example.imse_g2_m2.repo.MemberRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberService {

	private MemberRepo repo;
	
//	private AuthenticationManager authManager;

	
	public List<Member> getAllMembers() {
		
		return repo.findAll();
	}

	public Member getMemberById(int memberId) {
		
		return repo.findById(memberId)
                   .orElseThrow(() -> new MemberNotFoundException("Member with ID " + memberId + " not found"));
	}
	
//	public Member registerMember(Member newMember) {
//		return repo.save(newMember);
//	}

//	public String verify(User user) {
//		Authentication authentication = authManager.authenticate(
//										new UsernamePasswordAuthenticationToken(user.g, user.getPassword()));
//		if(authentication.isAuthenticated()) {
//			return "Success!";
//		} else {
//			return "Fail!";
//		}
//	}
}
