package org.a.c.b;

import com.a.a.a.am;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/c/b/e.class */
public class e extends b implements Closeable {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4360a = org.a.a.a.c.a(e.class);

    /* renamed from: b, reason: collision with root package name */
    private float f4361b;
    private final Map<n, m> c;
    private final Map<n, Long> d;
    private final List<p> e;
    private d f;
    private boolean g;
    private long h;
    private boolean i;
    private boolean j;
    private org.a.c.d.g k;
    private long l;

    public e() {
        this(org.a.c.d.g.a());
    }

    public e(org.a.c.d.g gVar) {
        this.f4361b = 1.4f;
        this.c = new HashMap();
        this.d = new HashMap();
        this.e = new ArrayList();
        this.g = true;
        this.i = false;
        this.k = gVar;
    }

    public final p a() {
        p pVar = new p(this.k);
        this.e.add(pVar);
        return pVar;
    }

    public final p a(d dVar) {
        p pVar = new p(this.k);
        for (Map.Entry<j, b> entry : dVar.e()) {
            pVar.a(entry.getKey(), entry.getValue());
        }
        return pVar;
    }

    public final void a(float f) {
        this.f4361b = f;
    }

    public final float b() {
        return this.f4361b;
    }

    public final boolean c() {
        boolean z = false;
        if (this.f != null) {
            z = this.f.a(j.aS) instanceof d;
        }
        return z;
    }

    public final d d() {
        return this.f.d(j.aS);
    }

    public final void b(d dVar) {
        this.f.a(j.aS, (b) dVar);
    }

    public final a e() {
        return h().f(j.bA);
    }

    public final void a(a aVar) {
        h().a(j.bA, (b) aVar);
    }

    private List<m> n() {
        return new ArrayList(this.c.values());
    }

    public final d h() {
        return this.f;
    }

    public final void c(d dVar) {
        this.f = dVar;
    }

    public final long i() {
        return this.l;
    }

    public final void a(long j) {
        this.l = j;
    }

    @Override // org.a.c.b.b
    public final Object a(u uVar) {
        return uVar.a(this);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (!this.i) {
            IOException iOException = null;
            Iterator<m> it = n().iterator();
            while (it.hasNext()) {
                b a2 = it.next().a();
                if (a2 instanceof p) {
                    iOException = am.a((p) a2, f4360a, "COSStream", iOException);
                }
            }
            Iterator<p> it2 = this.e.iterator();
            while (it2.hasNext()) {
                iOException = am.a(it2.next(), f4360a, "COSStream", iOException);
            }
            if (this.k != null) {
                iOException = am.a(this.k, f4360a, "ScratchFile", iOException);
            }
            this.i = true;
            if (iOException != null) {
                throw iOException;
            }
        }
    }

    public final boolean j() {
        return this.i;
    }

    protected void finalize() {
        if (!this.i) {
            close();
        }
    }

    public final m a(n nVar) {
        m mVar = null;
        if (nVar != null) {
            mVar = this.c.get(nVar);
        }
        if (mVar == null) {
            mVar = new m(null);
            if (nVar != null) {
                mVar.a(nVar.b());
                mVar.a(nVar.a());
                this.c.put(nVar, mVar);
            }
        }
        return mVar;
    }

    public final void a(Map<n, Long> map) {
        this.d.putAll(map);
    }

    public final Map<n, Long> k() {
        return this.d;
    }

    public final void b(long j) {
        this.h = j;
    }

    public final long l() {
        return this.h;
    }

    public final boolean m() {
        return this.j;
    }

    public final void b(boolean z) {
        this.j = z;
    }
}
