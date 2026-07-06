package com.cognizant.ormlearn.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * HANDS-ON 3: Basic entity mapping.
 * HANDS-ON 4: Many-to-One relationship - Employee -> Department.
 * HANDS-ON 6: Many-to-Many relationship - Employee <-> Skill (owning side).
 *
 * NOTE ON HANDS-ON 6 (LAZY vs EAGER):
 * The "skills" relationship below was FIRST implemented as:
 *
 *      @ManyToMany(fetch = FetchType.LAZY)
 *      @JoinTable(name = "employee_skill", ...)
 *      private Set<Skill> skills;
 *
 * Accessing employee.getSkills() after the Hibernate Session/transaction
 * was closed (e.g. from OrmLearnApplication after the service method
 * returned) threw:
 *
 *      org.hibernate.LazyInitializationException:
 *      failed to lazily initialize a collection of role:
 *      com.cognizant.ormlearn.model.Employee.skills,
 *      could not initialize proxy - no Session
 *
 * To FIX this, the fetch type was changed to FetchType.EAGER so the
 * skills collection loads together with the employee.
 */
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long empId;

    @Column(name = "emp_name", nullable = false, length = 100)
    private String empName;

    @Column(name = "salary", nullable = false)
    private Double salary;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dept_id")
    private Department department;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "employee_skill",
            joinColumns = @JoinColumn(name = "emp_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills = new HashSet<>();

    public Employee() {
    }

    public Employee(String empName, Double salary) {
        this.empName = empName;
        this.salary = salary;
    }

    public Employee(String empName, Double salary, Department department) {
        this.empName = empName;
        this.salary = salary;
        this.department = department;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", salary=" + salary +
                ", deptId=" + (department != null ? department.getDeptId() : null) +
                '}';
    }
}
