package com.example.imse_g2_m2.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.imse_g2_m2.exceptions.MemberNotFoundException;
import com.example.imse_g2_m2.model.Member;
import com.example.imse_g2_m2.repo.MemberRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberService {

    private MemberRepo repo;
    
    @PersistenceContext
    private EntityManager entityManager;
	
	public List<Member> getAllMembers() {
		
		return repo.findAll();
	}

	public Member getMemberById(int memberId) {
		
		return repo.findById(memberId)
                   .orElseThrow(() -> new MemberNotFoundException("Member with ID " + memberId + " not found"));
	}
	
	public void insertMember(Member member) {
		repo.save(member);
	}
	
	@Transactional
	public void clearMember() {
		repo.deleteAll();
		entityManager.createNativeQuery("ALTER TABLE member AUTO_INCREMENT = 1").executeUpdate();
	}

//	public Member getMemberByName(String name) {
//		return repo.findBy(null, null);
//	}
}
