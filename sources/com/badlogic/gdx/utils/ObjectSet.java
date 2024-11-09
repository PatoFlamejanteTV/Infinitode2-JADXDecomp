package com.badlogic.gdx.utils;

import com.badlogic.gdx.math.MathUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/ObjectSet.class */
public class ObjectSet<T> implements Iterable<T> {
    public int size;
    T[] keyTable;
    float loadFactor;
    int threshold;
    protected int shift;
    protected int mask;
    private transient ObjectSetIterator iterator1;
    private transient ObjectSetIterator iterator2;

    public ObjectSet() {
        this(51, 0.8f);
    }

    public ObjectSet(int i) {
        this(i, 0.8f);
    }

    public ObjectSet(int i, float f) {
        if (f <= 0.0f || f >= 1.0f) {
            throw new IllegalArgumentException("loadFactor must be > 0 and < 1: " + f);
        }
        this.loadFactor = f;
        int tableSize = tableSize(i, f);
        this.threshold = (int) (tableSize * f);
        this.mask = tableSize - 1;
        this.shift = Long.numberOfLeadingZeros(this.mask);
        this.keyTable = (T[]) new Object[tableSize];
    }

    public ObjectSet(ObjectSet<? extends T> objectSet) {
        this((int) (objectSet.keyTable.length * objectSet.loadFactor), objectSet.loadFactor);
        System.arraycopy(objectSet.keyTable, 0, this.keyTable, 0, objectSet.keyTable.length);
        this.size = objectSet.size;
    }

    protected int place(T t) {
        return (int) ((t.hashCode() * (-7046029254386353131L)) >>> this.shift);
    }

    int locateKey(T t) {
        if (t == null) {
            throw new IllegalArgumentException("key cannot be null.");
        }
        T[] tArr = this.keyTable;
        int place = place(t);
        while (true) {
            int i = place;
            T t2 = tArr[i];
            if (t2 == null) {
                return -(i + 1);
            }
            if (t2.equals(t)) {
                return i;
            }
            place = (i + 1) & this.mask;
        }
    }

    public boolean add(T t) {
        int locateKey = locateKey(t);
        if (locateKey >= 0) {
            return false;
        }
        this.keyTable[-(locateKey + 1)] = t;
        int i = this.size + 1;
        this.size = i;
        if (i >= this.threshold) {
            resize(this.keyTable.length << 1);
            return true;
        }
        return true;
    }

    public void addAll(Array<? extends T> array) {
        addAll(array.items, 0, array.size);
    }

    public void addAll(Array<? extends T> array, int i, int i2) {
        if (i + i2 > array.size) {
            throw new IllegalArgumentException("offset + length must be <= size: " + i + " + " + i2 + " <= " + array.size);
        }
        addAll(array.items, i, i2);
    }

    public boolean addAll(T... tArr) {
        return addAll(tArr, 0, tArr.length);
    }

    public boolean addAll(T[] tArr, int i, int i2) {
        ensureCapacity(i2);
        int i3 = this.size;
        int i4 = i + i2;
        for (int i5 = i; i5 < i4; i5++) {
            add(tArr[i5]);
        }
        return i3 != this.size;
    }

    public void addAll(ObjectSet<T> objectSet) {
        ensureCapacity(objectSet.size);
        for (T t : objectSet.keyTable) {
            if (t != null) {
                add(t);
            }
        }
    }

    private void addResize(T t) {
        T[] tArr = this.keyTable;
        int place = place(t);
        while (true) {
            int i = place;
            if (tArr[i] != null) {
                place = (i + 1) & this.mask;
            } else {
                tArr[i] = t;
                return;
            }
        }
    }

    public boolean remove(T t) {
        int locateKey = locateKey(t);
        int i = locateKey;
        if (locateKey < 0) {
            return false;
        }
        T[] tArr = this.keyTable;
        int i2 = this.mask;
        int i3 = i;
        while (true) {
            int i4 = (i3 + 1) & i2;
            T t2 = tArr[i4];
            if (t2 != null) {
                int place = place(t2);
                if (((i4 - place) & i2) > ((i - place) & i2)) {
                    tArr[i] = t2;
                    i = i4;
                }
                i3 = i4;
            } else {
                tArr[i] = null;
                this.size--;
                return true;
            }
        }
    }

    public boolean notEmpty() {
        return this.size > 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void shrink(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("maximumCapacity must be >= 0: " + i);
        }
        int tableSize = tableSize(i, this.loadFactor);
        if (this.keyTable.length > tableSize) {
            resize(tableSize);
        }
    }

    public void clear(int i) {
        int tableSize = tableSize(i, this.loadFactor);
        if (this.keyTable.length <= tableSize) {
            clear();
        } else {
            this.size = 0;
            resize(tableSize);
        }
    }

    public void clear() {
        if (this.size == 0) {
            return;
        }
        this.size = 0;
        Arrays.fill(this.keyTable, (Object) null);
    }

    public boolean contains(T t) {
        return locateKey(t) >= 0;
    }

    @Null
    public T get(T t) {
        int locateKey = locateKey(t);
        if (locateKey < 0) {
            return null;
        }
        return this.keyTable[locateKey];
    }

