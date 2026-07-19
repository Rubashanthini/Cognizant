package com.cognizant.gateway;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Smoke test that verifies the full Spring application context loads
 * successfully with all configured beans - including the Gateway routes,
 * the LoadBalancer configuration, and the Resilience4j configuration.
 *
 * <p>A failure in this test typically indicates a misconfiguration in one
 * of the {@code @Configuration} classes, {@code application.yml}, or a
 * missing/incompatible dependency.</p>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Application Context Load Test")
class GatewayApplicationTests {

    /**
     * Verifies that the Spring application context starts up without
     * throwing any exceptions.
     */
    @Test
    @DisplayName("Should load the Spring application context successfully")
    void contextLoads() {
        // If the application context fails to load, this test will fail
        // automatically during the @SpringBootTest bootstrap phase.
    }
}
