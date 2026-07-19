package com.cognizant.auth.service;

import com.cognizant.auth.config.JwtConfig;
import com.cognizant.auth.config.JwtTokenProvider;
import com.cognizant.auth.dto.JwtResponse;
import com.cognizant.auth.dto.LoginRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * Application service responsible for validating user credentials and issuing
 * self-contained JWT access tokens (Exercise 3).
 *
 * <p>Delegates credential verification to the standard Spring Security
 * {@link AuthenticationManager} (backed by the {@code DaoAuthenticationProvider} declared
 * in {@code SecurityConfig}), then uses {@link JwtTokenProvider} to mint a signed token
 * for the authenticated principal.</p>
 */
@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final JwtConfig jwtConfig;

    /**
     * Creates the authentication service using constructor injection.
     *
     * @param authenticationManager the Spring Security manager used to verify credentials
     * @param jwtTokenProvider      the component used to issue signed JWTs
     * @param jwtConfig             supplies the configured token expiration window
     */
    public AuthenticationService(AuthenticationManager authenticationManager,
                                  JwtTokenProvider jwtTokenProvider,
                                  JwtConfig jwtConfig) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtConfig = jwtConfig;
    }

    /**
     * Authenticates the supplied credentials and, if valid, issues a JWT access token.
     *
     * @param loginRequest the username/password credentials to verify
     * @return a {@link JwtResponse} containing the newly issued Bearer token
     * @throws BadCredentialsException if the username or password is invalid
     */
    public JwtResponse authenticate(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        String token = jwtTokenProvider.createToken(authentication.getName());
        return JwtResponse.bearer(token, jwtConfig.getExpirationMillis());
    }
}
