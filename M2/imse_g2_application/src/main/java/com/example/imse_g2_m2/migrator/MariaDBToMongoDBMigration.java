package com.example.imse_g2_m2.migrator;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MariaDBToMongoDBMigration {
    public static void main(String[] args) throws Exception {
    	
    	// Database connection parameters for MariaDB
        String url = "jdbc:mariadb://localhost:3306/imse"; 
        String username = "root";
        String password = "bigRamy69";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
        	System.out.println("Connecting to MongoDB");
        	MongoDatabase mongoDatabase = MongoClients.create("mongodb://localhost:27017").getDatabase("fitness-center");
        	
        	migrateMembers(connection, mongoDatabase);
            migrateLocation(connection, mongoDatabase);
            migrateTutorials(connection, mongoDatabase);
            System.out.println("The data is successfully migrated");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void migrateMembers(Connection mariaDBConnection, MongoDatabase mongoDatabase) throws Exception {
        String sqlQuery = "SELECT m.member_id, m.name, m.age, m.password, m.location_id, b.card_number, b.exp_date, l.address " +
                       "FROM member m " +
                       "LEFT JOIN bank_details b ON m.member_id = b.member_id " +
                       "LEFT JOIN location l ON m.location_id = l.location_id";
        Statement statement = mariaDBConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        MongoCollection<Document> memberCollection = mongoDatabase.getCollection("member");

        while (resultSet.next()) {
            Document memberDocument = new Document("member_id", resultSet.getInt("member_id"))
                    .append("name", resultSet.getString("name"))
                    .append("age", resultSet.getInt("age"))
                    .append("password", resultSet.getString("password"));

            Document bankDetails = new Document("card_number", resultSet.getString("card_number"))
                    .append("exp_date", resultSet.getString("exp_date"));
            memberDocument.append("bank_details", bankDetails);

            Document location = new Document("location_id", resultSet.getInt("location_id"))
                    .append("address", resultSet.getString("address"));
            memberDocument.append("location", location);

            String savedTutorialsQuery = "SELECT s.tutorial_id, t.name AS tutorial_name, t.muscle_group, t.difficulty_level, t.description " +
                                         "FROM saved s " +
                                         "JOIN tutorial t ON s.tutorial_id = t.tutorial_id " +
                                         "WHERE s.member_id = ?";
            PreparedStatement ps = mariaDBConnection.prepareStatement(savedTutorialsQuery);
            ps.setInt(1, resultSet.getInt("member_id"));
            ResultSet savedResultSet = ps.executeQuery();
            
            List<Document> savedTutorials = new ArrayList<>();

            while (savedResultSet.next()) {
                Document savedTutorial = new Document("tutorial_id", savedResultSet.getInt("tutorial_id"))
                        .append("tutorial_name", savedResultSet.getString("tutorial_name"))
                        .append("muscle_group", savedResultSet.getString("muscle_group"))
                        .append("difficulty_level", savedResultSet.getString("difficulty_level"))
                        .append("description", savedResultSet.getString("description"));
                savedTutorials.add(savedTutorial);
            }
            memberDocument.append("saved_tutorials", savedTutorials);
            
            memberCollection.insertOne(memberDocument);
        }
    }

    private static void migrateLocation(Connection mariaDBConnection, MongoDatabase mongoDatabase) throws Exception {
        String sqlQuery = "SELECT * FROM location";
        Statement statement = mariaDBConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        MongoCollection<Document> locationCollection = mongoDatabase.getCollection("location");

        while (resultSet.next()) {
            Document locationDocument = new Document("location_id", resultSet.getInt("location_id"))
                    .append("address", resultSet.getString("address"))
                    .append("manager_name", resultSet.getString("manager_name"));

            String membersQuery = "SELECT member_id, name FROM member WHERE location_id = ?";
            PreparedStatement ps = mariaDBConnection.prepareStatement(membersQuery);
            ps.setInt(1, resultSet.getInt("location_id"));
            ResultSet membersResultSet = ps.executeQuery();
            
            List<Document> members = new ArrayList<>();

            while (membersResultSet.next()) {
                Document member = new Document("member_id", membersResultSet.getInt("member_id"))
                        .append("name", membersResultSet.getString("name"));
                members.add(member);
            }
            locationDocument.append("members", members);

            locationCollection.insertOne(locationDocument);
        }
    }

    private static void migrateTutorials(Connection mariaDBConnection, MongoDatabase mongoDatabase) throws Exception {
        String sqlQuery = "SELECT * FROM tutorial";
        Statement statement = mariaDBConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        MongoCollection<Document> tutorialCollection = mongoDatabase.getCollection("tutorial");

        while (resultSet.next()) {
            Document tutorialDocument = new Document("tutorial_id", resultSet.getInt("tutorial_id"))
                    .append("name", resultSet.getString("name"))
                    .append("muscle_group", resultSet.getString("muscle_group"))
                    .append("difficulty_level", resultSet.getString("difficulty_level"))
                    .append("description", resultSet.getString("description"));

            String videoTutorialQuery = "SELECT * FROM video_tutorial WHERE tutorial_id = ?";
            PreparedStatement ps = mariaDBConnection.prepareStatement(videoTutorialQuery);
            ps.setInt(1, resultSet.getInt("tutorial_id"));
            ResultSet videoResultSet = ps.executeQuery();

            if (videoResultSet.next()) {
            	tutorialDocument.append("length", videoResultSet.getInt("duration"))
                        .append("url", videoResultSet.getString("url"));
            }

            String recommendQuery = "SELECT recommended_tutorial_id FROM recommend WHERE recommender_tutorial_id = ?";
            ps = mariaDBConnection.prepareStatement(recommendQuery);
            ps.setInt(1, resultSet.getInt("tutorial_id"));
            ResultSet recommendResultSet = ps.executeQuery();
            
            List<Document> recommendedTutorials = new ArrayList<>();

            while (recommendResultSet.next()) {
                Document recommendDoc = new Document("tutorial_id", recommendResultSet.getInt("recommended_tutorial_id"))
                        .append("tutorial_name", getTutorialNameById(mariaDBConnection, recommendResultSet.getInt("recommended_tutorial_id")))
                        .append("tutorial_link", getTutorialUrlById(mariaDBConnection, recommendResultSet.getInt("recommended_tutorial_id")));
                recommendedTutorials.add(recommendDoc);
            }
            tutorialDocument.append("recommends", recommendedTutorials);

            tutorialCollection.insertOne(tutorialDocument);
        }
    }

    private static String getTutorialNameById(Connection connection, int tutorialId) throws Exception {
        String query = "SELECT name FROM tutorial WHERE tutorial_id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, tutorialId);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("name");
        }
        return "";
    }

    private static String getTutorialUrlById(Connection connection, int tutorialId) throws Exception {
        String query = "SELECT url FROM video_tutorial WHERE tutorial_id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, tutorialId);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("url");
        }
        return "";
    }
}
