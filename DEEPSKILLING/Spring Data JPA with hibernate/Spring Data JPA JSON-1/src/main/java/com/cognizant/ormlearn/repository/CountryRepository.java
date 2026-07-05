package com.cognizant.ormlearn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Country;

/**
 * Hands-on 1 / Hands-on 5: CountryRepository
 *
 * Extending JpaRepository<Country, String> gives us, for free, methods
 * such as findAll(), findById(), save(), deleteById(), etc. without
 * writing any implementation - this is the core benefit of Spring Data JPA
 * over plain Hibernate (see Hands-on 4 for a side-by-side comparison).
 *
 * Additional Query Methods are declared below purely by following
 * Spring Data JPA's method-name-derived-query convention; Spring parses
 * the method name and automatically builds and executes the query.
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    /**
     * Derived query equivalent to:
     *   select c from Country c where c.code = ?1
     * (functionally identical to the built-in findById(), included here
     * to demonstrate the query-method naming convention explicitly).
     */
    Country findByCode(String code);

    /**
     * Case-insensitive "contains" search - used for Hands-on 5's
     * "search countries using partial name" feature.
     */
    List<Country> findByNameContainingIgnoreCase(String name);

    /**
     * Countries whose name starts with the given prefix.
     */
    List<Country> findByNameStartingWith(String prefix);

    /**
     * Countries whose name ends with the given suffix.
     */
    List<Country> findByNameEndingWith(String suffix);

    /**
     * Countries whose name matches the given SQL LIKE pattern
     * (caller supplies the % wildcards, e.g. "%United%").
     */
    List<Country> findByNameLike(String likePattern);
}
