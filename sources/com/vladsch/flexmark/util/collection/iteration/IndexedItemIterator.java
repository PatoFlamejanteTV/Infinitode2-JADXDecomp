package com.vladsch.flexmark.util.collection.iteration;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/iteration/IndexedItemIterator.class */
public class IndexedItemIterator<R> implements ReversibleIndexedIterator<R> {
    private final Indexed<R> items;
    private final boolean reversed;
    private int next;
    private int last;
    private int modificationCount;

    public IndexedItemIterator(Indexed<R> indexed) {
        this(indexed, false);
    }

    public IndexedItemIterator(Indexed<R> indexed, boolean z) {
        this.items = indexed;
        this.reversed = z;
        this.next = this.reversed ? indexed.size() - 1 : 0;
        if (this.next >= indexed.size()) {
            this.next = -1;
        }
        this.last = -1;
        this.modificationCount = indexed.modificationCount();
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIterator
    public boolean isReversed() {
        return this.reversed;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.next != -1;
    }

    @Override // java.util.Iterator
    public R next() {
        if (this.modificationCount != this.items.modificationCount()) {
            throw new ConcurrentModificationException();
        }
        if (this.next == -1) {
            throw new NoSuchElementException();
        }
        this.last = this.next;
        this.next = this.reversed ? this.next <= 0 ? -1 : this.next - 1 : this.next == this.items.size() - 1 ? -1 : this.next + 1;
        return this.items.get(this.last);
    }

    @Override // java.util.Iterator
    public void remove() {
        if (this.last == -1) {
            throw new NoSuchElementException();
        }
        if (this.modificationCount != this.items.modificationCount()) {
            throw new ConcurrentModificationException();
        }
        this.items.removeAt(this.last);
        this.last = -1;
        this.modificationCount = this.items.modificationCount();
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIndexedIterator
    public int getIndex() {
        if (this.last < 0) {
            throw new NoSuchElementException();
        }
        return this.last;
    }

    @Override // java.util.Iterator
    public void forEachRemaining(Consumer<? super R> consumer) {
        while (hasNext()) {
            consumer.accept(next());
        }
    }
}
