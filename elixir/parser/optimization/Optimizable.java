package com.atomteam.elixir.parser.optimization;

import com.atomteam.elixir.parser.ast.Node;

public interface Optimizable {

    Node optimize(Node node);

    int optimizationsCount();

    String summaryInfo();
}
