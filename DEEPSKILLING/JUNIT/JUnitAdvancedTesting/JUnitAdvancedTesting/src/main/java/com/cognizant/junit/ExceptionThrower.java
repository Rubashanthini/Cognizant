package com.cognizant.junit;

/**
 * ExceptionThrower
 * ------------------------------------------------------------------
 * Exercise 4 - Exception Testing.
 *
 * A simple class whose sole purpose is to deliberately throw an
 * {@link IllegalArgumentException} so that JUnit 5's
 * {@code assertThrows()} API can be exercised in
 * {@code ExceptionThrowerTest}.
 * ------------------------------------------------------------------
 */
public class ExceptionThrower {

    /**
     * The exact exception message that callers/tests can assert against.
     * Kept as a public constant so the test class does not need to
     * duplicate/hard-code the literal string in multiple places.
     */
    public static final String EXCEPTION_MESSAGE = "Invalid argument supplied to throwException()";

    /**
     * Always throws an {@link IllegalArgumentException} with a fixed message.
     * Used to demonstrate/verify exception testing with JUnit 5.
     *
     * @throws IllegalArgumentException always
     */
    public void throwException() {
        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
    }

    /**
     * Overloaded variant that throws the exception only when the supplied
     * value is negative. This gives the test class an additional,
     * slightly more realistic scenario to validate.
     *
     * @param value an integer input
     * @throws IllegalArgumentException if {@code value} is negative
     */
    public void throwExceptionIfNegative(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Negative values are not allowed: " + value);
        }
    }
}
