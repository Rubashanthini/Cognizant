package com.cognizant.greet.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test for {@link GreetController}, verifying the
 * {@code GET /greet} endpoint using Spring's MockMvc.
 * <p>
 * Eureka client auto-registration is disabled for this test via the
 * {@code eureka.client.enabled=false} property so that the test can run
 * in isolation without a running Eureka server.
 * </p>
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "eureka.client.enabled=false"
})
class GreetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Verifies that requesting the greet endpoint returns HTTP 200 and
     * the expected "Hello World" message.
     *
     * @throws Exception if the mock request fails unexpectedly
     */
    @Test
    void testGreetReturnsHelloWorld() throws Exception {
        mockMvc.perform(get("/greet"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello World"));
    }
}
