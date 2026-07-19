package com.cognizant.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * EvenCheckerTest
 * ------------------------------------------------------------------
 * Exercise 1 - Parameterized Tests.
 *
 * Uses JUnit 5's {@code @ParameterizedTest} together with
 * {@code @ValueSource} to run the same test logic against multiple
 * different inputs, avoiding duplicated test methods.
 * ------------------------------------------------------------------
 */
@DisplayName("EvenChecker - Parameterized Tests")
class EvenCheckerTest {

    private EvenChecker evenChecker;

    @BeforeEach
    void setUp() {
        evenChecker = new EvenChecker();
    }

    /**
     * Verifies isEven() returns true for a range of even numbers,
     * including zero and a negative even number.
     */
    @ParameterizedTest(name = "isEven({0}) should be TRUE")
    @ValueSource(ints = {2, 4, 6, 8, 10, 0, -2})
    @DisplayName("isEven() returns true for even numbers")
    void testIsEven_withEvenNumbers_returnsTrue(int number) {
        assertTrue(evenChecker.isEven(number),
                () -> number + " was expected to be even but isEven() returned false");
    }

    /**
     * Verifies isEven() returns false for a range of odd numbers,
     * including a negative odd number.
     */
    @ParameterizedTest(name = "isEven({0}) should be FALSE")
    @ValueSource(ints = {1, 3, 5, 7, 9, -1, -3})
    @DisplayName("isEven() returns false for odd numbers")
    void testIsEven_withOddNumbers_returnsFalse(int number) {
        assertFalse(evenChecker.isEven(number),
                () -> number + " was expected to be odd but isEven() returned true");
    }

    /**
     * A plain (non-parameterized) sanity check test to confirm the
     * EvenChecker instance is created correctly.
     */
    @Test
    @DisplayName("EvenChecker instance is not null")
    void testEvenCheckerInstanceCreated() {
        assertTrue(evenChecker != null, "EvenChecker instance should have been initialized in setUp()");
    }
}
