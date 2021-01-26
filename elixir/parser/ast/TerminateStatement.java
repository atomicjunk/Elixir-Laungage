/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atomteam.elixir.parser.ast;

import com.atomteam.elixir.Console;

/**
 *
 * @author Jode
 */
public class TerminateStatement implements Statement {
    

    public TerminateStatement() {
    }



    public void execute() {
       // System.out.println("Elixir Exception  : " + expression.eval());
        System.exit(0);
    }
    

    public void accept(Visitor visitor) {
    }

    public <R, T> R accept(ResultVisitor<R, T> visitor, T t) {
        return null;
    }

    @Override
    public String toString() {
        return "terminate";
    }
}
