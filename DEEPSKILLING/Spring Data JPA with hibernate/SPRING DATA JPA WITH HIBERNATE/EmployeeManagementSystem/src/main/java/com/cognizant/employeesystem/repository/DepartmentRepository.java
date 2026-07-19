package com.cognizant.employeesystem.repository;

import com.cognizant.employeesystem.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Exercise 3: DepartmentRepository extending JpaRepository - inherits
 * findAll, findById, save, deleteById, count, etc. for free.
 *
 * Exercise 5: Demonstrates derived query methods (method-name keyword
 * queries), the @Query annotation (both JPQL and native SQL), and
 * invoking a @NamedQuery declared on the Department entity.
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // ---------------------------------------------------------------
    // Derived query methods - Spring Data parses the method name and
    // builds the query automatically.
    // ---------------------------------------------------------------

    Optional<Department> findByName(String name);

    List<Department> findByNameContainingIgnoreCase(String keyword);

    boolean existsByName(String name);

    // ---------------------------------------------------------------
    // @Query - explicit JPQL
    // ---------------------------------------------------------------

    @Query("SELECT d FROM Department d WHERE SIZE(d.employees) > :minCount")
    List<Department> findDepartmentsWithMoreThanNEmployees(@Param("minCount") int minCount);

    // ---------------------------------------------------------------
    // @Query - native SQL
    // ---------------------------------------------------------------

    @Query(value = "SELECT * FROM department ORDER BY name ASC", nativeQuery = true)
    List<Department> findAllOrderedByNameNative();

    // ---------------------------------------------------------------
    // Named query - resolved by method name matching
    // "Department.findByNameIgnoreCase" declared via @NamedQuery
    // on the Department entity (see entity.Department).
    // ---------------------------------------------------------------

    Optional<Department> findByNameIgnoreCase(@Param("name") String name);
}
