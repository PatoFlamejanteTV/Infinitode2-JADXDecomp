package com.a.a.c.k.b;

import com.a.a.a.l;
import com.a.a.c.k.a.k;
import java.util.Objects;

/* loaded from: infinitode-2.jar:com/a/a/c/k/b/b.class */
public abstract class b<T> extends com.a.a.c.k.j<T> implements com.a.a.c.k.k {

    /* renamed from: a, reason: collision with root package name */
    protected final com.a.a.c.j f614a;

    /* renamed from: b, reason: collision with root package name */
    protected final com.a.a.c.c f615b;
    private boolean g;
    protected final Boolean c;
    protected final com.a.a.c.i.i d;
    protected final com.a.a.c.o<Object> e;
    protected com.a.a.c.k.a.k f;

    public abstract b<T> a(com.a.a.c.c cVar, com.a.a.c.i.i iVar, com.a.a.c.o<?> oVar, Boolean bool);

    protected abstract void b(T t, com.a.a.b.h hVar, com.a.a.c.aa aaVar);

    /* JADX INFO: Access modifiers changed from: protected */
    public b(Class<?> cls, com.a.a.c.j jVar, boolean z, com.a.a.c.i.i iVar, com.a.a.c.o<Object> oVar) {
        this(cls, jVar, z, iVar, null, oVar, null);
    }

    private b(Class<?> cls, com.a.a.c.j jVar, boolean z, com.a.a.c.i.i iVar, com.a.a.c.c cVar, com.a.a.c.o<?> oVar, Boolean bool) {
        super(cls, false);
        this.f614a = jVar;
        this.g = z || (jVar != null && jVar.m());
        this.d = iVar;
        this.f615b = cVar;
        this.e = oVar;
        this.f = com.a.a.c.k.a.k.a();
        this.c = bool;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public b(b<?> bVar, com.a.a.c.c cVar, com.a.a.c.i.i iVar, com.a.a.c.o<?> oVar, Boolean bool) {
        super(bVar);
        this.f614a = bVar.f614a;
        this.g = bVar.g;
        this.d = iVar;
        this.f615b = cVar;
        this.e = oVar;
        this.f = com.a.a.c.k.a.k.a();
        this.c = bool;
    }

    @Override // com.a.a.c.k.k
    public final com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.c cVar) {
        Object p;
        com.a.a.c.i.i iVar = this.d;
        com.a.a.c.i.i iVar2 = iVar;
        if (iVar != null) {
            iVar2 = iVar2.a(cVar);
        }
        com.a.a.c.o<Object> oVar = null;
        Boolean bool = null;
        if (cVar != null) {
            com.a.a.c.a d = aaVar.d();
            com.a.a.c.f.j e = cVar.e();
            if (e != null && (p = d.p(e)) != null) {
                oVar = aaVar.b(e, p);
            }
        }
        l.d a2 = a(aaVar, cVar, (Class<?>) a());
        if (a2 != null) {
            bool = a2.a(l.a.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
        }
        if (oVar == null) {
            oVar = this.e;
        }
        com.a.a.c.o<?> a3 = a(aaVar, cVar, (com.a.a.c.o<?>) oVar);
        com.a.a.c.o<?> oVar2 = a3;
        if (a3 == null && this.f614a != null && this.g && !this.f614a.q()) {
            oVar2 = aaVar.c(this.f614a, cVar);
        }
        if (oVar2 != this.e || cVar != this.f615b || this.d != iVar2 || !Objects.equals(this.c, bool)) {
            return a(cVar, iVar2, oVar2, bool);
        }
        return this;
    }

    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    public void a(T t, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        if (aaVar.a(com.a.a.c.z.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) && a((b<T>) t)) {
            b((b<T>) t, hVar, aaVar);
            return;
        }
        hVar.b(t);
        b((b<T>) t, hVar, aaVar);
        hVar.h();
    }

    @Override // com.a.a.c.o
    public final void a(T t, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.i.i iVar) {
        com.a.a.b.f.a a2 = iVar.a(hVar, iVar.a(t, com.a.a.b.o.START_ARRAY));
        hVar.a(t);
        b((b<T>) t, hVar, aaVar);
        iVar.b(hVar, a2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.o<Object> a(com.a.a.c.k.a.k kVar, Class<?> cls, com.a.a.c.aa aaVar) {
        k.d b2 = kVar.b(cls, aaVar, this.f615b);
        if (kVar != b2.f568b) {
            this.f = b2.f568b;
        }
        return b2.f567a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.o<Object> a(com.a.a.c.k.a.k kVar, com.a.a.c.j jVar, com.a.a.c.aa aaVar) {
        k.d b2 = kVar.b(jVar, aaVar, this.f615b);
        if (kVar != b2.f568b) {
            this.f = b2.f568b;
        }
        return b2.f567a;
    }
}
