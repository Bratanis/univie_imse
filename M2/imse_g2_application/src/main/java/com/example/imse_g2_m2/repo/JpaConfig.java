package com.example.imse_g2_m2.repo;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.imse_g2_m2.repo.sqlRepo")
public class JpaConfig {
}