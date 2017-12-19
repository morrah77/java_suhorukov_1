package com.jcourse.lazar.exceptionGenerator.core;

import org.apache.logging.log4j.core.util.ArrayUtils;

import java.util.ArrayList;

public class CoreExceptionGenerator implements ExceptionGenerator {
    public void generateNullPointerException() throws NullPointerException {
        ArrayList<Object> exceptingObject = new ArrayList<Object>();
        int exceptingIndex = exceptingObject.indexOf(new Object());
    }
    public void generateClassCastException() throws ClassCastException {
        Object exceptingObject = new Object();
        ArrayList<Object> al = (ArrayList<Object>)exceptingObject;
    }
    public void generateNumberFormatException() throws NumberFormatException {
        String exceptingString = "exceptingString";
        Integer i = Integer.valueOf(exceptingString);
    }
    public void generateStackOverflowError() throws StackOverflowError {
        recursiveAction(1);
    }
    public void generateOutOfMemoryError() throws OutOfMemoryError {
        int exceptingIntegerArray[] = new int[Integer.MAX_VALUE];
        while (true) {
            exceptingIntegerArray = exceptingIntegerArray.clone();
        }
    }
    public void generateMyException(String message) throws MyException {
        throw new MyException(message);
    }

    private void recursiveAction(int arg) {
        arg+=1;
        recursiveAction(arg);
    }
}
