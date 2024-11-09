package com.vladsch.flexmark.util.collection.iteration;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/iteration/ReversiblePeekingIterable.class */
public interface ReversiblePeekingIterable<E> extends ReversibleIterable<E> {
    @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIterable, java.lang.Iterable
    ReversiblePeekingIterator<E> iterator();

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIterable
    ReversiblePeekingIterable<E> reversed();

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIterable
    ReversiblePeekingIterator<E> reversedIterator();
}
