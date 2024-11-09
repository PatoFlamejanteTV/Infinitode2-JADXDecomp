package com.badlogic.gdx.utils;

import java.util.Arrays;
import java.util.NoSuchElementException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/IntSet.class */
public class IntSet {
    public int size;
    int[] keyTable;
    boolean hasZeroValue;
    private final float loadFactor;
    private int threshold;
    protected int shift;
    protected int mask;
    private transient IntSetIterator iterator1;
    private transient IntSetIterator iterator2;

    public IntSet() {
        this(51, 0.8f);
    }

    public IntSet(int i) {
        this(i, 0.8f);
    }

    public IntSet(int i, float f) {
        if (f <= 0.0f || f >= 1.0f) {
            throw new IllegalArgumentException("loadFactor must be > 0 and < 1: " + f);
        }
        this.loadFactor = f;
        int tableSize = ObjectSet.tableSize(i, f);
        this.threshold = (int) (tableSize * f);
        this.mask = tableSize - 1;
        this.shift = Long.numberOfLeadingZeros(this.mask);
        this.keyTable = new int[tableSize];
    }

    public IntSet(IntSet intSet) {
        this((int) (intSet.keyTable.length * intSet.loadFactor), intSet.loadFactor);
        System.arraycopy(intSet.keyTable, 0, this.keyTable, 0, intSet.keyTable.length);
        this.size = intSet.size;
        this.hasZeroValue = intSet.hasZeroValue;
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

    public boolean add(int i) {
        if (i == 0) {
            if (this.hasZeroValue) {
                return false;
            }
            this.hasZeroValue = true;
            this.size++;
            return true;
        }
        int locateKey = locateKey(i);
        if (locateKey >= 0) {
            return false;
        }
        this.keyTable[-(locateKey + 1)] = i;
        int i2 = this.size + 1;
        this.size = i2;
        if (i2 >= this.threshold) {
            resize(this.keyTable.length << 1);
            return true;
        }
        return true;
    }

    public void addAll(IntArray intArray) {
        addAll(intArray.items, 0, intArray.size);
    }

    public void addAll(IntArray intArray, int i, int i2) {
        if (i + i2 > intArray.size) {
            throw new IllegalArgumentException("offset + length must be <= size: " + i + " + " + i2 + " <= " + intArray.size);
        }
        addAll(intArray.items, i, i2);
    }

    public void addAll(int... iArr) {
        addAll(iArr, 0, iArr.length);
    }

    public void addAll(int[] iArr, int i, int i2) {
        ensureCapacity(i2);
        int i3 = i + i2;
        for (int i4 = i; i4 < i3; i4++) {
            add(iArr[i4]);
        }
    }

    public void addAll(IntSet intSet) {
        ensureCapacity(intSet.size);
        if (intSet.hasZeroValue) {
            add(0);
        }
        for (int i : intSet.keyTable) {
            if (i != 0) {
                add(i);
            }
        }
    }

    private void addResize(int i) {
        int[] iArr = this.keyTable;
        int place = place(i);
        while (true) {
            int i2 = place;
            if (iArr[i2] != 0) {
                place = (i2 + 1) & this.mask;
            } else {
                iArr[i2] = i;
                return;
            }
        }
    }

    public boolean remove(int i) {
        if (i == 0) {
            if (!this.hasZeroValue) {
                return false;
            }
            this.hasZeroValue = false;
            this.size--;
            return true;
        }
        int locateKey = locateKey(i);
        int i2 = locateKey;
        if (locateKey < 0) {
            return false;
        }
        int[] iArr = this.keyTable;
        int i3 = this.mask;
        int i4 = i2;
        while (true) {
            int i5 = (i4 + 1) & i3;
            int i6 = iArr[i5];
            if (i6 != 0) {
                int place = place(i6);
                if (((i5 - place) & i3) > ((i2 - place) & i3)) {
                    iArr[i2] = i6;
                    i2 = i5;
                }
                i4 = i5;
            } else {
                iArr[i2] = 0;
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
        this.size = 0;
        Arrays.fill(this.keyTable, 0);
        this.hasZeroValue = false;
    }

    public boolean contains(int i) {
        return i == 0 ? this.hasZeroValue : locateKey(i) >= 0;
    }

    public int first() {
        if (this.hasZeroValue) {
            return 0;
        }
        int[] iArr = this.keyTable;
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            if (iArr[i] != 0) {
                return iArr[i];
            }
        }
        throw new IllegalStateException("IntSet is empty.");
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
        this.keyTable = new int[i];
        if (this.size > 0) {
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = iArr[i2];
                if (i3 != 0) {
                    addResize(i3);
                }
            }
        }
    }

    public int hashCode() {
        int i = this.size;
        for (int i2 : this.keyTable) {
            if (i2 != 0) {
                i += i2;
            }
        }
        return i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof IntSet)) {
            return false;
        }
        IntSet intSet = (IntSet) obj;
        if (intSet.size != this.size || intSet.hasZeroValue != this.hasZeroValue) {
            return false;
        }
        int[] iArr = this.keyTable;
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            if (iArr[i] != 0 && !intSet.contains(iArr[i])) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006c A[SYNTHETIC] */
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
            r1 = r0
            r6 = r1
            int r0 = r0.length
            r7 = r0
            r0 = r4
            boolean r0 = r0.hasZeroValue
            if (r0 == 0) goto L34
            r0 = r5
            java.lang.String r1 = "0"
            java.lang.StringBuilder r0 = r0.append(r1)
            goto L4b
        L34:
            r0 = r7
            int r7 = r7 + (-1)
            if (r0 <= 0) goto L4b
            r0 = r6
            r1 = r7
            r0 = r0[r1]
            r1 = r0
            r8 = r1
            if (r0 == 0) goto L34
            r0 = r5
            r1 = r8
            java.lang.StringBuilder r0 = r0.append(r1)
        L4b:
            r0 = r7
            int r7 = r7 + (-1)
            if (r0 <= 0) goto L6c
            r0 = r6
            r1 = r7
            r0 = r0[r1]
            r1 = r0
            r8 = r1
            if (r0 == 0) goto L4b
            r0 = r5
            java.lang.String r1 = ", "
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r5
            r1 = r8
            java.lang.StringBuilder r0 = r0.append(r1)
            goto L4b
        L6c:
            r0 = r5
            r1 = 93
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r5
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.utils.IntSet.toString():java.lang.String");
    }

    public IntSetIterator iterator() {
        if (Collections.allocateIterators) {
            return new IntSetIterator(this);
        }
        if (this.iterator1 == null) {
            this.iterator1 = new IntSetIterator(this);
            this.iterator2 = new IntSetIterator(this);
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

    public static IntSet with(int... iArr) {
        IntSet intSet = new IntSet();
        intSet.addAll(iArr);
        return intSet;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/IntSet$IntSetIterator.class */
    public static class IntSetIterator {
        private static final int INDEX_ILLEGAL = -2;
        private static final int INDEX_ZERO = -1;
        public boolean hasNext;
        final IntSet set;
        int nextIndex;
        int currentIndex;
        boolean valid = true;

        public IntSetIterator(IntSet intSet) {
            this.set = intSet;
            reset();
        }

        public void reset() {
            this.currentIndex = -2;
            this.nextIndex = -1;
            if (this.set.hasZeroValue) {
                this.hasNext = true;
            } else {
                findNextIndex();
            }
        }

        void findNextIndex() {
            int[] iArr = this.set.keyTable;
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
            if (i == -1 && this.set.hasZeroValue) {
                this.set.hasZeroValue = false;
            } else {
                if (i2 < 0) {
                    throw new IllegalStateException("next must be called before remove.");
                }
                int[] iArr = this.set.keyTable;
                int i3 = this.set.mask;
                int i4 = i2;
                while (true) {
                    int i5 = (i4 + 1) & i3;
                    int i6 = iArr[i5];
                    if (i6 == 0) {
                        break;
                    }
                    int place = this.set.place(i6);
                    if (((i5 - place) & i3) > ((i2 - place) & i3)) {
                        iArr[i2] = i6;
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
            this.set.size--;
        }

        public int next() {
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            if (!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            int i = this.nextIndex == -1 ? 0 : this.set.keyTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            findNextIndex();
            return i;
        }

        public IntArray toArray() {
            IntArray intArray = new IntArray(true, this.set.size);
            while (this.hasNext) {
                intArray.add(next());
            }
            return intArray;
        }
    }
}
