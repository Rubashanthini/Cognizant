package com.cognizant.auth.config;

import com.cognizant.auth.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Core Spring Security 6 configuration for the application.
 *
 * <p>This class replaces the deprecated {@code WebSecurityConfigurerAdapter} approach shown
 * in the original exercise material with the modern, component-based configuration style
 * introduced in Spring Security 5.7+/6.x, built around {@link SecurityFilterChain} beans,
 * {@link AuthenticationManager} and {@link PasswordEncoder}.</p>
 *
 * <p>Two independent filter chains are declared here:</p>
 * <ol>
 *     <li><b>OAuth2 Login chain</b> (Exercise 1) - secures {@code /user} and the standard
 *     OAuth2/OIDC redirect endpoints using {@code oauth2Login()}.</li>
 *     <li><b>Stateless JWT chain</b> (Exercise 3) - secures {@code /api/**} endpoints using
 *     the custom {@link JwtAuthenticationFilter}, which validates self-issued Bearer tokens
 *     produced by {@code AuthenticationService}.</li>
 * </ol>
 *
 * <p>The Resource Server chain for Exercise 2 is declared separately in
 * {@link ResourceServerConfig} to keep each exercise's concerns isolated.</p>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Creates the security configuration.
     *
     * @param jwtAuthenticationFilter the custom filter that authenticates self-issued
     *                                JWT Bearer tokens for {@code /api/**} requests
     */
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    /**
     * Filter chain #1 - OAuth2 Login / OpenID Connect (Exercise 1).
     *
     * <p>Handles the browser-based, redirect-driven login flow against the configured
     * OAuth2/OIDC provider (see {@code application.yml}). Any request under {@code /user}
     * requires an authenticated session; unauthenticated users are redirected to the
     * provider's login page.</p>
     *
     * @param http the {@link HttpSecurity} builder scoped to this chain
     * @return the configured {@link SecurityFilterChain}
     * @throws Exception if the security configuration cannot be built
     */
    @Bean
    @Order(1)
    public SecurityFilterChain oauth2LoginFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/user", "/login/**", "/oauth2/**")
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated())
                .oauth2Login(AbstractHttpConfigurer::withDefaults);

        return http.build();
    }

    /**
     * Filter chain #2 - Self-contained JWT authentication (Exercise 3).
     *
     * <p>Secures every endpoint under {@code /api/**} (except the public login endpoint)
     * using stateless Bearer token authentication. The custom
     * {@link JwtAuthenticationFilter} is registered ahead of
     * {@link UsernamePasswordAuthenticationFilter} to populate the
     * {@code SecurityContext} before Spring Security's default authentication
     * processing runs.</p>
     *
     * @param http the {@link HttpSecurity} builder scoped to this chain
     * @return the configured {@link SecurityFilterChain}
     * @throws Exception if the security configuration cannot be built
     */
    @Bean
    @Order(3)
    public SecurityFilterChain jwtFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**")
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/login").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * In-memory user store backing {@link AuthenticationService}'s username/password
     * validation for issuing JWTs in Exercise 3. In a production system this would be
     * replaced by a database-backed {@link UserDetailsService}.
     *
     * @param passwordEncoder the encoder used to hash the demo user's password
     * @return an {@link InMemoryUserDetailsManager} containing a single demo user
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails demoUser = User.withUsername("demo-user")
                .password(passwordEncoder.encode("Password@123"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(demoUser);
    }

    /**
     * Exposes the {@link AuthenticationManager} built by Spring Security so that
     * {@code AuthenticationService} can authenticate username/password credentials
     * without relying on the deprecated {@code AuthenticationManagerBuilder} pattern.
     *
     * @param authenticationConfiguration the auto-configured {@link AuthenticationConfiguration}
     * @return the shared {@link AuthenticationManager}
     * @throws Exception if the manager cannot be obtained
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Wires the {@link DaoAuthenticationProvider} explicitly using constructor-friendly
     * setters so credential validation uses the {@link PasswordEncoder} bean defined below.
     *
     * @param userDetailsService the user detail source
     * @param passwordEncoder    the password hashing strategy
     * @return a fully configured {@link DaoAuthenticationProvider}
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                              PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    /**
     * Password hashing strategy used across the application. BCrypt is the current
     * Spring Security recommended default.
     *
     * @return a {@link BCryptPasswordEncoder} instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
