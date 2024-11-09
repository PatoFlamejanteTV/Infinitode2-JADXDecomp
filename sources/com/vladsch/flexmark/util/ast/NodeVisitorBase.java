package com.vladsch.flexmark.util.ast;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/NodeVisitorBase.class */
public abstract class NodeVisitorBase {
    protected abstract void visit(Node node);

    public void visitChildren(Node node) {
        Node firstChild = node.getFirstChild();
        while (true) {
            Node node2 = firstChild;
            if (node2 != null) {
                Node next = node2.getNext();
                visit(node2);
                firstChild = next;
            } else {
                return;
            }
        }
    }
}
