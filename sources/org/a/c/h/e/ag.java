package org.a.c.h.e;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:org/a/c/h/e/ag.class */
public class ag extends aa {
    private static final org.a.a.a.a e = org.a.a.a.c.a(ag.class);
    private org.a.c.b.d f;
    private org.a.c.i.d g;
    private org.a.b.h.a h;

    public ag(org.a.c.b.d dVar, org.a.c.h.k kVar) {
        super(dVar);
        l();
    }

    @Override // org.a.c.h.e.u
    public final String d() {
        return this.f4561b.g(org.a.c.b.j.cp);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.a.c.h.e.aa
    public final void l() {
        org.a.c.b.b a2 = this.f4561b.a(org.a.c.b.j.aR);
        if (a2 instanceof org.a.c.b.j) {
            org.a.c.b.j jVar = (org.a.c.b.j) a2;
            this.c = org.a.c.h.e.a.c.a(jVar);
            if (this.c == null) {
                new StringBuilder("Unknown encoding: ").append(jVar.a());
            }
        } else if (a2 instanceof org.a.c.b.d) {
            this.c = new org.a.c.h.e.a.b((org.a.c.b.d) a2);
        }
        this.d = org.a.c.h.e.a.d.a();
    }

    @Override // org.a.c.h.e.aa
    protected final org.a.c.h.e.a.c m() {
        throw new UnsupportedOperationException("not supported for Type 3 fonts");
    }

    @Override // org.a.c.h.e.aa
    protected final Boolean q() {
        return Boolean.FALSE;
    }

    @Override // org.a.c.h.e.u
    public final float a(int i) {
        int b2 = this.f4561b.b(org.a.c.b.j.ba, -1);
        int b3 = this.f4561b.b(org.a.c.b.j.bW, -1);
        if (!g().isEmpty() && i >= b2 && i <= b3) {
            Float f = g().get(i - b2);
            if (f == null) {
                return 0.0f;
            }
            return f.floatValue();
        }
        v b4 = b();
        if (b4 != null) {
            return b4.n();
        }
        return c(i);
    }

    @Override // org.a.c.h.e.u
    public final float c(int i) {
        af g = g(i);
        if (g != null) {
            g.b();
            if (g.b().d() == 0) {
                return 0.0f;
            }
            return g.d();
        }
        return 0.0f;
    }

    @Override // org.a.c.h.e.u
    public final boolean c() {
        return true;
    }

    @Override // org.a.c.h.e.u
    protected final byte[] d(int i) {
        throw new UnsupportedOperationException("Not implemented: Type3");
    }

    @Override // org.a.c.h.e.u
    public final int a(InputStream inputStream) {
        return inputStream.read();
    }

    @Override // org.a.c.h.e.u
    public final org.a.c.i.d h() {
        if (this.g == null) {
            org.a.c.b.b a2 = this.f4561b.a(org.a.c.b.j.bl);
            if (a2 instanceof org.a.c.b.a) {
                this.g = new org.a.c.i.d((org.a.c.b.a) a2);
            } else {
                return super.h();
            }
        }
        return this.g;
    }

    private org.a.c.h.a.h s() {
        org.a.c.b.b a2 = this.f4561b.a(org.a.c.b.j.bf);
        org.a.c.h.a.h hVar = null;
        if (a2 instanceof org.a.c.b.a) {
            hVar = new org.a.c.h.a.h((org.a.c.b.a) a2);
        }
        return hVar;
    }

    @Override // org.a.c.h.e.u
    public final org.a.b.h.a e() {
        if (this.h == null) {
            this.h = t();
        }
        return this.h;
    }

    private org.a.b.h.a t() {
        org.a.c.h.a.h s = s();
        if (s.c() == 0.0f && s.d() == 0.0f && s.e() == 0.0f && s.g() == 0.0f) {
            org.a.c.b.d u = u();
            Iterator<org.a.c.b.j> it = u.d().iterator();
            while (it.hasNext()) {
                org.a.c.b.b a2 = u.a(it.next());
                if (a2 instanceof org.a.c.b.p) {
                    try {
                        org.a.c.h.a.h c = new af(this, (org.a.c.b.p) a2).c();
                        if (c != null) {
                            s.a(Math.min(s.c(), c.c()));
                            s.b(Math.min(s.d(), c.d()));
                            s.c(Math.max(s.e(), c.e()));
                            s.d(Math.max(s.g(), c.g()));
                        }
                    } catch (IOException unused) {
                    }
                }
            }
        }
        return new org.a.b.h.a(s.c(), s.d(), s.e(), s.g());
    }

    private org.a.c.b.d u() {
        if (this.f == null) {
            this.f = (org.a.c.b.d) this.f4561b.a(org.a.c.b.j.T);
        }
        return this.f;
    }

    private af g(int i) {
        org.a.c.b.b a2 = u().a(org.a.c.b.j.a(n().a(i)));
        if (a2 instanceof org.a.c.b.p) {
            return new af(this, (org.a.c.b.p) a2);
        }
        return null;
    }
}
