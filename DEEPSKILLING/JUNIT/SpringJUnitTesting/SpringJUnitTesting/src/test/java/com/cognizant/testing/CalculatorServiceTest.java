package com.cognizant.testing;

import com.cognizant.testing.service.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Exercise 1 - Basic Unit Test.
 * <p>
 * Plain JUnit 5 unit test (no Spring context required) verifying the
 * behavior of {@link CalculatorService#add(int, int)}.
 */
@DisplayName("CalculatorService Unit Tests")
class CalculatorServiceTest {

    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorService();
    }

    @Test
    @DisplayName("add() should return the correct sum of two positive integers")
    void testAddPositiveNumbers() {
        int result = calculatorService.add(2, 3);
        assertEquals(5, result, "2 + 3 should equal 5");
    }

    @Test
    @DisplayName("add() should correctly handle negative numbers")
    void testAddNegativeNumbers() {
        int result = calculatorService.add(-2, -3);
        assertEquals(-5, result, "-2 + -3 should equal -5");
    }

    @Test
    @DisplayName("add() should return the other operand when one operand is zero")
    void testAddWithZero() {
        int result = calculatorService.add(0, 7);
        assertEquals(7, result, "0 + 7 should equal 7");
    }
}
