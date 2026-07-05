package com.cognizant.ormlearn.comparison;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Hands-on 4: Employee entity used to compare JPA, Hibernate and
 * Spring Data JPA side by side.
 *
 * This entity itself uses standard JPA annotations (javax.persistence.*)
 * - JPA only defines these annotations and the persistence contract; it
 * has NO runtime implementation of its own. Hibernate is the concrete
 * ORM engine (in the org.hibernate.* packages) that reads these
 * annotations and actually talks to the database on JPA's behalf.
 * Spring Data JPA then sits one layer above both, generating repository
 * implementations so application code never has to touch
 * EntityManager / Session directly.
 */
@Entity
@Table(name = "employee_comparison")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "salary")
    private int salary;

    public Employee() {
    }

    public Employee(String firstName, String lastName, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName
                + ", lastName=" + lastName + ", salary=" + salary + "]";
    }
}
