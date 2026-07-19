package com.cognizant.testing.service;

import com.cognizant.testing.entity.User;
import com.cognizant.testing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Service layer responsible for user-related business logic.
 * <p>
 * Delegates persistence operations to {@link UserRepository} and is the
 * primary target of mocking exercises (Exercise 2) and exception-handling
 * exercises (Exercise 6).
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Default no-args constructor used by Spring for field injection.
     */
    public UserService() {
    }

    /**
     * Constructor supporting explicit dependency injection, useful for
     * tests that construct the service manually with a mocked repository.
     *
     * @param userRepository the repository to use
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a user by id.
     *
     * @param id the id of the user to fetch
     * @return the matching {@link User}, or {@code null} if not found
     */
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Retrieves a user by id, throwing an exception if not found.
     * <p>
     * Used in Exercise 6 to demonstrate service-level exception handling,
     * and in Exercise 8 in combination with {@code GlobalExceptionHandler}
     * to return an HTTP 404 response from the controller layer.
     *
     * @param id the id of the user to fetch
     * @return the matching {@link User}
     * @throws NoSuchElementException if no user exists with the given id
     */
    public User getUserByIdOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    /**
     * Persists a new (or updated) user.
     *
     * @param user the user to save
     * @return the saved user, including any generated id
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Retrieves all users whose name matches the given value (Exercise 7).
     *
     * @param name the name to search for
     * @return list of matching users
     */
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }
}
