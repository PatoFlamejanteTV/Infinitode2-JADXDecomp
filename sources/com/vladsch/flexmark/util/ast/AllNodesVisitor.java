package com.vladsch.flexmark.util.ast;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/AllNodesVisitor.class */
public abstract class AllNodesVisitor {
    protected abstract void process(Node node);

    public void visit(Node node) {
        visitChildren(node);
    }

    private void visitChildren(Node node) {
        Node firstChild = node.getFirstChild();
        while (true) {
            Node node2 = firstChild;
            if (node2 != null) {
                Node next = node2.getNext();
                process(node2);
                visit(node2);
                firstChild = next;
            } else {
                return;
            }
        }
    }
}
