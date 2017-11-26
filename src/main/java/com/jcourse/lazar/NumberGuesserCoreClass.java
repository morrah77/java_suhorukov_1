package com.jcourse.lazar;

import java.util.Random;

class NumberGuesserCoreClass {
    private int numberMade, attemptsCount;
    public static final int MAX_ATTEMPTIONS = 8;
    public static final int MAX_BOUND = 100;
    public enum AnswerMessage {
        MORE, LESS, SUCCESS, EXHAUSTED
    };

    public NumberGuesserCoreClass() {
        numberMade = new Random().nextInt(MAX_BOUND) + 1;
        attemptsCount = 0;
    }

    public AnswerMessage Guess(int suggestion) {
        attemptsCount++;
        if (attemptsCount >= MAX_ATTEMPTIONS) {
            return AnswerMessage.EXHAUSTED;
        }
        if (numberMade == suggestion) {
            return AnswerMessage.SUCCESS;
        }
        if (numberMade > suggestion) {
            return AnswerMessage.MORE;
        }
        return AnswerMessage.LESS;
    }

    public int getNumberMade() {
        return numberMade;
    }
}
