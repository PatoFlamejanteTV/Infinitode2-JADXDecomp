package com.esotericsoftware.kryonet.util;

import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.util.IntArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/util/ObjectIntMap.class */
public class ObjectIntMap<K> implements Iterable<Entry<K>> {
    public int size;
    K[] keyTable;
    int[] valueTable;
    float loadFactor;
    int threshold;
    protected int shift;
    protected int mask;

    public ObjectIntMap() {
        this(51, 0.8f);
    }

    public ObjectIntMap(int i) {
        this(i, 0.8f);
    }

    public ObjectIntMap(int i, float f) {
        if (f <= 0.0f || f >= 1.0f) {
            throw new IllegalArgumentException("loadFactor must be > 0 and < 1: " + f);
        }
        this.loadFactor = f;
        int tableSize = tableSize(i, f);
        this.threshold = (int) (tableSize * f);
        this.mask = tableSize - 1;
        this.shift = Long.numberOfLeadingZeros(this.mask);
        this.keyTable = (K[]) new Object[tableSize];
        this.valueTable = new int[tableSize];
    }

    public ObjectIntMap(ObjectIntMap<? extends K> objectIntMap) {
        this((int) (objectIntMap.keyTable.length * objectIntMap.loadFactor), objectIntMap.loadFactor);
        System.arraycopy(objectIntMap.keyTable, 0, this.keyTable, 0, objectIntMap.keyTable.length);
        System.arraycopy(objectIntMap.valueTable, 0, this.valueTable, 0, objectIntMap.valueTable.length);
        this.size = objectIntMap.size;
    }

    protected int place(K k) {
        return (int) ((k.hashCode() * (-7046029254386353131L)) >>> this.shift);
    }

    int locateKey(K k) {
        if (k == null) {
            throw new IllegalArgumentException("key cannot be null.");
        }
        K[] kArr = this.keyTable;
        int place = place(k);
        while (true) {
            int i = place;
            K k2 = kArr[i];
            if (k2 == null) {
                return -(i + 1);
            }
            if (!k2.equals(k)) {
                place = (i + 1) & this.mask;
            } else {
                return i;
            }
        }
    }

    public void put(K k, int i) {
        int locateKey = locateKey(k);
        if (locateKey >= 0) {
            this.valueTable[locateKey] = i;
            return;
        }
        int i2 = -(locateKey + 1);
        this.keyTable[i2] = k;
        this.valueTable[i2] = i;
        int i3 = this.size + 1;
        this.size = i3;
        if (i3 >= this.threshold) {
            resize(this.keyTable.length << 1);
        }
    }

    public void putAll(ObjectIntMap<? extends K> objectIntMap) {
        ensureCapacity(objectIntMap.size);
        K[] kArr = objectIntMap.keyTable;
        int[] iArr = objectIntMap.valueTable;
        int length = kArr.length;
        for (int i = 0; i < length; i++) {
            K k = kArr[i];
            if (k != null) {
                put(k, iArr[i]);
            }
        }
    }

    private void putResize(K k, int i) {
        K[] kArr = this.keyTable;
        int place = place(k);
        while (true) {
            int i2 = place;
            if (kArr[i2] != null) {
                place = (i2 + 1) & this.mask;
            } else {
                kArr[i2] = k;
                this.valueTable[i2] = i;
                return;
            }
        }
    }

    public int get(K k, int i) {
        int locateKey = locateKey(k);
        return locateKey < 0 ? i : this.valueTable[locateKey];
    }

    public int getAndIncrement(K k, int i, int i2) {
        int locateKey = locateKey(k);
        if (locateKey >= 0) {
            int i3 = this.valueTable[locateKey];
            int[] iArr = this.valueTable;
            iArr[locateKey] = iArr[locateKey] + i2;
            return i3;
        }
        int i4 = -(locateKey + 1);
        this.keyTable[i4] = k;
        this.valueTable[i4] = i + i2;
        int i5 = this.size + 1;
        this.size = i5;
        if (i5 >= this.threshold) {
            resize(this.keyTable.length << 1);
        }
        return i;
    }

