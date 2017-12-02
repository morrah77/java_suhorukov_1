package com.jcourse.lazar.calculator.commands;

import com.jcourse.lazar.calculator.Command;

import java.util.Map;
import java.util.Stack;

public class CommandAdd extends Command {
    public CommandAdd(Stack<Double> stack, Map<String, Double> params) {
        super(stack, params);
    }
    public void execute(String operands[]) {
        Double operand1 = stack.pop();
        Double operand2 = stack.pop();
        Double result = operand1 + operand2;
        stack.push(result);
    }
}
