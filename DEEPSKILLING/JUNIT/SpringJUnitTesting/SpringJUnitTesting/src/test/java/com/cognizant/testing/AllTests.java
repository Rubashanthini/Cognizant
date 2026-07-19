package com.cognizant.testing;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * JUnit 5 test suite that aggregates every test class in the project so
 * they can all be executed together with a single run.
 * <p>
 * Requires the {@code junit-platform-suite} artifact on the test
 * classpath. If that artifact is not present, each test class can still
 * be run individually (or all together) via {@code mvn test}, which
 * discovers all {@code *Test.java} classes automatically regardless of
 * this suite class.
 */
@Suite
@SelectClasses({
        CalculatorServiceTest.class,
        UserServiceTest.class,
        UserControllerTest.class,
        IntegrationTest.class,
        CreateUserControllerTest.class,
        UserServiceExceptionTest.class,
        UserRepositoryTest.class,
        GlobalExceptionHandlerTest.class,
        ParameterizedCalculatorTest.class
})
public class AllTests {
    // No body needed; this class is a marker for the JUnit Platform Suite engine.
}
