package com.jcourse.lazar.calculator.commands;

import com.jcourse.lazar.calculator.ArgType;
import com.jcourse.lazar.calculator.Command;
import com.jcourse.lazar.calculator.CommandWithStack;
import com.jcourse.lazar.calculator.CommandBadArgumentsException;
import com.jcourse.lazar.calculator.In;

import java.util.Map;
import java.util.Stack;

public class CommandDefine extends CommandWithStack implements Command {

    @In(arg=ArgType.STACK)
    private Stack<Double> stack;

    @In(arg=ArgType.DEFINITIONS)
    private Map<String, Double> params;

    private final int OPERANDS_COUNT = 2;
    private final String BAD_OPERANDS_COUNT = "Want 2 operands!";
    private final String BAD_VALUE = "Value must be a number!";

    private Double value;
    private String name;

    public CommandDefine() {
        super();
    }

    public void execute(String operands[]) throws CommandBadArgumentsException {
        validateAndParseOperands(operands);
        params.put(name, value);
    }

    private void validateAndParseOperands(String operands[]) throws CommandBadArgumentsException {
        if (operands.length != OPERANDS_COUNT) {
            throw new CommandBadArgumentsException(BAD_OPERANDS_COUNT);
        }
        //TODO(h.lazar) consider to validate name
        name = operands[0];
        try {
            value = Double.parseDouble(operands[1]);
        } catch (NumberFormatException ex) {
            throw new CommandBadArgumentsException(BAD_VALUE);
        }
    }
}
