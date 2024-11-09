package org.a.c.h.e;

/* loaded from: infinitode-2.jar:org/a/c/h/e/v.class */
public final class v implements org.a.c.h.a.c {

    /* renamed from: a, reason: collision with root package name */
    private final org.a.c.b.d f4562a;

    /* renamed from: b, reason: collision with root package name */
    private float f4563b;
    private int c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public v() {
        this.f4563b = Float.NEGATIVE_INFINITY;
        this.c = -1;
        this.f4562a = new org.a.c.b.d();
        this.f4562a.a(org.a.c.b.j.dN, (org.a.c.b.b) org.a.c.b.j.bg);
    }

    public v(org.a.c.b.d dVar) {
        this.f4563b = Float.NEGATIVE_INFINITY;
        this.c = -1;
        this.f4562a = dVar;
    }

    public final boolean a() {
        return a(1);
    }

    public final void a(boolean z) {
        a(1, z);
    }

    public final boolean b() {
        return a(2);
    }

    public final void b(boolean z) {
        a(2, true);
    }

    public final boolean c() {
        return a(4);
    }

    public final void c(boolean z) {
        a(4, z);
    }

    public final void d(boolean z) {
        a(8, true);
    }

    public final void e(boolean z) {
        a(32, z);
    }

    public final boolean d() {
        return a(64);
    }

    public final void f(boolean z) {
        a(64, z);
    }

    private boolean a(int i) {
        return (s() & i) != 0;
    }

    private void a(int i, boolean z) {
        int i2;
        int s = s();
        if (z) {
            i2 = s | i;
        } else {
            i2 = s & (i ^ (-1));
        }
        b(i2);
    }

    @Override // org.a.c.h.a.c
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public final org.a.c.b.d f() {
        return this.f4562a;
    }

    public final String g() {
        String str = null;
        org.a.c.b.b a2 = this.f4562a.a(org.a.c.b.j.bm);
        if (a2 instanceof org.a.c.b.j) {
            str = ((org.a.c.b.j) a2).a();
        }
        return str;
    }

    public final void a(String str) {
        org.a.c.b.j jVar = null;
        if (str != null) {
            jVar = org.a.c.b.j.a(str);
        }
        this.f4562a.a(org.a.c.b.j.bm, (org.a.c.b.b) jVar);
    }

    public final String h() {
        String str = null;
        org.a.c.b.s sVar = (org.a.c.b.s) this.f4562a.a(org.a.c.b.j.bh);
        if (sVar != null) {
            str = sVar.b();
        }
        return str;
    }

    public final void b(String str) {
        org.a.c.b.s sVar = null;
        if (str != null) {
            sVar = new org.a.c.b.s(str);
        }
        this.f4562a.a(org.a.c.b.j.bh, (org.a.c.b.b) sVar);
    }

    public final float i() {
        return this.f4562a.b(org.a.c.b.j.bn, 0.0f);
    }

    public final void a(float f) {
        this.f4562a.a(org.a.c.b.j.bn, f);
    }

    private int s() {
        if (this.c == -1) {
            this.c = this.f4562a.b(org.a.c.b.j.bb, 0);
        }
        return this.c;
    }

    private void b(int i) {
        this.f4562a.a(org.a.c.b.j.bb, i);
        this.c = i;
    }

    public final org.a.c.h.a.h j() {
        org.a.c.b.a aVar = (org.a.c.b.a) this.f4562a.a(org.a.c.b.j.bf);
        org.a.c.h.a.h hVar = null;
        if (aVar != null) {
            hVar = new org.a.c.h.a.h(aVar);
        }
        return hVar;
    }

    public final void a(org.a.c.h.a.h hVar) {
        this.f4562a.a(org.a.c.b.j.bf, (org.a.c.b.b) hVar.b());
    }

    public final void b(float f) {
        this.f4562a.a(org.a.c.b.j.bK, f);
    }

    public final float k() {
        return this.f4562a.b(org.a.c.b.j.m, 0.0f);
    }

    public final void c(float f) {
        this.f4562a.a(org.a.c.b.j.m, f);
    }

    public final float l() {
        return this.f4562a.b(org.a.c.b.j.ax, 0.0f);
    }

    public final void d(float f) {
        this.f4562a.a(org.a.c.b.j.ax, f);
    }

    public final float m() {
        if (this.f4563b == Float.NEGATIVE_INFINITY) {
            this.f4563b = Math.abs(this.f4562a.b(org.a.c.b.j.M, 0.0f));
        }
        return this.f4563b;
    }

    public final void e(float f) {
        this.f4562a.a(org.a.c.b.j.M, f);
        this.f4563b = f;
    }

    public final void f(float f) {
        this.f4562a.a(org.a.c.b.j.ed, f);
    }

    public final void g(float f) {
        this.f4562a.a(org.a.c.b.j.dv, f);
    }

    public final void h(float f) {
        this.f4562a.a(org.a.c.b.j.s, f);
    }

    public final float n() {
        return this.f4562a.b(org.a.c.b.j.ck, 0.0f);
    }

    public final void c(String str) {
        org.a.c.b.s sVar = null;
        if (str != null) {
            sVar = new org.a.c.b.s(str);
        }
        this.f4562a.a(org.a.c.b.j.U, (org.a.c.b.b) sVar);
    }

    public final org.a.c.h.a.i o() {
        org.a.c.h.a.i iVar = null;
        org.a.c.b.b a2 = this.f4562a.a(org.a.c.b.j.bi);
        if (a2 instanceof org.a.c.b.p) {
            iVar = new org.a.c.h.a.i((org.a.c.b.p) a2);
        }
        return iVar;
    }

    public final org.a.c.h.a.i p() {
        org.a.c.h.a.i iVar = null;
        org.a.c.b.b a2 = this.f4562a.a(org.a.c.b.j.bj);
        if (a2 instanceof org.a.c.b.p) {
            iVar = new org.a.c.h.a.i((org.a.c.b.p) a2);
        }
        return iVar;
    }

    public final void a(org.a.c.h.a.i iVar) {
        this.f4562a.a(org.a.c.b.j.bj, iVar);
    }

    public final org.a.c.h.a.i q() {
        org.a.c.h.a.i iVar = null;
        org.a.c.b.b a2 = this.f4562a.a(org.a.c.b.j.bk);
        if (a2 instanceof org.a.c.b.p) {
            iVar = new org.a.c.h.a.i((org.a.c.b.p) a2);
        }
        return iVar;
    }

    public final void b(org.a.c.h.a.i iVar) {
        this.f4562a.a(org.a.c.b.j.Y, iVar);
    }

    public final y r() {
        org.a.c.b.d dVar = (org.a.c.b.d) this.f4562a.a(org.a.c.b.j.dB);
        if (dVar != null) {
            return new y(((org.a.c.b.s) dVar.a(org.a.c.b.j.cM)).c());
        }
        return null;
    }
}
