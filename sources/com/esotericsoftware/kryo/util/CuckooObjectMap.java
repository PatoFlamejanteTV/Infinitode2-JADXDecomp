package com.esotericsoftware.kryo.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

@Deprecated
/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/util/CuckooObjectMap.class */
public class CuckooObjectMap<K, V> {
    private static final int PRIME2 = -1105259343;
    private static final int PRIME3 = -1262997959;
    private static final int PRIME4 = -825114047;
    static Random random = new Random();
    public int size;
    K[] keyTable;
    V[] valueTable;
    int capacity;
    int stashSize;
    private float loadFactor;
    private int hashShift;
    private int mask;
    private int threshold;
    private int stashCapacity;
    private int pushIterations;
    private boolean isBigTable;

    public CuckooObjectMap() {
        this(32, 0.8f);
    }

    public CuckooObjectMap(int i) {
        this(i, 0.8f);
    }

    public CuckooObjectMap(int i, float f) {
        if (i < 0) {
            throw new IllegalArgumentException("initialCapacity must be >= 0: " + i);
        }
        if (i > 1073741824) {
            throw new IllegalArgumentException("initialCapacity is too large: " + i);
        }
        this.capacity = nextPowerOfTwo(i);
        if (f <= 0.0f) {
            throw new IllegalArgumentException("loadFactor must be > 0: " + f);
        }
        this.loadFactor = f;
        this.isBigTable = (this.capacity >>> 16) != 0;
        this.threshold = (int) (this.capacity * f);
        this.mask = this.capacity - 1;
        this.hashShift = 31 - Integer.numberOfTrailingZeros(this.capacity);
        this.stashCapacity = Math.max(3, ((int) Math.ceil(Math.log(this.capacity))) << 1);
        this.pushIterations = Math.max(Math.min(this.capacity, 8), ((int) Math.sqrt(this.capacity)) / 8);
        this.keyTable = (K[]) new Object[this.capacity + this.stashCapacity];
        this.valueTable = (V[]) new Object[this.keyTable.length];
    }

    public CuckooObjectMap(CuckooObjectMap<? extends K, ? extends V> cuckooObjectMap) {
        this(cuckooObjectMap.capacity, cuckooObjectMap.loadFactor);
        this.stashSize = cuckooObjectMap.stashSize;
        System.arraycopy(cuckooObjectMap.keyTable, 0, this.keyTable, 0, cuckooObjectMap.keyTable.length);
        System.arraycopy(cuckooObjectMap.valueTable, 0, this.valueTable, 0, cuckooObjectMap.valueTable.length);
        this.size = cuckooObjectMap.size;
    }

    public V put(K k, V v) {
        if (k == null) {
            throw new IllegalArgumentException("key cannot be null.");
        }
        return put_internal(k, v);
    }

