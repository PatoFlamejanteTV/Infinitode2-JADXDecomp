package com.a.a.b.d;

import com.a.a.b.n;

/* loaded from: infinitode-2.jar:com/a/a/b/d/f.class */
public final class f extends n {
    private f c;
    private b d;
    private f e;
    private String f;
    private Object g;
    private boolean h;

    private f(int i, f fVar, b bVar) {
        this.f183a = i;
        this.c = fVar;
        this.d = bVar;
        this.f184b = -1;
    }

    private f(int i, f fVar, b bVar, Object obj) {
        this.f183a = i;
        this.c = fVar;
        this.d = bVar;
        this.f184b = -1;
        this.g = obj;
    }

    private f a(int i) {
        this.f183a = i;
        this.f184b = -1;
        this.f = null;
        this.h = false;
        this.g = null;
        if (this.d != null) {
            this.d.b();
        }
        return this;
    }

    private f a(int i, Object obj) {
        this.f183a = i;
        this.f184b = -1;
        this.f = null;
        this.h = false;
        this.g = obj;
        if (this.d != null) {
            this.d.b();
        }
        return this;
    }

    public final f a(b bVar) {
        this.d = bVar;
        return this;
    }

    @Override // com.a.a.b.n
    public final Object h() {
        return this.g;
    }

    @Override // com.a.a.b.n
    public final void a(Object obj) {
        this.g = obj;
    }

    public static f b(b bVar) {
        return new f(0, null, bVar);
    }

    public final f i() {
        f fVar = this.e;
        if (fVar == null) {
            f fVar2 = new f(1, this, this.d == null ? null : this.d.a());
            this.e = fVar2;
            return fVar2;
        }
        return fVar.a(1);
    }

    public final f b(Object obj) {
        f fVar = this.e;
        if (fVar == null) {
            f fVar2 = new f(1, this, this.d == null ? null : this.d.a(), obj);
            this.e = fVar2;
            return fVar2;
        }
        return fVar.a(1, obj);
    }

    public final f j() {
        f fVar = this.e;
        if (fVar == null) {
            f fVar2 = new f(2, this, this.d == null ? null : this.d.a());
            this.e = fVar2;
            return fVar2;
        }
        return fVar.a(2);
    }

    public final f c(Object obj) {
        f fVar = this.e;
        if (fVar == null) {
            f fVar2 = new f(2, this, this.d == null ? null : this.d.a(), obj);
            this.e = fVar2;
            return fVar2;
        }
        return fVar.a(2, obj);
    }

    @Override // com.a.a.b.n
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public final f a() {
        return this.c;
    }

    @Override // com.a.a.b.n
    public final String g() {
        return this.f;
    }

    public final f l() {
        this.g = null;
        return this.c;
    }

    public final b m() {
        return this.d;
    }

    public final int a(String str) {
        if (this.f183a != 2 || this.h) {
            return 4;
        }
        this.h = true;
        this.f = str;
        if (this.d != null) {
            a(this.d, str);
        }
        return this.f184b < 0 ? 0 : 1;
    }

    private static void a(b bVar, String str) {
        if (bVar.a(str)) {
            Object c = bVar.c();
            throw new com.a.a.b.g("Duplicate field '" + str + "'", c instanceof com.a.a.b.h ? (com.a.a.b.h) c : null);
        }
    }

    public final int n() {
        if (this.f183a == 2) {
            if (!this.h) {
                return 5;
            }
            this.h = false;
            this.f184b++;
            return 2;
        }
        if (this.f183a == 1) {
            int i = this.f184b;
            this.f184b++;
            return i < 0 ? 0 : 1;
        }
        this.f184b++;
        return this.f184b == 0 ? 0 : 3;
    }
}
