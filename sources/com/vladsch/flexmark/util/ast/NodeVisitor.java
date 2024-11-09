package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.visitor.AstActionHandler;
import java.util.Collection;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/NodeVisitor.class */
public class NodeVisitor extends AstActionHandler<NodeVisitor, Node, Visitor<Node>, VisitHandler<Node>> implements NodeVisitHandler {
    protected static final VisitHandler[] EMPTY_HANDLERS = new VisitHandler[0];

    public NodeVisitor() {
        super(Node.AST_ADAPTER);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public NodeVisitor(VisitHandler... visitHandlerArr) {
        super(Node.AST_ADAPTER);
        super.addActionHandlers(visitHandlerArr);
    }

    public NodeVisitor(VisitHandler[]... visitHandlerArr) {
        super(Node.AST_ADAPTER);
        super.addActionHandlers(visitHandlerArr);
    }

    public NodeVisitor(Collection<VisitHandler> collection) {
        super(Node.AST_ADAPTER);
        addHandlers(collection);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public NodeVisitor addTypedHandlers(Collection<VisitHandler<?>> collection) {
        return (NodeVisitor) super.addActionHandlers((VisitHandler[]) collection.toArray(EMPTY_HANDLERS));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public NodeVisitor addHandlers(Collection<VisitHandler> collection) {
        return (NodeVisitor) super.addActionHandlers((VisitHandler[]) collection.toArray(EMPTY_HANDLERS));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public NodeVisitor addHandlers(VisitHandler[] visitHandlerArr) {
        return (NodeVisitor) super.addActionHandlers(visitHandlerArr);
    }

    public NodeVisitor addHandlers(VisitHandler[]... visitHandlerArr) {
        return (NodeVisitor) super.addActionHandlers(visitHandlerArr);
    }

    public NodeVisitor addHandler(VisitHandler visitHandler) {
        return (NodeVisitor) super.addActionHandler(visitHandler);
    }

    @Override // com.vladsch.flexmark.util.ast.Visitor
    public final void visit(Node node) {
        processNode(node, true, this::visit);
    }

    @Override // com.vladsch.flexmark.util.ast.NodeVisitHandler
    public final void visitNodeOnly(Node node) {
        processNode(node, false, this::visit);
    }

    @Override // com.vladsch.flexmark.util.ast.NodeVisitHandler
    public final void visitChildren(Node node) {
        processChildren(node, this::visit);
    }

    private void visit(Node node, Visitor<Node> visitor) {
        visitor.visit(node);
    }
}
