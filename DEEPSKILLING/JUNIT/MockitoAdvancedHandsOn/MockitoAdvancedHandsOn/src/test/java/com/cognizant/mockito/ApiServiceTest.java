package com.cognizant.mockito;

import com.cognizant.mockito.rest.RestClient;
import com.cognizant.mockito.service.ApiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Exercise 2 - Mocking External REST APIs.
 * <p>
 * This test class demonstrates how to mock a {@link RestClient} using
 * Mockito, stub its response methods to simulate an external API call,
 * and verify both the service output and the interaction with the mock.
 * </p>
 *
 * @author Cognizant Digital Nurture 5.0
 */
public class ApiServiceTest {

    /**
     * Verifies that {@link ApiService#fetchData()} correctly processes the
     * response returned by a mocked {@link RestClient}, and that the REST
     * client's {@code getResponse()} method was invoked exactly once.
     */
    @Test
    @DisplayName("ApiService should fetch and process mock REST response correctly")
    public void testServiceWithMockRestClient() {
        // Arrange: create a mock REST client and stub its behavior
        RestClient mockRestClient = mock(RestClient.class);
        when(mockRestClient.getResponse()).thenReturn("Mock Response");

        ApiService apiService = new ApiService(mockRestClient);

        // Act: invoke the method under test
        String result = apiService.fetchData();

        // Assert: verify the fetched/processed result
        assertEquals("Fetched Mock Response", result);

        // Verify: confirm the REST client interaction occurred exactly once
        verify(mockRestClient, times(1)).getResponse();
    }
}
