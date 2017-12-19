package com.jcourse.lazar.exceptionGenerator.core;

public class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
    public String getMessage() {
        return "My Exception message";
    }
}
