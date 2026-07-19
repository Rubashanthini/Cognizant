package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.exception.ResourceNotFoundException;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;

    // ---------- HANDS-ON 1 ----------

    public List<Employee> getAllEmployees() {
        logger.info("Fetching all employees using HQL: SELECT e FROM Employee e");
        List<Employee> employees = employeeRepository.getAllEmployees();
        logger.info("Total employees fetched: {}", employees.size());
        return employees;
    }

    public Employee getEmployeeById(int id) {
        logger.info("Fetching employee by id={} using HQL", id);
        Employee employee = employeeRepository.getEmployeeById(id);
        if (employee == null) {
            throw new ResourceNotFoundException("Employee", "empId", id);
        }
        return employee;
    }

    // ---------- HANDS-ON 2 ----------

    public List<Employee> getAllPermanentEmployees() {
        logger.info("Fetching permanent employees - plain HQL (WHERE e.permanent = true)");
        List<Employee> employees = employeeRepository.getAllPermanentEmployees();
        logger.info("Permanent employee count: {}", employees.size());
        return employees;
    }

    public List<Employee> getPermanentEmployeesUsingJoin() {
        logger.info("Fetching permanent employees - using plain JOIN (association stays lazy)");
        List<Employee> employees = employeeRepository.getPermanentEmployeesJoin();
        logger.info("Permanent employee count (JOIN): {}", employees.size());
        return employees;
    }

    public List<Employee> getPermanentEmployeesUsingFetchJoin() {
        logger.info("Fetching permanent employees - using JOIN FETCH (department + skills eagerly loaded in one SQL query)");
        List<Employee> employees = employeeRepository.getPermanentEmployeesFetchJoin();
        for (Employee e : employees) {
            logger.info("Employee: {} | Dept: {} | Skills: {}",
                    e.getEmpName(),
                    e.getDepartment() != null ? e.getDepartment().getDeptName() : "N/A",
                    e.getSkills());
        }
        return employees;
    }

    // ---------- HANDS-ON 4 ----------

    public Double getAverageSalary() {
        logger.info("Calculating average salary across all employees using AVG()");
        Double avg = employeeRepository.getAverageSalary();
        logger.info("Average Salary (all employees): {}", avg);
        return avg;
    }

    public Double getAverageSalary(int departmentId) {
        logger.info("Calculating average salary for department id={} using AVG() with @Param", departmentId);
        Double avg = employeeRepository.getAverageSalary(departmentId);
        logger.info("Average Salary (deptId={}): {}", departmentId, avg);
        return avg;
    }

    // ---------- HANDS-ON 5 ----------

    public List<Employee> getAllEmployeesNative() {
        logger.info("Fetching all employees using native SQL: SELECT * FROM employee");
        return employeeRepository.getAllEmployeesNative();
    }

    public List<Employee> getPermanentEmployeesNative() {
        logger.info("Fetching permanent employees using native SQL");
        return employeeRepository.getPermanentEmployeesNative();
    }

    public Double getAverageSalaryNative() {
        logger.info("Calculating average salary using native SQL AVG(salary)");
        return employeeRepository.getAverageSalaryNative();
    }
}
