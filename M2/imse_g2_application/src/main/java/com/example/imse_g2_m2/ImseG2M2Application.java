package com.example.imse_g2_m2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImseG2M2Application {

    public static void main(String[] args) {
//      Run the Spring Boot application
        SpringApplication.run(ImseG2M2Application.class, args);

        
//        try {
//        	MariaDBToMongoDBMigration.main(args); 
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.err.println("Error populating the database: " + e.getMessage());
//        }
    }
}
