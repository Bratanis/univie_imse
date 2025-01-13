package com.example.imse_g2_m2.service.populator;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

import com.example.imse_g2_m2.model.BankDetails;
import com.example.imse_g2_m2.model.Location;
import com.example.imse_g2_m2.model.Member;
import com.example.imse_g2_m2.model.Tutorial;
import com.example.imse_g2_m2.model.TutorialIdComparator;
import com.example.imse_g2_m2.service.LocationService;
import com.example.imse_g2_m2.service.AllMembersService;
import com.example.imse_g2_m2.service.TutorialService;
import com.github.javafaker.Faker;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MariaDBPopulatorService {

	LocationService locationService;
	AllMembersService memberService;
	TutorialService tutorialService;
	
    public void insertData() {
        Faker faker = new Faker();  // We use Faker instance to generate random data
        
        locationService.emptyLocations();
        //tutorialService.clearTutorial();
        //memberService.clearMember();

        insertLocations(faker);
        //insertTutorials(faker);
        //insertMembers(faker);
        /*insertBankDetails(faker);
        insertVideoTutorials(faker);*/
    }

    
    private void insertLocations(Faker faker) {
    	String managerName = "";
    	String address = "";
    	Location location;
            for (int i = 1; i <= 10; i++) {  
            	managerName = faker.name().fullName();
            	address = faker.address().fullAddress();
            	location = new Location(managerName, address);
            	try {
            		locationService.insertLocation(location);
            	} catch (Exception e) {
            		System.out.println(e.getMessage());
            	}
            }
     }
    
    private void insertTutorials(Faker faker) {
    	Random random = new Random();
        String[] tutorialNamesArray = {
                "Push-Ups Basics", "Dumbbell Chest Press", "Squat Masterclass", 
                "Deadlift Guide", "Pull-Up Techniques", "Plank Variations", 
                "Lunges Explained", "Bench Press Tips", "Bicep Curls Workout", "Tricep Dips"
            };
        String[] muscleGroupsArray = {"Chest", "Back", "Biceps", "Triceps", "Shoulders", "Thigh", "Calf", "Breast", "Abdominal muscles"};
        String[] difficultyLevelsArray = {"beginner", "intermediate", "advanced"};
        String description = "";
        Tutorial tutorial;
        
        for (int i = 1; i <= 50; i++) {
        	String tutorialName = tutorialNamesArray[random.nextInt(tutorialNamesArray.length)];
        	String muscleGroup = muscleGroupsArray[random.nextInt(muscleGroupsArray.length)];
        	String difficultyLevel = difficultyLevelsArray[random.nextInt(difficultyLevelsArray.length)];
        	description = faker.chuckNorris().fact();
        	tutorial = new Tutorial(tutorialName, muscleGroup, difficultyLevel, description);
        	
        	try {
        		tutorialService.insertTutorial(tutorial);
        	} catch (Exception e) {
        		System.out.println(e.getMessage());
        	}
        }
    }

    private void insertMembers(Faker faker) {
    	Random random = new Random(); 
    	String name = "";
    	int age;
    	String password = "";
    	
    	String cardNumber = "";
    	String expDate = "";
    	BankDetails bankDetails;
    	
    	Location location;
    	List<Location> locations = new ArrayList<>();
    	List<Tutorial> tutorials = new ArrayList<>();
    	
    	Member member;
    	
    	for (int i = 1; i <= 100; i++) {
    		name = faker.name().fullName();
        	age = random.nextInt(50) + 18;
        	password = faker.internet().password();
        	
        	cardNumber = faker.finance().creditCard();
        	expDate = faker.business().creditCardExpiry();
        	bankDetails = new BankDetails(cardNumber, expDate);
        	
        	locations = locationService.getAllLocations();
        	location = locations.get(random.nextInt(10));
        	
        	tutorials = tutorialService.getAllTutorials();
            int randomCount = random.nextInt(10) + 1;
            Set<Tutorial> usedTutorials = new TreeSet<>(new TutorialIdComparator());
            while (usedTutorials.size() < randomCount) {
                int tutorialId = random.nextInt(50);
                usedTutorials.add(tutorials.get(tutorialId));
            }
            List<Tutorial> savedTutorials = new ArrayList<>(usedTutorials);
        	
        	member = new Member(0, name, age, password, bankDetails, location, savedTutorials); // memberId gets overwritten by db
        	try {
        		memberService.insertMember(member);
        	} catch (Exception e) {
        		System.out.println(e.getMessage());
        	}      	
    	}
    }

    /*private static void insertBankDetails(Faker faker) throws SQLException {
        String insertSql = "INSERT INTO bank_details (member_id, card_number, exp_date) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(insertSql)) {
            for (int i = 1; i <= 100; i++) {
                ps.setInt(1, i);
                ps.setString(2, faker.finance().creditCard());
                ps.setString(3, faker.business().creditCardExpiry());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
    
    private static void insertTutorials(Faker faker) throws SQLException {
        String insertSql = "INSERT INTO tutorial (name, muscle_group, difficulty_level, description) VALUES (?, ?, ?, ?)";
        String[] tutorialNames = {
                "Push-Ups Basics", "Dumbbell Chest Press", "Squat Masterclass", 
                "Deadlift Guide", "Pull-Up Techniques", "Plank Variations", 
                "Lunges Explained", "Bench Press Tips", "Bicep Curls Workout", "Tricep Dips"
            };
        String[] muscleGroups = {"Chest", "Back", "Biceps", "Triceps", "Shoulders", "Thigh", "Calf", "Breast", "Abdominal muscles"};
        String[] difficultyLevels = {"beginner", "intermediate", "advanced"};
        
        try (PreparedStatement ps = connection.prepareStatement(insertSql)) {
            for (int i = 1; i <= 50; i++) {  
            	ps.setString(1, tutorialNames[faker.random().nextInt(tutorialNames.length)]);
                ps.setString(2, muscleGroups[faker.random().nextInt(muscleGroups.length)]);
                ps.setString(3, difficultyLevels[faker.random().nextInt(difficultyLevels.length)]);
                ps.setString(4, faker.chuckNorris().fact());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private static void insertVideoTutorials(Connection connection, Faker faker) throws SQLException {
    	Random random = new Random(); 
        String insertSql = "INSERT INTO video_tutorial (tutorial_id, duration, url) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(insertSql)) {
            for (int i = 1; i <= 50; i++) {
                ps.setInt(1, i);
                ps.setInt(2, random.nextInt(60) + 1);
                ps.setString(3, faker.internet().url());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
    
    private static void insertSavedTutorials(Connection connection) throws SQLException {
        String insertSql = "INSERT INTO saved (member_id, tutorial_id) VALUES (?, ?)";
        Random generator = new Random();

        try (PreparedStatement ps = connection.prepareStatement(insertSql)) {
            for (int memberId = 1; memberId <= 100; memberId++) {
                int randomCount = generator.nextInt(10) + 1;
                Set<Integer> usedTutorials = new TreeSet<>();

                while (usedTutorials.size() < randomCount) {
                    int tutorialId = generator.nextInt(50) + 1;
                    usedTutorials.add(tutorialId);
                }

                for (int tutorialId : usedTutorials) {
                	ps.setInt(2, tutorialId);
                    ps.setInt(1, memberId);         
                    ps.addBatch();              
                }
            }
            ps.executeBatch();
        }
    }
    
    private static void insertRecommendations(Connection connection) throws SQLException {
    	String insertSql = "INSERT INTO recommend(recommender_tutorial_id, recommended_tutorial_id) VALUES (?, ?)";
    	Random generator = new Random();
    	
    	try (PreparedStatement ps = connection.prepareStatement(insertSql)) {
    		for (int recommenderId = 1; recommenderId <= 50; recommenderId++) {
    			int randomCount = generator.nextInt(3) + 1;
    			Set<Integer> recommendedTutorials = new TreeSet<>();
    			
    			while (recommendedTutorials.size() < randomCount) {
    				int recommendedId = generator.nextInt(50) + 1;
    				if (recommenderId != recommendedId)
    					recommendedTutorials.add(recommendedId);
    			}
    			
    			for (int recommendedId : recommendedTutorials) {
    				ps.setInt(2, recommendedId);
    				ps.setInt(1, recommenderId);
    				ps.addBatch();
    			}
    		}
    		ps.executeBatch();
    	}
    }*/
}
