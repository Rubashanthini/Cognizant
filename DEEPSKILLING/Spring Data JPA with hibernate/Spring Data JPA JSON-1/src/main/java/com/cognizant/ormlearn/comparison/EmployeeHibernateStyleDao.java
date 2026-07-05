package com.cognizant.ormlearn.comparison;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hands-on 4: "plain Hibernate" version of "add an employee" - mirrors
 * the exact pattern shown in the exercise document:
 *
 *   public Integer addEmployee(Employee employee){
 *      Session session = factory.openSession();
 *      Transaction tx = null;
 *      Integer employeeID = null;
 *      try {
 *         tx = session.beginTransaction();
 *         employeeID = (Integer) session.save(employee);
 *         tx.commit();
 *      } catch (HibernateException e) {
 *         if (tx != null) tx.rollback();
 *         e.printStackTrace();
 *      } finally {
 *         session.close();
 *      }
 *      return employeeID;
 *   }
 *
 * Compare this class - which manually opens a Session, begins a
 * Transaction, commits/rolls it back, and closes the Session - with
 * EmployeeService.addEmployee() (Spring Data JPA), which achieves the
 * identical database result in a single line thanks to @Transactional
 * and the auto-generated EmployeeRepository.
 */
public class EmployeeHibernateStyleDao {

    private final SessionFactory factory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure("comparison/hibernate.cfg.xml");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    /**
     * Method to CREATE an employee in the database - plain Hibernate style.
     */
    public Integer addEmployee(Employee employee) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeId = null;

        try {
            tx = session.beginTransaction();
            employeeId = (Integer) session.save(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return employeeId;
    }
}
