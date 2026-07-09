package com.cognizant.springlearn.service;

import com.cognizant.springlearn.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * CountryService.java
 *
 * Exercise 4: Loads the "country" bean defined in country.xml using a
 * ClassPathXmlApplicationContext and displays its details via the Logger.
 */
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    /**
     * Loads the Spring XML configuration, retrieves the Country bean,
     * and logs its details.
     */
    public void displayCountry() {
        LOGGER.info("START - displayCountry()");

        // Create ApplicationContext from the country.xml classpath resource
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        LOGGER.debug("DEBUG - ApplicationContext loaded from country.xml");

        // Retrieve the Country bean defined in the XML file
        Country country = (Country) context.getBean("country");
        LOGGER.debug("DEBUG - Retrieved bean 'country' of type: {}", country.getClass().getName());

        LOGGER.info("Country details loaded from XML: {}", country);

        ((ClassPathXmlApplicationContext) context).close();

        LOGGER.info("END - displayCountry()");
    }
}
