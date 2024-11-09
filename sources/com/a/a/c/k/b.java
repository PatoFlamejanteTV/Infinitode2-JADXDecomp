package com.a.a.c.k;

import com.a.a.a.l;
import com.a.a.a.q;
import com.a.a.a.s;
import com.a.a.a.t;
import com.a.a.c.a.f;
import com.a.a.c.b.t;
import com.a.a.c.f.w;
import com.a.a.c.k.b.aa;
import com.a.a.c.k.b.ag;
import com.a.a.c.k.b.ai;
import com.a.a.c.k.b.aj;
import com.a.a.c.k.b.ak;
import com.a.a.c.k.b.am;
import com.a.a.c.k.b.ap;
import com.a.a.c.k.b.aq;
import com.a.a.c.k.b.ar;
import com.a.a.c.k.b.as;
import com.a.a.c.k.b.au;
import com.a.a.c.k.b.v;
import com.a.a.c.k.b.y;
import com.a.a.c.m.ac;
import com.a.a.c.z;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: infinitode-2.jar:com/a/a/c/k/b.class */
public abstract class b extends r implements Serializable {

    /* renamed from: b, reason: collision with root package name */
    private static HashMap<String, com.a.a.c.o<?>> f588b;
    private static HashMap<String, Class<? extends com.a.a.c.o<?>>> c;

    /* renamed from: a, reason: collision with root package name */
    protected final t f589a;

    protected abstract Iterable<w.a> a();