    public int remove(K k, int i) {
        int locateKey = locateKey(k);
        int i2 = locateKey;
        if (locateKey < 0) {
            return i;
        }
        K[] kArr = this.keyTable;
        int[] iArr = this.valueTable;
        int i3 = iArr[i2];
        int i4 = this.mask;
        int i5 = i2;
        while (true) {
            int i6 = (i5 + 1) & i4;
            K k2 = kArr[i6];
            if (k2 != null) {
                int place = place(k2);
                if (((i6 - place) & i4) > ((i2 - place) & i4)) {
                    kArr[i2] = k2;
                    iArr[i2] = iArr[i6];
                    i2 = i6;
                }
                i5 = i6;
            } else {
                kArr[i2] = null;
                this.size--;
                return i3;
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

    public boolean containsValue(int i) {
        K[] kArr = this.keyTable;
        int[] iArr = this.valueTable;
        for (int length = iArr.length - 1; length >= 0; length--) {
            if (kArr[length] != null && iArr[length] == i) {
                return true;
            }
        }
        return false;
    }

    public boolean containsKey(K k) {
        return locateKey(k) >= 0;
    }

    public K findKey(int i) {
        K[] kArr = this.keyTable;
        int[] iArr = this.valueTable;
        for (int length = iArr.length - 1; length >= 0; length--) {
            K k = kArr[length];
            if (k != null && iArr[length] == i) {
                return k;
            }
        }
        return null;
    }

    public void ensureCapacity(int i) {
        int tableSize = tableSize(this.size + i, this.loadFactor);
        if (this.keyTable.length < tableSize) {
            resize(tableSize);
        }
    }

    final void resize(int i) {
        int length = this.keyTable.length;
        this.threshold = (int) (i * this.loadFactor);
        this.mask = i - 1;
        this.shift = Long.numberOfLeadingZeros(this.mask);
        K[] kArr = this.keyTable;
        int[] iArr = this.valueTable;
        this.keyTable = (K[]) new Object[i];
        this.valueTable = new int[i];
        if (this.size > 0) {
            for (int i2 = 0; i2 < length; i2++) {
                K k = kArr[i2];
                if (k != null) {
                    putResize(k, iArr[i2]);
                }
            }
        }
    }

    public int hashCode() {
        int i = this.size;
        K[] kArr = this.keyTable;
        int[] iArr = this.valueTable;
        int length = kArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            K k = kArr[i2];
            if (k != null) {
                i += k.hashCode() + iArr[i2];
            }
        }
        return i;
    }

    public boolean equals(Object obj) {
        int i;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ObjectIntMap)) {
            return false;
        }
        ObjectIntMap objectIntMap = (ObjectIntMap) obj;
        if (objectIntMap.size != this.size) {
            return false;
        }
        K[] kArr = this.keyTable;
        int[] iArr = this.valueTable;
        int length = kArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            K k = kArr[i2];
            if (k != null && (((i = objectIntMap.get(k, 0)) == 0 && !objectIntMap.containsKey(k)) || i != iArr[i2])) {
                return false;
            }
        }
        return true;
    }

    public String toString(String str) {
        return toString(str, false);
    }

    public String toString() {
        return toString(", ", true);
    }

    private String toString(String str, boolean z) {
        if (this.size == 0) {
            return z ? "{}" : "";
        }
        StringBuilder sb = new StringBuilder(32);
        if (z) {
            sb.append('{');
        }
        K[] kArr = this.keyTable;
        int[] iArr = this.valueTable;
        int length = kArr.length;
        while (true) {
            int i = length;
            length--;
            if (i <= 0) {
                break;
            }
            K k = kArr[length];
            if (k != null) {
                sb.append(k);
                sb.append('=');
                sb.append(iArr[length]);
                break;
            }
        }
        while (true) {
            int i2 = length;
            length--;
            if (i2 <= 0) {
                break;
            }
            K k2 = kArr[length];
            if (k2 != null) {
                sb.append(str);
                sb.append(k2);
                sb.append('=');
                sb.append(iArr[length]);
            }
        }
        if (z) {
            sb.append('}');
        }
        return sb.toString();
    }

    @Override // java.lang.Iterable
    public Entries<K> iterator() {
        return entries();
    }

    public Entries<K> entries() {
        return new Entries<>(this);
    }

    public Values values() {
        return new Values(this);
    }

    public Keys<K> keys() {
        return new Keys<>(this);
    }

    private static int tableSize(int i, float f) {
        if (i < 0) {
            throw new IllegalArgumentException("capacity must be >= 0: " + i);
        }
        int nextPowerOfTwo = nextPowerOfTwo(Math.max(2, (int) Math.ceil(i / f)));
        if (nextPowerOfTwo > 1073741824) {
            throw new IllegalArgumentException("The required capacity is too large: " + i);
        }
        return nextPowerOfTwo;
    }

    private static int nextPowerOfTwo(int i) {
        if (i == 0) {
            return 1;
        }
        int i2 = i - 1;
        int i3 = i2 | (i2 >> 1);
        int i4 = i3 | (i3 >> 2);
        int i5 = i4 | (i4 >> 4);
        int i6 = i5 | (i5 >> 8);
        return (i6 | (i6 >> 16)) + 1;
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/util/ObjectIntMap$Entry.class */
    public static class Entry<K> {
        public K key;
        public int value;

        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/util/ObjectIntMap$MapIterator.class */
    public static class MapIterator<K> {
        public boolean hasNext;
        final ObjectIntMap<K> map;
        int nextIndex;
        int currentIndex;
        boolean valid = true;

        public MapIterator(ObjectIntMap<K> objectIntMap) {
            this.map = objectIntMap;
            reset();
        }

        public void reset() {
            this.currentIndex = -1;
            this.nextIndex = -1;
            findNextIndex();
        }

        void findNextIndex() {
            K[] kArr = this.map.keyTable;
            int length = kArr.length;
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

        public void remove() {
            int i = this.currentIndex;
            int i2 = i;
            if (i < 0) {
                throw new IllegalStateException("next must be called before remove.");
            }
            K[] kArr = this.map.keyTable;
            int[] iArr = this.map.valueTable;
            int i3 = this.map.mask;
            int i4 = i2;
            while (true) {
                int i5 = (i4 + 1) & i3;
                K k = kArr[i5];
                if (k == null) {
                    break;
                }
                int place = this.map.place(k);
                if (((i5 - place) & i3) > ((i2 - place) & i3)) {
                    kArr[i2] = k;
                    iArr[i2] = iArr[i5];
                    i2 = i5;
                }
                i4 = i5;
            }
            kArr[i2] = null;
            this.map.size--;
            if (i2 != this.currentIndex) {
                this.nextIndex--;
            }
            this.currentIndex = -1;
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/util/ObjectIntMap$Entries.class */
    public static class Entries<K> extends MapIterator<K> implements Iterable<Entry<K>>, Iterator<Entry<K>> {
        Entry<K> entry;

        @Override // com.esotericsoftware.kryonet.util.ObjectIntMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.esotericsoftware.kryonet.util.ObjectIntMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public Entries(ObjectIntMap<K> objectIntMap) {
            super(objectIntMap);
            this.entry = new Entry<>();
        }

        @Override // java.util.Iterator
        public Entry<K> next() {
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            if (!this.valid) {
                throw new KryoException("#iterator() cannot be used nested.");
            }
            K[] kArr = this.map.keyTable;
            this.entry.key = kArr[this.nextIndex];
            this.entry.value = this.map.valueTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            findNextIndex();
            return this.entry;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (!this.valid) {
                throw new KryoException("#iterator() cannot be used nested.");
            }
            return this.hasNext;
        }

        @Override // java.lang.Iterable
        public Entries<K> iterator() {
            return this;
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/util/ObjectIntMap$Values.class */
    public static class Values extends MapIterator<Object> {
        @Override // com.esotericsoftware.kryonet.util.ObjectIntMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.esotericsoftware.kryonet.util.ObjectIntMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public Values(ObjectIntMap<?> objectIntMap) {
            super(objectIntMap);
        }

        public boolean hasNext() {
            if (!this.valid) {
                throw new KryoException("#iterator() cannot be used nested.");
            }
            return this.hasNext;
        }

        public int next() {
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            if (!this.valid) {
                throw new KryoException("#iterator() cannot be used nested.");
            }
            int i = this.map.valueTable[this.nextIndex];
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

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/util/ObjectIntMap$Keys.class */
    public static class Keys<K> extends MapIterator<K> implements Iterable<K>, Iterator<K> {
        @Override // com.esotericsoftware.kryonet.util.ObjectIntMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.esotericsoftware.kryonet.util.ObjectIntMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public Keys(ObjectIntMap<K> objectIntMap) {
            super(objectIntMap);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (!this.valid) {
                throw new KryoException("#iterator() cannot be used nested.");
            }
            return this.hasNext;
        }

        @Override // java.util.Iterator
        public K next() {
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            if (!this.valid) {
                throw new KryoException("#iterator() cannot be used nested.");
            }
            K k = this.map.keyTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            findNextIndex();
            return k;
        }

        @Override // java.lang.Iterable
        public Keys<K> iterator() {
            return this;
        }

        public ArrayList<K> toList() {
            return (ArrayList) toList(new ArrayList(this.map.size));
        }

        public <T extends List<K>> T toList(T t) {
            while (this.hasNext) {
                t.add(next());
            }
            return t;
        }
    }
}
