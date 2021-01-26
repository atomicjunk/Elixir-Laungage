package com.atomteam.elixir.parser.ast;

import com.atomteam.elixir.lib.Value;

public interface Accessible extends Node {

    Value get();
    
    Value set(Value value);
}
