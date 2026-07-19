package com.cognizant.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Spring Cloud Gateway Exercises application.
 *
 * <p>This application demonstrates three core capabilities of an API Gateway
 * built with Spring Boot 3 and Spring Cloud:</p>
 * <ul>
 *     <li><b>Exercise 1:</b> Edge Services - Routing and Filtering using
 *     Spring Cloud Gateway with a custom {@code GlobalFilter} for request logging.</li>
 *     <li><b>Exercise 2:</b> Load Balancing - Client-side load balancing across
 *     multiple service instances using Spring Cloud LoadBalancer with a
 *     {@code RandomLoadBalancer} strategy.</li>
 *     <li><b>Exercise 3:</b> Resilience Patterns - Circuit Breaker and Time Limiter
 *     patterns implemented using Resilience4j, with fallback handling for
 *     downstream service failures.</li>
 * </ul>
 *
 * <p>The application is built on the reactive stack (Spring WebFlux) since
 * Spring Cloud Gateway requires a non-blocking, reactive runtime.</p>
 *
 * @author Cognizant Digital Nurture 5.0
 * @version 1.0.0
 */
@SpringBootApplication
public class GatewayApplication {

    /**
     * Bootstraps and launches the Spring Boot application context.
     *
     * @param args command-line arguments passed to the application at startup
     */
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
