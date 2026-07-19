package com.cognizant.mockito;

import com.cognizant.mockito.api.ExternalApi;
import com.cognizant.mockito.service.MyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Exercise 4: Handling Void Methods.
 *
 * Demonstrates:
 *  - Stubbing a void method explicitly using doNothing().when(mock)....
 *  - Verifying that the void method was invoked using verify().
 */
class VoidMethodTest {

    /** Mocked external dependency. */
    private ExternalApi mockApi;

    /** Service under test. */
    private MyService service;

    @BeforeEach
    void setUp() {
        mockApi = mock(ExternalApi.class);
        service = new MyService(mockApi);
    }

    /**
     * Verifies that calling processAndSave() results in a call to the
     * void saveData() method on the mock, after explicitly stubbing that
     * method to do nothing when invoked.
     */
    @Test
    @DisplayName("processAndSave() should invoke the void saveData() method")
    void testVoidMethodDoesNothing() {
        // Arrange: explicitly stub the void method to do nothing
        doNothing().when(mockApi).saveData(anyString());

        // Act
        service.processAndSave("Test Data");

        // Assert: verify the void method was called with the expected argument
        verify(mockApi).saveData("Test Data");
    }
}
