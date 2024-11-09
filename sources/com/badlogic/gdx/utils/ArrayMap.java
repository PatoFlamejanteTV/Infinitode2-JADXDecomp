package com.badlogic.gdx.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.reflect.ArrayReflection;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/ArrayMap.class */
public class ArrayMap<K, V> implements Iterable<ObjectMap.Entry<K, V>> {
    public K[] keys;
    public V[] values;
    public int size;
    public boolean ordered;
    private transient Entries entries1;
    private transient Entries entries2;
    private transient Values values1;
    private transient Values values2;
    private transient Keys keys1;
    private transient Keys keys2;

    public ArrayMap() {
        this(true, 16);
    }

    public ArrayMap(int i) {
        this(true, i);
    }

    public ArrayMap(boolean z, int i) {
        this.ordered = z;
        this.keys = (K[]) new Object[i];
        this.values = (V[]) new Object[i];
    }

    public ArrayMap(boolean z, int i, Class cls, Class cls2) {
        this.ordered = z;
        this.keys = (K[]) ((Object[]) ArrayReflection.newInstance(cls, i));
        this.values = (V[]) ((Object[]) ArrayReflection.newInstance(cls2, i));
    }

    public ArrayMap(Class cls, Class cls2) {
        this(false, 16, cls, cls2);
    }

    public ArrayMap(ArrayMap arrayMap) {
        this(arrayMap.ordered, arrayMap.size, arrayMap.keys.getClass().getComponentType(), arrayMap.values.getClass().getComponentType());
        this.size = arrayMap.size;
        System.arraycopy(arrayMap.keys, 0, this.keys, 0, this.size);
        System.arraycopy(arrayMap.values, 0, this.values, 0, this.size);
    }

    public int put(K k, V v) {
        int indexOfKey = indexOfKey(k);
        int i = indexOfKey;
        if (indexOfKey == -1) {
            if (this.size == this.keys.length) {
                resize(Math.max(8, (int) (this.size * 1.75f)));
            }
            int i2 = this.size;
            this.size = i2 + 1;
            i = i2;
        }
        this.keys[i] = k;
        this.values[i] = v;
        return i;
    }

    public int put(K k, V v, int i) {
        int indexOfKey = indexOfKey(k);
        if (indexOfKey != -1) {
            removeIndex(indexOfKey);
        } else if (this.size == this.keys.length) {
            resize(Math.max(8, (int) (this.size * 1.75f)));
        }
        K[] kArr = this.keys;
        System.arraycopy(kArr, i, kArr, i + 1, this.size - i);
        V[] vArr = this.values;
        System.arraycopy(vArr, i, vArr, i + 1, this.size - i);
        this.keys[i] = k;
        this.values[i] = v;
        this.size++;
        return i;
    }

    public void putAll(ArrayMap<? extends K, ? extends V> arrayMap) {
        putAll(arrayMap, 0, arrayMap.size);
    }

    public void putAll(ArrayMap<? extends K, ? extends V> arrayMap, int i, int i2) {
        if (i + i2 > arrayMap.size) {
            throw new IllegalArgumentException("offset + length must be <= size: " + i + " + " + i2 + " <= " + arrayMap.size);
        }
        int i3 = (this.size + i2) - i;
        if (i3 >= this.keys.length) {
            resize(Math.max(8, (int) (i3 * 1.75f)));
        }
        System.arraycopy(arrayMap.keys, i, this.keys, this.size, i2);
        System.arraycopy(arrayMap.values, i, this.values, this.size, i2);
        this.size += i2;
    }

    @Null
    public V get(K k) {
        return get(k, null);
    }

    @Null
    public V get(K k, @Null V v) {
        K[] kArr = this.keys;
        int i = this.size - 1;
        if (k == null) {
            while (i >= 0) {
                if (kArr[i] == k) {
                    return this.values[i];
                }
                i--;
            }
        } else {
            while (i >= 0) {
                if (k.equals(kArr[i])) {
                    return this.values[i];
                }
                i--;
            }
        }
        return v;
    }

    @Null
    public K getKey(V v, boolean z) {
        V[] vArr = this.values;
        int i = this.size - 1;
        if (z || v == null) {
            while (i >= 0) {
                if (vArr[i] == v) {
                    return this.keys[i];
                }
                i--;
            }
            return null;
        }
        while (i >= 0) {
            if (v.equals(vArr[i])) {
                return this.keys[i];
            }
            i--;
        }
        return null;
    }

