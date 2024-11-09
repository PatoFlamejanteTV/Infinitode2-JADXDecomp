package com.badlogic.gdx.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/IntIntMap.class */
public class IntIntMap implements Iterable<Entry> {
    public int size;
    int[] keyTable;
    int[] valueTable;
    int zeroValue;
    boolean hasZeroValue;
    private final float loadFactor;
    private int threshold;
    protected int shift;
    protected int mask;
    private transient Entries entries1;
    private transient Entries entries2;
    private transient Values values1;
    private transient Values values2;
    private transient Keys keys1;
    private transient Keys keys2;

    public IntIntMap() {
        this(51, 0.8f);
    }

    public IntIntMap(int i) {
        this(i, 0.8f);
    }

    public IntIntMap(int i, float f) {
        if (f <= 0.0f || f >= 1.0f) {
            throw new IllegalArgumentException("loadFactor must be > 0 and < 1: " + f);
        }
        this.loadFactor = f;
        int tableSize = ObjectSet.tableSize(i, f);
        this.threshold = (int) (tableSize * f);
        this.mask = tableSize - 1;
        this.shift = Long.numberOfLeadingZeros(this.mask);
        this.keyTable = new int[tableSize];
        this.valueTable = new int[tableSize];
    }

    public IntIntMap(IntIntMap intIntMap) {
        this((int) (intIntMap.keyTable.length * intIntMap.loadFactor), intIntMap.loadFactor);
        System.arraycopy(intIntMap.keyTable, 0, this.keyTable, 0, intIntMap.keyTable.length);
        System.arraycopy(intIntMap.valueTable, 0, this.valueTable, 0, intIntMap.valueTable.length);
        this.size = intIntMap.size;
        this.zeroValue = intIntMap.zeroValue;
        this.hasZeroValue = intIntMap.hasZeroValue;
    }

    protected int place(int i) {
        return (int) ((i * (-7046029254386353131L)) >>> this.shift);
    }

    private int locateKey(int i) {
        int[] iArr = this.keyTable;
        int place = place(i);
        while (true) {
            int i2 = place;
            int i3 = iArr[i2];
            if (i3 == 0) {
                return -(i2 + 1);
            }
            if (i3 == i) {
                return i2;
            }
            place = (i2 + 1) & this.mask;
        }
    }

    public void put(int i, int i2) {
        if (i == 0) {
            this.zeroValue = i2;
            if (!this.hasZeroValue) {
                this.hasZeroValue = true;
                this.size++;
                return;
            }
            return;
        }
        int locateKey = locateKey(i);
        if (locateKey >= 0) {
            this.valueTable[locateKey] = i2;
            return;
        }
        int i3 = -(locateKey + 1);
        this.keyTable[i3] = i;
        this.valueTable[i3] = i2;
        int i4 = this.size + 1;
        this.size = i4;
        if (i4 >= this.threshold) {
            resize(this.keyTable.length << 1);
        }
    }

    public int put(int i, int i2, int i3) {
        if (i == 0) {
            int i4 = this.zeroValue;
            this.zeroValue = i2;
            if (!this.hasZeroValue) {
                this.hasZeroValue = true;
                this.size++;
                return i3;
            }
            return i4;
        }
        int locateKey = locateKey(i);
        if (locateKey >= 0) {
            int i5 = this.valueTable[locateKey];
            this.valueTable[locateKey] = i2;
            return i5;
        }
        int i6 = -(locateKey + 1);
        this.keyTable[i6] = i;
        this.valueTable[i6] = i2;
        int i7 = this.size + 1;
        this.size = i7;
        if (i7 >= this.threshold) {
            resize(this.keyTable.length << 1);
        }
        return i3;
    }

