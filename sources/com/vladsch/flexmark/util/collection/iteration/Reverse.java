package com.vladsch.flexmark.util.collection.iteration;

import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/iteration/Reverse.class */
public class Reverse<T> implements ReversibleIterable<T> {
    private final List<T> list;
    private final boolean isReversed;

    public Reverse(List<T> list) {
        this(list, true);
    }

    public Reverse(List<T> list, boolean z) {
        this.list = list;
        this.isReversed = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/iteration/Reverse$ReversedListIterator.class */
    public static class ReversedListIterator<T> implements ReversibleIterator<T> {
        private final List<T> list;
        private final boolean isReversed;
        private int index;

        ReversedListIterator(List<T> list, boolean z) {
            this.list = list;
            this.isReversed = z;
            if (z) {
                this.index = list.size() == 0 ? -1 : list.size() - 1;
            } else {
                this.index = list.size() == 0 ? -1 : 0;
            }
        }

        @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIterator
        public boolean isReversed() {
            return this.isReversed;
        }

        @Override // java.util.Iterator
        public void remove() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.index != -1;
        }

        @Override // java.util.Iterator
        public T next() {
            T t = this.list.get(this.index);
            if (this.index != -1) {
                if (this.isReversed) {
                    this.index--;
                } else if (this.index == this.list.size() - 1) {
                    this.index = -1;
                } else {
                    this.index++;
                }
            }
            return t;
        }
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIterable, java.lang.Iterable
    public ReversibleIterator<T> iterator() {
        return new ReversedListIterator(this.list, this.isReversed);
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIterable
    public ReversibleIterable<T> reversed() {
        return new Reverse(this.list, !this.isReversed);
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIterable
    public boolean isReversed() {
        return this.isReversed;
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIterable
    public ReversibleIterator<T> reversedIterator() {
        return new ReversedListIterator(this.list, !this.isReversed);
    }
}
