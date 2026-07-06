package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> searchByNameContaining(String text) {
        return countryRepository.findByCountryNameContaining(text);
    }

    public List<Country> searchByNameContainingOrderByAsc(String text) {
        return countryRepository.findByCountryNameContainingOrderByCountryNameAsc(text);
    }

    public List<Country> searchByStartingLetter(String letter) {
        return countryRepository.findByCountryNameStartingWith(letter);
    }
}
