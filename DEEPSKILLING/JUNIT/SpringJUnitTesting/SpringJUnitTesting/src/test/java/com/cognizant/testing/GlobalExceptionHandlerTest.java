package com.cognizant.testing;

import com.cognizant.testing.controller.UserController;
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

import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Exercise 8 - Test Controller Exception Handling.
 * <p>
 * Verifies that {@link GlobalExceptionHandler} correctly intercepts a
 * {@link NoSuchElementException} thrown from the controller/service layer
 * and translates it into an HTTP 404 response.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("GlobalExceptionHandler MockMvc Tests")
class GlobalExceptionHandlerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        UserController userController = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("GET /users/{id} should return HTTP 404 with 'User not found' when user is missing")
    void testGetUser_NotFound_Returns404() throws Exception {
        // Arrange
        when(userService.getUserByIdOrThrow(999L))
                .thenThrow(new NoSuchElementException("User not found with id: 999"));

        // Act & Assert
        mockMvc.perform(get("/users/{id}", 999L))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }
}
