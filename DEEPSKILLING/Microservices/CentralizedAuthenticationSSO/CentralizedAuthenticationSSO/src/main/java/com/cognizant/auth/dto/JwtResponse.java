package com.cognizant.auth.dto;

/**
 * Response payload returned from {@code POST /api/auth/login} containing the issued
 * JWT access token (Exercise 3).
 *
 * @param accessToken the compact, signed JWT string
 * @param tokenType   the token scheme to present in the {@code Authorization} header,
 *                    always {@code "Bearer"}
 * @param expiresInMs the token's validity window, in milliseconds, from the moment of issuance
 */
public record JwtResponse(
        String accessToken,
        String tokenType,
        long expiresInMs
) {

    /**
     * Convenience factory that fixes the token type to {@code "Bearer"}.
     *
     * @param accessToken the compact, signed JWT string
     * @param expiresInMs the token's validity window, in milliseconds
     * @return a fully populated {@link JwtResponse}
     */
    public static JwtResponse bearer(String accessToken, long expiresInMs) {
        return new JwtResponse(accessToken, "Bearer", expiresInMs);
    }
}
