package com.a.a.c.k.a;

import com.a.a.c.aa;
import com.a.a.c.z;
import java.util.List;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/k/a/e.class */
public final class e extends com.a.a.c.k.b.b<List<?>> {
    @Override // com.a.a.c.k.j
    public final /* synthetic */ boolean a(Object obj) {
        return b((List<?>) obj);
    }

    @Override // com.a.a.c.o
    public final /* bridge */ /* synthetic */ boolean a(aa aaVar, Object obj) {
        return a((List<?>) obj);
    }

    public e(com.a.a.c.j jVar, boolean z, com.a.a.c.i.i iVar, com.a.a.c.o<Object> oVar) {
        super((Class<?>) List.class, jVar, z, iVar, oVar);
    }

    private e(e eVar, com.a.a.c.c cVar, com.a.a.c.i.i iVar, com.a.a.c.o<?> oVar, Boolean bool) {
        super(eVar, cVar, iVar, oVar, bool);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.b
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public e a(com.a.a.c.c cVar, com.a.a.c.i.i iVar, com.a.a.c.o<?> oVar, Boolean bool) {
        return new e(this, cVar, iVar, oVar, bool);
    }

    private static boolean a(List<?> list) {
        return list.isEmpty();
    }

    private static boolean b(List<?> list) {
        return list.size() == 1;
    }

    @Override // com.a.a.c.k.j
    public final com.a.a.c.k.j<?> b(com.a.a.c.i.i iVar) {
        return new e(this, this.f615b, iVar, this.e, this.c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.b, com.a.a.c.k.b.ao, com.a.a.c.o
    public void a(List<?> list, com.a.a.b.h hVar, aa aaVar) {
        int size = list.size();
        if (size == 1 && ((this.c == null && aaVar.a(z.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) || this.c == Boolean.TRUE)) {
            b(list, hVar, aaVar);
            return;
        }
        hVar.a(list, size);
        b(list, hVar, aaVar);
        hVar.h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.b
    public void b(List<?> list, com.a.a.b.h hVar, aa aaVar) {
        if (this.e != null) {
            a(list, hVar, aaVar, this.e);
            return;
        }
        if (this.d != null) {
            c(list, hVar, aaVar);
            return;
        }
        int size = list.size();
        if (size == 0) {
            return;
        }
        int i = 0;
        try {
            k kVar = this.f;
            while (i < size) {
                Object obj = list.get(i);
                if (obj == null) {
                    aaVar.a(hVar);
                } else {
                    Class<?> cls = obj.getClass();
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
                    oVar.a(obj, hVar, aaVar);
                }
                i++;
            }
        } catch (Exception e) {
            a(aaVar, e, list, i);
        }
    }

    private void a(List<?> list, com.a.a.b.h hVar, aa aaVar, com.a.a.c.o<Object> oVar) {
        int size = list.size();
        if (size == 0) {
            return;
        }
        com.a.a.c.i.i iVar = this.d;
        for (int i = 0; i < size; i++) {
            Object obj = list.get(i);
            if (obj == null) {
                try {
                    aaVar.a(hVar);
                } catch (Exception e) {
                    a(aaVar, e, list, i);
                }
            } else if (iVar == null) {
                oVar.a(obj, hVar, aaVar);
            } else {
                oVar.a(obj, hVar, aaVar, iVar);
            }
        }
    }

    private void c(List<?> list, com.a.a.b.h hVar, aa aaVar) {
        int size = list.size();
        if (size == 0) {
            return;
        }
        int i = 0;
        try {
            com.a.a.c.i.i iVar = this.d;
            k kVar = this.f;
            while (i < size) {
                Object obj = list.get(i);
                if (obj == null) {
                    aaVar.a(hVar);
                } else {
                    Class<?> cls = obj.getClass();
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
                    oVar.a(obj, hVar, aaVar, iVar);
                }
                i++;
            }
        } catch (Exception e) {
            a(aaVar, e, list, i);
        }
    }
}
