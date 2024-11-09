package com.a.a.c.b;

import com.a.a.c.f.w;
import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/c/b/t.class */
public final class t implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private static w.a[] f243a = new w.a[0];

    /* renamed from: b, reason: collision with root package name */
    private static com.a.a.c.k.i[] f244b = new com.a.a.c.k.i[0];
    private w.a[] c;
    private w.a[] d;
    private com.a.a.c.k.i[] e;

    public t() {
        this(null, null, null);
    }

    private t(w.a[] aVarArr, w.a[] aVarArr2, com.a.a.c.k.i[] iVarArr) {
        this.c = aVarArr == null ? f243a : aVarArr;
        this.d = aVarArr2 == null ? f243a : aVarArr2;
        this.e = iVarArr == null ? f244b : iVarArr;
    }

    public final boolean a() {
        return this.d.length > 0;
    }

    public final boolean b() {
        return this.e.length > 0;
    }

    public final Iterable<w.a> c() {
        return new com.a.a.c.m.e(this.c);
    }

    public final Iterable<w.a> d() {
        return new com.a.a.c.m.e(this.d);
    }

    public final Iterable<com.a.a.c.k.i> e() {
        return new com.a.a.c.m.e(this.e);
    }
}
