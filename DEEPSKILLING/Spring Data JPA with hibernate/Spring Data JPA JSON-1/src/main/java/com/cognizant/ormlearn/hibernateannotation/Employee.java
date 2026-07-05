package com.cognizant.ormlearn.hibernateannotation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Hands-on 3: Hibernate Annotation Configuration example.
 *
 * Same intent as Hands-on 2's Employee class, but here the
 * object-to-relational mapping is expressed with annotations directly
 * on the persistence class instead of an external XML mapping file.
 *
 *  @Entity        : marks this class as a persistent entity
 *  @Table         : maps it to the "employee_annotation" table
 *  @Id            : marks "id" as the primary key
 *  @GeneratedValue: tells Hibernate to auto-generate the primary key value
 *  @Column        : maps each field to its database column
 */
@Entity
@Table(name = "employee_annotation")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
