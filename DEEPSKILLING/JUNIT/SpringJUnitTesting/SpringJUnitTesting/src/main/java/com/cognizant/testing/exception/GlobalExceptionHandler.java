package com.cognizant.testing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

/**
 * Global exception handler (Exercise 8) that intercepts exceptions thrown
 * by any controller in the application and translates them into
 * appropriate HTTP responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link NoSuchElementException}, returned whenever a requested
     * user cannot be found, by responding with HTTP 404 (Not Found).
     *
     * @param ex the exception that was thrown
     * @return HTTP 404 response with a descriptive body message
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFound(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
}
