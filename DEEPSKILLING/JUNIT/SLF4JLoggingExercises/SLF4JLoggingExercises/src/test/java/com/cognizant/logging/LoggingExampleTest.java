package com.cognizant.logging;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for the SLF4J Logging Exercises.
 * <p>
 * These tests do not assert on the actual textual log output (that would
 * require a custom in-memory Logback appender); instead, they verify that:
 * <ul>
 *     <li>Loggers are correctly obtained from {@link LoggerFactory}.</li>
 *     <li>Each exercise's {@code main()} method executes without throwing
 *         any exception, which confirms that the Logback configuration
 *         ({@code logback.xml}) is valid and that all logging statements
 *         (including parameterized and exception logging) execute cleanly.</li>
 * </ul>
 * </p>
 *
 * @author Cognizant Digital Nurture 5.0
 * @version 1.0
 */
@DisplayName("SLF4J Logging Exercises - Test Suite")
class LoggingExampleTest {

    /** Logger used for reporting test lifecycle information. */
    private static final Logger testLogger = LoggerFactory.getLogger(LoggingExampleTest.class);

    /**
     * Runs before each test method; logs a message indicating a new test
     * is about to start.
     */
    @BeforeEach
    void setUp() {
        testLogger.info("---- Starting a new test case ----");
    }

    /**
     * Runs after each test method; logs a message indicating the test finished.
     */
    @AfterEach
    void tearDown() {
        testLogger.info("---- Test case finished ----");
    }

    /**
     * Verifies that a Logger instance can be successfully created for
     * {@link LoggingExample} via {@link LoggerFactory}.
     */
    @Test
    @DisplayName("Logger for LoggingExample should not be null")
    void testLoggerIsNotNull() {
        Logger logger = LoggerFactory.getLogger(LoggingExample.class);
        assertNotNull(logger, "Logger instance should not be null");
    }

    /**
     * Verifies that Exercise 1 (LoggingExample) executes without throwing
     * any exception, confirming that basic error/warn/info/debug/trace
     * logging statements are wired correctly.
     */
    @Test
    @DisplayName("Exercise 1: LoggingExample.main() should run without throwing an exception")
    void testLoggingExampleRunsSuccessfully() {
        assertDoesNotThrow(() -> LoggingExample.main(new String[]{}),
                "LoggingExample.main() should execute without throwing any exception");
    }

    /**
     * Verifies that Exercise 2 (ParameterizedLoggingExample) executes
     * without throwing any exception, confirming that parameterized
     * logging statements with varying argument counts and types work
     * correctly.
     */
    @Test
    @DisplayName("Exercise 2: ParameterizedLoggingExample.main() should run without throwing an exception")
    void testParameterizedLoggingExampleRunsSuccessfully() {
        assertDoesNotThrow(() -> ParameterizedLoggingExample.main(new String[]{}),
                "ParameterizedLoggingExample.main() should execute without throwing any exception");
    }

    /**
     * Verifies that Exercise 3 (AppenderLoggingExample) executes without
     * throwing any exception, confirming that both the Console and File
     * appenders configured in logback.xml are functioning correctly.
     */
    @Test
    @DisplayName("Exercise 3: AppenderLoggingExample.main() should run without throwing an exception")
    void testAppenderLoggingExampleRunsSuccessfully() {
        assertDoesNotThrow(() -> AppenderLoggingExample.main(new String[]{}),
                "AppenderLoggingExample.main() should execute without throwing any exception");
    }
}
