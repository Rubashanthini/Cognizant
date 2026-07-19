package com.cognizant.greet.controller;

import com.cognizant.common.dto.GreetingDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller exposing a simple greeting endpoint.
 * <p>
 * Endpoint: {@code GET /greet}
 * </p>
 * <p>
 * Example: a request to {@code http://localhost:8082/greet}, or, via
 * the API Gateway, {@code http://localhost:9090/greet-service/greet}.
 * </p>
 */
@RestController
public class GreetController {

    /**
     * Returns a static "Hello World" greeting.
     *
     * @return a {@link GreetingDto} wrapping the greeting message
     */
    @GetMapping("/greet")
    public GreetingDto greet() {
        return new GreetingDto("Hello World");
    }
}