    private V put_internal(K k, V v) {
        K[] kArr = this.keyTable;
        int i = this.mask;
        boolean z = this.isBigTable;
        int hashCode = k.hashCode();
        int i2 = hashCode & i;
        K k2 = kArr[i2];
        if (k.equals(k2)) {
            V v2 = this.valueTable[i2];
            this.valueTable[i2] = v;
            return v2;
        }
        int hash2 = hash2(hashCode);
        K k3 = kArr[hash2];
        if (k.equals(k3)) {
            V v3 = this.valueTable[hash2];
            this.valueTable[hash2] = v;
            return v3;
        }
        int hash3 = hash3(hashCode);
        K k4 = kArr[hash3];
        if (k.equals(k4)) {
            V v4 = this.valueTable[hash3];
            this.valueTable[hash3] = v;
            return v4;
        }
        int i3 = -1;
        K k5 = null;
        if (z) {
            i3 = hash4(hashCode);
            k5 = kArr[i3];
            if (k.equals(k5)) {
                V v5 = this.valueTable[i3];
                this.valueTable[i3] = v;
                return v5;
            }
        }
        int i4 = this.capacity;
        int i5 = i4 + this.stashSize;
        for (int i6 = i4; i6 < i5; i6++) {
            if (k.equals(kArr[i6])) {
                V v6 = this.valueTable[i6];
                this.valueTable[i6] = v;
                return v6;
            }
        }
        if (k2 == null) {
            kArr[i2] = k;
            this.valueTable[i2] = v;
            int i7 = this.size;
            this.size = i7 + 1;
            if (i7 >= this.threshold) {
                resize(this.capacity << 1);
                return null;
            }
            return null;
        }
        if (k3 == null) {
            kArr[hash2] = k;
            this.valueTable[hash2] = v;
            int i8 = this.size;
            this.size = i8 + 1;
            if (i8 >= this.threshold) {
                resize(this.capacity << 1);
                return null;
            }
            return null;
        }
        if (k4 == null) {
            kArr[hash3] = k;
            this.valueTable[hash3] = v;
            int i9 = this.size;
            this.size = i9 + 1;
            if (i9 >= this.threshold) {
                resize(this.capacity << 1);
                return null;
            }
            return null;
        }
        if (z && k5 == null) {
            kArr[i3] = k;
            this.valueTable[i3] = v;
            int i10 = this.size;
            this.size = i10 + 1;
            if (i10 >= this.threshold) {
                resize(this.capacity << 1);
                return null;
            }
            return null;
        }
        push(k, v, i2, k2, hash2, k3, hash3, k4, i3, k5);
        return null;
    }

    public void putAll(CuckooObjectMap<K, V> cuckooObjectMap) {
        ensureCapacity(cuckooObjectMap.size);
        Iterator<Entry<K, V>> it = cuckooObjectMap.entries().iterator();
        while (it.hasNext()) {
            Entry<K, V> next = it.next();
            put(next.key, next.value);
        }
    }

    private void putResize(K k, V v) {
        int hashCode = k.hashCode();
        int i = hashCode & this.mask;
        K k2 = this.keyTable[i];
        if (k2 == null) {
            this.keyTable[i] = k;
            this.valueTable[i] = v;
            int i2 = this.size;
            this.size = i2 + 1;
            if (i2 >= this.threshold) {
                resize(this.capacity << 1);
                return;
            }
            return;
        }
        int hash2 = hash2(hashCode);
        K k3 = this.keyTable[hash2];
        if (k3 == null) {
            this.keyTable[hash2] = k;
            this.valueTable[hash2] = v;
            int i3 = this.size;
            this.size = i3 + 1;
            if (i3 >= this.threshold) {
                resize(this.capacity << 1);
                return;
            }
            return;
        }
        int hash3 = hash3(hashCode);
        K k4 = this.keyTable[hash3];
        if (k4 == null) {
            this.keyTable[hash3] = k;
            this.valueTable[hash3] = v;
            int i4 = this.size;
            this.size = i4 + 1;
            if (i4 >= this.threshold) {
                resize(this.capacity << 1);
                return;
            }
            return;
        }
        int i5 = -1;
        K k5 = null;
        if (this.isBigTable) {
            i5 = hash4(hashCode);
            K k6 = this.keyTable[i5];
            k5 = k6;
            if (k6 == null) {
                this.keyTable[i5] = k;
                this.valueTable[i5] = v;
                int i6 = this.size;
                this.size = i6 + 1;
                if (i6 >= this.threshold) {
                    resize(this.capacity << 1);
                    return;
                }
                return;
            }
        }
        push(k, v, i, k2, hash2, k3, hash3, k4, i5, k5);
    }

