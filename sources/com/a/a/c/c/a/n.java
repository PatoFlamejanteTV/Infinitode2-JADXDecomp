package com.a.a.c.c.a;

import com.a.a.c.c.v;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/n.class */
public final class n extends v.a {
    private com.a.a.c.f.j h;

    private n(com.a.a.c.c.v vVar, com.a.a.c.f.j jVar) {
        super(vVar);
        this.h = jVar;
    }

    public static n a(com.a.a.c.c.v vVar, com.a.a.c.f.j jVar) {
        return new n(vVar, jVar);
    }

    @Override // com.a.a.c.c.v.a
    protected final com.a.a.c.c.v a(com.a.a.c.c.v vVar) {
        return new n(vVar, this.h);
    }

    @Override // com.a.a.c.c.v.a, com.a.a.c.c.v
    public final void a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        Object c;
        Object b2 = this.h.b(obj);
        if (b2 != null) {
            c = this.g.c(lVar, gVar, b2);
        } else {
            c = this.g.a(lVar, gVar);
        }
        if (c != b2) {
            this.g.a(obj, c);
        }
    }

    @Override // com.a.a.c.c.v.a, com.a.a.c.c.v
    public final Object b(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        Object c;
        Object b2 = this.h.b(obj);
        if (b2 != null) {
            c = this.g.c(lVar, gVar, b2);
        } else {
            c = this.g.a(lVar, gVar);
        }
        if (c != b2 && c != null) {
            return this.g.b(obj, c);
        }
        return obj;
    }

    @Override // com.a.a.c.c.v.a, com.a.a.c.c.v
    public final void a(Object obj, Object obj2) {
        if (obj2 != null) {
            this.g.a(obj, obj2);
        }
    }

    @Override // com.a.a.c.c.v.a, com.a.a.c.c.v
    public final Object b(Object obj, Object obj2) {
        if (obj2 != null) {
            return this.g.b(obj, obj2);
        }
        return obj;
    }
}
