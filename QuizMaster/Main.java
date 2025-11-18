
package com.quizmaster;

import com.quizmaster.core.QuizEngine;
import com.quizmaster.services.FileQuestionService;

public class Main {
    public static void main(String[] args) {

        String filePath = "questions.txt";

        QuizEngine engine = new QuizEngine(new FileQuestionService(filePath));
        engine.startQuiz();
    }
}
