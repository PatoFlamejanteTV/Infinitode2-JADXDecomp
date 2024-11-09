package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterator;
import java.util.Stack;
import java.util.function.Consumer;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/DescendantNodeIterator.class */
public class DescendantNodeIterator implements ReversiblePeekingIterator<Node> {
    private final boolean isReversed;
    private ReversiblePeekingIterator<Node> iterator;
    private Stack<ReversiblePeekingIterator<Node>> iteratorStack;
    private Node result;

    public DescendantNodeIterator(ReversiblePeekingIterator<Node> reversiblePeekingIterator) {
        this.isReversed = reversiblePeekingIterator.isReversed();
        this.iterator = reversiblePeekingIterator instanceof DescendantNodeIterator ? ((DescendantNodeIterator) reversiblePeekingIterator).iterator : reversiblePeekingIterator;
        this.iteratorStack = null;
        this.result = null;
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIterator
    public boolean isReversed() {
        return this.isReversed;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public Node next() {
        this.result = (Node) this.iterator.next();
        if (this.result.getFirstChild() != null) {
            if (this.iterator.hasNext()) {
                if (this.iteratorStack == null) {
                    this.iteratorStack = new Stack<>();
                }
                this.iteratorStack.push(this.iterator);
            }
            this.iterator = this.isReversed ? this.result.getReversedChildIterator() : this.result.getChildIterator();
        } else if (this.iteratorStack != null && !this.iteratorStack.isEmpty() && !this.iterator.hasNext()) {
            this.iterator = this.iteratorStack.pop();
        }
        return this.result;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterator
    public Node peek() {
        return this.iterator.peek();
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
        while (hasNext()) {
            consumer.accept(next());
        }
    }
}
