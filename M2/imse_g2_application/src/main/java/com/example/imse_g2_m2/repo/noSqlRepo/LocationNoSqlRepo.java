package com.example.imse_g2_m2.repo.noSqlRepo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.imse_g2_m2.model.Location;

@Repository("locationNoSqlRepo")
public interface LocationNoSqlRepo extends MongoRepository<Location, Integer> {

}
