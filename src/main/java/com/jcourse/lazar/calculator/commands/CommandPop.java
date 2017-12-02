package com.jcourse.lazar.calculator.commands;

import com.jcourse.lazar.calculator.Command;

import java.util.Map;
import java.util.Stack;

public class CommandPop extends Command {
    public CommandPop(Stack<Double> stack, Map<String, Double> params) {
        super(stack, params);
    }
    public void execute(String operands[]) {
        stack.pop();
    }
}
