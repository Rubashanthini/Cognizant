package com.cognizant.employeesystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Smoke test - verifies the full Spring application context (including
 * both the primary and secondary data source configurations) loads
 * successfully.
 */
@SpringBootTest
class EmployeeManagementSystemApplicationTests {

    @Test
    void contextLoads() {
        // If the application context fails to start, this test fails.
    }
}
