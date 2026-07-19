package com.cognizant.mockito;

import com.cognizant.mockito.api.ExternalApi;
import com.cognizant.mockito.service.MyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Exercise 6: Verifying Interaction Order.
 *
 * Demonstrates the use of Mockito's InOrder API to verify that a series
 * of methods (connect(), getData(), disconnect()) were invoked on the
 * mock in the exact order expected.
 */
class InteractionOrderTest {

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
     * Verifies that performFullOperation() invokes connect(), getData(),
     * and disconnect() on the mock, in that exact order.
     */
    @Test
    @DisplayName("performFullOperation() should invoke connect -> getData -> disconnect in order")
    void testInteractionOrder() {
        // Arrange
        when(mockApi.getData()).thenReturn("Ordered Data");

        // Act
        service.performFullOperation();

        // Assert: create an InOrder verifier for the mock
        InOrder inOrder = inOrder(mockApi);

        // Verify each interaction occurred in the expected sequence
        inOrder.verify(mockApi).connect();
        inOrder.verify(mockApi).getData();
        inOrder.verify(mockApi).disconnect();
    }
}
