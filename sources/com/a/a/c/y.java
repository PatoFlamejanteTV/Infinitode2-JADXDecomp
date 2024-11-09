package com.a.a.c;

import com.a.a.b.h;
import com.a.a.c.f.am;
import com.a.a.c.f.t;
import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/c/y.class */
public final class y extends com.a.a.c.b.r<z, y> implements Serializable {
    private static com.a.a.b.q d = new com.a.a.b.g.e();
    private static final int e = a(z.class);
    private com.a.a.c.k.a.d f;
    private com.a.a.b.q g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;

    public y(com.a.a.c.b.a aVar, com.a.a.c.i.d dVar, am amVar, com.a.a.c.m.z zVar, com.a.a.c.b.h hVar, com.a.a.c.b.l lVar) {
        super(aVar, dVar, amVar, zVar, hVar, lVar);
        this.h = e;
        this.f = null;
        this.g = d;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
    }

    private y(y yVar, long j, int i, int i2, int i3, int i4, int i5) {
        super(yVar, j);
        this.h = i;
        this.f = yVar.f;
        this.g = yVar.g;
        this.i = i2;
        this.j = i3;
        this.k = i4;
        this.l = i5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.b.r
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public y a(long j) {
        return new y(this, j, this.h, this.i, this.j, this.k, this.l);
    }

    public final com.a.a.b.q a() {
        com.a.a.b.q qVar = this.g;
        com.a.a.b.q qVar2 = qVar;
        if (qVar instanceof com.a.a.b.g.f) {
            qVar2 = (com.a.a.b.q) ((com.a.a.b.g.f) qVar2).a();
        }
        return qVar2;
    }

    public final void a(com.a.a.b.h hVar) {
        com.a.a.b.q a2;
        if (z.INDENT_OUTPUT.a(this.h) && hVar.c() == null && (a2 = a()) != null) {
            hVar.a(a2);
        }
        boolean a3 = z.WRITE_BIGDECIMAL_AS_PLAIN.a(this.h);
        int i = this.j;
        int i2 = i;
        if (i != 0 || a3) {
            int i3 = this.i;
            if (a3) {
                int b2 = h.a.WRITE_BIGDECIMAL_AS_PLAIN.b();
                i3 |= b2;
                i2 |= b2;
            }
            hVar.a(i3, i2);
        }
    }

    public final boolean a(z zVar) {
        return (this.h & zVar.b()) != 0;
    }

    public final boolean a(com.a.a.c.b.k kVar) {
        return this.c.a(kVar);
    }

    public final com.a.a.c.k.a.d b() {
        return this.f;
    }

    public final com.a.a.b.q c() {
        return this.g;
    }

    public final b a(j jVar) {
        return i().a(this, jVar, (t.a) this);
    }
}
