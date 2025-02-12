package com.example.imse_g2_m2.repo.sqlRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.imse_g2_m2.model.Location;

@Repository("locationSqlRepo")
public interface LocationSqlRepo extends JpaRepository<Location, Integer>{

	@Modifying
    @Query(value = "ALTER TABLE location AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
