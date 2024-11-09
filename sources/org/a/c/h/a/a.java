package org.a.c.h.a;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.a.c.b.j;
import org.a.c.b.k;
import org.a.c.b.l;
import org.a.c.b.m;
import org.a.c.b.s;

/* loaded from: infinitode-2.jar:org/a/c/h/a/a.class */
public final class a<E> implements List<E> {

    /* renamed from: a, reason: collision with root package name */
    private final org.a.c.b.a f4456a;

    /* renamed from: b, reason: collision with root package name */
    private final List<E> f4457b;
    private org.a.c.b.d c;
    private j d;

    public a() {
        this.f4456a = new org.a.c.b.a();
        this.f4457b = new ArrayList();
    }

    public a(List<E> list, org.a.c.b.a aVar) {
        this.f4457b = list;
        this.f4456a = aVar;
    }

    public a(org.a.c.b.d dVar, j jVar) {
        this.f4456a = new org.a.c.b.a();
        this.f4457b = new ArrayList();
        this.c = dVar;
        this.d = jVar;
    }

    public a(E e, org.a.c.b.b bVar, org.a.c.b.d dVar, j jVar) {
        this.f4456a = new org.a.c.b.a();
        this.f4456a.a(bVar);
        this.f4457b = new ArrayList();
        this.f4457b.add(e);
        this.c = dVar;
        this.d = jVar;
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return this.f4457b.size();
    }