    static {
        HashMap<String, Class<? extends com.a.a.c.o<?>>> hashMap = new HashMap<>();
        HashMap<String, com.a.a.c.o<?>> hashMap2 = new HashMap<>();
        hashMap2.put(String.class.getName(), new ap());
        as asVar = as.f611a;
        hashMap2.put(StringBuffer.class.getName(), asVar);
        hashMap2.put(StringBuilder.class.getName(), asVar);
        hashMap2.put(Character.class.getName(), asVar);
        hashMap2.put(Character.TYPE.getName(), asVar);
        aa.a(hashMap2);
        hashMap2.put(Boolean.TYPE.getName(), new com.a.a.c.k.b.f(true));
        hashMap2.put(Boolean.class.getName(), new com.a.a.c.k.b.f(false));
        hashMap2.put(BigInteger.class.getName(), new y(BigInteger.class));
        hashMap2.put(BigDecimal.class.getName(), new y(BigDecimal.class));
        hashMap2.put(Calendar.class.getName(), com.a.a.c.k.b.i.f621a);
        hashMap2.put(Date.class.getName(), com.a.a.c.k.b.l.f622a);
        for (Map.Entry<Class<?>, Object> entry : ak.a()) {
            Object value = entry.getValue();
            if (value instanceof com.a.a.c.o) {
                hashMap2.put(entry.getKey().getName(), (com.a.a.c.o) value);
            } else {
                hashMap.put(entry.getKey().getName(), (Class) value);
            }
        }
        hashMap.put(ac.class.getName(), au.class);
        f588b = hashMap2;
        c = hashMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public b(t tVar) {
        this.f589a = tVar == null ? new t() : tVar;
    }

    @Override // com.a.a.c.k.r
    public final com.a.a.c.o<Object> a(com.a.a.c.aa aaVar, com.a.a.c.j jVar, com.a.a.c.o<Object> oVar) {
        com.a.a.c.y a2 = aaVar.a();
        com.a.a.c.b a3 = a2.a(jVar);
        com.a.a.c.o<Object> oVar2 = null;
        if (this.f589a.a()) {
            Iterator<w.a> it = this.f589a.d().iterator();
            while (it.hasNext()) {
                com.a.a.c.o<?> m = it.next().m();
                oVar2 = m;
                if (m != null) {
                    break;
                }
            }
        }
        if (oVar2 == null) {
            com.a.a.c.o<Object> c2 = c(aaVar, a3.d());
            oVar2 = c2;
            if (c2 == null) {
                oVar2 = oVar;
                if (oVar == null) {
                    com.a.a.c.o<Object> a4 = am.a(jVar.b(), false);
                    oVar2 = a4;
                    if (a4 == null) {
                        com.a.a.c.f.j p = a3.p();
                        com.a.a.c.f.j jVar2 = p;
                        if (p == null) {
                            jVar2 = a3.q();
                        }
                        if (jVar2 != null) {
                            com.a.a.c.o<Object> a5 = a(aaVar, jVar2.c(), oVar);
                            if (a2.g()) {
                                com.a.a.c.m.i.a(jVar2.i(), a2.a(com.a.a.c.q.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
                            }
                            oVar2 = new com.a.a.c.k.b.t(jVar2, null, a5);
                        } else {
                            oVar2 = am.a(a2, jVar.b());
                        }
                    }
                }
            }
        }
        if (this.f589a.b()) {
            Iterator<i> it2 = this.f589a.e().iterator();
            while (it2.hasNext()) {
                it2.next();
                oVar2 = i.h(oVar2);
            }
        }
        return oVar2;
    }

    @Override // com.a.a.c.k.r
    @Deprecated
    public final com.a.a.c.o<Object> a(com.a.a.c.y yVar, com.a.a.c.j jVar, com.a.a.c.o<Object> oVar) {
        yVar.a(jVar);
        com.a.a.c.o<Object> oVar2 = null;
        if (this.f589a.a()) {
            Iterator<w.a> it = this.f589a.d().iterator();
            while (it.hasNext()) {
                com.a.a.c.o<?> m = it.next().m();
                oVar2 = m;
                if (m != null) {
                    break;
                }
            }
        }
        if (oVar2 == null) {
            oVar2 = oVar;
            if (oVar == null) {
                com.a.a.c.o<Object> a2 = am.a(jVar.b(), false);
                oVar2 = a2;
                if (a2 == null) {
                    oVar2 = am.a(yVar, jVar.b());
                }
            }
        }
        if (this.f589a.b()) {
            Iterator<i> it2 = this.f589a.e().iterator();
            while (it2.hasNext()) {
                it2.next();
                oVar2 = i.h(oVar2);
            }
        }
        return oVar2;
    }

    @Override // com.a.a.c.k.r
    public final com.a.a.c.i.i a(com.a.a.c.y yVar, com.a.a.c.j jVar) {
        com.a.a.c.f.d d = yVar.c(jVar.b()).d();
        com.a.a.c.i.h<?> a2 = yVar.j().a((com.a.a.c.b.q<?>) yVar, d, jVar);
        Collection<com.a.a.c.i.b> collection = null;
        if (a2 == null) {
            a2 = yVar.n();
        } else {
            collection = yVar.w().a(yVar, d);
        }
        if (a2 == null) {
            return null;
        }
        return a2.a(yVar, jVar, collection);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static com.a.a.c.o<?> a(com.a.a.c.j jVar) {
        Class<? extends com.a.a.c.o<?>> cls;
        String name = jVar.b().getName();
        com.a.a.c.o<?> oVar = f588b.get(name);
        if (oVar == null && (cls = c.get(name)) != null) {
            return (com.a.a.c.o) com.a.a.c.m.i.b((Class) cls, false);
        }
        return oVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.j jVar, com.a.a.c.b bVar) {
        if (com.a.a.c.n.class.isAssignableFrom(jVar.b())) {
            return ag.f601a;
        }
        com.a.a.c.f.j q = bVar.q();
        if (q != null) {
            if (aaVar.f()) {
                com.a.a.c.m.i.a(q.i(), aaVar.a(com.a.a.c.q.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
            }
            com.a.a.c.j c2 = q.c();
            com.a.a.c.o<Object> a2 = a(aaVar, q);
            com.a.a.c.o<Object> oVar = a2;
            if (a2 == null) {
                oVar = (com.a.a.c.o) c2.A();
            }
            com.a.a.c.i.i iVar = (com.a.a.c.i.i) c2.B();
            com.a.a.c.i.i iVar2 = iVar;
            if (iVar == null) {
                iVar2 = a(aaVar.a(), c2);
            }
            return new com.a.a.c.k.b.t(q, iVar2, oVar);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.j jVar, com.a.a.c.b bVar, boolean z) {
        if (jVar.h()) {
            return a(aaVar.a(), jVar, bVar);
        }
        Class<?> b2 = jVar.b();
        com.a.a.c.o<?> b3 = b(aaVar, jVar, bVar);
        if (b3 != null) {
            return b3;
        }
        if (Calendar.class.isAssignableFrom(b2)) {
            return com.a.a.c.k.b.i.f621a;
        }
        if (Date.class.isAssignableFrom(b2)) {
            return com.a.a.c.k.b.l.f622a;
        }
        if (Map.Entry.class.isAssignableFrom(b2)) {
            com.a.a.c.j d = jVar.d(Map.Entry.class);
            return a(aaVar, bVar, z, d.b(0), d.b(1));
        }
        if (ByteBuffer.class.isAssignableFrom(b2)) {
            return new com.a.a.c.k.b.h();
        }
        if (InetAddress.class.isAssignableFrom(b2)) {
            return new com.a.a.c.k.b.q();
        }
        if (InetSocketAddress.class.isAssignableFrom(b2)) {
            return new com.a.a.c.k.b.r();
        }
        if (TimeZone.class.isAssignableFrom(b2)) {
            return new aq();
        }
        if (Charset.class.isAssignableFrom(b2)) {
            return as.f611a;
        }
        if (Number.class.isAssignableFrom(b2)) {
            switch (bVar.a((l.d) null).c()) {
                case STRING:
                    return as.f611a;
                case OBJECT:
                case ARRAY:
                    return null;
                default:
                    return y.f637a;
            }
        }
        if (ClassLoader.class.isAssignableFrom(b2)) {
            return new ar(jVar);
        }
        return null;
    }

    private static com.a.a.c.o<?> b(com.a.a.c.aa aaVar, com.a.a.c.j jVar, com.a.a.c.b bVar) {
        return com.a.a.c.e.g.f424a.a(aaVar.a(), jVar, bVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.o<?> a(com.a.a.c.y yVar, com.a.a.c.j jVar, com.a.a.c.b bVar, boolean z) {
        com.a.a.c.j b2;
        com.a.a.c.j b3;
        Class<?> b4 = jVar.b();
        if (Iterator.class.isAssignableFrom(b4)) {
            yVar.p();
            com.a.a.c.j[] c2 = com.a.a.c.l.o.c(jVar, Iterator.class);
            if (c2 != null && c2.length == 1) {
                b3 = c2[0];
            } else {
                b3 = com.a.a.c.l.o.b();
            }
            return a(yVar, z, b3);
        }
        if (Iterable.class.isAssignableFrom(b4)) {
            yVar.p();
            com.a.a.c.j[] c3 = com.a.a.c.l.o.c(jVar, Iterable.class);
            if (c3 != null && c3.length == 1) {
                b2 = c3[0];
            } else {
                b2 = com.a.a.c.l.o.b();
            }
            return b(yVar, z, b2);
        }
        if (CharSequence.class.isAssignableFrom(b4)) {
            return as.f611a;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.o<Object> a(com.a.a.c.aa aaVar, com.a.a.c.f.b bVar) {
        Object n = aaVar.d().n(bVar);
        if (n == null) {
            return null;
        }
        return a(aaVar, bVar, (com.a.a.c.o<?>) aaVar.b(bVar, n));
    }

    private com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.f.b bVar, com.a.a.c.o<?> oVar) {
        com.a.a.c.m.k<Object, Object> b2 = b(aaVar, bVar);
        if (b2 != null) {
            aaVar.b();
            return new aj(b2, b2.b(), oVar);
        }
        return oVar;
    }

    private static com.a.a.c.m.k<Object, Object> b(com.a.a.c.aa aaVar, com.a.a.c.f.b bVar) {
        Object s = aaVar.d().s(bVar);
        if (s == null) {
            return null;
        }
        return aaVar.a(bVar, s);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.o<?> b(com.a.a.c.aa aaVar, com.a.a.c.j jVar, com.a.a.c.b bVar, boolean z) {
        com.a.a.c.y a2 = aaVar.a();
        if (!z && jVar.r() && (!jVar.n() || !jVar.u().q())) {
            z = true;
        }
        com.a.a.c.i.i a3 = a(a2, jVar.u());
        if (a3 != null) {
            z = false;
        }
        com.a.a.c.o<Object> d = d(aaVar, bVar.d());
        if (jVar.p()) {
            com.a.a.c.l.g gVar = (com.a.a.c.l.g) jVar;
            com.a.a.c.o<Object> c2 = c(aaVar, bVar.d());
            if (gVar instanceof com.a.a.c.l.h) {
                return a(aaVar, (com.a.a.c.l.h) gVar, bVar, z, c2, a3, d);
            }
            com.a.a.c.o<?> oVar = null;
            Iterator<w.a> it = a().iterator();
            while (it.hasNext()) {
                com.a.a.c.o<?> s = it.next().s();
                oVar = s;
                if (s != null) {
                    break;
                }
            }
            if (oVar == null) {
                oVar = a(aaVar, jVar, bVar);
            }
            if (oVar != null && this.f589a.b()) {
                Iterator<i> it2 = this.f589a.e().iterator();
                while (it2.hasNext()) {
                    it2.next();
                    oVar = i.f(oVar);
                }
            }
            return oVar;
        }
        if (jVar.o()) {
            com.a.a.c.l.d dVar = (com.a.a.c.l.d) jVar;
            if (dVar instanceof com.a.a.c.l.e) {
                return a(aaVar, (com.a.a.c.l.e) dVar, bVar, z, a3, d);
            }
            com.a.a.c.o<?> oVar2 = null;
            Iterator<w.a> it3 = a().iterator();
            while (it3.hasNext()) {
                com.a.a.c.o<?> q = it3.next().q();
                oVar2 = q;
                if (q != null) {
                    break;
                }
            }
            if (oVar2 == null) {
                oVar2 = a(aaVar, jVar, bVar);
            }
            if (oVar2 != null && this.f589a.b()) {
                Iterator<i> it4 = this.f589a.e().iterator();
                while (it4.hasNext()) {
                    it4.next();
                    oVar2 = i.d(oVar2);
                }
            }
            return oVar2;
        }
        if (jVar.g()) {
            return a(aaVar, (com.a.a.c.l.a) jVar, bVar, z, a3, d);
        }
        return null;
    }

    private com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.l.e eVar, com.a.a.c.b bVar, boolean z, com.a.a.c.i.i iVar, com.a.a.c.o<Object> oVar) {
        aaVar.a();
        com.a.a.c.o<?> oVar2 = null;
        Iterator<w.a> it = a().iterator();
        while (it.hasNext()) {
            com.a.a.c.o<?> p = it.next().p();
            oVar2 = p;
            if (p != null) {
                break;
            }
        }
        if (oVar2 == null) {
            com.a.a.c.o<?> a2 = a(aaVar, eVar, bVar);
            oVar2 = a2;
            if (a2 == null) {
                if (bVar.a((l.d) null).c() == l.c.OBJECT) {
                    return null;
                }
                Class<?> b2 = eVar.b();
                if (EnumSet.class.isAssignableFrom(b2)) {
                    com.a.a.c.j u = eVar.u();
                    com.a.a.c.j jVar = u;
                    if (!u.i()) {
                        jVar = null;
                    }
                    oVar2 = b(jVar);
                } else {
                    Class<?> b3 = eVar.u().b();
                    if (a(b2)) {
                        if (b3 == String.class) {
                            if (com.a.a.c.m.i.e(oVar)) {
                                oVar2 = com.a.a.c.k.a.f.f558a;
                            }
                        } else {
                            oVar2 = a(eVar.u(), z, iVar, oVar);
                        }
                    } else if (b3 == String.class && com.a.a.c.m.i.e(oVar)) {
                        oVar2 = com.a.a.c.k.a.p.f580a;
                    }
                    if (oVar2 == null) {
                        oVar2 = b(eVar.u(), z, iVar, oVar);
                    }
                }
            }
        }
        if (this.f589a.b()) {
            Iterator<i> it2 = this.f589a.e().iterator();
            while (it2.hasNext()) {
                it2.next();
                oVar2 = i.c(oVar2);
            }
        }
        return oVar2;
    }

    private static boolean a(Class<?> cls) {
        return RandomAccess.class.isAssignableFrom(cls);
    }

    private static j<?> a(com.a.a.c.j jVar, boolean z, com.a.a.c.i.i iVar, com.a.a.c.o<Object> oVar) {
        return new com.a.a.c.k.a.e(jVar, z, iVar, oVar);
    }

    private static j<?> b(com.a.a.c.j jVar, boolean z, com.a.a.c.i.i iVar, com.a.a.c.o<Object> oVar) {
        return new com.a.a.c.k.b.k(jVar, z, iVar, oVar);
    }

    private static com.a.a.c.o<?> b(com.a.a.c.j jVar) {
        return new com.a.a.c.k.b.o(jVar);
    }

    private com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.l.h hVar, com.a.a.c.b bVar, boolean z, com.a.a.c.o<Object> oVar, com.a.a.c.i.i iVar, com.a.a.c.o<Object> oVar2) {
        if (bVar.a((l.d) null).c() == l.c.OBJECT) {
            return null;
        }
        com.a.a.c.o<?> oVar3 = null;
        com.a.a.c.y a2 = aaVar.a();
        Iterator<w.a> it = a().iterator();
        while (it.hasNext()) {
            com.a.a.c.o<?> r = it.next().r();
            oVar3 = r;
            if (r != null) {
                break;
            }
        }
        if (oVar3 == null) {
            com.a.a.c.o<?> a3 = a(aaVar, hVar, bVar);
            oVar3 = a3;
            if (a3 == null) {
                Object a4 = a(a2, bVar);
                q.a b2 = a2.b(Map.class, bVar.d());
                Set<String> b3 = b2 == null ? null : b2.b();
                t.a a5 = a2.a(bVar.d());
                oVar3 = a(aaVar, bVar, v.a(b3, a5 == null ? null : a5.b(), hVar, z, iVar, oVar, oVar2, a4));
            }
        }
        if (this.f589a.b()) {
            Iterator<i> it2 = this.f589a.e().iterator();
            while (it2.hasNext()) {
                it2.next();
                oVar3 = i.e(oVar3);
            }
        }
        return oVar3;
    }

    private v a(com.a.a.c.aa aaVar, com.a.a.c.b bVar, v vVar) {
        Object obj;
        com.a.a.c.j d = vVar.d();
        s.b a2 = a(aaVar, bVar, d, (Class<?>) Map.class);
        s.a c2 = a2 == null ? s.a.USE_DEFAULTS : a2.c();
        s.a aVar = c2;
        if (c2 == s.a.USE_DEFAULTS || aVar == s.a.ALWAYS) {
            if (!aaVar.a(z.WRITE_NULL_MAP_VALUES)) {
                return vVar.a((Object) null, true);
            }
            return vVar;
        }
        boolean z = true;
        switch (aVar) {
            case NON_DEFAULT:
                Object a3 = com.a.a.c.m.f.a(d);
                obj = a3;
                if (a3 != null && obj.getClass().isArray()) {
                    obj = com.a.a.c.m.c.a(obj);
                    break;
                }
                break;
            case NON_ABSENT:
                obj = d.F() ? v.f634a : null;
                break;
            case NON_EMPTY:
                obj = v.f634a;
                break;
            case CUSTOM:
                Object a4 = aaVar.a((com.a.a.c.f.s) null, a2.e());
                obj = a4;
                if (a4 == null) {
                    z = true;
                    break;
                } else {
                    z = aaVar.b(obj);
                    break;
                }
            default:
                obj = null;
                break;
        }
        return vVar.a(obj, z);
    }

    private com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.b bVar, boolean z, com.a.a.c.j jVar, com.a.a.c.j jVar2) {
        Object obj;
        if (l.d.a(bVar.a((l.d) null), aaVar.a(Map.Entry.class)).c() == l.c.OBJECT) {
            return null;
        }
        com.a.a.c.k.a.h hVar = new com.a.a.c.k.a.h(jVar2, jVar, jVar2, z, a(aaVar.a(), jVar2), null);
        com.a.a.c.j d = hVar.d();
        s.b a2 = a(aaVar, bVar, d, (Class<?>) Map.Entry.class);
        s.a c2 = a2 == null ? s.a.USE_DEFAULTS : a2.c();
        s.a aVar = c2;
        if (c2 == s.a.USE_DEFAULTS || aVar == s.a.ALWAYS) {
            return hVar;
        }
        boolean z2 = true;
        switch (aVar) {
            case NON_DEFAULT:
                Object a3 = com.a.a.c.m.f.a(d);
                obj = a3;
                if (a3 != null && obj.getClass().isArray()) {
                    obj = com.a.a.c.m.c.a(obj);
                    break;
                }
                break;
            case NON_ABSENT:
                obj = d.F() ? v.f634a : null;
                break;
            case NON_EMPTY:
                obj = v.f634a;
                break;
            case CUSTOM:
                Object a4 = aaVar.a((com.a.a.c.f.s) null, a2.e());
                obj = a4;
                if (a4 == null) {
                    z2 = true;
                    break;
                } else {
                    z2 = aaVar.b(obj);
                    break;
                }
            default:
                obj = null;
                break;
        }
        return hVar.a(obj, z2);
    }

    private static s.b a(com.a.a.c.aa aaVar, com.a.a.c.b bVar, com.a.a.c.j jVar, Class<?> cls) {
        com.a.a.c.y a2 = aaVar.a();
        s.b a3 = a2.a(cls, bVar.a(a2.A()));
        s.b a4 = a2.a(jVar.b(), (s.b) null);
        if (a4 != null) {
            switch (a4.b()) {
                case CUSTOM:
                    a3 = a3.a(a4.e());
                    break;
                case USE_DEFAULTS:
                    break;
                default:
                    a3 = a3.b(a4.b());
                    break;
            }
        }
        return a3;
    }

    private com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.l.a aVar, com.a.a.c.b bVar, boolean z, com.a.a.c.i.i iVar, com.a.a.c.o<Object> oVar) {
        aaVar.a();
        com.a.a.c.o<?> oVar2 = null;
        Iterator<w.a> it = a().iterator();
        while (it.hasNext()) {
            com.a.a.c.o<?> o = it.next().o();
            oVar2 = o;
            if (o != null) {
                break;
            }
        }
        if (oVar2 == null) {
            Class<?> b2 = aVar.b();
            if (oVar == null || com.a.a.c.m.i.e(oVar)) {
                if (String[].class == b2) {
                    oVar2 = com.a.a.c.k.a.o.f579a;
                } else {
                    oVar2 = ai.a(b2);
                }
            }
            if (oVar2 == null) {
                oVar2 = new com.a.a.c.k.b.ac(aVar.u(), z, iVar, oVar);
            }
        }
        if (this.f589a.b()) {
            Iterator<i> it2 = this.f589a.e().iterator();
            while (it2.hasNext()) {
                it2.next();
                oVar2 = i.b(oVar2);
            }
        }
        return oVar2;
    }

    public final com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.l.j jVar, com.a.a.c.b bVar, boolean z) {
        com.a.a.c.j u = jVar.u();
        com.a.a.c.i.i iVar = (com.a.a.c.i.i) u.B();
        com.a.a.c.y a2 = aaVar.a();
        if (iVar == null) {
            iVar = a(a2, u);
        }
        com.a.a.c.o<Object> oVar = (com.a.a.c.o) u.A();
        Iterator<w.a> it = a().iterator();
        while (it.hasNext()) {
            com.a.a.c.o<?> n = it.next().n();
            if (n != null) {
                return n;
            }
        }
        if (jVar.b(AtomicReference.class)) {
            return a(aaVar, jVar, bVar, z, iVar, oVar);
        }
        return null;
    }

    private com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.l.j jVar, com.a.a.c.b bVar, boolean z, com.a.a.c.i.i iVar, com.a.a.c.o<Object> oVar) {
        Object obj;
        boolean z2;
        com.a.a.c.j E = jVar.E();
        s.b a2 = a(aaVar, bVar, E, (Class<?>) AtomicReference.class);
        s.a c2 = a2 == null ? s.a.USE_DEFAULTS : a2.c();
        s.a aVar = c2;
        if (c2 == s.a.USE_DEFAULTS || aVar == s.a.ALWAYS) {
            obj = null;
            z2 = false;
        } else {
            z2 = true;
            switch (aVar) {
                case NON_DEFAULT:
                    Object a3 = com.a.a.c.m.f.a(E);
                    obj = a3;
                    if (a3 != null && obj.getClass().isArray()) {
                        obj = com.a.a.c.m.c.a(obj);
                        break;
                    }
                    break;
                case NON_ABSENT:
                    obj = E.F() ? v.f634a : null;
                    break;
                case NON_EMPTY:
                    obj = v.f634a;
                    break;
                case CUSTOM:
                    Object a4 = aaVar.a((com.a.a.c.f.s) null, a2.e());
                    obj = a4;
                    if (a4 == null) {
                        z2 = true;
                        break;
                    } else {
                        z2 = aaVar.b(obj);
                        break;
                    }
                default:
                    obj = null;
                    break;
            }
        }
        return new com.a.a.c.k.b.c(jVar, z, iVar, oVar).a(obj, z2);
    }

    private com.a.a.c.o<?> a(com.a.a.c.y yVar, boolean z, com.a.a.c.j jVar) {
        return new com.a.a.c.k.a.g(jVar, z, a(yVar, jVar));
    }

    private com.a.a.c.o<?> b(com.a.a.c.y yVar, boolean z, com.a.a.c.j jVar) {
        return new com.a.a.c.k.b.s(jVar, z, a(yVar, jVar));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private com.a.a.c.o<?> a(com.a.a.c.y yVar, com.a.a.c.j jVar, com.a.a.c.b bVar) {
        l.d a2 = bVar.a((l.d) null);
        if (a2.c() != l.c.OBJECT) {
            com.a.a.c.o a3 = com.a.a.c.k.b.n.a(jVar.b(), yVar, a2);
            if (this.f589a.b()) {
                Iterator<i> it = this.f589a.e().iterator();
                while (it.hasNext()) {
                    it.next();
                    a3 = i.g(a3);
                }
            }
            return a3;
        }
        ((com.a.a.c.f.q) bVar).a("declaringClass");
        return null;
    }

    private static com.a.a.c.o<Object> c(com.a.a.c.aa aaVar, com.a.a.c.f.b bVar) {
        Object o = aaVar.d().o(bVar);
        if (o != null) {
            return aaVar.b(bVar, o);
        }
        return null;
    }

    private static com.a.a.c.o<Object> d(com.a.a.c.aa aaVar, com.a.a.c.f.b bVar) {
        Object p = aaVar.d().p(bVar);
        if (p != null) {
            return aaVar.b(bVar, p);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Object a(com.a.a.c.y yVar, com.a.a.c.b bVar) {
        return yVar.j().d((com.a.a.c.f.b) bVar.d());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean a(com.a.a.c.y yVar, com.a.a.c.b bVar, com.a.a.c.i.i iVar) {
        if (iVar != null) {
            return false;
        }
        f.b r = yVar.j().r(bVar.d());
        if (r == null || r == f.b.DEFAULT_TYPING) {
            return yVar.a(com.a.a.c.q.USE_STATIC_TYPING);
        }
        return r == f.b.STATIC;
    }
}
