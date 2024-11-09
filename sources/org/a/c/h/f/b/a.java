package org.a.c.h.f.b;

import java.awt.geom.AffineTransform;
import java.io.InputStream;
import org.a.c.b.d;
import org.a.c.b.f;
import org.a.c.b.j;
import org.a.c.b.p;
import org.a.c.h.a.h;
import org.a.c.h.a.i;
import org.a.c.h.b;
import org.a.c.h.k;

/* loaded from: infinitode-2.jar:org/a/c/h/f/b/a.class */
public class a extends org.a.c.h.f.a implements org.a.c.a.a {

    /* renamed from: a, reason: collision with root package name */
    private final k f4593a;

    public a(i iVar) {
        super(iVar, j.bo);
        this.f4593a = null;
    }

    public a(p pVar) {
        super(pVar, j.bo);
        this.f4593a = null;
    }

    public a(b bVar) {
        super(bVar, j.bo);
        this.f4593a = null;
    }

    public final void a(int i) {
        f().a(j.bp, 1);
    }

    public final i d() {
        return new i(f());
    }

    @Override // org.a.c.a.a
    public final InputStream a() {
        return f().k();
    }

    public final org.a.c.h.j e() {
        d d = f().d(j.dg);
        if (d != null) {
            return new org.a.c.h.j(d, this.f4593a);
        }
        if (f().o(j.dg)) {
            return new org.a.c.h.j();
        }
        return null;
    }

    public final void a(org.a.c.h.j jVar) {
        f().a(j.dg, jVar);
    }

    public final h g() {
        h hVar = null;
        org.a.c.b.a aVar = (org.a.c.b.a) f().a(j.w);
        if (aVar != null) {
            hVar = new h(aVar);
        }
        return hVar;
    }

    public final void a(h hVar) {
        f().a(j.w, (org.a.c.b.b) hVar.b());
    }

    public final org.a.c.i.d h() {
        return org.a.c.i.d.a(f().a(j.cf));
    }

    public final void a(AffineTransform affineTransform) {
        org.a.c.b.a aVar = new org.a.c.b.a();
        double[] dArr = new double[6];
        affineTransform.getMatrix(dArr);
        for (int i = 0; i < 6; i++) {
            aVar.a((org.a.c.b.b) new f((float) dArr[i]));
        }
        f().a(j.cf, (org.a.c.b.b) aVar);
    }
}
