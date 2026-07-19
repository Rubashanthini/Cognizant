package com.cognizant.employeesystem.service;

import com.cognizant.employeesystem.dto.DepartmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Exercise 4: Service contract for Department CRUD operations, plus
 * derived/@Query based lookups (Exercise 5) and pagination (Exercise 6).
 */
public interface DepartmentService {

    List<DepartmentDTO> getAllDepartments();

    DepartmentDTO getDepartmentById(Long id);

    DepartmentDTO createDepartment(DepartmentDTO departmentDTO);

    DepartmentDTO updateDepartment(Long id, DepartmentDTO departmentDTO);

    void deleteDepartment(Long id);

    List<DepartmentDTO> searchByName(String keyword);

    DepartmentDTO getDepartmentByName(String name);

    List<DepartmentDTO> getDepartmentsWithMoreThanNEmployees(int minCount);

    Page<DepartmentDTO> getDepartmentsPaged(Pageable pageable);
}
