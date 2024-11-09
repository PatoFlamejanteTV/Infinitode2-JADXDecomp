package com.vladsch.flexmark.util.collection.iteration;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/iteration/ReversibleIterable.class */
public interface ReversibleIterable<E> extends Iterable<E> {
    @Override // java.lang.Iterable
    ReversibleIterator<E> iterator();

    ReversibleIterable<E> reversed();

    boolean isReversed();

    ReversibleIterator<E> reversedIterator();
}
