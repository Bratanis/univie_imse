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
	
	public List<Member> getAllMembers() {
		
		return repo.findAll();
	}

	public Member getMemberById(int memberId) {
		
		return repo.findById(memberId)
                   .orElseThrow(() -> new MemberNotFoundException("Member with ID " + memberId + " not found"));
	}
}
