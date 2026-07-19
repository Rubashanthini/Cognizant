package com.cognizant.testing.controller;

import com.cognizant.testing.entity.User;
import com.cognizant.testing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller exposing endpoints for managing {@link User} resources.
 *
 * <p>Delegates all business logic to {@link UserService}. This controller
 * is the primary subject of the {@code @WebMvcTest} based controller unit
 * test and the {@code @SpringBootTest} based integration test.</p>
 */
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * Service used to handle business logic for User operations.
     */
    @Autowired
    private UserService userService;

    /**
     * Retrieves a User by its unique identifier.
     *
     * @param id the unique identifier of the user, taken from the path
     * @return {@link ResponseEntity} containing the {@link User} with HTTP 200 (OK)
     *         if found, or HTTP 404 (Not Found) if no user exists with the given id
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    /**
     * Retrieves all users in the system.
     *
     * @return {@link ResponseEntity} containing the list of all {@link User} entities
     *         with HTTP 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Creates a new User.
     *
     * @param user the user details supplied in the request body
     * @return {@link ResponseEntity} containing the created {@link User} entity
     *         with HTTP 201 (Created)
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    /**
     * Deletes a User by its unique identifier.
     *
     * @param id the unique identifier of the user to delete
     * @return {@link ResponseEntity} with HTTP 204 (No Content) indicating
     *         successful deletion
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
