package com.cognizant.testing.service;

import com.cognizant.testing.entity.User;
import com.cognizant.testing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class containing business logic related to {@link User} entities.
 *
 * <p>This class delegates data access operations to {@link UserRepository}
 * and is itself mocked or has its dependencies mocked in various test
 * scenarios (controller unit tests, service unit tests, integration tests).</p>
 */
@Service
public class UserService {

    /**
     * Repository used to perform CRUD operations on User entities.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Default no-argument constructor.
     */
    public UserService() {
    }

    /**
     * Constructor for manual instantiation (useful for unit testing
     * with @InjectMocks or manual wiring).
     *
     * @param userRepository the repository to use for data access
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a User by its unique identifier.
     *
     * @param id the unique identifier of the user to retrieve
     * @return the {@link User} if found, otherwise {@code null}
     */
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Retrieves all users present in the system.
     *
     * @return a list of all {@link User} entities
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Creates (persists) a new User.
     *
     * @param user the user entity to save
     * @return the persisted {@link User} entity
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Deletes a User by its unique identifier.
     *
     * @param id the unique identifier of the user to delete
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
