package com.jcourse.lazar;

import java.util.Scanner;

public class NumberGuesserGameClass {
    private static final String MESSAGE_START = "A number between 1 and %d has been made. Try to guess the number in %d attemptions.\nType your guess, press Enter, and I'll hint you is your suggestion less or more.\n";
    private static final String MESSAGE_INPUT_ERROR = "Incorrect input! Please try again.";
    private static final String MESSAGE_SUCCESS = "Congratulations!\nYou've been won.";
    private static final String MESSAGE_LOOSE = "Game over!\nThe number was %d\n";
    private static final String MESSAGE_LESS = "Less!";
    private static final String MESSAGE_MORE = "More!";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NumberGuesserCoreClass numberGuesser = new NumberGuesserCoreClass();
        String readString = "";
        int suggestion;
        System.out.printf(MESSAGE_START, NumberGuesserCoreClass.MAX_BOUND, NumberGuesserCoreClass.MAX_ATTEMPTIONS);
        // TODO (h.lazar) consider to use here a flag variable to decide weather to break or continue
        LOOP:
        while (true){
            readString = scanner.nextLine();
            try {
                suggestion = Integer.parseInt(readString);
            } catch (Exception e) {
                System.out.println(MESSAGE_INPUT_ERROR);
                continue LOOP;
            }
            NumberGuesserCoreClass.AnswerMessage answer = numberGuesser.Guess(suggestion);
            switch (answer) {
                case LESS:
                    System.out.println(MESSAGE_LESS);
                    break;
                case MORE:
                    System.out.println(MESSAGE_MORE);
                    break;
                case EXHAUSTED:
                    System.out.printf(MESSAGE_LOOSE, numberGuesser.getNumberMade());
                    break LOOP;
                case SUCCESS:
                    System.out.println(MESSAGE_SUCCESS);
                    break LOOP;
            }
        }
    }
}
