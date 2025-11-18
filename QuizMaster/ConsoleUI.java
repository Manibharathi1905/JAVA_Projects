package com.quizmaster.utils;

import com.quizmaster.models.Question;

import java.util.Scanner;

public class ConsoleUI {

    private static Scanner scanner = new Scanner(System.in);

    public static String askQuestion(Question q) {
        System.out.println("\n" + q.getQuestion());
        System.out.println("A) " + q.getOptionA());
        System.out.println("B) " + q.getOptionB());
        System.out.println("C) " + q.getOptionC());
        System.out.println("D) " + q.getOptionD());
        System.out.print("Your Answer: ");

        return scanner.next().toUpperCase();
    }
}
