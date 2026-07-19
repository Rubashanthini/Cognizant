package com.cognizant.employeesystem.projection;

/**
 * Exercise 8: Interface-based (closed) projection.
 *
 * Spring Data JPA generates a proxy implementation at runtime that
 * fetches ONLY the properties declared here (name, email) instead of
 * the whole Employee entity - reducing the amount of data pulled from
 * the database for read-only views.
 */
public interface EmployeeNameOnly {

    String getName();

    String getEmail();
}
