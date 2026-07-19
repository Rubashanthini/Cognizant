package com.cognizant.junit;

/**
 * PerformanceTester
 * ------------------------------------------------------------------
 * Exercise 5 - Timeout and Performance Testing.
 *
 * Simulates a long-running task so that JUnit 5's timeout assertions
 * ({@code assertTimeout()} and {@code assertTimeoutPreemptively()})
 * can be demonstrated in {@code PerformanceTesterTest}.
 * ------------------------------------------------------------------
 */
public class PerformanceTester {

    /**
     * Simulates a task that takes a short, bounded amount of time to
     * complete (well under one second), so that tests which assert a
     * generous timeout (e.g. 1-2 seconds) reliably pass.
     *
     * @return a completion message once the simulated work is done
     */
    public String performTask() {
        try {
            // Simulate work being done (e.g. I/O, computation) - 200ms.
            Thread.sleep(200);
        } catch (InterruptedException e) {
            // Restore the interrupt status and convert to an unchecked
            // exception so callers/tests are aware the task did not finish.
            Thread.currentThread().interrupt();
            throw new RuntimeException("Task was interrupted", e);
        }
        return "Task completed successfully";
    }

    /**
     * Simulates a long-running task whose duration can be controlled by
     * the caller. Useful for tests that need to prove a timeout
     * assertion correctly FAILS when a task takes too long, as well as
     * tests that prove it PASSES when the task is fast enough.
     *
     * @param delayInMillis how long (in milliseconds) the task should simulate work for
     * @return a completion message once the simulated work is done
     */
    public String performTask(long delayInMillis) {
        try {
            Thread.sleep(delayInMillis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Task was interrupted", e);
        }
        return "Task completed successfully after " + delayInMillis + "ms";
    }
}
