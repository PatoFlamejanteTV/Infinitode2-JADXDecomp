package com.a.a.c.k.a;

import com.a.a.c.aa;
import java.util.Iterator;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/k/a/g.class */
public final class g extends com.a.a.c.k.b.b<Iterator<?>> {
    @Override // com.a.a.c.k.j
    public final /* bridge */ /* synthetic */ boolean a(Object obj) {
        return false;
    }

    @Override // com.a.a.c.o
    public final /* bridge */ /* synthetic */ boolean a(aa aaVar, Object obj) {
        return a((Iterator<?>) obj);
    }

    public g(com.a.a.c.j jVar, boolean z, com.a.a.c.i.i iVar) {
        super((Class<?>) Iterator.class, jVar, z, iVar, (com.a.a.c.o<Object>) null);
    }

    private g(g gVar, com.a.a.c.c cVar, com.a.a.c.i.i iVar, com.a.a.c.o<?> oVar, Boolean bool) {
        super(gVar, cVar, iVar, oVar, bool);
    }

    private static boolean a(Iterator<?> it) {
        return !it.hasNext();
    }

    @Override // com.a.a.c.k.j
    public final com.a.a.c.k.j<?> b(com.a.a.c.i.i iVar) {
        return new g(this, this.f615b, iVar, this.e, this.c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.b
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public g a(com.a.a.c.c cVar, com.a.a.c.i.i iVar, com.a.a.c.o<?> oVar, Boolean bool) {
        return new g(this, cVar, iVar, oVar, bool);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.b, com.a.a.c.k.b.ao, com.a.a.c.o
    public void a(Iterator<?> it, com.a.a.b.h hVar, aa aaVar) {
        hVar.b(it);
        b(it, hVar, aaVar);
        hVar.h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.b
    public void b(Iterator<?> it, com.a.a.b.h hVar, aa aaVar) {
        if (!it.hasNext()) {
            return;
        }
        com.a.a.c.o<Object> oVar = this.e;
        if (oVar == null) {
            c(it, hVar, aaVar);
            return;
        }
        com.a.a.c.i.i iVar = this.d;
        do {
            Object next = it.next();
            if (next == null) {
                aaVar.a(hVar);
            } else if (iVar == null) {
                oVar.a(next, hVar, aaVar);
            } else {
                oVar.a(next, hVar, aaVar, iVar);
            }
        } while (it.hasNext());
    }

    private void c(Iterator<?> it, com.a.a.b.h hVar, aa aaVar) {
        com.a.a.c.i.i iVar = this.d;
        k kVar = this.f;
        do {
            Object next = it.next();
            if (next == null) {
                aaVar.a(hVar);
            } else {
                Class<?> cls = next.getClass();
                com.a.a.c.o<Object> a2 = kVar.a(cls);
                com.a.a.c.o<Object> oVar = a2;
                if (a2 == null) {
                    if (this.f614a.s()) {
                        oVar = a(kVar, aaVar.a(this.f614a, cls), aaVar);
                    } else {
                        oVar = a(kVar, cls, aaVar);
                    }
                    kVar = this.f;
                }
                if (iVar == null) {
                    oVar.a(next, hVar, aaVar);
                } else {
                    oVar.a(next, hVar, aaVar, iVar);
                }
            }
        } while (it.hasNext());
    }
}
