package com.cognizant.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Centralized Authentication and SSO demo application.
 *
 * <p>This application showcases three complementary authentication strategies built
 * entirely on Spring Security 6 (no {@code WebSecurityConfigurerAdapter}):</p>
 * <ul>
 *     <li><b>Exercise 1</b> - Centralized authentication via OAuth2 Login / OpenID Connect,
 *     handled by {@link com.cognizant.auth.config.SecurityConfig}.</li>
 *     <li><b>Exercise 2</b> - An OAuth2 Resource Server that validates externally issued
 *     JWT access tokens against an Authorization Server, handled by
 *     {@link com.cognizant.auth.config.ResourceServerConfig}.</li>
 *     <li><b>Exercise 3</b> - A self-contained JWT authentication scheme where this
 *     application issues and validates its own tokens, handled by
 *     {@link com.cognizant.auth.config.JwtConfig},
 *     {@link com.cognizant.auth.config.JwtTokenProvider} and
 *     {@link com.cognizant.auth.filter.JwtAuthenticationFilter}.</li>
 * </ul>
 */
@SpringBootApplication
public class CentralizedAuthenticationApplication {

    /**
     * Bootstraps the Spring application context.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(CentralizedAuthenticationApplication.class, args);
    }
}
