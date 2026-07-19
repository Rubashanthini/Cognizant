package com.cognizant.auth.controller;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * REST controller exposing the authenticated user endpoint for the OAuth2 Login flow
 * (Exercise 1).
 *
 * <p>{@code GET /user} is protected by the OAuth2 Login {@code SecurityFilterChain}
 * declared in {@code SecurityConfig}. Spring Security resolves the currently
 * authenticated {@link OAuth2User} automatically via method parameter injection - the
 * modern replacement for injecting a raw {@code java.security.Principal} as shown in the
 * original exercise material, giving direct, type-safe access to the OIDC/OAuth2 user
 * attributes returned by the provider.</p>
 */
@RestController
public class UserController {

    /**
     * Returns the profile attributes of the currently authenticated OAuth2/OIDC user.
     *
     * @param principal the authenticated user, injected by Spring Security once the
     *                  OAuth2 Login flow has completed
     * @return a map of the user's attributes as returned by the identity provider
     *         (e.g. {@code sub}, {@code name}, {@code email})
     */
    @GetMapping("/user")
    public Map<String, Object> user(OAuth2User principal) {
        return principal.getAttributes();
    }
}
