package com.cognizant.testing;

import com.cognizant.testing.controller.UserController;
import com.cognizant.testing.entity.User;
import com.cognizant.testing.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Exercise 1: Mocking a Service Dependency in a Controller Test.
 *
 * <p>This test class uses {@code @WebMvcTest} to load only the web layer
 * ({@link UserController}) of the application context, without starting
 * the full Spring Boot application. The {@link UserService} dependency
 * is mocked using {@code @MockBean} so that the controller can be tested
 * in isolation.</p>
 */
@WebMvcTest(UserController.class)
@DisplayName("UserController Unit Tests - Exercise 1 (Mocking Service in Controller Test)")
class UserControllerTest {

    /**
     * MockMvc instance used to perform simulated HTTP requests against
     * the controller without starting a real HTTP server.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Mocked UserService bean injected into the Spring application context,
     * replacing the real UserService implementation for this test slice.
     */
    @MockBean
    private UserService userService;

    /**
     * Verifies that GET /users/{id} returns HTTP 200 (OK) along with the
     * correct JSON body when the requested user exists.
     *
     * @throws Exception if the mock request fails to execute
     */
    @Test
    @DisplayName("GET /users/{id} should return 200 OK with user JSON when user exists")
    void testGetUser_UserExists_ReturnsOkWithUserJson() throws Exception {
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

        // Verify Mockito interaction: getUserById(1L) was called exactly once
        verify(userService, times(1)).getUserById(1L);
    }

    /**
     * Verifies that GET /users/{id} returns HTTP 404 (Not Found) when the
     * requested user does not exist.
     *
     * @throws Exception if the mock request fails to execute
     */
    @Test
    @DisplayName("GET /users/{id} should return 404 Not Found when user does not exist")
    void testGetUser_UserDoesNotExist_ReturnsNotFound() throws Exception {
        // Arrange
        when(userService.getUserById(99L)).thenReturn(null);

        // Act & Assert
        mockMvc.perform(get("/users/{id}", 99L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        // Verify Mockito interaction
        verify(userService, times(1)).getUserById(99L);
    }

    /**
     * Verifies that the UserService mock is invoked exactly once per request
     * and that no unexpected interactions occur, using anyLong() matcher.
     *
     * @throws Exception if the mock request fails to execute
     */
    @Test
    @DisplayName("GET /users/{id} should invoke userService.getUserById exactly once")
    void testGetUser_VerifiesServiceInteraction() throws Exception {
        // Arrange
        User mockUser = new User(2L, "Jane Smith", "jane.smith@example.com");
        when(userService.getUserById(anyLong())).thenReturn(mockUser);

        // Act
        mockMvc.perform(get("/users/{id}", 2L))
                .andExpect(status().isOk());

        // Assert - verify interaction count and no more interactions
        verify(userService, times(1)).getUserById(2L);
    }
}
