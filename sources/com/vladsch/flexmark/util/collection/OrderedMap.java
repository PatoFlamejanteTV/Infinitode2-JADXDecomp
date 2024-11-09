package com.vladsch.flexmark.util.collection;

import com.vladsch.flexmark.util.collection.iteration.Indexed;
import com.vladsch.flexmark.util.collection.iteration.IndexedIterable;
import com.vladsch.flexmark.util.collection.iteration.IndexedIterator;
import com.vladsch.flexmark.util.collection.iteration.ReversibleIndexedIterator;
import com.vladsch.flexmark.util.collection.iteration.ReversibleIterable;
import com.vladsch.flexmark.util.collection.iteration.ReversibleIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/OrderedMap.class */
public class OrderedMap<K, V> implements Iterable<Map.Entry<K, V>>, Map<K, V> {
    final OrderedSet<K> keySet;
    private final ArrayList<V> valueList;
    private final CollectionHost<K> host;
    boolean inUpdate;
    private Indexed<Map.Entry<K, V>> indexedEntryProxy;
    private Indexed<V> indexedValueProxy;

    public OrderedMap() {
        this(0, null);
    }

    public OrderedMap(int i) {
        this(i, null);
    }

    public OrderedMap(CollectionHost<K> collectionHost) {
        this(0, collectionHost);
    }

