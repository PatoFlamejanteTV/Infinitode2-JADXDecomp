package com.a.a.b.d;

import com.a.a.b.j;
import com.a.a.b.k;
import com.a.a.b.l;
import com.a.a.b.n;

/* loaded from: infinitode-2.jar:com/a/a/b/d/d.class */
public final class d extends n {
    private d c;
    private b d;
    private d e;
    private String f;
    private Object g;
    private int h;
    private int i;

    private d(d dVar, b bVar, int i, int i2, int i3) {
        this.c = dVar;
        this.d = bVar;
        this.f183a = i;
        this.h = i2;
        this.i = i3;
        this.f184b = -1;
    }

    private void a(int i, int i2, int i3) {
        this.f183a = i;
        this.f184b = -1;
        this.h = i2;
        this.i = i3;
        this.f = null;
        this.g = null;
        if (this.d != null) {
            this.d.b();
        }
    }

    public final d a(b bVar) {
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

    public static d b(b bVar) {
        return new d(null, bVar, 0, 1, 0);
    }

    public final d a(int i, int i2) {
        d dVar = this.e;
        d dVar2 = dVar;
        if (dVar == null) {
            d dVar3 = new d(this, this.d == null ? null : this.d.a(), 1, i, i2);
            dVar2 = dVar3;
            this.e = dVar3;
        } else {
            dVar2.a(1, i, i2);
        }
        return dVar2;
    }

    public final d b(int i, int i2) {
        d dVar = this.e;
        if (dVar == null) {
            d dVar2 = new d(this, this.d == null ? null : this.d.a(), 2, i, i2);
            this.e = dVar2;
            return dVar2;
        }
        dVar.a(2, i, i2);
        return dVar;
    }

    @Override // com.a.a.b.n
    public final String g() {
        return this.f;
    }

    @Override // com.a.a.b.n
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public final d a() {
        return this.c;
    }

    @Override // com.a.a.b.n
    public final j a(com.a.a.b.c.d dVar) {
        return new j(dVar, -1L, this.h, this.i);
    }

    public final d j() {
        this.g = null;
        return this.c;
    }

    public final b k() {
        return this.d;
    }

    public final boolean l() {
        int i = this.f184b + 1;
        this.f184b = i;
        return this.f183a != 0 && i > 0;
    }

    public final void a(String str) {
        this.f = str;
        if (this.d != null) {
            a(this.d, str);
        }
    }

    private static void a(b bVar, String str) {
        if (bVar.a(str)) {
            Object c = bVar.c();
            throw new k(c instanceof l ? (l) c : null, "Duplicate field '" + str + "'");
        }
    }
}
