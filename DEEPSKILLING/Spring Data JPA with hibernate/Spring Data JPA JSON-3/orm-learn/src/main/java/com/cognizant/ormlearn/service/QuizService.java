package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.exception.ResourceNotFoundException;
import com.cognizant.ormlearn.model.Attempt;
import com.cognizant.ormlearn.model.AttemptOption;
import com.cognizant.ormlearn.model.AttemptQuestion;
import com.cognizant.ormlearn.repository.AttemptRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizService {

    private static final Logger logger = LoggerFactory.getLogger(QuizService.class);

    private final AttemptRepository attemptRepository;

    /**
     * HANDS-ON 3 :: Prints the quiz result for a given user + attempt,
     * walking the fetch-joined graph:
     *   User -> Attempt -> AttemptQuestion -> Question
     *        -> AttemptOption -> Option
     */
    public Attempt getQuizResult(int userId, int attemptId) {
        logger.info("Fetching quiz result for userId={}, attemptId={} using multi-level JOIN FETCH", userId, attemptId);
        Attempt attempt = attemptRepository.getQuizResult(userId, attemptId);
        if (attempt == null) {
            throw new ResourceNotFoundException("Attempt not found for userId=" + userId + ", attemptId=" + attemptId);
        }
        printQuizOutput(attempt);
        return attempt;
    }

    private void printQuizOutput(Attempt attempt) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n================ QUIZ RESULT ================\n");
        sb.append("User      : ").append(attempt.getUser().getUsername()).append("\n");
        sb.append("Attempt ID: ").append(attempt.getAttemptId()).append("\n");
        sb.append("Date      : ").append(attempt.getAttemptDate()).append("\n");
        sb.append("Score     : ").append(attempt.getScore()).append("\n");
        sb.append("-----------------------------------------------\n");

        int qNo = 1;
        for (AttemptQuestion aq : attempt.getAttemptQuestions()) {
            sb.append(qNo++).append(". ").append(aq.getQuestion().getQuestionText()).append("\n");
            for (AttemptOption ao : aq.getAttemptOptions()) {
                String marker = Boolean.TRUE.equals(ao.getOption().getCorrect()) ? "[CORRECT]" : "[CHOSEN]";
                sb.append("     -> ").append(ao.getOption().getOptionText()).append(" ").append(marker).append("\n");
            }
        }
        sb.append("================================================\n");
        logger.info(sb.toString());
    }
}
