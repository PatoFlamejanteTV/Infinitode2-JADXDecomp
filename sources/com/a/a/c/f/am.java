package com.a.a.c.f;

import com.a.a.c.f.t;
import java.io.Serializable;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/f/am.class */
public final class am implements t.a, Serializable {

    /* renamed from: a, reason: collision with root package name */
    private t.a f441a;

    /* renamed from: b, reason: collision with root package name */
    private Map<com.a.a.c.l.b, Class<?>> f442b;

    public am(t.a aVar) {
        this.f441a = aVar;
    }

    @Override // com.a.a.c.f.t.a
    public final Class<?> i(Class<?> cls) {
        Class<?> i = this.f441a == null ? null : this.f441a.i(cls);
        Class<?> cls2 = i;
        if (i == null && this.f442b != null) {
            cls2 = this.f442b.get(new com.a.a.c.l.b(cls));
        }
        return cls2;
    }

    public final boolean a() {
        if (this.f442b == null) {
            if (this.f441a == null) {
                return false;
            }
            if (this.f441a instanceof am) {
                return ((am) this.f441a).a();
            }
            return true;
        }
        return true;
    }
}
