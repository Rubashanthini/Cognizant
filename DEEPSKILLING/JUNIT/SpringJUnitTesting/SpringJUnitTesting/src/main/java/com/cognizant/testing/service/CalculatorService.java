package com.cognizant.testing.service;

import org.springframework.stereotype.Service;

/**
 * Simple calculator service used to demonstrate basic unit testing
 * concepts with JUnit 5 (Exercise 1) and parameterized testing
 * (Exercise 9).
 */
@Service
public class CalculatorService {

    /**
     * Adds two integers together.
     *
     * @param a the first operand
     * @param b the second operand
     * @return the sum of {@code a} and {@code b}
     */
    public int add(int a, int b) {
        return a + b;
    }
}