    private void push(K k, V v, int i, K k2, int i2, K k3, int i3, K k4, int i4, K k5) {
        K k6;
        V v2;
        K[] kArr = this.keyTable;
        V[] vArr = this.valueTable;
        int i5 = this.mask;
        boolean z = this.isBigTable;
        int i6 = 0;
        int i7 = this.pushIterations;
        int i8 = z ? 4 : 3;
        while (true) {
            switch (random.nextInt(i8)) {
                case 0:
                    k6 = k2;
                    v2 = vArr[i];
                    kArr[i] = k;
                    vArr[i] = v;
                    break;
                case 1:
                    k6 = k3;
                    v2 = vArr[i2];
                    kArr[i2] = k;
                    vArr[i2] = v;
                    break;
                case 2:
                    k6 = k4;
                    v2 = vArr[i3];
                    kArr[i3] = k;
                    vArr[i3] = v;
                    break;
                default:
                    k6 = k5;
                    v2 = vArr[i4];
                    kArr[i4] = k;
                    vArr[i4] = v;
                    break;
            }
            int hashCode = k6.hashCode();
            i = hashCode & i5;
            K k7 = kArr[i];
            k2 = k7;
            if (k7 == null) {
                kArr[i] = k6;
                vArr[i] = v2;
                int i9 = this.size;
                this.size = i9 + 1;
                if (i9 >= this.threshold) {
                    resize(this.capacity << 1);
                    return;
                }
                return;
            }
            i2 = hash2(hashCode);
            K k8 = kArr[i2];
            k3 = k8;
            if (k8 == null) {
                kArr[i2] = k6;
                vArr[i2] = v2;
                int i10 = this.size;
                this.size = i10 + 1;
                if (i10 >= this.threshold) {
                    resize(this.capacity << 1);
                    return;
                }
                return;
            }
            i3 = hash3(hashCode);
            K k9 = kArr[i3];
            k4 = k9;
            if (k9 == null) {
                kArr[i3] = k6;
                vArr[i3] = v2;
                int i11 = this.size;
                this.size = i11 + 1;
                if (i11 >= this.threshold) {
                    resize(this.capacity << 1);
                    return;
                }
                return;
            }
            if (z) {
                i4 = hash4(hashCode);
                K k10 = kArr[i4];
                k5 = k10;
                if (k10 == null) {
                    kArr[i4] = k6;
                    vArr[i4] = v2;
                    int i12 = this.size;
                    this.size = i12 + 1;
                    if (i12 >= this.threshold) {
                        resize(this.capacity << 1);
                        return;
                    }
                    return;
                }
            }
            i6++;
            if (i6 != i7) {
                k = k6;
                v = v2;
            } else {
                putStash(k6, v2);
                return;
            }
        }
    }

    private void putStash(K k, V v) {
        if (this.stashSize == this.stashCapacity) {
            resize(this.capacity << 1);
            put_internal(k, v);
            return;
        }
        int i = this.capacity + this.stashSize;
        this.keyTable[i] = k;
        this.valueTable[i] = v;
        this.stashSize++;
        this.size++;
    }

    public V get(K k) {
        int hashCode = k.hashCode();
        int i = hashCode & this.mask;
        if (!k.equals(this.keyTable[i])) {
            i = hash2(hashCode);
            if (!k.equals(this.keyTable[i])) {
                i = hash3(hashCode);
                if (!k.equals(this.keyTable[i])) {
                    if (this.isBigTable) {
                        i = hash4(hashCode);
                        if (!k.equals(this.keyTable[i])) {
                            return getStash(k);
                        }
                    } else {
                        return getStash(k);
                    }
                }
            }
        }
        return this.valueTable[i];
    }

    private V getStash(K k) {
        K[] kArr = this.keyTable;
        int i = this.capacity;
        int i2 = i + this.stashSize;
        for (int i3 = i; i3 < i2; i3++) {
            if (k.equals(kArr[i3])) {
                return this.valueTable[i3];
            }
        }
        return null;
    }

    public V get(K k, V v) {
        int hashCode = k.hashCode();
        int i = hashCode & this.mask;
        if (!k.equals(this.keyTable[i])) {
            i = hash2(hashCode);
            if (!k.equals(this.keyTable[i])) {
                i = hash3(hashCode);
                if (!k.equals(this.keyTable[i])) {
                    if (this.isBigTable) {
                        i = hash4(hashCode);
                        if (!k.equals(this.keyTable[i])) {
                            return getStash(k, v);
                        }
                    } else {
                        return getStash(k, v);
                    }
                }
            }
        }
        return this.valueTable[i];
    }

