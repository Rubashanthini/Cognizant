package com.cognizant.ormlearn.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

/**
 * REST Controller exposing Country management operations over HTTP.
 *
 * Endpoints:
 *   GET    /countries
 *   GET    /countries/{code}
 *   POST   /countries
 *   PUT    /countries/{code}
 *   DELETE /countries/{code}
 *   GET    /countries/search/{name}
 */
@RestController
@RequestMapping("/countries")
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    /**
     * GET /countries - returns all countries.
     */
    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        LOGGER.info("GET /countries");
        List<Country> countries = countryService.getAllCountries();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    /**
     * GET /countries/{code} - returns a single country by code.
     */
    @GetMapping("/{code}")
    public ResponseEntity<?> getCountryByCode(@PathVariable String code) {
        LOGGER.info("GET /countries/{}", code);
        try {
            Country country = countryService.getCountry(code);
            return new ResponseEntity<>(country, HttpStatus.OK);
        } catch (CountryNotFoundException ex) {
            LOGGER.error("Country not found: {}", code);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * POST /countries - adds a new country. Body: {"code":"XX","name":"Country Name"}
     */
    @PostMapping
    public ResponseEntity<String> addCountry(@RequestBody Country country) {
        LOGGER.info("POST /countries body={}", country);
        countryService.addCountry(country);
        return new ResponseEntity<>("Country added successfully", HttpStatus.CREATED);
    }

    /**
     * PUT /countries/{code} - updates the country name for the given code.
     * Accepts the new name either as a request param (?name=) or in the body.
     */
    @PutMapping("/{code}")
    public ResponseEntity<String> updateCountry(@PathVariable String code,
                                                 @RequestParam(required = false) String name,
                                                 @RequestBody(required = false) Country body) {
        LOGGER.info("PUT /countries/{}", code);
        String newName = (name != null) ? name : (body != null ? body.getName() : null);

        if (newName == null || newName.trim().isEmpty()) {
            return new ResponseEntity<>("Country name is required", HttpStatus.BAD_REQUEST);
        }

        try {
            countryService.updateCountry(code, newName);
            return new ResponseEntity<>("Country updated successfully", HttpStatus.OK);
        } catch (CountryNotFoundException ex) {
            LOGGER.error("Country not found: {}", code);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * DELETE /countries/{code} - deletes the country with the given code.
     */
    @DeleteMapping("/{code}")
    public ResponseEntity<String> deleteCountry(@PathVariable String code) {
        LOGGER.info("DELETE /countries/{}", code);
        countryService.deleteCountry(code);
        return new ResponseEntity<>("Country deleted successfully", HttpStatus.OK);
    }

    /**
     * GET /countries/search/{name} - search countries by partial name (case-insensitive).
     */
    @GetMapping("/search/{name}")
    public ResponseEntity<List<Country>> searchCountries(@PathVariable String name) {
        LOGGER.info("GET /countries/search/{}", name);
        List<Country> countries = countryService.searchCountry(name);
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }
}
