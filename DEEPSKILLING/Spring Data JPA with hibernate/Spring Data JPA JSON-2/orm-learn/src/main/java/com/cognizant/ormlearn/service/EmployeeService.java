package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.exception.ResourceNotFoundException;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // HANDS-ON 4: get()
    @Transactional(readOnly = true)
    public Employee get(Long empId) {
        return employeeRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + empId));
    }

    // HANDS-ON 4: save()
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    // HANDS-ON 6: add a skill to an existing employee
    @Transactional
    public Employee addSkillToEmployee(Long empId, Skill skill) {
        Employee employee = get(empId);
        employee.addSkill(skill);
        return employeeRepository.save(employee);
    }
}
