package com.atomteam.elixir.parser.ast;

import com.atomteam.elixir.lib.Function;
import com.atomteam.elixir.lib.FunctionValue;
import com.atomteam.elixir.lib.NumberValue;
import com.atomteam.elixir.lib.StringValue;
import com.atomteam.elixir.lib.Types;
import com.atomteam.elixir.lib.Value;

/**
 *
 * @author aNNiMON
 */
public final class ValueExpression extends InterruptableNode implements Expression {
    
    public final Value value;
    
    public ValueExpression(Number value) {
        this.value = NumberValue.of(value);
    }
    
    public ValueExpression(String value) {
        this.value = new StringValue(value);
    }
    
    public ValueExpression(Function value) {
        this.value = new FunctionValue(value);
    }
    
    public ValueExpression(Value value) {
        this.value = value;
    }

    @Override
    public Value eval() {
        super.interruptionCheck();
        return value;
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
        if (value.type() == Types.STRING) {
            return "\"" + value.asString() + "\"";
        }
        return value.toString();
    }
}
