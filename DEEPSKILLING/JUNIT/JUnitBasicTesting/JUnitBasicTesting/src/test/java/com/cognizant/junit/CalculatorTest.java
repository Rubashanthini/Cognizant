package com.cognizant.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * CalculatorTest.java
 *
 * Exercise 2 - Writing Basic JUnit Tests.
 *
 * This test class contains basic JUnit test cases for every method
 * in the Calculator class (add, subtract, multiply, divide) and the
 * StringUtility class (reverse, isPalindrome).
 *
 * @author Cognizant Digital Nurture 5.0
 */
public class CalculatorTest {

    // Instances of the classes under test
    private Calculator calculator = new Calculator();
    private StringUtility stringUtility = new StringUtility();

    // ==================== Calculator.add() ====================

    @Test
    public void testAddPositiveNumbers() {
        int result = calculator.add(2, 3);
        assertEquals(5, result);
    }

    @Test
    public void testAddNegativeNumbers() {
        int result = calculator.add(-4, -6);
        assertEquals(-10, result);
    }

    @Test
    public void testAddPositiveAndNegativeNumber() {
        int result = calculator.add(10, -3);
        assertEquals(7, result);
    }

    // ==================== Calculator.subtract() ====================

    @Test
    public void testSubtractPositiveNumbers() {
        int result = calculator.subtract(10, 4);
        assertEquals(6, result);
    }

    @Test
    public void testSubtractResultingInNegative() {
        int result = calculator.subtract(3, 8);
        assertEquals(-5, result);
    }

    @Test
    public void testSubtractZero() {
        int result = calculator.subtract(15, 0);
        assertEquals(15, result);
    }

    // ==================== Calculator.multiply() ====================

    @Test
    public void testMultiplyPositiveNumbers() {
        int result = calculator.multiply(6, 7);
        assertEquals(42, result);
    }

    @Test
    public void testMultiplyByZero() {
        int result = calculator.multiply(9, 0);
        assertEquals(0, result);
    }

    @Test
    public void testMultiplyNegativeNumbers() {
        int result = calculator.multiply(-3, -4);
        assertEquals(12, result);
    }

    // ==================== Calculator.divide() ====================

    @Test
    public void testDivideEvenlyDivisibleNumbers() {
        int result = calculator.divide(20, 4);
        assertEquals(5, result);
    }

    @Test
    public void testDivideWithRemainder() {
        // Integer division truncates the decimal part
        int result = calculator.divide(7, 2);
        assertEquals(3, result);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZeroThrowsException() {
        // Division by zero must throw ArithmeticException
        calculator.divide(10, 0);
    }

    // ==================== StringUtility.reverse() ====================

    @Test
    public void testReverseRegularString() {
        String result = stringUtility.reverse("hello");
        assertEquals("olleh", result);
    }

    @Test
    public void testReverseEmptyString() {
        String result = stringUtility.reverse("");
        assertEquals("", result);
    }

    @Test
    public void testReverseSingleCharacter() {
        String result = stringUtility.reverse("a");
        assertEquals("a", result);
    }

    // ==================== StringUtility.isPalindrome() ====================

    @Test
    public void testIsPalindromeTrueCase() {
        boolean result = stringUtility.isPalindrome("madam");
        assertTrue(result);
    }

    @Test
    public void testIsPalindromeFalseCase() {
        boolean result = stringUtility.isPalindrome("hello");
        assertFalse(result);
    }

    @Test
    public void testIsPalindromeCaseInsensitive() {
        boolean result = stringUtility.isPalindrome("Racecar");
        assertTrue(result);
    }
}
