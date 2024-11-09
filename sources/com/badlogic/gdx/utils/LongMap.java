package com.badlogic.gdx.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/LongMap.class */
public class LongMap<V> implements Iterable<Entry<V>> {
    public int size;
    long[] keyTable;
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

    public LongMap() {
        this(51, 0.8f);
    }

    public LongMap(int i) {
        this(i, 0.8f);
    }

    public LongMap(int i, float f) {
        if (f <= 0.0f || f >= 1.0f) {
            throw new IllegalArgumentException("loadFactor must be > 0 and < 1: " + f);
        }
        this.loadFactor = f;
        int tableSize = ObjectSet.tableSize(i, f);
        this.threshold = (int) (tableSize * f);
        this.mask = tableSize - 1;
        this.shift = Long.numberOfLeadingZeros(this.mask);
        this.keyTable = new long[tableSize];
        this.valueTable = (V[]) new Object[tableSize];
    }

    public LongMap(LongMap<? extends V> longMap) {
        this((int) (longMap.keyTable.length * longMap.loadFactor), longMap.loadFactor);
        System.arraycopy(longMap.keyTable, 0, this.keyTable, 0, longMap.keyTable.length);
        System.arraycopy(longMap.valueTable, 0, this.valueTable, 0, longMap.valueTable.length);
        this.size = longMap.size;
        this.zeroValue = longMap.zeroValue;
        this.hasZeroValue = longMap.hasZeroValue;
    }

    protected int place(long j) {
        return (int) (((j ^ (j >>> 32)) * (-7046029254386353131L)) >>> this.shift);
    }

    private int locateKey(long j) {
        long[] jArr = this.keyTable;
        int place = place(j);
        while (true) {
            int i = place;
            long j2 = jArr[i];
            if (j2 == 0) {
                return -(i + 1);
            }
            if (j2 == j) {
                return i;
            }
            place = (i + 1) & this.mask;
        }
    }

