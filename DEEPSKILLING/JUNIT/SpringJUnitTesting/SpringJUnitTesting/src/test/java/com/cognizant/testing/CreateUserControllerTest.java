package com.cognizant.testing;

import com.cognizant.testing.controller.UserController;
import com.cognizant.testing.entity.User;
import com.cognizant.testing.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Exercise 5 - Test Controller POST Endpoint.
 * <p>
 * Verifies that {@code POST /users} correctly delegates to
 * {@link UserService#saveUser(User)} and returns the saved user as JSON.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("UserController MockMvc Tests - POST /users")
class CreateUserControllerTest {

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        UserController userController = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    @DisplayName("POST /users should return HTTP 200 and the created user JSON")
    void testCreateUser_ReturnsSavedUser() throws Exception {
        // Arrange
        User requestUser = new User("Diana");
        User savedUser = new User(10L, "Diana");
        when(userService.saveUser(any(User.class))).thenReturn(savedUser);

        // Act & Assert
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.name").value("Diana"));
    }
}
