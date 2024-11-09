package com.a.a.c.c;

import com.a.a.c.c.a.y;
import com.a.a.c.m.ac;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/a/a/c/c/i.class */
public final class i extends f {
    private com.a.a.c.f.k t;
    private com.a.a.c.j u;

    public i(g gVar, com.a.a.c.b bVar, com.a.a.c.j jVar, com.a.a.c.c.a.c cVar, Map<String, v> map, Set<String> set, boolean z, Set<String> set2, boolean z2) {
        super(gVar, bVar, cVar, map, set, z, set2, z2);
        this.u = jVar;
        this.t = gVar.f();
        if (this.q != null) {
            throw new IllegalArgumentException("Cannot use Object Id with Builder-based deserialization (type " + bVar.a() + ")");
        }
    }

    private i(i iVar, boolean z) {
        super(iVar, z);
        this.t = iVar.t;
        this.u = iVar.u;
    }

    private i(i iVar, com.a.a.c.m.r rVar) {
        super(iVar, rVar);
        this.t = iVar.t;
        this.u = iVar.u;
    }

    private i(i iVar, com.a.a.c.c.a.s sVar) {
        super(iVar, sVar);
        this.t = iVar.t;
        this.u = iVar.u;
    }

    private i(i iVar, Set<String> set, Set<String> set2) {
        super(iVar, set, set2);
        this.t = iVar.t;
        this.u = iVar.u;
    }

    private i(i iVar, com.a.a.c.c.a.c cVar) {
        super(iVar, cVar);
        this.t = iVar.t;
        this.u = iVar.u;
    }

    @Override // com.a.a.c.c.f, com.a.a.c.k
    public final com.a.a.c.k<Object> a(com.a.a.c.m.r rVar) {
        return new i(this, rVar);
    }

    @Override // com.a.a.c.c.f
    public final f a(com.a.a.c.c.a.s sVar) {
        return new i(this, sVar);
    }

    @Override // com.a.a.c.c.f
    public final f a(Set<String> set, Set<String> set2) {
        return new i(this, set, set2);
    }

    @Override // com.a.a.c.c.f
    public final f a(boolean z) {
        return new i(this, z);
    }

    @Override // com.a.a.c.c.f
    public final f a(com.a.a.c.c.a.c cVar) {
        return new i(this, cVar);
    }

    @Override // com.a.a.c.c.f
    protected final f g() {
        return new com.a.a.c.c.a.a(this, this.u, this.h.d(), this.t);
    }

    @Override // com.a.a.c.c.f, com.a.a.c.k
    public final Boolean a(com.a.a.c.f fVar) {
        return Boolean.FALSE;
    }

    private Object b(com.a.a.c.g gVar, Object obj) {
        if (this.t == null) {
            return obj;
        }
        try {
            return this.t.i().invoke(obj, (Object[]) null);
        } catch (Exception e) {
            return a(e, gVar);
        }
    }

