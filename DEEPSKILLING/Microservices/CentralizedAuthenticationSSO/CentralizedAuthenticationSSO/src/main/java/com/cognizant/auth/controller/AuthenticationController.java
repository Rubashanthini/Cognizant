package com.cognizant.auth.controller;

import com.cognizant.auth.dto.JwtResponse;
import com.cognizant.auth.dto.LoginRequest;
import com.cognizant.auth.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller exposing the login endpoint that issues self-contained JWT access
 * tokens (Exercise 3).
 *
 * <p>{@code POST /api/auth/login} is explicitly permitted by the {@code /api/**}
 * {@code SecurityFilterChain} declared in {@code SecurityConfig}, so it can be called
 * without an existing token.</p>
 */
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    /**
     * Creates the controller using constructor injection.
     *
     * @param authenticationService the service that validates credentials and issues tokens
     */
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Authenticates the supplied credentials and returns a signed JWT access token that
     * can subsequently be presented as {@code Authorization: Bearer <token>} to access
     * any endpoint under {@code /api/**}.
     *
     * @param loginRequest the username/password credentials to verify
     * @return HTTP 200 with a {@link JwtResponse} body on success
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authenticationService.authenticate(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }
}
