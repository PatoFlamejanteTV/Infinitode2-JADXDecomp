package com.badlogic.gdx.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/IntMap.class */
public class IntMap<V> implements Iterable<Entry<V>> {
    public int size;
    int[] keyTable;
    V[] valueTable;
    V zeroValue;
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

    public IntMap() {
        this(51, 0.8f);
    }

    public IntMap(int i) {
        this(i, 0.8f);
    }

    public IntMap(int i, float f) {
        if (f <= 0.0f || f >= 1.0f) {
            throw new IllegalArgumentException("loadFactor must be > 0 and < 1: " + f);
        }
        this.loadFactor = f;
        int tableSize = ObjectSet.tableSize(i, f);
        this.threshold = (int) (tableSize * f);
        this.mask = tableSize - 1;
        this.shift = Long.numberOfLeadingZeros(this.mask);
        this.keyTable = new int[tableSize];
        this.valueTable = (V[]) new Object[tableSize];
    }

    public IntMap(IntMap<? extends V> intMap) {
        this((int) (intMap.keyTable.length * intMap.loadFactor), intMap.loadFactor);
        System.arraycopy(intMap.keyTable, 0, this.keyTable, 0, intMap.keyTable.length);
        System.arraycopy(intMap.valueTable, 0, this.valueTable, 0, intMap.valueTable.length);
        this.size = intMap.size;
        this.zeroValue = intMap.zeroValue;
        this.hasZeroValue = intMap.hasZeroValue;
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

    @Null
    public V put(int i, @Null V v) {
        if (i == 0) {
            V v2 = this.zeroValue;
            this.zeroValue = v;
            if (!this.hasZeroValue) {
                this.hasZeroValue = true;
                this.size++;
            }
            return v2;
        }
        int locateKey = locateKey(i);
        if (locateKey >= 0) {
            V v3 = this.valueTable[locateKey];
            this.valueTable[locateKey] = v;
            return v3;
        }
        int i2 = -(locateKey + 1);
        this.keyTable[i2] = i;
        this.valueTable[i2] = v;
        int i3 = this.size + 1;
        this.size = i3;
        if (i3 >= this.threshold) {
            resize(this.keyTable.length << 1);
            return null;
        }
        return null;
    }

    public void putAll(IntMap<? extends V> intMap) {
        ensureCapacity(intMap.size);
        if (intMap.hasZeroValue) {
            put(0, intMap.zeroValue);
        }
        int[] iArr = intMap.keyTable;
        V[] vArr = intMap.valueTable;
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            int i2 = iArr[i];
            if (i2 != 0) {
                put(i2, vArr[i]);
            }
        }
    }

    private void putResize(int i, @Null V v) {
        int[] iArr = this.keyTable;
        int place = place(i);
        while (true) {
            int i2 = place;
            if (iArr[i2] != 0) {
                place = (i2 + 1) & this.mask;
            } else {
                iArr[i2] = i;
                this.valueTable[i2] = v;
                return;
            }
        }
    }

    public V get(int i) {
        if (i == 0) {
            if (this.hasZeroValue) {
                return this.zeroValue;
            }
            return null;
        }
        int locateKey = locateKey(i);
        if (locateKey >= 0) {
            return this.valueTable[locateKey];
        }
        return null;
    }

    public V get(int i, @Null V v) {
        if (i == 0) {
            return this.hasZeroValue ? this.zeroValue : v;
        }
        int locateKey = locateKey(i);
        return locateKey >= 0 ? this.valueTable[locateKey] : v;
    }

