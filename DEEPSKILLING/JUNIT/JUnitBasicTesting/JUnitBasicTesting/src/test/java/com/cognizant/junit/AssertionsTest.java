package com.cognizant.junit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * AssertionsTest.java
 *
 * Exercise 3 - Assertions in JUnit.
 *
 * This test class demonstrates every major JUnit assertion method
 * with meaningful, self-explanatory test cases:
 *   - assertEquals()
 *   - assertTrue()
 *   - assertFalse()
 *   - assertNull()
 *   - assertNotNull()
 *   - assertSame()
 *   - assertNotSame()
 *   - assertArrayEquals()
 *   - assertNotEquals()
 *
 * @author Cognizant Digital Nurture 5.0
 */
public class AssertionsTest {

    private Calculator calculator = new Calculator();
    private MathUtility mathUtility = new MathUtility();

    /**
     * Demonstrates assertEquals(): verifies that the actual value
     * matches the expected value.
     */
    @Test
    public void testAssertEquals() {
        // 2 + 3 should equal 5
        assertEquals(5, 2 + 3);
        // Calculator.add() should also produce the same result
        assertEquals(5, calculator.add(2, 3));
    }

    /**
     * Demonstrates assertTrue(): verifies that a given condition is true.
     */
    @Test
    public void testAssertTrue() {
        // 5 is greater than 3, so this condition is true
        assertTrue(5 > 3);
        // 7 is a prime number
        assertTrue(mathUtility.isPrime(7));
    }

    /**
     * Demonstrates assertFalse(): verifies that a given condition is false.
     */
    @Test
    public void testAssertFalse() {
        // 5 is not less than 3, so this condition is false
        assertFalse(5 < 3);
        // 8 is not a prime number
        assertFalse(mathUtility.isPrime(8));
    }

    /**
     * Demonstrates assertNull(): verifies that an object reference is null.
     */
    @Test
    public void testAssertNull() {
        String nullString = null;
        assertNull(nullString);
    }

    /**
     * Demonstrates assertNotNull(): verifies that an object reference
     * is not null.
     */
    @Test
    public void testAssertNotNull() {
        Object newObject = new Object();
        assertNotNull(newObject);
        // The Calculator instance should also not be null
        assertNotNull(calculator);
    }

    /**
     * Demonstrates assertSame(): verifies that two references point
     * to the exact same object in memory.
     */
    @Test
    public void testAssertSame() {
        Object firstReference = mathUtility.getSharedInstance();
        Object secondReference = mathUtility.getSharedInstance();
        // Both references point to the same static shared instance
        assertSame(firstReference, secondReference);
    }

    /**
     * Demonstrates assertNotSame(): verifies that two references do NOT
     * point to the same object in memory, even if their contents are equal.
     */
    @Test
    public void testAssertNotSame() {
        Object firstInstance = mathUtility.getNewInstance();
        Object secondInstance = mathUtility.getNewInstance();
        // Each call to getNewInstance() creates a brand-new object
        assertNotSame(firstInstance, secondInstance);
    }

    /**
     * Demonstrates assertArrayEquals(): verifies that two arrays
     * contain the same elements in the same order.
     */
    @Test
    public void testAssertArrayEquals() {
        int[] input = {1, 2, 3, 4};
        int[] expected = {1, 4, 9, 16};
        int[] actual = mathUtility.squareArray(input);
        assertArrayEquals(expected, actual);
    }

    /**
     * Demonstrates assertNotEquals(): verifies that two values are
     * NOT equal to each other.
     */
    @Test
    public void testAssertNotEquals() {
        // The sum of 2 + 2 should not equal 5
        assertNotEquals(5, 2 + 2);
        // Multiplying 3 by 3 should not equal 10
        assertNotEquals(10, calculator.multiply(3, 3));
    }
}
