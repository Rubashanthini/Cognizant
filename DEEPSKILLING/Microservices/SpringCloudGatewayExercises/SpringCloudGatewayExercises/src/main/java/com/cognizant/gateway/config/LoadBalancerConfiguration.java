package com.cognizant.gateway.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Configuration class responsible for defining the client-side load balancing
 * strategy used by Spring Cloud Gateway.
 *
 * <p>This satisfies <b>Exercise 2 - Load Balancing</b>, which requires:</p>
 * <ul>
 *     <li>Adding Spring Cloud LoadBalancer to the project.</li>
 *     <li>Configuring routes using the {@code lb://service-name} URI scheme.</li>
 *     <li>Implementing a {@link RandomLoadBalancer} strategy that randomly
 *     selects one instance from the available pool of service instances
 *     registered under a logical service name.</li>
 * </ul>
 *
 * <p>The {@code example-service} logical name used here is resolved against the
 * list of instances configured in {@code application.yml} under
 * {@code spring.cloud.discovery.client.simple.instances.example-service}, which
 * simulates multiple running instances of a downstream microservice for the
 * purpose of demonstrating load-balanced routing without requiring a full
 * service registry such as Eureka.</p>
 */
@Configuration
public class LoadBalancerConfiguration {

    /**
     * Defines a {@link RandomLoadBalancer} bean that randomly selects a service
     * instance from the list of instances available for a given service name.
     *
     * <p>Spring Cloud LoadBalancer will automatically use this bean whenever a
     * gateway route is configured with a {@code lb://} URI, replacing the
     * default round-robin strategy with random selection as required by
     * Exercise 2.</p>
     *
     * @param environment              the Spring {@link Environment}, used to resolve
     *                                 the logical service name this load balancer applies to
     * @param loadBalancerClientFactory factory used to lazily obtain the
     *                                 {@link ServiceInstanceListSupplier} for the service
     * @return a {@link ReactorLoadBalancer} that performs random instance selection
     */
    @Bean
    public ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(
            Environment environment,
            LoadBalancerClientFactory loadBalancerClientFactory) {

        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);

        return new RandomLoadBalancer(
                loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class),
                name);
    }
}
