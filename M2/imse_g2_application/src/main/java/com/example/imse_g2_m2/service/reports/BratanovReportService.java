package com.example.imse_g2_m2.service.reports;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.imse_g2_m2.model.reports.BratanovReportDTO;
import com.example.imse_g2_m2.repo.noSqlRepo.TutorialNoSqlRepo;
import com.example.imse_g2_m2.repo.reports.BratanovReportInterface;
import com.example.imse_g2_m2.repo.sqlRepo.TutorialSqlRepo;

@Service
public class BratanovReportService {

	private final TutorialSqlRepo mariaDBRepo;
    private final TutorialNoSqlRepo mongoDBRepo;	
    private BratanovReportInterface currentRepo;

    public BratanovReportService(
			@Qualifier("tutorialSqlRepo") TutorialSqlRepo mariaDBRepo,
			@Qualifier("tutorialNoSqlRepo") TutorialNoSqlRepo mongoDBRepo) {
		super();
		this.mariaDBRepo = mariaDBRepo;
		this.mongoDBRepo = mongoDBRepo;
		switchToMariaDB(); 
	} 
    
    public BratanovReportDTO getBeginnerTutorialReport() {
        return currentRepo.getBeginnerTutorialReport();
    }



	public void switchToMariaDB() {
		 this.currentRepo = mariaDBRepo;
	}



	public void switchToMongoDB() {
		 this.currentRepo = mongoDBRepo;
	}
}
