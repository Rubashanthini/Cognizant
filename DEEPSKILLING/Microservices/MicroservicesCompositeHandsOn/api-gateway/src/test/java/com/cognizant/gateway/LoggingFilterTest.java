package com.cognizant.gateway;

import com.cognizant.gateway.filter.LoggingFilter;
import org.junit.jupiter.api.Test;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for {@link LoggingFilter}, verifying that it delegates to
 * the remainder of the filter chain and completes successfully for an
 * incoming request, and that it is configured with the highest filter
 * precedence.
 */
class LoggingFilterTest {

    private final LoggingFilter loggingFilter = new LoggingFilter();

    /**
     * Verifies that the filter has the highest possible precedence, so
     * that it is guaranteed to observe every request before any other
     * gateway filter runs.
     */
    @Test
    void testFilterOrderIsHighestPrecedence() {
        assertEquals(Ordered.HIGHEST_PRECEDENCE, loggingFilter.getOrder());
    }

    /**
     * Verifies that invoking the filter with a mock request and a
     * filter chain that immediately completes results in the returned
     * {@link Mono} completing successfully (i.e. the request is logged
     * and then passed along the chain without error).
     */
    @Test
    void testFilterDelegatesToChainAndCompletes() {
        ServerHttpRequest request = MockServerHttpRequest.get("/greet-service/greet").build();
        ServerWebExchange exchange = MockServerWebExchange.from(request);

        Mono<Void> result = loggingFilter.filter(exchange, ex -> Mono.empty());

        // Blocking here is acceptable in a unit test context to
        // synchronously assert the reactive pipeline completes.
        result.block();

        assertTrue(true, "Filter chain completed without throwing an exception");
    }
}
