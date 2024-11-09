package com.a.a.c.k;

import com.a.a.c.y;
import java.util.Collections;
import java.util.List;

/* loaded from: infinitode-2.jar:com/a/a/c/k/g.class */
public final class g {

    /* renamed from: a, reason: collision with root package name */
    private static final e[] f643a = new e[0];

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.c.b f644b;
    private y c;
    private List<e> d = Collections.emptyList();
    private e[] e;
    private a f;
    private Object g;
    private com.a.a.c.f.j h;
    private com.a.a.c.k.a.m i;

    public g(com.a.a.c.b bVar) {
        this.f644b = bVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(y yVar) {
        this.c = yVar;
    }

    public final void a(List<e> list) {
        this.d = list;
    }

    public final void a(e[] eVarArr) {
        if (eVarArr.length != this.d.size()) {
            throw new IllegalArgumentException(String.format("Trying to set %d filtered properties; must match length of non-filtered `properties` (%d)", Integer.valueOf(eVarArr.length), Integer.valueOf(this.d.size())));
        }
        this.e = eVarArr;
    }

    public final void a(a aVar) {
        this.f = aVar;
    }

    public final void a(Object obj) {
        this.g = obj;
    }

    public final void a(com.a.a.c.f.j jVar) {
        if (this.h != null) {
            throw new IllegalArgumentException("Multiple type ids specified with " + this.h + " and " + jVar);
        }
        this.h = jVar;
    }

    public final void a(com.a.a.c.k.a.m mVar) {
        this.i = mVar;
    }

    public final com.a.a.c.b a() {
        return this.f644b;
    }

    public final List<e> b() {
        return this.d;
    }

    public final a c() {
        return this.f;
    }

    public final Object d() {
        return this.g;
    }

    public final com.a.a.c.f.j e() {
        return this.h;
    }

    public final com.a.a.c.k.a.m f() {
        return this.i;
    }

    public final com.a.a.c.o<?> g() {
        e[] eVarArr;
        if (this.h != null && this.c.a(com.a.a.c.q.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            this.h.a(this.c.a(com.a.a.c.q.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
        }
        if (this.f != null) {
            this.f.a(this.c);
        }
        if (this.d == null || this.d.isEmpty()) {
            if (this.f == null && this.i == null) {
                return null;
            }
            eVarArr = f643a;
        } else {
            eVarArr = (e[]) this.d.toArray(new e[this.d.size()]);
            if (this.c.a(com.a.a.c.q.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                for (e eVar : eVarArr) {
                    eVar.a(this.c);
                }
            }
        }
        if (this.e != null && this.e.length != this.d.size()) {
            throw new IllegalStateException(String.format("Mismatch between `properties` size (%d), `filteredProperties` (%s): should have as many (or `null` for latter)", Integer.valueOf(this.d.size()), Integer.valueOf(this.e.length)));
        }
        return new f(this.f644b.a(), this, eVarArr, this.e);
    }

    public final f h() {
        return f.a(this.f644b.a(), this);
    }
}
