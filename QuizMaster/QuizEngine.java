package com.quizmaster.core;

import com.quizmaster.models.Question;
import com.quizmaster.services.QuestionService;
import com.quizmaster.utils.ConsoleUI;

import java.util.Collections;
import java.util.List;

public class QuizEngine {

    private QuestionService questionService;
    private ScoreManager scoreManager;

    public QuizEngine(QuestionService questionService) {
        this.questionService = questionService;
        this.scoreManager = new ScoreManager();
    }

    public void startQuiz() {
        List<Question> questions = questionService.loadQuestions();

        if (questions.isEmpty()) {
            System.out.println("No questions available!");
            return;
        }

        Collections.shuffle(questions);

        System.out.println("===== Welcome to QuizMaster =====");

        for (Question q : questions) {
            String userAns = ConsoleUI.askQuestion(q);

            if (userAns.equals(q.getCorrectOption())) {
                System.out.println("✔ Correct!");
                scoreManager.correct();
            } else {
                System.out.println("✘ Wrong! Correct: " + q.getCorrectOption());
                scoreManager.wrong();
            }
        }

        scoreManager.printFinalScore(questions.size());
    }
}
