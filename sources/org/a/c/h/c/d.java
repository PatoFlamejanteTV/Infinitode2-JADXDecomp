package org.a.c.h.c;

import java.io.IOException;
import org.a.c.b.s;

/* loaded from: infinitode-2.jar:org/a/c/h/c/d.class */
public final class d implements org.a.c.h.a.c {

    /* renamed from: a, reason: collision with root package name */
    private final org.a.c.b.d f4497a;

    /* renamed from: b, reason: collision with root package name */
    private k f4498b;

    public d() {
        this.f4497a = new org.a.c.b.d();
    }

    public d(org.a.c.b.d dVar) {
        this.f4497a = dVar;
        this.f4498b = l.f4508a.a(r());
    }

    public final k a() {
        if (this.f4498b == null) {
            throw new IOException("No security handler for filter " + r());
        }
        return this.f4498b;
    }

    public final void a(k kVar) {
        this.f4498b = kVar;
    }

    @Override // org.a.c.h.a.c
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public final org.a.c.b.d f() {
        return this.f4497a;
    }

    public final void a(String str) {
        this.f4497a.a(org.a.c.b.j.aY, (org.a.c.b.b) org.a.c.b.j.a(str));
    }

    private String r() {
        return this.f4497a.g(org.a.c.b.j.aY);
    }

    public final void b(String str) {
        this.f4497a.a(org.a.c.b.j.dC, str);
    }

    public final void a(int i) {
        this.f4497a.a(org.a.c.b.j.dU, i);
    }

    public final int c() {
        return this.f4497a.b(org.a.c.b.j.dU, 0);
    }

    public final void b(int i) {
        this.f4497a.a(org.a.c.b.j.bX, i);
    }

    public final int d() {
        return this.f4497a.b(org.a.c.b.j.bX, 40);
    }

    public final void c(int i) {
        this.f4497a.a(org.a.c.b.j.da, i);
    }

    public final int e() {
        return this.f4497a.b(org.a.c.b.j.da, 0);
    }

    public final void a(byte[] bArr) {
        this.f4497a.a(org.a.c.b.j.ct, (org.a.c.b.b) new s(bArr));
    }

    public final byte[] g() {
        byte[] bArr = null;
        s sVar = (s) this.f4497a.a(org.a.c.b.j.ct);
        if (sVar != null) {
            bArr = sVar.c();
        }
        return bArr;
    }

    public final void b(byte[] bArr) {
        this.f4497a.a(org.a.c.b.j.dR, (org.a.c.b.b) new s(bArr));
    }

    public final byte[] h() {
        byte[] bArr = null;
        s sVar = (s) this.f4497a.a(org.a.c.b.j.dR);
        if (sVar != null) {
            bArr = sVar.c();
        }
        return bArr;
    }

    public final void c(byte[] bArr) {
        this.f4497a.a(org.a.c.b.j.cA, (org.a.c.b.b) new s(bArr));
    }

    public final byte[] i() {
        byte[] bArr = null;
        s sVar = (s) this.f4497a.a(org.a.c.b.j.cA);
        if (sVar != null) {
            bArr = sVar.c();
        }
        return bArr;
    }

    public final void d(byte[] bArr) {
        this.f4497a.a(org.a.c.b.j.dS, (org.a.c.b.b) new s(bArr));
    }

    public final byte[] j() {
        byte[] bArr = null;
        s sVar = (s) this.f4497a.a(org.a.c.b.j.dS);
        if (sVar != null) {
            bArr = sVar.c();
        }
        return bArr;
    }

    public final void d(int i) {
        this.f4497a.a(org.a.c.b.j.cJ, i);
    }

    public final int k() {
        return this.f4497a.b(org.a.c.b.j.cJ, 0);
    }

    public final boolean l() {
        boolean z = true;
        org.a.c.b.b a2 = this.f4497a.a(org.a.c.b.j.aT);
        if (a2 instanceof org.a.c.b.c) {
            z = ((org.a.c.b.c) a2).a();
        }
        return z;
    }

    public final void a(byte[][] bArr) {
        org.a.c.b.a aVar = new org.a.c.b.a();
        for (byte[] bArr2 : bArr) {
            aVar.a((org.a.c.b.b) new s(bArr2));
        }
        this.f4497a.a(org.a.c.b.j.dc, (org.a.c.b.b) aVar);
        aVar.a(true);
    }

    public final c m() {
        return c(org.a.c.b.j.du);
    }

    public final c n() {
        return c(org.a.c.b.j.at);
    }

    private c c(org.a.c.b.j jVar) {
        org.a.c.b.b a2 = this.f4497a.a(org.a.c.b.j.Q);
        if (a2 instanceof org.a.c.b.d) {
            org.a.c.b.b a3 = ((org.a.c.b.d) a2).a(jVar);
            if (a3 instanceof org.a.c.b.d) {
                return new c((org.a.c.b.d) a3);
            }
            return null;
        }
        return null;
    }

    private void a(org.a.c.b.j jVar, c cVar) {
        org.a.c.b.d d = this.f4497a.d(org.a.c.b.j.Q);
        org.a.c.b.d dVar = d;
        if (d == null) {
            dVar = new org.a.c.b.d();
            this.f4497a.a(org.a.c.b.j.Q, (org.a.c.b.b) dVar);
        }
        dVar.a(true);
        dVar.a(jVar, (org.a.c.b.b) cVar.f());
    }

    public final void a(c cVar) {
        cVar.f().a(true);
        a(org.a.c.b.j.du, cVar);
    }

    public final void b(c cVar) {
        cVar.f().a(true);
        a(org.a.c.b.j.at, cVar);
    }

    public final org.a.c.b.j o() {
        org.a.c.b.j jVar = (org.a.c.b.j) this.f4497a.a(org.a.c.b.j.dw);
        org.a.c.b.j jVar2 = jVar;
        if (jVar == null) {
            jVar2 = org.a.c.b.j.bB;
        }
        return jVar2;
    }

    public final void a(org.a.c.b.j jVar) {
        this.f4497a.a(org.a.c.b.j.dw, (org.a.c.b.b) jVar);
    }

    public final void b(org.a.c.b.j jVar) {
        this.f4497a.a(org.a.c.b.j.dx, (org.a.c.b.b) jVar);
    }

    public final void e(byte[] bArr) {
        this.f4497a.a(org.a.c.b.j.cR, (org.a.c.b.b) new s(bArr));
    }

    public final byte[] p() {
        byte[] bArr = null;
        s sVar = (s) this.f4497a.a(org.a.c.b.j.cR);
        if (sVar != null) {
            bArr = sVar.c();
        }
        return bArr;
    }

    public final void q() {
        this.f4497a.a(org.a.c.b.j.Q, (org.a.c.b.b) null);
        this.f4497a.a(org.a.c.b.j.dw, (org.a.c.b.b) null);
        this.f4497a.a(org.a.c.b.j.dx, (org.a.c.b.b) null);
    }
}
