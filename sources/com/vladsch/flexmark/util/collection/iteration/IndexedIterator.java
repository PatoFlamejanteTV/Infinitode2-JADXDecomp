package com.vladsch.flexmark.util.collection.iteration;

import com.vladsch.flexmark.util.collection.iteration.ReversibleIterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/iteration/IndexedIterator.class */
public class IndexedIterator<R, S, I extends ReversibleIterator<Integer>> implements ReversibleIndexedIterator<R> {
    private final I iterator;
    private final Indexed<S> items;
    private int lastIndex = -1;
    private int modificationCount;

    public IndexedIterator(Indexed<S> indexed, I i) {
        this.items = indexed;
        this.iterator = i;
        this.modificationCount = indexed.modificationCount();
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIterator
    public boolean isReversed() {
        return this.iterator.isReversed();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public R next() {
        if (this.modificationCount != this.items.modificationCount()) {
            throw new ConcurrentModificationException();
        }
        this.lastIndex = ((Integer) this.iterator.next()).intValue();
        return this.items.get(this.lastIndex);
    }

    @Override // java.util.Iterator
    public void remove() {
        if (this.lastIndex == -1) {
            throw new NoSuchElementException();
        }
        if (this.modificationCount != this.items.modificationCount()) {
            throw new ConcurrentModificationException();
        }
        this.items.removeAt(this.lastIndex);
        this.lastIndex = -1;
        this.modificationCount = this.items.modificationCount();
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIndexedIterator
    public int getIndex() {
        if (this.lastIndex < 0) {
            throw new NoSuchElementException();
        }
        return this.lastIndex;
    }

    @Override // java.util.Iterator
    public void forEachRemaining(Consumer<? super R> consumer) {
        while (hasNext()) {
            consumer.accept(next());
        }
    }
}
