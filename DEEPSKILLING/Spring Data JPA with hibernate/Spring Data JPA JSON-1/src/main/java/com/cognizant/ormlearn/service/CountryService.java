package com.cognizant.ormlearn.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

/**
 * Service class implementing all Country related business operations.
 *
 * Hands-on 1 : getAllCountries()
 * Hands-on 6 : findCountryByCode()
 * Hands-on 7 : addCountry()
 * Hands-on 8 : updateCountry()
 * Hands-on 9 : deleteCountry()
 * Hands-on 5 : searchCountry() (partial name search)
 *
 * @Service        marks this as a Spring-managed service bean
 * @Transactional  Spring opens a Hibernate Session and manages the
 *                 transaction boundary (begin/commit/rollback) for us
 *                 automatically for the duration of each method call.
 */
@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    @Autowired
    private CountryRepository countryRepository;

    /**
     * Hands-on 1: Get all countries.
     */
    @Transactional
    public List<Country> getAllCountries() {
        LOGGER.info("Start - getAllCountries");
        List<Country> countries = countryRepository.findAll();
        LOGGER.debug("countries={}", countries);
        LOGGER.info("End - getAllCountries");
        return countries;
    }

    /**
     * Hands-on 6: Find a country based on country code.
     * Throws CountryNotFoundException when no matching record exists.
     */
    @Transactional
    public Country findCountryByCode(String countryCode) throws CountryNotFoundException {
        LOGGER.info("Start - findCountryByCode");

        Optional<Country> result = countryRepository.findById(countryCode);

        if (!result.isPresent()) {
            LOGGER.error("Country not found for code={}", countryCode);
            throw new CountryNotFoundException("Country not found for code: " + countryCode);
        }

        Country country = result.get();
        LOGGER.debug("Country:{}", country);
        LOGGER.info("End - findCountryByCode");
        return country;
    }

    /**
     * Convenience alias used by the REST controller / getCountry() naming
     * requested in the exercise; delegates to findCountryByCode().
     */
    @Transactional
    public Country getCountry(String countryCode) throws CountryNotFoundException {
        return findCountryByCode(countryCode);
    }

    /**
     * Hands-on 7: Add a new country.
     */
    @Transactional
    public void addCountry(Country country) {
        LOGGER.info("Start - addCountry");
        countryRepository.save(country);
        LOGGER.debug("Added country={}", country);
        LOGGER.info("End - addCountry");
    }

    /**
     * Hands-on 8: Update a country's name based on its code.
     */
    @Transactional
    public void updateCountry(String code, String name) throws CountryNotFoundException {
        LOGGER.info("Start - updateCountry");

        Optional<Country> result = countryRepository.findById(code);
        if (!result.isPresent()) {
            LOGGER.error("Country not found for code={}", code);
            throw new CountryNotFoundException("Country not found for code: " + code);
        }

        Country country = result.get();
        country.setName(name);
        countryRepository.save(country);

        LOGGER.debug("Updated country={}", country);
        LOGGER.info("End - updateCountry");
    }

    /**
     * Hands-on 9: Delete a country based on its code.
     */
    @Transactional
    public void deleteCountry(String code) {
        LOGGER.info("Start - deleteCountry");
        countryRepository.deleteById(code);
        LOGGER.debug("Deleted country with code={}", code);
        LOGGER.info("End - deleteCountry");
    }

    /**
     * Hands-on 5: Search countries using a partial name (case-insensitive).
     */
    @Transactional
    public List<Country> searchCountry(String partialName) {
        LOGGER.info("Start - searchCountry");
        List<Country> countries = countryRepository.findByNameContainingIgnoreCase(partialName);
        LOGGER.debug("Search result for '{}' = {}", partialName, countries);
        LOGGER.info("End - searchCountry");
        return countries;
    }
}
