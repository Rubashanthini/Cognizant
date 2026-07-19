package com.cognizant.mockito;

import com.cognizant.mockito.api.ExternalApi;
import com.cognizant.mockito.service.MyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Exercise 7: Handling Void Methods with Exceptions.
 *
 * Demonstrates:
 *  - Stubbing a void method to throw an exception using doThrow().
 *  - Validating that the exception is actually thrown using assertThrows().
 *  - Verifying that the mocked method was still invoked using verify().
 */
class VoidExceptionTest {

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
     * Stubs saveData(null) to throw an IllegalArgumentException, then
     * verifies that calling processAndSave(null) propagates that
     * exception, and that the mock was indeed invoked with the
     * expected argument.
     */
    @Test
    @DisplayName("processAndSave(null) should propagate IllegalArgumentException from saveData()")
    void testVoidMethodThrowsException() {
        // Arrange: stub saveData(null) to throw an exception
        doThrow(new IllegalArgumentException("Invalid data supplied"))
                .when(mockApi).saveData(null);

        // Act & Assert: verify the exception is thrown and capture it
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.processAndSave(null)
        );

        // Assert: confirm the exception message is as expected
        assertEquals("Invalid data supplied", exception.getMessage());

        // Assert: confirm the mock's saveData(null) was indeed invoked
        verify(mockApi).saveData(null);
    }
}
