package com.atomteam.elixir.parser.visitors;

import com.atomteam.elixir.parser.ast.FunctionDefineStatement;

/**
 *
 * @author aNNiMON
 */
public final class FunctionAdder extends AbstractVisitor {

    @Override
    public void visit(FunctionDefineStatement s) {
        super.visit(s);
        s.execute();
    }
}
