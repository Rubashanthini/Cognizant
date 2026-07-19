package com.cognizant.ormlearn.controller;

import com.cognizant.ormlearn.model.Attempt;
import com.cognizant.ormlearn.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @GetMapping("/result/{userId}/{attemptId}")
    public Attempt getQuizResult(@PathVariable int userId, @PathVariable int attemptId) {
        return quizService.getQuizResult(userId, attemptId);
    }
}
