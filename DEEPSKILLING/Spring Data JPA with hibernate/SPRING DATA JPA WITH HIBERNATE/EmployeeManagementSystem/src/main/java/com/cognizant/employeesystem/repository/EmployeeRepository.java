package com.cognizant.employeesystem.repository;

import com.cognizant.employeesystem.entity.Employee;
import com.cognizant.employeesystem.projection.EmployeeDepartmentView;
import com.cognizant.employeesystem.projection.EmployeeNameOnly;
import com.cognizant.employeesystem.projection.EmployeeSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Exercise 3: EmployeeRepository extending JpaRepository.
 *
 * Exercise 5: Derived query methods, @Query (JPQL + native), and a
 * @NamedQuery lookup ("Employee.findByDepartmentName").
 *
 * Exercise 6: Page<Employee> findAll(Pageable) is inherited directly
 * from JpaRepository/PagingAndSortingRepository and used for
 * pagination + sorting.
 *
 * Exercise 8: Methods returning projection types (interface-based,
 * class-based/DTO) instead of the full entity.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // ---------------------------------------------------------------
    // Derived query methods
    // ---------------------------------------------------------------

    List<Employee> findByNameContainingIgnoreCase(String keyword);

    Optional<Employee> findByEmail(String email);

    List<Employee> findByDepartment_Id(Long departmentId);

    List<Employee> findByDepartment_NameIgnoreCase(String departmentName);

    long countByDepartment_Id(Long departmentId);

    // ---------------------------------------------------------------
    // @Query - explicit JPQL
    // ---------------------------------------------------------------

    @Query("SELECT e FROM Employee e WHERE e.department IS NULL")
    List<Employee> findUnassignedEmployees();

    @Query("SELECT e FROM Employee e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :keyword, '%')) "
            + "OR LOWER(e.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Employee> searchByNameOrEmail(@Param("keyword") String keyword, Pageable pageable);

    // ---------------------------------------------------------------
    // @Query - native SQL
    // ---------------------------------------------------------------

    @Query(value = "SELECT * FROM employee WHERE department_id = :deptId", nativeQuery = true)
    List<Employee> findByDepartmentIdNative(@Param("deptId") Long deptId);

    // ---------------------------------------------------------------
    // Named query - resolved via "Employee.findByDepartmentName"
    // declared with @NamedQuery on the Employee entity.
    // ---------------------------------------------------------------

    List<Employee> findByDepartmentName(@Param("departmentName") String departmentName);

    // ---------------------------------------------------------------
    // Exercise 8: Projections
    // ---------------------------------------------------------------

    /** Interface-based (closed) projection - only name & email are fetched. */
    List<EmployeeNameOnly> findByDepartment_IdOrderByNameAsc(Long departmentId);

    /** Interface-based projection with a nested/derived SpEL property. */
    List<EmployeeDepartmentView> findAllProjectedBy();

    /** Class-based (DTO) projection via JPQL constructor expression. */
    @Query("SELECT new com.cognizant.employeesystem.projection.EmployeeSummary(e.id, e.name, e.email) "
            + "FROM Employee e ORDER BY e.name ASC")
    List<EmployeeSummary> findAllSummaries();

    /** Paginated interface-based projection. */
    Page<EmployeeNameOnly> findAllBy(Pageable pageable);
}
