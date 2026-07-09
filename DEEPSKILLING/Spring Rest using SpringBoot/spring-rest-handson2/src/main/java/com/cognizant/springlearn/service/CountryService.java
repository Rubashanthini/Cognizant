package com.cognizant.springlearn.service;

import com.cognizant.springlearn.exception.CountryNotFoundException;
import com.cognizant.springlearn.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer responsible for country lookup business logic.
 *
 * Implements Exercise 5 (Return Country by Code) using Java 8 Streams
 * for a case-insensitive search, and Exercise 6 (Exception Handling)
 * by throwing CountryNotFoundException when no match is found.
 */
@Service
public class CountryService {

    /** SLF4J Logger for this service - System.out.println() is never used per Exercise 7. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    /**
     * The full list of countries, injected from the "countryList" bean
     * defined in resources/country.xml via @Qualifier.
     */
    private final List<Country> countryList;

    /**
     * Constructor-based injection of the countryList bean.
     *
     * @param countryList list of Country beans loaded from country.xml
     */
    public CountryService(@Qualifier("countryList") List<Country> countryList) {
        this.countryList = countryList;
    }

    /**
     * Retrieves a Country by its ISO code, performing a case-insensitive search.
     *
     * @param code the country code supplied by the client (any case)
     * @return the matching Country object
     * @throws CountryNotFoundException if no country matches the given code
     */
    public Country getCountry(String code) {
        LOGGER.info("START - getCountry()");
        LOGGER.debug("Searching for country with code: {}", code);

        Country result = countryList.stream()
                .filter(country -> country.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> {
                    LOGGER.debug("No country found matching code: {}", code);
                    return new CountryNotFoundException(code);
                });

        LOGGER.debug("Found country: {}", result);
        LOGGER.info("END - getCountry()");
        return result;
    }

    /**
     * Returns the complete list of countries.
     *
     * @return list of all Country objects
     */
    public List<Country> getAllCountries() {
        LOGGER.info("START - getAllCountries()");
        LOGGER.debug("Returning countryList of size: {}", countryList.size());
        LOGGER.info("END - getAllCountries()");
        return countryList;
    }
}
