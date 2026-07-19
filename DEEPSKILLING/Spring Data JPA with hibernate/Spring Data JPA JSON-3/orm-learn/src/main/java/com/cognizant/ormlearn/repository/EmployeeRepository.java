package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * ==========================================================
 * HANDS-ON 1 :: Introduction to HQL and JPQL
 * ==========================================================
 * HQL (Hibernate Query Language) and JPQL (Java Persistence Query
 * Language) are both object-oriented query languages that operate
 * on entities and their properties rather than directly on database
 * tables and columns.
 *
 * Difference between HQL and JPQL:
 *  1. JPQL is part of the JPA specification and is a subset/standard
 *     defined by Java Persistence API; HQL is Hibernate's own query
 *     language, a superset of JPQL with extra Hibernate-specific
 *     features.
 *  2. JPQL queries are guaranteed to be portable across any JPA
 *     provider (Hibernate, EclipseLink, OpenJPA); HQL is tied
 *     specifically to Hibernate as the ORM provider.
 *  3. HQL supports additional features not present in JPQL, such as
 *     more flexible use of implicit joins, native Hibernate functions
 *     (e.g. current_date(), extra collection functions), and support
 *     for UPDATE/DELETE with joins in some versions.
 *  4. In Spring Data JPA, when you write @Query(...) without
 *     nativeQuery = true, the string you supply is technically
 *     parsed by Hibernate's query translator, so it is valid HQL;
 *     since Hibernate's HQL is a superset of JPQL, standard JPQL
 *     syntax works interchangeably in Spring Data JPA @Query methods.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // ======================================================
    // HANDS-ON 1 :: basic HQL query methods
    // ======================================================

    @Query("SELECT e FROM Employee e")
    List<Employee> getAllEmployees();

    @Query("SELECT e FROM Employee e WHERE e.empId = :id")
    Employee getEmployeeById(@Param("id") int id);

    @Query("SELECT e FROM Employee e WHERE e.empName = :name")
    Employee getEmployeeByName(@Param("name") String name);

    // ======================================================
    // HANDS-ON 2 :: Get all Permanent Employees using HQL
    // ======================================================

    /**
     * Simple HQL - returns permanent employees only.
     * Department / Skill collections are LAZY, so accessing them
     * outside a transaction will trigger additional SELECTs (N+1).
     */
    @Query("SELECT e FROM Employee e WHERE e.permanent = true")
    List<Employee> getAllPermanentEmployees();

    /**
     * Optimized using normal JOIN (INNER JOIN).
     * A plain JOIN in HQL only affects the WHERE/ON filtering path;
     * it does NOT tell Hibernate to eagerly load the associated
     * entity into the returned Employee object's graph. The
     * associated Department is still lazily fetched later, so this
     * still results in N+1 selects when department is accessed.
     */
    @Query("SELECT e FROM Employee e JOIN e.department d WHERE e.permanent = true")
    List<Employee> getPermanentEmployeesJoin();

    /**
     * Optimized using FETCH JOIN.
     * FETCH JOIN instructs Hibernate to populate the association in
     * the SAME SELECT statement (a single SQL JOIN), avoiding the
     * N+1 select problem entirely. The department and skills are
     * fully initialized in the returned objects.
     *
     * Difference between JOIN and FETCH JOIN:
     *  - JOIN: used only to restrict/filter results via the
     *    association; the association itself remains a lazy proxy
     *    unless separately fetched -> can cause N+1 selects.
     *  - FETCH JOIN: eagerly initializes the association's data
     *    into the parent entity in the same query/round-trip,
     *    avoiding extra SELECTs, at the cost of a (possibly) larger
     *    result set from the JOIN.
     */
    @Query("SELECT DISTINCT e FROM Employee e " +
            "JOIN FETCH e.department d " +
            "LEFT JOIN FETCH e.skills s " +
            "WHERE e.permanent = true")
    List<Employee> getPermanentEmployeesFetchJoin();

    // ======================================================
    // HANDS-ON 4 :: Average Salary using HQL
    // ======================================================

    @Query("SELECT AVG(e.salary) FROM Employee e")
    Double getAverageSalary();

    @Query("SELECT AVG(e.salary) FROM Employee e WHERE e.department.deptId = :id")
    Double getAverageSalary(@Param("id") int id);

    // ======================================================
    // HANDS-ON 5 :: Native Query
    // ======================================================

    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<Employee> getAllEmployeesNative();

    @Query(value = "SELECT * FROM employee WHERE permanent = 1", nativeQuery = true)
    List<Employee> getPermanentEmployeesNative();

    @Query(value = "SELECT AVG(salary) FROM employee", nativeQuery = true)
    Double getAverageSalaryNative();
}
