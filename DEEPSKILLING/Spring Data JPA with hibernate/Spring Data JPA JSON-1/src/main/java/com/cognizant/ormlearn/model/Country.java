package com.cognizant.ormlearn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Persistence class for the "country" table.
 *
 * Hands-on 1: Country Entity
 *  - @Entity   : marks this class as a JPA entity managed by Spring Data JPA / Hibernate
 *  - @Table    : maps this entity to the "country" table
 *  - @Id       : marks "code" as the primary key
 *  - @Column   : maps each field to its corresponding database column
 */
@Entity
@Table(name = "country")
public class Country {

    @Id
    @Column(name = "co_code", length = 2, nullable = false)
    private String code;

    @Column(name = "co_name", length = 50, nullable = false)
    private String name;

    public Country() {
        // Default no-arg constructor required by JPA
    }

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country [code=" + code + ", name=" + name + "]";
    }
}
