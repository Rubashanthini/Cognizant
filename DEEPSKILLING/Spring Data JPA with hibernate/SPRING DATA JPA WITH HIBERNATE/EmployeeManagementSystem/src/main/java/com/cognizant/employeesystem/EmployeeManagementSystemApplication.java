package com.cognizant.employeesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Cognizant Digital Nurture 5.0 - "Spring Data JPA and Hibernate" exercises.
 *
 * Entry point of the Employee Management System. JPA auditing
 * (Exercise 7) and the primary/secondary data source configuration
 * (Exercise 9) are wired via dedicated @Configuration classes under
 * the {@code config} package rather than directly on this class, to
 * keep concerns separated and easy to navigate.
 */
@SpringBootApplication
public class EmployeeManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementSystemApplication.class, args);
    }
}
