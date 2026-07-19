package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.AttemptQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttemptQuestionRepository extends JpaRepository<AttemptQuestion, Integer> {
}
