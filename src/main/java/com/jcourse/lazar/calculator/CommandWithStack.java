package com.jcourse.lazar.calculator;

import java.util.Map;
import java.util.Stack;

public abstract class CommandWithStack implements Command {
    public Stack<Double> stack;
    public Map<String, Double> params;

    public CommandWithStack() {
    }

    abstract public void execute(String operands[]) throws Exception;

    public Stack<Double> getStack() {
        return stack;
    }
}
