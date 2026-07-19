package com.cognizant.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * Basic Spring context load test for the api-gateway application.
 * <p>
 * Eureka client registration and the discovery locator are disabled for
 * this test so it can run in isolation without a running Eureka server.
 * </p>
 */
@SpringBootTest
@TestPropertySource(properties = {
        "eureka.client.enabled=false",
        "spring.cloud.gateway.discovery.locator.enabled=false"
})
class ApiGatewayApplicationTests {

    /**
     * Verifies that the Spring application context loads successfully.
     */
    @Test
    void contextLoads() {
        // Intentionally empty: a successful test run means the
        // ApplicationContext was created without errors.
    }
}
