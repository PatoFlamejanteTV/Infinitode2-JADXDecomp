package com.a.a.c.k.a;

import com.a.a.a.s;
import com.a.a.c.aa;
import com.a.a.c.k.a.k;
import java.util.Map;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/k/a/h.class */
public final class h extends com.a.a.c.k.j<Map.Entry<?, ?>> implements com.a.a.c.k.k {

    /* renamed from: a, reason: collision with root package name */
    private static Object f559a = s.a.NON_EMPTY;

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.c.c f560b;
    private boolean c;
    private com.a.a.c.j d;
    private com.a.a.c.j e;
    private com.a.a.c.j f;
    private com.a.a.c.o<Object> g;
    private com.a.a.c.o<Object> i;
    private com.a.a.c.i.i j;
    private k k;
    private Object l;
    private boolean m;

    @Override // com.a.a.c.k.j
    public final /* bridge */ /* synthetic */ boolean a(Map.Entry<?, ?> entry) {
        return true;
    }

    public h(com.a.a.c.j jVar, com.a.a.c.j jVar2, com.a.a.c.j jVar3, boolean z, com.a.a.c.i.i iVar, com.a.a.c.c cVar) {
        super(jVar);
        this.d = jVar;
        this.e = jVar2;
        this.f = jVar3;
        this.c = z;
        this.j = iVar;
        this.f560b = cVar;
        this.k = k.a();
        this.l = null;
        this.m = false;
    }

    private h(h hVar, com.a.a.c.o<?> oVar, com.a.a.c.o<?> oVar2, Object obj, boolean z) {
        super(Map.class, false);
        this.d = hVar.d;
        this.e = hVar.e;
        this.f = hVar.f;
        this.c = hVar.c;
        this.j = hVar.j;
        this.g = oVar;
        this.i = oVar2;
        this.k = k.a();
        this.f560b = hVar.f560b;
        this.l = obj;
        this.m = z;
    }

    @Override // com.a.a.c.k.j
    public final com.a.a.c.k.j<?> b(com.a.a.c.i.i iVar) {
        return new h(this, this.g, this.i, this.l, this.m);
    }

    private h a(com.a.a.c.c cVar, com.a.a.c.o<?> oVar, com.a.a.c.o<?> oVar2, Object obj, boolean z) {
        return new h(this, oVar, oVar2, obj, z);
    }

    public final h a(Object obj, boolean z) {
        if (this.l == obj && this.m == z) {
            return this;
        }
        return new h(this, this.g, this.i, obj, z);
    }

    @Override // com.a.a.c.k.k
    public final com.a.a.c.o<?> a(aa aaVar, com.a.a.c.c cVar) {
        com.a.a.c.o<?> b2;
        s.b b3;
        s.a c;
        com.a.a.c.o<Object> oVar = null;
        com.a.a.c.o<Object> oVar2 = null;
        com.a.a.c.a d = aaVar.d();
        com.a.a.c.f.j e = cVar == null ? null : cVar.e();
        com.a.a.c.f.j jVar = e;
        if (e != null && d != null) {
            Object o = d.o(jVar);
            if (o != null) {
                oVar2 = aaVar.b(jVar, o);
            }
            Object p = d.p(jVar);
            if (p != null) {
                oVar = aaVar.b(jVar, p);
            }
        }
        if (oVar == null) {
            oVar = this.i;
        }
        com.a.a.c.o<?> a2 = a(aaVar, cVar, (com.a.a.c.o<?>) oVar);
        com.a.a.c.o<?> oVar3 = a2;
        if (a2 == null && this.c && !this.f.q()) {
            oVar3 = aaVar.c(this.f, cVar);
        }
        if (oVar2 == null) {
            oVar2 = this.g;
        }
        if (oVar2 == null) {
            b2 = aaVar.d(this.e, cVar);
        } else {
            b2 = aaVar.b((com.a.a.c.o<?>) oVar2, cVar);
        }
        Object obj = this.l;
        boolean z = this.m;
        if (cVar != null && (b3 = cVar.b(aaVar.a(), null)) != null && (c = b3.c()) != s.a.USE_DEFAULTS) {
            switch (i.f561a[c.ordinal()]) {
                case 1:
                    obj = com.a.a.c.m.f.a(this.f);
                    z = true;
                    if (obj != null && obj.getClass().isArray()) {
                        obj = com.a.a.c.m.c.a(obj);
                        break;
                    }
                    break;
                case 2:
                    z = true;
                    obj = this.f.F() ? f559a : null;
                    break;
                case 3:
                    z = true;
                    obj = f559a;
                    break;
                case 4:
                    Object a3 = aaVar.a((com.a.a.c.f.s) null, b3.e());
                    obj = a3;
                    if (a3 == null) {
                        z = true;
                        break;
                    } else {
                        z = aaVar.b(obj);
                        break;
                    }
                case 5:
                    obj = null;
                    z = true;
                    break;
                default:
                    obj = null;
                    z = false;
                    break;
            }
        }
        return a(cVar, b2, oVar3, obj, z);
    }

