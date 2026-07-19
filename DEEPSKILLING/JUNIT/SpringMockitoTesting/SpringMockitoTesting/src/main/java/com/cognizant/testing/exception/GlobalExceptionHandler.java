package com.cognizant.testing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Global exception handler for the application.
 *
 * <p>Centralizes exception handling across all {@code @RestController}
 * classes, converting exceptions into consistent, well-structured
 * JSON error responses.</p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link NoSuchElementException}, typically thrown when a
     * requested resource cannot be found.
     *
     * @param ex the exception that was thrown
     * @return a {@link ResponseEntity} containing error details and
     *         HTTP 404 (Not Found) status
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> handleNoSuchElementException(NoSuchElementException ex) {
        Map<String, Object> errorBody = buildErrorBody(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles any other uncaught {@link Exception} thrown by the application.
     *
     * @param ex the exception that was thrown
     * @return a {@link ResponseEntity} containing error details and
     *         HTTP 500 (Internal Server Error) status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        Map<String, Object> errorBody = buildErrorBody(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Builds a structured error response body containing timestamp,
     * status code, error reason, and message.
     *
     * @param status  the HTTP status associated with the error
     * @param message the detailed error message
     * @return a map representing the structured error body
     */
    private Map<String, Object> buildErrorBody(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return body;
    }
}
