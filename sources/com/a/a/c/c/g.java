package com.a.a.c.c;

import com.a.a.a.l;
import com.a.a.c.a.e;
import com.a.a.c.c.a.ae;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/c/g.class */
public final class g {

    /* renamed from: a, reason: collision with root package name */
    private com.a.a.c.f f403a;

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.c.g f404b;
    private com.a.a.c.b c;
    private Map<String, v> d = new LinkedHashMap();
    private List<ae> e;
    private HashMap<String, v> f;
    private HashSet<String> g;
    private HashSet<String> h;
    private x i;
    private com.a.a.c.c.a.s j;
    private u k;
    private boolean l;
    private com.a.a.c.f.k m;

    public g(com.a.a.c.b bVar, com.a.a.c.g gVar) {
        this.c = bVar;
        this.f404b = gVar;
        this.f403a = gVar.a();
    }

    public final void a(v vVar) {
        this.d.put(vVar.a(), vVar);
    }

    public final void b(v vVar) {
        v put = this.d.put(vVar.a(), vVar);
        if (put != null && put != vVar) {
            throw new IllegalArgumentException("Duplicate property '" + vVar.a() + "' for " + this.c.a());
        }
    }

    public final void a(String str, v vVar) {
        if (this.f == null) {
            this.f = new HashMap<>(4);
        }
        if (this.f403a.g()) {
            try {
                vVar.a(this.f403a);
            } catch (IllegalArgumentException e) {
                a(e);
            }
        }
        this.f.put(str, vVar);
    }

    public final void a(com.a.a.c.w wVar, com.a.a.c.j jVar, com.a.a.c.f.j jVar2, Object obj) {
        if (this.e == null) {
            this.e = new ArrayList();
        }
        if (this.f403a.g()) {
            try {
                jVar2.a(this.f403a.a(com.a.a.c.q.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
            } catch (IllegalArgumentException e) {
                a(e);
            }
        }
        this.e.add(new ae(wVar, jVar, jVar2, obj));
    }

    public final void a(String str) {
        if (this.g == null) {
            this.g = new HashSet<>();
        }
        this.g.add(str);
    }

    public final void b(String str) {
        if (this.h == null) {
            this.h = new HashSet<>();
        }
        this.h.add(str);
    }

    public final void c(v vVar) {
        b(vVar);
    }

    public final void a(u uVar) {
        if (this.k != null && uVar != null) {
            throw new IllegalStateException("_anySetter already set to non-null");
        }
        this.k = uVar;
    }

    public final void a(boolean z) {
        this.l = z;
    }

    public final void a(x xVar) {
        this.i = xVar;
    }

    public final void a(com.a.a.c.c.a.s sVar) {
        this.j = sVar;
    }

    public final void a(com.a.a.c.f.k kVar, e.a aVar) {
        this.m = kVar;
    }

    public final Iterator<v> a() {
        return this.d.values().iterator();
    }

    public final v a(com.a.a.c.w wVar) {
        return this.d.get(wVar.b());
    }

    public final u b() {
        return this.k;
    }

    public final x c() {
        return this.i;
    }

    public final List<ae> d() {
        return this.e;
    }

    public final com.a.a.c.c.a.s e() {
        return this.j;
    }

    public final com.a.a.c.f.k f() {
        return this.m;
    }

    public final boolean c(String str) {
        return com.a.a.c.m.n.a(str, this.g, this.h);
    }

    public final com.a.a.c.k<?> g() {
        Collection<v> values = this.d.values();
        a(values);
        com.a.a.c.c.a.c a2 = com.a.a.c.c.a.c.a(this.f403a, values, b(values), i());
        com.a.a.c.c.a.c cVar = a2;
        a2.a();
        boolean z = !this.f403a.a(com.a.a.c.q.DEFAULT_VIEW_INCLUSION);
        boolean z2 = z;
        if (!z) {
            Iterator<v> it = values.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (it.next().s()) {
                    z2 = true;
                    break;
                }
            }
        }
        if (this.j != null) {
            cVar = cVar.a(new com.a.a.c.c.a.u(this.j, com.a.a.c.v.f766a));
        }
        return new d(this, this.c, cVar, this.f, this.g, this.l, this.h, z2);
    }

    public final a h() {
        return new a(this, this.c, this.f, this.d);
    }

    public final com.a.a.c.k<?> a(com.a.a.c.j jVar, String str) {
        if (this.m == null) {
            if (!str.isEmpty()) {
                this.f404b.a(this.c.a(), String.format("Builder class %s does not have build method (name: '%s')", com.a.a.c.m.i.b(this.c.a()), str));
            }
        } else {
            Class<?> m = this.m.m();
            Class<?> b2 = jVar.b();
            if (m != b2 && !m.isAssignableFrom(b2) && !b2.isAssignableFrom(m)) {
                this.f404b.a(this.c.a(), String.format("Build method `%s` has wrong return type (%s), not compatible with POJO type (%s)", this.m.j(), com.a.a.c.m.i.c((Object) m), com.a.a.c.m.i.b(jVar)));
            }
        }
        Collection<v> values = this.d.values();
        a(values);
        com.a.a.c.c.a.c a2 = com.a.a.c.c.a.c.a(this.f403a, values, b(values), i());
        com.a.a.c.c.a.c cVar = a2;
        a2.a();
        boolean z = !this.f403a.a(com.a.a.c.q.DEFAULT_VIEW_INCLUSION);
        boolean z2 = z;
        if (!z) {
            Iterator<v> it = values.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (it.next().s()) {
                    z2 = true;
                    break;
                }
            }
        }
        if (this.j != null) {
            cVar = cVar.a(new com.a.a.c.c.a.u(this.j, com.a.a.c.v.f766a));
        }
        return a(jVar, cVar, z2);
    }

