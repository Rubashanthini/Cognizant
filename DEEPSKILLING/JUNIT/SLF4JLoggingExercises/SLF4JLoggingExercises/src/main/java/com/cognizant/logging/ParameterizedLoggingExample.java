package com.cognizant.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Exercise 2 - Parameterized Logging.
 * <p>
 * This class demonstrates SLF4J's parameterized logging feature, which
 * allows placeholders ({@code {}}) to be embedded in a log message and
 * substituted with argument values at runtime.
 * </p>
 * <p>
 * Parameterized logging is preferred over simple string concatenation
 * because:
 * <ul>
 *     <li>It avoids the cost of string concatenation when the log level
 *         is disabled (SLF4J only builds the final string if the message
 *         will actually be logged).</li>
 *     <li>It improves readability of the log statement.</li>
 * </ul>
 * </p>
 *
 * @author Cognizant Digital Nurture 5.0
 * @version 1.0
 */
public class ParameterizedLoggingExample {

    /** Logger instance bound to this class. */
    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLoggingExample.class);

    /**
     * Application entry point that demonstrates parameterized logging
     * with single, multiple, and varargs placeholders, using different
     * data types (String, int, double, boolean, List).
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {

        // ---- Single placeholder ----
        String username = "john.doe";
        logger.info("User {} has started the session", username);

        // ---- Two placeholders ----
        String location = "Chennai";
        logger.info("User {} logged in from {}", username, location);

        // ---- Multiple data types combined ----
        int loginAttempt = 1;
        double sessionDuration = 45.75;
        boolean isAdmin = false;
        logger.info("User {} logged in from {} (attempt #{}, admin={})",
                username, location, loginAttempt, isAdmin);

        // ---- WARN level with parameters ----
        int remainingAttempts = 2;
        logger.warn("User {} entered an incorrect password. {} attempt(s) remaining before lockout",
                username, remainingAttempts);

        // ---- ERROR level with parameters and an exception (3-arg form) ----
        try {
            validateSession(username, sessionDuration);
        } catch (IllegalStateException ex) {
            logger.error("Session validation failed for user {} after {} minutes", username, sessionDuration, ex);
        }

        // ---- DEBUG level with a collection as a parameter ----
        List<String> roles = Arrays.asList("USER", "REPORT_VIEWER");
        logger.debug("User {} has the following roles assigned: {}", username, roles);

        // ---- Varargs style logging with more than two placeholders ----
        String action = "FILE_UPLOAD";
        String fileName = "quarterly-report.pdf";
        long fileSizeKb = 2048L;
        logger.info("Action={} performed by user={} fileName={} sizeKb={}",
                action, username, fileName, fileSizeKb);

        logger.info("ParameterizedLoggingExample execution completed successfully");
    }

    /**
     * Helper method that simulates a session validation failure to
     * demonstrate combining parameterized messages with exception logging.
     *
     * @param username        the user whose session is being validated
     * @param sessionDuration duration, in minutes, that the session has been active
     */
    private static void validateSession(String username, double sessionDuration) {
        if (sessionDuration > 30.0) {
            throw new IllegalStateException("Session duration exceeded the allowed threshold for user: " + username);
        }
    }
}