    @Override // com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (lVar.q()) {
            lVar.g();
            if (this.g) {
                return b(gVar, v(lVar, gVar));
            }
            return b(gVar, b(lVar, gVar));
        }
        switch (lVar.l()) {
            case 2:
            case 5:
                return b(gVar, b(lVar, gVar));
            case 3:
                return d(lVar, gVar);
            case 4:
            case 11:
            default:
                return gVar.a(e(gVar), lVar);
            case 6:
                return b(gVar, i(lVar, gVar));
            case 7:
                return b(gVar, h(lVar, gVar));
            case 8:
                return b(gVar, j(lVar, gVar));
            case 9:
            case 10:
                return b(gVar, k(lVar, gVar));
            case 12:
                return lVar.N();
        }
    }

    @Override // com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        com.a.a.c.j jVar = this.u;
        Class<?> a2 = a();
        Class<?> cls = obj.getClass();
        if (a2.isAssignableFrom(cls)) {
            return gVar.a(jVar, String.format("Deserialization of %s by passing existing Builder (%s) instance not supported", jVar, a2.getName()));
        }
        return gVar.a(jVar, String.format("Deserialization of %s by passing existing instance (of %s) not supported", jVar, cls.getName()));
    }

    private final Object v(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        Object a2 = this.f402b.a(gVar);
        while (lVar.k() == com.a.a.b.o.FIELD_NAME) {
            String v = lVar.v();
            lVar.g();
            v a3 = this.h.a(v);
            if (a3 != null) {
                try {
                    a2 = a3.b(lVar, gVar, a2);
                } catch (Exception e) {
                    a(e, a2, v, gVar);
                }
            } else {
                a(lVar, gVar, a2, v);
            }
            lVar.g();
        }
        return a2;
    }

    @Override // com.a.a.c.c.f
    public final Object b(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        Class<?> d;
        if (this.f) {
            if (this.o != null) {
                return w(lVar, gVar);
            }
            if (this.p != null) {
                return y(lVar, gVar);
            }
            return g(lVar, gVar);
        }
        Object a2 = this.f402b.a(gVar);
        if (this.i != null) {
            a(gVar, a2);
        }
        if (this.n && (d = gVar.d()) != null) {
            return a(lVar, gVar, a2, d);
        }
        while (lVar.k() == com.a.a.b.o.FIELD_NAME) {
            String v = lVar.v();
            lVar.g();
            v a3 = this.h.a(v);
            if (a3 != null) {
                try {
                    a2 = a3.b(lVar, gVar, a2);
                } catch (Exception e) {
                    a(e, a2, v, gVar);
                }
            } else {
                a(lVar, gVar, a2, v);
            }
            lVar.g();
        }
        return a2;
    }

    @Override // com.a.a.c.c.f
    protected final Object c(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        Object a2;
        com.a.a.c.c.a.v vVar = this.e;
        y a3 = vVar.a(lVar, gVar, this.q);
        Class<?> d = this.n ? gVar.d() : null;
        ac acVar = null;
        com.a.a.b.o k = lVar.k();
        while (k == com.a.a.b.o.FIELD_NAME) {
            String v = lVar.v();
            lVar.g();
            v a4 = vVar.a(v);
            if (!a3.a(v) || a4 != null) {
                if (a4 != null) {
                    if (d != null && !a4.a(d)) {
                        lVar.j();
                    } else if (a3.a(a4, a4.a(lVar, gVar))) {
                        lVar.g();
                        try {
                            Object a5 = vVar.a(gVar, a3);
                            if (a5.getClass() != this.f401a.b()) {
                                return a(lVar, gVar, a5, acVar);
                            }
                            if (acVar != null) {
                                a5 = a(gVar, a5, acVar);
                            }
                            return b(lVar, gVar, a5);
                        } catch (Exception e) {
                            a(e, this.f401a.b(), v, gVar);
                        }
                    } else {
                        continue;
                    }
                } else {
                    v a6 = this.h.a(v);
                    if (a6 != null) {
                        a3.b(a6, a6.a(lVar, gVar));
                    } else if (com.a.a.c.m.n.a(v, this.k, this.l)) {
                        c(lVar, gVar, a(), v);
                    } else if (this.j != null) {
                        a3.a(this.j, v, this.j.a(lVar, gVar));
                    } else {
                        if (acVar == null) {
                            acVar = gVar.a(lVar);
                        }
                        acVar.a(v);
                        acVar.b(lVar);
                    }
                }
            }
            k = lVar.g();
        }
        try {
            a2 = vVar.a(gVar, a3);
        } catch (Exception e2) {
            a2 = a(e2, gVar);
        }
        if (acVar != null) {
            if (a2.getClass() != this.f401a.b()) {
                return a((com.a.a.b.l) null, gVar, a2, acVar);
            }
            return a(gVar, a2, acVar);
        }
        return a2;
    }

    private Object b(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        Class<?> d;
        if (this.i != null) {
            a(gVar, obj);
        }
        if (this.o != null) {
            if (lVar.a(com.a.a.b.o.START_OBJECT)) {
                lVar.g();
            }
            ac a2 = gVar.a(lVar);
            a2.i();
            return b(lVar, gVar, obj, a2);
        }
        if (this.p != null) {
            return c(lVar, gVar, obj);
        }
        if (this.n && (d = gVar.d()) != null) {
            return a(lVar, gVar, obj, d);
        }
        com.a.a.b.o k = lVar.k();
        com.a.a.b.o oVar = k;
        if (k == com.a.a.b.o.START_OBJECT) {
            oVar = lVar.g();
        }
        while (oVar == com.a.a.b.o.FIELD_NAME) {
            String v = lVar.v();
            lVar.g();
            v a3 = this.h.a(v);
            if (a3 != null) {
                try {
                    obj = a3.b(lVar, gVar, obj);
                } catch (Exception e) {
                    a(e, obj, v, gVar);
                }
            } else {
                a(lVar, gVar, obj, v);
            }
            oVar = lVar.g();
        }
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.a.a.c.c.b.ae
    public final Object d(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        com.a.a.c.k<Object> kVar = this.d;
        com.a.a.c.k<Object> kVar2 = kVar;
        if (kVar == null) {
            com.a.a.c.k<Object> kVar3 = this.c;
            kVar2 = kVar3;
            if (kVar3 == null) {
                com.a.a.c.b.b h = h(gVar);
                boolean a2 = gVar.a(com.a.a.c.i.UNWRAP_SINGLE_VALUE_ARRAYS);
                if (a2 || h != com.a.a.c.b.b.Fail) {
                    if (lVar.g() == com.a.a.b.o.END_ARRAY) {
                        switch (j.f406a[h.ordinal()]) {
                            case 1:
                                return c(gVar);
                            case 2:
                            case 3:
                                return a(gVar);
                            default:
                                return gVar.a(e(gVar), com.a.a.b.o.START_ARRAY, lVar, (String) null, new Object[0]);
                        }
                    }
                    if (a2) {
                        Object a3 = a(lVar, gVar);
                        if (lVar.g() != com.a.a.b.o.END_ARRAY) {
                            j(gVar);
                        }
                        return a3;
                    }
                }
                return gVar.a(e(gVar), lVar);
            }
        }
        Object b2 = this.f402b.b(gVar, kVar2.a(lVar, gVar));
        if (this.i != null) {
            a(gVar, b2);
        }
        return b(gVar, b2);
    }

    private Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj, Class<?> cls) {
        com.a.a.b.o k = lVar.k();
        while (k == com.a.a.b.o.FIELD_NAME) {
            String v = lVar.v();
            lVar.g();
            v a2 = this.h.a(v);
            if (a2 != null) {
                if (!a2.a(cls)) {
                    lVar.j();
                } else {
                    try {
                        obj = a2.b(lVar, gVar, obj);
                    } catch (Exception e) {
                        a(e, obj, v, gVar);
                    }
                }
            } else {
                a(lVar, gVar, obj, v);
            }
            k = lVar.g();
        }
        return obj;
    }

    private Object w(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (this.c != null) {
            return this.f402b.a(gVar, this.c.a(lVar, gVar));
        }
        if (this.e != null) {
            return x(lVar, gVar);
        }
        ac a2 = gVar.a(lVar);
        a2.i();
        Object a3 = this.f402b.a(gVar);
        if (this.i != null) {
            a(gVar, a3);
        }
        Class<?> d = this.n ? gVar.d() : null;
        while (lVar.k() == com.a.a.b.o.FIELD_NAME) {
            String v = lVar.v();
            lVar.g();
            v a4 = this.h.a(v);
            if (a4 != null) {
                if (d != null && !a4.a(d)) {
                    lVar.j();
                } else {
                    try {
                        a3 = a4.b(lVar, gVar, a3);
                    } catch (Exception e) {
                        a(e, a3, v, gVar);
                    }
                }
            } else if (com.a.a.c.m.n.a(v, this.k, this.l)) {
                c(lVar, gVar, a3, v);
            } else {
                a2.a(v);
                a2.b(lVar);
                if (this.j != null) {
                    try {
                        this.j.a(lVar, gVar, a3, v);
                    } catch (Exception e2) {
                        a(e2, a3, v, gVar);
                    }
                }
            }
            lVar.g();
        }
        a2.j();
        return this.o.a(gVar, a3, a2);
    }

    private Object x(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        com.a.a.c.c.a.v vVar = this.e;
        y a2 = vVar.a(lVar, gVar, this.q);
        ac a3 = gVar.a(lVar);
        a3.i();
        Object obj = null;
        com.a.a.b.o k = lVar.k();
        while (k == com.a.a.b.o.FIELD_NAME) {
            String v = lVar.v();
            lVar.g();
            v a4 = vVar.a(v);
            if (!a2.a(v) || a4 != null) {
                if (a4 != null) {
                    if (a2.a(a4, a4.a(lVar, gVar))) {
                        lVar.g();
                        try {
                            obj = vVar.a(gVar, a2);
                            if (obj.getClass() != this.f401a.b()) {
                                return a(lVar, gVar, obj, a3);
                            }
                            return b(lVar, gVar, obj, a3);
                        } catch (Exception e) {
                            a(e, this.f401a.b(), v, gVar);
                        }
                    } else {
                        continue;
                    }
                } else {
                    v a5 = this.h.a(v);
                    if (a5 != null) {
                        a2.b(a5, a5.a(lVar, gVar));
                    } else if (com.a.a.c.m.n.a(v, this.k, this.l)) {
                        c(lVar, gVar, a(), v);
                    } else {
                        a3.a(v);
                        a3.b(lVar);
                        if (this.j != null) {
                            a2.a(this.j, v, this.j.a(lVar, gVar));
                        }
                    }
                }
            }
            k = lVar.g();
        }
        a3.j();
        if (obj == null) {
            try {
                obj = vVar.a(gVar, a2);
            } catch (Exception e2) {
                return a(e2, gVar);
            }
        }
        return this.o.a(gVar, obj, a3);
    }

    private Object b(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj, ac acVar) {
        Class<?> d = this.n ? gVar.d() : null;
        com.a.a.b.o k = lVar.k();
        while (k == com.a.a.b.o.FIELD_NAME) {
            String v = lVar.v();
            v a2 = this.h.a(v);
            lVar.g();
            if (a2 != null) {
                if (d != null && !a2.a(d)) {
                    lVar.j();
                } else {
                    try {
                        obj = a2.b(lVar, gVar, obj);
                    } catch (Exception e) {
                        a(e, obj, v, gVar);
                    }
                }
            } else if (com.a.a.c.m.n.a(v, this.k, this.l)) {
                c(lVar, gVar, obj, v);
            } else {
                acVar.a(v);
                acVar.b(lVar);
                if (this.j != null) {
                    this.j.a(lVar, gVar, obj, v);
                }
            }
            k = lVar.g();
        }
        acVar.j();
        return this.o.a(gVar, obj, acVar);
    }

    private Object y(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (this.e != null) {
            return k(gVar);
        }
        return c(lVar, gVar, this.f402b.a(gVar));
    }

    private Object c(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        Class<?> d = this.n ? gVar.d() : null;
        com.a.a.c.c.a.g a2 = this.p.a();
        com.a.a.b.o k = lVar.k();
        while (k == com.a.a.b.o.FIELD_NAME) {
            String v = lVar.v();
            com.a.a.b.o g = lVar.g();
            v a3 = this.h.a(v);
            if (a3 != null) {
                if (g.g()) {
                    a2.a(lVar, gVar, v, obj);
                }
                if (d != null && !a3.a(d)) {
                    lVar.j();
                } else {
                    try {
                        obj = a3.b(lVar, gVar, obj);
                    } catch (Exception e) {
                        a(e, obj, v, gVar);
                    }
                }
            } else if (com.a.a.c.m.n.a(v, this.k, this.l)) {
                c(lVar, gVar, obj, v);
            } else if (!a2.b(lVar, gVar, v, obj)) {
                if (this.j != null) {
                    try {
                        this.j.a(lVar, gVar, obj, v);
                    } catch (Exception e2) {
                        a(e2, obj, v, gVar);
                    }
                } else {
                    b(lVar, gVar, obj, v);
                }
            }
            k = lVar.g();
        }
        return a2.a(lVar, gVar, obj);
    }

    private Object k(com.a.a.c.g gVar) {
        com.a.a.c.j jVar = this.u;
        return gVar.a(jVar, String.format("Deserialization (of %s) with Builder, External type id, @JsonCreator not yet implemented", jVar));
    }
}
