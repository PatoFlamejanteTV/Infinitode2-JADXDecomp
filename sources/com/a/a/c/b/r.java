package com.a.a.c.b;

import com.a.a.a.ac;
import com.a.a.a.f;
import com.a.a.a.l;
import com.a.a.a.q;
import com.a.a.a.s;
import com.a.a.a.t;
import com.a.a.b.c;
import com.a.a.c.b.r;
import com.a.a.c.f.am;
import com.a.a.c.f.ap;
import com.a.a.c.m.z;
import com.a.a.c.w;
import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/c/b/r.class */
public abstract class r<CFG extends com.a.a.b.c, T extends r<CFG, T>> extends q<T> implements Serializable {
    private static g d = g.a();
    private static final long e = com.a.a.c.q.c();
    private static final long f = (((com.a.a.c.q.AUTO_DETECT_FIELDS.d() | com.a.a.c.q.AUTO_DETECT_GETTERS.d()) | com.a.a.c.q.AUTO_DETECT_IS_GETTERS.d()) | com.a.a.c.q.AUTO_DETECT_SETTERS.d()) | com.a.a.c.q.AUTO_DETECT_CREATORS.d();
    private am g;
    private com.a.a.c.i.d h;

    /* renamed from: b, reason: collision with root package name */
    protected final w f242b;
    private Class<?> i;
    private j j;
    private z k;
    private h l;
    protected final l c;

    protected abstract T a(long j);

    /* JADX INFO: Access modifiers changed from: protected */
    public r(a aVar, com.a.a.c.i.d dVar, am amVar, z zVar, h hVar, l lVar) {
        super(aVar, e);
        this.g = amVar;
        this.h = dVar;
        this.k = zVar;
        this.f242b = null;
        this.i = null;
        this.j = j.a();
        this.l = hVar;
        this.c = lVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public r(r<CFG, T> rVar, long j) {
        super(rVar, j);
        this.g = rVar.g;
        this.h = rVar.h;
        this.k = rVar.k;
        this.f242b = rVar.f242b;
        this.i = rVar.i;
        this.j = rVar.j;
        this.l = rVar.l;
        this.c = rVar.c;
    }

    public final T a(com.a.a.c.q... qVarArr) {
        long j = this.f240a;
        for (int i = 0; i <= 0; i++) {
            j |= qVarArr[0].d();
        }
        if (j == this.f240a) {
            return this;
        }
        return a(j);
    }

    public final T b(com.a.a.c.q... qVarArr) {
        long j = this.f240a;
        for (int i = 0; i <= 0; i++) {
            j &= qVarArr[0].d() ^ (-1);
        }
        if (j == this.f240a) {
            return this;
        }
        return a(j);
    }

    public final com.a.a.c.i.d w() {
        return this.h;
    }

    public final w x() {
        return this.f242b;
    }

    public final Class<?> y() {
        return this.i;
    }

    public final j z() {
        return this.j;
    }

    @Override // com.a.a.c.b.q
    public final g d(Class<?> cls) {
        g a2 = this.l.a(cls);
        return a2 == null ? d : a2;
    }

    public final s.b A() {
        return this.l.a();
    }

    @Override // com.a.a.c.b.q
    public final s.b e(Class<?> cls) {
        s.b c = d(cls).c();
        s.b A = A();
        if (A == null) {
            return c;
        }
        return A.a(c);
    }

    @Override // com.a.a.c.b.q
    public final s.b a(Class<?> cls, Class<?> cls2) {
        s.b d2 = d(cls2).d();
        s.b e2 = e(cls);
        if (e2 == null) {
            return d2;
        }
        return e2.a(d2);
    }

    @Override // com.a.a.c.b.q
    public final l.d f(Class<?> cls) {
        return this.l.b(cls);
    }

    private q.a j(Class<?> cls) {
        q.a e2;
        g a2 = this.l.a(cls);
        if (a2 != null && (e2 = a2.e()) != null) {
            return e2;
        }
        return null;
    }

    public final q.a b(Class<?> cls, com.a.a.c.f.d dVar) {
        com.a.a.c.a j = j();
        return q.a.a(j == null ? null : j.b((com.a.a.c.f.b) dVar), j(cls));
    }

    public final t.a a(com.a.a.c.f.d dVar) {
        com.a.a.c.a j = j();
        if (j == null) {
            return null;
        }
        return j.c((com.a.a.c.f.b) dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ap<?> a() {
        ap<?> d2 = this.l.d();
        if ((this.f240a & f) != f) {
            if (!a(com.a.a.c.q.AUTO_DETECT_FIELDS)) {
                d2 = d2.e(f.b.NONE);
            }
            if (!a(com.a.a.c.q.AUTO_DETECT_GETTERS)) {
                d2 = d2.a(f.b.NONE);
            }
            if (!a(com.a.a.c.q.AUTO_DETECT_IS_GETTERS)) {
                d2 = d2.b(f.b.NONE);
            }
            if (!a(com.a.a.c.q.AUTO_DETECT_SETTERS)) {
                d2 = d2.c(f.b.NONE);
            }
            if (!a(com.a.a.c.q.AUTO_DETECT_CREATORS)) {
                d2 = d2.d(f.b.NONE);
            }
        }
        return d2;
    }

    @Override // com.a.a.c.b.q
    public final ap<?> a(Class<?> cls, com.a.a.c.f.d dVar) {
        ap<?> a2;
        if (com.a.a.c.m.i.m(cls)) {
            a2 = ap.a.b();
        } else {
            a2 = a();
        }
        com.a.a.c.a j = j();
        if (j != null) {
            a2 = j.a(dVar, a2);
        }
        g a3 = this.l.a(cls);
        if (a3 != null) {
            a2 = a2.a(a3.h());
        }
        return a2;
    }

    @Override // com.a.a.c.b.q
    public final ac.a q() {
        return this.l.b();
    }

    @Override // com.a.a.c.b.q
    public final Boolean r() {
        return this.l.c();
    }

    public final Boolean g(Class<?> cls) {
        Boolean i;
        g a2 = this.l.a(cls);
        if (a2 != null && (i = a2.i()) != null) {
            return i;
        }
        return this.l.c();
    }

    public final w e(com.a.a.c.j jVar) {
        if (this.f242b != null) {
            return this.f242b;
        }
        return this.k.a(jVar, this);
    }

    public final w h(Class<?> cls) {
        if (this.f242b != null) {
            return this.f242b;
        }
        return this.k.a(cls, this);
    }

    @Override // com.a.a.c.f.t.a
    public final Class<?> i(Class<?> cls) {
        return this.g.i(cls);
    }
}
