package com.a.a.c.k.b;

import com.a.a.a.l;
import com.a.a.c.k.a.k;
import java.util.Objects;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/k/b/ac.class */
public final class ac extends a<Object[]> {

    /* renamed from: a, reason: collision with root package name */
    private boolean f597a;
    private com.a.a.c.j d;
    private com.a.a.c.i.i e;
    private com.a.a.c.o<Object> f;
    private com.a.a.c.k.a.k g;

    @Override // com.a.a.c.k.j
    public final /* synthetic */ boolean a(Object obj) {
        return b((Object[]) obj);
    }

    @Override // com.a.a.c.o
    public final /* bridge */ /* synthetic */ boolean a(com.a.a.c.aa aaVar, Object obj) {
        return a((Object[]) obj);
    }

    public ac(com.a.a.c.j jVar, boolean z, com.a.a.c.i.i iVar, com.a.a.c.o<Object> oVar) {
        super(Object[].class);
        this.d = jVar;
        this.f597a = z;
        this.e = iVar;
        this.g = com.a.a.c.k.a.k.a();
        this.f = oVar;
    }

    private ac(ac acVar, com.a.a.c.c cVar, com.a.a.c.i.i iVar, com.a.a.c.o<?> oVar, Boolean bool) {
        super(acVar, cVar, bool);
        this.d = acVar.d;
        this.e = iVar;
        this.f597a = acVar.f597a;
        this.g = com.a.a.c.k.a.k.a();
        this.f = oVar;
    }

    @Override // com.a.a.c.k.b.a
    public final com.a.a.c.o<?> a(com.a.a.c.c cVar, Boolean bool) {
        return new ac(this, cVar, this.e, this.f, bool);
    }

    @Override // com.a.a.c.k.j
    public final com.a.a.c.k.j<?> b(com.a.a.c.i.i iVar) {
        return new ac(this.d, this.f597a, iVar, this.f);
    }

    private ac a(com.a.a.c.c cVar, com.a.a.c.i.i iVar, com.a.a.c.o<?> oVar, Boolean bool) {
        if (this.f590b == cVar && oVar == this.f && this.e == iVar && Objects.equals(this.c, bool)) {
            return this;
        }
        return new ac(this, cVar, iVar, oVar, bool);
    }

    @Override // com.a.a.c.k.b.a, com.a.a.c.k.k
    public final com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.c cVar) {
        Object p;
        com.a.a.c.i.i iVar = this.e;
        com.a.a.c.i.i iVar2 = iVar;
        if (iVar != null) {
            iVar2 = iVar2.a(cVar);
        }
        com.a.a.c.o<Object> oVar = null;
        Boolean bool = null;
        if (cVar != null) {
            com.a.a.c.f.j e = cVar.e();
            com.a.a.c.a d = aaVar.d();
            if (e != null && (p = d.p(e)) != null) {
                oVar = aaVar.b(e, p);
            }
        }
        l.d a2 = a(aaVar, cVar, (Class<?>) a());
        if (a2 != null) {
            bool = a2.a(l.a.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
        }
        if (oVar == null) {
            oVar = this.f;
        }
        com.a.a.c.o<?> a3 = a(aaVar, cVar, (com.a.a.c.o<?>) oVar);
        com.a.a.c.o<?> oVar2 = a3;
        if (a3 == null && this.d != null && this.f597a && !this.d.q()) {
            oVar2 = aaVar.c(this.d, cVar);
        }
        return a(cVar, iVar2, oVar2, bool);
    }

    private static boolean a(Object[] objArr) {
        return objArr.length == 0;
    }

    private static boolean b(Object[] objArr) {
        return objArr.length == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.a, com.a.a.c.k.b.ao, com.a.a.c.o
    public void a(Object[] objArr, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        int length = objArr.length;
        if (length == 1 && ((this.c == null && aaVar.a(com.a.a.c.z.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) || this.c == Boolean.TRUE)) {
            b(objArr, hVar, aaVar);
            return;
        }
        hVar.a(objArr, length);
        b(objArr, hVar, aaVar);
        hVar.h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.a
    public void b(Object[] objArr, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        int length = objArr.length;
        if (length == 0) {
            return;
        }
        if (this.f != null) {
            a(objArr, hVar, aaVar, this.f);
            return;
        }
        if (this.e != null) {
            c(objArr, hVar, aaVar);
            return;
        }
        int i = 0;
        Object obj = null;
        try {
            com.a.a.c.k.a.k kVar = this.g;
            while (i < length) {
                Object obj2 = objArr[i];
                obj = obj2;
                if (obj2 == null) {
                    aaVar.a(hVar);
                } else {
                    Class<?> cls = obj.getClass();
                    com.a.a.c.o<Object> a2 = kVar.a(cls);
                    com.a.a.c.o<Object> oVar = a2;
                    if (a2 == null) {
                        if (this.d.s()) {
                            oVar = a(kVar, aaVar.a(this.d, cls), aaVar);
                        } else {
                            oVar = a(kVar, cls, aaVar);
                        }
                    }
                    oVar.a(obj, hVar, aaVar);
                }
                i++;
            }
        } catch (Exception e) {
            a(aaVar, e, obj, i);
        }
    }

    private void a(Object[] objArr, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.o<Object> oVar) {
        int length = objArr.length;
        com.a.a.c.i.i iVar = this.e;
        Object obj = null;
        for (int i = 0; i < length; i++) {
            try {
                Object obj2 = objArr[i];
                obj = obj2;
                if (obj2 == null) {
                    aaVar.a(hVar);
                } else if (iVar == null) {
                    oVar.a(obj, hVar, aaVar);
                } else {
                    oVar.a(obj, hVar, aaVar, iVar);
                }
            } catch (Exception e) {
                a(aaVar, e, obj, i);
                return;
            }
        }
    }

    private void c(Object[] objArr, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        int length = objArr.length;
        com.a.a.c.i.i iVar = this.e;
        int i = 0;
        Object obj = null;
        try {
            com.a.a.c.k.a.k kVar = this.g;
            while (i < length) {
                Object obj2 = objArr[i];
                obj = obj2;
                if (obj2 == null) {
                    aaVar.a(hVar);
                } else {
                    Class<?> cls = obj.getClass();
                    com.a.a.c.o<Object> a2 = kVar.a(cls);
                    com.a.a.c.o<Object> oVar = a2;
                    if (a2 == null) {
                        oVar = a(kVar, cls, aaVar);
                    }
                    oVar.a(obj, hVar, aaVar, iVar);
                }
                i++;
            }
        } catch (Exception e) {
            a(aaVar, e, obj, i);
        }
    }

    private com.a.a.c.o<Object> a(com.a.a.c.k.a.k kVar, Class<?> cls, com.a.a.c.aa aaVar) {
        k.d b2 = kVar.b(cls, aaVar, this.f590b);
        if (kVar != b2.f568b) {
            this.g = b2.f568b;
        }
        return b2.f567a;
    }

    private com.a.a.c.o<Object> a(com.a.a.c.k.a.k kVar, com.a.a.c.j jVar, com.a.a.c.aa aaVar) {
        k.d b2 = kVar.b(jVar, aaVar, this.f590b);
        if (kVar != b2.f568b) {
            this.g = b2.f568b;
        }
        return b2.f567a;
    }
}
