package com.example.imse_g2_m2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.imse_g2_m2.model.Location;

@Repository
public interface LocationRepo extends JpaRepository<Location, Integer>{

}
