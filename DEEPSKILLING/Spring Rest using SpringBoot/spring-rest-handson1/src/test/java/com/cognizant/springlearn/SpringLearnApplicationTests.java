package com.cognizant.springlearn;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * SpringLearnApplicationTests.java
 *
 * Basic smoke test that verifies the Spring Boot application context
 * loads successfully with all bean definitions and configuration in place.
 */
@SpringBootTest
class SpringLearnApplicationTests {

    /**
     * Verifies the Spring application context starts up without errors.
     */
    @Test
    void contextLoads() {
        // If the ApplicationContext fails to start, this test will fail automatically.
    }
}
