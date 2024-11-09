package io.github.classgraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

/* loaded from: infinitode-2.jar:io/github/classgraph/PotentiallyUnmodifiableList.class */
class PotentiallyUnmodifiableList<T> extends ArrayList<T> {
    static final long serialVersionUID = 1;
    boolean modifiable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PotentiallyUnmodifiableList() {
        this.modifiable = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PotentiallyUnmodifiableList(int i) {
        super(i);
        this.modifiable = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PotentiallyUnmodifiableList(Collection<T> collection) {
        super(collection);
        this.modifiable = true;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        return super.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void makeUnmodifiable() {
        this.modifiable = false;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(T t) {
        if (!this.modifiable) {
            throw new IllegalArgumentException("List is immutable");
        }
        return super.add(t);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public void add(int i, T t) {
        if (!this.modifiable) {
            throw new IllegalArgumentException("List is immutable");
        }
        super.add(i, t);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        if (!this.modifiable) {
            throw new IllegalArgumentException("List is immutable");
        }
        return super.remove(obj);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public T remove(int i) {
        if (!this.modifiable) {
            throw new IllegalArgumentException("List is immutable");
        }
        return (T) super.remove(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends T> collection) {
        if (!this.modifiable && !collection.isEmpty()) {
            throw new IllegalArgumentException("List is immutable");
        }
        return super.addAll(collection);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public boolean addAll(int i, Collection<? extends T> collection) {
        if (!this.modifiable && !collection.isEmpty()) {
            throw new IllegalArgumentException("List is immutable");
        }
        return super.addAll(i, collection);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection<?> collection) {
        if (!this.modifiable && !collection.isEmpty()) {
            throw new IllegalArgumentException("List is immutable");
        }
        return super.removeAll(collection);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(Collection<?> collection) {
        if (!this.modifiable && !isEmpty()) {
            throw new IllegalArgumentException("List is immutable");
        }
        return super.retainAll(collection);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        if (!this.modifiable && !isEmpty()) {
            throw new IllegalArgumentException("List is immutable");
        }
        super.clear();
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public T set(int i, T t) {
        if (!this.modifiable) {
            throw new IllegalArgumentException("List is immutable");
        }
        return (T) super.set(i, t);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<T> iterator() {
        final Iterator it = super.iterator();
        return new Iterator<T>() { // from class: io.github.classgraph.PotentiallyUnmodifiableList.1
            @Override // java.util.Iterator
            public boolean hasNext() {
                if (PotentiallyUnmodifiableList.this.isEmpty()) {
                    return false;
                }
                return it.hasNext();
            }

            @Override // java.util.Iterator
            public T next() {
                return (T) it.next();
            }

            @Override // java.util.Iterator
            public void remove() {
                if (!PotentiallyUnmodifiableList.this.modifiable) {
                    throw new IllegalArgumentException("List is immutable");
                }
                it.remove();
            }
        };
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public ListIterator<T> listIterator() {
        final ListIterator listIterator = super.listIterator();
        return new ListIterator<T>() { // from class: io.github.classgraph.PotentiallyUnmodifiableList.2
            @Override // java.util.ListIterator, java.util.Iterator
            public boolean hasNext() {
                if (PotentiallyUnmodifiableList.this.isEmpty()) {
                    return false;
                }
                return listIterator.hasNext();
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public T next() {
                return (T) listIterator.next();
            }

            @Override // java.util.ListIterator
            public boolean hasPrevious() {
                if (PotentiallyUnmodifiableList.this.isEmpty()) {
                    return false;
                }
                return listIterator.hasPrevious();
            }

            @Override // java.util.ListIterator
            public T previous() {
                return (T) listIterator.previous();
            }

            @Override // java.util.ListIterator
            public int nextIndex() {
                if (PotentiallyUnmodifiableList.this.isEmpty()) {
                    return 0;
                }
                return listIterator.nextIndex();
            }

            @Override // java.util.ListIterator
            public int previousIndex() {
                if (PotentiallyUnmodifiableList.this.isEmpty()) {
                    return -1;
                }
                return listIterator.previousIndex();
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public void remove() {
                if (!PotentiallyUnmodifiableList.this.modifiable) {
                    throw new IllegalArgumentException("List is immutable");
                }
                listIterator.remove();
            }

            @Override // java.util.ListIterator
            public void set(T t) {
                if (!PotentiallyUnmodifiableList.this.modifiable) {
                    throw new IllegalArgumentException("List is immutable");
                }
                listIterator.set(t);
            }

            @Override // java.util.ListIterator
            public void add(T t) {
                if (!PotentiallyUnmodifiableList.this.modifiable) {
                    throw new IllegalArgumentException("List is immutable");
                }
                listIterator.add(t);
            }
        };
    }
}
