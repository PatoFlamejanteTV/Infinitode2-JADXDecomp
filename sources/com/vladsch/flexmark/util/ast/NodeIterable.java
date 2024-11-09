package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterable;
import com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterator;
import java.util.function.Consumer;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/NodeIterable.class */
public class NodeIterable implements ReversiblePeekingIterable<Node> {
    final Node firstNode;
    final Node lastNode;
    final boolean reversed;
    public static final ReversiblePeekingIterable<Node> EMPTY = new ReversiblePeekingIterable<Node>() { // from class: com.vladsch.flexmark.util.ast.NodeIterable.1
        @Override // com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterable, com.vladsch.flexmark.util.collection.iteration.ReversibleIterable, java.lang.Iterable
        public final ReversiblePeekingIterator<Node> iterator() {
            return NodeIterator.EMPTY;
        }

        @Override // com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterable, com.vladsch.flexmark.util.collection.iteration.ReversibleIterable
        public final ReversiblePeekingIterable<Node> reversed() {
            return this;
        }

        @Override // java.lang.Iterable
        public final void forEach(Consumer<? super Node> consumer) {
        }

        @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIterable
        public final boolean isReversed() {
            return false;
        }

        @Override // com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterable, com.vladsch.flexmark.util.collection.iteration.ReversibleIterable
        public final ReversiblePeekingIterator<Node> reversedIterator() {
            return NodeIterator.EMPTY;
        }
    };

    public NodeIterable(Node node, Node node2, boolean z) {
        this.firstNode = node;
        this.lastNode = node2;
        this.reversed = z;
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterable, com.vladsch.flexmark.util.collection.iteration.ReversibleIterable, java.lang.Iterable
    public ReversiblePeekingIterator<Node> iterator() {
        return new NodeIterator(this.firstNode, this.lastNode, this.reversed);
    }

    @Override // java.lang.Iterable
    public void forEach(Consumer<? super Node> consumer) {
        ReversiblePeekingIterator<Node> it = iterator();
        while (it.hasNext()) {
            consumer.accept(it.next());
        }
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterable, com.vladsch.flexmark.util.collection.iteration.ReversibleIterable
    public ReversiblePeekingIterable<Node> reversed() {
        return new NodeIterable(this.firstNode, this.lastNode, !this.reversed);
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIterable
    public boolean isReversed() {
        return this.reversed;
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterable, com.vladsch.flexmark.util.collection.iteration.ReversibleIterable
    public ReversiblePeekingIterator<Node> reversedIterator() {
        return new NodeIterator(this.firstNode, this.lastNode, !this.reversed);
    }
}