    public K getKeyAt(int i) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException(String.valueOf(i));
        }
        return this.keys[i];
    }

    public V getValueAt(int i) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException(String.valueOf(i));
        }
        return this.values[i];
    }

    public K firstKey() {
        if (this.size == 0) {
            throw new IllegalStateException("Map is empty.");
        }
        return this.keys[0];
    }

    public V firstValue() {
        if (this.size == 0) {
            throw new IllegalStateException("Map is empty.");
        }
        return this.values[0];
    }

    public void setKey(int i, K k) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException(String.valueOf(i));
        }
        this.keys[i] = k;
    }

    public void setValue(int i, V v) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException(String.valueOf(i));
        }
        this.values[i] = v;
    }

    public void insert(int i, K k, V v) {
        if (i > this.size) {
            throw new IndexOutOfBoundsException(String.valueOf(i));
        }
        if (this.size == this.keys.length) {
            resize(Math.max(8, (int) (this.size * 1.75f)));
        }
        if (this.ordered) {
            K[] kArr = this.keys;
            System.arraycopy(kArr, i, kArr, i + 1, this.size - i);
            V[] vArr = this.values;
            System.arraycopy(vArr, i, vArr, i + 1, this.size - i);
        } else {
            this.keys[this.size] = this.keys[i];
            this.values[this.size] = this.values[i];
        }
        this.size++;
        this.keys[i] = k;
        this.values[i] = v;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0033, code lost:            return false;     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0021, code lost:            if (r7 < 0) goto L21;     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0024, code lost:            r2 = r7;        r7 = r7 - 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002e, code lost:            if (r5.equals(r0[r2]) == false) goto L23;     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0031, code lost:            return true;     */
    /* JADX WARN: Code restructure failed: missing block: B:21:?, code lost:            return false;     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x000d, code lost:            if (r5 == null) goto L4;     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0011, code lost:            if (r7 < 0) goto L18;     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0014, code lost:            r1 = r7;        r7 = r7 - 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x001b, code lost:            if (r0[r1] != r5) goto L20;     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:            return true;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean containsKey(K r5) {
        /*
            r4 = this;
            r0 = r4
            K[] r0 = r0.keys
            r6 = r0
            r0 = r4
            int r0 = r0.size
            r1 = 1
            int r0 = r0 - r1
            r7 = r0
            r0 = r5
            if (r0 != 0) goto L20
        L10:
            r0 = r7
            if (r0 < 0) goto L33
            r0 = r6
            r1 = r7
            int r7 = r7 + (-1)
            r0 = r0[r1]
            r1 = r5
            if (r0 != r1) goto L10
            r0 = 1
            return r0
        L20:
            r0 = r7
            if (r0 < 0) goto L33
            r0 = r5
            r1 = r6
            r2 = r7
            int r7 = r7 + (-1)
            r1 = r1[r2]
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L20
            r0 = 1
            return r0
        L33:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.utils.ArrayMap.containsKey(java.lang.Object):boolean");
    }

    public boolean containsValue(V v, boolean z) {
        V[] vArr = this.values;
        int i = this.size - 1;
        if (z || v == null) {
            while (i >= 0) {
                int i2 = i;
                i--;
                if (vArr[i2] == v) {
                    return true;
                }
            }
            return false;
        }
        while (i >= 0) {
            int i3 = i;
            i--;
            if (v.equals(vArr[i3])) {
                return true;
            }
        }
        return false;
    }

    public int indexOfKey(K k) {
        K[] kArr = this.keys;
        if (k == null) {
            int i = this.size;
            for (int i2 = 0; i2 < i; i2++) {
                if (kArr[i2] == k) {
                    return i2;
                }
            }
            return -1;
        }
        int i3 = this.size;
        for (int i4 = 0; i4 < i3; i4++) {
            if (k.equals(kArr[i4])) {
                return i4;
            }
        }
        return -1;
    }

    public int indexOfValue(V v, boolean z) {
        V[] vArr = this.values;
        if (z || v == null) {
            int i = this.size;
            for (int i2 = 0; i2 < i; i2++) {
                if (vArr[i2] == v) {
                    return i2;
                }
            }
            return -1;
        }
        int i3 = this.size;
        for (int i4 = 0; i4 < i3; i4++) {
            if (v.equals(vArr[i4])) {
                return i4;
            }
        }
        return -1;
    }

    @Null
    public V removeKey(K k) {
        K[] kArr = this.keys;
        if (k == null) {
            int i = this.size;
            for (int i2 = 0; i2 < i; i2++) {
                if (kArr[i2] == k) {
                    V v = this.values[i2];
                    removeIndex(i2);
                    return v;
                }
            }
            return null;
        }
        int i3 = this.size;
        for (int i4 = 0; i4 < i3; i4++) {
            if (k.equals(kArr[i4])) {
                V v2 = this.values[i4];
                removeIndex(i4);
                return v2;
            }
        }
        return null;
    }

    public boolean removeValue(V v, boolean z) {
        V[] vArr = this.values;
        if (z || v == null) {
            int i = this.size;
            for (int i2 = 0; i2 < i; i2++) {
                if (vArr[i2] == v) {
                    removeIndex(i2);
                    return true;
                }
            }
            return false;
        }
        int i3 = this.size;
        for (int i4 = 0; i4 < i3; i4++) {
            if (v.equals(vArr[i4])) {
                removeIndex(i4);
                return true;
            }
        }
        return false;
    }

    public void removeIndex(int i) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException(String.valueOf(i));
        }
        K[] kArr = this.keys;
        this.size--;
        if (this.ordered) {
            System.arraycopy(kArr, i + 1, kArr, i, this.size - i);
            System.arraycopy(this.values, i + 1, this.values, i, this.size - i);
        } else {
            kArr[i] = kArr[this.size];
            V[] vArr = this.values;
            vArr[i] = vArr[this.size];
        }
        kArr[this.size] = null;
        this.values[this.size] = null;
    }

    public boolean notEmpty() {
        return this.size > 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public K peekKey() {
        return this.keys[this.size - 1];
    }

    public V peekValue() {
        return this.values[this.size - 1];
    }

    public void clear(int i) {
        if (this.keys.length <= i) {
            clear();
        } else {
            this.size = 0;
            resize(i);
        }
    }

    public void clear() {
        Arrays.fill(this.keys, 0, this.size, (Object) null);
        Arrays.fill(this.values, 0, this.size, (Object) null);
        this.size = 0;
    }

    public void shrink() {
        if (this.keys.length == this.size) {
            return;
        }
        resize(this.size);
    }

    public void ensureCapacity(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("additionalCapacity must be >= 0: " + i);
        }
        int i2 = this.size + i;
        if (i2 > this.keys.length) {
            resize(Math.max(Math.max(8, i2), (int) (this.size * 1.75f)));
        }
    }

    protected void resize(int i) {
        K[] kArr = (K[]) ((Object[]) ArrayReflection.newInstance(this.keys.getClass().getComponentType(), i));
        System.arraycopy(this.keys, 0, kArr, 0, Math.min(this.size, kArr.length));
        this.keys = kArr;
        V[] vArr = (V[]) ((Object[]) ArrayReflection.newInstance(this.values.getClass().getComponentType(), i));
        System.arraycopy(this.values, 0, vArr, 0, Math.min(this.size, vArr.length));
        this.values = vArr;
    }

    public void reverse() {
        int i = this.size - 1;
        int i2 = this.size / 2;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i - i3;
            K k = this.keys[i3];
            K[] kArr = this.keys;
            kArr[i3] = kArr[i4];
            this.keys[i4] = k;
            V v = this.values[i3];
            V[] vArr = this.values;
            vArr[i3] = vArr[i4];
            this.values[i4] = v;
        }
    }

    public void shuffle() {
        for (int i = this.size - 1; i >= 0; i--) {
            int random = MathUtils.random(i);
            K k = this.keys[i];
            K[] kArr = this.keys;
            kArr[i] = kArr[random];
            this.keys[random] = k;
            V v = this.values[i];
            V[] vArr = this.values;
            vArr[i] = vArr[random];
            this.values[random] = v;
        }
    }

    public void truncate(int i) {
        if (this.size <= i) {
            return;
        }
        for (int i2 = i; i2 < this.size; i2++) {
            this.keys[i2] = null;
            this.values[i2] = null;
        }
        this.size = i;
    }

    public int hashCode() {
        K[] kArr = this.keys;
        V[] vArr = this.values;
        int i = 0;
        int i2 = this.size;
        for (int i3 = 0; i3 < i2; i3++) {
            K k = kArr[i3];
            V v = vArr[i3];
            if (k != null) {
                i += k.hashCode() * 31;
            }
            if (v != null) {
                i += v.hashCode();
            }
        }
        return i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ArrayMap)) {
            return false;
        }
        ArrayMap arrayMap = (ArrayMap) obj;
        if (arrayMap.size != this.size) {
            return false;
        }
        K[] kArr = this.keys;
        V[] vArr = this.values;
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            K k = kArr[i2];
            V v = vArr[i2];
            if (v == null) {
                if (arrayMap.get(k, ObjectMap.dummy) != null) {
                    return false;
                }
            } else if (!v.equals(arrayMap.get(k))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean equalsIdentity(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ArrayMap)) {
            return false;
        }
        ArrayMap arrayMap = (ArrayMap) obj;
        if (arrayMap.size != this.size) {
            return false;
        }
        K[] kArr = this.keys;
        V[] vArr = this.values;
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (vArr[i2] != arrayMap.get(kArr[i2], ObjectMap.dummy)) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        if (this.size == 0) {
            return "{}";
        }
        K[] kArr = this.keys;
        V[] vArr = this.values;
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append('{');
        stringBuilder.append(kArr[0]);
        stringBuilder.append('=');
        stringBuilder.append(vArr[0]);
        for (int i = 1; i < this.size; i++) {
            stringBuilder.append(", ");
            stringBuilder.append(kArr[i]);
            stringBuilder.append('=');
            stringBuilder.append(vArr[i]);
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    @Override // java.lang.Iterable
    public Iterator<ObjectMap.Entry<K, V>> iterator() {
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
            this.entries1.index = 0;
            this.entries1.valid = true;
            this.entries2.valid = false;
            return this.entries1;
        }
        this.entries2.index = 0;
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
            this.values1.index = 0;
            this.values1.valid = true;
            this.values2.valid = false;
            return this.values1;
        }
        this.values2.index = 0;
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
            this.keys1.index = 0;
            this.keys1.valid = true;
            this.keys2.valid = false;
            return this.keys1;
        }
        this.keys2.index = 0;
        this.keys2.valid = true;
        this.keys1.valid = false;
        return this.keys2;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/ArrayMap$Entries.class */
    public static class Entries<K, V> implements Iterable<ObjectMap.Entry<K, V>>, Iterator<ObjectMap.Entry<K, V>> {
        private final ArrayMap<K, V> map;
        int index;
        ObjectMap.Entry<K, V> entry = new ObjectMap.Entry<>();
        boolean valid = true;

        public Entries(ArrayMap<K, V> arrayMap) {
            this.map = arrayMap;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.valid) {
                return this.index < this.map.size;
            }
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
        }

        @Override // java.lang.Iterable
        public Iterator<ObjectMap.Entry<K, V>> iterator() {
            return this;
        }

        @Override // java.util.Iterator
        public ObjectMap.Entry<K, V> next() {
            if (this.index >= this.map.size) {
                throw new NoSuchElementException(String.valueOf(this.index));
            }
            if (!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            this.entry.key = this.map.keys[this.index];
            ObjectMap.Entry<K, V> entry = this.entry;
            V[] vArr = this.map.values;
            int i = this.index;
            this.index = i + 1;
            entry.value = vArr[i];
            return this.entry;
        }

        @Override // java.util.Iterator
        public void remove() {
            this.index--;
            this.map.removeIndex(this.index);
        }

        public void reset() {
            this.index = 0;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/ArrayMap$Values.class */
    public static class Values<V> implements Iterable<V>, Iterator<V> {
        private final ArrayMap<Object, V> map;
        int index;
        boolean valid = true;

        public Values(ArrayMap<Object, V> arrayMap) {
            this.map = arrayMap;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.valid) {
                return this.index < this.map.size;
            }
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
        }

        @Override // java.lang.Iterable
        public Iterator<V> iterator() {
            return this;
        }

        @Override // java.util.Iterator
        public V next() {
            if (this.index >= this.map.size) {
                throw new NoSuchElementException(String.valueOf(this.index));
            }
            if (!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            V[] vArr = this.map.values;
            int i = this.index;
            this.index = i + 1;
            return vArr[i];
        }

        @Override // java.util.Iterator
        public void remove() {
            this.index--;
            this.map.removeIndex(this.index);
        }

        public void reset() {
            this.index = 0;
        }

        public Array<V> toArray() {
            return new Array<>(true, this.map.values, this.index, this.map.size - this.index);
        }

        public Array<V> toArray(Array array) {
            array.addAll(this.map.values, this.index, this.map.size - this.index);
            return array;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/ArrayMap$Keys.class */
    public static class Keys<K> implements Iterable<K>, Iterator<K> {
        private final ArrayMap<K, Object> map;
        int index;
        boolean valid = true;

        public Keys(ArrayMap<K, Object> arrayMap) {
            this.map = arrayMap;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.valid) {
                return this.index < this.map.size;
            }
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
        }

        @Override // java.lang.Iterable
        public Iterator<K> iterator() {
            return this;
        }

        @Override // java.util.Iterator
        public K next() {
            if (this.index >= this.map.size) {
                throw new NoSuchElementException(String.valueOf(this.index));
            }
            if (!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            K[] kArr = this.map.keys;
            int i = this.index;
            this.index = i + 1;
            return kArr[i];
        }

        @Override // java.util.Iterator
        public void remove() {
            this.index--;
            this.map.removeIndex(this.index);
        }

        public void reset() {
            this.index = 0;
        }

        public Array<K> toArray() {
            return new Array<>(true, this.map.keys, this.index, this.map.size - this.index);
        }

        public Array<K> toArray(Array array) {
            array.addAll(this.map.keys, this.index, this.map.size - this.index);
            return array;
        }
    }
}
