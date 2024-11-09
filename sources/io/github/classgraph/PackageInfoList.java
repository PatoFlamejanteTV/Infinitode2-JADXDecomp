package io.github.classgraph;

import java.util.Collection;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:io/github/classgraph/PackageInfoList.class */
public class PackageInfoList extends MappableInfoList<PackageInfo> {
    private static final long serialVersionUID = 1;
    static final PackageInfoList EMPTY_LIST = new PackageInfoList() { // from class: io.github.classgraph.PackageInfoList.1
        private static final long serialVersionUID = 1;

        @Override // io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public final boolean add(PackageInfo packageInfo) {
            throw new IllegalArgumentException("List is immutable");
        }

        @Override // io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractList, java.util.List
        public final void add(int i, PackageInfo packageInfo) {
            throw new IllegalArgumentException("List is immutable");
        }

        @Override // io.github.classgraph.InfoList, io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public final boolean remove(Object obj) {
            throw new IllegalArgumentException("List is immutable");
        }

        @Override // io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractList, java.util.List
        public final PackageInfo remove(int i) {
            throw new IllegalArgumentException("List is immutable");
        }

        @Override // io.github.classgraph.InfoList, io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public final boolean addAll(Collection<? extends PackageInfo> collection) {
            throw new IllegalArgumentException("List is immutable");
        }

        @Override // io.github.classgraph.InfoList, io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractList, java.util.List
        public final boolean addAll(int i, Collection<? extends PackageInfo> collection) {
            throw new IllegalArgumentException("List is immutable");
        }

        @Override // io.github.classgraph.InfoList, io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public final boolean removeAll(Collection<?> collection) {
            throw new IllegalArgumentException("List is immutable");
        }

        @Override // io.github.classgraph.InfoList, io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public final boolean retainAll(Collection<?> collection) {
            throw new IllegalArgumentException("List is immutable");
        }

        @Override // io.github.classgraph.InfoList, io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public final void clear() {
            throw new IllegalArgumentException("List is immutable");
        }

        @Override // io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractList, java.util.List
        public final PackageInfo set(int i, PackageInfo packageInfo) {
            throw new IllegalArgumentException("List is immutable");
        }
    };

    @FunctionalInterface
    /* loaded from: infinitode-2.jar:io/github/classgraph/PackageInfoList$PackageInfoFilter.class */
    public interface PackageInfoFilter {
        boolean accept(PackageInfo packageInfo);
    }

    PackageInfoList() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PackageInfoList(int i) {
        super(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PackageInfoList(Collection<PackageInfo> collection) {
        super(collection);
    }

    public PackageInfoList filter(PackageInfoFilter packageInfoFilter) {
        PackageInfoList packageInfoList = new PackageInfoList();
        Iterator it = iterator();
        while (it.hasNext()) {
            PackageInfo packageInfo = (PackageInfo) it.next();
            if (packageInfoFilter.accept(packageInfo)) {
                packageInfoList.add(packageInfo);
            }
        }
        return packageInfoList;
    }
}
