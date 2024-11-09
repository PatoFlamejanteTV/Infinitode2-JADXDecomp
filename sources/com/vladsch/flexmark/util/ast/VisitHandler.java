package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.visitor.AstHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/VisitHandler.class */
public class VisitHandler<N extends Node> extends AstHandler<N, Visitor<N>> implements Visitor<Node> {
    public VisitHandler(Class<N> cls, Visitor<N> visitor) {
        super(cls, visitor);
    }

    @Override // com.vladsch.flexmark.util.ast.Visitor
    public void visit(Node node) {
        getAdapter().visit(node);
    }
}
