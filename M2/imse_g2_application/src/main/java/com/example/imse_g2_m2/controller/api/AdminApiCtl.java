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
import com.example.imse_g2_m2.service.MemberService;
import com.example.imse_g2_m2.service.populator.MariaDBPopulatorService;
import com.example.imse_g2_m2.service.reports.BratanovReportService;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin // Will allow the front-end to access the back-end
@AllArgsConstructor
//@PreAuthorize("hasRole('ADMIN')") // authorization in SecurityConifg
@RequestMapping ("/api/admin") // Set this to the endpoint of a certain user that is logged in
public class AdminApiCtl {

//	private LocationService locationService;
	private MemberService memberService;
//	private TutorialService tutorialService;
	private MariaDBPopulatorService populatorService;
	
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
	public void populateMariaDb () {
		populatorService.insertData();
	}
	
	@GetMapping("/report_Bratanov") 
	public ResponseEntity<BratanovReportDTO> getBeginnerTutorialReport() {
        return ResponseEntity.ok(bratanovReportService.getBeginnerTutorialReport());
    }
	
}
