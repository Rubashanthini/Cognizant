package com.cognizant.springlearn;

import com.cognizant.springlearn.controller.CountryController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test class using MockMVC (Exercises 8 and 9).
 *
 * @SpringBootTest boots the full application context.
 * @AutoConfigureMockMvc auto-configures a MockMvc instance for
 * performing simulated HTTP requests without starting a real server.
 */
@SpringBootTest
@AutoConfigureMockMvc
class SpringLearnApplicationTests {

    /** MockMvc instance used to perform simulated HTTP requests against controllers. */
    @Autowired
    private MockMvc mockMvc;

    /** Directly autowired controller, used to verify it was loaded into the context. */
    @Autowired
    private CountryController countryController;

    /**
     * Verifies that the Spring application context loads successfully.
     */
    @Test
    void contextLoads() {
        // If the context fails to start, this test fails automatically.
        assertNotNull(mockMvc);
    }

    /**
     * Verifies that CountryController was created and injected by Spring.
     */
    @Test
    void controllerLoaded() {
        assertNotNull(countryController);
    }

    /**
     * Exercise 8: Verifies GET /country returns HTTP 200 with the
     * correct India country JSON payload (code = IN, name = India).
     */
    @Test
    void testGetCountry() throws Exception {
        mockMvc.perform(get("/country"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").exists())
                .andExpect(jsonPath("$.code").value("IN"))
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.name").value("India"));
    }

    /**
     * Exercise 9: Verifies GET /countries/{code} with an invalid code
     * returns HTTP 404 with reason "Country not found".
     */
    @Test
    void testGetCountryException() throws Exception {
        mockMvc.perform(get("/countries/xyz"))
                .andExpect(status().isNotFound())
                .andExpect(status().reason("Country not found"));
    }
}
