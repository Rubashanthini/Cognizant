package com.cognizant.eureka;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * Basic Spring context load test for the Eureka Discovery Server.
 * <p>
 * This test simply verifies that the Spring application context starts
 * up successfully with the {@code @EnableEurekaServer} configuration in
 * place. A random port is used so the test does not collide with a
 * locally running instance on port 8761.
 * </p>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "eureka.client.register-with-eureka=false",
        "eureka.client.fetch-registry=false"
})
class EurekaDiscoveryServerApplicationTests {

    /**
     * Verifies that the Spring application context for the Eureka server
     * loads without throwing any exceptions.
     */
    @Test
    void contextLoads() {
        // Intentionally empty: a successful test run means the
        // ApplicationContext was created without errors.
    }
}
