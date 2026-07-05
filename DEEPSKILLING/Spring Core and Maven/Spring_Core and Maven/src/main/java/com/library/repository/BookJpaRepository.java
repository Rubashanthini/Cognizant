package com.library.repository;

import com.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * BookJpaRepository - Exercise 9.
 *
 * Spring Data JPA repository interface for the {@link Book} entity, backed
 * by the H2 in-memory database. Spring Data automatically generates the
 * implementation at runtime, providing CRUD operations (save, findById,
 * findAll, deleteById, etc.) with no boilerplate code required.
 *
 * NAMING NOTE: The exercise sheet refers to this as "BookRepository".
 * Because this project already contains a plain, in-memory
 * {@link BookRepository} class (used for the Spring Core / DI / AOP
 * demonstrations in Exercises 1-8) in the same package, this JPA repository
 * has been named BookJpaRepository to avoid a duplicate-type compile error.
 * Functionally it fulfils exactly the same role that Exercise 9 describes:
 * a Spring Data JPA repository for the Book entity.
 */
@Repository
public interface BookJpaRepository extends JpaRepository<Book, Long> {
    // Spring Data JPA provides save(), findById(), findAll(), deleteById(),
    // count(), etc. automatically - no method bodies required.
}
