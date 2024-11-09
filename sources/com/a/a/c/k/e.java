package com.a.a.c.k;

import com.a.a.a.s;
import com.a.a.c.aa;
import com.a.a.c.k.a.k;
import com.a.a.c.k.a.t;
import com.a.a.c.v;
import com.a.a.c.w;
import com.a.a.c.y;
import com.a.a.c.z;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/k/e.class */
public class e extends p implements Serializable {

    /* renamed from: b, reason: collision with root package name */
    public static final Object f642b = s.a.NON_EMPTY;
    protected final com.a.a.b.c.k c;
    private w j;
    private com.a.a.c.j k;
    private com.a.a.c.j l;
    protected com.a.a.c.j d;
    private transient com.a.a.c.m.b m;
    private com.a.a.c.f.j n;
    private transient Method o;
    private transient Field p;
    protected com.a.a.c.o<Object> e;
    protected com.a.a.c.o<Object> f;
    protected com.a.a.c.i.i g;
    protected transient com.a.a.c.k.a.k h;
    private boolean q;
    protected final Object i;
    private Class<?>[] r;
    private transient HashMap<Object, Object> s;

    public e(com.a.a.c.f.s sVar, com.a.a.c.f.j jVar, com.a.a.c.m.b bVar, com.a.a.c.j jVar2, com.a.a.c.o<?> oVar, com.a.a.c.i.i iVar, com.a.a.c.j jVar3, boolean z, Object obj, Class<?>[] clsArr) {
        super(sVar);
        this.n = jVar;
        this.m = bVar;
        this.c = new com.a.a.b.c.k(sVar.a());
        this.j = sVar.c();
        this.k = jVar2;
        this.e = oVar;
        this.h = oVar == null ? com.a.a.c.k.a.k.a() : null;
        this.g = iVar;
        this.l = jVar3;
        if (jVar instanceof com.a.a.c.f.h) {
            this.o = null;
            this.p = (Field) jVar.i();
        } else if (jVar instanceof com.a.a.c.f.k) {
            this.o = (Method) jVar.i();
            this.p = null;
        } else {
            this.o = null;
            this.p = null;
        }
        this.q = z;
        this.i = obj;
        this.f = null;
        this.r = clsArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public e() {
        super(v.c);
        this.n = null;
        this.m = null;
        this.c = null;
        this.j = null;
        this.r = null;
        this.k = null;
        this.e = null;
        this.h = null;
        this.g = null;
        this.l = null;
        this.o = null;
        this.p = null;
        this.q = false;
        this.i = null;
        this.f = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public e(e eVar) {
        this(eVar, eVar.c);
    }

    private e(e eVar, w wVar) {
        super(eVar);
        this.c = new com.a.a.b.c.k(wVar.b());
        this.j = eVar.j;
        this.m = eVar.m;
        this.k = eVar.k;
        this.n = eVar.n;
        this.o = eVar.o;
        this.p = eVar.p;
        this.e = eVar.e;
        this.f = eVar.f;
        if (eVar.s != null) {
            this.s = new HashMap<>(eVar.s);
        }
        this.l = eVar.l;
        this.h = eVar.h;
        this.q = eVar.q;
        this.i = eVar.i;
        this.r = eVar.r;
        this.g = eVar.g;
        this.d = eVar.d;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public e(e eVar, com.a.a.b.c.k kVar) {
        super(eVar);
        this.c = kVar;
        this.j = eVar.j;
        this.n = eVar.n;
        this.m = eVar.m;
        this.k = eVar.k;
        this.o = eVar.o;
        this.p = eVar.p;
        this.e = eVar.e;
        this.f = eVar.f;
        if (eVar.s != null) {
            this.s = new HashMap<>(eVar.s);
        }
        this.l = eVar.l;
        this.h = eVar.h;
        this.q = eVar.q;
        this.i = eVar.i;
        this.r = eVar.r;
        this.g = eVar.g;
        this.d = eVar.d;
    }

    public e a(com.a.a.c.m.r rVar) {
        String a2 = rVar.a(this.c.a());
        if (a2.equals(this.c.toString())) {
            return this;
        }
        return b(w.a(a2));
    }

    private e b(w wVar) {
        return new e(this, wVar);
    }

    public final void a(com.a.a.c.i.i iVar) {
        this.g = iVar;
    }

    public void a(com.a.a.c.o<Object> oVar) {
        if (this.e != null && this.e != oVar) {
            throw new IllegalStateException(String.format("Cannot override _serializer: had a %s, trying to set to %s", com.a.a.c.m.i.d(this.e), com.a.a.c.m.i.d(oVar)));
        }
        this.e = oVar;
    }

    public void b(com.a.a.c.o<Object> oVar) {
        if (this.f != null && this.f != oVar) {
            throw new IllegalStateException(String.format("Cannot override _nullSerializer: had a %s, trying to set to %s", com.a.a.c.m.i.d(this.f), com.a.a.c.m.i.d(oVar)));
        }
        this.f = oVar;
    }

    public final e b(com.a.a.c.m.r rVar) {
        return new t(this, rVar);
    }

    public final void a(com.a.a.c.j jVar) {
        this.d = jVar;
    }

    public final void a(y yVar) {
        this.n.a(yVar.a(com.a.a.c.q.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
    }

    @Override // com.a.a.c.c, com.a.a.c.m.v
    public final String a() {
        return this.c.a();
    }

    @Override // com.a.a.c.c
    public final w b() {
        return new w(this.c.a());
    }

    @Override // com.a.a.c.c
    public final com.a.a.c.j c() {
        return this.k;
    }

    @Override // com.a.a.c.c
    public final com.a.a.c.f.j e() {
        return this.n;
    }

    public final boolean f() {
        return this.e != null;
    }

    public final boolean g() {
        return this.f != null;
    }

    public final com.a.a.c.i.i h() {
        return this.g;
    }

    public final boolean i() {
        return this.q;
    }

    public final boolean a(w wVar) {
        if (this.j != null) {
            return this.j.equals(wVar);
        }
        return wVar.c(this.c.a()) && !wVar.d();
    }

    public final com.a.a.c.j j() {
        return this.l;
    }

    public final Class<?>[] k() {
        return this.r;
    }

    @Override // com.a.a.c.k.p
    public void a(Object obj, com.a.a.b.h hVar, aa aaVar) {
        Object invoke = this.o == null ? this.p.get(obj) : this.o.invoke(obj, (Object[]) null);
        Object obj2 = invoke;
        if (invoke == null) {
            if ((this.i == null || !aaVar.b(this.i)) && this.f != null) {
                hVar.b((com.a.a.b.r) this.c);
                this.f.a(null, hVar, aaVar);
                return;
            }
            return;
        }
        com.a.a.c.o<Object> oVar = this.e;
        com.a.a.c.o<Object> oVar2 = oVar;
        if (oVar == null) {
            Class<?> cls = obj2.getClass();
            com.a.a.c.k.a.k kVar = this.h;
            com.a.a.c.o<Object> a2 = kVar.a(cls);
            oVar2 = a2;
            if (a2 == null) {
                oVar2 = a(kVar, cls, aaVar);
            }
        }
        if (this.i != null) {
            if (f642b == this.i) {
                if (oVar2.a(aaVar, obj2)) {
                    return;
                }
            } else if (this.i.equals(obj2)) {
                return;
            }
        }
        if (obj2 == obj && a(hVar, aaVar, (com.a.a.c.o<?>) oVar2)) {
            return;
        }
        hVar.b((com.a.a.b.r) this.c);
        if (this.g == null) {
            oVar2.a(obj2, hVar, aaVar);
        } else {
            oVar2.a(obj2, hVar, aaVar, this.g);
        }
    }

    public final void a(com.a.a.b.h hVar) {
    }

    public void b(Object obj, com.a.a.b.h hVar, aa aaVar) {
        Object invoke = this.o == null ? this.p.get(obj) : this.o.invoke(obj, (Object[]) null);
        Object obj2 = invoke;
        if (invoke == null) {
            if (this.f != null) {
                this.f.a(null, hVar, aaVar);
                return;
            } else {
                hVar.k();
                return;
            }
        }
        com.a.a.c.o<Object> oVar = this.e;
        com.a.a.c.o<Object> oVar2 = oVar;
        if (oVar == null) {
            Class<?> cls = obj2.getClass();
            com.a.a.c.k.a.k kVar = this.h;
            com.a.a.c.o<Object> a2 = kVar.a(cls);
            oVar2 = a2;
            if (a2 == null) {
                oVar2 = a(kVar, cls, aaVar);
            }
        }
        if (this.i != null) {
            if (f642b == this.i) {
                if (oVar2.a(aaVar, obj2)) {
                    a(hVar, aaVar);
                    return;
                }
            } else if (this.i.equals(obj2)) {
                a(hVar, aaVar);
                return;
            }
        }
        if (obj2 == obj && a(hVar, aaVar, (com.a.a.c.o<?>) oVar2)) {
            return;
        }
        if (this.g == null) {
            oVar2.a(obj2, hVar, aaVar);
        } else {
            oVar2.a(obj2, hVar, aaVar, this.g);
        }
    }

    public final void a(com.a.a.b.h hVar, aa aaVar) {
        if (this.f != null) {
            this.f.a(null, hVar, aaVar);
        } else {
            hVar.k();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public com.a.a.c.o<Object> a(com.a.a.c.k.a.k kVar, Class<?> cls, aa aaVar) {
        k.d a2;
        if (this.d != null) {
            a2 = kVar.a(aaVar.a(this.d, cls), aaVar, this);
        } else {
            a2 = kVar.a(cls, aaVar, this);
        }
        if (kVar != a2.f568b) {
            this.h = a2.f568b;
        }
        return a2.f567a;
    }

    public final Object a(Object obj) {
        return this.o == null ? this.p.get(obj) : this.o.invoke(obj, (Object[]) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean a(com.a.a.b.h hVar, aa aaVar, com.a.a.c.o<?> oVar) {
        if (!oVar.b()) {
            if (aaVar.a(z.FAIL_ON_SELF_REFERENCES)) {
                if (oVar instanceof com.a.a.c.k.b.d) {
                    aaVar.a(c(), "Direct self-reference leading to cycle");
                    return false;
                }
                return false;
            }
            if (aaVar.a(z.WRITE_SELF_REFERENCES_AS_NULL)) {
                if (this.f != null) {
                    if (!hVar.a().b()) {
                        hVar.b((com.a.a.b.r) this.c);
                    }
                    this.f.a(null, hVar, aaVar);
                    return true;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(40);
        sb.append("property '").append(a()).append("' (");
        if (this.o != null) {
            sb.append("via method ").append(this.o.getDeclaringClass().getName()).append("#").append(this.o.getName());
        } else if (this.p != null) {
            sb.append("field \"").append(this.p.getDeclaringClass().getName()).append("#").append(this.p.getName());
        } else {
            sb.append("virtual");
        }
        if (this.e == null) {
            sb.append(", no static serializer");
        } else {
            sb.append(", static serializer of type " + this.e.getClass().getName());
        }
        sb.append(')');
        return sb.toString();
    }
}
