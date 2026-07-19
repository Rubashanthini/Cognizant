package com.cognizant.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the SpringJUnitTesting application.
 * <p>
 * This application demonstrates the full Cognizant Digital Nurture 5.0
 * "JUnit Spring Test Exercises" curriculum, including:
 * <ul>
 *     <li>Basic unit testing with JUnit 5</li>
 *     <li>Mocking repositories with Mockito</li>
 *     <li>Testing REST controllers with MockMvc</li>
 *     <li>Full Spring Boot integration testing</li>
 *     <li>Exception handling at both the service and controller layers</li>
 *     <li>Custom Spring Data JPA repository queries</li>
 *     <li>Parameterized testing with JUnit 5</li>
 * </ul>
 *
 * @author Cognizant Digital Nurture 5.0
 */
@SpringBootApplication
public class SpringJUnitTestingApplication {

    /**
     * Boots the Spring application context.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringJUnitTestingApplication.class, args);
    }
}
