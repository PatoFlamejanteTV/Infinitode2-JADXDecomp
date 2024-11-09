package org.a.c.h.f.a;

import java.awt.color.ICC_Profile;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* loaded from: infinitode-2.jar:org/a/c/h/f/a/s.class */
public final class s implements org.a.c.h.a.c {

    /* renamed from: a, reason: collision with root package name */
    private final org.a.c.b.d f4588a = new org.a.c.b.d();

    public s(org.a.c.h.b bVar, InputStream inputStream) {
        this.f4588a.a(org.a.c.b.j.dN, (org.a.c.b.b) org.a.c.b.j.cH);
        this.f4588a.a(org.a.c.b.j.dn, (org.a.c.b.b) org.a.c.b.j.bv);
        this.f4588a.a(org.a.c.b.j.az, a(bVar, inputStream));
    }

    @Override // org.a.c.h.a.c
    public final org.a.c.b.b f() {
        return this.f4588a;
    }

    public final void a(String str) {
        this.f4588a.b(org.a.c.b.j.bI, str);
    }

    public final void b(String str) {
        this.f4588a.b(org.a.c.b.j.cF, str);
    }

    public final void c(String str) {
        this.f4588a.b(org.a.c.b.j.cG, str);
    }

    public final void d(String str) {
        this.f4588a.b(org.a.c.b.j.df, str);
    }

    private static org.a.c.h.a.i a(org.a.c.h.b bVar, InputStream inputStream) {
        ICC_Profile iCC_Profile = ICC_Profile.getInstance(inputStream);
        org.a.c.h.a.i iVar = new org.a.c.h.a.i(bVar, (InputStream) new ByteArrayInputStream(iCC_Profile.getData()), org.a.c.b.j.bc);
        iVar.f().a(org.a.c.b.j.co, iCC_Profile.getNumComponents());
        return iVar;
    }
}
