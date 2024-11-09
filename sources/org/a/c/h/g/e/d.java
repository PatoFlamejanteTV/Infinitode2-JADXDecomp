package org.a.c.h.g.e;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: infinitode-2.jar:org/a/c/h/g/e/d.class */
public final class d implements org.a.c.h.a.c {

    /* renamed from: a, reason: collision with root package name */
    private final org.a.c.h.b f4621a;

    /* renamed from: b, reason: collision with root package name */
    private final org.a.c.b.d f4622b = new org.a.c.b.d();

    static {
        org.a.a.a.c.a(d.class);
    }

    public d(org.a.c.h.b bVar) {
        this.f4621a = bVar;
        this.f4622b.a(org.a.c.b.j.aW, (org.a.c.b.b) new org.a.c.b.a());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final org.a.c.h.b a() {
        return this.f4621a;
    }

    @Override // org.a.c.h.a.c
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public final org.a.c.b.d f() {
        return this.f4622b;
    }

    public final List<j> c() {
        j a2;
        org.a.c.b.a aVar = (org.a.c.b.a) this.f4622b.a(org.a.c.b.j.aW);
        if (aVar == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < aVar.b(); i++) {
            org.a.c.b.d dVar = (org.a.c.b.d) aVar.a(i);
            if (dVar != null && (a2 = j.a(this, dVar, null)) != null) {
                arrayList.add(a2);
            }
        }
        return new org.a.c.h.a.a(arrayList, aVar);
    }

    public final boolean d() {
        return this.f4622b.b(org.a.c.b.j.cq, false);
    }

    public final void a(Boolean bool) {
        this.f4622b.a(org.a.c.b.j.cq, bool.booleanValue());
    }

    public final org.a.c.h.j e() {
        org.a.c.h.j jVar = null;
        org.a.c.b.b a2 = this.f4622b.a(org.a.c.b.j.aK);
        if (a2 instanceof org.a.c.b.d) {
            jVar = new org.a.c.h.j((org.a.c.b.d) a2, this.f4621a.j());
        }
        return jVar;
    }

    public final void a(org.a.c.h.j jVar) {
        this.f4622b.a(org.a.c.b.j.aK, jVar);
    }
}
