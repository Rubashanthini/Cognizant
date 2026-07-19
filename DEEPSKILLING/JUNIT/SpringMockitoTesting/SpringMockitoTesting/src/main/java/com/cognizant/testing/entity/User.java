package com.cognizant.testing.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class representing a User in the system.
 *
 * <p>This entity is mapped to the "users" table in the H2 database
 * and is used across the service, repository, and controller layers
 * for demonstrating Mockito-based mocking in Spring tests.</p>
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * Unique identifier of the user (primary key).
     */
    @Id
    private Long id;

    /**
     * Name of the user.
     */
    private String name;

    /**
     * Email address of the user.
     */
    private String email;

    /**
     * Default no-argument constructor required by JPA.
     */
    public User() {
    }

    /**
     * Parameterized constructor to create a fully initialized User.
     *
     * @param id    unique identifier of the user
     * @param name  name of the user
     * @param email email address of the user
     */
    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * Gets the user's unique identifier.
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
     * Gets the user's name.
     *
     * @return the user name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     *
     * @param name the user name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user's email address.
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
     * Compares this User with another object for equality based on
     * id, name, and email fields.
     *
     * @param o the object to compare with
     * @return true if the objects are equal, false otherwise
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
        return java.util.Objects.equals(id, user.id)
                && java.util.Objects.equals(name, user.name)
                && java.util.Objects.equals(email, user.email);
    }

    /**
     * Generates a hash code based on id, name, and email fields.
     *
     * @return the hash code of this User instance
     */
    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, name, email);
    }

    /**
     * Returns a string representation of this User.
     *
     * @return string representation containing id, name, and email
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
