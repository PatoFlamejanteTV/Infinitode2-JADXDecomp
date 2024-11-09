package com.a.a.c.k.b;

import com.a.a.a.s;
import com.a.a.c.a.f;
import com.d.h.w;

/* loaded from: infinitode-2.jar:com/a/a/c/k/b/ae.class */
public abstract class ae<T> extends ao<T> implements com.a.a.c.k.k {
    private static Object g = s.a.NON_EMPTY;
    private com.a.a.c.j i;

    /* renamed from: a, reason: collision with root package name */
    protected final com.a.a.c.c f598a;

    /* renamed from: b, reason: collision with root package name */
    protected final com.a.a.c.i.i f599b;
    protected final com.a.a.c.o<Object> c;
    protected final com.a.a.c.m.r d;
    private transient com.a.a.c.k.a.k j;
    protected final Object e;
    protected final boolean f;

    protected abstract ae<T> a(com.a.a.c.c cVar, com.a.a.c.i.i iVar, com.a.a.c.o<?> oVar, com.a.a.c.m.r rVar);

    public abstract ae<T> a(Object obj, boolean z);

    protected abstract boolean c(T t);

    protected abstract Object b(T t);

    protected abstract Object a(T t);

    public ae(com.a.a.c.l.j jVar, com.a.a.c.i.i iVar, com.a.a.c.o<Object> oVar) {
        super(jVar);
        this.i = jVar.E();
        this.f598a = null;
        this.f599b = iVar;
        this.c = oVar;
        this.d = null;
        this.e = null;
        this.f = false;
        this.j = com.a.a.c.k.a.k.a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ae(ae<?> aeVar, com.a.a.c.c cVar, com.a.a.c.i.i iVar, com.a.a.c.o<?> oVar, com.a.a.c.m.r rVar, Object obj, boolean z) {
        super(aeVar);
        this.i = aeVar.i;
        this.j = com.a.a.c.k.a.k.a();
        this.f598a = cVar;
        this.f599b = iVar;
        this.c = oVar;
        this.d = rVar;
        this.e = obj;
        this.f = z;
    }

    @Override // com.a.a.c.o
    public final com.a.a.c.o<T> a(com.a.a.c.m.r rVar) {
        com.a.a.c.o<?> oVar = this.c;
        com.a.a.c.o<?> oVar2 = oVar;
        if (oVar != null) {
            com.a.a.c.o<?> a2 = oVar2.a(rVar);
            oVar2 = a2;
            if (a2 == this.c) {
                return this;
            }
        }
        com.a.a.c.m.r a3 = this.d == null ? rVar : com.a.a.c.m.r.a(rVar, this.d);
        if (this.c == oVar2 && this.d == a3) {
            return this;
        }
        return a(this.f598a, this.f599b, oVar2, a3);
    }

    @Override // com.a.a.c.k.k
    public final com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.c cVar) {
        ae<T> a2;
        s.b b2;
        s.a c;
        Object obj;
        boolean z;
        com.a.a.c.i.i iVar = this.f599b;
        com.a.a.c.i.i iVar2 = iVar;
        if (iVar != null) {
            iVar2 = iVar2.a(cVar);
        }
        com.a.a.c.o<?> b3 = b(aaVar, cVar);
        com.a.a.c.o<?> oVar = b3;
        if (b3 == null) {
            com.a.a.c.o<?> oVar2 = this.c;
            oVar = oVar2;
            if (oVar2 == null) {
                if (a(aaVar, cVar, this.i)) {
                    oVar = a(aaVar, this.i, cVar);
                }
            } else {
                oVar = aaVar.a(oVar, cVar);
            }
        }
        if (this.f598a == cVar && this.f599b == iVar2 && this.c == oVar) {
            a2 = this;
        } else {
            a2 = a(cVar, iVar2, oVar, this.d);
        }
        if (cVar != null && (b2 = cVar.b(aaVar.a(), a())) != null && (c = b2.c()) != s.a.USE_DEFAULTS) {
            switch (c) {
                case NON_DEFAULT:
                    obj = com.a.a.c.m.f.a(this.i);
                    z = true;
                    if (obj != null && obj.getClass().isArray()) {
                        obj = com.a.a.c.m.c.a(obj);
                        break;
                    }
                    break;
                case NON_ABSENT:
                    z = true;
                    obj = this.i.F() ? g : null;
                    break;
                case NON_EMPTY:
                    z = true;
                    obj = g;
                    break;
                case CUSTOM:
                    Object a3 = aaVar.a((com.a.a.c.f.s) null, b2.e());
                    obj = a3;
                    if (a3 == null) {
                        z = true;
                        break;
                    } else {
                        z = aaVar.b(obj);
                        break;
                    }
                case NON_NULL:
                    obj = null;
                    z = true;
                    break;
                default:
                    obj = null;
                    z = false;
                    break;
            }
            if (this.e != obj || this.f != z) {
                a2 = a2.a(obj, z);
            }
        }
        return a2;
    }

