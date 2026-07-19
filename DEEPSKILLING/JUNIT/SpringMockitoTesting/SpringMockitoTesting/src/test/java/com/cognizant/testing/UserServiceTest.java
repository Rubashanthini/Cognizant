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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Exercise 2: Mocking a Repository in a Service Test.
 *
 * <p>This test class uses pure Mockito (via {@code @ExtendWith(MockitoExtension.class)})
 * to mock {@link UserRepository} and inject it into {@link UserService} using
 * {@code @InjectMocks}. No Spring application context is loaded, making this
 * a fast, isolated unit test of the service layer's business logic.</p>
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("UserService Unit Tests - Exercise 2 (Mocking Repository in Service Test)")
class UserServiceTest {

    /**
     * Mocked repository dependency, standing in for the real
     * {@link UserRepository} JPA implementation.
     */
    @Mock
    private UserRepository userRepository;

    /**
     * The service under test, with the mocked {@link UserRepository}
     * automatically injected by Mockito.
     */
    @InjectMocks
    private UserService userService;

    /**
     * Sample user instance reused across multiple test methods.
     */
    private User sampleUser;

    /**
     * Initializes common test fixtures before each test method executes.
     */
    @BeforeEach
    void setUp() {
        sampleUser = new User(1L, "John Doe", "john.doe@example.com");
    }

    /**
     * Verifies that {@code getUserById()} returns the correct user when
     * the repository finds a matching entity.
     */
    @Test
    @DisplayName("getUserById() should return user when found in repository")
    void testGetUserById_UserExists_ReturnsUser() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(sampleUser));

        // Act
        User result = userService.getUserById(1L);

        // Assert
        assertNotNull(result, "Returned user should not be null");
        assertEquals(sampleUser.getId(), result.getId(), "User id should match");
        assertEquals(sampleUser.getName(), result.getName(), "User name should match");
        assertEquals(sampleUser.getEmail(), result.getEmail(), "User email should match");

        // Verify repository interaction
        verify(userRepository, times(1)).findById(1L);
    }

    /**
     * Verifies that {@code getUserById()} returns {@code null} when the
     * repository does not find a matching entity.
     */
    @Test
    @DisplayName("getUserById() should return null when user not found in repository")
    void testGetUserById_UserDoesNotExist_ReturnsNull() {
        // Arrange
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        // Act
        User result = userService.getUserById(99L);

        // Assert
        assertNull(result, "Returned user should be null when not found");

        // Verify repository interaction
        verify(userRepository, times(1)).findById(99L);
    }

    /**
     * Verifies that {@code getAllUsers()} returns the full list of users
     * provided by the repository.
     */
    @Test
    @DisplayName("getAllUsers() should return all users from repository")
    void testGetAllUsers_ReturnsUserList() {
        // Arrange
        User secondUser = new User(2L, "Jane Smith", "jane.smith@example.com");
        List<User> mockUsers = Arrays.asList(sampleUser, secondUser);
        when(userRepository.findAll()).thenReturn(mockUsers);

        // Act
        List<User> result = userService.getAllUsers();

        // Assert
        assertNotNull(result, "Returned list should not be null");
        assertEquals(2, result.size(), "Returned list should contain 2 users");
        assertEquals(mockUsers, result, "Returned list should match mocked list");

        // Verify repository interaction
        verify(userRepository, times(1)).findAll();
    }

    /**
     * Verifies that {@code createUser()} delegates correctly to the
     * repository's save method and returns the persisted entity.
     */
    @Test
    @DisplayName("createUser() should save and return the persisted user")
    void testCreateUser_SavesAndReturnsUser() {
        // Arrange
        when(userRepository.save(sampleUser)).thenReturn(sampleUser);

        // Act
        User result = userService.createUser(sampleUser);

        // Assert
        assertNotNull(result, "Returned user should not be null");
        assertEquals(sampleUser, result, "Returned user should match saved user");

        // Verify repository interaction
        verify(userRepository, times(1)).save(sampleUser);
    }

    /**
     * Verifies that {@code deleteUser()} delegates correctly to the
     * repository's deleteById method and that no unexpected
     * repository methods are invoked.
     */
    @Test
    @DisplayName("deleteUser() should invoke repository deleteById exactly once")
    void testDeleteUser_InvokesRepositoryDeleteById() {
        // Act
        userService.deleteUser(1L);

        // Assert - verify interaction
        verify(userRepository, times(1)).deleteById(1L);
        verify(userRepository, never()).findAll();
    }
}
