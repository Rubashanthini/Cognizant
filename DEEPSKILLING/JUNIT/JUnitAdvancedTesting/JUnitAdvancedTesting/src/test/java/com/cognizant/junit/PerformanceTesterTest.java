package com.cognizant.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

/**
 * PerformanceTesterTest
 * ------------------------------------------------------------------
 * Exercise 5 - Timeout and Performance Testing.
 *
 * Demonstrates both of JUnit 5's timeout assertion styles:
 *
 *  1. {@code assertTimeout()}            - runs the supplied code on the
 *     SAME thread as the test and fails if it takes longer than the
 *     given Duration to complete (the code is allowed to finish even
 *     if it overruns, but the assertion still fails afterward).
 *
 *  2. {@code assertTimeoutPreemptively()} - runs the supplied code on a
 *     SEPARATE thread and aborts it immediately (preemptively) the
 *     moment the given Duration elapses.
 * ------------------------------------------------------------------
 */
@DisplayName("PerformanceTester - Timeout & Performance Testing")
class PerformanceTesterTest {

    private PerformanceTester performanceTester;

    @BeforeEach
    void setUp() {
        performanceTester = new PerformanceTester();
    }

    /**
     * performTask() simulates ~200ms of work; asserting a 1-second
     * timeout should comfortably pass using assertTimeout().
     */
    @Test
    @DisplayName("performTask() completes within 1 second (assertTimeout)")
    void testPerformTask_completesWithinTimeout() {
        String result = assertTimeout(Duration.ofSeconds(1), () -> performanceTester.performTask());
        assertEquals("Task completed successfully", result);
    }

    /**
     * performTask() simulates ~200ms of work; asserting a 1-second
     * timeout should comfortably pass using assertTimeoutPreemptively().
     */
    @Test
    @DisplayName("performTask() completes within 1 second (assertTimeoutPreemptively)")
    void testPerformTask_completesWithinPreemptiveTimeout() {
        String result = assertTimeoutPreemptively(Duration.ofSeconds(1), () -> performanceTester.performTask());
        assertEquals("Task completed successfully", result);
    }

    /**
     * Uses the overloaded performTask(long) to simulate a very fast
     * task (50ms) and confirms it finishes well inside a 500ms budget.
     */
    @Test
    @DisplayName("performTask(50ms) finishes within 500ms budget")
    void testPerformTask_withShortDelay_completesQuickly() {
        assertTimeout(Duration.ofMillis(500), () -> performanceTester.performTask(50));
    }

    /**
     * Demonstrates assertTimeoutPreemptively() aborting a task that
     * would otherwise run too long. The simulated task requests 2
     * seconds of "work", but the assertion is given only 300ms - so
     * assertTimeoutPreemptively() is expected to interrupt/abort it
     * and report a timeout failure. We assert that an AssertionError
     * is indeed raised, proving the preemptive abort behavior works.
     */
    @Test
    @DisplayName("assertTimeoutPreemptively() aborts a task that exceeds the timeout")
    void testPerformTask_exceedsPreemptiveTimeout_throwsAssertionError() {
        org.junit.jupiter.api.Assertions.assertThrows(AssertionError.class, () ->
                assertTimeoutPreemptively(Duration.ofMillis(300), () -> performanceTester.performTask(2000))
        );
    }
}
