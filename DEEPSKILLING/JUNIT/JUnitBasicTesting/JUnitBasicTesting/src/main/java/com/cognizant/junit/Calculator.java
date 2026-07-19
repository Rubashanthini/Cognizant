package com.cognizant.junit;

/**
 * Calculator.java
 *
 * A simple calculator utility class that performs basic arithmetic
 * operations: addition, subtraction, multiplication, and division.
 *
 * This class is used to demonstrate basic JUnit testing concepts
 * as part of the Cognizant Digital Nurture 5.0 JUnit training exercises.
 *
 * @author Cognizant Digital Nurture 5.0
 */
public class Calculator {

    /**
     * Adds two integers.
     *
     * @param a first operand
     * @param b second operand
     * @return the sum of a and b
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * Subtracts the second integer from the first.
     *
     * @param a first operand (minuend)
     * @param b second operand (subtrahend)
     * @return the result of (a - b)
     */
    public int subtract(int a, int b) {
        return a - b;
    }

    /**
     * Multiplies two integers.
     *
     * @param a first operand
     * @param b second operand
     * @return the product of a and b
     */
    public int multiply(int a, int b) {
        return a * b;
    }

    /**
     * Divides the first integer by the second.
     *
     * @param a the dividend
     * @param b the divisor
     * @return the quotient of a divided by b
     * @throws ArithmeticException if b is zero
     */
    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return a / b;
    }
}
