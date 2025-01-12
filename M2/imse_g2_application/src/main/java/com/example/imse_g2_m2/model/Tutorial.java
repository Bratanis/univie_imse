package com.example.imse_g2_m2.model;

import jakarta.persistence.Column;
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

    @Column(name = "muscle_group")
    protected String muscleGroup;
    
    @Column(name = "difficulty_level")
    protected String difficultyLevel;
    
    @Column(length = 2048)
    protected String description;
	    
    public Tutorial(String name, String muscleGroup, String difficultyLevel, String description) {
		this.name = name;
		this.muscleGroup = muscleGroup;
		this.difficultyLevel = difficultyLevel;
		this.description = description;
	}

}
