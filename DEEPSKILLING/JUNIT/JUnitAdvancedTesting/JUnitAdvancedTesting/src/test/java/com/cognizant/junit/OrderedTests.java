package com.cognizant.junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * OrderedTests
 * ------------------------------------------------------------------
 * Exercise 3 - Test Execution Order.
 *
 * Demonstrates JUnit 5's {@code @TestMethodOrder} combined with
 * {@code @Order} to force test methods to run in a specific,
 * predictable sequence rather than the default (non-deterministic /
 * method-name based) order.
 *
 * A shared, static list ({@code executionOrder}) records the order in
 * which each test method actually runs. The final test method
 * (Order 5) asserts that all previous methods executed in the exact
 * order that was declared, proving @Order works as expected.
 * ------------------------------------------------------------------
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Ordered Test Execution - @TestMethodOrder / @Order")
class OrderedTests {

    // Shared across all test method invocations within this test class instance.
    private static final List<String> executionOrder = new ArrayList<>();

    @Test
    @Order(1)
    @DisplayName("Step 1: First test executed")
    void firstTest() {
        executionOrder.add("firstTest");
        assertEquals(1, executionOrder.size(), "firstTest should be the 1st method to run");
    }

    @Test
    @Order(2)
    @DisplayName("Step 2: Second test executed")
    void secondTest() {
        executionOrder.add("secondTest");
        assertEquals(2, executionOrder.size(), "secondTest should be the 2nd method to run");
        assertEquals("firstTest", executionOrder.get(0), "firstTest must have run before secondTest");
    }

    @Test
    @Order(3)
    @DisplayName("Step 3: Third test executed")
    void thirdTest() {
        executionOrder.add("thirdTest");
        assertEquals(3, executionOrder.size(), "thirdTest should be the 3rd method to run");
        assertEquals("secondTest", executionOrder.get(1), "secondTest must have run before thirdTest");
    }

    @Test
    @Order(4)
    @DisplayName("Step 4: Fourth test executed")
    void fourthTest() {
        executionOrder.add("fourthTest");
        assertEquals(4, executionOrder.size(), "fourthTest should be the 4th method to run");
        assertEquals("thirdTest", executionOrder.get(2), "thirdTest must have run before fourthTest");
    }

    @Test
    @Order(5)
    @DisplayName("Step 5: Fifth test verifies full execution order")
    void fifthTest_verifiesCompleteOrder() {
        executionOrder.add("fifthTest_verifiesCompleteOrder");
        assertEquals(5, executionOrder.size(), "fifthTest should be the 5th and final method to run");

        List<String> expectedOrder = Arrays.asList(
                "firstTest",
                "secondTest",
                "thirdTest",
                "fourthTest",
                "fifthTest_verifiesCompleteOrder"
        );
        assertEquals(expectedOrder, executionOrder,
                "Test methods should have executed in the exact order declared by @Order");
    }
}
