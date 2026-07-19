package com.cognizant.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exercise 1 - Logging Error Messages and Warning Levels.
 * <p>
 * This class demonstrates the five standard SLF4J logging levels:
 * TRACE, DEBUG, INFO, WARN, and ERROR (in increasing order of severity).
 * </p>
 * <p>
 * SLF4J (Simple Logging Facade for Java) provides a simple abstraction
 * layer for various logging frameworks (in this project, Logback is
 * used as the underlying implementation). The actual level that is
 * printed at runtime is controlled by the {@code logback.xml}
 * configuration file found in {@code src/main/resources}.
 * </p>
 *
 * @author Cognizant Digital Nurture 5.0
 * @version 1.0
 */
public class LoggingExample {

    /**
     * Logger instance bound to this class. Using {@code getClass()} style
     * naming (via {@code LoggingExample.class}) ensures that log output
     * clearly identifies the class that produced the message.
     */
    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    /**
     * Application entry point that demonstrates all standard log levels.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {

        logger.trace("This is a TRACE message - very detailed diagnostic information");
        logger.debug("This is a DEBUG message - useful for debugging the application");
        logger.info("This is an INFO message - general informational message");
        logger.warn("This is a WARNING message - something unexpected happened, but the application can continue");
        logger.error("This is an ERROR message - a serious problem has occurred");

        // Demonstrate logging an exception along with an error message
        try {
            simulateFailure();
        } catch (ArithmeticException ex) {
            logger.error("An arithmetic exception occurred while simulating a failure", ex);
        }

        logger.info("LoggingExample execution completed successfully");
    }

    /**
     * Helper method that intentionally throws an {@link ArithmeticException}
     * to demonstrate how exceptions can be logged alongside an error message.
     */
    private static void simulateFailure() {
        int result = 10 / 0;
        logger.info("This line will never be reached: {}", result);
    }
}
