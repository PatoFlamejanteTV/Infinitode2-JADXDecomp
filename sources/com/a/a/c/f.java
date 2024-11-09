package com.a.a.c;

import com.a.a.c.f.am;
import com.a.a.c.f.t;
import java.io.Serializable;
import java.util.Collection;

/* loaded from: infinitode-2.jar:com/a/a/c/f.class */
public final class f extends com.a.a.c.b.r<i, f> implements Serializable {
    private static final int d = a(i.class);
    private com.a.a.c.m.p<com.a.a.c.c.o> e;
    private com.a.a.c.j.l f;
    private com.a.a.c.b.d g;
    private com.a.a.c.b.i h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;

    public f(com.a.a.c.b.a aVar, com.a.a.c.i.d dVar, am amVar, com.a.a.c.m.z zVar, com.a.a.c.b.h hVar, com.a.a.c.b.d dVar2, com.a.a.c.b.l lVar) {
        super(aVar, dVar, amVar, zVar, hVar, lVar);
        this.i = d;
        this.e = null;
        this.f = com.a.a.c.j.l.f544a;
        this.h = null;
        this.g = dVar2;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
    }

    private f(f fVar, long j, int i, int i2, int i3, int i4, int i5) {
        super(fVar, j);
        this.i = i;
        this.e = fVar.e;
        this.f = fVar.f;
        this.g = fVar.g;
        this.h = fVar.h;
        this.j = i2;
        this.k = i3;
        this.l = i4;
        this.m = i5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.b.r
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public f a(long j) {
        return new f(this, j, this.i, this.j, this.k, this.l, this.m);
    }

    public final com.a.a.b.l a(com.a.a.b.l lVar) {
        if (this.k != 0) {
            lVar.a(this.j, this.k);
        }
        return lVar;
    }

    public final boolean a() {
        if (this.f242b != null) {
            return !this.f242b.e();
        }
        return a(i.UNWRAP_ROOT_VALUE);
    }

    public final boolean a(i iVar) {
        return (this.i & iVar.b()) != 0;
    }

    public final int b() {
        return this.i;
    }

    public final boolean a(com.a.a.c.b.k kVar) {
        return this.c.a(kVar);
    }

    public final com.a.a.c.m.p<com.a.a.c.c.o> c() {
        return this.e;
    }

    public final com.a.a.c.j.l d() {
        return this.f;
    }

    public final com.a.a.c.b.i e() {
        if (this.h == null) {
            return com.a.a.c.b.i.f224a;
        }
        return this.h;
    }

    public final b a(j jVar) {
        return i().b(this, jVar, this);
    }

    public final b b(j jVar) {
        return i().a(this, jVar, (t.a) this);
    }

    public final b a(j jVar, b bVar) {
        return i().a(this, jVar, this, bVar);
    }

    public final com.a.a.c.i.e c(j jVar) {
        com.a.a.c.f.d d2 = c(jVar.b()).d();
        com.a.a.c.i.h<?> a2 = j().a((com.a.a.c.b.q<?>) this, d2, jVar);
        Collection<com.a.a.c.i.b> collection = null;
        if (a2 == null) {
            com.a.a.c.i.h<?> n = n();
            a2 = n;
            if (n == null) {
                return null;
            }
        } else {
            collection = w().b(this, d2);
        }
        return a2.a(this, jVar, collection);
    }

    public final com.a.a.c.b.b a(com.a.a.c.l.f fVar, Class<?> cls, com.a.a.c.b.f fVar2) {
        return this.g.a(this, fVar, cls, fVar2);
    }

    public final com.a.a.c.b.b a(com.a.a.c.l.f fVar, Class<?> cls, com.a.a.c.b.b bVar) {
        return this.g.a(this, fVar, cls, bVar);
    }
}
