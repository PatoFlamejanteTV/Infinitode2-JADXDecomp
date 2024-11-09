package com.badlogic.gdx.utils;

import com.badlogic.gdx.utils.ObjectMap;
import java.util.NoSuchElementException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/OrderedMap.class */
public class OrderedMap<K, V> extends ObjectMap<K, V> {
    final Array<K> keys;

    public OrderedMap() {
        this.keys = new Array<>();
    }

    public OrderedMap(int i) {
        super(i);
        this.keys = new Array<>(i);
    }

    public OrderedMap(int i, float f) {
        super(i, f);
        this.keys = new Array<>(i);
    }

    public OrderedMap(OrderedMap<? extends K, ? extends V> orderedMap) {
        super(orderedMap);
        this.keys = new Array<>(orderedMap.keys);
    }

    @Override // com.badlogic.gdx.utils.ObjectMap
    public V put(K k, V v) {
        int locateKey = locateKey(k);
        if (locateKey >= 0) {
            V v2 = this.valueTable[locateKey];
            this.valueTable[locateKey] = v;
            return v2;
        }
        int i = -(locateKey + 1);
        this.keyTable[i] = k;
        this.valueTable[i] = v;
        this.keys.add(k);
        int i2 = this.size + 1;
        this.size = i2;
        if (i2 >= this.threshold) {
            resize(this.keyTable.length << 1);
            return null;
        }
        return null;
    }

    public <T extends K> void putAll(OrderedMap<T, ? extends V> orderedMap) {
        ensureCapacity(orderedMap.size);
        T[] tArr = orderedMap.keys.items;
        int i = orderedMap.keys.size;
        for (int i2 = 0; i2 < i; i2++) {
            T t = tArr[i2];
            put(t, orderedMap.get(t));
        }
    }

    @Override // com.badlogic.gdx.utils.ObjectMap
    public V remove(K k) {
        this.keys.removeValue(k, false);
        return (V) super.remove(k);
    }

