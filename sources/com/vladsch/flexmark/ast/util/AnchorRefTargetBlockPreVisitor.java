package com.vladsch.flexmark.ast.util;

import com.vladsch.flexmark.util.ast.Node;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/util/AnchorRefTargetBlockPreVisitor.class */
public interface AnchorRefTargetBlockPreVisitor {
    boolean preVisit(Node node, AnchorRefTargetBlockVisitor anchorRefTargetBlockVisitor);
}
