package com.cognizant.ormlearn.service.exception;

/**
 * Hands-on 6: Custom checked exception thrown when a country lookup by
 * code does not find a matching record in the database.
 */
public class CountryNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public CountryNotFoundException(String message) {
        super(message);
    }

    public CountryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
