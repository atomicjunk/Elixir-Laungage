package com.atomteam.elixir.parser.ast;

import com.atomteam.elixir.exceptions.ParseException;
import com.atomteam.elixir.parser.parser.Lexer;
import com.atomteam.elixir.parser.parser.Parser;
import com.atomteam.elixir.parser.parser.SourceLoader;
import com.atomteam.elixir.parser.parser.Token;
import com.atomteam.elixir.parser.visitors.FunctionAdder;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author aNNiMON
 */
public final class IncludeStatement extends InterruptableNode implements Statement {

    public final Expression expression;
    
    public IncludeStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void execute() {
        super.interruptionCheck();
        try {
            final Statement program = loadProgram(expression.eval().asString());
            if (program != null) {
                program.accept(new FunctionAdder());
                program.execute();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public Statement loadProgram(String path) throws IOException {
        final String input = SourceLoader.readSource(path);
        final List<Token> tokens = Lexer.tokenize(input);
        final Parser parser = new Parser(tokens);
        final Statement program = parser.parse();
        if (parser.getParseErrors().hasErrors()) {
            throw new ParseException(parser.getParseErrors().toString());
        }
        return program;
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <R, T> R accept(ResultVisitor<R, T> visitor, T t) {
        return visitor.visit(this, t);
    }

    @Override
    public String toString() {
        return "include " + expression;
    }
}
