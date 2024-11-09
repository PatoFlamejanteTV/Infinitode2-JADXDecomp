package com.a.a.c.k.b;

import com.a.a.a.am;
import com.a.a.a.l;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/a/a/c/k/b/d.class */
public abstract class d extends ao<Object> implements com.a.a.c.k.k, com.a.a.c.k.q {

    /* renamed from: b, reason: collision with root package name */
    protected static final com.a.a.c.k.e[] f616b;

    /* renamed from: a, reason: collision with root package name */
    private com.a.a.c.j f617a;
    protected final com.a.a.c.k.e[] c;
    protected final com.a.a.c.k.e[] d;
    protected final com.a.a.c.k.a e;
    protected final Object f;
    private com.a.a.c.f.j i;
    protected final com.a.a.c.k.a.m g;
    private l.c j;

    public abstract d a(com.a.a.c.k.a.m mVar);

    protected abstract d a(Set<String> set, Set<String> set2);

    protected abstract d d();

    public abstract d a(Object obj);

    protected abstract d a(com.a.a.c.k.e[] eVarArr, com.a.a.c.k.e[] eVarArr2);

    static {
        new com.a.a.c.w("#object-ref");
        f616b = new com.a.a.c.k.e[0];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public d(com.a.a.c.j jVar, com.a.a.c.k.g gVar, com.a.a.c.k.e[] eVarArr, com.a.a.c.k.e[] eVarArr2) {
        super(jVar);
        this.f617a = jVar;
        this.c = eVarArr;
        this.d = eVarArr2;
        if (gVar == null) {
            this.i = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.j = null;
            return;
        }
        this.i = gVar.e();
        this.e = gVar.c();
        this.f = gVar.d();
        this.g = gVar.f();
        this.j = gVar.a().a((l.d) null).c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public d(d dVar, com.a.a.c.k.e[] eVarArr, com.a.a.c.k.e[] eVarArr2) {
        super(dVar.h);
        this.f617a = dVar.f617a;
        this.c = eVarArr;
        this.d = eVarArr2;
        this.i = dVar.i;
        this.e = dVar.e;
        this.g = dVar.g;
        this.f = dVar.f;
        this.j = dVar.j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public d(d dVar, com.a.a.c.k.a.m mVar) {
        this(dVar, mVar, dVar.f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public d(d dVar, com.a.a.c.k.a.m mVar, Object obj) {
        super(dVar.h);
        this.f617a = dVar.f617a;
        this.c = dVar.c;
        this.d = dVar.d;
        this.i = dVar.i;
        this.e = dVar.e;
        this.g = mVar;
        this.f = obj;
        this.j = dVar.j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public d(d dVar, Set<String> set, Set<String> set2) {
        super(dVar.h);
        this.f617a = dVar.f617a;
        com.a.a.c.k.e[] eVarArr = dVar.c;
        com.a.a.c.k.e[] eVarArr2 = dVar.d;
        int length = eVarArr.length;
        ArrayList arrayList = new ArrayList(length);
        ArrayList arrayList2 = eVarArr2 == null ? null : new ArrayList(length);
        for (int i = 0; i < length; i++) {
            com.a.a.c.k.e eVar = eVarArr[i];
            if (!com.a.a.c.m.n.a(eVar.a(), set, set2)) {
                arrayList.add(eVar);
                if (eVarArr2 != null) {
                    arrayList2.add(eVarArr2[i]);
                }
            }
        }
        this.c = (com.a.a.c.k.e[]) arrayList.toArray(new com.a.a.c.k.e[arrayList.size()]);
        this.d = arrayList2 == null ? null : (com.a.a.c.k.e[]) arrayList2.toArray(new com.a.a.c.k.e[arrayList2.size()]);
        this.i = dVar.i;
        this.e = dVar.e;
        this.g = dVar.g;
        this.f = dVar.f;
        this.j = dVar.j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public d(d dVar, com.a.a.c.m.r rVar) {
        this(dVar, a(dVar.c, rVar), a(dVar.d, rVar));
    }

    private static final com.a.a.c.k.e[] a(com.a.a.c.k.e[] eVarArr, com.a.a.c.m.r rVar) {
        if (eVarArr == null || eVarArr.length == 0 || rVar == null || rVar == com.a.a.c.m.r.f742a) {
            return eVarArr;
        }
        int length = eVarArr.length;
        com.a.a.c.k.e[] eVarArr2 = new com.a.a.c.k.e[length];
        for (int i = 0; i < length; i++) {
            com.a.a.c.k.e eVar = eVarArr[i];
            if (eVar != null) {
                eVarArr2[i] = eVar.a(rVar);
            }
        }
        return eVarArr2;
    }

    @Override // com.a.a.c.k.q
    public final void a(com.a.a.c.aa aaVar) {
        com.a.a.c.k.e eVar;
        com.a.a.c.i.i iVar;
        com.a.a.c.o<Object> m;
        com.a.a.c.k.e eVar2;
        int length = this.d == null ? 0 : this.d.length;
        int length2 = this.c.length;
        for (int i = 0; i < length2; i++) {
            com.a.a.c.k.e eVar3 = this.c[i];
            if (!eVar3.i() && !eVar3.g() && (m = aaVar.m()) != null) {
                eVar3.b(m);
                if (i < length && (eVar2 = this.d[i]) != null) {
                    eVar2.b(m);
                }
            }
            if (!eVar3.f()) {
                com.a.a.c.o<Object> a2 = a(aaVar, eVar3);
                com.a.a.c.o<Object> oVar = a2;
                if (a2 == null) {
                    com.a.a.c.j j = eVar3.j();
                    com.a.a.c.j jVar = j;
                    if (j == null) {
                        com.a.a.c.j c = eVar3.c();
                        jVar = c;
                        if (!c.m()) {
                            if (jVar.n() || jVar.w() > 0) {
                                eVar3.a(jVar);
                            }
                        }
                    }
                    oVar = aaVar.a(jVar, (com.a.a.c.c) eVar3);
                    if (jVar.n() && (iVar = (com.a.a.c.i.i) jVar.u().B()) != null && (oVar instanceof com.a.a.c.k.j)) {
                        oVar = ((com.a.a.c.k.j) oVar).a(iVar);
                    }
                }
                if (i < length && (eVar = this.d[i]) != null) {
                    eVar.a(oVar);
                } else {
                    eVar3.a(oVar);
                }
            }
        }
        if (this.e != null) {
            this.e.a(aaVar);
        }
    }

    private static com.a.a.c.o<Object> a(com.a.a.c.aa aaVar, com.a.a.c.k.e eVar) {
        com.a.a.c.f.j e;
        Object s;
        com.a.a.c.a d = aaVar.d();
        if (d != null && (e = eVar.e()) != null && (s = d.s(e)) != null) {
            com.a.a.c.m.k<Object, Object> a2 = aaVar.a((com.a.a.c.f.b) eVar.e(), s);
            aaVar.b();
            com.a.a.c.j b2 = a2.b();
            return new aj(a2, b2, b2.q() ? null : aaVar.a(b2, (com.a.a.c.c) eVar));
        }
        return null;
    }

    @Override // com.a.a.c.k.k
    public final com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.c cVar) {
        com.a.a.c.k.a.m a2;
        com.a.a.c.k.e[] eVarArr;
        com.a.a.c.f.ad a3;
        com.a.a.c.a d = aaVar.d();
        com.a.a.c.f.j e = (cVar == null || d == null) ? null : cVar.e();
        com.a.a.c.y a4 = aaVar.a();
        l.d a5 = a(aaVar, cVar, (Class<?>) this.h);
        l.c cVar2 = null;
        if (a5 != null && a5.g()) {
            l.c c = a5.c();
            cVar2 = c;
            if (c != l.c.ANY && cVar2 != this.j) {
                if (this.f617a.h()) {
                    switch (e.f618a[cVar2.ordinal()]) {
                        case 1:
                        case 2:
                        case 3:
                            a4.d(this.f617a);
                            return aaVar.a((com.a.a.c.o<?>) n.a(this.f617a.b(), aaVar.a(), a5), cVar);
                    }
                }
                if (cVar2 == l.c.NATURAL && ((!this.f617a.p() || !Map.class.isAssignableFrom(this.h)) && Map.Entry.class.isAssignableFrom(this.h))) {
                    com.a.a.c.j d2 = this.f617a.d(Map.Entry.class);
                    return aaVar.a((com.a.a.c.o<?>) new com.a.a.c.k.a.h(this.f617a, d2.b(0), d2.b(1), false, null, cVar), cVar);
                }
            }
        }
        com.a.a.c.k.a.m mVar = this.g;
        int i = 0;
        Set<String> set = null;
        Set<String> set2 = null;
        Object obj = null;
        if (e != null) {
            set = d.b((com.a.a.c.f.b) e).b();
            set2 = d.c((com.a.a.c.f.b) e).b();
            com.a.a.c.f.ad a6 = d.a((com.a.a.c.f.b) e);
            if (a6 == null) {
                if (mVar != null && (a3 = d.a(e, (com.a.a.c.f.ad) null)) != null) {
                    mVar = this.g.a(a3.f());
                }
            } else {
                com.a.a.c.f.ad a7 = d.a(e, a6);
                Class<? extends com.a.a.a.al<?>> d3 = a7.d();
                com.a.a.c.j a8 = aaVar.a((Type) d3);
                aaVar.b();
                com.a.a.c.j jVar = com.a.a.c.l.o.c(a8, com.a.a.a.al.class)[0];
                if (d3 == am.c.class) {
                    String b2 = a7.b().b();
                    int i2 = 0;
                    int length = this.c.length;
                    while (true) {
                        if (i2 == length) {
                            aaVar.a(this.f617a, String.format("Invalid Object Id definition for %s: cannot find property with name %s", com.a.a.c.m.i.g((Class<?>) a()), com.a.a.c.m.i.b(b2)));
                        }
                        com.a.a.c.k.e eVar = this.c[i2];
                        if (!b2.equals(eVar.a())) {
                            i2++;
                        } else {
                            i = i2;
                            mVar = com.a.a.c.k.a.m.a(eVar.c(), (com.a.a.c.w) null, new com.a.a.c.k.a.j(a7, eVar), a7.f());
                        }
                    }
                } else {
                    mVar = com.a.a.c.k.a.m.a(jVar, a7.b(), aaVar.a((com.a.a.c.f.b) e, a7), a7.f());
                }
            }
            Object d4 = d.d((com.a.a.c.f.b) e);
            if (d4 != null && (this.f == null || !d4.equals(this.f))) {
                obj = d4;
            }
        }
        d dVar = this;
        if (i > 0) {
            com.a.a.c.k.e[] eVarArr2 = (com.a.a.c.k.e[]) Arrays.copyOf(this.c, this.c.length);
            com.a.a.c.k.e eVar2 = eVarArr2[i];
            System.arraycopy(eVarArr2, 0, eVarArr2, 1, i);
            eVarArr2[0] = eVar2;
            if (this.d == null) {
                eVarArr = null;
            } else {
                com.a.a.c.k.e[] eVarArr3 = (com.a.a.c.k.e[]) Arrays.copyOf(this.d, this.d.length);
                eVarArr = eVarArr3;
                com.a.a.c.k.e eVar3 = eVarArr3[i];
                System.arraycopy(eVarArr, 0, eVarArr, 1, i);
                eVarArr[0] = eVar3;
            }
            dVar = dVar.a(eVarArr2, eVarArr);
        }
        if (mVar != null && (a2 = mVar.a(aaVar.a(mVar.f576a, cVar))) != this.g) {
            dVar = dVar.a(a2);
        }
        if ((set != null && !set.isEmpty()) || set2 != null) {
            dVar = dVar.a(set, set2);
        }
        if (obj != null) {
            dVar = dVar.a(obj);
        }
        if (cVar2 == null) {
            cVar2 = this.j;
        }
        if (cVar2 == l.c.ARRAY) {
            return dVar.d();
        }
        return dVar;
    }

    @Override // com.a.a.c.o
    public final boolean b() {
        return this.g != null;
    }

    @Override // com.a.a.c.o
    public void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.i.i iVar) {
        if (this.g != null) {
            b(obj, hVar, aaVar, iVar);
            return;
        }
        com.a.a.b.f.a a2 = a(iVar, obj, com.a.a.b.o.START_OBJECT);
        iVar.a(hVar, a2);
        hVar.a(obj);
        if (this.f != null) {
            c(obj, hVar, aaVar);
        } else {
            b(obj, hVar, aaVar);
        }
        iVar.b(hVar, a2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar, boolean z) {
        com.a.a.c.k.a.m mVar = this.g;
        com.a.a.c.k.a.v a2 = aaVar.a(obj, mVar.c);
        if (a2.a(hVar, aaVar, mVar)) {
            return;
        }
        Object a3 = a2.a(obj);
        if (mVar.e) {
            mVar.d.a(a3, hVar, aaVar);
            return;
        }
        if (z) {
            hVar.c(obj);
        }
        a2.b(hVar, aaVar, mVar);
        if (this.f != null) {
            c(obj, hVar, aaVar);
        } else {
            b(obj, hVar, aaVar);
        }
        if (z) {
            hVar.j();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void b(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.i.i iVar) {
        com.a.a.c.k.a.m mVar = this.g;
        com.a.a.c.k.a.v a2 = aaVar.a(obj, mVar.c);
        if (a2.a(hVar, aaVar, mVar)) {
            return;
        }
        Object a3 = a2.a(obj);
        if (mVar.e) {
            mVar.d.a(a3, hVar, aaVar);
        } else {
            a(obj, hVar, aaVar, iVar, a2);
        }
    }

    private void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.i.i iVar, com.a.a.c.k.a.v vVar) {
        com.a.a.c.k.a.m mVar = this.g;
        com.a.a.b.f.a a2 = a(iVar, obj, com.a.a.b.o.START_OBJECT);
        iVar.a(hVar, a2);
        hVar.a(obj);
        vVar.b(hVar, aaVar, mVar);
        if (this.f != null) {
            c(obj, hVar, aaVar);
        } else {
            b(obj, hVar, aaVar);
        }
        iVar.b(hVar, a2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.b.f.a a(com.a.a.c.i.i iVar, Object obj, com.a.a.b.o oVar) {
        if (this.i == null) {
            return iVar.a(obj, oVar);
        }
        Object b2 = this.i.b(obj);
        Object obj2 = b2;
        if (b2 == null) {
            obj2 = "";
        }
        return iVar.a(obj, oVar, obj2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void b(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        com.a.a.c.k.e[] eVarArr;
        if (this.d != null && aaVar.e() != null) {
            eVarArr = this.d;
        } else {
            eVarArr = this.c;
        }
        int i = 0;
        try {
            int length = eVarArr.length;
            while (i < length) {
                com.a.a.c.k.e eVar = eVarArr[i];
                if (eVar != null) {
                    eVar.a(obj, hVar, aaVar);
                }
                i++;
            }
            if (this.e != null) {
                this.e.a(obj, hVar, aaVar);
            }
        } catch (Exception e) {
            a(aaVar, e, obj, i == eVarArr.length ? "[anySetter]" : eVarArr[i].a());
        } catch (StackOverflowError e2) {
            com.a.a.c.l lVar = new com.a.a.c.l(hVar, "Infinite recursion (StackOverflowError)", e2);
            lVar.a(obj, i == eVarArr.length ? "[anySetter]" : eVarArr[i].a());
            throw lVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void c(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        com.a.a.c.k.e[] eVarArr;
        if (this.d != null && aaVar.e() != null) {
            eVarArr = this.d;
        } else {
            eVarArr = this.c;
        }
        com.a.a.c.k.o a2 = a(aaVar, this.f, obj);
        if (a2 == null) {
            b(obj, hVar, aaVar);
            return;
        }
        int i = 0;
        try {
            int length = eVarArr.length;
            while (i < length) {
                com.a.a.c.k.e eVar = eVarArr[i];
                if (eVar != null) {
                    a2.a(obj, hVar, aaVar, eVar);
                }
                i++;
            }
            if (this.e != null) {
                this.e.a(obj, hVar, aaVar, a2);
            }
        } catch (Exception e) {
            a(aaVar, e, obj, i == eVarArr.length ? "[anySetter]" : eVarArr[i].a());
        } catch (StackOverflowError e2) {
            com.a.a.c.l lVar = new com.a.a.c.l(hVar, "Infinite recursion (StackOverflowError)", e2);
            lVar.a(obj, i == eVarArr.length ? "[anySetter]" : eVarArr[i].a());
            throw lVar;
        }
    }
}
