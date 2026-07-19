package com.cognizant.junit;

/**
 * StringUtility.java
 *
 * A utility class that provides common string manipulation operations
 * such as reversing a string and checking whether a string is a palindrome.
 *
 * This class is used to demonstrate basic JUnit testing concepts
 * as part of the Cognizant Digital Nurture 5.0 JUnit training exercises.
 *
 * @author Cognizant Digital Nurture 5.0
 */
public class StringUtility {

    /**
     * Reverses the given input string.
     *
     * @param input the string to reverse
     * @return the reversed string
     * @throws IllegalArgumentException if input is null
     */
    public String reverse(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }
        return new StringBuilder(input).reverse().toString();
    }

    /**
     * Checks whether the given input string is a palindrome.
     * A palindrome reads the same forward and backward.
     * The check is case-insensitive.
     *
     * @param input the string to check
     * @return true if the string is a palindrome, false otherwise
     * @throws IllegalArgumentException if input is null
     */
    public boolean isPalindrome(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }
        String normalized = input.toLowerCase();
        String reversed = new StringBuilder(normalized).reverse().toString();
        return normalized.equals(reversed);
    }
}
