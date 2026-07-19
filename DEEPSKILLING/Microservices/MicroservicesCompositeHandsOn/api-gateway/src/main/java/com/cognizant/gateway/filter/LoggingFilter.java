package com.cognizant.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Instant;

/**
 * Global gateway filter that logs every single incoming request that
 * passes through the API Gateway, along with basic timing and response
 * status information once the downstream call completes.
 * <p>
 * Because it implements {@link GlobalFilter}, this filter is
 * automatically applied to <strong>every</strong> route configured in
 * {@code application.yml}, without needing to be referenced explicitly
 * by each route definition.
 * </p>
 * <p>
 * Implementing {@link Ordered} lets us control precisely when, relative
 * to other filters, this logging logic runs; here it is configured to
 * run first ({@link Ordered#HIGHEST_PRECEDENCE}) so that the incoming
 * request is logged before any other filter has a chance to modify or
 * short-circuit it.
 * </p>
 */
@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);

    /**
     * {@inheritDoc}
     * <p>
     * Logs the HTTP method and path of every incoming request as soon as
     * it arrives, then, once the downstream response completes, logs the
     * resulting HTTP status code and the total elapsed time.
     * </p>
     *
     * @param exchange the current server exchange, giving access to the
     *                 incoming {@link ServerHttpRequest} and outgoing
     *                 {@link ServerHttpResponse}
     * @param chain    the gateway filter chain, used to delegate to the
     *                 next filter/route handler
     * @return a {@link Mono} signalling when request processing has
     *         completed
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        Instant startTime = Instant.now();

        LOGGER.info("Incoming request -> method: {}, path: {}, remoteAddress: {}",
                request.getMethod(),
                request.getURI().getPath(),
                request.getRemoteAddress());

        return chain.filter(exchange).doOnSuccess(unused -> logCompletion(exchange, startTime));
    }

    /**
     * Logs the outcome of a request once the gateway has finished
     * processing it, including the resulting HTTP status and the total
     * time taken.
     *
     * @param exchange  the current server exchange
     * @param startTime the instant at which the request was first received
     */
    private void logCompletion(ServerWebExchange exchange, Instant startTime) {
        ServerHttpResponse response = exchange.getResponse();
        HttpStatusCode statusCode = response.getStatusCode();
        long elapsedMillis = Instant.now().toEpochMilli() - startTime.toEpochMilli();

        LOGGER.info("Completed request -> path: {}, status: {}, durationMs: {}",
                exchange.getRequest().getURI().getPath(),
                statusCode,
                elapsedMillis);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@link Ordered#HIGHEST_PRECEDENCE}, ensuring this filter
     *         runs before all other gateway filters
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
