package com.cognizant.testing;

import com.cognizant.testing.entity.User;
import com.cognizant.testing.repository.UserRepository;
import com.cognizant.testing.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Exercise 2 - Mocking a Repository in a Service Test.
 * <p>
 * Uses Mockito to mock {@link UserRepository} so that {@link UserService}
 * can be tested in isolation, without a real database or Spring context.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("UserService Unit Tests with Mocked Repository")
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User sampleUser;

    @BeforeEach
    void setUp() {
        sampleUser = new User(1L, "Alice");
    }

    @Test
    @DisplayName("getUserById() should return the user when found in the repository")
    void testGetUserById_UserFound() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(sampleUser));

        // Act
        User result = userService.getUserById(1L);

        // Assert
        assertEquals("Alice", result.getName());
        assertEquals(1L, result.getId());

        // Verify repository interaction
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("getUserById() should return null when the user is not found")
    void testGetUserById_UserNotFound() {
        // Arrange
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        // Act
        User result = userService.getUserById(99L);

        // Assert
        assertNull(result, "Expected null when the user does not exist");

        // Verify repository interaction
        verify(userRepository, times(1)).findById(99L);
    }
}
