package com.example.imse_g2_m2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.example.imse_g2_m2.model.Location;
import com.example.imse_g2_m2.repo.noSqlRepo.LocationNoSqlRepo;
import com.example.imse_g2_m2.repo.sqlRepo.LocationSqlRepo;

@Service
public class LocationService {

    private final LocationSqlRepo mariaDBRepo;

    private final LocationNoSqlRepo mongoDBRepo;
    
    private CrudRepository<Location, Integer> currentRepo;
	

    
 
    public LocationService(
			@Qualifier("locationSqlRepo") LocationSqlRepo mariaDBRepo,
			@Qualifier("locationNoSqlRepo") LocationNoSqlRepo mongoDBRepo) {
		super();
		this.mariaDBRepo = mariaDBRepo;
		this.mongoDBRepo = mongoDBRepo;
		switchToMariaDB(); 
	}
   
	
	public List<Location> getAllLocations() {
		
		return (List<Location>) currentRepo.findAll();
	}
	
	public void switchToMongoDB() {
        this.currentRepo = mongoDBRepo;
    }
	
	 public void switchToMariaDB() {
	        this.currentRepo = mariaDBRepo;
	 }
	
	public void insertLocation(Location location) {
		currentRepo.save(location);
	}
	
	
	public void emptyLocations() {
		currentRepo.deleteAll();
		if (currentRepo instanceof LocationSqlRepo)
			((LocationSqlRepo) currentRepo).resetAutoIncrement();
	}


}
