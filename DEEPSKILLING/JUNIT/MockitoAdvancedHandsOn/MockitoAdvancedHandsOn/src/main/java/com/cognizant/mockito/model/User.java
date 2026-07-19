package com.cognizant.mockito.model;

import java.util.Objects;

/**
 * User model class representing a simple domain entity used throughout
 * the Mockito Advanced Hands-On exercises.
 * <p>
 * This class follows the standard JavaBean convention with a no-argument
 * constructor, a parameterized constructor, getters, setters, and
 * overridden {@code equals()}, {@code hashCode()}, and {@code toString()}
 * methods.
 * </p>
 *
 * @author Cognizant Digital Nurture 5.0
 */
public class User {

    /** Unique identifier of the user. */
    private Long id;

    /** Full name of the user. */
    private String name;

    /** Email address of the user. */
    private String email;

    /**
     * Default no-argument constructor.
     */
    public User() {
    }

    /**
     * Parameterized constructor to create a fully initialized {@code User}.
     *
     * @param id    the unique identifier of the user
     * @param name  the full name of the user
     * @param email the email address of the user
     */
    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * Returns the user's unique identifier.
     *
     * @return the user id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the user's unique identifier.
     *
     * @param id the user id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the user's full name.
     *
     * @return the user name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's full name.
     *
     * @param name the user name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the user's email address.
     *
     * @return the user email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email the user email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id)
                && Objects.equals(name, user.name)
                && Objects.equals(email, user.email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", email='" + email + '\''
                + '}';
    }
}
