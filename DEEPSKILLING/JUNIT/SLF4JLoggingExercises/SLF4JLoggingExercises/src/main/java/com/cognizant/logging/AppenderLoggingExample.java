package com.cognizant.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exercise 3 - Using Different Appenders.
 * <p>
 * This class demonstrates logging output being routed simultaneously to
 * multiple destinations ("appenders") as configured in
 * {@code src/main/resources/logback.xml}:
 * <ul>
 *     <li>{@code ConsoleAppender} - prints log statements to the console (stdout)</li>
 *     <li>{@code FileAppender} - writes log statements to a file named {@code app.log}
 *         in the project's working directory</li>
 * </ul>
 * </p>
 * <p>
 * Because the root logger level in {@code logback.xml} is set to
 * {@code TRACE}, every level demonstrated below will be emitted to both
 * appenders.
 * </p>
 *
 * @author Cognizant Digital Nurture 5.0
 * @version 1.0
 */
public class AppenderLoggingExample {

    /** Logger instance bound to this class. */
    private static final Logger logger = LoggerFactory.getLogger(AppenderLoggingExample.class);

    /**
     * Application entry point that logs a message at every SLF4J level
     * (TRACE, DEBUG, INFO, WARN, ERROR). Because logback.xml wires both
     * the ConsoleAppender and FileAppender to the root logger, each
     * message below will appear on the console AND inside {@code app.log}.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {

        logger.trace("TRACE: Entering AppenderLoggingExample.main() - lowest level, most granular detail");
        logger.debug("DEBUG: Initializing application components");
        logger.info("INFO: Application started successfully and is ready to process requests");
        logger.warn("WARN: Configuration value 'timeout' not set explicitly, using default of 30 seconds");
        logger.error("ERROR: Failed to connect to secondary data source, falling back to primary");

        performBusinessOperation();

        logger.info("INFO: AppenderLoggingExample execution completed - check console output and app.log file");
    }

    /**
     * Simulates a small business operation, logging progress at various
     * levels to further illustrate multi-appender output.
     */
    private static void performBusinessOperation() {
        logger.debug("DEBUG: Starting business operation 'calculateMonthlyReport'");
        int recordsProcessed = 250;
        logger.info("INFO: Processed {} records successfully", recordsProcessed);

        int failedRecords = 3;
        if (failedRecords > 0) {
            logger.warn("WARN: {} record(s) failed validation and were skipped", failedRecords);
        }

        logger.trace("TRACE: Exiting performBusinessOperation()");
    }
}
