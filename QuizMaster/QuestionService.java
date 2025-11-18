package com.quizmaster.services;

import com.quizmaster.models.Question;
import java.util.List;

public interface QuestionService {
    List<Question> loadQuestions();
}
