/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atomteam.elixir.parser.ast;

/**
 *
 * @author Jode
 */
public class AssertStatement implements Statement {
    public final Expression expression;

    public AssertStatement(Expression expression) {
        this.expression = expression;
    }
    
    @Override
    public void execute() {
        final int result = expression.eval().asInt();
        if (result == 0) {
            System.out.println("Elixir : assertion error");
            System.exit(0);
        }
    }
    
    public void accept(Visitor visitor) {
    }

    @Override
    public <R, T> R accept(ResultVisitor<R, T> visitor, T t) {
        return null;
    }

    @Override
    public String toString() {
        return "assert" + expression;
    }
}
