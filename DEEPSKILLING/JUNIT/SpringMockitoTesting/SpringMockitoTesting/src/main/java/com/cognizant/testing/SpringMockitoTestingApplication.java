package com.cognizant.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Spring Boot application.
 *
 * <p>This application demonstrates mocking dependencies in Spring tests
 * using Mockito, covering controller unit tests, service unit tests,
 * and full integration tests as per the Cognizant Digital Nurture 5.0
 * "Mocking Dependencies in Spring Tests using Mockito" module.</p>
 *
 * @author Cognizant Digital Nurture 5.0
 * @version 1.0.0
 */
@SpringBootApplication
public class SpringMockitoTestingApplication {

    /**
     * Main method used to launch the Spring Boot application.
     *
     * @param args command line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringMockitoTestingApplication.class, args);
    }
}
