package com.a.a.c.b;

import com.a.a.a.ac;
import com.a.a.a.l;
import com.a.a.a.s;
import com.a.a.c.f.ap;
import java.io.Serializable;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/b/h.class */
public final class h implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private Map<Class<?>, Object> f222a;

    /* renamed from: b, reason: collision with root package name */
    private s.b f223b;
    private ac.a c;
    private ap<?> d;
    private Boolean e;
    private Boolean f;

    public h() {
        this(null, s.b.a(), ac.a.a(), ap.a.a(), null, null);
    }

    private h(Map<Class<?>, Object> map, s.b bVar, ac.a aVar, ap<?> apVar, Boolean bool, Boolean bool2) {
        this.f222a = map;
        this.f223b = bVar;
        this.c = aVar;
        this.d = apVar;
        this.e = bool;
        this.f = bool2;
    }

    public final g a(Class<?> cls) {
        if (this.f222a == null) {
            return null;
        }
        return (g) this.f222a.get(cls);
    }

    public final l.d b(Class<?> cls) {
        g gVar;
        l.d b2;
        if (this.f222a != null && (gVar = (g) this.f222a.get(cls)) != null && (b2 = gVar.b()) != null) {
            if (!b2.k()) {
                return b2.a(this.f);
            }
            return b2;
        }
        if (this.f == null) {
            return l.d.a();
        }
        return l.d.a(this.f.booleanValue());
    }

    public final s.b a() {
        return this.f223b;
    }

    public final ac.a b() {
        return this.c;
    }

    public final Boolean c() {
        return this.e;
    }

    public final ap<?> d() {
        return this.d;
    }
}
