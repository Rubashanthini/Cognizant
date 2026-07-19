package com.cognizant.testing;

import com.cognizant.testing.repository.UserRepository;
import com.cognizant.testing.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Exercise 6 - Test Service Exception Handling.
 * <p>
 * Verifies that {@link UserService#getUserByIdOrThrow(Long)} throws a
 * {@link NoSuchElementException} with the expected message when a user
 * cannot be found.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("UserService Exception Handling Tests")
class UserServiceExceptionTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        when(userRepository.findById(42L)).thenReturn(Optional.empty());
    }

    @Test
    @DisplayName("getUserByIdOrThrow() should throw NoSuchElementException when user is missing")
    void testGetUserByIdOrThrow_ThrowsWhenNotFound() {
        NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                () -> userService.getUserByIdOrThrow(42L),
                "Expected getUserByIdOrThrow() to throw NoSuchElementException"
        );

        assertEquals("User not found with id: 42", exception.getMessage());
    }
}
