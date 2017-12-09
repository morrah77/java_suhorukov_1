package com.jcourse.lazar.calculator;

import java.util.Stack;

public interface Command {
    void execute(String operands[]) throws Exception;
//    Stack<Double> getStack();
}
