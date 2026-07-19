package com.cognizant.testing;

import com.cognizant.testing.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Exercise 4 - Integration Test with Spring Boot.
 * <p>
 * Loads the full Spring application context (Controller -> Service ->
 * Repository -> H2 Database) and exercises the real end-to-end flow via
 * MockMvc, backed by data pre-loaded from {@code data.sql}.
 */
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Full Stack Integration Tests - Controller to H2 Database")
class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /users/{id} should return a user pre-loaded from data.sql")
    void testGetUser_FullStackFlow() throws Exception {
        // User with id=1 ("Alice") is inserted by data.sql on startup.
        mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Alice"));
    }

    @Test
    @DisplayName("POST /users then GET should persist and retrieve the same user end-to-end")
    void testCreateThenFetchUser_FullStackFlow() throws Exception {
        User newUser = new User("Charlie");

        String responseBody = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Charlie"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        User createdUser = objectMapper.readValue(responseBody, User.class);

        mockMvc.perform(get("/users/{id}", createdUser.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Charlie"));
    }
}
