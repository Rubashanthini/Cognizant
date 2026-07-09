package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller exposing country-related endpoints.
 *
 * Implements:
 *  - Exercise 3: GET /country          -> returns the single "india" bean
 *  - Exercise 4: GET /countries        -> returns the full countryList bean
 *  - Exercise 5: GET /countries/{code} -> returns a country matched by code (case-insensitive)
 *
 * Spring automatically converts the returned Country / List&lt;Country&gt;
 * objects into JSON using the Jackson library included with
 * spring-boot-starter-web, since @RestController implies @ResponseBody.
 */
@RestController
public class CountryController {

    /** SLF4J Logger for this controller. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    /** The single "india" Country bean, injected from country.xml. */
    private final Country india;

    /** Service used to look up countries by code (Exercise 5). */
    private final CountryService countryService;

    /**
     * Constructor-based injection of the india bean and the CountryService.
     *
     * @param india          the India Country bean, defined in country.xml
     * @param countryService service used for country lookups
     */
    public CountryController(@Qualifier("india") Country india, CountryService countryService) {
        this.india = india;
        this.countryService = countryService;
    }

    /**
     * Handles GET /country requests.
     * Returns the India Country bean, loaded from country.xml.
     *
     * @return the India Country object, auto-converted to JSON
     */
    @GetMapping("/country")
    public Country getCountry() {
        LOGGER.info("START - getCountry()");
        LOGGER.debug("Returning india bean: {}", india);
        LOGGER.info("END - getCountry()");
        return india;
    }

    /**
     * Handles GET /countries requests.
     * Returns the full list of countries loaded from country.xml.
     *
     * @return list of all Country objects, auto-converted to JSON array
     */
    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        LOGGER.info("START - getAllCountries()");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("Returning {} countries", countries.size());
        LOGGER.info("END - getAllCountries()");
        return countries;
    }

    /**
     * Handles GET /countries/{code} requests.
     * Performs a case-insensitive lookup of a country by its code.
     *
     * @param code the country code path variable, e.g. "in", "IN", "In"
     * @return the matching Country object
     */
    @GetMapping("/countries/{code}")
    public Country getCountryByCode(@PathVariable String code) {
        LOGGER.info("START - getCountryByCode()");
        LOGGER.debug("Looking up country for code: {}", code);
        Country result = countryService.getCountry(code);
        LOGGER.debug("Match found: {}", result);
        LOGGER.info("END - getCountryByCode()");
        return result;
    }
}
