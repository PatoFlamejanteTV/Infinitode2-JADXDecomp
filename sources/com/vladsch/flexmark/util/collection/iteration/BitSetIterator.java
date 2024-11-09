package com.vladsch.flexmark.util.collection.iteration;

import java.util.BitSet;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/iteration/BitSetIterator.class */
public class BitSetIterator implements ReversibleIterator<Integer> {
    private final BitSet bitSet;
    private final boolean reversed;
    private int next;
    private int last;

    public BitSetIterator(BitSet bitSet) {
        this(bitSet, false);
    }

    public BitSetIterator(BitSet bitSet, boolean z) {
        this.bitSet = bitSet;
        this.reversed = z;
        this.next = z ? bitSet.previousSetBit(bitSet.length()) : bitSet.nextSetBit(0);
        this.last = -1;
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
    public Integer next() {
        if (this.next == -1) {
            throw new NoSuchElementException();
        }
        this.last = this.next;
        this.next = this.reversed ? this.next == 0 ? -1 : this.bitSet.previousSetBit(this.next - 1) : this.bitSet.nextSetBit(this.next + 1);
        return Integer.valueOf(this.last);
    }

    @Override // java.util.Iterator
    public void remove() {
        if (this.last == -1) {
            throw new NoSuchElementException();
        }
        this.bitSet.clear(this.last);
    }

    @Override // java.util.Iterator
    public void forEachRemaining(Consumer<? super Integer> consumer) {
        while (hasNext()) {
            consumer.accept(next());
        }
    }
}
