package com.a.a.c.m;

import com.a.a.c.m.a.e;
import java.io.Serializable;
import java.util.Map;
import java.util.function.BiConsumer;

/* loaded from: infinitode-2.jar:com/a/a/c/m/o.class */
public final class o<K, V> implements q<K, V>, Serializable {

    /* renamed from: a, reason: collision with root package name */
    private transient com.a.a.c.m.a.e<K, V> f739a;

    public o(int i, int i2) {
        this.f739a = new e.b().a(i).a(i2).b(4).a();
    }

    public final V a(K k, V v) {
        return this.f739a.put(k, v);
    }

    @Override // com.a.a.c.m.q
    public final V b(K k, V v) {
        return this.f739a.putIfAbsent(k, v);
    }

    @Override // com.a.a.c.m.q
    public final V a(Object obj) {
        return this.f739a.get(obj);
    }

    public final int a() {
        return this.f739a.size();
    }

    public final void a(BiConsumer<K, V> biConsumer) {
        for (Map.Entry<K, V> entry : this.f739a.entrySet()) {
            biConsumer.accept(entry.getKey(), entry.getValue());
        }
    }
}
