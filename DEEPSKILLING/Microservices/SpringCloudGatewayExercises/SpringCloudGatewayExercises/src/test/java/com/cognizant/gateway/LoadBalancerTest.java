package com.cognizant.gateway;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.reactive.server.WebTestClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancer;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.DefaultRequest;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests verifying that client-side load balancing is correctly
 * configured, as required by Exercise 2 (Load Balancing).
 *
 * <p>These tests confirm both that the {@link ReactorLoadBalancer} bean
 * defined in {@code LoadBalancerConfiguration} is able to resolve a service
 * instance for the {@code example-service} logical name, and that the
 * gateway route using the {@code lb://} scheme successfully forwards
 * requests end-to-end.</p>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Exercise 2: Load Balancer Configuration Tests")
class LoadBalancerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private LoadBalancerClientFactory loadBalancerClientFactory;

    /**
     * Verifies that the {@code RandomLoadBalancer} bean is able to successfully
     * resolve a {@link ServiceInstance} for the {@code example-service} logical
     * service name, confirming the static discovery instances declared in
     * {@code application.yml} are being picked up correctly.
     */
    @Test
    @DisplayName("Should resolve a service instance for 'example-service' via RandomLoadBalancer")
    void shouldResolveServiceInstanceForExampleService() {
        ReactorServiceInstanceLoadBalancer loadBalancer =
                loadBalancerClientFactory.getInstance("example-service", ReactorServiceInstanceLoadBalancer.class);

        assertThat(loadBalancer).isNotNull();

        Request<Object> request = new DefaultRequest<>();

        StepVerifier.create(loadBalancer.choose(request))
                .assertNext(response -> {
                    assertThat(response.hasServer()).isTrue();
                    ServiceInstance instance = response.getServer();
                    assertThat(instance).isNotNull();
                    assertThat(instance.getServiceId()).isEqualToIgnoringCase("example-service");
                })
                .verifyComplete();
    }

    /**
     * Verifies that a request routed via the {@code lb://example-service}
     * gateway route successfully resolves to one of the configured instances
     * and returns a valid response, confirming end-to-end load-balanced routing.
     */
    @Test
    @DisplayName("Should route /loadbalanced/status through the load-balanced gateway route")
    void shouldRouteThroughLoadBalancedRoute() {
        webTestClient.get()
                .uri("/loadbalanced/status")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.service").isEqualTo("example-service")
                .jsonPath("$.simulatedInstanceId").exists();
    }

    /**
     * Repeats several calls to the load-balanced route to demonstrate that
     * requests are consistently and successfully distributed across the
     * configured instance pool without error.
     */
    @Test
    @DisplayName("Should consistently succeed across multiple load-balanced calls")
    void shouldSucceedAcrossMultipleLoadBalancedCalls() {
        for (int i = 0; i < 5; i++) {
            webTestClient.get()
                    .uri("/loadbalanced/status")
                    .exchange()
                    .expectStatus().isOk();
        }
    }
}
