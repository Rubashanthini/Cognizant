package com.cognizant.ormlearn.comparison;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Hands-on 4: Spring Data JPA repository for Employee.
 *
 * Notice there is NO implementation here at all - just an interface
 * extending JpaRepository<Employee, Integer>. At runtime, Spring Data
 * JPA generates a proxy implementation of this interface automatically,
 * backed by Hibernate underneath. Compare the near-zero code required
 * here with EmployeeHibernateStyleDao's addEmployee() method below,
 * which performs the exact same "insert a row" operation but requires
 * manually managing a Session and Transaction.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // save(), findById(), findAll(), deleteById(), etc. are all
    // inherited for free from JpaRepository - no code needed.
}
