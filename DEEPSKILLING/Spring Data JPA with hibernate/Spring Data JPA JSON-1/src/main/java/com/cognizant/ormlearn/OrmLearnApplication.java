package com.cognizant.ormlearn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

/**
 * Main Spring Boot application class.
 *
 * @SpringBootApplication is a convenience annotation combining:
 *   @Configuration, @EnableAutoConfiguration and @ComponentScan
 * so Spring Boot auto-configures the application context, scans
 * this package (and sub-packages) for components, and lets us run
 * the application as a plain Java application via main().
 */
@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    private static CountryService countryService;

    public static void main(String[] args) {
        LOGGER.info("Inside main");

        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        countryService = context.getBean(CountryService.class);

        // Hands-on 1: display all countries
        testGetAllCountries();

        // Hands-on 6: find a country by code
        testFindCountryByCode();

        // Hands-on 7: add a new country
        testAddCountry();

        // Hands-on 8: update a country
        testUpdateCountry();

        // Hands-on 9: delete a country
        testDeleteCountry();
    }

    /**
     * Hands-on 1: Test method to get all countries from the service.
     */
    private static void testGetAllCountries() {
        LOGGER.info("Start");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("countries={}", countries);
        LOGGER.info("End");
    }

    /**
     * Hands-on 6: Test method to find a country based on country code
     * and verify the returned country name.
     */
    private static void testFindCountryByCode() {
        LOGGER.info("Start");
        try {
            Country country = countryService.findCountryByCode("IN");
            LOGGER.debug("Country:{}", country);

            if ("India".equals(country.getName())) {
                LOGGER.info("testFindCountryByCode PASSED");
            } else {
                LOGGER.error("testFindCountryByCode FAILED - unexpected name: {}", country.getName());
            }
        } catch (CountryNotFoundException e) {
            LOGGER.error("testFindCountryByCode FAILED", e);
        }
        LOGGER.info("End");
    }

    /**
     * Hands-on 7: Test method to add a new country then verify it was
     * persisted by fetching it back using its code.
     */
    private static void testAddCountry() {
        LOGGER.info("Start");
        try {
            Country newCountry = new Country("ZZ", "Test Land");
            countryService.addCountry(newCountry);

            Country fetched = countryService.findCountryByCode("ZZ");
            LOGGER.debug("Fetched after add: {}", fetched);

            if (fetched != null && "Test Land".equals(fetched.getName())) {
                LOGGER.info("testAddCountry PASSED");
            } else {
                LOGGER.error("testAddCountry FAILED");
            }
        } catch (CountryNotFoundException e) {
            LOGGER.error("testAddCountry FAILED - country not found after add", e);
        }
        LOGGER.info("End");
    }

    /**
     * Hands-on 8: Test method to update a country's name and verify
     * the change was persisted.
     */
    private static void testUpdateCountry() {
        LOGGER.info("Start");
        try {
            countryService.updateCountry("ZZ", "Updated Test Land");

            Country updated = countryService.findCountryByCode("ZZ");
            LOGGER.debug("Fetched after update: {}", updated);

            if (updated != null && "Updated Test Land".equals(updated.getName())) {
                LOGGER.info("testUpdateCountry PASSED");
            } else {
                LOGGER.error("testUpdateCountry FAILED");
            }
        } catch (CountryNotFoundException e) {
            LOGGER.error("testUpdateCountry FAILED", e);
        }
        LOGGER.info("End");
    }

    /**
     * Hands-on 9: Test method to delete the country added in
     * testAddCountry() and verify it can no longer be found.
     */
    private static void testDeleteCountry() {
        LOGGER.info("Start");
        countryService.deleteCountry("ZZ");

        try {
            countryService.findCountryByCode("ZZ");
            LOGGER.error("testDeleteCountry FAILED - country still exists");
        } catch (CountryNotFoundException e) {
            LOGGER.info("testDeleteCountry PASSED - country no longer found as expected");
        }
        LOGGER.info("End");
    }
}
