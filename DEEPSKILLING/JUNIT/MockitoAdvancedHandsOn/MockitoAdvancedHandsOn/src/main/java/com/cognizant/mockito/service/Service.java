package com.cognizant.mockito.service;

import com.cognizant.mockito.repository.Repository;

/**
 * Service class that depends on a {@link Repository} to fetch raw data and
 * apply business processing logic to it.
 * <p>
 * This class is used in Exercise 1 (Mocking Databases and Repositories) and
 * Exercise 5 (Mocking Multiple Return Values). The {@link Repository}
 * dependency is injected via the constructor, which allows a mocked
 * repository to be substituted during unit testing.
 * </p>
 *
 * @author Cognizant Digital Nurture 5.0
 */
public class Service {

    /** The repository dependency used to fetch raw data. */
    private final Repository repository;

    /**
     * Constructs a new {@code Service} with the given {@link Repository}
     * dependency.
     *
     * @param repository the repository used to fetch data
     */
    public Service(Repository repository) {
        this.repository = repository;
    }

    /**
     * Fetches raw data from the repository and applies processing logic
     * by prefixing it with {@code "Processed "}.
     *
     * @return the processed data as a {@link String}
     */
    public String processData() {
        String rawData = repository.getData();
        return "Processed " + rawData;
    }
}
