package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {

    // HANDS-ON 1: Search countries containing text
    List<Country> findByCountryNameContaining(String text);

    // HANDS-ON 1: Search countries containing text in ascending order
    List<Country> findByCountryNameContainingOrderByCountryNameAsc(String text);

    // HANDS-ON 1: Search countries starting with a specific alphabet
    List<Country> findByCountryNameStartingWith(String letter);
}
