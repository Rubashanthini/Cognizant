package com.cognizant.mockito.service;

import com.cognizant.mockito.rest.RestClient;

/**
 * ApiService class that depends on a {@link RestClient} to fetch data from
 * an external RESTful API and apply business processing logic to it.
 * <p>
 * This class is used in Exercise 2 (Mocking External REST APIs). The
 * {@link RestClient} dependency is injected via the constructor, which
 * allows a mocked REST client to be substituted during unit testing so that
 * no real HTTP calls are made.
 * </p>
 *
 * @author Cognizant Digital Nurture 5.0
 */
public class ApiService {

    /** The REST client dependency used to communicate with the external API. */
    private final RestClient restClient;

    /**
     * Constructs a new {@code ApiService} with the given {@link RestClient}
     * dependency.
     *
     * @param restClient the REST client used to fetch data from the external API
     */
    public ApiService(RestClient restClient) {
        this.restClient = restClient;
    }

    /**
     * Fetches raw data from the REST client and applies processing logic
     * by prefixing it with {@code "Fetched "}.
     *
     * @return the fetched data as a {@link String}
     */
    public String fetchData() {
        String response = restClient.getResponse();
        return "Fetched " + response;
    }
}
