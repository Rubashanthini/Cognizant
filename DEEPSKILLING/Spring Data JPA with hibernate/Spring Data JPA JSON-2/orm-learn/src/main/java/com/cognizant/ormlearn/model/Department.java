package com.cognizant.ormlearn.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * HANDS-ON 3: Basic entity mapping.
 * HANDS-ON 5: One-to-Many relationship - Department -> Employees.
 *
 * NOTE ON HANDS-ON 5 (LAZY vs EAGER):
 * The relationship below was FIRST implemented as:
 *
 *      @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
 *      private List<Employee> employees;
 *
 * When the Department was fetched inside a transaction/session and the
 * "employees" collection was accessed AFTER the Hibernate Session was
 * closed (e.g. after returning from a @Transactional service method and
 * calling department.getEmployees() from the console/test method), it
 * threw:
 *
 *      org.hibernate.LazyInitializationException:
 *      failed to lazily initialize a collection of role:
 *      com.cognizant.ormlearn.model.Department.employees,
 *      could not initialize proxy - no Session
 *
 * To FIX this and allow the collection to be read safely after the
 * session closes, the fetch type below was changed to FetchType.EAGER,
 * which loads the employees collection immediately with the department.
 */
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private Long deptId;

    @Column(name = "dept_name", nullable = false, length = 100)
    private String deptName;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();

    public Department() {
    }

    public Department(String deptName) {
        this.deptName = deptName;
    }

    public Department(Long deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
