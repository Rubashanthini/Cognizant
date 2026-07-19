package com.cognizant.testing.repository;

import com.cognizant.testing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Spring Data JPA repository for {@link User} entities.
 * <p>
 * Extending {@link JpaRepository} provides CRUD operations (findById, save,
 * findAll, deleteById, etc.) out of the box, with no implementation required.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Custom derived query method (Exercise 7) that returns all users
     * matching the given name. Spring Data JPA generates the implementation
     * automatically based on the method name.
     *
     * @param name the name to search for
     * @return list of users whose name matches the given value
     */
    List<User> findByName(String name);
}
