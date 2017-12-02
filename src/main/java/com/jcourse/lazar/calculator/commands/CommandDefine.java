package com.jcourse.lazar.calculator.commands;

import com.jcourse.lazar.calculator.Command;

import java.util.Map;
import java.util.Stack;

public class CommandDefine extends Command {
    
    @In(arg=STACK)
    private Stack<Double> stack;

    @In(arg=DEFINITIONS)
    private Map<String, Double> params;


    public CommandDefine(Stack<Double> stack, Map<String, Double> params) {
        super(stack, params);
    }
    public void execute(String operands[]) {
        params.put(operands[0], Double(operands[1]));
    }
}
