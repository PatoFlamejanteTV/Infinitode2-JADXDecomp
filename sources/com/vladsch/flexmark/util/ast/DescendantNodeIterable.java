package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterable;
import com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterator;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/DescendantNodeIterable.class */
public class DescendantNodeIterable implements ReversiblePeekingIterable<Node> {
    private ReversiblePeekingIterable<Node> iterable;

    public DescendantNodeIterable(ReversiblePeekingIterable<Node> reversiblePeekingIterable) {
        if (reversiblePeekingIterable instanceof DescendantNodeIterable) {
            this.iterable = ((DescendantNodeIterable) reversiblePeekingIterable).iterable;
        } else {
            this.iterable = reversiblePeekingIterable;
        }
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterable, com.vladsch.flexmark.util.collection.iteration.ReversibleIterable, java.lang.Iterable
    public ReversiblePeekingIterator<Node> iterator() {
        return new DescendantNodeIterator(this.iterable.iterator());
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterable, com.vladsch.flexmark.util.collection.iteration.ReversibleIterable
    public ReversiblePeekingIterable<Node> reversed() {
        return new DescendantNodeIterable(this.iterable.reversed());
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterable, com.vladsch.flexmark.util.collection.iteration.ReversibleIterable
    public ReversiblePeekingIterator<Node> reversedIterator() {
        return new DescendantNodeIterator(this.iterable.reversedIterator());
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIterable
    public boolean isReversed() {
        return this.iterable.isReversed();
    }
}
