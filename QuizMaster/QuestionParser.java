package com.quizmaster.core;

import com.quizmaster.exceptions.QuestionFormatException;
import com.quizmaster.models.Question;

public class QuestionParser {

    public static Question parse(String line) throws QuestionFormatException {
        String[] parts = line.split("\\|");
        if (parts.length != 6) {
            throw new QuestionFormatException("Incorrect question format: " + line);
        }

        return new Question(
                parts[0].trim(),
                parts[1].trim(),
                parts[2].trim(),
                parts[3].trim(),
                parts[4].trim(),
                parts[5].trim().toUpperCase()
        );
    }
}
