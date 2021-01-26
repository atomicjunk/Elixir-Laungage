package com.atomteam.elixir.parser.linters;

import com.atomteam.elixir.parser.ast.IncludeStatement;
import com.atomteam.elixir.parser.ast.Statement;
import com.atomteam.elixir.parser.ast.Visitor;
import com.atomteam.elixir.parser.visitors.AbstractVisitor;
import com.atomteam.elixir.parser.visitors.VisitorUtils;

public abstract class LintVisitor extends AbstractVisitor {

    protected void applyVisitor(IncludeStatement s, Visitor visitor) {
        final Statement program = VisitorUtils.includeProgram(s);
        if (program != null) {
            program.accept(visitor);
        }
    }
}
