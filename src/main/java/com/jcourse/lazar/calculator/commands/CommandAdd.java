package com.jcourse.lazar.calculator.commands;

import com.jcourse.lazar.calculator.*;

import java.util.Map;
import java.util.Stack;

public class CommandAdd extends CommandWithStack implements Command {
    @In(arg= ArgType.STACK)
    private Stack<Double> stack;

    @In(arg=ArgType.DEFINITIONS)
    private Map<String, Double> params;

    private final int OPERANDS_COUNT = 0;
    private final String BAD_OPERANDS_COUNT = "Want 0 operands!";
    private final String BAD_VALUE = "Value must be a number!";

    public CommandAdd() {
        super();
    }

    public void execute(String operands[]) throws CommandBadArgumentsException {
        validateAndParseOperands(operands);
        Double arg1 = stack.pop();
        Double arg2 = stack.pop();
        Double result = arg1 + arg2;
        stack.push(result);
    }

    private void validateAndParseOperands(String operands[]) throws CommandBadArgumentsException {
        if (operands.length != OPERANDS_COUNT) {
            throw new CommandBadArgumentsException(BAD_OPERANDS_COUNT);
        }
    }
}