    @Null
    public V put(long j, @Null V v) {
        if (j == 0) {
            V v2 = this.zeroValue;
            this.zeroValue = v;
            if (!this.hasZeroValue) {
                this.hasZeroValue = true;
                this.size++;
            }
            return v2;
        }
        int locateKey = locateKey(j);
        if (locateKey >= 0) {
            V v3 = this.valueTable[locateKey];
            this.valueTable[locateKey] = v;
            return v3;
        }
        int i = -(locateKey + 1);
        this.keyTable[i] = j;
        this.valueTable[i] = v;
        int i2 = this.size + 1;
        this.size = i2;
        if (i2 >= this.threshold) {
            resize(this.keyTable.length << 1);
            return null;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void putAll(LongMap<? extends V> longMap) {
        V v;
        ensureCapacity(longMap.size);
        if (longMap.hasZeroValue) {
            v = longMap.zeroValue;
            put(0L, v);
        }
        long[] jArr = longMap.keyTable;
        V[] vArr = longMap.valueTable;
        int length = jArr.length;
        for (int i = 0; i < length; i++) {
            long j = v;
            if (jArr[i] != 0) {
                v = vArr[i];
                put(j, v);
            }
        }
    }

    private void putResize(long j, @Null V v) {
        long[] jArr = this.keyTable;
        int place = place(j);
        while (true) {
            int i = place;
            if (jArr[i] != 0) {
                place = (i + 1) & this.mask;
            } else {
                jArr[i] = j;
                this.valueTable[i] = v;
                return;
            }
        }
    }

    @Null
    public V get(long j) {
        if (j == 0) {
            if (this.hasZeroValue) {
                return this.zeroValue;
            }
            return null;
        }
        int locateKey = locateKey(j);
        if (locateKey >= 0) {
            return this.valueTable[locateKey];
        }
        return null;
    }

    public V get(long j, @Null V v) {
        if (j == 0) {
            return this.hasZeroValue ? this.zeroValue : v;
        }
        int locateKey = locateKey(j);
        return locateKey >= 0 ? this.valueTable[locateKey] : v;
    }

    @Null
    public V remove(long j) {
        if (j == 0) {
            if (!this.hasZeroValue) {
                return null;
            }
            this.hasZeroValue = false;
            V v = this.zeroValue;
            this.zeroValue = null;
            this.size--;
            return v;
        }
        int locateKey = locateKey(j);
        int i = locateKey;
        if (locateKey < 0) {
            return null;
        }
        long[] jArr = this.keyTable;
        V[] vArr = this.valueTable;
        V v2 = vArr[i];
        int i2 = this.mask;
        int i3 = i;
        while (true) {
            int i4 = (i3 + 1) & i2;
            long j2 = jArr[i4];
            if (j2 != 0) {
                int place = place(j2);
                if (((i4 - place) & i2) > ((i - place) & i2)) {
                    jArr[i] = j2;
                    vArr[i] = vArr[i4];
                    i = i4;
                }
                i3 = i4;
            } else {
                jArr[i] = 0;
                vArr[i] = null;
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
        Arrays.fill(this.keyTable, 0L);
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
            long[] jArr = this.keyTable;
            for (int length = vArr.length - 1; length >= 0; length--) {
                if (jArr[length] != 0 && vArr[length] == null) {
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

    public boolean containsKey(long j) {
        return j == 0 ? this.hasZeroValue : locateKey(j) >= 0;
    }

    public long findKey(@Null Object obj, boolean z, long j) {
        V[] vArr = this.valueTable;
        if (obj == null) {
            if (this.hasZeroValue && this.zeroValue == null) {
                return 0L;
            }
            long[] jArr = this.keyTable;
            for (int length = vArr.length - 1; length >= 0; length--) {
                if (jArr[length] != 0 && vArr[length] == null) {
                    return jArr[length];
                }
            }
        } else if (z) {
            if (obj == this.zeroValue) {
                return 0L;
            }
            for (int length2 = vArr.length - 1; length2 >= 0; length2--) {
                if (vArr[length2] == obj) {
                    return this.keyTable[length2];
                }
            }
        } else {
            if (this.hasZeroValue && obj.equals(this.zeroValue)) {
                return 0L;
            }
            for (int length3 = vArr.length - 1; length3 >= 0; length3--) {
                if (obj.equals(vArr[length3])) {
                    return this.keyTable[length3];
                }
            }
        }
        return j;
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
        long[] jArr = this.keyTable;
        V[] vArr = this.valueTable;
        this.keyTable = new long[i];
        this.valueTable = (V[]) new Object[i];
        if (this.size > 0) {
            for (int i2 = 0; i2 < length; i2++) {
                long j = jArr[i2];
                if (j != 0) {
                    putResize(j, vArr[i2]);
                }
            }
        }
    }

    public int hashCode() {
        int i = this.size;
        if (this.hasZeroValue && this.zeroValue != null) {
            i += this.zeroValue.hashCode();
        }
        long[] jArr = this.keyTable;
        V[] vArr = this.valueTable;
        int length = jArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            long j = jArr[i2];
            if (j != 0) {
                i = (int) (i + (j * 31));
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
        if (!(obj instanceof LongMap)) {
            return false;
        }
        LongMap longMap = (LongMap) obj;
        if (longMap.size != this.size || longMap.hasZeroValue != this.hasZeroValue) {
            return false;
        }
        if (this.hasZeroValue) {
            if (longMap.zeroValue == null) {
                if (this.zeroValue != null) {
                    return false;
                }
            } else if (!longMap.zeroValue.equals(this.zeroValue)) {
                return false;
            }
        }
        long[] jArr = this.keyTable;
        V[] vArr = this.valueTable;
        int length = jArr.length;
        for (int i = 0; i < length; i++) {
            long j = jArr[i];
            if (j != 0) {
                V v = vArr[i];
                if (v == null) {
                    if (longMap.get(j, ObjectMap.dummy) != null) {
                        return false;
                    }
                } else if (!v.equals(longMap.get(j))) {
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
        if (!(obj instanceof LongMap)) {
            return false;
        }
        LongMap longMap = (LongMap) obj;
        if (longMap.size != this.size || longMap.hasZeroValue != this.hasZeroValue) {
            return false;
        }
        if (this.hasZeroValue && this.zeroValue != longMap.zeroValue) {
            return false;
        }
        long[] jArr = this.keyTable;
        V[] vArr = this.valueTable;
        int length = jArr.length;
        for (int i = 0; i < length; i++) {
            long j = jArr[i];
            if (j != 0 && vArr[i] != longMap.get(j, ObjectMap.dummy)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00a3 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String toString() {
        /*
            r5 = this;
            r0 = r5
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
            r6 = r1
            r1 = 91
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r5
            long[] r0 = r0.keyTable
            r7 = r0
            r0 = r5
            V[] r0 = r0.valueTable
            r8 = r0
            r0 = r7
            int r0 = r0.length
            r9 = r0
            r0 = r5
            boolean r0 = r0.hasZeroValue
            if (r0 == 0) goto L43
            r0 = r6
            java.lang.String r1 = "0="
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r6
            r1 = r5
            V r1 = r1.zeroValue
            java.lang.StringBuilder r0 = r0.append(r1)
            goto L6e
        L43:
            r0 = r9
            int r9 = r9 + (-1)
            if (r0 <= 0) goto L6e
            r0 = r7
            r1 = r9
            r0 = r0[r1]
            r1 = r0; r1 = r0; 
            r10 = r1
            r1 = 0
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 == 0) goto L43
            r0 = r6
            r1 = r10
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r6
            r1 = 61
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r6
            r1 = r8
            r2 = r9
            r1 = r1[r2]
            java.lang.StringBuilder r0 = r0.append(r1)
        L6e:
            r0 = r9
            int r9 = r9 + (-1)
            if (r0 <= 0) goto La3
            r0 = r7
            r1 = r9
            r0 = r0[r1]
            r1 = r0; r1 = r0; 
            r10 = r1
            r1 = 0
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 == 0) goto L6e
            r0 = r6
            java.lang.String r1 = ", "
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r6
            r1 = r10
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r6
            r1 = 61
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r6
            r1 = r8
            r2 = r9
            r1 = r1[r2]
            java.lang.StringBuilder r0 = r0.append(r1)
            goto L6e
        La3:
            r0 = r6
            r1 = 93
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r6
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.utils.LongMap.toString():java.lang.String");
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

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/LongMap$Entry.class */
    public static class Entry<V> {
        public long key;

        @Null
        public V value;

        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/LongMap$MapIterator.class */
    public static class MapIterator<V> {
        private static final int INDEX_ILLEGAL = -2;
        static final int INDEX_ZERO = -1;
        public boolean hasNext;
        final LongMap<V> map;
        int nextIndex;
        int currentIndex;
        boolean valid = true;

        public MapIterator(LongMap<V> longMap) {
            this.map = longMap;
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
            long[] jArr = this.map.keyTable;
            int length = jArr.length;
            do {
                int i = this.nextIndex + 1;
                this.nextIndex = i;
                if (i >= length) {
                    this.hasNext = false;
                    return;
                }
            } while (jArr[this.nextIndex] == 0);
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
                long[] jArr = this.map.keyTable;
                V[] vArr = this.map.valueTable;
                int i3 = this.map.mask;
                int i4 = i2;
                while (true) {
                    int i5 = (i4 + 1) & i3;
                    long j = jArr[i5];
                    if (j == 0) {
                        break;
                    }
                    int place = this.map.place(j);
                    if (((i5 - place) & i3) > ((i2 - place) & i3)) {
                        jArr[i2] = j;
                        vArr[i2] = vArr[i5];
                        i2 = i5;
                    }
                    i4 = i5;
                }
                jArr[i2] = 0;
                vArr[i2] = null;
                if (i2 != this.currentIndex) {
                    this.nextIndex--;
                }
            }
            this.currentIndex = -2;
            this.map.size--;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/LongMap$Entries.class */
    public static class Entries<V> extends MapIterator<V> implements Iterable<Entry<V>>, Iterator<Entry<V>> {
        private final Entry<V> entry;

        @Override // com.badlogic.gdx.utils.LongMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.badlogic.gdx.utils.LongMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public Entries(LongMap longMap) {
            super(longMap);
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
            long[] jArr = this.map.keyTable;
            if (this.nextIndex == -1) {
                this.entry.key = 0L;
                this.entry.value = this.map.zeroValue;
            } else {
                this.entry.key = jArr[this.nextIndex];
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

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/LongMap$Values.class */
    public static class Values<V> extends MapIterator<V> implements Iterable<V>, Iterator<V> {
        @Override // com.badlogic.gdx.utils.LongMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.badlogic.gdx.utils.LongMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public Values(LongMap<V> longMap) {
            super(longMap);
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

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/LongMap$Keys.class */
    public static class Keys extends MapIterator {
        @Override // com.badlogic.gdx.utils.LongMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.badlogic.gdx.utils.LongMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public Keys(LongMap longMap) {
            super(longMap);
        }

        public long next() {
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            if (!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            long j = this.nextIndex == -1 ? 0L : this.map.keyTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            findNextIndex();
            return j;
        }

        public LongArray toArray() {
            LongArray longArray = new LongArray(true, this.map.size);
            while (this.hasNext) {
                longArray.add(next());
            }
            return longArray;
        }

        public LongArray toArray(LongArray longArray) {
            while (this.hasNext) {
                longArray.add(next());
            }
            return longArray;
        }
    }
}
