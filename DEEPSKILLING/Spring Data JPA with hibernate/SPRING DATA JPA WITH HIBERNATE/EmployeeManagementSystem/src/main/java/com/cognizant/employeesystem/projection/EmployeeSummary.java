package com.cognizant.employeesystem.projection;

import lombok.Value;

/**
 * Exercise 8: Class-based (DTO) projection.
 *
 * Annotated with Lombok's {@code @Value} which generates an immutable
 * class with a single all-args constructor, getters, equals/hashCode
 * and toString - exactly the shape required for JPQL "constructor
 * expression" projections, e.g.:
 *
 * <pre>
 * SELECT new com.cognizant.employeesystem.projection.EmployeeSummary(e.id, e.name, e.email)
 * FROM Employee e
 * </pre>
 *
 * Because the class has no default constructor, Spring Data JPA/Hibernate
 * matches it to the corresponding "new" constructor expression in a
 * @Query, avoiding proxy-based interface projections entirely.
 */
@Value
public class EmployeeSummary {

    Long id;
    String name;
    String email;
}
