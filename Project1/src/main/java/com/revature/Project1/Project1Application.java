package com.revature.Project1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Project 1 Application
 * @author Cyrus De Jesus
 */
@SpringBootApplication
@EntityScan("com.revature.models") // Spring Boot Search for DB Entities
@ComponentScan("com.revature") // Beans Search
@EnableJpaRepositories("com.revature.daos") // Spring Boot Search for JPARepositories
public class Project1Application {

	/**
	 * Main Method
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Project1Application.class, args);
	}

}
