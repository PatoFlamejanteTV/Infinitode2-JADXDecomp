package com.a.a.c.k.a;

import com.a.a.a.al;
import com.a.a.a.am;
import com.a.a.c.f.ad;

/* loaded from: infinitode-2.jar:com/a/a/c/k/a/j.class */
public final class j extends am.c {

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.c.k.e f562b;

    public j(ad adVar, com.a.a.c.k.e eVar) {
        this(adVar.c(), eVar);
    }

    private j(Class<?> cls, com.a.a.c.k.e eVar) {
        super(cls);
        this.f562b = eVar;
    }

    @Override // com.a.a.a.am.c, com.a.a.a.am.a, com.a.a.a.al
    public final boolean a(al<?> alVar) {
        if (alVar.getClass() == getClass()) {
            j jVar = (j) alVar;
            return jVar.a() == this.f49a && jVar.f562b == this.f562b;
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Throwable, java.lang.Object] */
    @Override // com.a.a.a.al
    public final Object b(Object obj) {
        ?? a2;
        try {
            a2 = this.f562b.a(obj);
            return a2;
        } catch (RuntimeException e) {
            throw a2;
        } catch (Exception e2) {
            throw new IllegalStateException("Problem accessing property '" + this.f562b.a() + "': " + e2.getMessage(), e2);
        }
    }

    @Override // com.a.a.a.al
    public final al<Object> a(Class<?> cls) {
        return cls == this.f49a ? this : new j(cls, this.f562b);
    }

    @Override // com.a.a.a.al
    public final al<Object> b() {
        return this;
    }

    @Override // com.a.a.a.al
    public final al.a a(Object obj) {
        if (obj == null) {
            return null;
        }
        return new al.a(getClass(), this.f49a, obj);
    }
}
