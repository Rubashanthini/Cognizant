package com.cognizant.employeesystem.service;

import com.cognizant.employeesystem.dto.EmployeeDTO;
import com.cognizant.employeesystem.projection.EmployeeDepartmentView;
import com.cognizant.employeesystem.projection.EmployeeNameOnly;
import com.cognizant.employeesystem.projection.EmployeeSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Exercise 4: Service contract for Employee CRUD operations, plus the
 * query-method (Exercise 5), pagination/sorting (Exercise 6), and
 * projection (Exercise 8) capabilities exposed to the controller layer.
 */
public interface EmployeeService {

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long id);

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);

    void deleteEmployee(Long id);

    // Exercise 5: query methods
    List<EmployeeDTO> searchByKeyword(String keyword);

    List<EmployeeDTO> getEmployeesByDepartmentId(Long departmentId);

    List<EmployeeDTO> getEmployeesByDepartmentName(String departmentName);

    List<EmployeeDTO> getUnassignedEmployees();

    // Exercise 6: pagination & sorting
    Page<EmployeeDTO> getEmployeesPaged(Pageable pageable);

    List<EmployeeDTO> getEmployeesSorted(String field, String direction);

    Page<EmployeeDTO> searchPaged(String keyword, Pageable pageable);

    // Exercise 8: projections
    List<EmployeeNameOnly> getEmployeeNamesByDepartment(Long departmentId);

    List<EmployeeDepartmentView> getEmployeeDepartmentViews();

    List<EmployeeSummary> getAllEmployeeSummaries();

    // Exercise 10: batch processing demo
    List<EmployeeDTO> createEmployeesInBatch(List<EmployeeDTO> employees);
}
