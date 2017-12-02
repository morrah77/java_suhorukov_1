package com.jcourse.lazar.calculator.commands;

import com.jcourse.lazar.calculator.Command;

import java.util.Map;
import java.util.Stack;

public class CommandPush extends Command {
    public CommandPush(Stack<Double> stack, Map<String, Double> params) {
        super(stack, params);
    }
    public void execute(String operands[]) {
        stack.push(operands[0]);
    }
}
