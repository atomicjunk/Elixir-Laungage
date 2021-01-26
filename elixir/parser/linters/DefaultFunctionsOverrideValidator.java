package com.atomteam.elixir.parser.linters;

import com.atomteam.elixir.parser.ast.FunctionDefineStatement;
import com.atomteam.elixir.parser.ast.UseStatement;
import com.atomteam.elixir.parser.ast.IncludeStatement;
import com.atomteam.elixir.Console;
import com.atomteam.elixir.lib.Functions;

public final class DefaultFunctionsOverrideValidator extends LintVisitor {

    @Override
    public void visit(FunctionDefineStatement s) {
        super.visit(s);
        if (Functions.isExists(s.name)) {
            Console.error(String.format(
                    "Warning: function \"%s\" overrides default module function", s.name));
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
