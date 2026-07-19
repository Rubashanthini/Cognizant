package com.cognizant.auth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Smoke test verifying that the Spring application context loads successfully with all
 * three security filter chains (OAuth2 Login, Resource Server, self-issued JWT) wired up.
 */
@SpringBootTest
class CentralizedAuthenticationApplicationTests {

    /**
     * Fails the build if the application context cannot start, catching bean wiring or
     * security configuration errors early.
     */
    @Test
    void contextLoads() {
    }
}
