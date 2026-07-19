package com.cognizant.loan.controller;

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
 * Integration test for {@link LoanController}, verifying the
 * {@code GET /loans/{number}} endpoint using Spring's MockMvc.
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
class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Verifies that requesting a loan account number returns HTTP 200
     * and a JSON payload containing the same loan number, along with the
     * expected dummy type, loan amount, EMI and tenure values.
     *
     * @throws Exception if the mock request fails unexpectedly
     */
    @Test
    void testGetLoanReturnsExpectedPayload() throws Exception {
        mockMvc.perform(get("/loans/H00987987972342"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value("H00987987972342"))
                .andExpect(jsonPath("$.type").value("car"))
                .andExpect(jsonPath("$.loan").value(400000.0))
                .andExpect(jsonPath("$.emi").value(3258.0))
                .andExpect(jsonPath("$.tenure").value(18));
    }
}
