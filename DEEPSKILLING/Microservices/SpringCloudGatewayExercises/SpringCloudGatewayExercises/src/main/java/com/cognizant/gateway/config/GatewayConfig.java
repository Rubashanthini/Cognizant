package com.cognizant.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Central configuration class for Spring Cloud Gateway routes.
 *
 * <p>While the majority of the gateway routes for this project are declared
 * declaratively in {@code application.yml} (as required by Exercise 1 and
 * Exercise 2), this class demonstrates the equivalent <b>programmatic</b>
 * (Java DSL) style of route configuration using {@link RouteLocatorBuilder}.</p>
 *
 * <p>This class defines:</p>
 * <ul>
 *     <li>A simple routing rule for the {@code /example/**} path
 *     (Exercise 1 - Edge Services routing and filtering).</li>
 *     <li>A load-balanced routing rule for the {@code /loadbalanced/**} path
 *     that forwards requests to the {@code example-service} logical service
 *     name via the {@code lb://} URI scheme (Exercise 2 - Load Balancing).</li>
 * </ul>
 */
@Configuration
public class GatewayConfig {

    /**
     * Defines custom programmatic routes for the API Gateway.
     *
     * <p>This bean complements the YAML-based route configuration and shows
     * how routes, predicates, and filters can also be composed fluently in Java.</p>
     *
     * @param builder the {@link RouteLocatorBuilder} injected by Spring Cloud Gateway,
     *                used to fluently construct route definitions
     * @return a fully configured {@link RouteLocator} bean containing custom routes
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Exercise 1: Simple routing rule to a static/edge downstream URI
                .route("example_route_programmatic", r -> r
                        .path("/programmatic/example/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8080"))

                // Exercise 2: Load-balanced routing rule using the lb:// scheme
                .route("load_balanced_route_programmatic", r -> r
                        .path("/programmatic/loadbalanced/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://example-service"))
                .build();
    }
}
