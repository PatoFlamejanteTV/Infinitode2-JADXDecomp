package com.vladsch.flexmark.util.ast;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/NodeVisitHandler.class */
public interface NodeVisitHandler extends Visitor<Node> {
    void visitNodeOnly(Node node);

    void visitChildren(Node node);
}
