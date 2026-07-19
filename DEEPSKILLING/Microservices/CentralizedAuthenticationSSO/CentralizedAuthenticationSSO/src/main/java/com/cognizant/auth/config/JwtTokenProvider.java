package com.cognizant.auth.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

/**
 * Generates, validates and parses self-contained JWT access tokens (Exercise 3).
 *
 * <p>Implemented entirely with the modern, non-deprecated JJWT 0.12.x fluent API
 * ({@code Jwts.builder()...signWith(SecretKey)} and {@code Jwts.parser()...verifyWith(SecretKey)}),
 * replacing the deprecated {@code SignatureAlgorithm}-based calls used in older JJWT
 * releases.</p>
 */
@Component
public class JwtTokenProvider {

    private final SecretKey signingKey;

    private final long expirationMillis;

    /**
     * Creates the token provider.
     *
     * @param signingKey the shared HMAC key used to sign and verify tokens
     * @param jwtConfig  supplies the configured token expiration window
     */
    public JwtTokenProvider(SecretKey signingKey, JwtConfig jwtConfig) {
        this.signingKey = signingKey;
        this.expirationMillis = jwtConfig.getExpirationMillis();
    }

    /**
     * Issues a signed JWT for the given username.
     *
     * @param username the subject to embed in the token
     * @return a compact, signed JWT string
     */
    public String createToken(String username) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMillis);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(signingKey)
                .compact();
    }

    /**
     * Validates a token's signature and expiry.
     *
     * @param token the compact JWT string to validate
     * @return {@code true} if the token is well-formed, correctly signed and not expired;
     *         {@code false} otherwise
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(signingKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    /**
     * Extracts the username (subject claim) from a validated token.
     *
     * @param token the compact JWT string
     * @return the username embedded in the token's subject claim
     */
    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    /**
     * Builds a Spring Security {@link Authentication} object for a validated token, so it
     * can be placed into the {@code SecurityContext} by {@code JwtAuthenticationFilter}.
     *
     * @param token the compact, already-validated JWT string
     * @return an authenticated {@link UsernamePasswordAuthenticationToken} carrying a
     *         minimal {@link UserDetails} principal with role {@code ROLE_USER}
     */
    public Authentication getAuthentication(String token) {
        String username = getUsername(token);
        UserDetails principal = new User(username, "", List.of(new SimpleGrantedAuthority("ROLE_USER")));
        return new UsernamePasswordAuthenticationToken(principal, token, principal.getAuthorities());
    }
}
