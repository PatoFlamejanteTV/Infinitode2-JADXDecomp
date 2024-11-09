package com.a.a.c.m.a;

import com.a.a.c.m.a.a;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: infinitode-2.jar:com/a/a/c/m/a/b.class */
final class b<E extends com.a.a.c.m.a.a<E>> extends AbstractCollection<E> implements Deque<E> {

    /* renamed from: a, reason: collision with root package name */
    private E f672a;

    /* renamed from: b, reason: collision with root package name */
    private E f673b;

    private void e(E e) {
        E e2 = this.f672a;
        this.f672a = e;
        if (e2 == null) {
            this.f673b = e;
        } else {
            e2.a(e);
            e.b(e2);
        }
    }

    private void f(E e) {
        E e2 = this.f673b;
        this.f673b = e;
        if (e2 == null) {
            this.f672a = e;
        } else {
            e2.b(e);
            e.a(e2);
        }
    }

    private E b() {
        E e = this.f672a;
        E e2 = (E) e.b();
        e.b(null);
        this.f672a = e2;
        if (e2 == null) {
            this.f673b = null;
        } else {
            e2.a(null);
        }
        return e;
    }

    private E c() {
        E e = this.f673b;
        E e2 = (E) e.a();
        e.a(null);
        this.f673b = e2;
        if (e2 == null) {
            this.f672a = null;
        } else {
            e2.b(null);
        }
        return e;
    }

    private void g(E e) {
        E e2 = (E) e.a();
        E e3 = (E) e.b();
        if (e2 == null) {
            this.f672a = e3;
        } else {
            e2.b(e3);
            e.a(null);
        }
        if (e3 == null) {
            this.f673b = e2;
        } else {
            e3.a(e2);
            e.b(null);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean isEmpty() {
        return this.f672a == null;
    }

    private void d() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Deque
    public final int size() {
        int i = 0;
        com.a.a.c.m.a.a aVar = this.f672a;
        while (true) {
            com.a.a.c.m.a.a aVar2 = aVar;
            if (aVar2 != null) {
                i++;
                aVar = aVar2.b();
            } else {
                return i;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v6, types: [com.a.a.c.m.a.a] */
    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        E e = this.f672a;
        while (true) {
            E e2 = e;
            if (e2 != null) {
                ?? b2 = e2.b();
                e2.a(null);
                e2.b(null);
                e = b2;
            } else {
                this.f673b = null;
                this.f672a = null;
                return;
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Deque
    public final boolean contains(Object obj) {
        return (obj instanceof com.a.a.c.m.a.a) && a((com.a.a.c.m.a.a) obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean a(com.a.a.c.m.a.a<?> aVar) {
        return (aVar.a() == null && aVar.b() == null && aVar != this.f672a) ? false : true;
    }

    public final void b(E e) {
        if (e != this.f673b) {
            g(e);
            f(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.Deque, java.util.Queue
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public E peek() {
        return peekFirst();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.Deque
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public E peekFirst() {
        return this.f672a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.Deque
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public E peekLast() {
        return this.f673b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.Deque
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public E getFirst() {
        d();
        return peekFirst();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.Deque
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public E getLast() {
        d();
        return peekLast();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.Deque, java.util.Queue
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public E element() {
        return getFirst();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.Deque, java.util.Queue
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public boolean offer(E e) {
        return offerLast(e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.Deque
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public boolean offerFirst(E e) {
        if (a(e)) {
            return false;
        }
        e(e);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.Deque
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public boolean offerLast(E e) {
        if (a(e)) {
            return false;
        }
        f(e);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Deque, java.util.Queue
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public final boolean add(E e) {
        return offerLast(e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.Deque
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public void addFirst(E e) {
        if (!offerFirst(e)) {
            throw new IllegalArgumentException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.Deque
    /* renamed from: l, reason: merged with bridge method [inline-methods] */
    public void addLast(E e) {
        if (!offerLast(e)) {
            throw new IllegalArgumentException();
        }
    }

    @Override // java.util.Deque, java.util.Queue
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public final E poll() {
        return pollFirst();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.Deque
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        return b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.Deque
    /* renamed from: l, reason: merged with bridge method [inline-methods] */
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        return c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.Deque, java.util.Queue
    /* renamed from: m, reason: merged with bridge method [inline-methods] */
    public E remove() {
        return removeFirst();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Deque
    public final boolean remove(Object obj) {
        return (obj instanceof com.a.a.c.m.a.a) && d((com.a.a.c.m.a.a) obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean d(E e) {
        if (a(e)) {
            g(e);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.Deque
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public E removeFirst() {
        d();
        return pollFirst();
    }

    @Override // java.util.Deque
    public final boolean removeFirstOccurrence(Object obj) {
        return remove(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.Deque
    /* renamed from: o, reason: merged with bridge method [inline-methods] */
    public E removeLast() {
        d();
        return pollLast();
    }

    @Override // java.util.Deque
    public final boolean removeLastOccurrence(Object obj) {
        return remove(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean removeAll(Collection<?> collection) {
        boolean z = false;
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            z |= remove(it.next());
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.Deque
    /* renamed from: m, reason: merged with bridge method [inline-methods] */
    public void push(E e) {
        addFirst(e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.Deque
    /* renamed from: p, reason: merged with bridge method [inline-methods] */
    public E pop() {
        return removeFirst();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Deque
    public final Iterator<E> iterator() {
        return new c(this, this.f672a);
    }

    @Override // java.util.Deque
    public final Iterator<E> descendingIterator() {
        return new d(this, this.f673b);
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/m/a/b$a.class */
    abstract class a implements Iterator<E> {

        /* renamed from: a, reason: collision with root package name */
        E f674a;

        abstract E a();

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(b bVar, E e) {
            this.f674a = e;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f674a != null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // java.util.Iterator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E e = this.f674a;
            this.f674a = (E) a();
            return e;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
