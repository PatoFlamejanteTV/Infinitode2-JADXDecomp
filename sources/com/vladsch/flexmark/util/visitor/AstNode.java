package com.vladsch.flexmark.util.visitor;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/visitor/AstNode.class */
public interface AstNode<N> {
    N getFirstChild(N n);

    N getNext(N n);
}
