package com.cognizant.ormlearn.hibernateannotation;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hands-on 3: Hibernate Annotation Configuration - CRUD example.
 *
 * Functionally identical workflow to Hands-on 2's EmployeeXmlDao
 * (SessionFactory -> Session -> Transaction -> save/get/update/delete),
 * the only difference being that the Employee entity here is mapped via
 * annotations rather than an external .hbm.xml file.
 */
public class EmployeeAnnotationDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeAnnotationDao.class);

    private final SessionFactory factory = HibernateAnnotationConfigUtil.getSessionFactory();

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
