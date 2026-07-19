package com.cognizant.employeesystem.controller;

import com.cognizant.employeesystem.dto.EmployeeDTO;
import com.cognizant.employeesystem.projection.EmployeeDepartmentView;
import com.cognizant.employeesystem.projection.EmployeeNameOnly;
import com.cognizant.employeesystem.projection.EmployeeSummary;
import com.cognizant.employeesystem.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Exercise 4/5/6/8: REST controller exposing Employee endpoints -
 * standard CRUD, query-method based search, pagination/sorting, and
 * projection based views.
 */
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // -----------------------------------------------------------------
    // Exercise 4: Basic CRUD
    // -----------------------------------------------------------------

    /**
     * GET /employees
     * GET /employees?page=0&size=5   (Exercise 6: pagination when params supplied)
     */
    @GetMapping
    public ResponseEntity<?> getAllEmployees(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {

        if (page != null && size != null) {
            Page<EmployeeDTO> result = employeeService.getEmployeesPaged(PageRequest.of(page, size));
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO created = employeeService.createEmployee(employeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id,
                                                        @Valid @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    // -----------------------------------------------------------------
    // Exercise 5: Query method endpoints
    // -----------------------------------------------------------------

    @GetMapping("/search")
    public ResponseEntity<List<EmployeeDTO>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(employeeService.searchByKeyword(keyword));
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<EmployeeDTO>> getByDepartmentId(@PathVariable Long departmentId) {
        return ResponseEntity.ok(employeeService.getEmployeesByDepartmentId(departmentId));
    }

    @GetMapping("/department-name/{departmentName}")
    public ResponseEntity<List<EmployeeDTO>> getByDepartmentName(@PathVariable String departmentName) {
        return ResponseEntity.ok(employeeService.getEmployeesByDepartmentName(departmentName));
    }

    @GetMapping("/unassigned")
    public ResponseEntity<List<EmployeeDTO>> getUnassigned() {
        return ResponseEntity.ok(employeeService.getUnassignedEmployees());
    }

    // -----------------------------------------------------------------
    // Exercise 6: Pagination & Sorting
    // -----------------------------------------------------------------

    /** GET /employees/sort?field=name&direction=asc */
    @GetMapping("/sort")
    public ResponseEntity<List<EmployeeDTO>> getSorted(
            @RequestParam String field,
            @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(employeeService.getEmployeesSorted(field, direction));
    }

    /** GET /employees/page-sort?page=0&size=5&sort=name */
    @GetMapping("/page-sort")
    public ResponseEntity<Page<EmployeeDTO>> getPagedAndSorted(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sort) {
        Pageable pageable = PageRequest.of(page, size, org.springframework.data.domain.Sort.by(sort));
        return ResponseEntity.ok(employeeService.getEmployeesPaged(pageable));
    }

    /** GET /employees/search-paged?keyword=al&page=0&size=5&sort=name */
    @GetMapping("/search-paged")
    public ResponseEntity<Page<EmployeeDTO>> searchPaged(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sort) {
        Pageable pageable = PageRequest.of(page, size, org.springframework.data.domain.Sort.by(sort));
        return ResponseEntity.ok(employeeService.searchPaged(keyword, pageable));
    }

    // -----------------------------------------------------------------
    // Exercise 8: Projection endpoints
    // -----------------------------------------------------------------

    @GetMapping("/projections/names/{departmentId}")
    public ResponseEntity<List<EmployeeNameOnly>> getNamesByDepartment(@PathVariable Long departmentId) {
        return ResponseEntity.ok(employeeService.getEmployeeNamesByDepartment(departmentId));
    }

    @GetMapping("/projections/department-view")
    public ResponseEntity<List<EmployeeDepartmentView>> getDepartmentViews() {
        return ResponseEntity.ok(employeeService.getEmployeeDepartmentViews());
    }

    @GetMapping("/projections/summaries")
    public ResponseEntity<List<EmployeeSummary>> getSummaries() {
        return ResponseEntity.ok(employeeService.getAllEmployeeSummaries());
    }

    // -----------------------------------------------------------------
    // Exercise 10: Batch insert demo endpoint
    // -----------------------------------------------------------------

    @PostMapping("/batch")
    public ResponseEntity<List<EmployeeDTO>> createBatch(@RequestBody List<EmployeeDTO> employees) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployeesInBatch(employees));
    }
}
