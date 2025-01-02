package com.example.imse_g2_m2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.imse_g2_m2.model.Member;

@Repository
public interface MemberRepo extends JpaRepository<Member, Integer>{

	//Member findByName(String username);
}
