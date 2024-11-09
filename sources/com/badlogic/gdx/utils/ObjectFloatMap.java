package com.badlogic.gdx.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/ObjectFloatMap.class */
public class ObjectFloatMap<K> implements Iterable<Entry<K>> {
    public int size;
    K[] keyTable;
    float[] valueTable;
    float loadFactor;
    int threshold;
    protected int shift;
    protected int mask;
    transient Entries entries1;
    transient Entries entries2;
    transient Values values1;
    transient Values values2;
    transient Keys keys1;
    transient Keys keys2;

    public ObjectFloatMap() {
        this(51, 0.8f);
    }

    public ObjectFloatMap(int i) {
        this(i, 0.8f);
    }

    public ObjectFloatMap(int i, float f) {
        if (f <= 0.0f || f >= 1.0f) {
            throw new IllegalArgumentException("loadFactor must be > 0 and < 1: " + f);
        }
        this.loadFactor = f;
        int tableSize = ObjectSet.tableSize(i, f);
        this.threshold = (int) (tableSize * f);
        this.mask = tableSize - 1;
        this.shift = Long.numberOfLeadingZeros(this.mask);
        this.keyTable = (K[]) new Object[tableSize];
        this.valueTable = new float[tableSize];
    }

    public ObjectFloatMap(ObjectFloatMap<? extends K> objectFloatMap) {
        this((int) Math.floor(objectFloatMap.keyTable.length * objectFloatMap.loadFactor), objectFloatMap.loadFactor);
        System.arraycopy(objectFloatMap.keyTable, 0, this.keyTable, 0, objectFloatMap.keyTable.length);
        System.arraycopy(objectFloatMap.valueTable, 0, this.valueTable, 0, objectFloatMap.valueTable.length);
        this.size = objectFloatMap.size;
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
            if (k2.equals(k)) {
                return i;
            }
            place = (i + 1) & this.mask;
        }
    }

    public void put(K k, float f) {
        int locateKey = locateKey(k);
        if (locateKey >= 0) {
            this.valueTable[locateKey] = f;
            return;
        }
        int i = -(locateKey + 1);
        this.keyTable[i] = k;
        this.valueTable[i] = f;
        int i2 = this.size + 1;
        this.size = i2;
        if (i2 >= this.threshold) {
            resize(this.keyTable.length << 1);
        }
    }

    public float put(K k, float f, float f2) {
        int locateKey = locateKey(k);
        if (locateKey >= 0) {
            float f3 = this.valueTable[locateKey];
            this.valueTable[locateKey] = f;
            return f3;
        }
        int i = -(locateKey + 1);
        this.keyTable[i] = k;
        this.valueTable[i] = f;
        int i2 = this.size + 1;
        this.size = i2;
        if (i2 >= this.threshold) {
            resize(this.keyTable.length << 1);
        }
        return f2;
    }

    public void putAll(ObjectFloatMap<? extends K> objectFloatMap) {
        ensureCapacity(objectFloatMap.size);
        K[] kArr = objectFloatMap.keyTable;
        float[] fArr = objectFloatMap.valueTable;
        int length = kArr.length;
        for (int i = 0; i < length; i++) {
            K k = kArr[i];
            if (k != null) {
                put(k, fArr[i]);
            }
        }
    }

    private void putResize(K k, float f) {
        K[] kArr = this.keyTable;
        int place = place(k);
        while (true) {
            int i = place;
            if (kArr[i] != null) {
                place = (i + 1) & this.mask;
            } else {
                kArr[i] = k;
                this.valueTable[i] = f;
                return;
            }
        }
    }

    public float get(K k, float f) {
        int locateKey = locateKey(k);
        return locateKey < 0 ? f : this.valueTable[locateKey];
    }

    public float getAndIncrement(K k, float f, float f2) {
        int locateKey = locateKey(k);
        if (locateKey >= 0) {
            float f3 = this.valueTable[locateKey];
            float[] fArr = this.valueTable;
            fArr[locateKey] = fArr[locateKey] + f2;
            return f3;
        }
        int i = -(locateKey + 1);
        this.keyTable[i] = k;
        this.valueTable[i] = f + f2;
        int i2 = this.size + 1;
        this.size = i2;
        if (i2 >= this.threshold) {
            resize(this.keyTable.length << 1);
        }
        return f;
    }

    public float remove(K k, float f) {
        int locateKey = locateKey(k);
        int i = locateKey;
        if (locateKey < 0) {
            return f;
        }
        K[] kArr = this.keyTable;
        float[] fArr = this.valueTable;
        float f2 = fArr[i];
        int i2 = this.mask;
        int i3 = i;
        while (true) {
            int i4 = (i3 + 1) & i2;
            K k2 = kArr[i4];
            if (k2 != null) {
                int place = place(k2);
                if (((i4 - place) & i2) > ((i - place) & i2)) {
                    kArr[i] = k2;
                    fArr[i] = fArr[i4];
                    i = i4;
                }
                i3 = i4;
            } else {
                kArr[i] = null;
                this.size--;
                return f2;
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

    public boolean containsValue(float f) {
        K[] kArr = this.keyTable;
        float[] fArr = this.valueTable;
        for (int length = fArr.length - 1; length >= 0; length--) {
            if (kArr[length] != null && fArr[length] == f) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(float f, float f2) {
        K[] kArr = this.keyTable;
        float[] fArr = this.valueTable;
        for (int length = fArr.length - 1; length >= 0; length--) {
            if (kArr[length] != null && Math.abs(fArr[length] - f) <= f2) {
                return true;
            }
        }
        return false;
    }

    public boolean containsKey(K k) {
        return locateKey(k) >= 0;
    }

    @Null
    public K findKey(float f) {
        K[] kArr = this.keyTable;
        float[] fArr = this.valueTable;
        for (int length = fArr.length - 1; length >= 0; length--) {
            K k = kArr[length];
            if (k != null && fArr[length] == f) {
                return k;
            }
        }
        return null;
    }

    @Null
    public K findKey(float f, float f2) {
        K[] kArr = this.keyTable;
        float[] fArr = this.valueTable;
        for (int length = fArr.length - 1; length >= 0; length--) {
            K k = kArr[length];
            if (k != null && Math.abs(fArr[length] - f) <= f2) {
                return k;
            }
        }
        return null;
    }

    public void ensureCapacity(int i) {
        int tableSize = ObjectSet.tableSize(this.size + i, this.loadFactor);
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
        float[] fArr = this.valueTable;
        this.keyTable = (K[]) new Object[i];
        this.valueTable = new float[i];
        if (this.size > 0) {
            for (int i2 = 0; i2 < length; i2++) {
                K k = kArr[i2];
                if (k != null) {
                    putResize(k, fArr[i2]);
                }
            }
        }
    }

    public int hashCode() {
        int i = this.size;
        K[] kArr = this.keyTable;
        float[] fArr = this.valueTable;
        int length = kArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            K k = kArr[i2];
            if (k != null) {
                i += k.hashCode() + NumberUtils.floatToRawIntBits(fArr[i2]);
            }
        }
        return i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ObjectFloatMap)) {
            return false;
        }
        ObjectFloatMap objectFloatMap = (ObjectFloatMap) obj;
        if (objectFloatMap.size != this.size) {
            return false;
        }
        K[] kArr = this.keyTable;
        float[] fArr = this.valueTable;
        int length = kArr.length;
        for (int i = 0; i < length; i++) {
            K k = kArr[i];
            if (k != null) {
                float f = objectFloatMap.get(k, 0.0f);
                if ((f == 0.0f && !objectFloatMap.containsKey(k)) || f != fArr[i]) {
                    return false;
                }
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
        java.lang.StringBuilder sb = new java.lang.StringBuilder(32);
        if (z) {
            sb.append('{');
        }
        K[] kArr = this.keyTable;
        float[] fArr = this.valueTable;
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
                sb.append(fArr[length]);
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
                sb.append(fArr[length]);
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

    public Keys<K> keys() {
        if (Collections.allocateIterators) {
            return new Keys<>(this);
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

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/ObjectFloatMap$Entry.class */
    public static class Entry<K> {
        public K key;
        public float value;

        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/ObjectFloatMap$MapIterator.class */
    public static class MapIterator<K> {
        public boolean hasNext;
        final ObjectFloatMap<K> map;
        int nextIndex;
        int currentIndex;
        boolean valid = true;

        public MapIterator(ObjectFloatMap<K> objectFloatMap) {
            this.map = objectFloatMap;
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
            float[] fArr = this.map.valueTable;
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
                    fArr[i2] = fArr[i5];
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

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/ObjectFloatMap$Entries.class */
    public static class Entries<K> extends MapIterator<K> implements Iterable<Entry<K>>, Iterator<Entry<K>> {
        Entry<K> entry;

        @Override // com.badlogic.gdx.utils.ObjectFloatMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.badlogic.gdx.utils.ObjectFloatMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public Entries(ObjectFloatMap<K> objectFloatMap) {
            super(objectFloatMap);
            this.entry = new Entry<>();
        }

        @Override // java.util.Iterator
        public Entry<K> next() {
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            if (!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
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
            if (this.valid) {
                return this.hasNext;
            }
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
        }

        @Override // java.lang.Iterable
        public Entries<K> iterator() {
            return this;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/ObjectFloatMap$Values.class */
    public static class Values extends MapIterator<Object> {
        @Override // com.badlogic.gdx.utils.ObjectFloatMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.badlogic.gdx.utils.ObjectFloatMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public Values(ObjectFloatMap<?> objectFloatMap) {
            super(objectFloatMap);
        }

        public boolean hasNext() {
            if (this.valid) {
                return this.hasNext;
            }
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
        }

        public float next() {
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            if (!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            float f = this.map.valueTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            findNextIndex();
            return f;
        }

        public Values iterator() {
            return this;
        }

        public FloatArray toArray() {
            FloatArray floatArray = new FloatArray(true, this.map.size);
            while (this.hasNext) {
                floatArray.add(next());
            }
            return floatArray;
        }

        public FloatArray toArray(FloatArray floatArray) {
            while (this.hasNext) {
                floatArray.add(next());
            }
            return floatArray;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/ObjectFloatMap$Keys.class */
    public static class Keys<K> extends MapIterator<K> implements Iterable<K>, Iterator<K> {
        @Override // com.badlogic.gdx.utils.ObjectFloatMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.badlogic.gdx.utils.ObjectFloatMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public Keys(ObjectFloatMap<K> objectFloatMap) {
            super(objectFloatMap);
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
            K k = this.map.keyTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            findNextIndex();
            return k;
        }

        @Override // java.lang.Iterable
        public Keys<K> iterator() {
            return this;
        }

        public Array<K> toArray() {
            return toArray(new Array<>(true, this.map.size));
        }

        public Array<K> toArray(Array<K> array) {
            while (this.hasNext) {
                array.add(next());
            }
            return array;
        }
    }
}
