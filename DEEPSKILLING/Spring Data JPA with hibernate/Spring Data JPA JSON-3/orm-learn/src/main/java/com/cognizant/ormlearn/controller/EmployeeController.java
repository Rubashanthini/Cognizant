package com.cognizant.ormlearn.controller;

import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/permanent")
    public List<Employee> getAllPermanentEmployees() {
        return employeeService.getAllPermanentEmployees();
    }

    @GetMapping("/permanent/join")
    public List<Employee> getPermanentEmployeesUsingJoin() {
        return employeeService.getPermanentEmployeesUsingJoin();
    }

    @GetMapping("/permanent/fetch-join")
    public List<Employee> getPermanentEmployeesUsingFetchJoin() {
        return employeeService.getPermanentEmployeesUsingFetchJoin();
    }

    @GetMapping("/average-salary")
    public Double getAverageSalary() {
        return employeeService.getAverageSalary();
    }

    @GetMapping("/average-salary/department/{deptId}")
    public Double getAverageSalaryByDepartment(@PathVariable int deptId) {
        return employeeService.getAverageSalary(deptId);
    }

    @GetMapping("/native")
    public List<Employee> getAllEmployeesNative() {
        return employeeService.getAllEmployeesNative();
    }

    @GetMapping("/native/permanent")
    public List<Employee> getPermanentEmployeesNative() {
        return employeeService.getPermanentEmployeesNative();
    }

    @GetMapping("/native/average-salary")
    public Double getAverageSalaryNative() {
        return employeeService.getAverageSalaryNative();
    }
}
