package com.cognizant.springlearn.model;

/**
 * Country model class.
 *
 * Represents a country with its ISO code, name, capital, and population.
 * Instances of this class are configured as Spring beans via XML
 * bean configuration (see resources/country.xml) and are automatically
 * converted to JSON by Spring's built-in Jackson message converter
 * whenever returned from a @RestController method.
 */
public class Country {

    /** ISO 3166-1 alpha-2 country code, e.g. "IN" for India. */
    private String code;

    /** Full country name, e.g. "India". */
    private String name;

    /** Capital city of the country. */
    private String capital;

    /** Population of the country. */
    private long population;

    /**
     * Default no-argument constructor.
     * Required by Spring's XML bean configuration (property-based injection)
     * and by Jackson for JSON deserialization.
     */
    public Country() {
    }

    /**
     * All-argument constructor for convenient programmatic creation.
     *
     * @param code       ISO country code
     * @param name       country name
     * @param capital    capital city
     * @param population population count
     */
    public Country(String code, String name, String capital, long population) {
        this.code = code;
        this.name = name;
        this.capital = capital;
        this.population = population;
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

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    /**
     * Human-readable representation, useful for logging (Exercise 7).
     */
    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", population=" + population +
                '}';
    }
}
