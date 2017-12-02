package com.jcourse.lazar.calculator;

import com.jcourse.lazar.calculator.commands.*;

import java.util.Map;
import java.util.Stack;

public class Invoker {
    public Stack<Double> stack;
    public Map<String, Double> params;
    public Invoker(Stack<Double> stackArgument, Map<String, Double> paramsArgument) {
        stack = stackArgument;
        params = paramsArgument;
    }
    public void Invoke(String line) {
        String operator = parseOperator(line);
        Double operands[] = parseOperands(line);
        for (int i = 0; i < operands.length; i++) {
            stack.push(operands[i]);
            switch (operator) {
                case "DEFINE":
                    commandDefine(operands);
                    break;
                case "PUSH":
                    commandPush(operands);
                    break;
                case "POP":
                    commandPush(operands);
                    break;
            }
        }
    }
    private String parseOperator(String line) {
        String operator;

        return operator;
    }
    private Double[] parseOperands(String line) {
        Double operands[];

        return operands[];
    }
    public void commandDefine(Double args[]) {
        Command command = new CommandDefine(stack);
        command.execute(args);
    }
    public void commandPush(Double args[]) {
        Command command = new CommandPush(stack);
        command.execute(args);
    }
    public void commandPop(Double args[]) {
        Command command = new CommandPop(stack);
        command.execute(args);
    }
}
