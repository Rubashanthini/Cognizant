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
 * Exercise 5: Mocking and Stubbing with Multiple Returns.
 *
 * Demonstrates stubbing a mock to return different values on consecutive
 * invocations using chained thenReturn() calls, and verifying every one
 * of those returned values.
 */
class MultipleReturnTest {

    /** Mocked external dependency. */
    private ExternalApi mockApi;

    /** Service under test. */
    private MyService service;

    @BeforeEach
    void setUp() {
        mockApi = mock(ExternalApi.class);
        service = new MyService(mockApi);
    }

    /**
     * Stubs getStatus() to return three different values on three
     * successive calls, then verifies each returned value in order.
     */
    @Test
    @DisplayName("fetchStatus() should return different values on consecutive calls")
    void testMultipleReturnValues() {
        // Arrange: stub three consecutive return values
        when(mockApi.getStatus())
                .thenReturn("Active")
                .thenReturn("Inactive")
                .thenReturn("Disconnected");

        // Act & Assert: first call returns "Active"
        assertEquals("Active", service.fetchStatus());

        // Act & Assert: second call returns "Inactive"
        assertEquals("Inactive", service.fetchStatus());

        // Act & Assert: third call returns "Disconnected"
        assertEquals("Disconnected", service.fetchStatus());

        // Act & Assert: Mockito repeats the last stubbed value for any
        // further calls beyond the ones explicitly configured
        assertEquals("Disconnected", service.fetchStatus());
    }
}
