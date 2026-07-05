package com.cognizant.ormlearn.hibernatexml;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hands-on 2: Hibernate XML Configuration - CRUD example.
 *
 * Demonstrates the classic Hibernate core-object workflow:
 *   SessionFactory -> Session -> Transaction -> beginTransaction()
 *   -> commit() / rollback() -> session.save() / .get() / .delete()
 *   -> session.createQuery().list()
 *
 * This class is intentionally NOT a Spring bean - it is a plain,
 * self-contained DAO to keep the classic Hibernate workflow visible,
 * uncluttered by Spring's @Transactional abstraction (contrast with
 * Hands-on 4's Spring Data JPA equivalent).
 */
public class EmployeeXmlDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeXmlDao.class);

    private final SessionFactory factory = HibernateXmlConfigUtil.getSessionFactory();

    /**
     * CREATE: adds a new Employee row and returns the generated id.
     */
    public Integer addEmployee(Employee employee) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeId = null;

        try {
            tx = session.beginTransaction();
            employeeId = (Integer) session.save(employee);
            tx.commit();
            LOGGER.info("Employee added with id={}", employeeId);
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            LOGGER.error("addEmployee failed", e);
        } finally {
            session.close();
        }
        return employeeId;
    }

    /**
     * READ (single): fetch one Employee by id using session.get().
     */
    public Employee getEmployee(int employeeId) {
        Session session = factory.openSession();
        Employee employee = null;
        try {
            employee = session.get(Employee.class, employeeId);
        } catch (HibernateException e) {
            LOGGER.error("getEmployee failed", e);
        } finally {
            session.close();
        }
        return employee;
    }

    /**
     * READ (list): fetch all Employee rows using an HQL query.
     */
    @SuppressWarnings("unchecked")
    public List<Employee> listEmployees() {
        Session session = factory.openSession();
        List<Employee> employees = null;
        try {
            employees = session.createQuery("FROM Employee").list();
        } catch (HibernateException e) {
            LOGGER.error("listEmployees failed", e);
        } finally {
            session.close();
        }
        return employees;
    }

    /**
     * UPDATE: modifies an existing Employee's salary.
     */
    public void updateEmployeeSalary(int employeeId, int salary) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            if (employee != null) {
                employee.setSalary(salary);
                session.update(employee);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            LOGGER.error("updateEmployeeSalary failed", e);
        } finally {
            session.close();
        }
    }

    /**
     * DELETE: removes an Employee row by id.
     */
    public void deleteEmployee(int employeeId) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            if (employee != null) {
                session.delete(employee);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            LOGGER.error("deleteEmployee failed", e);
        } finally {
            session.close();
        }
    }
}
