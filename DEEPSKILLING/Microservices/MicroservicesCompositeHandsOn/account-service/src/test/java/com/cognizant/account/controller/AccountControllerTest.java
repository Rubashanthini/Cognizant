package com.cognizant.account.controller;

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
 * Integration test for {@link AccountController}, verifying the
 * {@code GET /accounts/{number}} endpoint using Spring's MockMvc.
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
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Verifies that requesting an account number returns HTTP 200 and a
     * JSON payload containing the same account number, along with the
     * expected dummy type and balance values.
     *
     * @throws Exception if the mock request fails unexpectedly
     */
    @Test
    void testGetAccountReturnsExpectedPayload() throws Exception {
        mockMvc.perform(get("/accounts/00987987973432"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value("00987987973432"))
                .andExpect(jsonPath("$.type").value("savings"))
                .andExpect(jsonPath("$.balance").value(234343.0));
    }
}
