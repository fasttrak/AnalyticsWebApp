package com.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableCaching
@EnableMongoRepositories("com.webapp.repository") 
@SpringBootApplication
public class AnalyticsWebAppApplication{

	public static void main(String[] args) {
		SpringApplication.run(AnalyticsWebAppApplication.class, args);
	}
}
