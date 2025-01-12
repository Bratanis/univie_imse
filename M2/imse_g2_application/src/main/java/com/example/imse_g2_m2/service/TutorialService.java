package com.example.imse_g2_m2.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.imse_g2_m2.model.Tutorial;
import com.example.imse_g2_m2.repo.TutorialRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TutorialService {
	
    private TutorialRepo repo;
    
    @PersistenceContext
    private EntityManager entityManager;

	public List<Tutorial> getAllTutorials() {
		
		return repo.findAll();
	}
	
	public void insertTutorial(Tutorial tutorial) {
		repo.save(tutorial);
	}
	
	@Transactional
	public void clearTutorial() {
		repo.deleteAll();
		entityManager.createNativeQuery("ALTER TABLE tutorial AUTO_INCREMENT = 1").executeUpdate();
	}
}