    @Null
    public V remove(int i) {
        if (i == 0) {
            if (!this.hasZeroValue) {
                return null;
            }
            this.hasZeroValue = false;
            V v = this.zeroValue;
            this.zeroValue = null;
            this.size--;
            return v;
        }
        int locateKey = locateKey(i);
        int i2 = locateKey;
        if (locateKey < 0) {
            return null;
        }
        int[] iArr = this.keyTable;
        V[] vArr = this.valueTable;
        V v2 = vArr[i2];
        int i3 = this.mask;
        int i4 = i2;
        while (true) {
            int i5 = (i4 + 1) & i3;
            int i6 = iArr[i5];
            if (i6 != 0) {
                int place = place(i6);
                if (((i5 - place) & i3) > ((i2 - place) & i3)) {
                    iArr[i2] = i6;
                    vArr[i2] = vArr[i5];
                    i2 = i5;
                }
                i4 = i5;
            } else {
                iArr[i2] = 0;
                vArr[i2] = null;
                this.size--;
                return v2;
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
        this.zeroValue = null;
        resize(tableSize);
    }

    public void clear() {
        if (this.size == 0) {
            return;
        }
        this.size = 0;
        Arrays.fill(this.keyTable, 0);
        Arrays.fill(this.valueTable, (Object) null);
        this.zeroValue = null;
        this.hasZeroValue = false;
    }

    public boolean containsValue(@Null Object obj, boolean z) {
        V[] vArr = this.valueTable;
        if (obj == null) {
            if (this.hasZeroValue && this.zeroValue == null) {
                return true;
            }
            int[] iArr = this.keyTable;
            for (int length = vArr.length - 1; length >= 0; length--) {
                if (iArr[length] != 0 && vArr[length] == null) {
                    return true;
                }
            }
            return false;
        }
        if (z) {
            if (obj == this.zeroValue) {
                return true;
            }
            for (int length2 = vArr.length - 1; length2 >= 0; length2--) {
                if (vArr[length2] == obj) {
                    return true;
                }
            }
            return false;
        }
        if (this.hasZeroValue && obj.equals(this.zeroValue)) {
            return true;
        }
        for (int length3 = vArr.length - 1; length3 >= 0; length3--) {
            if (obj.equals(vArr[length3])) {
                return true;
            }
        }
        return false;
    }

    public boolean containsKey(int i) {
        return i == 0 ? this.hasZeroValue : locateKey(i) >= 0;
    }

    public int findKey(@Null Object obj, boolean z, int i) {
        V[] vArr = this.valueTable;
        if (obj == null) {
            if (this.hasZeroValue && this.zeroValue == null) {
                return 0;
            }
            int[] iArr = this.keyTable;
            for (int length = vArr.length - 1; length >= 0; length--) {
                if (iArr[length] != 0 && vArr[length] == null) {
                    return iArr[length];
                }
            }
        } else if (z) {
            if (obj == this.zeroValue) {
                return 0;
            }
            for (int length2 = vArr.length - 1; length2 >= 0; length2--) {
                if (vArr[length2] == obj) {
                    return this.keyTable[length2];
                }
            }
        } else {
            if (this.hasZeroValue && obj.equals(this.zeroValue)) {
                return 0;
            }
            for (int length3 = vArr.length - 1; length3 >= 0; length3--) {
                if (obj.equals(vArr[length3])) {
                    return this.keyTable[length3];
                }
            }
        }
        return i;
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
        V[] vArr = this.valueTable;
        this.keyTable = new int[i];
        this.valueTable = (V[]) new Object[i];
        if (this.size > 0) {
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = iArr[i2];
                if (i3 != 0) {
                    putResize(i3, vArr[i2]);
                }
            }
        }
    }

    public int hashCode() {
        int i = this.size;
        if (this.hasZeroValue && this.zeroValue != null) {
            i += this.zeroValue.hashCode();
        }
        int[] iArr = this.keyTable;
        V[] vArr = this.valueTable;
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = iArr[i2];
            if (i3 != 0) {
                i += i3 * 31;
                V v = vArr[i2];
                if (v != null) {
                    i += v.hashCode();
                }
            }
        }
        return i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IntMap)) {
            return false;
        }
        IntMap intMap = (IntMap) obj;
        if (intMap.size != this.size || intMap.hasZeroValue != this.hasZeroValue) {
            return false;
        }
        if (this.hasZeroValue) {
            if (intMap.zeroValue == null) {
                if (this.zeroValue != null) {
                    return false;
                }
            } else if (!intMap.zeroValue.equals(this.zeroValue)) {
                return false;
            }
        }
        int[] iArr = this.keyTable;
        V[] vArr = this.valueTable;
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            int i2 = iArr[i];
            if (i2 != 0) {
                V v = vArr[i];
                if (v == null) {
                    if (intMap.get(i2, ObjectMap.dummy) != null) {
                        return false;
                    }
                } else if (!v.equals(intMap.get(i2))) {
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean equalsIdentity(@Null Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IntMap)) {
            return false;
        }
        IntMap intMap = (IntMap) obj;
        if (intMap.size != this.size || intMap.hasZeroValue != this.hasZeroValue) {
            return false;
        }
        if (this.hasZeroValue && this.zeroValue != intMap.zeroValue) {
            return false;
        }
        int[] iArr = this.keyTable;
        V[] vArr = this.valueTable;
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            int i2 = iArr[i];
            if (i2 != 0 && vArr[i] != intMap.get(i2, ObjectMap.dummy)) {
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
            V[] r0 = r0.valueTable
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
            V r1 = r1.zeroValue
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
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.utils.IntMap.toString():java.lang.String");
    }

    @Override // java.lang.Iterable
    public Iterator<Entry<V>> iterator() {
        return entries();
    }

    public Entries<V> entries() {
        if (Collections.allocateIterators) {
            return new Entries<>(this);
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

    public Values<V> values() {
        if (Collections.allocateIterators) {
            return new Values<>(this);
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

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/IntMap$Entry.class */
    public static class Entry<V> {
        public int key;

        @Null
        public V value;

        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/IntMap$MapIterator.class */
    public static class MapIterator<V> {
        private static final int INDEX_ILLEGAL = -2;
        static final int INDEX_ZERO = -1;
        public boolean hasNext;
        final IntMap<V> map;
        int nextIndex;
        int currentIndex;
        boolean valid = true;

        public MapIterator(IntMap<V> intMap) {
            this.map = intMap;
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
                this.map.zeroValue = null;
            } else {
                if (i2 < 0) {
                    throw new IllegalStateException("next must be called before remove.");
                }
                int[] iArr = this.map.keyTable;
                V[] vArr = this.map.valueTable;
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
                        vArr[i2] = vArr[i5];
                        i2 = i5;
                    }
                    i4 = i5;
                }
                iArr[i2] = 0;
                vArr[i2] = null;
                if (i2 != this.currentIndex) {
                    this.nextIndex--;
                }
            }
            this.currentIndex = -2;
            this.map.size--;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/IntMap$Entries.class */
    public static class Entries<V> extends MapIterator<V> implements Iterable<Entry<V>>, Iterator<Entry<V>> {
        private final Entry<V> entry;

        @Override // com.badlogic.gdx.utils.IntMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.badlogic.gdx.utils.IntMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public Entries(IntMap intMap) {
            super(intMap);
            this.entry = new Entry<>();
        }

        @Override // java.util.Iterator
        public Entry<V> next() {
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
        public Iterator<Entry<V>> iterator() {
            return this;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/IntMap$Values.class */
    public static class Values<V> extends MapIterator<V> implements Iterable<V>, Iterator<V> {
        @Override // com.badlogic.gdx.utils.IntMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.badlogic.gdx.utils.IntMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public Values(IntMap<V> intMap) {
            super(intMap);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.valid) {
                return this.hasNext;
            }
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
        }

        @Override // java.util.Iterator
        @Null
        public V next() {
            V v;
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            if (!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            if (this.nextIndex == -1) {
                v = this.map.zeroValue;
            } else {
                v = this.map.valueTable[this.nextIndex];
            }
            this.currentIndex = this.nextIndex;
            findNextIndex();
            return v;
        }

        @Override // java.lang.Iterable
        public Iterator<V> iterator() {
            return this;
        }

        public Array<V> toArray() {
            Array<V> array = new Array<>(true, this.map.size);
            while (this.hasNext) {
                array.add(next());
            }
            return array;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/IntMap$Keys.class */
    public static class Keys extends MapIterator {
        @Override // com.badlogic.gdx.utils.IntMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.badlogic.gdx.utils.IntMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public Keys(IntMap intMap) {
            super(intMap);
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
