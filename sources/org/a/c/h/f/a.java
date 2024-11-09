package org.a.c.h.f;

import org.a.c.b.j;
import org.a.c.b.p;
import org.a.c.h.a.c;
import org.a.c.h.a.i;
import org.a.c.h.b;

/* loaded from: infinitode-2.jar:org/a/c/h/f/a.class */
public class a implements c {

    /* renamed from: a, reason: collision with root package name */
    private final i f4567a;

    /* JADX INFO: Access modifiers changed from: protected */
    public a(p pVar, j jVar) {
        this.f4567a = new i(pVar);
        pVar.a(j.dN, j.ee.a());
        pVar.a(j.dE, jVar.a());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public a(i iVar, j jVar) {
        this.f4567a = iVar;
        iVar.f().a(j.dN, j.ee.a());
        iVar.f().a(j.dE, jVar.a());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public a(b bVar, j jVar) {
        this.f4567a = new i(bVar);
        this.f4567a.f().a(j.dN, j.ee.a());
        this.f4567a.f().a(j.dE, jVar.a());
    }

    @Override // org.a.c.h.a.c
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public final p f() {
        return this.f4567a.f();
    }

    public final i c() {
        return this.f4567a;
    }
}
