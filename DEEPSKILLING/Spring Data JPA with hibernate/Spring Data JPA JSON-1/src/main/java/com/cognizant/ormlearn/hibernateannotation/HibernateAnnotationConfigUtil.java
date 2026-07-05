package com.cognizant.ormlearn.hibernateannotation;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hands-on 3: builds and exposes a Hibernate SessionFactory configured
 * from hibernateannotation/hibernate.cfg.xml, which registers the
 * annotated Employee class directly (no separate .hbm.xml file).
 */
public final class HibernateAnnotationConfigUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateAnnotationConfigUtil.class);

    private static SessionFactory sessionFactory;

    private HibernateAnnotationConfigUtil() {
    }

    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.configure("hibernateannotation/hibernate.cfg.xml");

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                LOGGER.info("Hibernate Annotation Config SessionFactory created successfully");
            } catch (Exception e) {
                LOGGER.error("SessionFactory creation failed", e);
                throw new ExceptionInInitializerError(e);
            }
        }
        return sessionFactory;
    }
}
