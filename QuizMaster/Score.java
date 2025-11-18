package com.quizmaster.models;

public class Score {
    private int correctCount;
    private int wrongCount;

    public void incrementCorrect() { correctCount++; }
    public void incrementWrong() { wrongCount++; }

    public int getCorrectCount() { return correctCount; }
    public int getWrongCount() { return wrongCount; }

    public int getTotalScore() { return correctCount * 10; }
}
