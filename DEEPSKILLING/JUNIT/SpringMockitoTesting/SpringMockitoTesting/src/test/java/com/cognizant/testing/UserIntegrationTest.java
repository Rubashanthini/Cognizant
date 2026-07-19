package com.cognizant.testing;

import com.cognizant.testing.entity.User;
import com.cognizant.testing.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Exercise 3: Mocking a Service Dependency in an Integration Test.
 *
 * <p>This test class loads the complete Spring Boot application context
 * using {@code @SpringBootTest} and configures MockMvc via
 * {@code @AutoConfigureMockMvc}, exercising the full request-processing
 * pipeline (dispatcher servlet, controller advice, message converters).
 * The {@link UserService} dependency is replaced with a Mockito mock via
 * {@code @MockBean}, allowing the controller-to-service integration to be
 * verified without touching the real database layer.</p>
 */
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("User Integration Tests - Exercise 3 (Mocking Service in Integration Test)")
class UserIntegrationTest {

    /**
     * MockMvc instance, auto-configured with the full Spring application
     * context, used to perform simulated HTTP requests end-to-end.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Mocked UserService bean that replaces the real bean in the full
     * Spring application context for this integration test.
     */
    @MockBean
    private UserService userService;

    /**
     * Verifies the complete controller flow for GET /users/{id}, ensuring
     * that a successful HTTP 200 response with the correct JSON body is
     * returned when the mocked service provides a user.
     *
     * @throws Exception if the mock request fails to execute
     */
    @Test
    @DisplayName("Integration: GET /users/{id} should return 200 OK with correct JSON")
    void testGetUser_IntegrationFlow_ReturnsOkWithUserJson() throws Exception {
        // Arrange
        User mockUser = new User(1L, "John Doe", "john.doe@example.com");
        when(userService.getUserById(1L)).thenReturn(mockUser);

        // Act & Assert
        mockMvc.perform(get("/users/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        // Verify Mockito interaction across the full application context
        verify(userService, times(1)).getUserById(1L);
    }

    /**
     * Verifies the complete controller flow for GET /users/{id} when the
     * requested user does not exist, ensuring an HTTP 404 response.
     *
     * @throws Exception if the mock request fails to execute
     */
    @Test
    @DisplayName("Integration: GET /users/{id} should return 404 Not Found when user is missing")
    void testGetUser_IntegrationFlow_ReturnsNotFound() throws Exception {
        // Arrange
        when(userService.getUserById(42L)).thenReturn(null);

        // Act & Assert
        mockMvc.perform(get("/users/{id}", 42L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        // Verify Mockito interaction
        verify(userService, times(1)).getUserById(42L);
    }

    /**
     * Verifies that the Spring application context loads successfully
     * with the mocked UserService bean in place.
     */
    @Test
    @DisplayName("Integration: Application context loads successfully with mocked UserService")
    void testApplicationContext_LoadsSuccessfully() {
        // The presence of an injected, non-null mockMvc and userService
        // (proxy) confirms that the context loaded correctly.
        org.junit.jupiter.api.Assertions.assertNotNull(mockMvc);
        org.junit.jupiter.api.Assertions.assertNotNull(userService);
    }
}
