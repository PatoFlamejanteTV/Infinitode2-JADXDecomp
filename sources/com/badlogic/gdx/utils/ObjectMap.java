package com.badlogic.gdx.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/ObjectMap.class */
public class ObjectMap<K, V> implements Iterable<Entry<K, V>> {
    static final Object dummy = new Object();
    public int size;
    K[] keyTable;
    V[] valueTable;
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

    public ObjectMap() {
        this(51, 0.8f);
    }

    public ObjectMap(int i) {
        this(i, 0.8f);
    }

    public ObjectMap(int i, float f) {
        if (f <= 0.0f || f >= 1.0f) {
            throw new IllegalArgumentException("loadFactor must be > 0 and < 1: " + f);
        }
        this.loadFactor = f;
        int tableSize = ObjectSet.tableSize(i, f);
        this.threshold = (int) (tableSize * f);
        this.mask = tableSize - 1;
        this.shift = Long.numberOfLeadingZeros(this.mask);
        this.keyTable = (K[]) new Object[tableSize];
        this.valueTable = (V[]) new Object[tableSize];
    }

    public ObjectMap(ObjectMap<? extends K, ? extends V> objectMap) {
        this((int) (objectMap.keyTable.length * objectMap.loadFactor), objectMap.loadFactor);
        System.arraycopy(objectMap.keyTable, 0, this.keyTable, 0, objectMap.keyTable.length);
        System.arraycopy(objectMap.valueTable, 0, this.valueTable, 0, objectMap.valueTable.length);
        this.size = objectMap.size;
    }

