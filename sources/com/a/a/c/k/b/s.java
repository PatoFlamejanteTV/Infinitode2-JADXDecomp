package com.a.a.c.k.b;

import java.util.Iterator;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/k/b/s.class */
public final class s extends b<Iterable<?>> {
    @Override // com.a.a.c.k.j
    public final /* synthetic */ boolean a(Object obj) {
        return b((Iterable<?>) obj);
    }

    @Override // com.a.a.c.o
    public final /* bridge */ /* synthetic */ boolean a(com.a.a.c.aa aaVar, Object obj) {
        return a((Iterable<?>) obj);
    }

    public s(com.a.a.c.j jVar, boolean z, com.a.a.c.i.i iVar) {
        super((Class<?>) Iterable.class, jVar, z, iVar, (com.a.a.c.o<Object>) null);
    }

    private s(s sVar, com.a.a.c.c cVar, com.a.a.c.i.i iVar, com.a.a.c.o<?> oVar, Boolean bool) {
        super(sVar, cVar, iVar, oVar, bool);
    }

    @Override // com.a.a.c.k.j
    public final com.a.a.c.k.j<?> b(com.a.a.c.i.i iVar) {
        return new s(this, this.f615b, iVar, this.e, this.c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.b
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public s a(com.a.a.c.c cVar, com.a.a.c.i.i iVar, com.a.a.c.o<?> oVar, Boolean bool) {
        return new s(this, cVar, iVar, oVar, bool);
    }

    private static boolean a(Iterable<?> iterable) {
        return !iterable.iterator().hasNext();
    }

    private static boolean b(Iterable<?> iterable) {
        if (iterable != null) {
            Iterator<?> it = iterable.iterator();
            if (it.hasNext()) {
                it.next();
                if (!it.hasNext()) {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.b, com.a.a.c.k.b.ao, com.a.a.c.o
    public void a(Iterable<?> iterable, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        if (((this.c == null && aaVar.a(com.a.a.c.z.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) || this.c == Boolean.TRUE) && b(iterable)) {
            b(iterable, hVar, aaVar);
            return;
        }
        hVar.b(iterable);
        b(iterable, hVar, aaVar);
        hVar.h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.b
    public void b(Iterable<?> iterable, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        Iterator<?> it = iterable.iterator();
        if (it.hasNext()) {
            com.a.a.c.i.i iVar = this.d;
            com.a.a.c.o<Object> oVar = null;
            Class<?> cls = null;
            do {
                Object next = it.next();
                if (next == null) {
                    aaVar.a(hVar);
                } else {
                    com.a.a.c.o<Object> oVar2 = this.e;
                    com.a.a.c.o<Object> oVar3 = oVar2;
                    if (oVar2 == null) {
                        Class<?> cls2 = next.getClass();
                        if (cls2 == cls) {
                            oVar3 = oVar;
                        } else {
                            com.a.a.c.o<Object> a2 = aaVar.a(cls2, this.f615b);
                            oVar3 = a2;
                            oVar = a2;
                            cls = cls2;
                        }
                    }
                    if (iVar == null) {
                        oVar3.a(next, hVar, aaVar);
                    } else {
                        oVar3.a(next, hVar, aaVar, iVar);
                    }
                }
            } while (it.hasNext());
        }
    }
}
