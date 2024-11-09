package com.a.a.c.f;

import com.a.a.a.l;
import com.a.a.a.s;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/* loaded from: infinitode-2.jar:com/a/a/c/f/v.class */
public abstract class v implements com.a.a.c.c, Serializable {

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.c.v f475b;
    private transient List<com.a.a.c.w> c;

    /* JADX INFO: Access modifiers changed from: protected */
    public v(com.a.a.c.v vVar) {
        this.f475b = vVar == null ? com.a.a.c.v.c : vVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public v(v vVar) {
        this.f475b = vVar.f475b;
    }

    public final boolean t() {
        return this.f475b.a();
    }

    @Override // com.a.a.c.c
    public com.a.a.c.v d() {
        return this.f475b;
    }

    @Override // com.a.a.c.c
    public final l.d a(com.a.a.c.b.q<?> qVar, Class<?> cls) {
        j e;
        l.d f = qVar.f(cls);
        l.d dVar = null;
        com.a.a.c.a j = qVar.j();
        if (j != null && (e = e()) != null) {
            dVar = j.h((b) e);
        }
        return f == null ? dVar == null ? f245a : dVar : dVar == null ? f : f.a(dVar);
    }

    @Override // com.a.a.c.c
    public final s.b b(com.a.a.c.b.q<?> qVar, Class<?> cls) {
        com.a.a.c.a j = qVar.j();
        j e = e();
        if (e == null) {
            return qVar.e(cls);
        }
        s.b a2 = qVar.a(cls, e.d());
        if (j == null) {
            return a2;
        }
        s.b t = j.t(e);
        if (a2 == null) {
            return t;
        }
        return a2.a(t);
    }

    public final List<com.a.a.c.w> a(com.a.a.c.b.q<?> qVar) {
        j e;
        List<com.a.a.c.w> list = this.c;
        List<com.a.a.c.w> list2 = list;
        if (list == null) {
            com.a.a.c.a j = qVar.j();
            if (j != null && (e = e()) != null) {
                list2 = j.l(e);
            }
            if (list2 == null) {
                list2 = Collections.emptyList();
            }
            this.c = list2;
        }
        return list2;
    }
}
