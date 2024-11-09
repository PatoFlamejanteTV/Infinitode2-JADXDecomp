package com.vladsch.flexmark.util.collection;

import java.util.BitSet;
import java.util.function.Function;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/IndexedItemBitSetMap.class */
public class IndexedItemBitSetMap<K, M> extends IndexedItemSetMapBase<K, BitSet, M> {
    private final Function<M, K> computable;

    public IndexedItemBitSetMap(Function<M, K> function) {
        this(function, 0);
    }

    public IndexedItemBitSetMap(Function<M, K> function, int i) {
        super(i);
        this.computable = function;
    }

    public Function<M, K> getComputable() {
        return this.computable;
    }

    @Override // com.vladsch.flexmark.util.collection.IndexedItemSetMapBase, com.vladsch.flexmark.util.collection.IndexedItemSetMap
    public K mapKey(M m) {
        return this.computable.apply(m);
    }

    @Override // com.vladsch.flexmark.util.collection.IndexedItemSetMapBase, com.vladsch.flexmark.util.collection.IndexedItemSetMap
    public BitSet newSet() {
        return new BitSet();
    }

    @Override // com.vladsch.flexmark.util.collection.IndexedItemSetMapBase, com.vladsch.flexmark.util.collection.IndexedItemSetMap
    public boolean addSetItem(BitSet bitSet, int i) {
        boolean z = bitSet.get(i);
        bitSet.set(i);
        return z;
    }

    @Override // com.vladsch.flexmark.util.collection.IndexedItemSetMapBase, com.vladsch.flexmark.util.collection.IndexedItemSetMap
    public boolean removeSetItem(BitSet bitSet, int i) {
        boolean z = bitSet.get(i);
        bitSet.clear(i);
        return z;
    }

    @Override // com.vladsch.flexmark.util.collection.IndexedItemSetMapBase, com.vladsch.flexmark.util.collection.IndexedItemSetMap
    public boolean containsSetItem(BitSet bitSet, int i) {
        return bitSet.get(i);
    }
}
