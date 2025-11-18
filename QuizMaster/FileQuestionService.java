package com.quizmaster.services;

import com.quizmaster.core.QuestionParser;
import com.quizmaster.exceptions.QuestionFormatException;
import com.quizmaster.models.Question;

import java.io.*;
import java.util.*;

public class FileQuestionService implements QuestionService {

    private String filePath;

    public FileQuestionService(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Question> loadQuestions() {
        List<Question> questions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = br.readLine()) != null) {
                try {
                    Question q = QuestionParser.parse(line);
                    questions.add(q);
                } catch (QuestionFormatException e) {
                    System.out.println("Skipping invalid question: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }

        return questions;
    }
}
