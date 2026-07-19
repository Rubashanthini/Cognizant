package com.cognizant.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Bootstraps the Account microservice.
 * <p>
 * {@link EnableDiscoveryClient} registers this application with the
 * configured Eureka Discovery Server (see {@code application.properties}
 * for the {@code eureka.client.service-url.defaultZone} setting) under
 * the logical service id {@code account-service}, allowing the
 * api-gateway and other clients to locate it without a hardcoded
 * host/port.
 * </p>
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AccountServiceApplication {

    /**
     * Application entry point.
     *
     * @param args standard command line arguments, forwarded to Spring Boot
     */
    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }
}
