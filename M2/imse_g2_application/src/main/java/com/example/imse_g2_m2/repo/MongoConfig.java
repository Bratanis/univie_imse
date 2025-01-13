package com.example.imse_g2_m2.repo;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.imse_g2_m2.repo.noSqlRepo")
public class MongoConfig {
}
