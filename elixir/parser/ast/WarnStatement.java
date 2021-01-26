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
public class WarnStatement implements Statement {
    
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";
    final Expression args;
    
    public WarnStatement(Expression expression) {
        this.args = expression;
    }

    @Override
    public void execute() {
        System.out.println(ANSI_YELLOW + args.eval() + ANSI_RESET);
    }

    @Override
    public void accept(Visitor visitor) {
    }

    @Override
    public <R, T> R accept(ResultVisitor<R, T> visitor, T input) {
        return null;
    }
    
}
