package com.example.imse_g2_m2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.imse_g2_m2.model.Tutorial;
import com.example.imse_g2_m2.repo.TutorialRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TutorialService {
	
private TutorialRepo repo;


	public List<Tutorial> getAllTutorials() {
		
		return repo.findAll();
	}
}
