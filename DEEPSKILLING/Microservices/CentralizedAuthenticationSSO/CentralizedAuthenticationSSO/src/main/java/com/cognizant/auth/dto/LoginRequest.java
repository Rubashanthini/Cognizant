package com.cognizant.auth.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Request payload carrying username/password credentials submitted to
 * {@code POST /api/auth/login} in order to obtain a self-issued JWT (Exercise 3).
 *
 * <p>Implemented as an immutable record for conciseness; Bean Validation annotations
 * ensure both fields are supplied before the request reaches
 * {@code AuthenticationService}.</p>
 *
 * @param username the account username
 * @param password the account's plain-text password, verified against the stored hash
 */
public record LoginRequest(

        @NotBlank(message = "Username must not be blank")
        String username,

        @NotBlank(message = "Password must not be blank")
        String password
) {
}
