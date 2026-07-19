package com.cognizant.gateway;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.reactive.server.WebTestClient;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests verifying that the Resilience4j Circuit Breaker and Time
 * Limiter patterns are correctly configured and behave as expected, as
 * required by Exercise 3 (Resilience Patterns).
 *
 * <p>These tests confirm:</p>
 * <ul>
 *     <li>The {@code ReactiveResilience4JCircuitBreakerFactory} bean exists
 *     and is configured with the default customizer from
 *     {@code ResilienceConfiguration}.</li>
 *     <li>A downstream call that responds within the configured time limit
 *     completes normally.</li>
 *     <li>A downstream call that exceeds the configured time limit triggers
 *     the Time Limiter, causing the gateway to invoke the fallback route
 *     and return a graceful degraded response instead of an error.</li>
 * </ul>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Exercise 3: Resilience Patterns Tests")
class ResilienceTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ReactiveResilience4JCircuitBreakerFactory circuitBreakerFactory;

    /**
     * Verifies that the {@link ReactiveResilience4JCircuitBreakerFactory} bean
     * is present in the application context, confirming that
     * {@code ResilienceConfiguration} has been correctly wired.
     */
    @Test
    @DisplayName("Should have the Resilience4j Circuit Breaker factory bean configured")
    void shouldHaveCircuitBreakerFactoryConfigured() {
        assertThat(circuitBreakerFactory).isNotNull();
    }

    /**
     * Verifies that a fast downstream call (well within the configured
     * Time Limiter threshold) completes successfully without triggering
     * the fallback.
     */
    @Test
    @DisplayName("Should return the normal response for a fast downstream call")
    void shouldReturnNormalResponseForFastCall() {
        webTestClient.mutate()
                .responseTimeout(java.time.Duration.ofSeconds(10))
                .build()
                .get()
                .uri("/slow/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.service").isEqualTo("example-service")
                .jsonPath("$.status").doesNotExist();
    }

    /**
     * Verifies that a slow downstream call which exceeds the configured
     * Time Limiter timeout (4 seconds, as defined in {@code application.yml})
     * causes the Circuit Breaker gateway filter to invoke the fallback route,
     * resulting in a graceful {@code FALLBACK} response rather than a timeout error.
     */
    @Test
    @DisplayName("Should invoke fallback route when downstream call exceeds the time limit")
    void shouldInvokeFallbackWhenCallExceedsTimeLimit() {
        webTestClient.mutate()
                .responseTimeout(java.time.Duration.ofSeconds(15))
                .build()
                .get()
                .uri("/slow/6")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.status").isEqualTo("FALLBACK")
                .jsonPath("$.message").exists();
    }

    /**
     * Verifies that the fallback endpoint itself, when called directly,
     * returns the expected graceful degradation payload.
     */
    @Test
    @DisplayName("Should return graceful fallback payload when fallback endpoint is invoked directly")
    void shouldReturnFallbackPayloadDirectly() {
        webTestClient.get()
                .uri("/fallback/example")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.status").isEqualTo("FALLBACK")
                .jsonPath("$.service").isEqualTo("example-service");
    }
}
