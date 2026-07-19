package com.cognizant.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * BasicUtilityTest
 * ------------------------------------------------------------------
 * Standard (non-parameterized) JUnit 5 tests for the {@code Utility}
 * helper class. This class rounds out the test suite with plain
 * assertEquals/assertTrue/assertFalse/assertThrows style tests,
 * complementing the five core exercises and giving AllTests /
 * OrderedTests additional coverage to aggregate.
 * ------------------------------------------------------------------
 */
@DisplayName("Utility - Basic Unit Tests")
class BasicUtilityTest {

    private Utility utility;

    @BeforeEach
    void setUp() {
        utility = new Utility();
    }

    @Nested
    @DisplayName("add()")
    class AddTests {

        @Test
        @DisplayName("adds two positive numbers correctly")
        void testAdd_positiveNumbers() {
            assertEquals(7, utility.add(3, 4));
        }

        @Test
        @DisplayName("adds a positive and a negative number correctly")
        void testAdd_withNegativeNumber() {
            assertEquals(-1, utility.add(4, -5));
        }
    }

    @Nested
    @DisplayName("multiply()")
    class MultiplyTests {

        @Test
        @DisplayName("multiplies two positive numbers correctly")
        void testMultiply_positiveNumbers() {
            assertEquals(12, utility.multiply(3, 4));
        }

        @Test
        @DisplayName("multiplying by zero returns zero")
        void testMultiply_byZero() {
            assertEquals(0, utility.multiply(100, 0));
        }
    }

    @Nested
    @DisplayName("isPrime()")
    class IsPrimeTests {

        @Test
        @DisplayName("correctly identifies a prime number")
        void testIsPrime_withPrimeNumber() {
            assertTrue(utility.isPrime(7));
        }

        @Test
        @DisplayName("correctly identifies a non-prime number")
        void testIsPrime_withNonPrimeNumber() {
            assertFalse(utility.isPrime(8));
        }

        @Test
        @DisplayName("numbers less than 2 are never prime")
        void testIsPrime_withNumberLessThanTwo() {
            assertFalse(utility.isPrime(1));
            assertFalse(utility.isPrime(0));
            assertFalse(utility.isPrime(-5));
        }
    }

    @Nested
    @DisplayName("reverse()")
    class ReverseTests {

        @Test
        @DisplayName("reverses a normal String correctly")
        void testReverse_normalString() {
            assertEquals("olleh", utility.reverse("hello"));
        }

        @Test
        @DisplayName("throws NullPointerException for null input")
        void testReverse_nullInput_throwsException() {
            assertThrows(NullPointerException.class, () -> utility.reverse(null));
        }
    }

    @Nested
    @DisplayName("isNullOrEmpty()")
    class IsNullOrEmptyTests {

        @Test
        @DisplayName("returns true for null input")
        void testIsNullOrEmpty_withNull() {
            assertTrue(utility.isNullOrEmpty(null));
        }

        @Test
        @DisplayName("returns true for blank/whitespace-only input")
        void testIsNullOrEmpty_withBlankString() {
            assertTrue(utility.isNullOrEmpty("   "));
        }

        @Test
        @DisplayName("returns false for a non-empty String")
        void testIsNullOrEmpty_withNonEmptyString() {
            assertFalse(utility.isNullOrEmpty("Cognizant"));
        }
    }
}
