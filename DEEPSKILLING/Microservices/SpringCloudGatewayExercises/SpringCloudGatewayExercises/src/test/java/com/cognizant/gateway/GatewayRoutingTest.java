package com.cognizant.gateway;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.reactive.server.WebTestClient;

/**
 * Integration tests verifying that the API Gateway correctly routes and
 * filters incoming requests, as required by Exercise 1 (Edge Services -
 * Routing and Filtering).
 *
 * <p>These tests start the full application context on a random port and
 * exercise the gateway's routing rules and the {@code LoggingFilter} that
 * is applied globally to every route.</p>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Exercise 1: Gateway Routing and Filtering Tests")
class GatewayRoutingTest {

    @Autowired
    private WebTestClient webTestClient;

    /**
     * Verifies that a request to the {@code /example/status} path is
     * correctly routed by the {@code example_route} defined in
     * {@code application.yml}, and that the downstream handler responds
     * successfully.
     */
    @Test
    @DisplayName("Should route /example/status to the downstream service and return 200 OK")
    void shouldRouteExampleStatusSuccessfully() {
        webTestClient.get()
                .uri("/example/status")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.service").isEqualTo("example-service")
                .jsonPath("$.message").exists()
                .jsonPath("$.timestamp").exists();
    }

    /**
     * Verifies that a request to a path with no matching route definition
     * results in a 404 Not Found response, confirming that the gateway's
     * path predicates are being correctly evaluated.
     */
    @Test
    @DisplayName("Should return 404 for a path with no matching route")
    void shouldReturnNotFoundForUnmatchedRoute() {
        webTestClient.get()
                .uri("/no-such-route/here")
                .exchange()
                .expectStatus().isNotFound();
    }

    /**
     * Verifies that the request successfully passes through the global
     * LoggingFilter without altering the response - i.e. the filter is
     * transparent to the client while still performing its logging duties.
     */
    @Test
    @DisplayName("Should pass requests through the global LoggingFilter transparently")
    void shouldPassThroughLoggingFilterWithoutModifyingResponse() {
        webTestClient.get()
                .uri("/example/status")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().exists("Content-Type");
    }
}
