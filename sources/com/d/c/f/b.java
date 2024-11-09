package com.d.c.f;

import com.d.c.d.j;

/* loaded from: infinitode-2.jar:com/d/c/f/b.class */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private boolean f1084a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f1085b;
    private float c;
    private float d;

    public b() {
        this.f1084a = false;
        this.f1085b = false;
    }

    public b(float f, float f2) {
        this.f1084a = false;
        this.f1085b = false;
        this.c = f;
        this.d = f2;
        this.f1085b = false;
        this.f1085b = false;
    }

    public b(com.d.c.a.a aVar, c cVar, d dVar) {
        j jVar;
        this.f1084a = false;
        this.f1085b = false;
        g i = cVar.i(aVar);
        if (!(i instanceof com.d.c.f.a.f)) {
            if (i instanceof com.d.c.f.a.e) {
                com.d.c.f.a.e eVar = (com.d.c.f.a.e) i;
                if (!eVar.a().contains("%")) {
                    float a2 = (int) eVar.a(aVar, 0.0f, dVar);
                    this.d = a2;
                    this.c = a2;
                    return;
                } else {
                    this.f1085b = true;
                    this.f1084a = true;
                    float b2 = i.b() / 100.0f;
                    this.d = b2;
                    this.c = b2;
                    return;
                }
            }
            return;
        }
        com.d.c.f.a.f fVar = (com.d.c.f.a.f) i;
        j jVar2 = fVar.j().get(0);
        if (fVar.j().size() > 1) {
            jVar = fVar.j().get(1);
        } else {
            jVar = jVar2;
        }
        if (aVar.equals(com.d.c.a.a.aO) || aVar.equals(com.d.c.a.a.aQ)) {
            b(aVar, cVar, jVar2, dVar);
            a(aVar, cVar, jVar, dVar);
        } else {
            a(aVar, cVar, jVar2, dVar);
            b(aVar, cVar, jVar, dVar);
        }
    }

    private void a(com.d.c.a.a aVar, c cVar, j jVar, d dVar) {
        if (jVar.a() == 2) {
            this.f1084a = true;
            this.c = jVar.f() / 100.0f;
        } else {
            this.c = (int) com.d.c.f.a.e.a(cVar, aVar, jVar.d(), jVar.f(), jVar.a(), 0.0f, dVar);
        }
    }

    private void b(com.d.c.a.a aVar, c cVar, j jVar, d dVar) {
        if (jVar.a() == 2) {
            jVar.f();
            this.f1085b = true;
            this.d = jVar.f() / 100.0f;
            return;
        }
        this.d = (int) com.d.c.f.a.e.a(cVar, aVar, jVar.d(), jVar.f(), jVar.a(), 0.0f, dVar);
    }

    public final boolean a() {
        return this.c > 0.0f || this.d > 0.0f;
    }

    public final float a(float f) {
        if (this.f1084a) {
            return f * this.c;
        }
        if (this.c > f) {
            return f;
        }
        return this.c;
    }

    public final float b(float f) {
        if (this.f1085b) {
            return f * this.d;
        }
        if (this.d > f) {
            return f;
        }
        return this.d;
    }

    public final float b() {
        return this.c;
    }

    public final float c() {
        return this.d;
    }
}
