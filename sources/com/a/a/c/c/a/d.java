package com.a.a.c.c.a;

import com.a.a.a.b;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/d.class */
public final class d {

    /* renamed from: a, reason: collision with root package name */
    private com.a.a.c.a f255a;

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.c.f.o f256b;
    private int c;
    private a[] d;

    private d(com.a.a.c.a aVar, com.a.a.c.f.o oVar, a[] aVarArr, int i) {
        this.f255a = aVar;
        this.f256b = oVar;
        this.d = aVarArr;
        this.c = i;
    }

    public static d a(com.a.a.c.a aVar, com.a.a.c.f.o oVar, com.a.a.c.f.s[] sVarArr) {
        int f = oVar.f();
        a[] aVarArr = new a[f];
        for (int i = 0; i < f; i++) {
            com.a.a.c.f.n c = oVar.c(i);
            aVarArr[i] = new a(c, sVarArr == null ? null : sVarArr[i], aVar.e((com.a.a.c.f.j) c));
        }
        return new d(aVar, oVar, aVarArr, f);
    }

    public final com.a.a.c.f.o a() {
        return this.f256b;
    }

    public final int b() {
        return this.c;
    }

    public final b.a a(int i) {
        return this.d[i].c;
    }

    public final com.a.a.c.f.n b(int i) {
        return this.d[i].f257a;
    }

    public final com.a.a.c.f.s c(int i) {
        return this.d[i].f258b;
    }

    public final com.a.a.c.w d(int i) {
        com.a.a.c.f.s sVar = this.d[i].f258b;
        if (sVar != null) {
            return sVar.b();
        }
        return null;
    }

    public final com.a.a.c.w e(int i) {
        com.a.a.c.f.s sVar = this.d[0].f258b;
        if (sVar != null && sVar.e()) {
            return sVar.b();
        }
        return null;
    }

    public final com.a.a.c.w f(int i) {
        String g = this.f255a.g((com.a.a.c.f.j) this.d[i].f257a);
        if (g != null && !g.isEmpty()) {
            return com.a.a.c.w.a(g);
        }
        return null;
    }

    public final int c() {
        int i = -1;
        for (int i2 = 0; i2 < this.c; i2++) {
            if (this.d[i2].c == null) {
                if (i >= 0) {
                    return -1;
                }
                i = i2;
            }
        }
        return i;
    }

    public final String toString() {
        return this.f256b.toString();
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/a/d$a.class */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public final com.a.a.c.f.n f257a;

        /* renamed from: b, reason: collision with root package name */
        public final com.a.a.c.f.s f258b;
        public final b.a c;

        public a(com.a.a.c.f.n nVar, com.a.a.c.f.s sVar, b.a aVar) {
            this.f257a = nVar;
            this.f258b = sVar;
            this.c = aVar;
        }
    }
}