    private com.a.a.c.k<?> a(com.a.a.c.j jVar, com.a.a.c.c.a.c cVar, boolean z) {
        return new i(this, this.c, jVar, cVar, this.f, this.g, this.l, this.h, z);
    }

    private void a(Collection<v> collection) {
        if (this.f403a.g()) {
            Iterator<v> it = collection.iterator();
            while (it.hasNext()) {
                try {
                    it.next().a(this.f403a);
                } catch (IllegalArgumentException e) {
                    a(e);
                }
            }
        }
        if (this.k != null) {
            try {
                this.k.a(this.f403a);
            } catch (IllegalArgumentException e2) {
                a(e2);
            }
        }
        if (this.m != null) {
            try {
                this.m.a(this.f403a.a(com.a.a.c.q.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
            } catch (IllegalArgumentException e3) {
                a(e3);
            }
        }
    }

    private Map<String, List<com.a.a.c.w>> b(Collection<v> collection) {
        HashMap hashMap = null;
        com.a.a.c.a j = this.f403a.j();
        if (j != null) {
            for (v vVar : collection) {
                List<com.a.a.c.w> l = j.l(vVar.e());
                if (l != null && !l.isEmpty()) {
                    if (hashMap == null) {
                        hashMap = new HashMap();
                    }
                    hashMap.put(vVar.a(), l);
                }
            }
        }
        if (hashMap == null) {
            return Collections.emptyMap();
        }
        return hashMap;
    }

    private boolean i() {
        Boolean a2 = this.c.a((l.d) null).a(l.a.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        return a2 == null ? this.f403a.a(com.a.a.c.q.ACCEPT_CASE_INSENSITIVE_PROPERTIES) : a2.booleanValue();
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [com.a.a.c.e, java.lang.Object] */
    private void a(IllegalArgumentException illegalArgumentException) {
        ?? a2;
        try {
            a2 = this.f404b.a(this.c, illegalArgumentException.getMessage(), new Object[0]);
        } catch (com.a.a.c.e e) {
            if (a2.getCause() == null) {
                e.initCause(illegalArgumentException);
            }
            throw e;
        }
    }
}
