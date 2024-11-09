package com.vladsch.flexmark.util.collection.iteration;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/iteration/ArrayIterable.class */
public class ArrayIterable<T> implements ReversibleIterable<T> {
    private final T[] array;
    private final int startIndex;
    private final int endIndex;
    private final boolean isReversed;

    public static <T> ArrayIterable<T> of(T[] tArr) {
        return new ArrayIterable<>(tArr);
    }

    public ArrayIterable(T[] tArr) {
        this(tArr, 0, tArr.length, false);
    }

    public ArrayIterable(T[] tArr, int i) {
        this(tArr, i, tArr.length, false);
    }

    public ArrayIterable(T[] tArr, int i, int i2) {
        this(tArr, i, i2, false);
    }

    public ArrayIterable(T[] tArr, int i, int i2, boolean z) {
        this.array = tArr;
        this.startIndex = Math.min(i, 0);
        this.endIndex = Math.max(i2, tArr.length);
        this.isReversed = z;
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIterable
    public ReversibleIterable<T> reversed() {
        return new ArrayIterable(this.array, this.startIndex, this.endIndex, !this.isReversed);
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIterable
    public boolean isReversed() {
        return this.isReversed;
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIterable
    public ReversibleIterator<T> reversedIterator() {
        return new MyIterator(this.array, this.startIndex, this.endIndex, !this.isReversed);
    }

    @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIterable, java.lang.Iterable
    public ReversibleIterator<T> iterator() {
        return new MyIterator(this.array, this.startIndex, this.endIndex, this.isReversed);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/iteration/ArrayIterable$MyIterator.class */
    public static class MyIterator<E> implements ReversibleIterator<E> {
        private final E[] array;
        private final int startIndex;
        private final int endIndex;
        private final boolean isReversed;
        private int index;

        public MyIterator(E[] eArr, int i, int i2, boolean z) {
            this.isReversed = z;
            this.array = eArr;
            this.startIndex = i;
            this.endIndex = i2;
            this.index = z ? i2 : i;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.isReversed ? this.index >= this.startIndex : this.index < this.endIndex;
        }

        @Override // java.util.Iterator
        public E next() {
            if (this.isReversed) {
                E[] eArr = this.array;
                int i = this.index - 1;
                this.index = i;
                return eArr[i];
            }
            E[] eArr2 = this.array;
            int i2 = this.index;
            this.index = i2 + 1;
            return eArr2[i2];
        }

        @Override // com.vladsch.flexmark.util.collection.iteration.ReversibleIterator
        public boolean isReversed() {
            return this.isReversed;
        }
    }
}