    public T first() {
        T[] tArr = this.keyTable;
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            if (tArr[i] != null) {
                return tArr[i];
            }
        }
        throw new IllegalStateException("ObjectSet is empty.");
    }

    public void ensureCapacity(int i) {
        int tableSize = tableSize(this.size + i, this.loadFactor);
        if (this.keyTable.length < tableSize) {
            resize(tableSize);
        }
    }

    private void resize(int i) {
        int length = this.keyTable.length;
        this.threshold = (int) (i * this.loadFactor);
        this.mask = i - 1;
        this.shift = Long.numberOfLeadingZeros(this.mask);
        T[] tArr = this.keyTable;
        this.keyTable = (T[]) new Object[i];
        if (this.size > 0) {
            for (int i2 = 0; i2 < length; i2++) {
                T t = tArr[i2];
                if (t != null) {
                    addResize(t);
                }
            }
        }
    }

    public int hashCode() {
        int i = this.size;
        for (T t : this.keyTable) {
            if (t != null) {
                i += t.hashCode();
            }
        }
        return i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ObjectSet)) {
            return false;
        }
        ObjectSet objectSet = (ObjectSet) obj;
        if (objectSet.size != this.size) {
            return false;
        }
        T[] tArr = this.keyTable;
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            if (tArr[i] != null && !objectSet.contains(tArr[i])) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return "{" + toString(", ") + '}';
    }

    public String toString(String str) {
        if (this.size == 0) {
            return "";
        }
        java.lang.StringBuilder sb = new java.lang.StringBuilder(32);
        T[] tArr = this.keyTable;
        int length = tArr.length;
        while (true) {
            int i = length;
            length--;
            if (i <= 0) {
                break;
            }
            T t = tArr[length];
            if (t != null) {
                sb.append(t == this ? "(this)" : t);
            }
        }
        while (true) {
            int i2 = length;
            length--;
            if (i2 > 0) {
                T t2 = tArr[length];
                if (t2 != null) {
                    sb.append(str);
                    sb.append(t2 == this ? "(this)" : t2);
                }
            } else {
                return sb.toString();
            }
        }
    }

    @Override // java.lang.Iterable
    public ObjectSetIterator<T> iterator() {
        if (Collections.allocateIterators) {
            return new ObjectSetIterator<>(this);
        }
        if (this.iterator1 == null) {
            this.iterator1 = new ObjectSetIterator(this);
            this.iterator2 = new ObjectSetIterator(this);
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

    public static <T> ObjectSet<T> with(T... tArr) {
        ObjectSet<T> objectSet = new ObjectSet<>();
        objectSet.addAll(tArr);
        return objectSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int tableSize(int i, float f) {
        if (i < 0) {
            throw new IllegalArgumentException("capacity must be >= 0: " + i);
        }
        int nextPowerOfTwo = MathUtils.nextPowerOfTwo(Math.max(2, (int) Math.ceil(i / f)));
        if (nextPowerOfTwo > 1073741824) {
            throw new IllegalArgumentException("The required capacity is too large: " + i);
        }
        return nextPowerOfTwo;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/ObjectSet$ObjectSetIterator.class */
    public static class ObjectSetIterator<K> implements Iterable<K>, Iterator<K> {
        public boolean hasNext;
        final ObjectSet<K> set;
        int nextIndex;
        int currentIndex;
        boolean valid = true;

        public ObjectSetIterator(ObjectSet<K> objectSet) {
            this.set = objectSet;
            reset();
        }

        public void reset() {
            this.currentIndex = -1;
            this.nextIndex = -1;
            findNextIndex();
        }

        private void findNextIndex() {
            K[] kArr = this.set.keyTable;
            int length = this.set.keyTable.length;
            do {
                int i = this.nextIndex + 1;
                this.nextIndex = i;
                if (i >= length) {
                    this.hasNext = false;
                    return;
                }
            } while (kArr[this.nextIndex] == null);
            this.hasNext = true;
        }

        @Override // java.util.Iterator
        public void remove() {
            int i = this.currentIndex;
            int i2 = i;
            if (i < 0) {
                throw new IllegalStateException("next must be called before remove.");
            }
            K[] kArr = this.set.keyTable;
            int i3 = this.set.mask;
            int i4 = i2;
            while (true) {
                int i5 = (i4 + 1) & i3;
                K k = kArr[i5];
                if (k == null) {
                    break;
                }
                int place = this.set.place(k);
                if (((i5 - place) & i3) > ((i2 - place) & i3)) {
                    kArr[i2] = k;
                    i2 = i5;
                }
                i4 = i5;
            }
            kArr[i2] = null;
            this.set.size--;
            if (i2 != this.currentIndex) {
                this.nextIndex--;
            }
            this.currentIndex = -1;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.valid) {
                return this.hasNext;
            }
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
        }

        @Override // java.util.Iterator
        public K next() {
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            if (!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            K k = this.set.keyTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            findNextIndex();
            return k;
        }

        @Override // java.lang.Iterable
        public ObjectSetIterator<K> iterator() {
            return this;
        }

        public Array<K> toArray(Array<K> array) {
            while (this.hasNext) {
                array.add(next());
            }
            return array;
        }

        public Array<K> toArray() {
            return toArray(new Array<>(true, this.set.size));
        }
    }
}
