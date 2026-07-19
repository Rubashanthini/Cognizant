package com.cognizant.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * SetupTeardownTest.java
 *
 * Exercise 4 - Test Fixtures, Setup and Teardown Methods in JUnit.
 *
 * This test class demonstrates the use of @Before and @After annotations
 * to initialize test objects before each test method runs, and to release
 * resources after each test method completes.
 *
 * JUnit calls the @Before method before EVERY @Test method, and the
 * @After method after EVERY @Test method, ensuring each test runs with
 * a fresh, isolated set of test fixtures.
 *
 * @author Cognizant Digital Nurture 5.0
 */
public class SetupTeardownTest {

    // Test fixtures - shared objects used across multiple test methods
    private Calculator calculator;
    private StringUtility stringUtility;
    private MathUtility mathUtility;

    /**
     * Setup method that runs BEFORE each test method.
     * Initializes fresh instances of the classes under test so that
     * every test method starts with a clean, predictable state.
     */
    @Before
    public void setUp() {
        calculator = new Calculator();
        stringUtility = new StringUtility();
        mathUtility = new MathUtility();
        System.out.println("[@Before] Test fixtures initialized.");
    }

    /**
     * Teardown method that runs AFTER each test method.
     * Releases references to the test fixtures so they can be
     * garbage collected, simulating resource cleanup.
     */
    @After
    public void tearDown() {
        calculator = null;
        stringUtility = null;
        mathUtility = null;
        System.out.println("[@After] Test fixtures released.");
    }

    /**
     * Test 1: Verifies Calculator addition using the fixture
     * initialized in the @Before method.
     */
    @Test
    public void testCalculatorAdditionWithFixture() {
        // Arrange: firstNumber and secondNumber to be added
        int firstNumber = 12;
        int secondNumber = 8;

        // Act: perform addition using the shared fixture
        int result = calculator.add(firstNumber, secondNumber);

        // Assert: verify the expected sum
        assertEquals(20, result);
    }

    /**
     * Test 2: Verifies Calculator division using the fixture
     * initialized in the @Before method.
     */
    @Test
    public void testCalculatorDivisionWithFixture() {
        // Arrange: dividend and divisor
        int dividend = 45;
        int divisor = 9;

        // Act: perform division using the shared fixture
        int result = calculator.divide(dividend, divisor);

        // Assert: verify the expected quotient
        assertEquals(5, result);
    }

    /**
     * Test 3: Verifies StringUtility palindrome check using the fixture
     * initialized in the @Before method.
     */
    @Test
    public void testStringUtilityPalindromeWithFixture() {
        // Arrange: a known palindrome word
        String word = "civic";

        // Act: check whether the word is a palindrome
        boolean result = stringUtility.isPalindrome(word);

        // Assert: verify the word is a palindrome
        assertTrue(result);
    }

    /**
     * Test 4: Verifies StringUtility reversal using the fixture
     * initialized in the @Before method.
     */
    @Test
    public void testStringUtilityReverseWithFixture() {
        // Arrange: input word to reverse
        String word = "JUnit";

        // Act: reverse the word using the shared fixture
        String reversed = stringUtility.reverse(word);

        // Assert: verify the reversed result
        assertEquals("tinUJ", reversed);
    }

    /**
     * Test 5: Verifies MathUtility prime check using the fixture
     * initialized in the @Before method.
     */
    @Test
    public void testMathUtilityIsPrimeWithFixture() {
        // Arrange: a known prime number
        int number = 13;

        // Act: check whether the number is prime
        boolean result = mathUtility.isPrime(number);

        // Assert: verify the number is indeed prime
        assertTrue(result);
    }

    /**
     * Test 6: Verifies that fixtures are properly null after being
     * reset conceptually - demonstrates test isolation by re-checking
     * a fresh fixture state within a new test (fixtures are re-created
     * by @Before before this test runs too).
     */
    @Test
    public void testFixturesAreFreshForEachTest() {
        // Arrange & Act: fixtures were just re-initialized by @Before
        // Assert: confirm none of the fixtures are null at test start
        assertFalse(calculator == null);
        assertFalse(stringUtility == null);
        assertFalse(mathUtility == null);
    }
}
