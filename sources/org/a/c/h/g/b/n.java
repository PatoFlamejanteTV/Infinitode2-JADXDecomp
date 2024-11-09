package org.a.c.h.g.b;

/* loaded from: infinitode-2.jar:org/a/c/h/g/b/n.class */
public final class n implements org.a.c.h.a.c {

    /* renamed from: a, reason: collision with root package name */
    private final org.a.c.b.d f4610a;

    public n(org.a.c.b.d dVar) {
        this.f4610a = dVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // org.a.c.h.a.c
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public org.a.c.b.d f() {
        return this.f4610a;
    }

    public final int a() {
        return f().b(org.a.c.b.j.da, 0);
    }

    public final org.a.c.h.f.a.e b() {
        return a(org.a.c.b.j.x);
    }

    public final org.a.c.h.f.a.e c() {
        return a(org.a.c.b.j.y);
    }

    public final void a(String str) {
        f().b(org.a.c.b.j.J, str);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0017. Please report as an issue. */
    private org.a.c.h.f.a.e a(org.a.c.b.j jVar) {
        org.a.c.h.f.a.h hVar;
        org.a.c.b.b n = f().n(jVar);
        if (n instanceof org.a.c.b.a) {
            switch (((org.a.c.b.a) n).b()) {
                case 1:
                    hVar = org.a.c.h.f.a.i.f4574a;
                    return new org.a.c.h.f.a.e((org.a.c.b.a) n, hVar);
                case 2:
                default:
                    return null;
                case 3:
                    hVar = org.a.c.h.f.a.m.f4580a;
                    return new org.a.c.h.f.a.e((org.a.c.b.a) n, hVar);
                case 4:
                    hVar = org.a.c.h.f.a.g.f4572a;
                    return new org.a.c.h.f.a.e((org.a.c.b.a) n, hVar);
            }
        }
        return null;
    }
}