    public OrderedMap(int i, CollectionHost<K> collectionHost) {
        this.valueList = new ArrayList<>(i);
        this.host = collectionHost;
        this.indexedEntryProxy = null;
        this.indexedValueProxy = null;
        this.keySet = new OrderedSet<>(i, new CollectionHost<K>() { // from class: com.vladsch.flexmark.util.collection.OrderedMap.1
            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public void adding(int i2, K k, Object obj) {
                OrderedMap.this.adding(i2, k, obj);
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public Object removing(int i2, K k) {
                return OrderedMap.this.removing(i2, k);
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public void clearing() {
                OrderedMap.this.clearing();
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public void addingNulls(int i2) {
                OrderedMap.this.addingNull(i2);
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public boolean skipHostUpdate() {
                return OrderedMap.this.inUpdate;
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public int getIteratorModificationCount() {
                return OrderedMap.this.getModificationCount();
            }
        });
    }

    public Indexed<Map.Entry<K, V>> getIndexedEntryProxy() {
        if (this.indexedEntryProxy != null) {
            return this.indexedEntryProxy;
        }
        this.indexedEntryProxy = new Indexed<Map.Entry<K, V>>() { // from class: com.vladsch.flexmark.util.collection.OrderedMap.2
            @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
            public Map.Entry<K, V> get(int i) {
                return OrderedMap.this.getEntry(i);
            }

            @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
            public void set(int i, Map.Entry<K, V> entry) {
                throw new UnsupportedOperationException();
            }

            @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
            public void removeAt(int i) {
                OrderedMap.this.keySet.removeIndexHosted(i);
            }

            @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
            public int size() {
                return OrderedMap.this.size();
            }

            @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
            public int modificationCount() {
                return OrderedMap.this.getModificationCount();
            }
        };
        return this.indexedEntryProxy;
    }

    public Indexed<V> getIndexedValueProxy() {
        if (this.indexedValueProxy != null) {
            return this.indexedValueProxy;
        }
        this.indexedValueProxy = new Indexed<V>() { // from class: com.vladsch.flexmark.util.collection.OrderedMap.3
            @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
            public V get(int i) {
                return (V) OrderedMap.this.getValue(i);
            }

            @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
            public void set(int i, V v) {
                throw new UnsupportedOperationException();
            }

            @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
            public void removeAt(int i) {
                OrderedMap.this.keySet.removeIndexHosted(i);
            }

            @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
            public int size() {
                return OrderedMap.this.size();
            }

            @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
            public int modificationCount() {
                return OrderedMap.this.getModificationCount();
            }
        };
        return this.indexedValueProxy;
    }

    Map.Entry<K, V> getEntry(int i) {
        return new MapEntry(this.keySet.getValue(i), this.valueList.get(i));
    }

    public int getModificationCount() {
        return this.keySet.getModificationCount();
    }

    void adding(int i, K k, Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException();
        }
        if (this.host != null && !this.host.skipHostUpdate()) {
            this.host.adding(i, k, obj);
        }
        this.valueList.add(obj);
    }

    void addingNull(int i) {
        if (this.host != null && !this.host.skipHostUpdate()) {
            this.host.addingNulls(i);
        }
        addNulls(i);
    }

    Object removing(int i, K k) {
        if (this.host != null && !this.host.skipHostUpdate()) {
            this.host.removing(i, k);
        }
        return this.valueList.get(i);
    }

    void clearing() {
        if (this.host != null && !this.host.skipHostUpdate()) {
            this.host.clearing();
        }
        this.valueList.clear();
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
        return this.keySet.isValidIndex(this.valueList.indexOf(obj));
    }

    public void addNull() {
        addNulls(this.valueList.size());
    }

    public void addNulls(int i) {
        if (i < this.valueList.size()) {
            throw new IllegalArgumentException("addNulls(" + i + ") called when valueList size is " + this.valueList.size());
        }
        while (this.valueList.size() <= i) {
            this.valueList.add(null);
        }
    }

    @Override // java.util.Map
    public V get(Object obj) {
        int indexOf = this.keySet.indexOf(obj);
        if (indexOf == -1) {
            return null;
        }
        return this.valueList.get(indexOf);
    }

    @Override // java.util.Map
    public V put(K k, V v) {
        int indexOf = this.keySet.indexOf(k);
        if (indexOf != -1) {
            V v2 = this.valueList.get(indexOf);
            this.valueList.set(indexOf, v);
            return v2;
        }
        this.keySet.add(k, v);
        return null;
    }

    public V computeIfMissing(K k, Function<? super K, ? extends V> function) {
        int indexOf = this.keySet.indexOf(k);
        if (indexOf != -1) {
            return this.valueList.get(indexOf);
        }
        V apply = function.apply(k);
        this.keySet.add(k, apply);
        return apply;
    }

    @Override // java.util.Map
    public V remove(Object obj) {
        return (V) this.keySet.removeHosted(obj);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public void addAll(Collection<? extends Map.Entry<? extends K, ? extends V>> collection) {
        for (Map.Entry<? extends K, ? extends V> entry : collection) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public void clear() {
        this.keySet.clear();
    }

    @Override // java.util.Map
    public OrderedSet<K> keySet() {
        return this.keySet;
    }

    @Override // java.util.Map
    public Collection<V> values() {
        if (!this.keySet.isSparse()) {
            return this.valueList;
        }
        ArrayList arrayList = new ArrayList(this.keySet.size());
        ReversibleIterator<Integer> indexIterator = this.keySet.indexIterator();
        while (indexIterator.hasNext()) {
            arrayList.add(this.valueList.get(indexIterator.next().intValue()));
        }
        return arrayList;
    }

    public K getKey(int i) {
        if (this.keySet.isValidIndex(i)) {
            return this.keySet.getValueList().get(i);
        }
        return null;
    }

    public V getValue(int i) {
        if (this.keySet.isValidIndex(i)) {
            return this.valueList.get(i);
        }
        return null;
    }

    @Override // java.util.Map
    public OrderedSet<Map.Entry<K, V>> entrySet() {
        this.inUpdate = true;
        OrderedSet<Map.Entry<K, V>> orderedSet = new OrderedSet<>(this.keySet.size(), new EntryCollectionHost());
        ReversibleIndexedIterator<Map.Entry<K, V>> entryIterator = entryIterator();
        while (entryIterator.hasNext()) {
            orderedSet.add(entryIterator.next());
        }
        this.inUpdate = false;
        return orderedSet;
    }

    public List<Map.Entry<K, V>> entries() {
        ArrayList arrayList = new ArrayList();
        ReversibleIndexedIterator<Map.Entry<K, V>> entryIterator = entryIterator();
        while (entryIterator.hasNext()) {
            arrayList.add(entryIterator.next());
        }
        return arrayList;
    }

    public List<K> keys() {
        return this.keySet.values();
    }

    public ReversibleIndexedIterator<V> valueIterator() {
        return new IndexedIterator(getIndexedValueProxy(), this.keySet.indexIterator());
    }

    public ReversibleIndexedIterator<V> reversedValueIterator() {
        return new IndexedIterator(getIndexedValueProxy(), this.keySet.reversedIndexIterator());
    }

    public ReversibleIndexedIterator<K> keyIterator() {
        return this.keySet.iterator();
    }

    public ReversibleIndexedIterator<K> reversedKeyIterator() {
        return this.keySet.reversedIterator();
    }

    public ReversibleIndexedIterator<Map.Entry<K, V>> entryIterator() {
        return new IndexedIterator(getIndexedEntryProxy(), this.keySet.indexIterator());
    }

    public ReversibleIndexedIterator<Map.Entry<K, V>> reversedEntryIterator() {
        return new IndexedIterator(getIndexedEntryProxy(), this.keySet.reversedIndexIterator());
    }

    public ReversibleIndexedIterator<Map.Entry<K, V>> reversedIterator() {
        return reversedEntryIterator();
    }

    public ReversibleIterable<V> valueIterable() {
        return new IndexedIterable(getIndexedValueProxy(), this.keySet.indexIterable());
    }

    public ReversibleIterable<V> reversedValueIterable() {
        return new IndexedIterable(getIndexedValueProxy(), this.keySet.reversedIndexIterable());
    }

    public ReversibleIterable<K> keyIterable() {
        return this.keySet.iterable();
    }

    public ReversibleIterable<K> reversedKeyIterable() {
        return this.keySet.reversedIterable();
    }

    public ReversibleIterable<Map.Entry<K, V>> entryIterable() {
        return new IndexedIterable(getIndexedEntryProxy(), this.keySet.indexIterable());
    }

    public ReversibleIterable<Map.Entry<K, V>> reversedEntryIterable() {
        return new IndexedIterable(getIndexedEntryProxy(), this.keySet.reversedIndexIterable());
    }

    public ReversibleIterable<Map.Entry<K, V>> reversedIterable() {
        return reversedEntryIterable();
    }

    @Override // java.lang.Iterable
    public ReversibleIndexedIterator<Map.Entry<K, V>> iterator() {
        return entryIterator();
    }

    @Override // java.lang.Iterable
    public void forEach(Consumer<? super Map.Entry<K, V>> consumer) {
        ReversibleIndexedIterator<Map.Entry<K, V>> it = iterator();
        while (it.hasNext()) {
            consumer.accept(it.next());
        }
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OrderedMap orderedMap = (OrderedMap) obj;
        if (size() != orderedMap.size()) {
            return false;
        }
        return entrySet().equals(orderedMap.entrySet());
    }

    @Override // java.util.Map
    public int hashCode() {
        return (this.keySet.hashCode() * 31) + this.valueList.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/OrderedMap$EntryCollectionHost.class */
    public class EntryCollectionHost<KK extends K, VV extends V> implements CollectionHost<Map.Entry<KK, VV>> {
        static final /* synthetic */ boolean $assertionsDisabled;

        private EntryCollectionHost() {
        }

        static {
            $assertionsDisabled = !OrderedMap.class.desiredAssertionStatus();
        }

        @Override // com.vladsch.flexmark.util.collection.CollectionHost
        public void adding(int i, Map.Entry<KK, VV> entry, Object obj) {
            if (!$assertionsDisabled && obj != null) {
                throw new AssertionError();
            }
            OrderedMap.this.keySet.add(entry.getKey(), entry.getValue());
        }

        @Override // com.vladsch.flexmark.util.collection.CollectionHost
        public Object removing(int i, Map.Entry<KK, VV> entry) {
            OrderedMap.this.keySet.removeIndex(i);
            return entry;
        }

        @Override // com.vladsch.flexmark.util.collection.CollectionHost
        public void clearing() {
            OrderedMap.this.keySet.clear();
        }

        @Override // com.vladsch.flexmark.util.collection.CollectionHost
        public void addingNulls(int i) {
            OrderedMap.this.keySet.addNulls(i);
        }

        @Override // com.vladsch.flexmark.util.collection.CollectionHost
        public boolean skipHostUpdate() {
            return OrderedMap.this.inUpdate;
        }

        @Override // com.vladsch.flexmark.util.collection.CollectionHost
        public int getIteratorModificationCount() {
            return OrderedMap.this.getModificationCount();
        }
    }
}
