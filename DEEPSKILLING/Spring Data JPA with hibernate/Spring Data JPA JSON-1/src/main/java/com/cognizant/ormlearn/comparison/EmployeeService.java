package com.cognizant.ormlearn.comparison;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Hands-on 4: Spring Data JPA version of "add an employee".
 *
 * Compare this addEmployee() method with
 * EmployeeHibernateStyleDao.addEmployee() below (plain Hibernate) and
 * with the JPA explanation in the class-level Javadoc of Employee.java.
 *
 * Spring Data JPA:
 *   - Does NOT implement JPA itself; it delegates to a JPA provider
 *     (Hibernate, in this project) to do the actual persistence work.
 *   - Removes the boilerplate of opening a Session/EntityManager,
 *     starting a transaction, committing/rolling back and closing the
 *     session - @Transactional + the repository call is all that is
 *     needed.
 *   - @Transactional here causes Spring to open a persistence context,
 *     start a transaction before the method executes, and commit it
 *     (or roll it back on exception) after the method returns -
 *     exactly what the try/tx.commit()/tx.rollback()/finally block
 *     does manually in the Hibernate-style DAO.
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}
