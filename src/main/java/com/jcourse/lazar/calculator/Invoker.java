package com.jcourse.lazar.calculator;

import java.util.Map;
import java.util.Stack;

public class Invoker {
    public Stack<Double> stack;
    public Map<String, Double> definitions;
    public Factory commandsFactory;
    public Invoker(Stack<Double> stackArgument, Map<String, Double> definitionsArgument) {
        stack = stackArgument;
        definitions = definitionsArgument;
        commandsFactory = new Factory();
    }
    public void Invoke(String line) {
        String operator = parseOperator(line);
        String operands[] = parseOperands(line);
        Command command = commandsFactory.getCommand(stack, definitions, operator);
        try {
            command.execute(operands);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
    private String parseOperator(String line) {
        String operator;
        operator = "";
        return operator;
    }
    private String[] parseOperands(String line) {
        String operands[];

        return operands;
    }
}
