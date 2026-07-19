package com.cognizant.mockito.repository;

/**
 * Repository interface representing a data access layer that communicates
 * with a database or other persistent data store.
 * <p>
 * This interface is used in Exercise 1 (Mocking Databases and Repositories)
 * and Exercise 5 (Mocking Multiple Return Values) to demonstrate how Mockito
 * can be used to mock database interactions without requiring an actual
 * database connection during unit testing.
 * </p>
 *
 * @author Cognizant Digital Nurture 5.0
 */
public interface Repository {

    /**
     * Retrieves raw data from the underlying data store.
     *
     * @return a {@link String} representing the data fetched from the repository
     */
    String getData();
}
