package com.library;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Basic smoke test that verifies the Spring Boot application context
 * (Exercise 9) loads successfully with all beans wired correctly.
 */
@SpringBootTest
class LibraryManagementApplicationTests {

    /**
     * If the Spring Boot application context fails to start, this test
     * will fail - a simple but effective sanity check for the whole
     * Spring Boot / Spring Data JPA / H2 wiring.
     */
    @Test
    void contextLoads() {
        // Intentionally empty - success means the ApplicationContext started.
    }
}
