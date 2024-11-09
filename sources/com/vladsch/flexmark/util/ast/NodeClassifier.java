package com.vladsch.flexmark.util.ast;

import java.util.function.Function;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/NodeClassifier.class */
public class NodeClassifier implements Function<Node, Class<?>> {
    public static final NodeClassifier INSTANCE = new NodeClassifier();

    private NodeClassifier() {
    }

    @Override // java.util.function.Function
    public Class<?> apply(Node node) {
        return node.getClass();
    }
}
