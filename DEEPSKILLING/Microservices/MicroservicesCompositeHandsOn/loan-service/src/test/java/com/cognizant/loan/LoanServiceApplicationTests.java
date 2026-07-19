package com.cognizant.loan;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * Basic Spring context load test for the loan-service application.
 */
@SpringBootTest
@TestPropertySource(properties = {
        "eureka.client.enabled=false"
})
class LoanServiceApplicationTests {

    /**
     * Verifies that the Spring application context loads successfully.
     */
    @Test
    void contextLoads() {
        // Intentionally empty: a successful test run means the
        // ApplicationContext was created without errors.
    }
}
