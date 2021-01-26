package com.atomteam.elixir.parser.parser;

import com.atomteam.elixir.Console;
import com.atomteam.elixir.parser.linters.AssignValidator;
import com.atomteam.elixir.parser.linters.UseWithNonStringValueValidator;
import com.atomteam.elixir.parser.linters.DefaultFunctionsOverrideValidator;
import com.atomteam.elixir.lib.Functions;
import com.atomteam.elixir.lib.Variables;
import com.atomteam.elixir.parser.ast.Statement;
import com.atomteam.elixir.parser.ast.Visitor;

public final class Linter {

    public static void lint(Statement program) {
        new Linter(program).execute();
    }

    private final Statement program;

    private Linter(Statement program) {
        this.program = program;
    }
    
    public void execute() {
        final Visitor[] validators = new Visitor[] {
            new UseWithNonStringValueValidator(),
            new AssignValidator(),
            new DefaultFunctionsOverrideValidator()
        };
        resetState();
        for (Visitor validator : validators) {
            program.accept(validator);
            resetState();
        }
        Console.println("Lint validation complete!");
    }

    private void resetState() {
        Variables.clear();
        Functions.getFunctions().clear();
    }
}
