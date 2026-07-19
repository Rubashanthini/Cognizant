package com.cognizant.mockito;

import com.cognizant.mockito.repository.Repository;
import com.cognizant.mockito.service.Service;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Exercise 1 - Mocking Databases and Repositories.
 * <p>
 * This test class demonstrates how to mock a {@link Repository} using
 * Mockito, stub its methods to return predefined data, and verify both
 * the processed result and the interaction with the mock.
 * </p>
 *
 * @author Cognizant Digital Nurture 5.0
 */
public class ServiceTest {

    /**
     * Verifies that {@link Service#processData()} correctly processes data
     * returned by a mocked {@link Repository}, and that the repository's
     * {@code getData()} method was invoked exactly once.
     */
    @Test
    @DisplayName("Service should process mock repository data correctly")
    public void testServiceWithMockRepository() {
        // Arrange: create a mock repository and stub its behavior
        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getData()).thenReturn("Mock Data");

        Service service = new Service(mockRepository);

        // Act: invoke the method under test
        String result = service.processData();

        // Assert: verify the processed result
        assertEquals("Processed Mock Data", result);

        // Verify: confirm the repository interaction occurred exactly once
        verify(mockRepository, times(1)).getData();
    }
}
