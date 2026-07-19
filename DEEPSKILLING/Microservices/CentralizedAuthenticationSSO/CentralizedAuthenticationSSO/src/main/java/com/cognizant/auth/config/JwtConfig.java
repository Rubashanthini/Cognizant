package com.cognizant.auth.config;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

/**
 * Provides the cryptographic configuration used by {@link JwtTokenProvider} to issue and
 * validate self-contained JWT access tokens (Exercise 3).
 *
 * <p>The signing secret is externalised to {@code application.yml}
 * ({@code spring.security.jwt.secret}) and must be a Base64-encoded value that decodes to
 * at least 256 bits, satisfying the minimum key length required by the HMAC-SHA256
 * algorithm used for signing.</p>
 */
@Configuration
public class JwtConfig {

    private final String secret;

    private final long expirationMillis;

    /**
     * Creates the JWT configuration from externalised properties.
     *
     * @param secret           the Base64-encoded HMAC signing secret
     * @param expirationMillis the token validity duration, in milliseconds
     */
    public JwtConfig(@Value("${spring.security.jwt.secret}") String secret,
                      @Value("${spring.security.jwt.expiration-ms:3600000}") long expirationMillis) {
        this.secret = secret;
        this.expirationMillis = expirationMillis;
    }

    /**
     * Builds the {@link SecretKey} used for both signing new tokens and verifying
     * incoming ones. Using a single shared bean guarantees the encoder and decoder
     * are always symmetric.
     *
     * @return the HMAC-SHA signing key derived from the configured secret
     */
    @Bean
    public SecretKey jwtSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Returns the configured token validity window.
     *
     * @return token expiration, in milliseconds, applied from the moment of issuance
     */
    public long getExpirationMillis() {
        return expirationMillis;
    }
}
