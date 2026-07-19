package com.cognizant.junit;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * AllTests
 * ------------------------------------------------------------------
 * Exercise 2 - Test Suites and Categories.
 *
 * Aggregates every individual test class in the project into a
 * single JUnit 5 Suite using the {@code junit-platform-suite}
 * module's {@code @Suite} and {@code @SelectClasses} annotations.
 *
 * Running THIS class (e.g. via "mvn test -Dtest=AllTests", an IDE's
 * "Run as JUnit Test" action, or any JUnit-Platform-aware runner)
 * will execute every test method in every listed class in one go.
 *
 * NOTE: This class itself contains no @Test methods - it is purely
 * a container/aggregator, as is standard for JUnit 5 suites.
 * ------------------------------------------------------------------
 */
@Suite
@SuiteDisplayName("Cognizant Advanced JUnit Testing - Full Test Suite")
@SelectClasses({
        EvenCheckerTest.class,
        OrderedTests.class,
        ExceptionThrowerTest.class,
        PerformanceTesterTest.class,
        BasicUtilityTest.class
})
public class AllTests {
    // Intentionally empty - this class is a marker/aggregator for the
    // JUnit Platform Suite engine. Annotations above define its behavior.
}
