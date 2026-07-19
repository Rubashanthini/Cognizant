package com.cognizant.testing;

import com.cognizant.testing.controller.UserController;
import com.cognizant.testing.entity.User;
import com.cognizant.testing.exception.GlobalExceptionHandler;
import com.cognizant.testing.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Exercise 3 - Testing a REST Controller with MockMvc.
 * <p>
 * Builds a standalone MockMvc instance around {@link UserController} with a
 * mocked {@link UserService}, avoiding the overhead of loading the full
 * Spring application context.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("UserController MockMvc Tests - GET /users/{id}")
class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        UserController userController = new UserController(userService);
        // Register the GlobalExceptionHandler so 404 behavior can also be exercised here.
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("GET /users/{id} should return HTTP 200 and the user JSON when found")
    void testGetUser_ReturnsUser() throws Exception {
        // Arrange
        User user = new User(1L, "Alice");
        when(userService.getUserByIdOrThrow(1L)).thenReturn(user);

        // Act & Assert
        mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Alice"));
    }
}
