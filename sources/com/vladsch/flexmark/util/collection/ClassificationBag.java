package com.vladsch.flexmark.util.collection;

import com.vladsch.flexmark.util.collection.iteration.BitSetIterable;
import com.vladsch.flexmark.util.collection.iteration.IndexedIterable;
import com.vladsch.flexmark.util.collection.iteration.ReversibleIterable;
import java.util.BitSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/ClassificationBag.class */
public class ClassificationBag<K, V> {
    private final OrderedSet<V> items;
    final IndexedItemBitSetMap<K, V> bag;
    final CollectionHost<V> host;

    public ClassificationBag(Function<V, K> function) {
        this(0, function);
    }

    public ClassificationBag(Function<V, K> function, CollectionHost<V> collectionHost) {
        this(0, function, collectionHost);
    }

    public ClassificationBag(int i, Function<V, K> function) {
        this(i, function, null);
    }

    public ClassificationBag(int i, Function<V, K> function, CollectionHost<V> collectionHost) {
        this.host = collectionHost;
        this.items = new OrderedSet<>(i, new CollectionHost<V>() { // from class: com.vladsch.flexmark.util.collection.ClassificationBag.1
            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public void adding(int i2, V v, Object obj) {
                if (ClassificationBag.this.host != null && !ClassificationBag.this.host.skipHostUpdate()) {
                    ClassificationBag.this.host.adding(i2, v, obj);
                }
                if (v != null) {
                    ClassificationBag.this.bag.addItem(v, i2);
                }
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public Object removing(int i2, V v) {
                if (ClassificationBag.this.host != null && !ClassificationBag.this.host.skipHostUpdate()) {
                    ClassificationBag.this.host.removing(i2, v);
                }
                if (v != null) {
                    ClassificationBag.this.bag.removeItem(v, i2);
                    return null;
                }
                return null;
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public void clearing() {
                if (ClassificationBag.this.host != null && !ClassificationBag.this.host.skipHostUpdate()) {
                    ClassificationBag.this.host.clearing();
                }
                ClassificationBag.this.bag.clear();
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public void addingNulls(int i2) {
                if (ClassificationBag.this.host == null || ClassificationBag.this.host.skipHostUpdate()) {
                    return;
                }
                ClassificationBag.this.host.addingNulls(i2);
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public boolean skipHostUpdate() {
                return false;
            }

            @Override // com.vladsch.flexmark.util.collection.CollectionHost
            public int getIteratorModificationCount() {
                return ClassificationBag.this.getModificationCount();
            }
        });
        this.bag = new IndexedItemBitSetMap<>(function);
    }

    public OrderedSet<V> getItems() {
        return this.items;
    }

    public int getModificationCount() {
        return this.items.getModificationCount();
    }

    public boolean add(V v) {
        return this.items.add(v);
    }

    public boolean remove(V v) {
        return this.items.remove(v);
    }

    public boolean remove(int i) {
        return this.items.removeIndex(i);
    }

    public boolean contains(V v) {
        return this.items.contains(v);
    }

    public boolean containsCategory(K k) {
        BitSet bitSet = this.bag.get(k);
        return (bitSet == null || bitSet.isEmpty()) ? false : true;
    }

    public BitSet getCategorySet(K k) {
        return this.bag.get(k);
    }

    public int getCategoryCount(K k) {
        BitSet bitSet = this.bag.get(k);
        if (bitSet == null) {
            return 0;
        }
        return bitSet.cardinality();
    }

    public Map<K, BitSet> getCategoryMap() {
        return this.bag;
    }

    public void clear() {
        this.items.clear();
    }

    @SafeVarargs
    public final <X> ReversibleIterable<X> getCategoryItems(Class<? extends X> cls, K... kArr) {
        return new IndexedIterable(this.items.getConcurrentModsIndexedProxy(), new BitSetIterable(categoriesBitSet(kArr), false));
    }

    public final <X> ReversibleIterable<X> getCategoryItems(Class<? extends X> cls, Collection<? extends K> collection) {
        return new IndexedIterable(this.items.getConcurrentModsIndexedProxy(), new BitSetIterable(categoriesBitSet(collection), false));
    }

    public final <X> ReversibleIterable<X> getCategoryItems(Class<? extends X> cls, BitSet bitSet) {
        return new IndexedIterable(this.items.getConcurrentModsIndexedProxy(), new BitSetIterable(bitSet, false));
    }

    @SafeVarargs
    public final <X> ReversibleIterable<X> getCategoryItemsReversed(Class<? extends X> cls, K... kArr) {
        return new IndexedIterable(this.items.getConcurrentModsIndexedProxy(), new BitSetIterable(categoriesBitSet(kArr), true));
    }

    public final <X> ReversibleIterable<X> getCategoryItemsReversed(Class<? extends X> cls, Collection<? extends K> collection) {
        return new IndexedIterable(this.items.getConcurrentModsIndexedProxy(), new BitSetIterable(categoriesBitSet(collection), true));
    }

    public final <X> ReversibleIterable<X> getCategoryItemsReversed(Class<? extends X> cls, BitSet bitSet) {
        return new IndexedIterable(this.items.getConcurrentModsIndexedProxy(), new BitSetIterable(bitSet, true));
    }

    @SafeVarargs
    public final BitSet categoriesBitSet(K... kArr) {
        BitSet bitSet = new BitSet();
        for (K k : kArr) {
            BitSet bitSet2 = this.bag.get(k);
            if (bitSet2 != null) {
                bitSet.or(bitSet2);
            }
        }
        return bitSet;
    }

    public final BitSet categoriesBitSet(Collection<? extends K> collection) {
        BitSet bitSet = new BitSet();
        Iterator<? extends K> it = collection.iterator();
        while (it.hasNext()) {
            BitSet bitSet2 = this.bag.get(it.next());
            if (bitSet2 != null) {
                bitSet.or(bitSet2);
            }
        }
        return bitSet;
    }
}
