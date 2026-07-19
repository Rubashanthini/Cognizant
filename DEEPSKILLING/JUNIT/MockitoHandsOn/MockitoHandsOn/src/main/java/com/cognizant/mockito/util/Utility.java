package com.cognizant.mockito.util;

import com.cognizant.mockito.model.User;

/**
 * Utility is a small collection of static helper methods used across the
 * project. It has no dependency on any external system and therefore does
 * not need to be mocked - it is used here purely to support the service
 * layer with simple validation and formatting logic.
 */
public final class Utility {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Utility() {
        throw new AssertionError("Utility class - do not instantiate");
    }

    /**
     * Performs a very basic validation of an email address by checking
     * that it is not null/empty and contains an '@' symbol.
     *
     * @param email the email address to validate
     * @return true if the email looks valid, false otherwise
     */
    public static boolean isValidEmail(String email) {
        return email != null && !email.trim().isEmpty() && email.contains("@");
    }

    /**
     * Validates a {@link User} instance by checking that it is not null,
     * has a non-empty name, and has a valid email address.
     *
     * @param user the user to validate
     * @return true if the user is valid, false otherwise
     */
    public static boolean isValidUser(User user) {
        if (user == null) {
            return false;
        }
        boolean hasName = user.getName() != null && !user.getName().trim().isEmpty();
        return hasName && isValidEmail(user.getEmail());
    }

    /**
     * Formats a raw data string by trimming whitespace and converting it
     * to upper case. Used to demonstrate simple data-processing logic
     * that can be unit tested without any mocking.
     *
     * @param data the raw data string
     * @return the formatted (trimmed, upper-cased) string, or null if the
     *         input was null
     */
    public static String formatData(String data) {
        if (data == null) {
            return null;
        }
        return data.trim().toUpperCase();
    }
}
