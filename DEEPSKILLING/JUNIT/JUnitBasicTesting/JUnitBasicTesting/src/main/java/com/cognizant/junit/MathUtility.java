package com.cognizant.junit;

/**
 * MathUtility.java
 *
 * A utility class providing additional mathematical operations used to
 * demonstrate various JUnit assertions (assertArrayEquals, assertSame,
 * assertNotSame, assertNotEquals, etc.) as part of the Cognizant Digital
 * Nurture 5.0 JUnit training exercises.
 *
 * @author Cognizant Digital Nurture 5.0
 */
public class MathUtility {

    /**
     * A constant reference used to demonstrate assertSame() / assertNotSame().
     * Since it is a shared static instance, two calls to getSharedInstance()
     * will return the exact same object reference.
     */
    private static final Object SHARED_INSTANCE = new Object();

    /**
     * Checks whether the given integer is an even number.
     *
     * @param number the number to check
     * @return true if the number is even, false otherwise
     */
    public boolean isEven(int number) {
        return number % 2 == 0;
    }

    /**
     * Checks whether the given integer is a prime number.
     *
     * @param number the number to check
     * @return true if the number is prime, false otherwise
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
     * Calculates the factorial of a given non-negative integer.
     *
     * @param n the number to calculate the factorial of
     * @return the factorial of n
     * @throws IllegalArgumentException if n is negative
     */
    public long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Returns an array containing the squares of each element
     * in the input array. Useful for demonstrating assertArrayEquals().
     *
     * @param numbers the input array
     * @return a new array with each element squared
     */
    public int[] squareArray(int[] numbers) {
        int[] result = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            result[i] = numbers[i] * numbers[i];
        }
        return result;
    }

    /**
     * Returns the greatest common divisor (GCD) of two integers
     * using the Euclidean algorithm.
     *
     * @param a first integer
     * @param b second integer
     * @return the GCD of a and b
     */
    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    /**
     * Returns a shared static Object instance.
     * Used to demonstrate assertSame(), since every call returns
     * a reference to the exact same object.
     *
     * @return the shared Object instance
     */
    public Object getSharedInstance() {
        return SHARED_INSTANCE;
    }

    /**
     * Returns a brand-new Object instance every time it is called.
     * Used to demonstrate assertNotSame(), since two calls will
     * never return the same object reference.
     *
     * @return a new Object instance
     */
    public Object getNewInstance() {
        return new Object();
    }
}
