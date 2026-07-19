package com.cognizant.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Simple REST controller that plays the role of a downstream "backend"
 * microservice for the purposes of these exercises.
 *
 * <p>It exposes:</p>
 * <ul>
 *     <li>{@code /example/**} and {@code /loadbalanced/**} endpoints - simulate
 *     the downstream services referenced in Exercise 1 and Exercise 2 routes.</li>
 *     <li>{@code /slow/**} - an artificially delayed endpoint used to
 *     exercise the Time Limiter / Circuit Breaker configured in Exercise 3.</li>
 *     <li>{@code /fallback/**} - fallback endpoints invoked by the gateway's
 *     circuit breaker filter when a downstream call fails or times out.</li>
 * </ul>
 *
 * <p>Running the gateway and this controller in the same application keeps
 * the project fully self-contained and runnable with a single
 * {@code mvn spring-boot:run}, without requiring separate microservice
 * projects or a service registry to be started.</p>
 */
@RestController
public class TestController {

    /**
     * Basic health/echo endpoint used to verify the gateway is routing correctly.
     *
     * @return a simple confirmation message with a timestamp
     */
    @GetMapping("/example/status")
    public Mono<ResponseEntity<Map<String, Object>>> exampleStatus() {
        return Mono.just(ResponseEntity.ok(Map.of(
                "service", "example-service",
                "message", "Routing via Spring Cloud Gateway is working",
                "timestamp", Instant.now().toString()
        )));
    }

    /**
     * Endpoint used to demonstrate load-balanced routing. Returns a randomly
     * generated "instance id" on each call so that, when accessed through
     * multiple running instances behind the gateway's {@code lb://} route,
     * different responses can be observed to confirm load balancing is occurring.
     *
     * @return details identifying which simulated instance served the request
     */
    @GetMapping("/loadbalanced/status")
    public Mono<ResponseEntity<Map<String, Object>>> loadBalancedStatus() {
        int simulatedInstanceId = ThreadLocalRandom.current().nextInt(1, 4);
        return Mono.just(ResponseEntity.ok(Map.of(
                "service", "example-service",
                "simulatedInstanceId", simulatedInstanceId,
                "message", "Handled by load-balanced instance #" + simulatedInstanceId,
                "timestamp", Instant.now().toString()
        )));
    }

    /**
     * An intentionally slow endpoint used to trigger the Resilience4j Time Limiter
     * and, upon repeated failures, the Circuit Breaker configured in Exercise 3.
     *
     * @param delaySeconds number of seconds to artificially delay the response by
     * @return a delayed response, or an error if interrupted
     */
    @GetMapping("/slow/{delaySeconds}")
    public Mono<ResponseEntity<Map<String, Object>>> slowEndpoint(@PathVariable int delaySeconds) {
        return Mono.just(ResponseEntity.ok(Map.of(
                        "service", "example-service",
                        "message", "Responded after simulated delay",
                        "delaySeconds", delaySeconds,
                        "timestamp", Instant.now().toString()
                ))
                .delayElement(java.time.Duration.ofSeconds(delaySeconds)));
    }

    /**
     * Fallback endpoint invoked by the gateway's Circuit Breaker filter when the
     * downstream {@code example-service} route fails, times out, or the circuit
     * is open. Demonstrates graceful degradation as required by Exercise 3.
     *
     * @return a {@link ResponseEntity} with HTTP 200 and a fallback payload,
     *         so that clients receive a graceful degraded response instead of an error
     */
    @GetMapping("/fallback/example")
    public Mono<ResponseEntity<Map<String, Object>>> fallback() {
        return Mono.just(ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "service", "example-service",
                "status", "FALLBACK",
                "message", "The downstream service is currently unavailable. Please try again later.",
                "timestamp", Instant.now().toString()
        )));
    }
}
