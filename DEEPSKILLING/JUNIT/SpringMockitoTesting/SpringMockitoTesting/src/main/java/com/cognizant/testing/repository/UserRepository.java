package com.cognizant.testing.repository;

import com.cognizant.testing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on the {@link User} entity.
 *
 * <p>Extends {@link JpaRepository} to inherit standard data access methods
 * such as findById, findAll, save, and deleteById without requiring any
 * additional implementation.</p>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
