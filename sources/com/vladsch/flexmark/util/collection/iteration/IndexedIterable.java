package com.vladsch.flexmark.util.collection.iteration;

import com.vladsch.flexmark.util.collection.iteration.ReversibleIterable;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/iteration/IndexedIterable.class */
public class IndexedIterable<R, S, I extends ReversibleIterable<Integer>> implements ReversibleIndexedIterable<R> {
    private final ReversibleIterable<Integer> iterable;
    private final Indexed<S> items;

    public IndexedIterable(Indexed<S> indexed, I i) {
        this.items = indexed;
        this.iterable = i;
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIterable
    public boolean isReversed() {
        return this.iterable.isReversed();
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIndexedIterable, com.vladsch.flexmark.util.collection.iteration.ReversibleIterable, java.lang.Iterable
    public ReversibleIndexedIterator<R> iterator() {
        return new IndexedIterator(this.items, this.iterable.iterator());
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIndexedIterable, com.vladsch.flexmark.util.collection.iteration.ReversibleIterable
    public ReversibleIndexedIterable<R> reversed() {
        return new IndexedIterable(this.items, this.iterable.reversed());
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIndexedIterable, com.vladsch.flexmark.util.collection.iteration.ReversibleIterable
    public ReversibleIndexedIterator<R> reversedIterator() {
        return new IndexedIterator(this.items, this.iterable.reversedIterator());
    }
}
