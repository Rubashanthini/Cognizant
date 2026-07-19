package com.cognizant.testing.controller;

import com.cognizant.testing.entity.User;
import com.cognizant.testing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller exposing user-related endpoints.
 * <p>
 * Covers Exercise 3 (GET by id via MockMvc), Exercise 5 (POST endpoint),
 * and Exercise 8 (delegating "not found" errors to
 * {@code GlobalExceptionHandler} for a 404 response).
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Default no-args constructor used by Spring for field injection.
     */
    public UserController() {
    }

    /**
     * Constructor supporting explicit dependency injection, useful for
     * tests that construct the controller manually with a mocked service.
     *
     * @param userService the service to use
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves a single user by id.
     * <p>
     * If the user does not exist, {@link java.util.NoSuchElementException}
     * is thrown and translated to an HTTP 404 response by
     * {@code GlobalExceptionHandler}.
     *
     * @param id the id of the user to retrieve
     * @return HTTP 200 with the user in the response body
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserByIdOrThrow(id));
    }

    /**
     * Creates (saves) a new user.
     *
     * @param user the user to create, supplied as the JSON request body
     * @return HTTP 200 with the saved user (including generated id) in the response body
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }
}
