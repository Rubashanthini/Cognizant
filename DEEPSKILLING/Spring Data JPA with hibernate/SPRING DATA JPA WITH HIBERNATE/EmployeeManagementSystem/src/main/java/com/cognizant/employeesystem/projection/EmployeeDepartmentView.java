package com.cognizant.employeesystem.projection;

import org.springframework.beans.factory.annotation.Value;

/**
 * Exercise 8: Interface-based projection with a SpEL-backed "open"
 * projection method (@Value). Demonstrates deriving a computed/nested
 * property (the department's name) directly on the projection
 * interface instead of exposing the whole Department object.
 */
public interface EmployeeDepartmentView {

    Long getId();

    String getName();

    @Value("#{target.department != null ? target.department.name : 'Unassigned'}")
    String getDepartmentName();
}
