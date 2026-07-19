package com.cognizant.common.dto;

import java.util.Objects;

/**
 * Data Transfer Object representing a simple greeting message.
 * <p>
 * Produced by the {@code greet-service} module and returned to any
 * caller (directly, or via the API Gateway).
 * </p>
 */
public class GreetingDto {

    /** The greeting text, e.g. "Hello World". */
    private String message;

    /**
     * Default no-argument constructor.
     * Required for JSON (de)serialization frameworks such as Jackson.
     */
    public GreetingDto() {
    }

    /**
     * Fully parameterized constructor.
     *
     * @param message the greeting text
     */
    public GreetingDto(String message) {
        this.message = message;
    }

    /**
     * @return the greeting text
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the greeting text to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GreetingDto)) {
            return false;
        }
        GreetingDto that = (GreetingDto) o;
        return Objects.equals(message, that.message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GreetingDto{" + "message='" + message + '\'' + '}';
    }
}
