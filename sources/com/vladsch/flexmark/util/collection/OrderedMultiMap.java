package com.vladsch.flexmark.util.collection;

import com.vladsch.flexmark.util.collection.iteration.BitSetIterable;
import com.vladsch.flexmark.util.collection.iteration.BitSetIterator;
import com.vladsch.flexmark.util.collection.iteration.Indexed;
import com.vladsch.flexmark.util.collection.iteration.IndexedIterable;
import com.vladsch.flexmark.util.collection.iteration.IndexedIterator;
import com.vladsch.flexmark.util.collection.iteration.ReversibleIndexedIterator;
import com.vladsch.flexmark.util.collection.iteration.ReversibleIterable;
import com.vladsch.flexmark.util.misc.Pair;
import com.vladsch.flexmark.util.misc.Paired;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/OrderedMultiMap.class */
public class OrderedMultiMap<K, V> implements Iterable<Map.Entry<K, V>>, Map<K, V> {
    private final OrderedSet<K> keySet;
    private final OrderedSet<V> valueSet;
    private final CollectionHost<Paired<K, V>> host;
    boolean isInKeyUpdate;
    boolean isInValueUpdate;
    private Indexed<Map.Entry<K, V>> indexedProxy;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !OrderedMultiMap.class.desiredAssertionStatus();
    }

    public OrderedMultiMap() {
        this(0, null);
    }

    public OrderedMultiMap(int i) {
        this(i, null);
    }

    public OrderedMultiMap(CollectionHost<Paired<K, V>> collectionHost) {
        this(0, collectionHost);
    }

    public OrderedMultiMap(int i, CollectionHost<Paired<K, V>> collectionHost) {
        this.host = collectionHost;
        this.indexedProxy = null;
        this.valueSet = new OrderedSet<>(i, new CollectionHost<V>() { // from class: com.vladsch.flexmark.util.collection.OrderedMultiMap.1
            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public void adding(int i2, V v, Object obj) {
                OrderedMultiMap.this.addingValue(i2, v, obj);
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public Object removing(int i2, V v) {
                return OrderedMultiMap.this.removingValue(i2, v);
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public void clearing() {
                OrderedMultiMap.this.clear();
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public void addingNulls(int i2) {
                OrderedMultiMap.this.addingNullValue(i2);
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public boolean skipHostUpdate() {
                return OrderedMultiMap.this.isInKeyUpdate;
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public int getIteratorModificationCount() {
                return OrderedMultiMap.this.getModificationCount();
            }
        });
        this.keySet = new OrderedSet<>(i, new CollectionHost<K>() { // from class: com.vladsch.flexmark.util.collection.OrderedMultiMap.2
            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public void adding(int i2, K k, Object obj) {
                OrderedMultiMap.this.addingKey(i2, k, obj);
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public Object removing(int i2, K k) {
                return OrderedMultiMap.this.removingKey(i2, k);
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public void clearing() {
                OrderedMultiMap.this.clear();
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public void addingNulls(int i2) {
                OrderedMultiMap.this.addingNullKey(i2);
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public boolean skipHostUpdate() {
                return OrderedMultiMap.this.isInValueUpdate;
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public int getIteratorModificationCount() {
                return OrderedMultiMap.this.getModificationCount();
            }
        });
    }

    public Indexed<Map.Entry<K, V>> getIndexedProxy() {
        if (this.indexedProxy != null) {
            return this.indexedProxy;
        }
        this.indexedProxy = new Indexed<Map.Entry<K, V>>() { // from class: com.vladsch.flexmark.util.collection.OrderedMultiMap.3
            @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
            public Map.Entry<K, V> get(int i) {
                return OrderedMultiMap.this.getEntry(i);
            }

            @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
            public void set(int i, Map.Entry<K, V> entry) {
                throw new UnsupportedOperationException();
            }

            @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
            public void removeAt(int i) {
                OrderedMultiMap.this.removeEntryIndex(i);
            }

            @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
            public int size() {
                return OrderedMultiMap.this.size();
            }

            @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
            public int modificationCount() {
                return OrderedMultiMap.this.getModificationCount();
            }
        };
        return this.indexedProxy;
    }

    Map.Entry<K, V> getEntry(int i) {
        return new MapEntry(this.keySet.getValueOrNull(i), this.valueSet.getValueOrNull(i));
    }

    public int getModificationCount() {
        return (int) (this.keySet.getModificationCount() + this.valueSet.getModificationCount());
    }

    void addingKey(int i, K k, Object obj) {
        if (!$assertionsDisabled && this.isInValueUpdate) {
            throw new AssertionError();
        }
        this.isInValueUpdate = true;
        if (this.host != null && !this.host.skipHostUpdate()) {
            this.host.adding(i, new Pair(k, obj), null);
        }
        if (obj == null) {
            this.valueSet.addNulls(i);
        } else {
            this.valueSet.add(obj);
        }
        this.isInValueUpdate = false;
    }

    void addingNullKey(int i) {
        if (!$assertionsDisabled && this.isInValueUpdate) {
            throw new AssertionError();
        }
        this.isInValueUpdate = true;
        if (this.host != null && !this.host.skipHostUpdate()) {
            this.host.addingNulls(i);
        }
        while (valueSet().size() <= i) {
            this.valueSet.add(null);
        }
        this.isInValueUpdate = false;
    }

    Object removingKey(int i, K k) {
        if (!$assertionsDisabled && this.isInValueUpdate) {
            throw new AssertionError();
        }
        this.isInValueUpdate = true;
        if (this.host != null && !this.host.skipHostUpdate()) {
            this.host.removing(i, new Pair(k, null));
        }
        Object removeIndexHosted = this.valueSet.removeIndexHosted(i);
        this.isInValueUpdate = false;
        return removeIndexHosted;
    }

    void addingValue(int i, V v, Object obj) {
        if (!$assertionsDisabled && this.isInKeyUpdate) {
            throw new AssertionError();
        }
        this.isInKeyUpdate = true;
        if (this.host != null && !this.host.skipHostUpdate()) {
            this.host.adding(i, new Pair(obj, v), null);
        }
        if (obj == null) {
            this.keySet.addNulls(i);
        } else {
            this.keySet.add(obj);
        }
        this.isInKeyUpdate = false;
    }

    void addingNullValue(int i) {
        if (!$assertionsDisabled && this.isInKeyUpdate) {
            throw new AssertionError();
        }
        this.isInKeyUpdate = true;
        if (this.host != null && !this.host.skipHostUpdate()) {
            this.host.addingNulls(i);
        }
        while (this.keySet.size() <= i) {
            this.keySet.add(null);
        }
        this.isInKeyUpdate = false;
    }

    Object removingValue(int i, V v) {
        if (!$assertionsDisabled && this.isInKeyUpdate) {
            throw new AssertionError();
        }
        this.isInKeyUpdate = true;
        if (this.host != null && !this.host.skipHostUpdate()) {
            this.host.removing(i, new Pair(null, v));
        }
        Object removeIndexHosted = this.keySet.removeIndexHosted(i);
        this.isInKeyUpdate = false;
        return removeIndexHosted;
    }

    @Override // java.util.Map
    public int size() {
        return this.keySet.size();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.keySet.isEmpty();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.keySet.contains(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.keySet.isValidIndex(this.valueSet.indexOf(obj));
    }

    @Override // java.util.Map
    public V get(Object obj) {
        return getKeyValue(obj);
    }

    public V getKeyValue(Object obj) {
        int indexOf = this.keySet.indexOf(obj);
        if (indexOf == -1) {
            return null;
        }
        return this.valueSet.getValue(indexOf);
    }

    public K getValueKey(Object obj) {
        int indexOf = this.valueSet.indexOf(obj);
        if (indexOf == -1) {
            return null;
        }
        return this.keySet.getValue(indexOf);
    }

    @Override // java.util.Map
    public V put(K k, V v) {
        return putKeyValue(k, v);
    }

    public void addNullEntry(int i) {
        this.isInKeyUpdate = true;
        this.isInValueUpdate = true;
        if (this.host != null && !this.host.skipHostUpdate()) {
            this.host.addingNulls(i);
        }
        this.keySet.addNulls(i);
        this.valueSet.addNulls(i);
        this.isInValueUpdate = false;
        this.isInKeyUpdate = false;
    }

    public boolean putEntry(Map.Entry<K, V> entry) {
        return addKeyValue(entry.getKey(), entry.getValue());
    }

    public boolean putKeyValueEntry(Map.Entry<K, V> entry) {
        return addKeyValue(entry.getKey(), entry.getValue());
    }

    public boolean putValueKeyEntry(Map.Entry<V, K> entry) {
        return addKeyValue(entry.getValue(), entry.getKey());
    }

    public boolean putKeyValuePair(Paired<K, V> paired) {
        return addKeyValue(paired.getFirst(), paired.getSecond());
    }

    public boolean putValueKeyPair(Paired<V, K> paired) {
        return addKeyValue(paired.getSecond(), paired.getFirst());
    }

    public V putKeyValue(K k, V v) {
        if (addKeyValue(k, v)) {
            return null;
        }
        return v;
    }

    public K putValueKey(V v, K k) {
        if (addKeyValue(k, v)) {
            return null;
        }
        return k;
    }

    private boolean addKeyValue(K k, V v) {
        int indexOf = this.keySet.indexOf(k);
        int indexOf2 = this.valueSet.indexOf(v);
        if (indexOf == -1 && indexOf2 == -1) {
            this.isInKeyUpdate = true;
            this.isInValueUpdate = true;
            if (this.host != null && !this.host.skipHostUpdate()) {
                this.host.adding(this.keySet.getValueList().size(), new Pair(k, v), null);
            }
            if (k == null) {
                this.keySet.addNull();
            } else {
                this.keySet.add(k, v);
            }
            if (k == null) {
                this.valueSet.addNull();
            } else {
                this.valueSet.add(v, k);
            }
            this.isInValueUpdate = false;
            this.isInKeyUpdate = false;
            return true;
        }
        if (indexOf == -1) {
            this.isInKeyUpdate = true;
            this.isInValueUpdate = true;
            if (this.host != null && !this.host.skipHostUpdate()) {
                this.host.adding(indexOf2, new Pair(k, v), null);
            }
            if (k == null) {
                this.keySet.removeIndex(indexOf2);
            } else {
                this.keySet.setValueAt(indexOf2, k, v);
            }
            this.isInValueUpdate = false;
            this.isInKeyUpdate = false;
            return true;
        }
        if (indexOf2 == -1) {
            this.isInKeyUpdate = true;
            this.isInValueUpdate = true;
            if (this.host != null && !this.host.skipHostUpdate()) {
                this.host.adding(indexOf, new Pair(k, v), null);
            }
            if (k == null) {
                this.valueSet.removeIndex(indexOf2);
            } else {
                this.valueSet.setValueAt(indexOf, v, k);
            }
            this.isInValueUpdate = false;
            return true;
        }
        if (indexOf2 != indexOf) {
            throw new IllegalStateException("keySet[" + indexOf + "]=" + k + " and valueSet[" + indexOf2 + "]=" + v + " are out of sync");
        }
        return false;
    }

    @Override // java.util.Map
    public V remove(Object obj) {
        return removeKey(obj);
    }

    public Map.Entry<K, V> removeEntry(Map.Entry<K, V> entry) {
        if (removeEntryIndex(-1, entry.getKey(), entry.getValue())) {
            return entry;
        }
        return null;
    }

    boolean removeEntryIndex(int i) {
        return removeEntryIndex(i, this.keySet.getValueOrNull(i), this.valueSet.getValueOrNull(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean removeEntryIndex(int i, K k, V v) {
        int indexOf = this.keySet.indexOf(k);
        int indexOf2 = this.valueSet.indexOf(v);
        if (indexOf != indexOf2) {
            throw new IllegalStateException("keySet[" + indexOf + "]=" + k + " and valueSet[" + indexOf2 + "]=" + v + " are out of sync");
        }
        if (i != -1 && indexOf != i) {
            throw new IllegalStateException("removeEntryIndex " + i + " does not match keySet[" + indexOf + "]=" + k + " and valueSet[" + indexOf2 + "]=" + v + " are out of sync");
        }
        if (indexOf != -1) {
            this.isInKeyUpdate = true;
            this.isInValueUpdate = true;
            if (this.host != null && !this.host.skipHostUpdate()) {
                this.host.removing(indexOf, new Pair(k, v));
            }
            this.keySet.removeHosted(k);
            this.valueSet.removeHosted(v);
            this.isInValueUpdate = false;
            this.isInKeyUpdate = false;
            return true;
        }
        return false;
    }

    public V removeKey(Object obj) {
        int indexOf;
        this.isInKeyUpdate = true;
        if (this.host != null && !this.host.skipHostUpdate() && (indexOf = this.keySet.indexOf(obj)) != -1) {
            this.host.removing(indexOf, new Pair(obj, this.valueSet.isValidIndex(indexOf) ? this.valueSet.getValue(indexOf) : null));
        }
        V v = (V) this.keySet.removeHosted(obj);
        this.isInKeyUpdate = false;
        return v;
    }

    public K removeValue(Object obj) {
        this.isInValueUpdate = true;
        int indexOf = this.valueSet.indexOf(obj);
        if (this.host != null && !this.host.skipHostUpdate() && indexOf != -1) {
            this.host.removing(indexOf, new Pair(this.keySet.isValidIndex(indexOf) ? this.keySet.getValue(indexOf) : null, obj));
        }
        K k = (K) this.valueSet.removeHosted(obj);
        this.isInValueUpdate = false;
        return k;
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        putAllKeyValues(map);
    }

    public void putAllKeyValues(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public void putAllValueKeys(Map<? extends V, ? extends K> map) {
        for (Map.Entry<? extends V, ? extends K> entry : map.entrySet()) {
            putValueKey(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public void clear() {
        this.isInValueUpdate = true;
        this.isInKeyUpdate = true;
        if (this.host != null && !this.host.skipHostUpdate()) {
            this.host.clearing();
        }
        this.keySet.clear();
        this.valueSet.clear();
        this.isInKeyUpdate = false;
        this.isInValueUpdate = false;
    }

    @Override // java.util.Map
    public OrderedSet<K> keySet() {
        return this.keySet;
    }

    @Override // java.util.Map
    public Collection<V> values() {
        if (!this.keySet.isSparse()) {
            return this.valueSet;
        }
        ArrayList arrayList = new ArrayList(this.keySet.size());
        arrayList.addAll(this.valueSet);
        return arrayList;
    }

    public OrderedSet<V> valueSet() {
        return this.valueSet;
    }

    public Collection<K> keys() {
        if (!this.keySet.isSparse()) {
            return this.keySet;
        }
        ArrayList arrayList = new ArrayList(this.valueSet.size());
        arrayList.addAll(this.keySet);
        return arrayList;
    }

    public K getKey(int i) {
        if (this.keySet.isValidIndex(i)) {
            return this.keySet.getValueList().get(i);
        }
        return null;
    }

    public V getValue(int i) {
        if (this.valueSet.isValidIndex(i)) {
            return this.valueSet.getValue(i);
        }
        return null;
    }

    @Override // java.util.Map
    public OrderedSet<Map.Entry<K, V>> entrySet() {
        return keyValueEntrySet();
    }

    public ReversibleIndexedIterator<V> valueIterator() {
        return this.valueSet.iterator();
    }

    public ReversibleIndexedIterator<V> reversedValueIterator() {
        return this.valueSet.reversedIterator();
    }

    public ReversibleIterable<V> valueIterable() {
        return new IndexedIterable(this.valueSet.getIndexedProxy(), this.valueSet.indexIterable());
    }

    public ReversibleIterable<V> reversedValueIterable() {
        return new IndexedIterable(this.valueSet.getIndexedProxy(), this.valueSet.reversedIndexIterable());
    }

    public ReversibleIndexedIterator<K> keyIterator() {
        return keySet().iterator();
    }

    public ReversibleIndexedIterator<K> reversedKeyIterator() {
        return keySet().reversedIterator();
    }

    public ReversibleIterable<K> keyIterable() {
        return new IndexedIterable(this.keySet.getIndexedProxy(), this.keySet.indexIterable());
    }

    public ReversibleIterable<K> reversedKeyIterable() {
        return new IndexedIterable(this.keySet.getIndexedProxy(), this.keySet.reversedIndexIterable());
    }

    public ReversibleIndexedIterator<Map.Entry<K, V>> entrySetIterator() {
        return new IndexedIterator(getIndexedProxy(), new BitSetIterator(getKeyValueUnionSet()));
    }

    public ReversibleIndexedIterator<Map.Entry<K, V>> reversedEntrySetIterator() {
        return new IndexedIterator(getIndexedProxy(), new BitSetIterator(getKeyValueUnionSet(), true));
    }

    public ReversibleIterable<Map.Entry<K, V>> entrySetIterable() {
        return new IndexedIterable(getIndexedProxy(), new BitSetIterable(getKeyValueUnionSet()));
    }

    public ReversibleIterable<Map.Entry<K, V>> reversedEntrySetIterable() {
        return new IndexedIterable(getIndexedProxy(), new BitSetIterable(getKeyValueUnionSet()));
    }

    private BitSet getKeyValueUnionSet() {
        BitSet bitSet = new BitSet(this.keySet.size());
        bitSet.or(this.keySet.getValidIndices());
        bitSet.or(this.valueSet.getValidIndices());
        return bitSet;
    }

    private BitSet getKeyValueIntersectionSet() {
        BitSet bitSet = new BitSet(this.keySet.size());
        bitSet.or(this.keySet.getValidIndices());
        bitSet.and(this.valueSet.getValidIndices());
        return bitSet;
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<K, V>> iterator() {
        return entrySetIterator();
    }

    @Override // java.lang.Iterable
    public void forEach(Consumer<? super Map.Entry<K, V>> consumer) {
        ReversibleIndexedIterator<Map.Entry<K, V>> entrySetIterator = entrySetIterator();
        while (entrySetIterator.hasNext()) {
            consumer.accept(entrySetIterator.next());
        }
    }

    public OrderedSet<Map.Entry<K, V>> keyValueEntrySet() {
        this.isInValueUpdate = true;
        this.isInKeyUpdate = true;
        OrderedSet<Map.Entry<K, V>> orderedSet = new OrderedSet<>(this.keySet.size(), new CollectionHost<Map.Entry<K, V>>() { // from class: com.vladsch.flexmark.util.collection.OrderedMultiMap.4
            static final /* synthetic */ boolean $assertionsDisabled;

            static {
                $assertionsDisabled = !OrderedMultiMap.class.desiredAssertionStatus();
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public void adding(int i, Map.Entry<K, V> entry, Object obj) {
                if (!$assertionsDisabled && obj != null) {
                    throw new AssertionError();
                }
                OrderedMultiMap.this.putKeyValue(entry.getKey(), entry.getValue());
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public Object removing(int i, Map.Entry<K, V> entry) {
                if (OrderedMultiMap.this.removeEntryIndex(i, entry.getKey(), entry.getValue())) {
                    return entry;
                }
                return null;
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public void clearing() {
                OrderedMultiMap.this.clear();
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public void addingNulls(int i) {
                OrderedMultiMap.this.addNullEntry(i);
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public boolean skipHostUpdate() {
                return OrderedMultiMap.this.isInKeyUpdate || OrderedMultiMap.this.isInValueUpdate;
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public int getIteratorModificationCount() {
                return OrderedMultiMap.this.getModificationCount();
            }
        });
        ReversibleIndexedIterator<Map.Entry<K, V>> entrySetIterator = entrySetIterator();
        while (entrySetIterator.hasNext()) {
            orderedSet.add(entrySetIterator.next());
        }
        this.isInValueUpdate = false;
        this.isInKeyUpdate = false;
        return orderedSet;
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OrderedMultiMap orderedMultiMap = (OrderedMultiMap) obj;
        if (size() != orderedMultiMap.size()) {
            return false;
        }
        return entrySet().equals(orderedMultiMap.entrySet());
    }

    @Override // java.util.Map
    public int hashCode() {
        return (this.keySet.hashCode() * 31) + this.valueSet.hashCode();
    }
}
