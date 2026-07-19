package com.cognizant.mockito.service;

import com.cognizant.mockito.network.NetworkClient;

/**
 * NetworkService class that depends on a {@link NetworkClient} to establish
 * a network connection and apply business processing logic to the result.
 * <p>
 * This class is used in Exercise 4 (Mocking Network Interactions). The
 * {@link NetworkClient} dependency is injected via the constructor, which
 * allows a mocked network client to be substituted during unit testing so
 * that no real network connection is required.
 * </p>
 *
 * @author Cognizant Digital Nurture 5.0
 */
public class NetworkService {

    /** The network client dependency used to establish connections. */
    private final NetworkClient networkClient;

    /**
     * Constructs a new {@code NetworkService} with the given
     * {@link NetworkClient} dependency.
     *
     * @param networkClient the network client used to connect to the server
     */
    public NetworkService(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }

    /**
     * Establishes a connection using the {@link NetworkClient} and applies
     * processing logic by prefixing the result with
     * {@code "Connected to "}.
     *
     * @return the connection result as a {@link String}
     */
    public String connectToServer() {
        String connectionResult = networkClient.connect();
        return "Connected to " + connectionResult;
    }
}
