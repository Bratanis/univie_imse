package com.example.imse_g2_m2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tutorial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Tutorial {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tutorialId;

    protected String name;
    protected String muscleGroup;
    protected String difficultyLevel;
    protected String description;
	    
}
