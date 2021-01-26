package com.atomteam.elixir.parser.optimization;

import com.atomteam.elixir.lib.Value;

public final class VariableInfo {
    public Value value;
    int modifications;

    @Override
    public String toString() {
        return (value == null ? "?" : value) + " (" + modifications + " mods)";
    }
}