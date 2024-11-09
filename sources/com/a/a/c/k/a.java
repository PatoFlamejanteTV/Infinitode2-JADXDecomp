package com.a.a.c.k;

import com.a.a.c.aa;
import com.a.a.c.k.b.v;
import com.a.a.c.y;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/k/a.class */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private com.a.a.c.c f554a;

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.c.f.j f555b;
    private com.a.a.c.o<Object> c;
    private v d;

    public a(com.a.a.c.c cVar, com.a.a.c.f.j jVar, com.a.a.c.o<?> oVar) {
        this.f555b = jVar;
        this.f554a = cVar;
        this.c = oVar;
        if (oVar instanceof v) {
            this.d = (v) oVar;
        }
    }

    public final void a(y yVar) {
        this.f555b.a(yVar.a(com.a.a.c.q.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
    }

    public final void a(Object obj, com.a.a.b.h hVar, aa aaVar) {
        Object b2 = this.f555b.b(obj);
        if (b2 == null) {
            return;
        }
        if (!(b2 instanceof Map)) {
            aaVar.a(this.f554a.c(), String.format("Value returned by 'any-getter' %s() not java.util.Map but %s", this.f555b.b(), b2.getClass().getName()));
        }
        if (this.d != null) {
            this.d.a((Map<?, ?>) b2, hVar, aaVar);
        } else {
            this.c.a(b2, hVar, aaVar);
        }
    }

    public final void a(Object obj, com.a.a.b.h hVar, aa aaVar, o oVar) {
        Object b2 = this.f555b.b(obj);
        if (b2 == null) {
            return;
        }
        if (!(b2 instanceof Map)) {
            aaVar.a(this.f554a.c(), String.format("Value returned by 'any-getter' (%s()) not java.util.Map but %s", this.f555b.b(), b2.getClass().getName()));
        }
        if (this.d != null) {
            this.d.a(aaVar, hVar, obj, (Map<?, ?>) b2, oVar, (Object) null);
        } else {
            this.c.a(b2, hVar, aaVar);
        }
    }

    public final void a(aa aaVar) {
        if (this.c instanceof k) {
            com.a.a.c.o<?> a2 = aaVar.a(this.c, this.f554a);
            this.c = a2;
            if (a2 instanceof v) {
                this.d = (v) a2;
            }
        }
    }
}
