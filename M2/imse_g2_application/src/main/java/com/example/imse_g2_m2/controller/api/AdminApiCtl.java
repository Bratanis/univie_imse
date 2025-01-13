package com.example.imse_g2_m2.controller.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.imse_g2_m2.model.Member;
import com.example.imse_g2_m2.model.reports.BratanovReportDTO;
import com.example.imse_g2_m2.service.LocationService;
import com.example.imse_g2_m2.service.MemberService;
import com.example.imse_g2_m2.service.TutorialService;
import com.example.imse_g2_m2.service.migrator.MariaDBToMongoDBMigrationService;
import com.example.imse_g2_m2.service.populator.MariaDBPopulatorService;
import com.example.imse_g2_m2.service.reports.BratanovReportService;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin // Will allow the front-end to access the back-end
@AllArgsConstructor
//@PreAuthorize("hasRole('ADMIN')") // authorization in SecurityConifg
@RequestMapping ("/api/admin") // Set this to the endpoint of a certain user that is logged in
public class AdminApiCtl {

	private LocationService locationService;
	private MemberService memberService;
	private TutorialService tutorialService;
	
	private MariaDBPopulatorService populatorService;
	
	private MariaDBToMongoDBMigrationService migratorService;
	
	private BratanovReportService bratanovReportService;
	
	@GetMapping("")
	public String initialAdminGreeting() {
		return "Hello, admin!!!"; 
	}
	
	
	@GetMapping("/all_members")
	public List <Member> getAllMembers (){
		return memberService.getAllMembers();
	}
	
	@GetMapping("/members/{memberId}")
	public Member getMemberById (@PathVariable int memberId){
		return memberService.getMemberById(memberId);
	}
	
//	@GetMapping("/members/{name}")
//	public Member getMemberByName (@PathVariable String name){
//		return memberService.getMemberByName(name);
//	}
	
	// Doesnt work
	@GetMapping("/populate")
	public String populateMariaDb () {
		populatorService.insertData();
		return "Successfully inserted example data!";
	}
	
	@GetMapping("/migrate")
	public String migrateDb () {
		try {
			migratorService.attemptMigration();
			return "Successfully migrated example data!";
		} catch (Exception e) {
			return "Migration attempt failed" + e.getStackTrace();
		}
		
	}
	
	@GetMapping("/use_MariaDB")
	public String useSqlServiceRepo () {
		
		locationService.switchToMariaDB();
		memberService.switchToMariaDB();
		tutorialService.switchToMariaDB();
		bratanovReportService.switchToMariaDB();
		
		return "You are now using MariaDB!";
	}
	
	@GetMapping("/use_MongoDB")
	public String useNoSqlServiceRepo () {

		locationService.switchToMongoDB();
		memberService.switchToMongoDB();
		tutorialService.switchToMongoDB();
		bratanovReportService.switchToMongoDB();

		return "You are now using MongoDB!";
	}
	
	
	
	@GetMapping("/report_Bratanov") 
	public ResponseEntity<BratanovReportDTO> getBeginnerTutorialReport() {
        return ResponseEntity.ok(bratanovReportService.getBeginnerTutorialReport());
    }
	
}








