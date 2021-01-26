package com.atomteam.elixir.parser.visitors;

import com.atomteam.elixir.parser.ast.VariableExpression;
import com.atomteam.elixir.parser.ast.AssignmentExpression;
import com.atomteam.elixir.Console;

/**
 *
 * @author aNNiMON
 */
public final class VariablePrinter extends AbstractVisitor {

    @Override
    public void visit(AssignmentExpression s) {
        super.visit(s);
        Console.println(s.target);
    }

    @Override
    public void visit(VariableExpression s) {
        super.visit(s);
        Console.println(s.name);
    }
}
