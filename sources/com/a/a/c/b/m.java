package com.a.a.c.b;

import com.a.a.c.c.b.ah;
import com.a.a.c.f.w;
import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/c/b/m.class */
public final class m implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private static w.a[] f233a = new w.a[0];

    /* renamed from: b, reason: collision with root package name */
    private static com.d.c.d.a.j[] f234b = new com.d.c.d.a.j[0];
    private static com.a.a.c.k.a.d[] c = new com.a.a.c.k.a.d[0];
    private static w.a[] d = new w.a[0];
    private static com.a.a.c.c.r[] e = {new ah()};
    private w.a[] f;
    private com.a.a.c.c.r[] g;
    private com.d.c.d.a.j[] h;
    private com.a.a.c.k.a.d[] i;
    private w.a[] j;

    public m() {
        this(null, null, null, null, null);
    }

    private m(w.a[] aVarArr, com.a.a.c.c.r[] rVarArr, com.d.c.d.a.j[] jVarArr, com.a.a.c.k.a.d[] dVarArr, w.a[] aVarArr2) {
        this.f = aVarArr == null ? f233a : aVarArr;
        this.g = rVarArr == null ? e : rVarArr;
        this.h = jVarArr == null ? f234b : jVarArr;
        this.i = dVarArr == null ? c : dVarArr;
        this.j = aVarArr2 == null ? d : aVarArr2;
    }

    public final boolean a() {
        return this.g.length > 0;
    }

    public final boolean b() {
        return this.h.length > 0;
    }

    public final boolean c() {
        return this.i.length > 0;
    }

    public final boolean d() {
        return this.j.length > 0;
    }

    public final Iterable<w.a> e() {
        return new com.a.a.c.m.e(this.f);
    }

    public final Iterable<com.a.a.c.c.r> f() {
        return new com.a.a.c.m.e(this.g);
    }

    public final Iterable<com.d.c.d.a.j> g() {
        return new com.a.a.c.m.e(this.h);
    }

    public final Iterable<com.a.a.c.k.a.d> h() {
        return new com.a.a.c.m.e(this.i);
    }

    public final Iterable<w.a> i() {
        return new com.a.a.c.m.e(this.j);
    }
}
