package com.cognizant.junit;

/**
 * EvenChecker
 * ------------------------------------------------------------------
 * Exercise 1 - Parameterized Tests.
 *
 * A simple utility class that determines whether a given integer
 * is even or odd. This class is intentionally kept simple so that
 * the focus of Exercise 1 remains on writing JUnit 5 parameterized
 * tests (using @ParameterizedTest and @ValueSource) rather than on
 * complex business logic.
 * ------------------------------------------------------------------
 */
public class EvenChecker {

    /**
     * Checks whether the supplied number is even.
     *
     * @param number the integer to check (may be positive, negative, or zero)
     * @return {@code true} if the number is even, {@code false} if it is odd
     */
    public boolean isEven(int number) {
        // The modulo operator works correctly for negative numbers in Java
        // because -2 % 2 == 0, so this also handles negative even numbers.
        return number % 2 == 0;
    }
}
