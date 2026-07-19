package com.cognizant.mockito;

import com.cognizant.mockito.api.ExternalApi;
import com.cognizant.mockito.service.MyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Exercise 3: Argument Matching.
 *
 * Demonstrates the use of Mockito argument matchers:
 *  - any()       - matches any object, including null
 *  - anyString() - matches any non-null String
 *  - anyInt()    - matches any int
 *  - eq()        - matches a specific value exactly
 *  - argThat()   - matches based on a custom predicate/condition
 */
class ArgumentMatcherTest {

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
     * Demonstrates anyInt(): stubs getDataById() to return the same value
     * regardless of which int id is passed in.
     */
    @Test
    @DisplayName("getDataById() stubbed with anyInt() matches any id")
    void testAnyIntMatcher() {
        when(mockApi.getDataById(anyInt())).thenReturn("Data For Any Id");

        String result = service.fetchDataById(42);

        assertEquals("Data For Any Id", result);
        verify(mockApi).getDataById(anyInt());
    }

    /**
     * Demonstrates eq(): verifies that getDataById() was called with an
     * exact, specific id value.
     */
    @Test
    @DisplayName("getDataById() verified with eq() for an exact id")
    void testEqMatcher() {
        when(mockApi.getDataById(eq(7))).thenReturn("Data For Id 7");

        String result = service.fetchDataById(7);

        assertEquals("Data For Id 7", result);
        verify(mockApi).getDataById(eq(7));
    }

    /**
     * Demonstrates anyString() and any(): verifies that saveData() can be
     * matched using either matcher for a String argument.
     */
    @Test
    @DisplayName("saveData() verified with anyString() and any()")
    void testAnyStringAndAnyMatcher() {
        service.processAndSave("Sample Payload");

        // anyString() matches any non-null String argument
        verify(mockApi).saveData(anyString());
    }

    /**
     * Demonstrates any(): a more general matcher that matches any object
     * reference (including null), used here on the same String argument.
     */
    @Test
    @DisplayName("saveData() verified with the general any() matcher")
    void testAnyMatcher() {
        service.processAndSave("Another Payload");

        verify(mockApi).saveData(any(String.class));
        verify(mockApi).saveData(any());
    }

    /**
     * Demonstrates argThat(): uses a custom lambda predicate to match an
     * int argument that satisfies a specific business condition
     * (in this case, that the id is a positive number).
     */
    @Test
    @DisplayName("getDataById() verified with a custom argThat() condition")
    void testArgThatMatcher() {
        when(mockApi.getDataById(argThat(id -> id > 0))).thenReturn("Positive Id Data");

        String result = service.fetchDataById(15);

        assertEquals("Positive Id Data", result);
        verify(mockApi).getDataById(argThat(id -> id > 0));
    }
}
