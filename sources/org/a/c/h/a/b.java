package org.a.c.h.a;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.a.c.b.j;

/* loaded from: infinitode-2.jar:org/a/c/h/a/b.class */
public final class b<K, V> implements Map<K, V> {

    /* renamed from: a, reason: collision with root package name */
    private final org.a.c.b.d f4459a;

    /* renamed from: b, reason: collision with root package name */
    private final Map<K, V> f4460b;

    public b(Map<K, V> map, org.a.c.b.d dVar) {
        this.f4460b = map;
        this.f4459a = dVar;
    }

    @Override // java.util.Map
    public final int size() {
        return this.f4459a.a();
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return this.f4460b.containsKey(obj);
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return this.f4460b.containsValue(obj);
    }

    @Override // java.util.Map
    public final V get(Object obj) {
        return this.f4460b.get(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public final V put(K k, V v) {
        this.f4459a.a(j.a((String) k), ((c) v).f());
        return this.f4460b.put(k, v);
    }

    @Override // java.util.Map
    public final V remove(Object obj) {
        this.f4459a.m(j.a((String) obj));
        return this.f4460b.remove(obj);
    }

    @Override // java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override // java.util.Map
    public final void clear() {
        this.f4459a.b();
        this.f4460b.clear();
    }

    @Override // java.util.Map
    public final Set<K> keySet() {
        return this.f4460b.keySet();
    }

    @Override // java.util.Map
    public final Collection<V> values() {
        return this.f4460b.values();
    }

    @Override // java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        return Collections.unmodifiableSet(this.f4460b.entrySet());
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        boolean z = false;
        if (obj instanceof b) {
            z = ((b) obj).f4459a.equals(this.f4459a);
        }
        return z;
    }

    public final String toString() {
        return this.f4460b.toString();
    }

    @Override // java.util.Map
    public final int hashCode() {
        return this.f4459a.hashCode();
    }
}
