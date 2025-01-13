package com.example.imse_g2_m2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.imse_g2_m2.model.Member;
import com.example.imse_g2_m2.model.Tutorial;
import com.example.imse_g2_m2.model.UserPrincipal;
import com.example.imse_g2_m2.repo.sqlRepo.MemberSqlRepo;

@Service
public class SingleMemberService {

	
	private final MemberSqlRepo currentRepo;
	
//	Member myData;
	
	public SingleMemberService(
			@Qualifier("memberSqlRepo") MemberSqlRepo mariaDBRepo
			/*@Qualifier("memberNoSqlRepo") MemberNoSqlRepo mongoDBRepo*/) {
		super();
		this.currentRepo = mariaDBRepo;
//		myData = null;
//		this.mongoDBRepo = mongoDBRepo;
//		switchToMariaDB(); 
	}	
	
	public Member getAuthenticatedMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserPrincipal) {
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            
            return userPrincipal.getMemberData(); // Return the Member data
        }
        throw new RuntimeException("User is not authenticated");
    }
	
	public String saveTutorial(Tutorial tutorial) {
		Member myData = getAuthenticatedMember();
		if (myData != null) {
			List<Tutorial> savedTutorials = myData.getSaved_tutorials();
			if (savedTutorials.add(tutorial)) {
				currentRepo.save(myData);
				return "Tutorial saved successfully!";
			} else
				return "Tutorial couldn't be saved!";
		}
		else 
			throw new RuntimeException("myData is null!");
	}
	
	public String removeTutorial(Tutorial tutorial) {
		Member myData = getAuthenticatedMember();
		if (myData != null) {
			List<Tutorial> savedTutorials = myData.getSaved_tutorials();
			if (savedTutorials.remove(tutorial)) {
				currentRepo.save(myData);
				return "Tutorial removed successfully!";
			} else
				return "Tutorial couldn't be removed!";
		}
		else 
			throw new RuntimeException("myData is null!");
	}
}
