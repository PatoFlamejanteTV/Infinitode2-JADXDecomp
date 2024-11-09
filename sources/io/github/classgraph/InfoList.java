package io.github.classgraph;

import io.github.classgraph.HasName;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* loaded from: infinitode-2.jar:io/github/classgraph/InfoList.class */
public class InfoList<T extends HasName> extends PotentiallyUnmodifiableList<T> {
    static final long serialVersionUID = 1;

    @Override // io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ ListIterator listIterator() {
        return super.listIterator();
    }

    @Override // io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public /* bridge */ /* synthetic */ Iterator iterator() {
        return super.iterator();
    }

    @Override // io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    @Override // io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    @Override // io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ boolean addAll(int i, Collection collection) {
        return super.addAll(i, collection);
    }

    @Override // io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean addAll(Collection collection) {
        return super.addAll(collection);
    }

    @Override // io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InfoList() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InfoList(int i) {
        super(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InfoList(Collection<T> collection) {
        super(collection);
    }

    @Override // io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        return super.hashCode();
    }

    public List<String> getNames() {
        if (isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(size());
        Iterator it = iterator();
        while (it.hasNext()) {
            HasName hasName = (HasName) it.next();
            if (hasName != null) {
                arrayList.add(hasName.getName());
            }
        }
        return arrayList;
    }

    public List<String> getAsStrings() {
        if (isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(size());
        Iterator it = iterator();
        while (it.hasNext()) {
            HasName hasName = (HasName) it.next();
            arrayList.add(hasName == null ? "null" : hasName.toString());
        }
        return arrayList;
    }

    public List<String> getAsStringsWithSimpleNames() {
        String obj;
        if (isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(size());
        Iterator it = iterator();
        while (it.hasNext()) {
            Object obj2 = (HasName) it.next();
            if (obj2 == null) {
                obj = "null";
            } else if (obj2 instanceof ScanResultObject) {
                obj = ((ScanResultObject) obj2).toStringWithSimpleNames();
            } else {
                obj = obj2.toString();
            }
            arrayList.add(obj);
        }
        return arrayList;
    }
}
