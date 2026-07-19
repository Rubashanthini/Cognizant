package com.cognizant.mockito;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Test suite that aggregates all individual test classes for the
 * Mockito Advanced Hands-On exercises.
 * <p>
 * This suite allows all exercise tests to be executed together as a single
 * unit using the JUnit Platform Suite engine. Running this class will
 * trigger execution of:
 * </p>
 * <ul>
 *   <li>{@link ServiceTest} - Exercise 1: Mocking Databases and Repositories</li>
 *   <li>{@link ApiServiceTest} - Exercise 2: Mocking External REST APIs</li>
 *   <li>{@link FileServiceTest} - Exercise 3: Mocking File I/O</li>
 *   <li>{@link NetworkServiceTest} - Exercise 4: Mocking Network Interactions</li>
 *   <li>{@link MultiReturnServiceTest} - Exercise 5: Mocking Multiple Return Values</li>
 * </ul>
 * <p>
 * Note: Running {@code mvn test} will already execute all {@code *Test.java}
 * classes individually via Surefire; this suite class is provided as a
 * convenience for running them together from an IDE that supports the
 * JUnit Platform Suite engine.
 * </p>
 *
 * @author Cognizant Digital Nurture 5.0
 */
@Suite
@SelectClasses({
        ServiceTest.class,
        ApiServiceTest.class,
        FileServiceTest.class,
        NetworkServiceTest.class,
        MultiReturnServiceTest.class
})
public class AllTests {
    // This class intentionally left without a body.
    // It serves solely as a holder for the @Suite and @SelectClasses annotations.
}
