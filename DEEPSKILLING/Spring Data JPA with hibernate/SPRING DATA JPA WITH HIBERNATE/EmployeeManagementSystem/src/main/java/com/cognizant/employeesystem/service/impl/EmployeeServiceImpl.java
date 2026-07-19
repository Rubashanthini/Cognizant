package com.cognizant.employeesystem.service.impl;

import com.cognizant.employeesystem.dto.EmployeeDTO;
import com.cognizant.employeesystem.entity.Department;
import com.cognizant.employeesystem.entity.Employee;
import com.cognizant.employeesystem.exception.ResourceNotFoundException;
import com.cognizant.employeesystem.projection.EmployeeDepartmentView;
import com.cognizant.employeesystem.projection.EmployeeNameOnly;
import com.cognizant.employeesystem.projection.EmployeeSummary;
import com.cognizant.employeesystem.repository.DepartmentRepository;
import com.cognizant.employeesystem.repository.EmployeeRepository;
import com.cognizant.employeesystem.service.AuditService;
import com.cognizant.employeesystem.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Exercise 4/5/6/8/10: Concrete implementation of {@link EmployeeService}.
 *
 * All read operations run in a read-only transaction; writes run in a
 * default read-write transaction bound to the PRIMARY transaction
 * manager. After each write, {@link AuditService} persists a record to
 * the SECONDARY data source (Exercise 9), running in its own,
 * independent transaction.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "transactionManager")
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final AuditService auditService;

    // -----------------------------------------------------------------
    // CRUD (Exercise 4)
    // -----------------------------------------------------------------

    @Override
    @Transactional(transactionManager = "transactionManager", readOnly = true)
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(transactionManager = "transactionManager", readOnly = true)
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = findEmployeeOrThrow(id);
        return toDTO(employee);
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setDepartment(resolveDepartment(employeeDTO.getDepartmentId()));

        Employee saved = employeeRepository.save(employee);
        auditService.recordAction("Employee", saved.getId(), "CREATE", "api-user");
        log.info("Created employee id={} name={}", saved.getId(), saved.getName());
        return toDTO(saved);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee = findEmployeeOrThrow(id);
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setDepartment(resolveDepartment(employeeDTO.getDepartmentId()));

        Employee saved = employeeRepository.save(employee);
        auditService.recordAction("Employee", saved.getId(), "UPDATE", "api-user");
        log.info("Updated employee id={}", saved.getId());
        return toDTO(saved);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = findEmployeeOrThrow(id);
        employeeRepository.delete(employee);
        auditService.recordAction("Employee", id, "DELETE", "api-user");
        log.info("Deleted employee id={}", id);
    }

    // -----------------------------------------------------------------
    // Query methods (Exercise 5)
    // -----------------------------------------------------------------

    @Override
    @Transactional(transactionManager = "transactionManager", readOnly = true)
    public List<EmployeeDTO> searchByKeyword(String keyword) {
        return employeeRepository.findByNameContainingIgnoreCase(keyword).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(transactionManager = "transactionManager", readOnly = true)
    public List<EmployeeDTO> getEmployeesByDepartmentId(Long departmentId) {
        return employeeRepository.findByDepartment_Id(departmentId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(transactionManager = "transactionManager", readOnly = true)
    public List<EmployeeDTO> getEmployeesByDepartmentName(String departmentName) {
        // Uses the @NamedQuery "Employee.findByDepartmentName" declared on Employee entity
        return employeeRepository.findByDepartmentName(departmentName).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(transactionManager = "transactionManager", readOnly = true)
    public List<EmployeeDTO> getUnassignedEmployees() {
        return employeeRepository.findUnassignedEmployees().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // -----------------------------------------------------------------
    // Pagination & sorting (Exercise 6)
    // -----------------------------------------------------------------

    @Override
    @Transactional(transactionManager = "transactionManager", readOnly = true)
    public Page<EmployeeDTO> getEmployeesPaged(Pageable pageable) {
        return employeeRepository.findAll(pageable).map(this::toDTO);
    }

    @Override
    @Transactional(transactionManager = "transactionManager", readOnly = true)
    public List<EmployeeDTO> getEmployeesSorted(String field, String direction) {
        Sort.Direction dir = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(dir, field);
        return employeeRepository.findAll(sort).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(transactionManager = "transactionManager", readOnly = true)
    public Page<EmployeeDTO> searchPaged(String keyword, Pageable pageable) {
        return employeeRepository.searchByNameOrEmail(keyword, pageable).map(this::toDTO);
    }

    // -----------------------------------------------------------------
    // Projections (Exercise 8)
    // -----------------------------------------------------------------

    @Override
    @Transactional(transactionManager = "transactionManager", readOnly = true)
    public List<EmployeeNameOnly> getEmployeeNamesByDepartment(Long departmentId) {
        return employeeRepository.findByDepartment_IdOrderByNameAsc(departmentId);
    }

    @Override
    @Transactional(transactionManager = "transactionManager", readOnly = true)
    public List<EmployeeDepartmentView> getEmployeeDepartmentViews() {
        return employeeRepository.findAllProjectedBy();
    }

    @Override
    @Transactional(transactionManager = "transactionManager", readOnly = true)
    public List<EmployeeSummary> getAllEmployeeSummaries() {
        return employeeRepository.findAllSummaries();
    }

    // -----------------------------------------------------------------
    // Exercise 10: Hibernate batch insert demonstration.
    // Because hibernate.jdbc.batch_size / order_inserts / order_updates
    // are configured (see PrimaryDataSourceConfig / application.properties),
    // saveAll() groups these INSERT statements into JDBC batches instead
    // of issuing one round-trip per row.
    // -----------------------------------------------------------------

    @Override
    public List<EmployeeDTO> createEmployeesInBatch(List<EmployeeDTO> employees) {
        List<Employee> entities = employees.stream()
                .map(dto -> Employee.builder()
                        .name(dto.getName())
                        .email(dto.getEmail())
                        .department(resolveDepartment(dto.getDepartmentId()))
                        .build())
                .collect(Collectors.toList());

        List<Employee> saved = employeeRepository.saveAll(entities);
        saved.forEach(e -> auditService.recordAction("Employee", e.getId(), "CREATE", "batch-job"));
        log.info("Batch-inserted {} employees", saved.size());
        return saved.stream().map(this::toDTO).collect(Collectors.toList());
    }

    // -----------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------

    private Employee findEmployeeOrThrow(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
    }

    private Department resolveDepartment(Long departmentId) {
        if (departmentId == null) {
            return null;
        }
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", departmentId));
    }

    private EmployeeDTO toDTO(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .departmentId(employee.getDepartment() != null ? employee.getDepartment().getId() : null)
                .departmentName(employee.getDepartment() != null ? employee.getDepartment().getName() : null)
                .build();
    }
}
