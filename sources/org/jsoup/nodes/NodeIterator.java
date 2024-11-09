package org.jsoup.nodes;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Node;

/* loaded from: infinitode-2.jar:org/jsoup/nodes/NodeIterator.class */
public class NodeIterator<T extends Node> implements Iterator<T> {
    private Node root;
    private T next;
    private Node current;
    private Node previous;
    private Node currentParent;
    private final Class<T> type;

    public NodeIterator(Node node, Class<T> cls) {
        Validate.notNull(node);
        Validate.notNull(cls);
        this.type = cls;
        restart(node);
    }

    public static NodeIterator<Node> from(Node node) {
        return new NodeIterator<>(node, Node.class);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void restart(Node node) {
        if (this.type.isInstance(node)) {
            this.next = node;
        }
        this.current = node;
        this.previous = node;
        this.root = node;
        this.currentParent = this.current.parent();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        maybeFindNext();
        return this.next != null;
    }

    @Override // java.util.Iterator
    public T next() {
        maybeFindNext();
        if (this.next == null) {
            throw new NoSuchElementException();
        }
        T t = this.next;
        this.previous = this.current;
        this.current = this.next;
        this.currentParent = this.current.parent();
        this.next = null;
        return t;
    }

    private void maybeFindNext() {
        if (this.next != null) {
            return;
        }
        if (this.currentParent != null && !this.current.hasParent()) {
            this.current = this.previous;
        }
        this.next = findNextNode();
    }

    private T findNextNode() {
        Node node = this.current;
        do {
            if (node.childNodeSize() > 0) {
                node = node.childNode(0);
            } else if (this.root.equals(node)) {
                node = null;
            } else {
                if (node.nextSibling() != null) {
                    node = node.nextSibling();
                }
                do {
                    Node parent = node.parent();
                    node = parent;
                    if (parent == null || this.root.equals(node)) {
                        return null;
                    }
                } while (node.nextSibling() == null);
                node = node.nextSibling();
            }
            if (node == null) {
                return null;
            }
        } while (!this.type.isInstance(node));
        return (T) node;
    }

    @Override // java.util.Iterator
    public void remove() {
        this.current.remove();
    }
}
