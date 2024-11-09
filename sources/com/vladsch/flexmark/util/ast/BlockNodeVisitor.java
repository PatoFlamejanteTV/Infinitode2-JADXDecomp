package com.vladsch.flexmark.util.ast;

import java.util.Collection;
import java.util.function.BiConsumer;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/BlockNodeVisitor.class */
public class BlockNodeVisitor extends NodeVisitor {
    public BlockNodeVisitor() {
    }

    public BlockNodeVisitor(VisitHandler... visitHandlerArr) {
        super(visitHandlerArr);
    }

    public BlockNodeVisitor(VisitHandler[]... visitHandlerArr) {
        super(visitHandlerArr);
    }

    public BlockNodeVisitor(Collection<VisitHandler> collection) {
        super(collection);
    }

    @Override // com.vladsch.flexmark.util.visitor.AstActionHandler
    public void processNode(Node node, boolean z, BiConsumer<Node, Visitor<Node>> biConsumer) {
        if (node instanceof Block) {
            super.processNode((BlockNodeVisitor) node, z, (BiConsumer<BlockNodeVisitor, A>) biConsumer);
        }
    }
}
