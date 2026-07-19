package com.cognizant.employeesystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Exercise 2: Department entity.
 *
 * Fields: id, name.
 * Holds the "one" side of the one-to-many relationship with Employee.
 *
 * Exercise 5: Demonstrates @NamedQuery / @NamedQueries.
 * Exercise 10: Demonstrates Hibernate-specific annotations
 * (@DynamicInsert / @DynamicUpdate) which instruct Hibernate to generate
 * INSERT/UPDATE statements containing only non-null columns, improving
 * performance for entities with many optional columns.
 */
@Entity
@Table(name = "department")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = "employees")
@ToString(exclude = "employees")
@DynamicInsert
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NamedQueries({
        @NamedQuery(
                name = "Department.findByNameIgnoreCase",
                query = "SELECT d FROM Department d WHERE LOWER(d.name) = LOWER(:name)"
        ),
        @NamedQuery(
                name = "Department.findAllOrderByNameAsc",
                query = "SELECT d FROM Department d ORDER BY d.name ASC"
        )
})
public class Department extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    /**
     * One department has many employees. mappedBy indicates that the
     * Employee entity owns the foreign key (department_id column).
     * CascadeType.ALL + orphanRemoval means removing an employee from
     * this list (and saving the department) will delete it from the DB.
     */
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Employee> employees = new ArrayList<>();

    /**
     * Convenience helper to keep both sides of the bidirectional
     * relationship in sync.
     */
    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setDepartment(this);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.setDepartment(null);
    }
}
