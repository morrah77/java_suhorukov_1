package com.jcourse.lazar.calculator.commands;

import com.jcourse.lazar.calculator.ArgType;
import com.jcourse.lazar.calculator.Command;
import com.jcourse.lazar.calculator.CommandWithStack;
import com.jcourse.lazar.calculator.In;

import java.util.Stack;

public class CommandPrint extends CommandWithStack implements Command {

    @In(arg= ArgType.STACK)
    private Stack<Double> stack;

    public void execute(String operands[]) throws Exception {
        //TODO(h.lazar) pass output stream on instantiation
        System.out.println(stack.peek());
    }
}
