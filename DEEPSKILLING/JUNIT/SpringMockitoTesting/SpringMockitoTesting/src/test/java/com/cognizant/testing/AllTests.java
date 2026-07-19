package com.cognizant.testing;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Aggregated JUnit 5 test suite that runs all test classes for the
 * "Mocking Dependencies in Spring Tests using Mockito" module in a
 * single execution.
 *
 * <p>Includes:</p>
 * <ul>
 *   <li>{@link UserControllerTest} - Exercise 1 (Controller unit test with mocked service)</li>
 *   <li>{@link UserServiceTest} - Exercise 2 (Service unit test with mocked repository)</li>
 *   <li>{@link UserIntegrationTest} - Exercise 3 (Integration test with mocked service)</li>
 * </ul>
 *
 * <p>Run this suite using: {@code mvn test -Dtest=AllTests}</p>
 */
@Suite
@SelectClasses({
        UserControllerTest.class,
        UserServiceTest.class,
        UserIntegrationTest.class
})
public class AllTests {
    // This class intentionally has no body; it is a marker/aggregator
    // for the JUnit Platform Suite engine.
}
