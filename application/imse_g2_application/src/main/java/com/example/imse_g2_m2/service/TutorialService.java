package com.example.imse_g2_m2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.imse_g2_m2.model.Tutorial;
import com.example.imse_g2_m2.repo.noSqlRepo.TutorialNoSqlRepo;
import com.example.imse_g2_m2.repo.sqlRepo.TutorialSqlRepo;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class TutorialService {

	
	private final TutorialSqlRepo mariaDBRepo;
	
    private final TutorialNoSqlRepo mongoDBRepo;
    
    private CrudRepository<Tutorial, Integer> currentRepo;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public TutorialService(
			@Qualifier("tutorialSqlRepo") TutorialSqlRepo mariaDBRepo,
			@Qualifier("tutorialNoSqlRepo") TutorialNoSqlRepo mongoDBRepo) {
		super();
		this.mariaDBRepo = mariaDBRepo;
		this.mongoDBRepo = mongoDBRepo;
		switchToMariaDB(); 
	}
    
    @PostConstruct
    private void initCurrentRepo() {
        switchToMariaDB(); // Initialize with mariaDBRepo by default
    }

	public List<Tutorial> getAllTutorials() {
		
		return (List<Tutorial>) currentRepo.findAll();
	}
	
	public void switchToMongoDB() {
        this.currentRepo = mongoDBRepo;
    }
	
	 public void switchToMariaDB() {
	        this.currentRepo = mariaDBRepo;
	 }
	
	public void insertTutorial(Tutorial tutorial) {
		currentRepo.save(tutorial);
	}
	
	@Transactional
	public void clearTutorial() {
		currentRepo.deleteAll();
		entityManager.createNativeQuery("ALTER TABLE tutorial AUTO_INCREMENT = 1").executeUpdate();
	}
}
