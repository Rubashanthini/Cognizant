package com.cognizant.springlearn.service;

import com.cognizant.springlearn.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * BeanScopeService.java
 *
 * Exercise 5: Demonstrates the difference between Spring's default Singleton
 * bean scope and the Prototype bean scope, both configured in country.xml.
 *
 * Singleton (default scope / explicit scope="singleton"):
 *   - The Spring container creates exactly ONE instance of the bean per
 *     container, and every call to context.getBean() returns a reference
 *     to that SAME instance. The constructor is invoked only once.
 *
 * Prototype (scope="prototype"):
 *   - The Spring container creates a NEW instance every time the bean is
 *     requested via context.getBean(). The constructor is invoked on
 *     every single retrieval.
 */
public class BeanScopeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanScopeService.class);

    /**
     * Retrieves the singleton and prototype beans multiple times from the
     * ApplicationContext and logs whether the references are identical,
     * demonstrating the difference in scope behaviour.
     */
    public void demonstrateBeanScope() {
        LOGGER.info("START - demonstrateBeanScope()");

        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        LOGGER.debug("DEBUG - ApplicationContext loaded from country.xml");

        // ---------------------------------------------------------------
        // Singleton scope demonstration
        // ---------------------------------------------------------------
        LOGGER.info("--- Singleton Scope Demonstration ---");
        Country singleton1 = (Country) context.getBean("singletonCountry");
        Country singleton2 = (Country) context.getBean("singletonCountry");
        Country singleton3 = (Country) context.getBean("singletonCountry");

        LOGGER.info("singleton1 hashCode: {}", System.identityHashCode(singleton1));
        LOGGER.info("singleton2 hashCode: {}", System.identityHashCode(singleton2));
        LOGGER.info("singleton3 hashCode: {}", System.identityHashCode(singleton3));
        LOGGER.info("Are singleton1 and singleton2 the SAME instance? {}", (singleton1 == singleton2));
        LOGGER.info("Are singleton2 and singleton3 the SAME instance? {}", (singleton2 == singleton3));
        LOGGER.info("Singleton scope => constructor invoked only ONCE, same instance reused for every getBean() call.");

        // ---------------------------------------------------------------
        // Prototype scope demonstration
        // ---------------------------------------------------------------
        LOGGER.info("--- Prototype Scope Demonstration ---");
        Country prototype1 = (Country) context.getBean("prototypeCountry");
        Country prototype2 = (Country) context.getBean("prototypeCountry");
        Country prototype3 = (Country) context.getBean("prototypeCountry");

        LOGGER.info("prototype1 hashCode: {}", System.identityHashCode(prototype1));
        LOGGER.info("prototype2 hashCode: {}", System.identityHashCode(prototype2));
        LOGGER.info("prototype3 hashCode: {}", System.identityHashCode(prototype3));
        LOGGER.info("Are prototype1 and prototype2 the SAME instance? {}", (prototype1 == prototype2));
        LOGGER.info("Are prototype2 and prototype3 the SAME instance? {}", (prototype2 == prototype3));
        LOGGER.info("Prototype scope => constructor invoked EVERY TIME getBean() is called, a new instance is returned each time.");

        ((ClassPathXmlApplicationContext) context).close();

        LOGGER.info("END - demonstrateBeanScope()");
    }
}
