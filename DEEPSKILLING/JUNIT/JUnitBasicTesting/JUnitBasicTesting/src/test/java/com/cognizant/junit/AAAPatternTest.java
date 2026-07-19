package com.cognizant.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * AAAPatternTest.java
 *
 * Exercise 4 - Arrange-Act-Assert (AAA) Pattern.
 *
 * This test class demonstrates how to organize JUnit test cases using
 * the Arrange-Act-Assert (AAA) pattern, where each test is clearly
 * divided into three sections:
 *   1. Arrange - set up the objects and data needed for the test
 *   2. Act     - execute the method or behavior under test
 *   3. Assert  - verify that the outcome matches expectations
 *
 * @author Cognizant Digital Nurture 5.0
 */
public class AAAPatternTest {

    @Test
    public void testAddition_usingAAAPattern() {
        // Arrange: set up the calculator and input values
        Calculator calculator = new Calculator();
        int firstNumber = 15;
        int secondNumber = 25;

        // Act: perform the addition operation
        int actualResult = calculator.add(firstNumber, secondNumber);

        // Assert: verify the result matches the expected value
        assertEquals(40, actualResult);
    }

    @Test
    public void testSubtraction_usingAAAPattern() {
        // Arrange: set up the calculator and input values
        Calculator calculator = new Calculator();
        int firstNumber = 50;
        int secondNumber = 20;

        // Act: perform the subtraction operation
        int actualResult = calculator.subtract(firstNumber, secondNumber);

        // Assert: verify the result matches the expected value
        assertEquals(30, actualResult);
    }

    @Test
    public void testMultiplication_usingAAAPattern() {
        // Arrange: set up the calculator and input values
        Calculator calculator = new Calculator();
        int firstNumber = 6;
        int secondNumber = 9;

        // Act: perform the multiplication operation
        int actualResult = calculator.multiply(firstNumber, secondNumber);

        // Assert: verify the result matches the expected value
        assertEquals(54, actualResult);
    }

    @Test
    public void testDivision_usingAAAPattern() {
        // Arrange: set up the calculator and input values
        Calculator calculator = new Calculator();
        int dividend = 100;
        int divisor = 5;

        // Act: perform the division operation
        int actualResult = calculator.divide(dividend, divisor);

        // Assert: verify the result matches the expected value
        assertEquals(20, actualResult);
    }

    @Test
    public void testPalindromeCheck_usingAAAPattern() {
        // Arrange: set up the StringUtility instance and input string
        StringUtility stringUtility = new StringUtility();
        String candidateWord = "level";

        // Act: check whether the string is a palindrome
        boolean isPalindromeResult = stringUtility.isPalindrome(candidateWord);

        // Assert: verify that the word is indeed a palindrome
        assertTrue(isPalindromeResult);
    }

    @Test
    public void testStringReversal_usingAAAPattern() {
        // Arrange: set up the StringUtility instance and input string
        StringUtility stringUtility = new StringUtility();
        String originalWord = "Cognizant";

        // Act: reverse the input string
        String reversedWord = stringUtility.reverse(originalWord);

        // Assert: verify the reversed string matches the expected value
        assertEquals("tnazingoC", reversedWord);
    }
}
