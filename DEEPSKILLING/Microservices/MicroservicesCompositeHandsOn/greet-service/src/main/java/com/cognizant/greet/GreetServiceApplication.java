package com.cognizant.greet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Bootstraps the Greet microservice.
 * <p>
 * {@link EnableDiscoveryClient} registers this application with the
 * configured Eureka Discovery Server under the logical service id
 * {@code greet-service}, allowing the api-gateway to route requests to
 * it (e.g. {@code http://localhost:9090/greet-service/greet}).
 * </p>
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GreetServiceApplication {

    /**
     * Application entry point.
     *
     * @param args standard command line arguments, forwarded to Spring Boot
     */
    public static void main(String[] args) {
        SpringApplication.run(GreetServiceApplication.class, args);
    }
}
