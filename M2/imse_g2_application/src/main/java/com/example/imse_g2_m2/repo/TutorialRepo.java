package com.example.imse_g2_m2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.imse_g2_m2.model.Tutorial;
import com.example.imse_g2_m2.model.reports.BratanovReportDTO;

@Repository
public interface TutorialRepo extends JpaRepository<Tutorial, Integer>{
	@Query(value = """
	        SELECT 
	            t.name AS tutorialName,
	            vt.url AS videoUrl,
	            MIN(m.age) AS minAge,
	            MAX(m.age) AS maxAge,
	            COUNT(s.tutorial_id) AS timesSaved
	        FROM 
	            member m
	        JOIN 
	            saved s ON m.member_id = s.member_id
	        JOIN 
	            tutorial t ON s.tutorial_id = t.tutorial_id
	        JOIN 
	            video_tutorial vt ON t.tutorial_id = vt.tutorial_id
	        WHERE 
	            t.difficulty_level = 'beginner'
	        GROUP BY 
	            t.name, vt.url
	        ORDER BY 
	            timesSaved DESC
	        LIMIT 1
	        """, nativeQuery = true)
	    BratanovReportDTO getBeginnerTutorialReport();
}
