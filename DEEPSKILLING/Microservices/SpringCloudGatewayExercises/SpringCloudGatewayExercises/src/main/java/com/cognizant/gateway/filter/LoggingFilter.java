package com.cognizant.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Instant;

/**
 * Global filter that logs details of every incoming request that passes
 * through the API Gateway.
 *
 * <p>This satisfies <b>Exercise 1 - Edge Services (Routing and Filtering)</b>,
 * which requires implementing a custom filter that logs the URI of every
 * incoming request before it is routed to the downstream service.</p>
 *
 * <p>Because this class implements {@link GlobalFilter}, it is automatically
 * applied to <i>every</i> route configured in the gateway, regardless of
 * whether the route was declared in {@code application.yml} or programmatically
 * in {@link com.cognizant.gateway.config.GatewayConfig}.</p>
 *
 * <p>The filter also implements {@link Ordered} to explicitly control its
 * position in the filter chain, ensuring logging happens as early as possible.</p>
 */
@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    /** Logger instance used to record incoming request details. */
    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    /**
     * Intercepts every request routed through the gateway, logs its URI,
     * HTTP method, and timestamp, and then passes control to the next
     * filter in the chain.
     *
     * @param exchange the current server web exchange, containing the request and response
     * @param chain    the gateway filter chain used to delegate to the next filter
     * @return a {@link Mono} signaling when request processing has completed
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        logger.info("Incoming Request -> Method: [{}] URI: [{}] Timestamp: [{}]",
                request.getMethod(),
                request.getURI(),
                Instant.now());

        // Also print to standard output as required by the exercise specification
        System.out.println("Request: " + request.getURI());

        long startTime = System.currentTimeMillis();

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            long duration = System.currentTimeMillis() - startTime;
            logger.info("Completed Request -> URI: [{}] Status: [{}] Duration: [{} ms]",
                    request.getURI(),
                    exchange.getResponse().getStatusCode(),
                    duration);
        }));
    }

    /**
     * Determines the priority of this filter within the gateway filter chain.
     * A lower value gives the filter higher precedence.
     *
     * @return the order value for this filter (highest precedence)
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
