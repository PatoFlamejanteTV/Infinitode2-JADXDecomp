package com.a.a.c.c;

import com.a.a.c.c.a.y;
import com.a.a.c.c.a.z;
import com.a.a.c.m.ac;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/a/a/c/c/d.class */
public class d extends f implements Serializable {
    private transient Exception t;
    private volatile transient com.a.a.c.m.r u;

    @Override // com.a.a.c.c.f
    public final /* synthetic */ f a(Set set, Set set2) {
        return b((Set<String>) set, (Set<String>) set2);
    }

    public d(g gVar, com.a.a.c.b bVar, com.a.a.c.c.a.c cVar, Map<String, v> map, HashSet<String> hashSet, boolean z, Set<String> set, boolean z2) {
        super(gVar, bVar, cVar, map, hashSet, z, set, z2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public d(f fVar) {
        super(fVar, fVar.m);
    }

    private d(f fVar, boolean z) {
        super(fVar, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public d(f fVar, com.a.a.c.m.r rVar) {
        super(fVar, rVar);
    }

    private d(f fVar, com.a.a.c.c.a.s sVar) {
        super(fVar, sVar);
    }

    private d(f fVar, Set<String> set, Set<String> set2) {
        super(fVar, set, set2);
    }

    private d(f fVar, com.a.a.c.c.a.c cVar) {
        super(fVar, cVar);
    }

    @Override // com.a.a.c.c.f, com.a.a.c.k
    public com.a.a.c.k<Object> a(com.a.a.c.m.r rVar) {
        if (getClass() != d.class) {
            return this;
        }
        if (this.u == rVar) {
            return this;
        }
        this.u = rVar;
        try {
            return new d(this, rVar);
        } finally {
            this.u = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.c.f
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public d a(com.a.a.c.c.a.s sVar) {
        return new d(this, sVar);
    }

    private d b(Set<String> set, Set<String> set2) {
        return new d(this, set, set2);
    }

    @Override // com.a.a.c.c.f
    public final f a(boolean z) {
        return new d(this, z);
    }

    @Override // com.a.a.c.c.f
    public final f a(com.a.a.c.c.a.c cVar) {
        return new d(this, cVar);
    }

    @Override // com.a.a.c.c.f
    protected final f g() {
        return new com.a.a.c.c.a.b(this, this.h.d());
    }

    @Override // com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (lVar.q()) {
            if (this.g) {
                lVar.g();
                return v(lVar, gVar);
            }
            lVar.g();
            if (this.q != null) {
                return e(lVar, gVar);
            }
            return b(lVar, gVar);
        }
        return a(lVar, gVar, lVar.k());
    }

    private Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.b.o oVar) {
        if (oVar != null) {
            switch (oVar) {
                case VALUE_STRING:
                    return i(lVar, gVar);
                case VALUE_NUMBER_INT:
                    return h(lVar, gVar);
                case VALUE_NUMBER_FLOAT:
                    return j(lVar, gVar);
                case VALUE_EMBEDDED_OBJECT:
                    return l(lVar, gVar);
                case VALUE_TRUE:
                case VALUE_FALSE:
                    return k(lVar, gVar);
                case VALUE_NULL:
                    return w(lVar, gVar);
                case START_ARRAY:
                    return d(lVar, gVar);
                case FIELD_NAME:
                case END_OBJECT:
                    if (this.g) {
                        return v(lVar, gVar);
                    }
                    if (this.q != null) {
                        return e(lVar, gVar);
                    }
                    return b(lVar, gVar);
            }
        }
        return gVar.a(e(gVar), lVar);
    }

    @Override // com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        String v;
        String h;
        Class<?> d;
        lVar.a(obj);
        if (this.i != null) {
            a(gVar, obj);
        }
        if (this.o != null) {
            return b(lVar, gVar, obj);
        }
        if (this.p != null) {
            return c(lVar, gVar, obj);
        }
        if (lVar.q()) {
            String h2 = lVar.h();
            v = h2;
            if (h2 == null) {
                return obj;
            }
        } else if (lVar.c(5)) {
            v = lVar.v();
        } else {
            return obj;
        }
        if (this.n && (d = gVar.d()) != null) {
            return a(lVar, gVar, obj, d);
        }
        do {
            lVar.g();
            v a2 = this.h.a(v);
            if (a2 != null) {
                try {
                    a2.a(lVar, gVar, obj);
                } catch (Exception e) {
                    a(e, obj, v, gVar);
                }
            } else {
                a(lVar, gVar, obj, v);
            }
            h = lVar.h();
            v = h;
        } while (h != null);
        return obj;
    }

    private final Object v(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        String h;
        Object a2 = this.f402b.a(gVar);
        lVar.a(a2);
        if (lVar.c(5)) {
            String v = lVar.v();
            do {
                lVar.g();
                v a3 = this.h.a(v);
                if (a3 != null) {
                    try {
                        a3.a(lVar, gVar, a2);
                    } catch (Exception e) {
                        a(e, a2, v, gVar);
                    }
                } else {
                    a(lVar, gVar, a2, v);
                }
                h = lVar.h();
                v = h;
            } while (h != null);
        }
        return a2;
    }

    @Override // com.a.a.c.c.f
    public Object b(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        String h;
        Class<?> d;
        Object U;
        if (this.q != null && this.q.c() && lVar.c(5) && this.q.a(lVar.v(), lVar)) {
            return f(lVar, gVar);
        }
        if (this.f) {
            if (this.o != null) {
                return x(lVar, gVar);
            }
            if (this.p != null) {
                return z(lVar, gVar);
            }
            return g(lVar, gVar);
        }
        Object a2 = this.f402b.a(gVar);
        lVar.a(a2);
        if (lVar.S() && (U = lVar.U()) != null) {
            a(lVar, gVar, a2, U);
        }
        if (this.i != null) {
            a(gVar, a2);
        }
        if (this.n && (d = gVar.d()) != null) {
            return a(lVar, gVar, a2, d);
        }
        if (lVar.c(5)) {
            String v = lVar.v();
            do {
                lVar.g();
                v a3 = this.h.a(v);
                if (a3 != null) {
                    try {
                        a3.a(lVar, gVar, a2);
                    } catch (Exception e) {
                        a(e, a2, v, gVar);
                    }
                } else {
                    a(lVar, gVar, a2, v);
                }
                h = lVar.h();
                v = h;
            } while (h != null);
        }
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.a.a.c.c.f
    public final Object c(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        Object obj;
        Object a2;
        com.a.a.c.c.a.v vVar = this.e;
        y a3 = vVar.a(lVar, gVar, this.q);
        ac acVar = null;
        Class<?> d = this.n ? gVar.d() : null;
        com.a.a.b.o k = lVar.k();
        ArrayList arrayList = null;
        while (k == com.a.a.b.o.FIELD_NAME) {
            String v = lVar.v();
            lVar.g();
            v a4 = vVar.a(v);
            if (!a3.a(v) || a4 != null) {
                if (a4 != null) {
                    if (d != null && !a4.a(d)) {
                        lVar.j();
                    } else if (a3.a(a4, a(lVar, gVar, a4))) {
                        lVar.g();
                        try {
                            a2 = vVar.a(gVar, a3);
                        } catch (Exception e) {
                            a2 = a(e, gVar);
                        }
                        if (a2 == null) {
                            return gVar.a(a(), (Object) null, j());
                        }
                        lVar.a(a2);
                        if (a2.getClass() != this.f401a.b()) {
                            return a(lVar, gVar, a2, acVar);
                        }
                        if (acVar != null) {
                            a2 = a(gVar, a2, acVar);
                        }
                        return a(lVar, gVar, a2);
                    }
                } else {
                    v a5 = this.h.a(v);
                    if (a5 != null) {
                        try {
                            a3.b(a5, a(lVar, gVar, a5));
                        } catch (w e2) {
                            a a6 = a(gVar, a5, a3, e2);
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                            }
                            arrayList.add(a6);
                        }
                    } else if (com.a.a.c.m.n.a(v, this.k, this.l)) {
                        c(lVar, gVar, a(), v);
                    } else if (this.j != null) {
                        try {
                            a3.a(this.j, v, this.j.a(lVar, gVar));
                        } catch (Exception e3) {
                            a(e3, this.f401a.b(), v, gVar);
                        }
                    } else if (this.m) {
                        lVar.j();
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
            obj = vVar.a(gVar, a3);
        } catch (Exception e4) {
            a(e4, gVar);
            obj = null;
        }
        if (this.i != null) {
            a(gVar, obj);
        }
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((a) it.next()).a(obj);
            }
        }
        if (acVar != null) {
            if (obj.getClass() != this.f401a.b()) {
                return a((com.a.a.b.l) null, gVar, obj, acVar);
            }
            return a(gVar, obj, acVar);
        }
        return obj;
    }

    private static a a(com.a.a.c.g gVar, v vVar, y yVar, w wVar) {
        a aVar = new a(gVar, wVar, vVar.c(), vVar);
        wVar.d().a((z.a) aVar);
        return aVar;
    }

    private Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, v vVar) {
        try {
            return vVar.a(lVar, gVar);
        } catch (Exception e) {
            a(e, this.f401a.b(), vVar.a(), gVar);
            return null;
        }
    }

    private Object w(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        Object b2;
        if (lVar.b()) {
            ac a2 = gVar.a(lVar);
            a2.j();
            com.a.a.b.l c = a2.c(lVar);
            c.g();
            if (this.g) {
                com.a.a.b.o oVar = com.a.a.b.o.END_OBJECT;
                b2 = v(c, gVar);
            } else {
                b2 = b(c, gVar);
            }
            Object obj = b2;
            c.close();
            return obj;
        }
        return gVar.a(e(gVar), lVar);
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
                    com.a.a.b.o g = lVar.g();
                    if (g == com.a.a.b.o.END_ARRAY) {
                        switch (h) {
                            case AsEmpty:
                                return c(gVar);
                            case AsNull:
                            case TryConvert:
                                return a(gVar);
                            default:
                                return gVar.a(e(gVar), com.a.a.b.o.START_ARRAY, lVar, (String) null, new Object[0]);
                        }
                    }
                    if (a2) {
                        if (g == com.a.a.b.o.START_ARRAY) {
                            com.a.a.c.j e = e(gVar);
                            return gVar.a(e, com.a.a.b.o.START_ARRAY, lVar, "Cannot deserialize value of type %s from deeply-nested Array: only single wrapper allowed with `%s`", com.a.a.c.m.i.b(e), "DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS");
                        }
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
        return b2;
    }

    private Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj, Class<?> cls) {
        String h;
        if (lVar.c(5)) {
            String v = lVar.v();
            do {
                lVar.g();
                v a2 = this.h.a(v);
                if (a2 != null) {
                    if (!a2.a(cls)) {
                        lVar.j();
                    } else {
                        try {
                            a2.a(lVar, gVar, obj);
                        } catch (Exception e) {
                            a(e, obj, v, gVar);
                        }
                    }
                } else {
                    a(lVar, gVar, obj, v);
                }
                h = lVar.h();
                v = h;
            } while (h != null);
        }
        return obj;
    }

    private Object x(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (this.c != null) {
            return this.f402b.a(gVar, this.c.a(lVar, gVar));
        }
        if (this.e != null) {
            return y(lVar, gVar);
        }
        ac a2 = gVar.a(lVar);
        a2.i();
        Object a3 = this.f402b.a(gVar);
        lVar.a(a3);
        if (this.i != null) {
            a(gVar, a3);
        }
        Class<?> d = this.n ? gVar.d() : null;
        String v = lVar.c(5) ? lVar.v() : null;
        while (true) {
            String str = v;
            if (str != null) {
                lVar.g();
                v a4 = this.h.a(str);
                if (a4 != null) {
                    if (d != null && !a4.a(d)) {
                        lVar.j();
                    } else {
                        try {
                            a4.a(lVar, gVar, a3);
                        } catch (Exception e) {
                            a(e, a3, str, gVar);
                        }
                    }
                } else if (com.a.a.c.m.n.a(str, this.k, this.l)) {
                    c(lVar, gVar, a3, str);
                } else if (this.j == null) {
                    a2.a(str);
                    a2.b(lVar);
                } else {
                    ac b2 = gVar.b(lVar);
                    a2.a(str);
                    a2.a(b2);
                    try {
                        this.j.a(b2.p(), gVar, a3, str);
                    } catch (Exception e2) {
                        a(e2, a3, str, gVar);
                    }
                }
                v = lVar.h();
            } else {
                a2.j();
                this.o.a(gVar, a3, a2);
                return a3;
            }
        }
    }

    private Object b(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        com.a.a.b.o k = lVar.k();
        com.a.a.b.o oVar = k;
        if (k == com.a.a.b.o.START_OBJECT) {
            oVar = lVar.g();
        }
        ac a2 = gVar.a(lVar);
        a2.i();
        Class<?> d = this.n ? gVar.d() : null;
        while (oVar == com.a.a.b.o.FIELD_NAME) {
            String v = lVar.v();
            v a3 = this.h.a(v);
            lVar.g();
            if (a3 != null) {
                if (d != null && !a3.a(d)) {
                    lVar.j();
                } else {
                    try {
                        a3.a(lVar, gVar, obj);
                    } catch (Exception e) {
                        a(e, obj, v, gVar);
                    }
                }
            } else if (com.a.a.c.m.n.a(v, this.k, this.l)) {
                c(lVar, gVar, obj, v);
            } else if (this.j == null) {
                a2.a(v);
                a2.b(lVar);
            } else {
                ac b2 = gVar.b(lVar);
                a2.a(v);
                a2.a(b2);
                try {
                    this.j.a(b2.p(), gVar, obj, v);
                } catch (Exception e2) {
                    a(e2, obj, v, gVar);
                }
            }
            oVar = lVar.g();
        }
        a2.j();
        this.o.a(gVar, obj, a2);
        return obj;
    }

    private Object y(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        Object a2;
        com.a.a.c.c.a.v vVar = this.e;
        y a3 = vVar.a(lVar, gVar, this.q);
        ac a4 = gVar.a(lVar);
        a4.i();
        com.a.a.b.o k = lVar.k();
        while (k == com.a.a.b.o.FIELD_NAME) {
            String v = lVar.v();
            lVar.g();
            v a5 = vVar.a(v);
            if (!a3.a(v) || a5 != null) {
                if (a5 != null) {
                    if (a3.a(a5, a(lVar, gVar, a5))) {
                        com.a.a.b.o g = lVar.g();
                        try {
                            a2 = vVar.a(gVar, a3);
                        } catch (Exception e) {
                            a2 = a(e, gVar);
                        }
                        lVar.a(a2);
                        while (g == com.a.a.b.o.FIELD_NAME) {
                            a4.b(lVar);
                            g = lVar.g();
                        }
                        if (g != com.a.a.b.o.END_OBJECT) {
                            gVar.a(this, com.a.a.b.o.END_OBJECT, "Attempted to unwrap '%s' value", a().getName());
                        }
                        a4.j();
                        if (a2.getClass() == this.f401a.b()) {
                            return this.o.a(gVar, a2, a4);
                        }
                        gVar.a(a5, "Cannot create polymorphic instances with unwrapped values", new Object[0]);
                        return null;
                    }
                } else {
                    v a6 = this.h.a(v);
                    if (a6 != null) {
                        a3.b(a6, a(lVar, gVar, a6));
                    } else if (com.a.a.c.m.n.a(v, this.k, this.l)) {
                        c(lVar, gVar, a(), v);
                    } else if (this.j == null) {
                        a4.a(v);
                        a4.b(lVar);
                    } else {
                        ac b2 = gVar.b(lVar);
                        a4.a(v);
                        a4.a(b2);
                        try {
                            a3.a(this.j, v, this.j.a(b2.p(), gVar));
                        } catch (Exception e2) {
                            a(e2, this.f401a.b(), v, gVar);
                        }
                    }
                }
            }
            k = lVar.g();
        }
        try {
            return this.o.a(gVar, vVar.a(gVar, a3), a4);
        } catch (Exception e3) {
            a(e3, gVar);
            return null;
        }
    }

    private Object z(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (this.e != null) {
            return A(lVar, gVar);
        }
        if (this.c != null) {
            return this.f402b.a(gVar, this.c.a(lVar, gVar));
        }
        return c(lVar, gVar, this.f402b.a(gVar));
    }

    private Object c(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        return a(lVar, gVar, obj, this.p.a());
    }

    private Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj, com.a.a.c.c.a.g gVar2) {
        Class<?> d = this.n ? gVar.d() : null;
        com.a.a.b.o k = lVar.k();
        while (k == com.a.a.b.o.FIELD_NAME) {
            String v = lVar.v();
            com.a.a.b.o g = lVar.g();
            v a2 = this.h.a(v);
            if (a2 != null) {
                if (g.g()) {
                    gVar2.a(lVar, gVar, v, obj);
                }
                if (d != null && !a2.a(d)) {
                    lVar.j();
                } else {
                    try {
                        a2.a(lVar, gVar, obj);
                    } catch (Exception e) {
                        a(e, obj, v, gVar);
                    }
                }
            } else if (com.a.a.c.m.n.a(v, this.k, this.l)) {
                c(lVar, gVar, obj, v);
            } else if (!gVar2.b(lVar, gVar, v, obj)) {
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
        return gVar2.a(lVar, gVar, obj);
    }

    private Object A(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        com.a.a.c.c.a.g a2 = this.p.a();
        com.a.a.c.c.a.v vVar = this.e;
        y a3 = vVar.a(lVar, gVar, this.q);
        Class<?> d = this.n ? gVar.d() : null;
        com.a.a.b.o k = lVar.k();
        while (k == com.a.a.b.o.FIELD_NAME) {
            String v = lVar.v();
            com.a.a.b.o g = lVar.g();
            v a4 = vVar.a(v);
            if (!a3.a(v) || a4 != null) {
                if (a4 != null) {
                    if (!a2.b(lVar, gVar, v, (Object) null) && a3.a(a4, a(lVar, gVar, a4))) {
                        lVar.g();
                        try {
                            Object a5 = vVar.a(gVar, a3);
                            if (a5.getClass() != this.f401a.b()) {
                                return gVar.a(this.f401a, String.format("Cannot create polymorphic instances with external type ids (%s -> %s)", this.f401a, a5.getClass()));
                            }
                            return a(lVar, gVar, a5, a2);
                        } catch (Exception e) {
                            a(e, this.f401a.b(), v, gVar);
                        }
                    }
                } else {
                    v a6 = this.h.a(v);
                    if (a6 != null) {
                        if (g.g()) {
                            a2.a(lVar, gVar, v, (Object) null);
                        }
                        if (d != null && !a6.a(d)) {
                            lVar.j();
                        } else {
                            a3.b(a6, a6.a(lVar, gVar));
                        }
                    } else if (!a2.b(lVar, gVar, v, (Object) null)) {
                        if (com.a.a.c.m.n.a(v, this.k, this.l)) {
                            c(lVar, gVar, a(), v);
                        } else if (this.j != null) {
                            a3.a(this.j, v, this.j.a(lVar, gVar));
                        } else {
                            b(lVar, gVar, this.s, v);
                        }
                    }
                }
            }
            k = lVar.g();
        }
        try {
            return a2.a(lVar, gVar, a3, vVar);
        } catch (Exception e2) {
            return a(e2, gVar);
        }
    }

    private Exception j() {
        if (this.t == null) {
            this.t = new NullPointerException("JSON Creator returned null");
        }
        return this.t;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/c/d$a.class */
    public static class a extends z.a {

        /* renamed from: a, reason: collision with root package name */
        private final com.a.a.c.g f397a;

        /* renamed from: b, reason: collision with root package name */
        private final v f398b;
        private Object c;

        a(com.a.a.c.g gVar, w wVar, com.a.a.c.j jVar, v vVar) {
            super(wVar, jVar);
            this.f397a = gVar;
            this.f398b = vVar;
        }

        public final void a(Object obj) {
            this.c = obj;
        }

        @Override // com.a.a.c.c.a.z.a
        public final void a(Object obj, Object obj2) {
            if (this.c == null) {
                this.f397a.a(this.f398b, "Cannot resolve ObjectId forward reference using property '%s' (of type %s): Bean not yet resolved", this.f398b.a(), this.f398b.k().getName());
            }
            this.f398b.a(this.c, obj2);
        }
    }
}
