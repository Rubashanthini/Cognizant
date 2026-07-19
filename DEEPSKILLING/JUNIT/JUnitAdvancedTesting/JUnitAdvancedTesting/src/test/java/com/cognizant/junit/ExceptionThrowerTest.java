package com.cognizant.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * ExceptionThrowerTest
 * ------------------------------------------------------------------
 * Exercise 4 - Exception Testing.
 *
 * Demonstrates JUnit 5's {@code assertThrows()} API to validate both
 * the TYPE of exception thrown and its exact MESSAGE.
 * ------------------------------------------------------------------
 */
@DisplayName("ExceptionThrower - Exception Testing")
class ExceptionThrowerTest {

    private ExceptionThrower exceptionThrower;

    @BeforeEach
    void setUp() {
        exceptionThrower = new ExceptionThrower();
    }

    /**
     * Validates that throwException() throws exactly an
     * IllegalArgumentException (exception TYPE check).
     */
    @Test
    @DisplayName("throwException() throws IllegalArgumentException")
    void testThrowException_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> exceptionThrower.throwException(),
                "Expected throwException() to throw IllegalArgumentException");
    }

    /**
     * Validates that throwException() throws an exception carrying the
     * EXACT expected message (exception MESSAGE check).
     */
    @Test
    @DisplayName("throwException() throws exception with expected message")
    void testThrowException_hasExpectedMessage() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> exceptionThrower.throwException()
        );
        assertEquals(ExceptionThrower.EXCEPTION_MESSAGE, thrown.getMessage(),
                "Exception message did not match the expected text");
    }

    /**
     * Validates the overloaded throwExceptionIfNegative() method throws
     * for a negative input, and that the message contains the offending value.
     */
    @Test
    @DisplayName("throwExceptionIfNegative() throws for negative input")
    void testThrowExceptionIfNegative_withNegativeValue_throws() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> exceptionThrower.throwExceptionIfNegative(-5)
        );
        assertEquals("Negative values are not allowed: -5", thrown.getMessage());
    }

    /**
     * Validates the overloaded throwExceptionIfNegative() method does NOT
     * throw for a valid (non-negative) input.
     */
    @Test
    @DisplayName("throwExceptionIfNegative() does not throw for non-negative input")
    void testThrowExceptionIfNegative_withPositiveValue_doesNotThrow() {
        assertDoesNotThrow(() -> exceptionThrower.throwExceptionIfNegative(10),
                "throwExceptionIfNegative() should not throw for a non-negative value");
    }
}
