package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * ==========================================================
 * HANDS-ON 3 :: Quiz Application
 * ==========================================================
 * Fetches an Attempt with its full object graph in a single query:
 *
 *   User -> Attempt -> AttemptQuestion -> Question
 *        -> AttemptOption -> Option
 *
 * joined in exactly that order, filtered by userId and attemptId.
 */
public interface AttemptRepository extends JpaRepository<Attempt, Integer> {

    @Query("SELECT DISTINCT a FROM Attempt a " +
            "JOIN FETCH a.user u " +
            "JOIN FETCH a.attemptQuestions aq " +
            "JOIN FETCH aq.question q " +
            "JOIN FETCH aq.attemptOptions ao " +
            "JOIN FETCH ao.option o " +
            "WHERE u.userId = :userId AND a.attemptId = :attemptId")
    Attempt getQuizResult(@Param("userId") int userId, @Param("attemptId") int attemptId);

    @Query("SELECT DISTINCT a FROM Attempt a JOIN FETCH a.user u WHERE u.userId = :userId")
    List<Attempt> getAttemptsByUser(@Param("userId") int userId);
}
