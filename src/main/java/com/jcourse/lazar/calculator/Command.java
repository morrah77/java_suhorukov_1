package com.jcourse.lazar.calculator;

import java.util.Map;
import java.util.Stack;

public abstract class Command {
    public Stack<Double> stack;
    public Map<String, Double> params;
    public Command(Stack<Double> stackArgument, Map<String, Double> paramsArgument) {
        stack = stackArgument;
        params = paramsArgument;
    }
    abstract public void execute(String operands[]);
}
