package com.cognizant.mockito.model;

/**
 * User is a simple POJO (Plain Old Java Object) that models a user entity
 * within the application. It is used by {@link com.cognizant.mockito.util.Utility}
 * to demonstrate validation logic and can be used as a data carrier in
 * service-layer operations.
 */
public class User {

    /** Unique identifier of the user. */
    private int id;

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
     * Constructs a fully initialised User instance.
     *
     * @param id    the unique identifier of the user
     * @param name  the full name of the user
     * @param email the email address of the user
     */
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        if (id != user.id) {
            return false;
        }
        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        return email != null ? email.equals(user.email) : user.email == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
