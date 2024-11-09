package com.vladsch.flexmark.util.format;

import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.builder.Seg;
import com.vladsch.flexmark.util.sequence.builder.tree.BasedOffsetTracker;
import com.vladsch.flexmark.util.sequence.builder.tree.OffsetInfo;
import com.vladsch.flexmark.util.sequence.builder.tree.SegmentOffsetTree;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.UnaryOperator;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/TrackedOffsetList.class */
public class TrackedOffsetList implements List<TrackedOffset> {
    public static TrackedOffsetList EMPTY_LIST;
    private final BasedSequence myBaseSeq;
    private final List<TrackedOffset> myTrackedOffsets;
    private final BasedOffsetTracker myBasedOffsetTracker;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !TrackedOffsetList.class.desiredAssertionStatus();
        EMPTY_LIST = new TrackedOffsetList(BasedSequence.NULL, Collections.emptyList());
    }

    public static TrackedOffsetList create(BasedSequence basedSequence, List<TrackedOffset> list) {
        return list instanceof TrackedOffsetList ? (TrackedOffsetList) list : new TrackedOffsetList(basedSequence, list);
    }

    public static TrackedOffsetList create(BasedSequence basedSequence, int[] iArr) {
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i : iArr) {
            arrayList.add(TrackedOffset.track(i));
        }
        return new TrackedOffsetList(basedSequence, arrayList);
    }

    private TrackedOffsetList(BasedSequence basedSequence, List<TrackedOffset> list) {
        this.myBaseSeq = basedSequence;
        this.myTrackedOffsets = new ArrayList(list);
        this.myTrackedOffsets.sort(Comparator.comparing((v0) -> {
            return v0.getOffset();
        }));
        ArrayList arrayList = new ArrayList(list.size());
        for (TrackedOffset trackedOffset : this.myTrackedOffsets) {
            arrayList.add(Seg.segOf(trackedOffset.getOffset(), trackedOffset.getOffset() + 1));
        }
        this.myBasedOffsetTracker = BasedOffsetTracker.create(basedSequence, SegmentOffsetTree.build((Iterable<Seg>) arrayList, (CharSequence) ""));
        if (!$assertionsDisabled && this.myBasedOffsetTracker.size() != this.myTrackedOffsets.size()) {
            throw new AssertionError();
        }
    }

    public TrackedOffsetList getUnresolvedOffsets() {
        ArrayList arrayList = new ArrayList();
        for (TrackedOffset trackedOffset : this.myTrackedOffsets) {
            if (!trackedOffset.isResolved()) {
                arrayList.add(trackedOffset);
            }
        }
        return arrayList.isEmpty() ? EMPTY_LIST : new TrackedOffsetList(this.myBaseSeq, arrayList);
    }

    public boolean haveUnresolved() {
        Iterator<TrackedOffset> it = this.myTrackedOffsets.iterator();
        while (it.hasNext()) {
            if (!it.next().isResolved()) {
                return true;
            }
        }
        return false;
    }

    public BasedSequence getBaseSeq() {
        return this.myBaseSeq;
    }

    public List<TrackedOffset> getTrackedOffsets() {
        return this.myTrackedOffsets;
    }

    public BasedOffsetTracker getBasedOffsetTracker() {
        return this.myBasedOffsetTracker;
    }

    public TrackedOffsetList getTrackedOffsets(int i, int i2) {
        int i3;
        OffsetInfo offsetInfo = this.myBasedOffsetTracker.getOffsetInfo(i, i == i2);
        OffsetInfo offsetInfo2 = this.myBasedOffsetTracker.getOffsetInfo(i2, true);
        int i4 = offsetInfo.pos;
        int i5 = offsetInfo2.pos;
        if (i4 < 0 && i5 >= 0) {
            i4 = 0;
            i3 = i5 + 1;
        } else if (i4 >= 0 && i5 >= 0) {
            i3 = i5 + 1;
        } else {
            return EMPTY_LIST;
        }
        int min = Math.min(this.myBasedOffsetTracker.size(), i3);
        if (i4 >= min) {
            return EMPTY_LIST;
        }
        if (this.myTrackedOffsets.get(i4).getOffset() < i) {
            i4++;
        }
        if (this.myTrackedOffsets.get(min - 1).getOffset() > i2) {
            min--;
        }
        return i4 >= min ? EMPTY_LIST : new TrackedOffsetList(this.myBaseSeq, this.myTrackedOffsets.subList(i4, min));
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(TrackedOffset trackedOffset) {
        throw new IllegalStateException("Not supported. Immutable list.");
    }

    @Override // java.util.List
    public TrackedOffset set(int i, TrackedOffset trackedOffset) {
        throw new IllegalStateException("Not supported. Immutable list.");
    }

    @Override // java.util.List
    public void add(int i, TrackedOffset trackedOffset) {
        throw new IllegalStateException("Not supported. Immutable list.");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.List
    public TrackedOffset remove(int i) {
        throw new IllegalStateException("Not supported. Immutable list.");
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends TrackedOffset> collection) {
        throw new IllegalStateException("Not supported. Immutable list.");
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends TrackedOffset> collection) {
        throw new IllegalStateException("Not supported. Immutable list.");
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        throw new IllegalStateException("Not supported. Immutable list.");
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        throw new IllegalStateException("Not supported. Immutable list.");
    }

    @Override // java.util.List
    public void replaceAll(UnaryOperator<TrackedOffset> unaryOperator) {
        throw new IllegalStateException("Not supported. Immutable list.");
    }

    @Override // java.util.List
    public void sort(Comparator<? super TrackedOffset> comparator) {
        throw new IllegalStateException("Not supported. Immutable list.");
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        throw new IllegalStateException("Not supported. Immutable list.");
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        throw new IllegalStateException("Not supported. Immutable list.");
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        return this.myTrackedOffsets.size();
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.myTrackedOffsets.isEmpty();
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        return this.myTrackedOffsets.contains(obj);
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<TrackedOffset> iterator() {
        return this.myTrackedOffsets.iterator();
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return this.myTrackedOffsets.toArray();
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) this.myTrackedOffsets.toArray(tArr);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        return this.myTrackedOffsets.containsAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object obj) {
        return this.myTrackedOffsets.equals(obj);
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        return this.myTrackedOffsets.hashCode();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.List
    public TrackedOffset get(int i) {
        return this.myTrackedOffsets.get(i);
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        return this.myTrackedOffsets.indexOf(obj);
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        return this.myTrackedOffsets.lastIndexOf(obj);
    }

    @Override // java.util.List
    public ListIterator<TrackedOffset> listIterator() {
        return this.myTrackedOffsets.listIterator();
    }

    @Override // java.util.List
    public ListIterator<TrackedOffset> listIterator(int i) {
        return this.myTrackedOffsets.listIterator(i);
    }

    @Override // java.util.List
    public List<TrackedOffset> subList(int i, int i2) {
        return this.myTrackedOffsets.subList(i, i2);
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Spliterator<TrackedOffset> spliterator() {
        return this.myTrackedOffsets.spliterator();
    }
}
