package com.cognizant.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configures this application as an OAuth2 Resource Server (Exercise 2).
 *
 * <p>Endpoints matched by this chain accept only requests carrying a valid JWT access
 * token issued by an external Authorization Server. The token is validated using a
 * {@code JwtDecoder} that Spring Boot auto-configures from the
 * {@code spring.security.oauth2.resourceserver.jwt.issuer-uri} property declared in
 * {@code application.yml}: the decoder fetches the issuer's public signing keys via
 * OpenID Connect discovery and verifies the token's signature, issuer and expiry.</p>
 *
 * <p>Implemented with a {@link SecurityFilterChain} bean rather than
 * {@code WebSecurityConfigurerAdapter}, in line with Spring Security 6 guidance.</p>
 */
@Configuration
public class ResourceServerConfig {

    /**
     * Filter chain #3 - OAuth2 Resource Server (Exercise 2).
     *
     * <p>Secures every request under {@code /secure/**}, requiring a valid Bearer JWT
     * whose signature and claims are verified against the configured issuer.</p>
     *
     * @param http the {@link HttpSecurity} builder scoped to this chain
     * @return the configured {@link SecurityFilterChain}
     * @throws Exception if the security configuration cannot be built
     */
    @Bean
    @Order(2)
    public SecurityFilterChain resourceServerFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/secure/**")
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(AbstractHttpConfigurer::withDefaults));

        return http.build();
    }
}
