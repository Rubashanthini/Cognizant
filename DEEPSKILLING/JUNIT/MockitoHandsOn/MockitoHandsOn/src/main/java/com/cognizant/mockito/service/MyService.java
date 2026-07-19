package com.cognizant.mockito.service;

import com.cognizant.mockito.api.ExternalApi;

/**
 * MyService is the class under test throughout this hands-on exercise
 * project. It depends on {@link ExternalApi}, which is injected via the
 * constructor (constructor-based dependency injection). This design makes
 * MyService easy to unit test in isolation, because tests can pass in a
 * Mockito mock of ExternalApi instead of a real implementation.
 */
public class MyService {

    /** The external dependency that MyService delegates to. */
    private final ExternalApi externalApi;

    /**
     * Constructs a MyService instance with the given ExternalApi
     * dependency.
     *
     * @param externalApi the external API implementation (or mock) to use
     */
    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    /**
     * Fetches data by delegating to {@link ExternalApi#getData()}.
     * Demonstrates basic mocking and stubbing (Exercise 1) as well as
     * interaction verification (Exercise 2).
     *
     * @return the data returned by the external API
     */
    public String fetchData() {
        return externalApi.getData();
    }

    /**
     * Fetches data for a specific id by delegating to
     * {@link ExternalApi#getDataById(int)}. Used to demonstrate argument
     * matchers such as any(), anyInt(), eq(), and argThat() (Exercise 3).
     *
     * @param id the identifier to fetch data for
     * @return the data associated with the given id
     */
    public String fetchDataById(int id) {
        return externalApi.getDataById(id);
    }

    /**
     * Delegates to the void {@link ExternalApi#saveData(String)} method.
     * Used to demonstrate stubbing void methods with doNothing() and
     * doThrow(), together with verify() (Exercises 4 and 7).
     *
     * @param data the data to save via the external API
     */
    public void processAndSave(String data) {
        externalApi.saveData(data);
    }

    /**
     * Fetches the current status by delegating to
     * {@link ExternalApi#getStatus()}. Used to demonstrate stubbing
     * multiple consecutive return values (Exercise 5).
     *
     * @return the current status reported by the external API
     */
    public String fetchStatus() {
        return externalApi.getStatus();
    }

    /**
     * Performs a full operation cycle: connect, fetch data, then
     * disconnect. Used to demonstrate verifying the order of interactions
     * using Mockito's InOrder API (Exercise 6).
     *
     * @return the data fetched during the operation
     */
    public String performFullOperation() {
        externalApi.connect();
        String data = externalApi.getData();
        externalApi.disconnect();
        return data;
    }
}
