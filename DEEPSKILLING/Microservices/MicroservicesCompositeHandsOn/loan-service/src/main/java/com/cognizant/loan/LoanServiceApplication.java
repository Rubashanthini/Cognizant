package com.cognizant.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Bootstraps the Loan microservice.
 * <p>
 * {@link EnableDiscoveryClient} registers this application with the
 * configured Eureka Discovery Server under the logical service id
 * {@code loan-service}, allowing the api-gateway and other clients to
 * locate it without a hardcoded host/port.
 * </p>
 */
@SpringBootApplication
@EnableDiscoveryClient
public class LoanServiceApplication {

    /**
     * Application entry point.
     *
     * @param args standard command line arguments, forwarded to Spring Boot
     */
    public static void main(String[] args) {
        SpringApplication.run(LoanServiceApplication.class, args);
    }
}
