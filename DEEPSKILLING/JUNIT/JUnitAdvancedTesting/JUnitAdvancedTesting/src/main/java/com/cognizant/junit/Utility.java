package com.cognizant.junit;

/**
 * Utility
 * ------------------------------------------------------------------
 * A small collection of general-purpose helper methods used to
 * demonstrate standard (non-parameterized) JUnit 5 test writing in
 * {@code BasicUtilityTest}. This class complements the five core
 * exercises by giving the test suite a few extra, simple assertions
 * to exercise (basic assertEquals/assertTrue/assertFalse/assertNull
 * style tests).
 * ------------------------------------------------------------------
 */
public class Utility {

    /**
     * Adds two integers.
     *
     * @param a first operand
     * @param b second operand
     * @return the sum of {@code a} and {@code b}
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * Multiplies two integers.
     *
     * @param a first operand
     * @param b second operand
     * @return the product of {@code a} and {@code b}
     */
    public int multiply(int a, int b) {
        return a * b;
    }

    /**
     * Checks whether a given integer is a prime number.
     *
     * @param number the number to check
     * @return {@code true} if {@code number} is prime, {@code false} otherwise
     */
    public boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Reverses a String.
     *
     * @param input the String to reverse (must not be {@code null})
     * @return the reversed String
     * @throws NullPointerException if {@code input} is {@code null}
     */
    public String reverse(String input) {
        if (input == null) {
            throw new NullPointerException("input must not be null");
        }
        return new StringBuilder(input).reverse().toString();
    }

    /**
     * Checks whether a String is {@code null} or empty (after trimming).
     *
     * @param input the String to check
     * @return {@code true} if {@code input} is {@code null} or blank
     */
    public boolean isNullOrEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }
}