    @Override // java.util.List, java.util.Collection
    public final boolean isEmpty() {
        return this.f4457b.isEmpty();
    }

    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        return this.f4457b.contains(obj);
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public final Iterator<E> iterator() {
        return this.f4457b.iterator();
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray() {
        return this.f4457b.toArray();
    }

    @Override // java.util.List, java.util.Collection
    public final <X> X[] toArray(X[] xArr) {
        return (X[]) this.f4457b.toArray(xArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.List, java.util.Collection
    public final boolean add(E e) {
        if (this.c != null) {
            this.c.a(this.d, (org.a.c.b.b) this.f4456a);
            this.c = null;
        }
        if (e instanceof String) {
            this.f4456a.a((org.a.c.b.b) new s((String) e));
        } else if (this.f4456a != null) {
            this.f4456a.a(((c) e).f());
        }
        return this.f4457b.add(e);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        boolean z = true;
        int indexOf = this.f4457b.indexOf(obj);
        if (indexOf >= 0) {
            this.f4457b.remove(indexOf);
            this.f4456a.d(indexOf);
        } else {
            z = false;
        }
        return z;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean containsAll(Collection<?> collection) {
        return this.f4457b.containsAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean addAll(Collection<? extends E> collection) {
        if (this.c != null && collection.size() > 0) {
            this.c.a(this.d, (org.a.c.b.b) this.f4456a);
            this.c = null;
        }
        this.f4456a.c(a(collection));
        return this.f4457b.addAll(collection);
    }

    @Override // java.util.List
    public final boolean addAll(int i, Collection<? extends E> collection) {
        if (this.c != null && collection.size() > 0) {
            this.c.a(this.d, (org.a.c.b.b) this.f4456a);
            this.c = null;
        }
        this.f4456a.a(i, a(collection));
        return this.f4457b.addAll(i, collection);
    }

    public static List<Integer> a(org.a.c.b.a aVar) {
        org.a.c.b.b b2;
        a aVar2 = null;
        if (aVar != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < aVar.b(); i++) {
                if (aVar.b(i) instanceof m) {
                    b2 = ((m) aVar.b(i)).a();
                } else {
                    b2 = aVar.b(i);
                }
                arrayList.add(Integer.valueOf(((l) b2).c()));
            }
            aVar2 = new a(arrayList, aVar);
        }
        return aVar2;
    }

    public static List<Float> b(org.a.c.b.a aVar) {
        a aVar2 = null;
        if (aVar != null) {
            ArrayList arrayList = new ArrayList(aVar.b());
            for (int i = 0; i < aVar.b(); i++) {
                org.a.c.b.b a2 = aVar.a(i);
                if (a2 instanceof l) {
                    arrayList.add(Float.valueOf(((l) a2).a()));
                } else {
                    arrayList.add(null);
                }
            }
            aVar2 = new a(arrayList, aVar);
        }
        return aVar2;
    }

    public static List<String> c(org.a.c.b.a aVar) {
        a aVar2 = null;
        if (aVar != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < aVar.b(); i++) {
                arrayList.add(((j) aVar.a(i)).a());
            }
            aVar2 = new a(arrayList, aVar);
        }
        return aVar2;
    }

    public static List<String> d(org.a.c.b.a aVar) {
        a aVar2 = null;
        if (aVar != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < aVar.b(); i++) {
                arrayList.add(((s) aVar.a(i)).b());
            }
            aVar2 = new a(arrayList, aVar);
        }
        return aVar2;
    }

    public static org.a.c.b.a a(List<String> list) {
        org.a.c.b.a aVar = new org.a.c.b.a();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            aVar.a((org.a.c.b.b) new s(it.next()));
        }
        return aVar;
    }

    public static org.a.c.b.a b(List<?> list) {
        org.a.c.b.a aVar = null;
        if (list != null) {
            if (list instanceof a) {
                aVar = ((a) list).f4456a;
            } else {
                aVar = new org.a.c.b.a();
                for (Object obj : list) {
                    if (obj instanceof String) {
                        aVar.a((org.a.c.b.b) new s((String) obj));
                    } else if ((obj instanceof Integer) || (obj instanceof Long)) {
                        aVar.a((org.a.c.b.b) org.a.c.b.i.a(((Number) obj).longValue()));
                    } else if ((obj instanceof Float) || (obj instanceof Double)) {
                        aVar.a((org.a.c.b.b) new org.a.c.b.f(((Number) obj).floatValue()));
                    } else if (obj instanceof c) {
                        aVar.a(((c) obj).f());
                    } else if (obj == null) {
                        aVar.a((org.a.c.b.b) k.f4371a);
                    } else {
                        throw new IllegalArgumentException("Error: Don't know how to convert type to COSBase '" + obj.getClass().getName() + "'");
                    }
                }
            }
        }
        return aVar;
    }

    private static List<org.a.c.b.b> a(Collection<?> collection) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : collection) {
            if (obj instanceof String) {
                arrayList.add(new s((String) obj));
            } else {
                arrayList.add(((c) obj).f());
            }
        }
        return arrayList;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean removeAll(Collection<?> collection) {
        this.f4456a.a(a(collection));
        return this.f4457b.removeAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean retainAll(Collection<?> collection) {
        this.f4456a.b(a(collection));
        return this.f4457b.retainAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public final void clear() {
        if (this.c != null) {
            this.c.a(this.d, (org.a.c.b.b) null);
        }
        this.f4457b.clear();
        this.f4456a.a();
    }

    @Override // java.util.List, java.util.Collection
    public final boolean equals(Object obj) {
        return this.f4457b.equals(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final int hashCode() {
        return this.f4457b.hashCode();
    }

    @Override // java.util.List
    public final E get(int i) {
        return this.f4457b.get(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.List
    public final E set(int i, E e) {
        if (e instanceof String) {
            s sVar = new s((String) e);
            if (this.c != null && i == 0) {
                this.c.a(this.d, (org.a.c.b.b) sVar);
            }
            this.f4456a.b(i, sVar);
        } else {
            if (this.c != null && i == 0) {
                this.c.a(this.d, ((c) e).f());
            }
            this.f4456a.b(i, ((c) e).f());
        }
        return this.f4457b.set(i, e);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.List
    public final void add(int i, E e) {
        if (this.c != null) {
            this.c.a(this.d, (org.a.c.b.b) this.f4456a);
            this.c = null;
        }
        this.f4457b.add(i, e);
        if (e instanceof String) {
            this.f4456a.a(i, (org.a.c.b.b) new s((String) e));
        } else {
            this.f4456a.a(i, ((c) e).f());
        }
    }

    @Override // java.util.List
    public final E remove(int i) {
        this.f4456a.d(i);
        return this.f4457b.remove(i);
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        return this.f4457b.indexOf(obj);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        return this.f4457b.indexOf(obj);
    }

    @Override // java.util.List
    public final ListIterator<E> listIterator() {
        return this.f4457b.listIterator();
    }

    @Override // java.util.List
    public final ListIterator<E> listIterator(int i) {
        return this.f4457b.listIterator(i);
    }

    @Override // java.util.List
    public final List<E> subList(int i, int i2) {
        return this.f4457b.subList(i, i2);
    }

    public final String toString() {
        return "COSArrayList{" + this.f4456a.toString() + "}";
    }

    public final org.a.c.b.a a() {
        return this.f4456a;
    }
}
