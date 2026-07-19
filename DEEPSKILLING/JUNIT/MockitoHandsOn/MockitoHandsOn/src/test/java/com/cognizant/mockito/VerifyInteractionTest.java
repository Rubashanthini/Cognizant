package com.cognizant.mockito;

import com.cognizant.mockito.api.ExternalApi;
import com.cognizant.mockito.service.MyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Exercise 2: Verifying Interactions.
 *
 * Demonstrates:
 *  - verify() - checking that a specific method was called on the mock.
 *  - verify(mock, times(1)) - checking the exact number of invocations.
 *  - verifyNoMoreInteractions() - ensuring no other interactions occurred.
 */
class VerifyInteractionTest {

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
     * Verifies that fetchData() results in exactly one call to
     * ExternalApi.getData(), and that no other interactions took place
     * on the mock.
     */
    @Test
    @DisplayName("fetchData() should call getData() exactly once")
    void testVerifyInteraction() {
        // Act
        service.fetchData();

        // Assert: verify getData() was called (default = exactly once)
        verify(mockApi).getData();

        // Assert: verify getData() was called exactly one time explicitly
        verify(mockApi, times(1)).getData();

        // Assert: confirm no other methods on the mock were invoked
        verifyNoMoreInteractions(mockApi);
    }
}
