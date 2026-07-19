package com.cognizant.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * REST controller demonstrating both JWT-secured strategies covered by the exercises.
 *
 * <ul>
 *     <li>{@code GET /secure} - protected by the OAuth2 Resource Server chain
 *     (Exercise 2); the caller must present a valid Bearer token issued by the external
 *     Authorization Server configured via {@code issuer-uri}.</li>
 *     <li>{@code GET /api/protected} - protected by the self-contained JWT chain
 *     (Exercise 3); the caller must present a valid Bearer token issued by this
 *     application's own {@code /api/auth/login} endpoint.</li>
 * </ul>
 */
@RestController
public class SecureController {

    /**
     * Resource Server protected endpoint (Exercise 2).
     *
     * @param jwt the decoded, verified JWT injected by Spring Security's
     *            OAuth2 Resource Server support
     * @return a confirmation message together with the token's subject claim
     */
    @GetMapping("/secure")
    public Map<String, Object> secure(Jwt jwt) {
        return Map.of(
                "message", "This is a secure endpoint protected by the OAuth2 Resource Server",
                "subject", jwt.getSubject(),
                "issuer", String.valueOf(jwt.getIssuer())
        );
    }

    /**
     * Self-issued JWT protected endpoint (Exercise 3).
     *
     * @param authentication the authentication populated by
     *                       {@code JwtAuthenticationFilter} from a validated,
     *                       self-issued Bearer token
     * @return a confirmation message together with the authenticated username
     */
    @GetMapping("/api/protected")
    public Map<String, Object> protectedResource(Authentication authentication) {
        return Map.of(
                "message", "This endpoint is protected using self-issued Bearer JWT authentication",
                "username", authentication.getName()
        );
    }
}
