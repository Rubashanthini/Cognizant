package com.cognizant.gateway.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * Configuration class responsible for defining resilience patterns applied to
 * downstream service calls made through the API Gateway.
 *
 * <p>This satisfies <b>Exercise 3 - Resilience Patterns</b>, which requires:</p>
 * <ul>
 *     <li>Adding Resilience4j to the project.</li>
 *     <li>Configuring a Circuit Breaker to prevent cascading failures.</li>
 *     <li>Configuring a Time Limiter to bound how long a downstream call may take.</li>
 *     <li>Demonstrating fallback behavior when the circuit is open or a call times out.</li>
 * </ul>
 *
 * <p>The default configuration defined here applies to all circuit breakers
 * created via the {@link ReactiveResilience4JCircuitBreakerFactory} unless a
 * specific circuit breaker instance overrides it (see {@code application.yml}
 * for the {@code exampleCircuitBreaker} instance-specific overrides).</p>
 */
@Configuration
public class ResilienceConfiguration {

    /**
     * Provides the default Resilience4j configuration (Circuit Breaker + Time Limiter)
     * applied to every circuit breaker created by the
     * {@link ReactiveResilience4JCircuitBreakerFactory}.
     *
     * <p>The Circuit Breaker is configured with:</p>
     * <ul>
     *     <li>A sliding window of 10 calls used to calculate the failure rate.</li>
     *     <li>A failure rate threshold of 50% before the circuit opens.</li>
     *     <li>A wait duration of 5 seconds in the open state before transitioning
     *     to half-open.</li>
     * </ul>
     *
     * <p>The Time Limiter is configured with a 4-second timeout for downstream calls.</p>
     *
     * @return a {@link Customizer} that applies the default circuit breaker and
     *         time limiter configuration to the {@link ReactiveResilience4JCircuitBreakerFactory}
     */
    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.custom()
                        .slidingWindowSize(10)
                        .failureRateThreshold(50.0f)
                        .waitDurationInOpenState(Duration.ofSeconds(5))
                        .permittedNumberOfCallsInHalfOpenState(3)
                        .build())
                .timeLimiterConfig(TimeLimiterConfig.custom()
                        .timeoutDuration(Duration.ofSeconds(4))
                        .build())
                .build());
    }
}