    private V getStash(K k, V v) {
        K[] kArr = this.keyTable;
        int i = this.capacity;
        int i2 = i + this.stashSize;
        for (int i3 = i; i3 < i2; i3++) {
            if (k.equals(kArr[i3])) {
                return this.valueTable[i3];
            }
        }
        return v;
    }

    public V remove(K k) {
        int hashCode = k.hashCode();
        int i = hashCode & this.mask;
        if (k.equals(this.keyTable[i])) {
            this.keyTable[i] = null;
            V v = this.valueTable[i];
            this.valueTable[i] = null;
            this.size--;
            return v;
        }
        int hash2 = hash2(hashCode);
        if (k.equals(this.keyTable[hash2])) {
            this.keyTable[hash2] = null;
            V v2 = this.valueTable[hash2];
            this.valueTable[hash2] = null;
            this.size--;
            return v2;
        }
        int hash3 = hash3(hashCode);
        if (k.equals(this.keyTable[hash3])) {
            this.keyTable[hash3] = null;
            V v3 = this.valueTable[hash3];
            this.valueTable[hash3] = null;
            this.size--;
            return v3;
        }
        if (this.isBigTable) {
            int hash4 = hash4(hashCode);
            if (k.equals(this.keyTable[hash4])) {
                this.keyTable[hash4] = null;
                V v4 = this.valueTable[hash4];
                this.valueTable[hash4] = null;
                this.size--;
                return v4;
            }
        }
        return removeStash(k);
    }

    V removeStash(K k) {
        K[] kArr = this.keyTable;
        int i = this.capacity;
        int i2 = i + this.stashSize;
        for (int i3 = i; i3 < i2; i3++) {
            if (k.equals(kArr[i3])) {
                V v = this.valueTable[i3];
                removeStashIndex(i3);
                this.size--;
                return v;
            }
        }
        return null;
    }

    void removeStashIndex(int i) {
        this.stashSize--;
        int i2 = this.capacity + this.stashSize;
        if (i < i2) {
            K[] kArr = this.keyTable;
            kArr[i] = kArr[i2];
            V[] vArr = this.valueTable;
            vArr[i] = vArr[i2];
            this.valueTable[i2] = null;
            return;
        }
        this.valueTable[i] = null;
    }

