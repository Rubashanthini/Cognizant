package com.cognizant.employeesystem.exception;

/**
 * Thrown when a requested Employee or Department cannot be found by id
 * (or another unique identifier). Translated into an HTTP 404 response
 * by {@link GlobalExceptionHandler}.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
