package com.vladsch.flexmark.util.collection;

import com.vladsch.flexmark.util.collection.iteration.BitSetIterable;
import com.vladsch.flexmark.util.collection.iteration.BitSetIterator;
import com.vladsch.flexmark.util.collection.iteration.Indexed;
import com.vladsch.flexmark.util.collection.iteration.IndexedIterable;
import com.vladsch.flexmark.util.collection.iteration.IndexedIterator;
import com.vladsch.flexmark.util.collection.iteration.ReversibleIndexedIterator;
import com.vladsch.flexmark.util.collection.iteration.ReversibleIterable;
import com.vladsch.flexmark.util.collection.iteration.ReversibleIterator;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/OrderedSet.class */
public class OrderedSet<E> implements Iterable<E>, Set<E> {
    private final HashMap<E, Integer> keyMap;
    final ArrayList<E> valueList;
    private final CollectionHost<E> host;
    private Indexed<E> indexedProxy;
    private Indexed<E> allowConcurrentModsIndexedProxy;
    private final BitSet validIndices;
    private int modificationCount;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !OrderedSet.class.desiredAssertionStatus();
    }

    public OrderedSet() {
        this(0);
    }

    public OrderedSet(int i) {
        this(i, null);
    }

    public OrderedSet(CollectionHost<E> collectionHost) {
        this(0, collectionHost);
    }

    public OrderedSet(int i, CollectionHost<E> collectionHost) {
        this.keyMap = new HashMap<>(i);
        this.valueList = new ArrayList<>(i);
        this.validIndices = new BitSet();
        this.host = collectionHost;
        this.modificationCount = Integer.MIN_VALUE;
        this.indexedProxy = null;
        this.allowConcurrentModsIndexedProxy = null;
    }

    public BitSet indexBitSet(Iterable<? extends E> iterable) {
        BitSet bitSet = new BitSet();
        Iterator<? extends E> it = iterable.iterator();
        while (it.hasNext()) {
            int indexOf = indexOf(it.next());
            if (indexOf != -1) {
                bitSet.set(indexOf);
            }
        }
        return bitSet;
    }

    public BitSet differenceBitSet(Iterable<? extends E> iterable) {
        return differenceBitSet(iterable.iterator());
    }

    public BitSet differenceBitSet(Iterator<? extends E> it) {
        BitSet bitSet = new BitSet();
        int i = 0;
        while (it.hasNext()) {
            int indexOf = indexOf(it.next());
            if (indexOf != i) {
                bitSet.set(indexOf);
            }
            i++;
        }
        return bitSet;
    }

    public BitSet keyDifferenceBitSet(Iterable<? extends Map.Entry<? extends E, ?>> iterable) {
        return keyDifferenceBitSet(iterable.iterator());
    }

    public BitSet keyDifferenceBitSet(Iterator<? extends Map.Entry<? extends E, ?>> it) {
        BitSet bitSet = new BitSet();
        int i = 0;
        while (it.hasNext()) {
            int indexOf = indexOf(it.next().getKey());
            if (indexOf != i) {
                bitSet.set(indexOf);
            }
            i++;
        }
        return bitSet;
    }

    public BitSet valueDifferenceBitSet(Iterable<? extends Map.Entry<?, ? extends E>> iterable) {
        return valueDifferenceBitSet(iterable.iterator());
    }

    public BitSet valueDifferenceBitSet(Iterator<? extends Map.Entry<?, ? extends E>> it) {
        BitSet bitSet = new BitSet();
        int i = 0;
        while (it.hasNext()) {
            int indexOf = indexOf(it.next().getValue());
            if (indexOf != i) {
                bitSet.set(indexOf);
            }
            i++;
        }
        return bitSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/OrderedSet$IndexedProxy.class */
    public class IndexedProxy implements Indexed<E> {
        private final boolean allowConcurrentMods;

        public IndexedProxy(boolean z) {
            this.allowConcurrentMods = z;
        }

        @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
        public E get(int i) {
            return (E) OrderedSet.this.getValue(i);
        }

        @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
        public void set(int i, E e) {
            OrderedSet.this.setValueAt(i, e, null);
        }

        @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
        public void removeAt(int i) {
            OrderedSet.this.removeIndexHosted(i);
        }

        @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
        public int size() {
            return OrderedSet.this.valueList.size();
        }

        @Override // com.vladsch.flexmark.util.collection.iteration.Indexed
        public int modificationCount() {
            if (this.allowConcurrentMods) {
                return 0;
            }
            return OrderedSet.this.getIteratorModificationCount();
        }
    }

    public Indexed<E> getIndexedProxy() {
        if (this.indexedProxy != null) {
            return this.indexedProxy;
        }
        this.indexedProxy = new IndexedProxy(false);
        return this.indexedProxy;
    }

    public Indexed<E> getConcurrentModsIndexedProxy() {
        if (this.allowConcurrentModsIndexedProxy != null) {
            return this.allowConcurrentModsIndexedProxy;
        }
        this.allowConcurrentModsIndexedProxy = new IndexedProxy(true);
        return this.allowConcurrentModsIndexedProxy;
    }

    public int getModificationCount() {
        return this.modificationCount;
    }

    int getIteratorModificationCount() {
        return this.host != null ? this.host.getIteratorModificationCount() : this.modificationCount;
    }

    public static <T1> T1 ifNull(T1 t1, T1 t12) {
        return t1 == null ? t12 : t1;
    }

    public boolean inHostUpdate() {
        return this.host != null && this.host.skipHostUpdate();
    }

    public int indexOf(Object obj) {
        return ((Integer) ifNull(this.keyMap.get(obj), -1)).intValue();
    }

    public boolean isValidIndex(int i) {
        return i >= 0 && i < this.valueList.size() && this.validIndices.get(i);
    }

    public void validateIndex(int i) {
        if (!isValidIndex(i)) {
            throw new IndexOutOfBoundsException("Index " + i + " is not valid, size=" + this.valueList.size() + " validIndices[" + i + "]=" + this.validIndices.get(i));
        }
    }

    public E getValue(int i) {
        validateIndex(i);
        return this.valueList.get(i);
    }

    public E getValueOrNull(int i) {
        if (isValidIndex(i)) {
            return this.valueList.get(i);
        }
        return null;
    }

    @Override // java.util.Set, java.util.Collection
    public int size() {
        return this.keyMap.size();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        return this.keyMap.isEmpty();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean contains(Object obj) {
        return this.keyMap.containsKey(obj);
    }

    public List<E> getValueList() {
        return this.valueList;
    }

    public List<E> values() {
        if (!isSparse()) {
            return this.valueList;
        }
        ArrayList arrayList = new ArrayList();
        ReversibleIterator<E> it = iterable().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public boolean setValueAt(int i, E e, Object obj) {
        int indexOf = indexOf(e);
        if (indexOf != -1) {
            if (i != indexOf) {
                throw new IllegalStateException("Trying to add existing element " + e + "[" + indexOf + "] at index " + i);
            }
            return false;
        }
        if (i < this.valueList.size()) {
            if (this.validIndices.get(i)) {
                throw new IllegalStateException("Trying to add new element " + e + " at index " + i + ", already occupied by " + this.valueList.get(i));
            }
        } else if (i > this.valueList.size()) {
            addNulls(i - 1);
        }
        if (this.host != null && !this.host.skipHostUpdate()) {
            this.host.adding(i, e, obj);
        }
        this.keyMap.put(e, Integer.valueOf(i));
        this.valueList.set(i, e);
        this.validIndices.set(i);
        return true;
    }

    public boolean isSparse() {
        return this.validIndices.nextClearBit(0) < this.valueList.size();
    }

    public void addNull() {
        addNulls(this.valueList.size());
    }

    public void addNulls(int i) {
        if (!$assertionsDisabled && i < this.valueList.size()) {
            throw new AssertionError();
        }
        if (this.host != null && !this.host.skipHostUpdate()) {
            this.host.addingNulls(i);
        }
        this.modificationCount++;
        while (this.valueList.size() <= i) {
            this.valueList.add(null);
        }
    }

    public ReversibleIterator<Integer> indexIterator() {
        return new BitSetIterator(this.validIndices);
    }

    public ReversibleIterator<Integer> reversedIndexIterator() {
        return new BitSetIterator(this.validIndices, true);
    }

    public ReversibleIterable<Integer> indexIterable() {
        return new BitSetIterable(this.validIndices);
    }

    public ReversibleIterable<Integer> reversedIndexIterable() {
        return new BitSetIterable(this.validIndices, true);
    }

    @Override // java.lang.Iterable, java.util.Set, java.util.Collection
    public ReversibleIndexedIterator<E> iterator() {
        return new IndexedIterator(getIndexedProxy(), indexIterator());
    }

    public ReversibleIndexedIterator<E> reversedIterator() {
        return new IndexedIterator(getIndexedProxy(), reversedIndexIterator());
    }

    public ReversibleIterable<E> iterable() {
        return new IndexedIterable(getIndexedProxy(), indexIterable());
    }

    public ReversibleIterable<E> reversedIterable() {
        return new IndexedIterable(getIndexedProxy(), reversedIndexIterable());
    }

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray() {
        Object[] objArr = new Object[this.keyMap.size()];
        int i = -1;
        int i2 = -1;
        while (i + 1 < this.valueList.size()) {
            i++;
            if (this.validIndices.get(i)) {
                i2++;
                objArr[i2] = this.valueList.get(i);
            }
        }
        return objArr;
    }

    @Override // java.util.Set, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        Object[] objArr = tArr;
        if (tArr.length < this.keyMap.size()) {
            objArr = tArr.getClass() == Object[].class ? new Object[this.keyMap.size()] : (Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.keyMap.size());
        }
        int i = -1;
        int i2 = -1;
        while (i + 1 < this.valueList.size()) {
            i++;
            if (this.validIndices.get(i)) {
                i2++;
                objArr[i2] = this.valueList.get(i);
            }
        }
        int i3 = i2 + 1;
        if (objArr.length > i3) {
            objArr[i3] = null;
        }
        return objArr;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean add(E e) {
        return add(e, null);
    }

    public boolean add(E e, Object obj) {
        if (this.keyMap.containsKey(e)) {
            return false;
        }
        int size = this.valueList.size();
        if (this.host != null && !this.host.skipHostUpdate()) {
            this.host.adding(size, e, obj);
        }
        this.modificationCount++;
        this.keyMap.put(e, Integer.valueOf(size));
        this.valueList.add(e);
        this.validIndices.set(size);
        return true;
    }

    public boolean removeIndex(int i) {
        return removeIndexHosted(i) != null;
    }

    public Object removeIndexHosted(int i) {
        Object obj;
        validateIndex(i);
        E e = this.valueList.get(i);
        if (this.host != null && !this.host.skipHostUpdate()) {
            obj = this.host.removing(i, e);
        } else {
            obj = e;
        }
        this.modificationCount++;
        this.keyMap.remove(e);
        if (this.keyMap.size() == 0) {
            if (this.host != null && !this.host.skipHostUpdate()) {
                this.host.clearing();
            }
            this.valueList.clear();
            this.validIndices.clear();
        } else {
            if (this.host == null && i == this.valueList.size() - 1) {
                this.valueList.remove(i);
            }
            this.validIndices.clear(i);
        }
        return obj;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean remove(Object obj) {
        return removeHosted(obj) != null;
    }

    public Object removeHosted(Object obj) {
        Integer num = this.keyMap.get(obj);
        if (num == null) {
            return null;
        }
        return removeIndexHosted(num.intValue());
    }

    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!this.keyMap.containsKey(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        boolean[] zArr = {false};
        Iterator<? extends E> it = collection.iterator();
        while (it.hasNext()) {
            if (add(it.next())) {
                zArr[0] = true;
            }
        }
        return zArr[0];
    }

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        boolean z;
        BitSet bitSet = new BitSet(this.valueList.size());
        bitSet.set(0, this.valueList.size());
        bitSet.and(this.validIndices);
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            int indexOf = indexOf(it.next());
            if (indexOf != -1) {
                bitSet.clear(indexOf);
            }
        }
        int size = this.valueList.size();
        int i = size;
        if (size == 0) {
            return false;
        }
        boolean z2 = false;
        while (true) {
            z = z2;
            int i2 = i;
            int i3 = i - 1;
            if (i2 <= 0) {
                break;
            }
            int previousSetBit = bitSet.previousSetBit(i3);
            i = previousSetBit;
            if (previousSetBit == -1) {
                break;
            }
            remove(this.valueList.get(i));
            z2 = true;
        }
        return z;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        boolean z = false;
        for (Object obj : collection) {
            if (this.keyMap.containsKey(obj) && remove(obj)) {
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        if (this.host != null && !this.host.skipHostUpdate()) {
            this.host.clearing();
        }
        this.modificationCount++;
        this.keyMap.clear();
        this.valueList.clear();
        this.validIndices.clear();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OrderedSet orderedSet = (OrderedSet) obj;
        if (size() != orderedSet.size()) {
            return false;
        }
        ReversibleIndexedIterator<E> it = orderedSet.iterator();
        ReversibleIndexedIterator<E> it2 = iterator();
        while (it2.hasNext()) {
            if (!it2.next().equals(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public int hashCode() {
        return (((this.keyMap.hashCode() * 31) + this.valueList.hashCode()) * 31) + this.validIndices.hashCode();
    }

    public BitSet getValidIndices() {
        return this.validIndices;
    }
}
