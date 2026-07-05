package com.cognizant.ormlearn.hibernatexml;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hands-on 2: builds and exposes a Hibernate SessionFactory that is
 * configured entirely from the standalone XML file
 * hibernatexml/hibernate.cfg.xml (which in turn points at the
 * Employee.hbm.xml mapping file) - this is independent from the Spring
 * Boot managed EntityManagerFactory used elsewhere in this project.
 */
public final class HibernateXmlConfigUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateXmlConfigUtil.class);

    private static SessionFactory sessionFactory;

    private HibernateXmlConfigUtil() {
    }

    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.configure("hibernatexml/hibernate.cfg.xml");

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                LOGGER.info("Hibernate XML Config SessionFactory created successfully");
            } catch (Exception e) {
                LOGGER.error("SessionFactory creation failed", e);
                throw new ExceptionInInitializerError(e);
            }
        }
        return sessionFactory;
    }
}
