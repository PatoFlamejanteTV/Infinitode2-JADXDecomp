package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/NodeIterator.class */
public class NodeIterator implements ReversiblePeekingIterator<Node> {
    final Node firstNode;
    final Node lastNode;
    final boolean reversed;
    Node node;
    Node result;
    public static final ReversiblePeekingIterator<Node> EMPTY = new ReversiblePeekingIterator<Node>() { // from class: com.vladsch.flexmark.util.ast.NodeIterator.1
        @Override // java.util.Iterator
        public final void remove() {
        }

        @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIterator
        public final boolean isReversed() {
            return false;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public final Node next() {
            throw new NoSuchElementException();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterator
        public final Node peek() {
            return null;
        }
    };

    public NodeIterator(Node node) {
        this(node, null, false);
    }

    public NodeIterator(Node node, boolean z) {
        this(node, null, z);
    }

    public NodeIterator(Node node, Node node2) {
        this(node, node2, false);
    }

    public NodeIterator(Node node, Node node2, boolean z) {
        if (node == null) {
            throw new NullPointerException();
        }
        this.firstNode = node;
        this.lastNode = node2;
        this.reversed = z;
        this.node = z ? node2 : node;
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIterator
    public boolean isReversed() {
        return this.reversed;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.node != null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0055, code lost:            if (r3.result == (r3.reversed ? r3.firstNode : r3.lastNode)) goto L17;     */
    @Override // java.util.Iterator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.vladsch.flexmark.util.ast.Node next() {
        /*
            r3 = this;
            r0 = r3
            r1 = 0
            r0.result = r1
            r0 = r3
            com.vladsch.flexmark.util.ast.Node r0 = r0.node
            if (r0 != 0) goto L14
            java.util.NoSuchElementException r0 = new java.util.NoSuchElementException
            r1 = r0
            r1.<init>()
            throw r0
        L14:
            r0 = r3
            r1 = r0
            com.vladsch.flexmark.util.ast.Node r1 = r1.node
            r0.result = r1
            r0 = r3
            r1 = r0
            boolean r1 = r1.reversed
            if (r1 == 0) goto L2e
            r1 = r3
            com.vladsch.flexmark.util.ast.Node r1 = r1.node
            com.vladsch.flexmark.util.ast.Node r1 = r1.getPrevious()
            goto L35
        L2e:
            r1 = r3
            com.vladsch.flexmark.util.ast.Node r1 = r1.node
            com.vladsch.flexmark.util.ast.Node r1 = r1.getNext()
        L35:
            r0.node = r1
            r0 = r3
            com.vladsch.flexmark.util.ast.Node r0 = r0.node
            if (r0 == 0) goto L58
            r0 = r3
            com.vladsch.flexmark.util.ast.Node r0 = r0.result
            r1 = r3
            boolean r1 = r1.reversed
            if (r1 == 0) goto L51
            r1 = r3
            com.vladsch.flexmark.util.ast.Node r1 = r1.firstNode
            goto L55
        L51:
            r1 = r3
            com.vladsch.flexmark.util.ast.Node r1 = r1.lastNode
        L55:
            if (r0 != r1) goto L5d
        L58:
            r0 = r3
            r1 = 0
            r0.node = r1
        L5d:
            r0 = r3
            com.vladsch.flexmark.util.ast.Node r0 = r0.result
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vladsch.flexmark.util.ast.NodeIterator.next():com.vladsch.flexmark.util.ast.Node");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterator
    public Node peek() {
        if (this.node != null) {
            return this.node;
        }
        return null;
    }

    @Override // java.util.Iterator
    public void remove() {
        if (this.result == null) {
            throw new IllegalStateException("Either next() was not called yet or the node was removed");
        }
        this.result.unlink();
        this.result = null;
    }

    @Override // java.util.Iterator
    public void forEachRemaining(Consumer<? super Node> consumer) {
        if (consumer == null) {
            throw new NullPointerException();
        }
        while (hasNext()) {
            consumer.accept(next());
        }
    }
}