    public V removeIndex(int i) {
        return (V) super.remove(this.keys.removeIndex(i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean alter(K k, K k2) {
        int indexOf;
        if (containsKey(k2) || (indexOf = this.keys.indexOf(k, false)) == -1) {
            return false;
        }
        super.put(k2, super.remove(k));
        this.keys.set(indexOf, k2);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean alterIndex(int i, K k) {
        if (i < 0 || i >= this.size || containsKey(k)) {
            return false;
        }
        super.put(k, super.remove(this.keys.get(i)));
        this.keys.set(i, k);
        return true;
    }

    @Override // com.badlogic.gdx.utils.ObjectMap
    public void clear(int i) {
        this.keys.clear();
        super.clear(i);
    }

    @Override // com.badlogic.gdx.utils.ObjectMap
    public void clear() {
        this.keys.clear();
        super.clear();
    }

    public Array<K> orderedKeys() {
        return this.keys;
    }

    @Override // com.badlogic.gdx.utils.ObjectMap, java.lang.Iterable
    public ObjectMap.Entries<K, V> iterator() {
        return entries();
    }

    @Override // com.badlogic.gdx.utils.ObjectMap
    public ObjectMap.Entries<K, V> entries() {
        if (Collections.allocateIterators) {
            return new OrderedMapEntries(this);
        }
        if (this.entries1 == null) {
            this.entries1 = new OrderedMapEntries(this);
            this.entries2 = new OrderedMapEntries(this);
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

    @Override // com.badlogic.gdx.utils.ObjectMap
    public ObjectMap.Values<V> values() {
        if (Collections.allocateIterators) {
            return new OrderedMapValues(this);
        }
        if (this.values1 == null) {
            this.values1 = new OrderedMapValues(this);
            this.values2 = new OrderedMapValues(this);
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

    @Override // com.badlogic.gdx.utils.ObjectMap
    public ObjectMap.Keys<K> keys() {
        if (Collections.allocateIterators) {
            return new OrderedMapKeys(this);
        }
        if (this.keys1 == null) {
            this.keys1 = new OrderedMapKeys(this);
            this.keys2 = new OrderedMapKeys(this);
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

    @Override // com.badlogic.gdx.utils.ObjectMap
    protected String toString(String str, boolean z) {
        if (this.size == 0) {
            return z ? "{}" : "";
        }
        java.lang.StringBuilder sb = new java.lang.StringBuilder(32);
        if (z) {
            sb.append('{');
        }
        Array<K> array = this.keys;
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            K k = array.get(i2);
            if (i2 > 0) {
                sb.append(str);
            }
            sb.append(k == this ? "(this)" : k);
            sb.append('=');
            V v = get(k);
            sb.append(v == this ? "(this)" : v);
        }
        if (z) {
            sb.append('}');
        }
        return sb.toString();
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/OrderedMap$OrderedMapEntries.class */
    public static class OrderedMapEntries<K, V> extends ObjectMap.Entries<K, V> {
        private Array<K> keys;

        public OrderedMapEntries(OrderedMap<K, V> orderedMap) {
            super(orderedMap);
            this.keys = orderedMap.keys;
        }

        @Override // com.badlogic.gdx.utils.ObjectMap.Entries, com.badlogic.gdx.utils.ObjectMap.MapIterator
        public void reset() {
            this.currentIndex = -1;
            this.nextIndex = 0;
            this.hasNext = this.map.size > 0;
        }

        @Override // com.badlogic.gdx.utils.ObjectMap.Entries, java.util.Iterator
        public ObjectMap.Entry next() {
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            if (!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            this.currentIndex = this.nextIndex;
            this.entry.key = this.keys.get(this.nextIndex);
            this.entry.value = this.map.get(this.entry.key);
            this.nextIndex++;
            this.hasNext = this.nextIndex < this.map.size;
            return this.entry;
        }

        @Override // com.badlogic.gdx.utils.ObjectMap.Entries, com.badlogic.gdx.utils.ObjectMap.MapIterator, java.util.Iterator
        public void remove() {
            if (this.currentIndex < 0) {
                throw new IllegalStateException("next must be called before remove.");
            }
            this.map.remove(this.entry.key);
            this.nextIndex--;
            this.currentIndex = -1;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/OrderedMap$OrderedMapKeys.class */
    public static class OrderedMapKeys<K> extends ObjectMap.Keys<K> {
        private Array<K> keys;

        public OrderedMapKeys(OrderedMap<K, ?> orderedMap) {
            super(orderedMap);
            this.keys = orderedMap.keys;
        }

        @Override // com.badlogic.gdx.utils.ObjectMap.Keys, com.badlogic.gdx.utils.ObjectMap.MapIterator
        public void reset() {
            this.currentIndex = -1;
            this.nextIndex = 0;
            this.hasNext = this.map.size > 0;
        }

        @Override // com.badlogic.gdx.utils.ObjectMap.Keys, java.util.Iterator
        public K next() {
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            if (!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            K k = this.keys.get(this.nextIndex);
            this.currentIndex = this.nextIndex;
            this.nextIndex++;
            this.hasNext = this.nextIndex < this.map.size;
            return k;
        }

        @Override // com.badlogic.gdx.utils.ObjectMap.Keys, com.badlogic.gdx.utils.ObjectMap.MapIterator, java.util.Iterator
        public void remove() {
            if (this.currentIndex < 0) {
                throw new IllegalStateException("next must be called before remove.");
            }
            ((OrderedMap) this.map).removeIndex(this.currentIndex);
            this.nextIndex = this.currentIndex;
            this.currentIndex = -1;
        }

        @Override // com.badlogic.gdx.utils.ObjectMap.Keys
        public Array<K> toArray(Array<K> array) {
            array.addAll((Array<? extends K>) this.keys, this.nextIndex, this.keys.size - this.nextIndex);
            this.nextIndex = this.keys.size;
            this.hasNext = false;
            return array;
        }

        @Override // com.badlogic.gdx.utils.ObjectMap.Keys
        public Array<K> toArray() {
            return toArray(new Array<>(true, this.keys.size - this.nextIndex));
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/OrderedMap$OrderedMapValues.class */
    public static class OrderedMapValues<V> extends ObjectMap.Values<V> {
        private Array keys;

        public OrderedMapValues(OrderedMap<?, V> orderedMap) {
            super(orderedMap);
            this.keys = orderedMap.keys;
        }

        @Override // com.badlogic.gdx.utils.ObjectMap.Values, com.badlogic.gdx.utils.ObjectMap.MapIterator
        public void reset() {
            this.currentIndex = -1;
            this.nextIndex = 0;
            this.hasNext = this.map.size > 0;
        }

        @Override // com.badlogic.gdx.utils.ObjectMap.Values, java.util.Iterator
        public V next() {
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            if (!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            V v = this.map.get(this.keys.get(this.nextIndex));
            this.currentIndex = this.nextIndex;
            this.nextIndex++;
            this.hasNext = this.nextIndex < this.map.size;
            return v;
        }

        @Override // com.badlogic.gdx.utils.ObjectMap.Values, com.badlogic.gdx.utils.ObjectMap.MapIterator, java.util.Iterator
        public void remove() {
            if (this.currentIndex < 0) {
                throw new IllegalStateException("next must be called before remove.");
            }
            ((OrderedMap) this.map).removeIndex(this.currentIndex);
            this.nextIndex = this.currentIndex;
            this.currentIndex = -1;
        }

        @Override // com.badlogic.gdx.utils.ObjectMap.Values
        public Array<V> toArray(Array<V> array) {
            int i = this.keys.size;
            array.ensureCapacity(i - this.nextIndex);
            Object[] objArr = this.keys.items;
            for (int i2 = this.nextIndex; i2 < i; i2++) {
                array.add(this.map.get(objArr[i2]));
            }
            this.currentIndex = i - 1;
            this.nextIndex = i;
            this.hasNext = false;
            return array;
        }

        @Override // com.badlogic.gdx.utils.ObjectMap.Values
        public Array<V> toArray() {
            return toArray(new Array<>(true, this.keys.size - this.nextIndex));
        }
    }
}
