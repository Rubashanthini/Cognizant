package com.cognizant.mockito.network;

/**
 * NetworkClient interface representing a component responsible for
 * establishing network connections to remote servers or resources.
 * <p>
 * This interface is used in Exercise 4 (Mocking Network Interactions) to
 * demonstrate how Mockito can be used to simulate network connectivity
 * without requiring an actual network connection during unit testing.
 * </p>
 *
 * @author Cognizant Digital Nurture 5.0
 */
public interface NetworkClient {

    /**
     * Establishes a connection to a remote network resource.
     *
     * @return a {@link String} representing the result of the connection attempt
     */
    String connect();
}
