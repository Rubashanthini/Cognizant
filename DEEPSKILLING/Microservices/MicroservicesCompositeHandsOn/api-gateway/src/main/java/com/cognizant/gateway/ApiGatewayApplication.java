package com.cognizant.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Bootstraps the API Gateway.
 * <p>
 * This Spring Cloud Gateway application:
 * </p>
 * <ul>
 *   <li>Registers itself with Eureka via {@link EnableDiscoveryClient}</li>
 *   <li>Load-balances requests to downstream services registered in
 *       Eureka using the {@code lb://SERVICE-NAME} URI scheme (see
 *       {@code application.yml} for the configured routes)</li>
 *   <li>Applies the global {@link com.cognizant.gateway.filter.LoggingFilter}
 *       to log every incoming request</li>
 * </ul>
 * <p>
 * Once running, requests such as
 * {@code http://localhost:9090/greet-service/greet},
 * {@code http://localhost:9090/account-service/accounts/{number}} and
 * {@code http://localhost:9090/loan-service/loans/{number}} are
 * transparently routed to the corresponding downstream microservice.
 * </p>
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

    /**
     * Application entry point.
     *
     * @param args standard command line arguments, forwarded to Spring Boot
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
