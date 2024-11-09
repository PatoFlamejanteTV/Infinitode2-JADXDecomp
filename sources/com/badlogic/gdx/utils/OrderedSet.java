package com.badlogic.gdx.utils;

import com.badlogic.gdx.utils.ObjectSet;
import java.util.NoSuchElementException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/OrderedSet.class */
public class OrderedSet<T> extends ObjectSet<T> {
    final Array<T> items;
    transient OrderedSetIterator iterator1;
    transient OrderedSetIterator iterator2;

    public OrderedSet() {
        this.items = new Array<>();
    }

    public OrderedSet(int i, float f) {
        super(i, f);
        this.items = new Array<>(i);
    }

    public OrderedSet(int i) {
        super(i);
        this.items = new Array<>(i);
    }

    public OrderedSet(OrderedSet<? extends T> orderedSet) {
        super(orderedSet);
        this.items = new Array<>(orderedSet.items);
    }

    @Override // com.badlogic.gdx.utils.ObjectSet
    public boolean add(T t) {
        if (!super.add(t)) {
            return false;
        }
        this.items.add(t);
        return true;
    }

    public boolean add(T t, int i) {
        if (!super.add(t)) {
            int indexOf = this.items.indexOf(t, true);
            if (indexOf != i) {
                Array<T> array = this.items;
                array.insert(i, array.removeIndex(indexOf));
                return false;
            }
            return false;
        }
        this.items.insert(i, t);
        return true;
    }

    public void addAll(OrderedSet<T> orderedSet) {
        ensureCapacity(orderedSet.size);
        T[] tArr = orderedSet.items.items;
        int i = orderedSet.items.size;
        for (int i2 = 0; i2 < i; i2++) {
            add(tArr[i2]);
        }
    }

    @Override // com.badlogic.gdx.utils.ObjectSet
    public boolean remove(T t) {
        if (!super.remove(t)) {
            return false;
        }
        this.items.removeValue(t, false);
        return true;
    }

    public T removeIndex(int i) {
        T removeIndex = this.items.removeIndex(i);
        super.remove(removeIndex);
        return removeIndex;
    }

    public boolean alter(T t, T t2) {
        if (contains(t2) || !super.remove(t)) {
            return false;
        }
        super.add(t2);
        this.items.set(this.items.indexOf(t, false), t2);
        return true;
    }

    public boolean alterIndex(int i, T t) {
        if (i < 0 || i >= this.size || contains(t)) {
            return false;
        }
        super.remove(this.items.get(i));
        super.add(t);
        this.items.set(i, t);
        return true;
    }

    @Override // com.badlogic.gdx.utils.ObjectSet
    public void clear(int i) {
        this.items.clear();
        super.clear(i);
    }

    @Override // com.badlogic.gdx.utils.ObjectSet
    public void clear() {
        this.items.clear();
        super.clear();
    }

    public Array<T> orderedItems() {
        return this.items;
    }

    @Override // com.badlogic.gdx.utils.ObjectSet, java.lang.Iterable
    public OrderedSetIterator<T> iterator() {
        if (Collections.allocateIterators) {
            return new OrderedSetIterator<>(this);
        }
        if (this.iterator1 == null) {
            this.iterator1 = new OrderedSetIterator(this);
            this.iterator2 = new OrderedSetIterator(this);
        }
        if (!this.iterator1.valid) {
            this.iterator1.reset();
            this.iterator1.valid = true;
            this.iterator2.valid = false;
            return this.iterator1;
        }
        this.iterator2.reset();
        this.iterator2.valid = true;
        this.iterator1.valid = false;
        return this.iterator2;
    }

    @Override // com.badlogic.gdx.utils.ObjectSet
    public String toString() {
        if (this.size == 0) {
            return "{}";
        }
        T[] tArr = this.items.items;
        java.lang.StringBuilder sb = new java.lang.StringBuilder(32);
        sb.append('{');
        sb.append(tArr[0]);
        for (int i = 1; i < this.size; i++) {
            sb.append(", ");
            sb.append(tArr[i]);
        }
        sb.append('}');
        return sb.toString();
    }

    @Override // com.badlogic.gdx.utils.ObjectSet
    public String toString(String str) {
        return this.items.toString(str);
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/OrderedSet$OrderedSetIterator.class */
    public static class OrderedSetIterator<K> extends ObjectSet.ObjectSetIterator<K> {
        private Array<K> items;

        public OrderedSetIterator(OrderedSet<K> orderedSet) {
            super(orderedSet);
            this.items = orderedSet.items;
        }

        @Override // com.badlogic.gdx.utils.ObjectSet.ObjectSetIterator
        public void reset() {
            this.nextIndex = 0;
            this.hasNext = this.set.size > 0;
        }

        @Override // com.badlogic.gdx.utils.ObjectSet.ObjectSetIterator, java.util.Iterator
        public K next() {
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            if (!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            K k = this.items.get(this.nextIndex);
            this.nextIndex++;
            this.hasNext = this.nextIndex < this.set.size;
            return k;
        }

        @Override // com.badlogic.gdx.utils.ObjectSet.ObjectSetIterator, java.util.Iterator
        public void remove() {
            if (this.nextIndex < 0) {
                throw new IllegalStateException("next must be called before remove.");
            }
            this.nextIndex--;
            ((OrderedSet) this.set).removeIndex(this.nextIndex);
        }

        @Override // com.badlogic.gdx.utils.ObjectSet.ObjectSetIterator
        public Array<K> toArray(Array<K> array) {
            array.addAll((Array<? extends K>) this.items, this.nextIndex, this.items.size - this.nextIndex);
            this.nextIndex = this.items.size;
            this.hasNext = false;
            return array;
        }

        @Override // com.badlogic.gdx.utils.ObjectSet.ObjectSetIterator
        public Array<K> toArray() {
            return toArray(new Array<>(true, this.set.size - this.nextIndex));
        }
    }

    public static <T> OrderedSet<T> with(T... tArr) {
        OrderedSet<T> orderedSet = new OrderedSet<>();
        orderedSet.addAll(tArr);
        return orderedSet;
    }
}
