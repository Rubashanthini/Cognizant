package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Country.java
 *
 * Exercise 4: Simple POJO representing a Country, loaded as a Spring bean
 * from the country.xml XML configuration file.
 *
 * Fields:
 *  - code : ISO country code (e.g. "IN")
 *  - name : Full country name (e.g. "India")
 *
 * Debug logs have been added inside the constructor, getters and setters
 * as required by the hands-on specification, so that every access to this
 * bean is traceable in the application logs.
 */
public class Country {

    private static final Logger LOGGER = LoggerFactory.getLogger(Country.class);

    private String code;
    private String name;

    /**
     * Default (no-argument) constructor.
     * Required by Spring for certain instantiation strategies and general JavaBean convention.
     */
    public Country() {
        LOGGER.debug("START - Country() default constructor invoked");
        LOGGER.debug("END - Country() default constructor completed");
    }

    /**
     * Parameterized constructor used by Spring XML configuration via <constructor-arg>.
     *
     * @param code the ISO country code
     * @param name the full country name
     */
    public Country(String code, String name) {
        LOGGER.debug("START - Country(String, String) constructor invoked with code={}, name={}", code, name);
        this.code = code;
        this.name = name;
        LOGGER.debug("END - Country(String, String) constructor completed for code={}", code);
    }

    /**
     * Getter for code.
     *
     * @return the country code
     */
    public String getCode() {
        LOGGER.debug("START - getCode() invoked");
        LOGGER.debug("END - getCode() returning value: {}", code);
        return code;
    }

    /**
     * Setter for code.
     *
     * @param code the country code to set
     */
    public void setCode(String code) {
        LOGGER.debug("START - setCode() invoked with value: {}", code);
        this.code = code;
        LOGGER.debug("END - setCode() completed");
    }

    /**
     * Getter for name.
     *
     * @return the country name
     */
    public String getName() {
        LOGGER.debug("START - getName() invoked");
        LOGGER.debug("END - getName() returning value: {}", name);
        return name;
    }

    /**
     * Setter for name.
     *
     * @param name the country name to set
     */
    public void setName(String name) {
        LOGGER.debug("START - setName() invoked with value: {}", name);
        this.name = name;
        LOGGER.debug("END - setName() completed");
    }

    /**
     * Returns a human-readable String representation of this Country instance.
     *
     * @return formatted string containing code and name
     */
    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
