package com.cognizant.ormlearn;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Standard Spring Boot context-load smoke test.
 *
 * NOTE: this loads the full application context, which requires a
 * reachable MySQL "ormlearn" schema (see README.md - MySQL setup).
 * Run `mvn test` only after the database is up and application.properties
 * points to it, otherwise this test will fail with a connection error.
 */
@SpringBootTest
class OrmLearnApplicationTests {

    @Test
    void contextLoads() {
        // Intentionally empty - passing means the Spring context,
        // including the DataSource / EntityManagerFactory, started successfully.
    }
}
