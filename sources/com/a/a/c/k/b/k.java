package com.a.a.c.k.b;

import java.util.Collection;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/a/a/c/k/b/k.class */
public final class k extends b<Collection<?>> {
    @Override // com.a.a.c.k.j
    public final /* synthetic */ boolean a(Object obj) {
        return c((Collection) obj);
    }

    @Override // com.a.a.c.o
    public final /* synthetic */ boolean a(com.a.a.c.aa aaVar, Object obj) {
        return b((Collection<?>) obj);
    }

    public k(com.a.a.c.j jVar, boolean z, com.a.a.c.i.i iVar, com.a.a.c.o<Object> oVar) {
        super((Class<?>) Collection.class, jVar, z, iVar, oVar);
    }

    private k(k kVar, com.a.a.c.c cVar, com.a.a.c.i.i iVar, com.a.a.c.o<?> oVar, Boolean bool) {
        super(kVar, cVar, iVar, oVar, bool);
    }

    @Override // com.a.a.c.k.j
    public final com.a.a.c.k.j<?> b(com.a.a.c.i.i iVar) {
        return new k(this, this.f615b, iVar, this.e, this.c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.b
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public k a(com.a.a.c.c cVar, com.a.a.c.i.i iVar, com.a.a.c.o<?> oVar, Boolean bool) {
        return new k(this, cVar, iVar, oVar, bool);
    }

    private static boolean b(Collection<?> collection) {
        return collection.isEmpty();
    }

    private static boolean c(Collection<?> collection) {
        return collection.size() == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.b, com.a.a.c.k.b.ao, com.a.a.c.o
    public void a(Collection<?> collection, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        int size = collection.size();
        if (size == 1 && ((this.c == null && aaVar.a(com.a.a.c.z.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) || this.c == Boolean.TRUE)) {
            b(collection, hVar, aaVar);
            return;
        }
        hVar.a(collection, size);
        b(collection, hVar, aaVar);
        hVar.h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.b
    public void b(Collection<?> collection, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        hVar.a(collection);
        if (this.e != null) {
            a(collection, hVar, aaVar, this.e);
            return;
        }
        Iterator<?> it = collection.iterator();
        if (!it.hasNext()) {
            return;
        }
        com.a.a.c.k.a.k kVar = this.f;
        com.a.a.c.i.i iVar = this.d;
        int i = 0;
        do {
            try {
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
                i++;
            } catch (Exception e) {
                a(aaVar, e, collection, i);
                return;
            }
        } while (it.hasNext());
    }

    private void a(Collection<?> collection, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.o<Object> oVar) {
        Iterator<?> it = collection.iterator();
        if (it.hasNext()) {
            com.a.a.c.i.i iVar = this.d;
            int i = 0;
            do {
                Object next = it.next();
                if (next == null) {
                    try {
                        aaVar.a(hVar);
                    } catch (Exception e) {
                        a(aaVar, e, collection, i);
                    }
                } else if (iVar == null) {
                    oVar.a(next, hVar, aaVar);
                } else {
                    oVar.a(next, hVar, aaVar, iVar);
                }
                i++;
            } while (it.hasNext());
        }
    }
}
