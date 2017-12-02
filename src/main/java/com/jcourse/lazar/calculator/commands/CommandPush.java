package com.jcourse.lazar.calculator.commands;

import com.jcourse.lazar.calculator.ArgType;
import com.jcourse.lazar.calculator.Command;
import com.jcourse.lazar.calculator.CommandBadArgumentsException;
import com.jcourse.lazar.calculator.In;

import java.util.Map;
import java.util.Stack;

public class CommandPush extends Command {
    @In(arg=ArgType.STACK)
    private Stack<Double> stack;

    @In(arg=ArgType.DEFINITIONS)
    private Map<String, Double> params;

    private final int OPERANDS_COUNT = 2;
    private final String BAD_OPERANDS_COUNT = "Want 1 operand!";
    private final String BAD_VALUE = "Value must be a number!";

    private Double value;

    public CommandPush() {
        super();
    }

    public void execute(String operands[]) throws CommandBadArgumentsException {
        validateAndParseOperands(operands);
        stack.push(value);
    }

    private void validateAndParseOperands(String operands[]) throws CommandBadArgumentsException {
        if (operands.length != OPERANDS_COUNT) {
            throw new CommandBadArgumentsException(BAD_OPERANDS_COUNT);
        }
        try {
            value = Double.parseDouble(operands[0]);
        } catch (NumberFormatException ex) {
            throw new CommandBadArgumentsException(BAD_VALUE);
        }
    }
}