    private static boolean a(com.a.a.c.aa aaVar, com.a.a.c.c cVar, com.a.a.c.j jVar) {
        if (jVar.q()) {
            return false;
        }
        if (jVar.m() || jVar.r()) {
            return true;
        }
        com.a.a.c.a d = aaVar.d();
        if (d != null && cVar != null && cVar.e() != null) {
            f.b r = d.r(cVar.e());
            if (r == f.b.STATIC) {
                return true;
            }
            if (r == f.b.DYNAMIC) {
                return false;
            }
        }
        return aaVar.a(com.a.a.c.q.USE_STATIC_TYPING);
    }

    @Override // com.a.a.c.o
    public final boolean a(com.a.a.c.aa aaVar, T t) {
        if (!c(t)) {
            return true;
        }
        Object b2 = b(t);
        if (b2 == null) {
            return this.f;
        }
        if (this.e == null) {
            return false;
        }
        com.a.a.c.o<Object> oVar = this.c;
        com.a.a.c.o<Object> oVar2 = oVar;
        if (oVar == null) {
            try {
                oVar2 = a(aaVar, b2.getClass());
            } catch (com.a.a.c.l e) {
                throw new w.a(e);
            }
        }
        return this.e == g ? oVar2.a(aaVar, b2) : this.e.equals(b2);
    }

    @Override // com.a.a.c.o
    public final boolean c() {
        return this.d != null;
    }

    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    public final void a(T t, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        Object a2 = a((ae<T>) t);
        if (a2 == null) {
            if (this.d == null) {
                aaVar.a(hVar);
                return;
            }
            return;
        }
        com.a.a.c.o<Object> oVar = this.c;
        com.a.a.c.o<Object> oVar2 = oVar;
        if (oVar == null) {
            oVar2 = a(aaVar, a2.getClass());
        }
        if (this.f599b != null) {
            oVar2.a(a2, hVar, aaVar, this.f599b);
        } else {
            oVar2.a(a2, hVar, aaVar);
        }
    }

    @Override // com.a.a.c.o
    public final void a(T t, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.i.i iVar) {
        Object a2 = a((ae<T>) t);
        if (a2 == null) {
            if (this.d == null) {
                aaVar.a(hVar);
            }
        } else {
            com.a.a.c.o<Object> oVar = this.c;
            com.a.a.c.o<Object> oVar2 = oVar;
            if (oVar == null) {
                oVar2 = a(aaVar, a2.getClass());
            }
            oVar2.a(a2, hVar, aaVar, iVar);
        }
    }

    private final com.a.a.c.o<Object> a(com.a.a.c.aa aaVar, Class<?> cls) {
        com.a.a.c.o<Object> a2 = this.j.a(cls);
        com.a.a.c.o<Object> oVar = a2;
        if (a2 == null) {
            if (this.i.s()) {
                oVar = aaVar.b(aaVar.a(this.i, cls), this.f598a);
            } else {
                oVar = aaVar.b(cls, this.f598a);
            }
            if (this.d != null) {
                oVar = oVar.a(this.d);
            }
            this.j = this.j.b(cls, oVar);
        }
        return oVar;
    }

    private static com.a.a.c.o<Object> a(com.a.a.c.aa aaVar, com.a.a.c.j jVar, com.a.a.c.c cVar) {
        return aaVar.b(jVar, cVar);
    }
}