    protected int place(K k) {
        return (int) ((k.hashCode() * (-7046029254386353131L)) >>> this.shift);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int locateKey(K k) {
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

    @Null
    public V put(K k, @Null V v) {
        int locateKey = locateKey(k);
        if (locateKey >= 0) {
            V v2 = this.valueTable[locateKey];
            this.valueTable[locateKey] = v;
            return v2;
        }
        int i = -(locateKey + 1);
        this.keyTable[i] = k;
        this.valueTable[i] = v;
        int i2 = this.size + 1;
        this.size = i2;
        if (i2 >= this.threshold) {
            resize(this.keyTable.length << 1);
            return null;
        }
        return null;
    }

    public void putAll(ObjectMap<? extends K, ? extends V> objectMap) {
        ensureCapacity(objectMap.size);
        K[] kArr = objectMap.keyTable;
        V[] vArr = objectMap.valueTable;
        int length = kArr.length;
        for (int i = 0; i < length; i++) {
            K k = kArr[i];
            if (k != null) {
                put(k, vArr[i]);
            }
        }
    }

    private void putResize(K k, @Null V v) {
        K[] kArr = this.keyTable;
        int place = place(k);
        while (true) {
            int i = place;
            if (kArr[i] != null) {
                place = (i + 1) & this.mask;
            } else {
                kArr[i] = k;
                this.valueTable[i] = v;
                return;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Null
    public <T extends K> V get(T t) {
        int locateKey = locateKey(t);
        if (locateKey < 0) {
            return null;
        }
        return this.valueTable[locateKey];
    }

    public V get(K k, @Null V v) {
        int locateKey = locateKey(k);
        return locateKey < 0 ? v : this.valueTable[locateKey];
    }

    @Null
    public V remove(K k) {
        int locateKey = locateKey(k);
        int i = locateKey;
        if (locateKey < 0) {
            return null;
        }
        K[] kArr = this.keyTable;
        V[] vArr = this.valueTable;
        V v = vArr[i];
        int i2 = this.mask;
        int i3 = i;
        while (true) {
            int i4 = (i3 + 1) & i2;
            K k2 = kArr[i4];
            if (k2 != null) {
                int place = place(k2);
                if (((i4 - place) & i2) > ((i - place) & i2)) {
                    kArr[i] = k2;
                    vArr[i] = vArr[i4];
                    i = i4;
                }
                i3 = i4;
            } else {
                kArr[i] = null;
                vArr[i] = null;
                this.size--;
                return v;
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
        Arrays.fill(this.valueTable, (Object) null);
    }

    public boolean containsValue(@Null Object obj, boolean z) {
        V[] vArr = this.valueTable;
        if (obj == null) {
            K[] kArr = this.keyTable;
            for (int length = vArr.length - 1; length >= 0; length--) {
                if (kArr[length] != null && vArr[length] == null) {
                    return true;
                }
            }
            return false;
        }
        if (z) {
            for (int length2 = vArr.length - 1; length2 >= 0; length2--) {
                if (vArr[length2] == obj) {
                    return true;
                }
            }
            return false;
        }
        for (int length3 = vArr.length - 1; length3 >= 0; length3--) {
            if (obj.equals(vArr[length3])) {
                return true;
            }
        }
        return false;
    }

    public boolean containsKey(K k) {
        return locateKey(k) >= 0;
    }

    @Null
    public K findKey(@Null Object obj, boolean z) {
        V[] vArr = this.valueTable;
        if (obj == null) {
            K[] kArr = this.keyTable;
            for (int length = vArr.length - 1; length >= 0; length--) {
                if (kArr[length] != null && vArr[length] == null) {
                    return kArr[length];
                }
            }
            return null;
        }
        if (z) {
            for (int length2 = vArr.length - 1; length2 >= 0; length2--) {
                if (vArr[length2] == obj) {
                    return this.keyTable[length2];
                }
            }
            return null;
        }
        for (int length3 = vArr.length - 1; length3 >= 0; length3--) {
            if (obj.equals(vArr[length3])) {
                return this.keyTable[length3];
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void resize(int i) {
        int length = this.keyTable.length;
        this.threshold = (int) (i * this.loadFactor);
        this.mask = i - 1;
        this.shift = Long.numberOfLeadingZeros(this.mask);
        K[] kArr = this.keyTable;
        V[] vArr = this.valueTable;
        this.keyTable = (K[]) new Object[i];
        this.valueTable = (V[]) new Object[i];
        if (this.size > 0) {
            for (int i2 = 0; i2 < length; i2++) {
                K k = kArr[i2];
                if (k != null) {
                    putResize(k, vArr[i2]);
                }
            }
        }
    }

    public int hashCode() {
        int i = this.size;
        K[] kArr = this.keyTable;
        V[] vArr = this.valueTable;
        int length = kArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            K k = kArr[i2];
            if (k != null) {
                i += k.hashCode();
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
        if (!(obj instanceof ObjectMap)) {
            return false;
        }
        ObjectMap objectMap = (ObjectMap) obj;
        if (objectMap.size != this.size) {
            return false;
        }
        K[] kArr = this.keyTable;
        V[] vArr = this.valueTable;
        int length = kArr.length;
        for (int i = 0; i < length; i++) {
            K k = kArr[i];
            if (k != null) {
                V v = vArr[i];
                if (v == null) {
                    if (objectMap.get(k, dummy) != null) {
                        return false;
                    }
                } else if (!v.equals(objectMap.get(k))) {
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
        if (!(obj instanceof ObjectMap)) {
            return false;
        }
        ObjectMap objectMap = (ObjectMap) obj;
        if (objectMap.size != this.size) {
            return false;
        }
        K[] kArr = this.keyTable;
        V[] vArr = this.valueTable;
        int length = kArr.length;
        for (int i = 0; i < length; i++) {
            K k = kArr[i];
            if (k != null && vArr[i] != objectMap.get(k, dummy)) {
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

    protected String toString(String str, boolean z) {
        if (this.size == 0) {
            return z ? "{}" : "";
        }
        java.lang.StringBuilder sb = new java.lang.StringBuilder(32);
        if (z) {
            sb.append('{');
        }
        K[] kArr = this.keyTable;
        V[] vArr = this.valueTable;
        int length = kArr.length;
        while (true) {
            int i = length;
            length--;
            if (i <= 0) {
                break;
            }
            K k = kArr[length];
            if (k != null) {
                sb.append(k == this ? "(this)" : k);
                sb.append('=');
                V v = vArr[length];
                sb.append(v == this ? "(this)" : v);
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
                sb.append(k2 == this ? "(this)" : k2);
                sb.append('=');
                V v2 = vArr[length];
                sb.append(v2 == this ? "(this)" : v2);
            }
        }
        if (z) {
            sb.append('}');
        }
        return sb.toString();
    }

    @Override // java.lang.Iterable
    public Entries<K, V> iterator() {
        return entries();
    }

    public Entries<K, V> entries() {
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

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/ObjectMap$Entry.class */
    public static class Entry<K, V> {
        public K key;

        @Null
        public V value;

        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/ObjectMap$MapIterator.class */
    public static abstract class MapIterator<K, V, I> implements Iterable<I>, Iterator<I> {
        public boolean hasNext;
        final ObjectMap<K, V> map;
        int nextIndex;
        int currentIndex;
        boolean valid = true;

        public MapIterator(ObjectMap<K, V> objectMap) {
            this.map = objectMap;
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
            V[] vArr = this.map.valueTable;
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
                    vArr[i2] = vArr[i5];
                    i2 = i5;
                }
                i4 = i5;
            }
            kArr[i2] = null;
            vArr[i2] = null;
            this.map.size--;
            if (i2 != this.currentIndex) {
                this.nextIndex--;
            }
            this.currentIndex = -1;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/ObjectMap$Entries.class */
    public static class Entries<K, V> extends MapIterator<K, V, Entry<K, V>> {
        Entry<K, V> entry;

        @Override // com.badlogic.gdx.utils.ObjectMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.badlogic.gdx.utils.ObjectMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public Entries(ObjectMap<K, V> objectMap) {
            super(objectMap);
            this.entry = new Entry<>();
        }

        @Override // java.util.Iterator
        public Entry<K, V> next() {
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
        public Entries<K, V> iterator() {
            return this;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/ObjectMap$Values.class */
    public static class Values<V> extends MapIterator<Object, V, V> {
        @Override // com.badlogic.gdx.utils.ObjectMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.badlogic.gdx.utils.ObjectMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public Values(ObjectMap<?, V> objectMap) {
            super(objectMap);
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
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            if (!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            V v = this.map.valueTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            findNextIndex();
            return v;
        }

        @Override // java.lang.Iterable
        public Values<V> iterator() {
            return this;
        }

        public Array<V> toArray() {
            return toArray(new Array<>(true, this.map.size));
        }

        public Array<V> toArray(Array<V> array) {
            while (this.hasNext) {
                array.add(next());
            }
            return array;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/ObjectMap$Keys.class */
    public static class Keys<K> extends MapIterator<K, Object, K> {
        @Override // com.badlogic.gdx.utils.ObjectMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.badlogic.gdx.utils.ObjectMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public Keys(ObjectMap<K, ?> objectMap) {
            super(objectMap);
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
