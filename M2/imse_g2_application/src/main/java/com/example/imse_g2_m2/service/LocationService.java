package com.example.imse_g2_m2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.imse_g2_m2.model.Location;
import com.example.imse_g2_m2.repo.LocationRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LocationService {

	private LocationRepo repo;
	
	public List<Location> getAllLocations() {
		
		return repo.findAll();
	}
	
	public void insertLocation(Location location) {
		repo.save(location);
	}
	
	public void clearLocation() {
		repo.deleteAll();
	}


}
