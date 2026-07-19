package com.cognizant.employeesystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Exercise 2: Employee entity.
 *
 * Fields: id, name, email, department.
 * Holds the "many" side of the one-to-many relationship with Department.
 *
 * Exercise 5: Demonstrates @NamedQuery / @NamedQueries at the entity level.
 * Exercise 10: @DynamicInsert / @DynamicUpdate (Hibernate generates SQL with
 * only the columns that are actually populated / changed) and @BatchSize
 * (hints Hibernate to batch-fetch lazy associations) demonstrate
 * Hibernate-specific performance tuning annotations.
 */
@Entity
@Table(name = "employee")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = "department")
@ToString(exclude = "department")
@DynamicInsert
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NamedQueries({
        @NamedQuery(
                name = "Employee.findByDepartmentName",
                query = "SELECT e FROM Employee e WHERE e.department.name = :departmentName"
        ),
        @NamedQuery(
                name = "Employee.findAllOrderByNameAsc",
                query = "SELECT e FROM Employee e ORDER BY e.name ASC"
        )
})
public class Employee extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Employee name must not be blank")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Employee email must not be blank")
    @Email(message = "Employee email must be a valid email address")
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    /**
     * Many employees belong to one department. This side owns the
     * foreign key (department_id). FetchType.LAZY avoids loading the
     * department eagerly for every employee query; @BatchSize allows
     * Hibernate to fetch several lazy departments in a single batched
     * query instead of issuing one query per employee (N+1 mitigation).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @BatchSize(size = 10)
    private Department department;
}
