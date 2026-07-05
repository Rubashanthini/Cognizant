package com.cognizant.ormlearn.hibernatexml;

/**
 * Hands-on 2: Hibernate XML Configuration example.
 *
 * This is a plain POJO ("persistence class") with NO JPA/Hibernate
 * annotations at all. The mapping between this class's fields and the
 * "employee_xml" table's columns is defined entirely in the external
 * XML mapping file Employee.hbm.xml (see src/main/resources/hibernatexml/).
 *
 * This demonstrates the original way Hibernate mapped objects to
 * relational tables, before annotation-based mapping (Hands-on 3) and
 * later Spring Data JPA (Hands-on 1, 4, 5) simplified the process.
 */
public class Employee {

    private int id;
    private String firstName;
    private String lastName;
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
