package com.example.imse_g2_m2.repo.noSqlRepo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.imse_g2_m2.model.Member;

@Repository("memberNoSqlRepo")
public interface MemberNoSqlRepo extends MongoRepository<Member, Integer> {

}