    public final com.a.a.c.j d() {
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.o
    public boolean a(aa aaVar, Map.Entry<?, ?> entry) {
        Object value = entry.getValue();
        if (value == null) {
            return this.m;
        }
        if (this.l == null) {
            return false;
        }
        com.a.a.c.o<Object> oVar = this.i;
        com.a.a.c.o<Object> oVar2 = oVar;
        if (oVar == null) {
            Class<?> cls = value.getClass();
            com.a.a.c.o<Object> a2 = this.k.a(cls);
            oVar2 = a2;
            if (a2 == null) {
                try {
                    oVar2 = a(this.k, cls, aaVar);
                } catch (com.a.a.c.l unused) {
                    return false;
                }
            }
        }
        return this.l == f559a ? oVar2.a(aaVar, value) : this.l.equals(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    public void a(Map.Entry<?, ?> entry, com.a.a.b.h hVar, aa aaVar) {
        hVar.c(entry);
        b(entry, hVar, aaVar);
        hVar.j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.o
    public void a(Map.Entry<?, ?> entry, com.a.a.b.h hVar, aa aaVar, com.a.a.c.i.i iVar) {
        hVar.a(entry);
        com.a.a.b.f.a a2 = iVar.a(hVar, iVar.a(entry, com.a.a.b.o.START_OBJECT));
        b(entry, hVar, aaVar);
        iVar.b(hVar, a2);
    }

    private void b(Map.Entry<?, ?> entry, com.a.a.b.h hVar, aa aaVar) {
        com.a.a.c.o<Object> oVar;
        com.a.a.c.o<Object> oVar2;
        com.a.a.c.i.i iVar = this.j;
        Object key = entry.getKey();
        if (key == null) {
            oVar = aaVar.l();
        } else {
            oVar = this.g;
        }
        Object value = entry.getValue();
        if (value == null) {
            if (this.m) {
                return;
            } else {
                oVar2 = aaVar.k();
            }
        } else {
            com.a.a.c.o<Object> oVar3 = this.i;
            oVar2 = oVar3;
            if (oVar3 == null) {
                Class<?> cls = value.getClass();
                com.a.a.c.o<Object> a2 = this.k.a(cls);
                oVar2 = a2;
                if (a2 == null) {
                    if (this.f.s()) {
                        oVar2 = a(this.k, aaVar.a(this.f, cls), aaVar);
                    } else {
                        oVar2 = a(this.k, cls, aaVar);
                    }
                }
            }
            if (this.l != null && ((this.l == f559a && oVar2.a(aaVar, value)) || this.l.equals(value))) {
                return;
            }
        }
        oVar.a(key, hVar, aaVar);
        try {
            if (iVar == null) {
                oVar2.a(value, hVar, aaVar);
            } else {
                oVar2.a(value, hVar, aaVar, iVar);
            }
        } catch (Exception e) {
            a(aaVar, e, entry, new StringBuilder().append(key).toString());
        }
    }

    private com.a.a.c.o<Object> a(k kVar, Class<?> cls, aa aaVar) {
        k.d b2 = kVar.b(cls, aaVar, this.f560b);
        if (kVar != b2.f568b) {
            this.k = b2.f568b;
        }
        return b2.f567a;
    }

    private com.a.a.c.o<Object> a(k kVar, com.a.a.c.j jVar, aa aaVar) {
        k.d b2 = kVar.b(jVar, aaVar, this.f560b);
        if (kVar != b2.f568b) {
            this.k = b2.f568b;
        }
        return b2.f567a;
    }
}
