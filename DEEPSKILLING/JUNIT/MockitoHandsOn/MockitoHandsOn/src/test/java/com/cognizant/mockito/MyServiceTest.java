package com.cognizant.mockito;

import com.cognizant.mockito.api.ExternalApi;
import com.cognizant.mockito.service.MyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Exercise 1: Mocking and Stubbing.
 *
 * Demonstrates:
 *  - Creating a mock object for an external dependency using Mockito.mock().
 *  - Stubbing the mock's methods using when(...).thenReturn(...).
 *  - Verifying the result using assertEquals().
 */
class MyServiceTest {

    /** Mocked external dependency. */
    private ExternalApi mockApi;

    /** Service under test, wired with the mocked dependency. */
    private MyService service;

    /**
     * Creates a fresh mock and service instance before each test so that
     * tests remain independent of one another.
     */
    @BeforeEach
    void setUp() {
        mockApi = mock(ExternalApi.class);
        service = new MyService(mockApi);
    }

    /**
     * Verifies that MyService.fetchData() returns the stubbed value
     * configured on the mocked ExternalApi.
     */
    @Test
    @DisplayName("fetchData() should return the value stubbed on the mock")
    void testExternalApi() {
        // Arrange: stub the mock so that getData() returns a known value
        when(mockApi.getData()).thenReturn("Mock Data");

        // Act: invoke the method under test
        String result = service.fetchData();

        // Assert: verify the returned value matches the stubbed value
        assertEquals("Mock Data", result);
    }
}
