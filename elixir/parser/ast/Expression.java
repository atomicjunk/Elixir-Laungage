package com.atomteam.elixir.parser.ast;

import com.atomteam.elixir.lib.Value;

/**
 *
 * @author aNNiMON
 */
public interface Expression extends Node {
    
    Value eval();
}
