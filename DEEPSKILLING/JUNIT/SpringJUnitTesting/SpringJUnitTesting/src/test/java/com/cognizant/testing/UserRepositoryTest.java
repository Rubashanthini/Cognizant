package com.cognizant.testing;

import com.cognizant.testing.entity.User;
import com.cognizant.testing.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Exercise 7 - Test Custom Repository Query.
 * <p>
 * Uses {@code @DataJpaTest} to spin up a lightweight, transactional JPA
 * slice backed by an embedded H2 database, verifying
 * {@link UserRepository#findByName(String)}.
 * <p>
 * SQL init (schema.sql / data.sql) is disabled here and replaced with
 * Hibernate's {@code create-drop} DDL mode so that this slice test is
 * fully self-contained and does not collide with data seeded elsewhere.
 * Test data is inserted explicitly via {@link TestEntityManager}.
 */
@DataJpaTest
@TestPropertySource(properties = {
        "spring.sql.init.mode=never",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@DisplayName("UserRepository Custom Query Tests")
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("findByName() should return all users matching the given name")
    void testFindByName_ReturnsMatchingUsers() {
        // Arrange: insert sample data directly via the entity manager
        entityManager.persist(new User("Alice"));
        entityManager.persist(new User("Bob"));
        entityManager.persist(new User("Alice"));
        entityManager.flush();

        // Act
        List<User> results = userRepository.findByName("Alice");

        // Assert
        assertEquals(2, results.size(), "Expected exactly 2 users named 'Alice'");
        assertTrue(results.stream().allMatch(u -> u.getName().equals("Alice")));
    }

    @Test
    @DisplayName("findByName() should return an empty list when no users match")
    void testFindByName_NoMatches() {
        entityManager.persist(new User("Bob"));
        entityManager.flush();

        List<User> results = userRepository.findByName("NonExistentName");

        assertTrue(results.isEmpty(), "Expected no users to match a nonexistent name");
    }
}
