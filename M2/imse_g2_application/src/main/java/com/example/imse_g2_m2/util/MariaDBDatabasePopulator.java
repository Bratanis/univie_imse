package com.example.imse_g2_m2.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.imse_g2_m2.model.Location;
import com.example.imse_g2_m2.service.LocationService;
import com.github.javafaker.Faker;

public class MariaDBDatabasePopulator {

	@Autowired
	private LocationService locationService;
	
    public static void main(String[] args) {
        Faker faker = new Faker();  // We use Faker instance to generate random data

        // Database connection parameters for MariaDB
        String url = "jdbc:mariadb://localhost:3306/imse"; 
        String username = "root";
        String password = "bigRamy69";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected to the MariaDB database.");

            insertLocations(connection, faker);  
            insertMembers(connection, faker);
            insertBankDetails(connection, faker);
            insertTutorials(connection, faker);
            insertVideoTutorials(connection, faker);
            insertSavedTutorials(connection);
            insertRecommendations(connection);

            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    private static void insertLocations(Connection connection, Faker faker) throws SQLException {
        String insertSql = "INSERT INTO location (manager_name, address) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(insertSql)) {
            for (int i = 1; i <= 10; i++) {  
            	Location newLocation;
            	try {
//					locationService.insertLocation(newLocation);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                ps.setString(1, faker.name().fullName());
                ps.setString(2, faker.address().fullAddress());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private static void insertMembers(Connection connection, Faker faker) throws SQLException {
    	Random random = new Random(); 
        String insertSql = "INSERT INTO member (name, age, password, location_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 1; i <= 100; i++) {
                ps.setString(1, faker.name().fullName());
                ps.setInt(2, random.nextInt(50) + 18);  
                ps.setString(3, faker.internet().password());
                ps.setInt(4, random.nextInt(10) + 1); 
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private static void insertBankDetails(Connection connection, Faker faker) throws SQLException {
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
    
    private static void insertTutorials(Connection connection, Faker faker) throws SQLException {
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
    }
}
