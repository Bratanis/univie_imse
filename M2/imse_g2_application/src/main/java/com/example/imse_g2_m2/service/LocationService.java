package com.example.imse_g2_m2.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.imse_g2_m2.model.Location;
import com.example.imse_g2_m2.repo.LocationRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LocationService {

	private LocationRepo repo;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public List<Location> getAllLocations() {
		
		return repo.findAll();
	}
	
	public void insertLocation(Location location) {
		repo.save(location);
	}
	
	@Transactional
	public void clearLocation() {
		repo.deleteAll();
		entityManager.createNativeQuery("ALTER TABLE member AUTO_INCREMENT = 1").executeUpdate();
	}
}