    public void shrink(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("maximumCapacity must be >= 0: " + i);
        }
        if (this.size > i) {
            i = this.size;
        }
        if (this.capacity <= i) {
            return;
        }
        resize(nextPowerOfTwo(i));
    }

    public void clear(int i) {
        if (this.capacity <= i) {
            clear();
        } else {
            this.size = 0;
            resize(i);
        }
    }

    public void clear() {
        K[] kArr = this.keyTable;
        V[] vArr = this.valueTable;
        int i = this.capacity + this.stashSize;
        while (true) {
            int i2 = i;
            i--;
            if (i2 > 0) {
                kArr[i] = null;
                vArr[i] = null;
            } else {
                this.size = 0;
                this.stashSize = 0;
                return;
            }
        }
    }

    public boolean containsValue(Object obj, boolean z) {
        V[] vArr = this.valueTable;
        if (obj != null) {
            if (z) {
                int i = this.capacity + this.stashSize;
                do {
                    int i2 = i;
                    i--;
                    if (i2 <= 0) {
                        return false;
                    }
                } while (vArr[i] != obj);
                return true;
            }
            int i3 = this.capacity + this.stashSize;
            do {
                int i4 = i3;
                i3--;
                if (i4 <= 0) {
                    return false;
                }
            } while (!obj.equals(vArr[i3]));
            return true;
        }
        K[] kArr = this.keyTable;
        int i5 = this.capacity + this.stashSize;
        while (true) {
            int i6 = i5;
            i5--;
            if (i6 > 0) {
                if (kArr[i5] != null && vArr[i5] == null) {
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    public boolean containsKey(K k) {
        int hashCode = k.hashCode();
        if (!k.equals(this.keyTable[hashCode & this.mask])) {
            if (!k.equals(this.keyTable[hash2(hashCode)])) {
                if (!k.equals(this.keyTable[hash3(hashCode)])) {
                    if (this.isBigTable) {
                        if (k.equals(this.keyTable[hash4(hashCode)])) {
                            return true;
                        }
                        return containsKeyStash(k);
                    }
                    return containsKeyStash(k);
                }
                return true;
            }
            return true;
        }
        return true;
    }

    private boolean containsKeyStash(K k) {
        K[] kArr = this.keyTable;
        int i = this.capacity;
        int i2 = i + this.stashSize;
        for (int i3 = i; i3 < i2; i3++) {
            if (k.equals(kArr[i3])) {
                return true;
            }
        }
        return false;
    }

    public K findKey(Object obj, boolean z) {
        V[] vArr = this.valueTable;
        if (obj != null) {
            if (z) {
                int i = this.capacity + this.stashSize;
                do {
                    int i2 = i;
                    i--;
                    if (i2 <= 0) {
                        return null;
                    }
                } while (vArr[i] != obj);
                return this.keyTable[i];
            }
            int i3 = this.capacity + this.stashSize;
            do {
                int i4 = i3;
                i3--;
                if (i4 <= 0) {
                    return null;
                }
            } while (!obj.equals(vArr[i3]));
            return this.keyTable[i3];
        }
        K[] kArr = this.keyTable;
        int i5 = this.capacity + this.stashSize;
        while (true) {
            int i6 = i5;
            i5--;
            if (i6 > 0) {
                if (kArr[i5] != null && vArr[i5] == null) {
                    return kArr[i5];
                }
            } else {
                return null;
            }
        }
    }

    public void ensureCapacity(int i) {
        int i2 = this.size + i;
        if (i2 >= this.threshold) {
            resize(nextPowerOfTwo((int) (i2 / this.loadFactor)));
        }
    }

    private void resize(int i) {
        int i2 = this.capacity + this.stashSize;
        this.capacity = i;
        this.threshold = (int) (i * this.loadFactor);
        this.mask = i - 1;
        this.hashShift = 31 - Integer.numberOfTrailingZeros(i);
        this.stashCapacity = Math.max(3, ((int) Math.ceil(Math.log(i))) << 1);
        this.pushIterations = Math.max(Math.min(i, 8), ((int) Math.sqrt(i)) / 8);
        this.isBigTable = (this.capacity >>> 16) != 0;
        K[] kArr = this.keyTable;
        V[] vArr = this.valueTable;
        this.keyTable = (K[]) new Object[i + this.stashCapacity];
        this.valueTable = (V[]) new Object[i + this.stashCapacity];
        int i3 = this.size;
        this.size = 0;
        this.stashSize = 0;
        if (i3 > 0) {
            for (int i4 = 0; i4 < i2; i4++) {
                K k = kArr[i4];
                if (k != null) {
                    putResize(k, vArr[i4]);
                }
            }
        }
    }

    private int hash2(int i) {
        int i2 = i * PRIME2;
        return (i2 ^ (i2 >>> this.hashShift)) & this.mask;
    }

    private int hash3(int i) {
        int i2 = i * PRIME3;
        return (i2 ^ (i2 >>> this.hashShift)) & this.mask;
    }

    private int hash4(int i) {
        int i2 = i * PRIME4;
        return (i2 ^ (i2 >>> this.hashShift)) & this.mask;
    }

    public String toString() {
        if (this.size == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append('{');
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
                sb.append(k);
                sb.append('=');
                sb.append(vArr[length]);
                break;
            }
        }
        while (true) {
            int i2 = length;
            length--;
            if (i2 > 0) {
                K k2 = kArr[length];
                if (k2 != null) {
                    sb.append(", ");
                    sb.append(k2);
                    sb.append('=');
                    sb.append(vArr[length]);
                }
            } else {
                sb.append('}');
                return sb.toString();
            }
        }
    }

    public Entries<K, V> entries() {
        return new Entries<>(this);
    }

    public Values<V> values() {
        return new Values<>(this);
    }

    public Keys<K> keys() {
        return new Keys<>(this);
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/util/CuckooObjectMap$Entry.class */
    public static class Entry<K, V> {
        public K key;
        public V value;

        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/util/CuckooObjectMap$MapIterator.class */
    public static class MapIterator<K, V> {
        public boolean hasNext;
        final CuckooObjectMap<K, V> map;
        int nextIndex;
        int currentIndex;

        public MapIterator(CuckooObjectMap<K, V> cuckooObjectMap) {
            this.map = cuckooObjectMap;
            reset();
        }

        public void reset() {
            this.currentIndex = -1;
            this.nextIndex = -1;
            advance();
        }

        void advance() {
            this.hasNext = false;
            K[] kArr = this.map.keyTable;
            int i = this.map.capacity + this.map.stashSize;
            do {
                int i2 = this.nextIndex + 1;
                this.nextIndex = i2;
                if (i2 >= i) {
                    return;
                }
            } while (kArr[this.nextIndex] == null);
            this.hasNext = true;
        }

        public void remove() {
            if (this.currentIndex < 0) {
                throw new IllegalStateException("next must be called before remove.");
            }
            if (this.currentIndex >= this.map.capacity) {
                this.map.removeStashIndex(this.currentIndex);
                this.nextIndex = this.currentIndex - 1;
                advance();
            } else {
                this.map.keyTable[this.currentIndex] = null;
                this.map.valueTable[this.currentIndex] = null;
            }
            this.currentIndex = -1;
            this.map.size--;
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/util/CuckooObjectMap$Entries.class */
    public static class Entries<K, V> extends MapIterator<K, V> implements Iterable<Entry<K, V>>, Iterator<Entry<K, V>> {
        Entry<K, V> entry;

        @Override // com.esotericsoftware.kryo.util.CuckooObjectMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.esotericsoftware.kryo.util.CuckooObjectMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public Entries(CuckooObjectMap<K, V> cuckooObjectMap) {
            super(cuckooObjectMap);
            this.entry = new Entry<>();
        }

        @Override // java.util.Iterator
        public Entry<K, V> next() {
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            K[] kArr = this.map.keyTable;
            this.entry.key = kArr[this.nextIndex];
            this.entry.value = this.map.valueTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            advance();
            return this.entry;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.hasNext;
        }

        @Override // java.lang.Iterable
        public Iterator<Entry<K, V>> iterator() {
            return this;
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/util/CuckooObjectMap$Values.class */
    public static class Values<V> extends MapIterator<Object, V> implements Iterable<V>, Iterator<V> {
        @Override // com.esotericsoftware.kryo.util.CuckooObjectMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.esotericsoftware.kryo.util.CuckooObjectMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public Values(CuckooObjectMap<?, V> cuckooObjectMap) {
            super(cuckooObjectMap);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.hasNext;
        }

        @Override // java.util.Iterator
        public V next() {
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            V v = this.map.valueTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            advance();
            return v;
        }

        @Override // java.lang.Iterable
        public Iterator<V> iterator() {
            return this;
        }

        public ArrayList<V> toArray() {
            ArrayList<V> arrayList = new ArrayList<>(this.map.size);
            while (this.hasNext) {
                arrayList.add(next());
            }
            return arrayList;
        }

        public void toArray(ArrayList<V> arrayList) {
            while (this.hasNext) {
                arrayList.add(next());
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/util/CuckooObjectMap$Keys.class */
    public static class Keys<K> extends MapIterator<K, Object> implements Iterable<K>, Iterator<K> {
        @Override // com.esotericsoftware.kryo.util.CuckooObjectMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.esotericsoftware.kryo.util.CuckooObjectMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public Keys(CuckooObjectMap<K, ?> cuckooObjectMap) {
            super(cuckooObjectMap);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.hasNext;
        }

        @Override // java.util.Iterator
        public K next() {
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            K k = this.map.keyTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            advance();
            return k;
        }

        @Override // java.lang.Iterable
        public Iterator<K> iterator() {
            return this;
        }

        public ArrayList<K> toArray() {
            ArrayList<K> arrayList = new ArrayList<>(this.map.size);
            while (this.hasNext) {
                arrayList.add(next());
            }
            return arrayList;
        }
    }

    public static int nextPowerOfTwo(int i) {
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
}
