package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Integer> {
}