    public void putAll(IntIntMap intIntMap) {
        ensureCapacity(intIntMap.size);
        if (intIntMap.hasZeroValue) {
            put(0, intIntMap.zeroValue);
        }
        int[] iArr = intIntMap.keyTable;
        int[] iArr2 = intIntMap.valueTable;
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            int i2 = iArr[i];
            if (i2 != 0) {
                put(i2, iArr2[i]);
            }
        }
    }

    private void putResize(int i, int i2) {
        int[] iArr = this.keyTable;
        int place = place(i);
        while (true) {
            int i3 = place;
            if (iArr[i3] != 0) {
                place = (i3 + 1) & this.mask;
            } else {
                iArr[i3] = i;
                this.valueTable[i3] = i2;
                return;
            }
        }
    }

    public int get(int i, int i2) {
        if (i == 0) {
            return this.hasZeroValue ? this.zeroValue : i2;
        }
        int locateKey = locateKey(i);
        return locateKey >= 0 ? this.valueTable[locateKey] : i2;
    }

    public int getAndIncrement(int i, int i2, int i3) {
        if (i == 0) {
            if (!this.hasZeroValue) {
                this.hasZeroValue = true;
                this.zeroValue = i2 + i3;
                this.size++;
                return i2;
            }
            int i4 = this.zeroValue;
            this.zeroValue += i3;
            return i4;
        }
        int locateKey = locateKey(i);
        if (locateKey >= 0) {
            int i5 = this.valueTable[locateKey];
            int[] iArr = this.valueTable;
            iArr[locateKey] = iArr[locateKey] + i3;
            return i5;
        }
        int i6 = -(locateKey + 1);
        this.keyTable[i6] = i;
        this.valueTable[i6] = i2 + i3;
        int i7 = this.size + 1;
        this.size = i7;
        if (i7 >= this.threshold) {
            resize(this.keyTable.length << 1);
        }
        return i2;
    }

    public int remove(int i, int i2) {
        if (i == 0) {
            if (!this.hasZeroValue) {
                return i2;
            }
            this.hasZeroValue = false;
            this.size--;
            return this.zeroValue;
        }
        int locateKey = locateKey(i);
        int i3 = locateKey;
        if (locateKey < 0) {
            return i2;
        }
        int[] iArr = this.keyTable;
        int[] iArr2 = this.valueTable;
        int i4 = iArr2[i3];
        int i5 = this.mask;
        int i6 = i3;
        while (true) {
            int i7 = (i6 + 1) & i5;
            int i8 = iArr[i7];
            if (i8 != 0) {
                int place = place(i8);
                if (((i7 - place) & i5) > ((i3 - place) & i5)) {
                    iArr[i3] = i8;
                    iArr2[i3] = iArr2[i7];
                    i3 = i7;
                }
                i6 = i7;
            } else {
                iArr[i3] = 0;
                this.size--;
                return i4;
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
        int tableSize = ObjectSet.tableSize(i, this.loadFactor);
        if (this.keyTable.length > tableSize) {
            resize(tableSize);
        }
    }

    public void clear(int i) {
        int tableSize = ObjectSet.tableSize(i, this.loadFactor);
        if (this.keyTable.length <= tableSize) {
            clear();
            return;
        }
        this.size = 0;
        this.hasZeroValue = false;
        resize(tableSize);
    }

    public void clear() {
        if (this.size == 0) {
            return;
        }
        Arrays.fill(this.keyTable, 0);
        this.size = 0;
        this.hasZeroValue = false;
    }

    public boolean containsValue(int i) {
        if (this.hasZeroValue && this.zeroValue == i) {
            return true;
        }
        int[] iArr = this.keyTable;
        int[] iArr2 = this.valueTable;
        for (int length = iArr2.length - 1; length >= 0; length--) {
            if (iArr[length] != 0 && iArr2[length] == i) {
                return true;
            }
        }
        return false;
    }

    public boolean containsKey(int i) {
        return i == 0 ? this.hasZeroValue : locateKey(i) >= 0;
    }

    public int findKey(int i, int i2) {
        if (this.hasZeroValue && this.zeroValue == i) {
            return 0;
        }
        int[] iArr = this.keyTable;
        int[] iArr2 = this.valueTable;
        for (int length = iArr2.length - 1; length >= 0; length--) {
            int i3 = iArr[length];
            if (i3 != 0 && iArr2[length] == i) {
                return i3;
            }
        }
        return i2;
    }

    public void ensureCapacity(int i) {
        int tableSize = ObjectSet.tableSize(this.size + i, this.loadFactor);
        if (this.keyTable.length < tableSize) {
            resize(tableSize);
        }
    }

    private void resize(int i) {
        int length = this.keyTable.length;
        this.threshold = (int) (i * this.loadFactor);
        this.mask = i - 1;
        this.shift = Long.numberOfLeadingZeros(this.mask);
        int[] iArr = this.keyTable;
        int[] iArr2 = this.valueTable;
        this.keyTable = new int[i];
        this.valueTable = new int[i];
        if (this.size > 0) {
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = iArr[i2];
                if (i3 != 0) {
                    putResize(i3, iArr2[i2]);
                }
            }
        }
    }

    public int hashCode() {
        int i = this.size;
        if (this.hasZeroValue) {
            i += this.zeroValue;
        }
        int[] iArr = this.keyTable;
        int[] iArr2 = this.valueTable;
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = iArr[i2];
            if (i3 != 0) {
                i += (i3 * 31) + iArr2[i2];
            }
        }
        return i;
    }

    public boolean equals(Object obj) {
        int i;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IntIntMap)) {
            return false;
        }
        IntIntMap intIntMap = (IntIntMap) obj;
        if (intIntMap.size != this.size || intIntMap.hasZeroValue != this.hasZeroValue) {
            return false;
        }
        if (this.hasZeroValue && intIntMap.zeroValue != this.zeroValue) {
            return false;
        }
        int[] iArr = this.keyTable;
        int[] iArr2 = this.valueTable;
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = iArr[i2];
            if (i3 != 0 && (((i = intIntMap.get(i3, 0)) == 0 && !intIntMap.containsKey(i3)) || i != iArr2[i2])) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x009f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String toString() {
        /*
            r4 = this;
            r0 = r4
            int r0 = r0.size
            if (r0 != 0) goto La
            java.lang.String r0 = "[]"
            return r0
        La:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = r0
            r2 = 32
            r1.<init>(r2)
            r1 = r0
            r5 = r1
            r1 = 91
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r4
            int[] r0 = r0.keyTable
            r6 = r0
            r0 = r4
            int[] r0 = r0.valueTable
            r7 = r0
            r0 = r6
            int r0 = r0.length
            r8 = r0
            r0 = r4
            boolean r0 = r0.hasZeroValue
            if (r0 == 0) goto L43
            r0 = r5
            java.lang.String r1 = "0="
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r5
            r1 = r4
            int r1 = r1.zeroValue
            java.lang.StringBuilder r0 = r0.append(r1)
            goto L6c
        L43:
            r0 = r8
            int r8 = r8 + (-1)
            if (r0 <= 0) goto L6c
            r0 = r6
            r1 = r8
            r0 = r0[r1]
            r1 = r0
            r9 = r1
            if (r0 == 0) goto L43
            r0 = r5
            r1 = r9
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r5
            r1 = 61
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r5
            r1 = r7
            r2 = r8
            r1 = r1[r2]
            java.lang.StringBuilder r0 = r0.append(r1)
        L6c:
            r0 = r8
            int r8 = r8 + (-1)
            if (r0 <= 0) goto L9f
            r0 = r6
            r1 = r8
            r0 = r0[r1]
            r1 = r0
            r9 = r1
            if (r0 == 0) goto L6c
            r0 = r5
            java.lang.String r1 = ", "
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r5
            r1 = r9
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r5
            r1 = 61
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r5
            r1 = r7
            r2 = r8
            r1 = r1[r2]
            java.lang.StringBuilder r0 = r0.append(r1)
            goto L6c
        L9f:
            r0 = r5
            r1 = 93
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r5
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.utils.IntIntMap.toString():java.lang.String");
    }

    @Override // java.lang.Iterable
    public Iterator<Entry> iterator() {
        return entries();
    }

    public Entries entries() {
        if (Collections.allocateIterators) {
            return new Entries(this);
        }
        if (this.entries1 == null) {
            this.entries1 = new Entries(this);
            this.entries2 = new Entries(this);
        }
        if (!this.entries1.valid) {
            this.entries1.reset();
            this.entries1.valid = true;
            this.entries2.valid = false;
            return this.entries1;
        }
        this.entries2.reset();
        this.entries2.valid = true;
        this.entries1.valid = false;
        return this.entries2;
    }

    public Values values() {
        if (Collections.allocateIterators) {
            return new Values(this);
        }
        if (this.values1 == null) {
            this.values1 = new Values(this);
            this.values2 = new Values(this);
        }
        if (!this.values1.valid) {
            this.values1.reset();
            this.values1.valid = true;
            this.values2.valid = false;
            return this.values1;
        }
        this.values2.reset();
        this.values2.valid = true;
        this.values1.valid = false;
        return this.values2;
    }

    public Keys keys() {
        if (Collections.allocateIterators) {
            return new Keys(this);
        }
        if (this.keys1 == null) {
            this.keys1 = new Keys(this);
            this.keys2 = new Keys(this);
        }
        if (!this.keys1.valid) {
            this.keys1.reset();
            this.keys1.valid = true;
            this.keys2.valid = false;
            return this.keys1;
        }
        this.keys2.reset();
        this.keys2.valid = true;
        this.keys1.valid = false;
        return this.keys2;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/IntIntMap$Entry.class */
    public static class Entry {
        public int key;
        public int value;

        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/IntIntMap$MapIterator.class */
    public static class MapIterator {
        private static final int INDEX_ILLEGAL = -2;
        static final int INDEX_ZERO = -1;
        public boolean hasNext;
        final IntIntMap map;
        int nextIndex;
        int currentIndex;
        boolean valid = true;

        public MapIterator(IntIntMap intIntMap) {
            this.map = intIntMap;
            reset();
        }

        public void reset() {
            this.currentIndex = -2;
            this.nextIndex = -1;
            if (this.map.hasZeroValue) {
                this.hasNext = true;
            } else {
                findNextIndex();
            }
        }

        void findNextIndex() {
            int[] iArr = this.map.keyTable;
            int length = iArr.length;
            do {
                int i = this.nextIndex + 1;
                this.nextIndex = i;
                if (i >= length) {
                    this.hasNext = false;
                    return;
                }
            } while (iArr[this.nextIndex] == 0);
            this.hasNext = true;
        }

        public void remove() {
            int i = this.currentIndex;
            int i2 = i;
            if (i == -1 && this.map.hasZeroValue) {
                this.map.hasZeroValue = false;
            } else {
                if (i2 < 0) {
                    throw new IllegalStateException("next must be called before remove.");
                }
                int[] iArr = this.map.keyTable;
                int[] iArr2 = this.map.valueTable;
                int i3 = this.map.mask;
                int i4 = i2;
                while (true) {
                    int i5 = (i4 + 1) & i3;
                    int i6 = iArr[i5];
                    if (i6 == 0) {
                        break;
                    }
                    int place = this.map.place(i6);
                    if (((i5 - place) & i3) > ((i2 - place) & i3)) {
                        iArr[i2] = i6;
                        iArr2[i2] = iArr2[i5];
                        i2 = i5;
                    }
                    i4 = i5;
                }
                iArr[i2] = 0;
                if (i2 != this.currentIndex) {
                    this.nextIndex--;
                }
            }
            this.currentIndex = -2;
            this.map.size--;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/IntIntMap$Entries.class */
    public static class Entries extends MapIterator implements Iterable<Entry>, Iterator<Entry> {
        private final Entry entry;

        @Override // com.badlogic.gdx.utils.IntIntMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.badlogic.gdx.utils.IntIntMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public Entries(IntIntMap intIntMap) {
            super(intIntMap);
            this.entry = new Entry();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Entry next() {
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            if (!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            int[] iArr = this.map.keyTable;
            if (this.nextIndex == -1) {
                this.entry.key = 0;
                this.entry.value = this.map.zeroValue;
            } else {
                this.entry.key = iArr[this.nextIndex];
                this.entry.value = this.map.valueTable[this.nextIndex];
            }
            this.currentIndex = this.nextIndex;
            findNextIndex();
            return this.entry;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.valid) {
                return this.hasNext;
            }
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
        }

        @Override // java.lang.Iterable
        public Iterator<Entry> iterator() {
            return this;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/IntIntMap$Values.class */
    public static class Values extends MapIterator {
        @Override // com.badlogic.gdx.utils.IntIntMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.badlogic.gdx.utils.IntIntMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public Values(IntIntMap intIntMap) {
            super(intIntMap);
        }

        public boolean hasNext() {
            if (this.valid) {
                return this.hasNext;
            }
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
        }

        public int next() {
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            if (!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            int i = this.nextIndex == -1 ? this.map.zeroValue : this.map.valueTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            findNextIndex();
            return i;
        }

        public Values iterator() {
            return this;
        }

        public IntArray toArray() {
            IntArray intArray = new IntArray(true, this.map.size);
            while (this.hasNext) {
                intArray.add(next());
            }
            return intArray;
        }

        public IntArray toArray(IntArray intArray) {
            while (this.hasNext) {
                intArray.add(next());
            }
            return intArray;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/IntIntMap$Keys.class */
    public static class Keys extends MapIterator {
        @Override // com.badlogic.gdx.utils.IntIntMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.badlogic.gdx.utils.IntIntMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public Keys(IntIntMap intIntMap) {
            super(intIntMap);
        }

        public int next() {
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            if (!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            int i = this.nextIndex == -1 ? 0 : this.map.keyTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            findNextIndex();
            return i;
        }

        public IntArray toArray() {
            IntArray intArray = new IntArray(true, this.map.size);
            while (this.hasNext) {
                intArray.add(next());
            }
            return intArray;
        }

        public IntArray toArray(IntArray intArray) {
            while (this.hasNext) {
                intArray.add(next());
            }
            return intArray;
        }
    }
}
