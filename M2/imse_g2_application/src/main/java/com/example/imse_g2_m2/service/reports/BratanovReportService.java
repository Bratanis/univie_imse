package com.example.imse_g2_m2.service.reports;

import org.springframework.stereotype.Service;

import com.example.imse_g2_m2.model.reports.BratanovReportDTO;
import com.example.imse_g2_m2.repo.TutorialRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BratanovReportService {

	private TutorialRepo tutorialRepo;

    public BratanovReportDTO getBeginnerTutorialReport() {
        return tutorialRepo.getBeginnerTutorialReport();
    }
}
