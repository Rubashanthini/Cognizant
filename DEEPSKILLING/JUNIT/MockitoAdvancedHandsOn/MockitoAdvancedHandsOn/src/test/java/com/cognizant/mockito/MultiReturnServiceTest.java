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
 * Exercise 5 - Mocking Multiple Return Values.
 * <p>
 * This test class demonstrates how to stub a mocked {@link Repository} to
 * return different values on consecutive invocations using chained
 * {@code thenReturn()} calls, and verifies both returned values as well as
 * the total number of interactions using {@code times()}.
 * </p>
 *
 * @author Cognizant Digital Nurture 5.0
 */
public class MultiReturnServiceTest {

    /**
     * Verifies that {@link Service#processData()} returns different
     * processed results on consecutive calls when the underlying mocked
     * {@link Repository} is stubbed to return different values in sequence.
     * Also verifies that {@code getData()} was invoked exactly twice.
     */
    @Test
    @DisplayName("Service should return different processed results on consecutive calls")
    public void testServiceWithMultipleReturnValues() {
        // Arrange: create a mock repository and stub consecutive return values
        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getData())
                .thenReturn("First Mock Data")
                .thenReturn("Second Mock Data");

        Service service = new Service(mockRepository);

        // Act: invoke the method under test twice
        String firstResult = service.processData();
        String secondResult = service.processData();

        // Assert: verify both returned/processed values
        assertEquals("Processed First Mock Data", firstResult);
        assertEquals("Processed Second Mock Data", secondResult);

        // Verify: confirm the repository interaction occurred exactly twice
        verify(mockRepository, times(2)).getData();
    }
}
