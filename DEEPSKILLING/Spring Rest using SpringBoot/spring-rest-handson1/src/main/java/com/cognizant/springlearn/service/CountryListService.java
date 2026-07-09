package com.cognizant.springlearn.service;

import com.cognizant.springlearn.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * CountryListService.java
 *
 * Exercise 6: Loads the "countryList" bean (a java.util.ArrayList of Country
 * beans built using &lt;list&gt; and &lt;ref&gt; elements) defined in
 * country.xml, and logs each Country in the list.
 */
public class CountryListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryListService.class);

    /**
     * Loads the Spring XML configuration, retrieves the countryList bean,
     * and logs every Country contained within it.
     */
    @SuppressWarnings("unchecked")
    public void displayCountries() {
        LOGGER.info("START - displayCountries()");

        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        LOGGER.debug("DEBUG - ApplicationContext loaded from country.xml");

        // Retrieve the ArrayList<Country> bean defined via <list> and <ref> in the XML file
        List<Country> countryList = (List<Country>) context.getBean("countryList");
        LOGGER.debug("DEBUG - Retrieved bean 'countryList' with size: {}", countryList.size());

        LOGGER.info("Displaying all countries loaded from countryList bean:");
        for (Country country : countryList) {
            LOGGER.info("Country -> {}", country);
        }

        ((ClassPathXmlApplicationContext) context).close();

        LOGGER.info("END - displayCountries()");
    }
}
