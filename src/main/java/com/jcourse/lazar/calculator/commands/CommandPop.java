package com.jcourse.lazar.calculator.commands;

import com.jcourse.lazar.calculator.ArgType;
import com.jcourse.lazar.calculator.Command;
import com.jcourse.lazar.calculator.CommandWithStack;
import com.jcourse.lazar.calculator.In;

import java.util.Map;
import java.util.Stack;

public class CommandPop extends CommandWithStack implements Command {
    @In(arg=ArgType.STACK)
    private Stack<Double> stack;

    public CommandPop() {
        super();
    }

    public void execute(String operands[]) throws Exception {
        stack.pop();
    }
}
