package com.cognizant.springlearn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception thrown whenever a requested country code
 * does not match any country available in the countryList bean.
 *
 * The @ResponseStatus annotation instructs Spring MVC to automatically
 * translate this exception into an HTTP 404 (Not Found) response,
 * with the given reason phrase, whenever it propagates out of a
 * controller method uncaught. This satisfies Exercise 6
 * (Exception Handling) and produces a proper JSON error response
 * via Spring Boot's default /error handling mechanism.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Country not found")
public class CountryNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new exception with the invalid code embedded in the message,
     * useful for debug logging even though the client only sees the reason phrase.
     *
     * @param code the invalid/unmatched country code supplied by the client
     */
    public CountryNotFoundException(String code) {
        super("Country not found for code: " + code);
    }
}
