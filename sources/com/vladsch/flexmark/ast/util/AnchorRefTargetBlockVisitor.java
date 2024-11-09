package com.vladsch.flexmark.ast.util;

import com.vladsch.flexmark.ast.AnchorRefTarget;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeVisitorBase;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/util/AnchorRefTargetBlockVisitor.class */
public abstract class AnchorRefTargetBlockVisitor extends NodeVisitorBase {
    protected abstract void visit(AnchorRefTarget anchorRefTarget);

    protected boolean preVisit(Node node) {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.vladsch.flexmark.util.ast.NodeVisitorBase
    public void visit(Node node) {
        if (node instanceof AnchorRefTarget) {
            visit((AnchorRefTarget) node);
        }
        if (preVisit(node) && (node instanceof Block)) {
            visitChildren(node);
        }
    }
}
