package com.cognizant.employeesystem.service.impl;

import com.cognizant.employeesystem.dto.DepartmentDTO;
import com.cognizant.employeesystem.entity.Department;
import com.cognizant.employeesystem.entity.Employee;
import com.cognizant.employeesystem.exception.ResourceNotFoundException;
import com.cognizant.employeesystem.repository.DepartmentRepository;
import com.cognizant.employeesystem.service.AuditService;
import com.cognizant.employeesystem.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Exercise 4/5/6: Concrete implementation of {@link DepartmentService}.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "transactionManager")
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final AuditService auditService;

    @Override
    @Transactional(transactionManager = "transactionManager", readOnly = true)
    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(transactionManager = "transactionManager", readOnly = true)
    public DepartmentDTO getDepartmentById(Long id) {
        return toDTO(findDepartmentOrThrow(id));
    }

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setName(departmentDTO.getName());

        Department saved = departmentRepository.save(department);
        auditService.recordAction("Department", saved.getId(), "CREATE", "api-user");
        log.info("Created department id={} name={}", saved.getId(), saved.getName());
        return toDTO(saved);
    }

    @Override
    public DepartmentDTO updateDepartment(Long id, DepartmentDTO departmentDTO) {
        Department department = findDepartmentOrThrow(id);
        department.setName(departmentDTO.getName());

        Department saved = departmentRepository.save(department);
        auditService.recordAction("Department", saved.getId(), "UPDATE", "api-user");
        log.info("Updated department id={}", saved.getId());
        return toDTO(saved);
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = findDepartmentOrThrow(id);
        departmentRepository.delete(department);
        auditService.recordAction("Department", id, "DELETE", "api-user");
        log.info("Deleted department id={}", id);
    }

    @Override
    @Transactional(transactionManager = "transactionManager", readOnly = true)
    public List<DepartmentDTO> searchByName(String keyword) {
        return departmentRepository.findByNameContainingIgnoreCase(keyword).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(transactionManager = "transactionManager", readOnly = true)
    public DepartmentDTO getDepartmentByName(String name) {
        Department department = departmentRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "name", name));
        return toDTO(department);
    }

    @Override
    @Transactional(transactionManager = "transactionManager", readOnly = true)
    public List<DepartmentDTO> getDepartmentsWithMoreThanNEmployees(int minCount) {
        return departmentRepository.findDepartmentsWithMoreThanNEmployees(minCount).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(transactionManager = "transactionManager", readOnly = true)
    public Page<DepartmentDTO> getDepartmentsPaged(Pageable pageable) {
        return departmentRepository.findAll(pageable).map(this::toDTO);
    }

    // -----------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------

    private Department findDepartmentOrThrow(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
    }

    private DepartmentDTO toDTO(Department department) {
        List<String> employeeNames = department.getEmployees() == null
                ? java.util.Collections.emptyList()
                : department.getEmployees().stream().map(Employee::getName).collect(Collectors.toList());

        return DepartmentDTO.builder()
                .id(department.getId())
                .name(department.getName())
                .employeeNames(employeeNames)
                .build();
    }
}
