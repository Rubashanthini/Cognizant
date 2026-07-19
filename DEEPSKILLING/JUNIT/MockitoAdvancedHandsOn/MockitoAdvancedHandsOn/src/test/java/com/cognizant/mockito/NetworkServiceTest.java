package com.cognizant.mockito;

import com.cognizant.mockito.network.NetworkClient;
import com.cognizant.mockito.service.NetworkService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Exercise 4 - Mocking Network Interactions.
 * <p>
 * This test class demonstrates how to mock a {@link NetworkClient} using
 * Mockito, stub its connection method to simulate a network interaction,
 * verify the returned result, and verify interaction order where
 * appropriate using {@link InOrder}.
 * </p>
 *
 * @author Cognizant Digital Nurture 5.0
 */
public class NetworkServiceTest {

    /**
     * Verifies that {@link NetworkService#connectToServer()} correctly
     * processes the connection result returned by a mocked
     * {@link NetworkClient}, and that the network client's {@code connect()}
     * method was invoked exactly once.
     */
    @Test
    @DisplayName("NetworkService should connect and process mock network result correctly")
    public void testServiceWithMockNetworkClient() {
        // Arrange: create a mock network client and stub its behavior
        NetworkClient mockNetworkClient = mock(NetworkClient.class);
        when(mockNetworkClient.connect()).thenReturn("Mock Connection");

        NetworkService networkService = new NetworkService(mockNetworkClient);

        // Act: invoke the method under test
        String result = networkService.connectToServer();

        // Assert: verify the returned result
        assertEquals("Connected to Mock Connection", result);

        // Verify: confirm the network client interaction occurred exactly once
        verify(mockNetworkClient, times(1)).connect();
    }

    /**
     * Verifies interaction order when {@link NetworkService#connectToServer()}
     * is invoked multiple times in sequence, demonstrating Mockito's
     * {@link InOrder} verification capability for network interactions.
     */
    @Test
    @DisplayName("NetworkService should invoke connect() in the expected order across multiple calls")
    public void testServiceInteractionOrder() {
        // Arrange: create a mock network client and stub consecutive behavior
        NetworkClient mockNetworkClient = mock(NetworkClient.class);
        when(mockNetworkClient.connect())
                .thenReturn("First Connection")
                .thenReturn("Second Connection");

        NetworkService networkService = new NetworkService(mockNetworkClient);

        // Act: invoke the method under test twice, in sequence
        String firstResult = networkService.connectToServer();
        String secondResult = networkService.connectToServer();

        // Assert: verify both results
        assertEquals("Connected to First Connection", firstResult);
        assertEquals("Connected to Second Connection", secondResult);

        // Verify: confirm the calls to connect() happened in the expected order
        InOrder inOrder = inOrder(mockNetworkClient);
        inOrder.verify(mockNetworkClient, times(2)).connect();
    }
}
