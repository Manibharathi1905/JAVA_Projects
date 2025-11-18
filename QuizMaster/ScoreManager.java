package com.quizmaster.core;

import com.quizmaster.models.Score;

public class ScoreManager {

    private Score score;

    public ScoreManager() {
        this.score = new Score();
    }

    public void correct() { score.incrementCorrect(); }
    public void wrong() { score.incrementWrong(); }

    public Score getScore() { return score; }

    public void printFinalScore(int totalQuestions) {
        System.out.println("\n======== RESULTS ========");
        System.out.println("Correct : " + score.getCorrectCount());
        System.out.println("Wrong   : " + score.getWrongCount());
        System.out.println("Score   : " + score.getTotalScore() + "/" + (totalQuestions * 10));
        System.out.println("==========================");
    }
}
