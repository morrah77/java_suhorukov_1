package com.jcourse.lazar.calculator;

import java.util.Arrays;
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
    public void Invoke(String line) throws Exception {
        System.out.println(line);
        if (line.substring(0, 1).equals("#")) {
            System.out.println("Return on comment!");
            return;
        }
        String parsed[] = line.split(" ");
        if (parsed.length <= 0) {
            System.out.println("Return on empty command!");
            return;
        }
        String operands[] = new String[0];
        String operator = parsed[0];
        if (parsed.length > 1) {
            operands = Arrays.copyOfRange(parsed, 1, parsed.length);
        }

        Command command = commandsFactory.getCommand(stack, definitions, operator);
        try {
            command.execute(operands);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
