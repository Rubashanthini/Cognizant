package com.cognizant.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Bootstraps the Netflix Eureka Server.
 * <p>
 * The {@link EnableEurekaServer} annotation turns this plain Spring Boot
 * application into a fully functional Service Registry. Once started,
 * it listens on port {@code 8761} (see {@code application.properties})
 * and every other microservice in this project (account-service,
 * loan-service, greet-service, api-gateway) registers itself with, and
 * discovers peers through, this server.
 * </p>
 * <p>
 * The registry dashboard can be viewed by browsing to
 * {@code http://localhost:8761}.
 * </p>
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaDiscoveryServerApplication {

    /**
     * Application entry point.
     *
     * @param args standard command line arguments, forwarded to Spring Boot
     */
    public static void main(String[] args) {
        SpringApplication.run(EurekaDiscoveryServerApplication.class, args);
    }
}
