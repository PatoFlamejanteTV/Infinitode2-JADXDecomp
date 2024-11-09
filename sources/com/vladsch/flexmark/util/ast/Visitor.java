package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.visitor.AstAction;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/Visitor.class */
public interface Visitor<N extends Node> extends AstAction<N> {
    void visit(N n);
}
