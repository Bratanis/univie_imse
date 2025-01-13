package com.example.imse_g2_m2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.imse_g2_m2.exceptions.MemberNotFoundException;
import com.example.imse_g2_m2.model.Member;
import com.example.imse_g2_m2.repo.noSqlRepo.MemberNoSqlRepo;
import com.example.imse_g2_m2.repo.sqlRepo.MemberSqlRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class MemberService {
	
	
	private final MemberSqlRepo mariaDBRepo;

    private final MemberNoSqlRepo mongoDBRepo;

    private CrudRepository<Member, Integer> currentRepo = null;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public MemberService(
			@Qualifier("memberSqlRepo") MemberSqlRepo mariaDBRepo,
			@Qualifier("memberNoSqlRepo") MemberNoSqlRepo mongoDBRepo) {
		super();
		this.mariaDBRepo = mariaDBRepo;
		this.mongoDBRepo = mongoDBRepo;
		switchToMariaDB(); 
	}	
	public List<Member> getAllMembers() {
		
		return (List<Member>) currentRepo.findAll();
	}

	public void switchToMongoDB() {
        this.currentRepo = mongoDBRepo;
    }
	
	 public void switchToMariaDB() {
	        this.currentRepo = mariaDBRepo;
	 }
	
	
	public Member getMemberById(int memberId) {
		
		return currentRepo.findById(memberId)
                   .orElseThrow(() -> new MemberNotFoundException("Member with ID " + memberId + " not found"));
	}
	
	public void insertMember(Member member) {
		currentRepo.save(member);
	}
	
	@Transactional
	public void clearMember() {
		currentRepo.deleteAll();
		entityManager.createNativeQuery("ALTER TABLE member AUTO_INCREMENT = 1").executeUpdate();
	}

//	public Member getMemberByName(String name) {
//		return repo.findBy(null, null);
//	}
}
