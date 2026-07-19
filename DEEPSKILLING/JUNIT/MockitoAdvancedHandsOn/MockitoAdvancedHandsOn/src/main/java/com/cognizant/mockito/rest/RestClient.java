package com.cognizant.mockito.rest;

/**
 * RestClient interface representing a client used to communicate with
 * external RESTful APIs.
 * <p>
 * This interface is used in Exercise 2 (Mocking External REST APIs) to
 * demonstrate how Mockito can be used to simulate responses from external
 * web services without making actual HTTP calls during unit testing.
 * </p>
 *
 * @author Cognizant Digital Nurture 5.0
 */
public interface RestClient {

    /**
     * Sends a request to an external REST API and retrieves the response.
     *
     * @return a {@link String} representing the response received from the REST API
     */
    String getResponse();
}
