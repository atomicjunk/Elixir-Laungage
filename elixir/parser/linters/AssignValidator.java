package com.atomteam.elixir.parser.linters;

import com.atomteam.elixir.parser.ast.VariableExpression;
import com.atomteam.elixir.parser.ast.AssignmentExpression;
import com.atomteam.elixir.parser.ast.UseStatement;
import com.atomteam.elixir.parser.ast.IncludeStatement;
import com.atomteam.elixir.Console;
import com.atomteam.elixir.lib.Variables;

/**
 *
 * @author aNNiMON
 */
public final class AssignValidator extends LintVisitor {

    @Override
    public void visit(AssignmentExpression s) {
        super.visit(s);
        if (s.target instanceof VariableExpression) {
            final String variable = ((VariableExpression) s.target).name;
            if (Variables.isExists(variable)) {
                Console.error(String.format(
                    "Warning: variable \"%s\" overrides constant", variable));
            }
        }
    }

    @Override
    public void visit(IncludeStatement st) {
        super.visit(st);
        applyVisitor(st, this);
    }

    @Override
    public void visit(UseStatement st) {
        super.visit(st);
        st.execute();
    }
}
