package com.d.e;

import com.a.a.c.k.b.ak;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: infinitode-2.jar:com/d/e/v.class */
public final class v implements com.d.c.f.d {

    /* renamed from: a, reason: collision with root package name */
    private aa f1162a;

    /* renamed from: b, reason: collision with root package name */
    private t f1163b;
    private com.d.i.x e;
    private com.d.d.j h;
    private int j;
    private int k;
    private String m;
    private String n;
    private t p;
    private com.d.i.aa q;
    private i s;
    private final com.d.b.a i = new com.d.b.a();
    private final Map<com.d.c.f.c, a> l = new HashMap();
    private int o = 0;
    private boolean r = true;
    private Boolean t = null;
    private final com.d.a.d u = new com.d.a.d();
    private com.d.a.c v = new com.d.c.d.a.c();
    private byte w = 0;
    private com.d.a.a x = new ak();
    private LinkedList<b> f = new LinkedList<>();
    private LinkedList<t> g = new LinkedList<>();
    private ab c = new ab();
    private ab d = new ab();

    public final com.d.d.r d() {
        return this.f1162a.e();
    }

    @Override // com.d.c.f.d
    public final com.d.e.a c() {
        return this.f1162a.j();
    }

    public final com.d.d.l e() {
        return this.f1162a.l();
    }

    public final com.d.a.d f() {
        return this.u;
    }

    public final void a(com.d.a.a aVar) {
        this.x = aVar;
    }

    public final com.d.a.a g() {
        return this.x;
    }

    public final com.d.a.c h() {
        return this.v;
    }

    public final void a(com.d.a.c cVar) {
        this.v = cVar;
    }

    public final byte i() {
        return this.w;
    }

    public final void a(byte b2) {
        this.w = b2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public v(aa aaVar) {
        this.f1162a = aaVar;
    }

    public final void a(boolean z) {
        this.c = new ab();
        this.d = new ab();
        this.e = null;
        this.f = new LinkedList<>();
        if (!z) {
            this.f1163b = null;
            this.g = new LinkedList<>();
        }
        this.j = 0;
        this.k = 0;
    }

    public final w j() {
        w wVar = new w();
        wVar.b(this.c);
        wVar.a(this.d);
        wVar.a(this.e);
        wVar.a(this.f);
        if (r()) {
            wVar.a(B());
            wVar.b(z());
            wVar.a(A());
            wVar.c(C());
        }
        return wVar;
    }

    public final void a(w wVar) {
        this.c = wVar.d();
        this.d = wVar.c();
        this.e = wVar.b();
        this.f = wVar.a();
        if (r()) {
            b(wVar.e());
            a(wVar.g());
            b(wVar.f());
            c(wVar.h());
        }
    }

    public final w k() {
        w wVar = new w();
        wVar.a(this.d.d());
        wVar.b(this.c.d());
        wVar.a(this.e);
        if (r()) {
            wVar.a(B());
        }
        return wVar;
    }

    public final void b(w wVar) {
        this.c = wVar.d();
        this.d = wVar.c();
        this.e = wVar.b();
        if (r()) {
            b(wVar.e());
        }
    }

    public final b l() {
        return this.f.getLast();
    }

    public final void a(b bVar) {
        this.f.add(bVar);
    }

    public final void m() {
        this.f.removeLast();
    }

    public final void a(com.d.i.f fVar) {
        t tVar;
        if (this.f1163b == null) {
            tVar = new t(fVar, this);
            this.f1163b = tVar;
        } else {
            t o = o();
            tVar = new t(o, fVar);
            o.a(tVar);
        }
        b(tVar);
    }

    private void b(t tVar) {
        this.g.add(tVar);
    }

    public final void n() {
        o().c(this);
        this.g.removeLast();
    }

    public final t o() {
        return this.g.getLast();
    }

    public final t p() {
        return this.f1163b;
    }

    public final void a(int i, int i2) {
        l().a(i, i2);
    }

    public final void a(String str, com.d.i.f fVar) {
        this.f1162a.a(str, fVar);
    }

    public final void a(String str) {
        this.f1162a.b(str);
    }

    @Override // com.d.c.f.d
    public final float a() {
        return this.f1162a.o();
    }

    @Override // com.d.c.f.d
    public final int b() {
        return this.f1162a.s();
    }

    @Override // com.d.c.f.d
    public final float a(com.d.c.g.a aVar) {
        return this.f1162a.a(aVar).a();
    }

    @Override // com.d.c.f.d
    public final float b(com.d.c.g.a aVar) {
        return this.f1162a.a(w(), aVar);
    }

    @Override // com.d.c.f.d
    public final com.d.i.k c(com.d.c.g.a aVar) {
        return this.f1162a.a(aVar);
    }

    public final com.d.d.s q() {
        return this.f1162a.n();
    }

    public final boolean r() {
        if (this.t != null) {
            return this.t.booleanValue();
        }
        return this.f1162a.r();
    }

    public final void a(Boolean bool) {
        this.t = bool;
    }

    public final ab s() {
        return this.c;
    }

    public final ab t() {
        return this.d;
    }

    public final com.d.i.x u() {
        return this.e;
    }

    public final void a(com.d.i.x xVar) {
        this.e = xVar;
    }

    public final com.d.d.o v() {
        return this.f1162a.t();
    }

    public final com.d.d.j w() {
        return this.h;
    }

    public final void a(com.d.d.j jVar) {
        this.h = jVar;
    }

    public final com.d.b.a x() {
        return this.i;
    }

    public final aa y() {
        return this.f1162a;
    }

    public final int z() {
        return this.k;
    }

    public final void a(int i) {
        this.k = i;
    }

    public final int A() {
        return this.j;
    }

    public final void b(int i) {
        this.j = i;
    }

    public final void a(com.d.c.f.c cVar, Integer num) {
        this.l.put(cVar, new a(this, cVar, num));
    }

    public final void a(com.d.c.f.c cVar) {
        a(cVar, (Integer) null);
    }

    public final a b(com.d.c.f.c cVar) {
        return this.l.get(cVar);
    }

    @Override // com.d.c.f.d
    public final com.d.i.l a(com.d.i.k kVar) {
        com.d.d.r d = d();
        w();
        return d.a(kVar);
    }

    /* loaded from: infinitode-2.jar:com/d/e/v$a.class */
    public class a {

        /* renamed from: a, reason: collision with root package name */
        private Map<String, Integer> f1164a = new HashMap();

        /* renamed from: b, reason: collision with root package name */
        private a f1165b;

        a(v vVar, com.d.c.f.c cVar, Integer num) {
            if (num != null) {
                this.f1164a.put("list-item", num);
            }
            this.f1165b = (a) vVar.l.get(cVar.a());
            if (this.f1165b == null) {
                this.f1165b = new a(vVar);
            }
            List<ad> f = cVar.f();
            if (f != null) {
                a aVar = this.f1165b;
                Objects.requireNonNull(aVar);
                f.forEach(aVar::b);
            }
            List<ad> g = cVar.g();
            if (g != null) {
                for (ad adVar : g) {
                    if (!this.f1165b.a(adVar)) {
                        this.f1165b.b(new ad(adVar.a(), 0));
                        this.f1165b.a(adVar);
                    }
                }
            }
            if (cVar.a(com.d.c.a.a.G, com.d.c.a.c.ae)) {
                if (num != null) {
                    this.f1165b.f1164a.put("list-item", num);
                }
                this.f1165b.a(1);
            }
        }

        private a(v vVar) {
        }

        private boolean a(ad adVar) {
            if ("list-item".equals(adVar.a())) {
                a(adVar.b());
                return true;
            }
            Integer num = this.f1164a.get(adVar.a());
            if (num != null) {
                this.f1164a.put(adVar.a(), new Integer(num.intValue() + adVar.b()));
                return true;
            }
            if (this.f1165b == null) {
                return false;
            }
            return this.f1165b.a(adVar);
        }

        private void a(int i) {
            Integer num = this.f1164a.get("list-item");
            Integer num2 = num;
            if (num == null) {
                num2 = 0;
            }
            this.f1164a.put("list-item", new Integer(num2.intValue() + i));
        }

        private void b(ad adVar) {
            this.f1164a.put(adVar.a(), new Integer(adVar.b()));
        }

        public final int a(String str) {
            Integer c = this.f1165b.c(str);
            if (c == null) {
                this.f1165b.b(new ad(str, 0));
                return 0;
            }
            return c.intValue();
        }

        private Integer c(String str) {
            Integer num = this.f1164a.get(str);
            if (num != null) {
                return num;
            }
            if (this.f1165b == null) {
                return null;
            }
            return this.f1165b.c(str);
        }

        public final List<Integer> b(String str) {
            ArrayList arrayList = new ArrayList();
            this.f1165b.a(str, arrayList);
            if (arrayList.size() == 0) {
                this.f1165b.b(new ad(str, 0));
                arrayList.add(0);
            }
            return arrayList;
        }

        private void a(String str, List<Integer> list) {
            if (this.f1165b != null) {
                this.f1165b.a(str, list);
            }
            Integer num = this.f1164a.get(str);
            if (num != null) {
                list.add(num);
            }
        }
    }

    public final String B() {
        return this.n;
    }

    public final void b(String str) {
        this.n = str;
    }

    public final int C() {
        return this.o;
    }

    public final void c(int i) {
        this.o = i;
    }

    public final boolean D() {
        return this.o == 0;
    }

    public final String E() {
        return this.m;
    }

    public final void c(String str) {
        this.m = str;
    }

    public final t F() {
        return this.p;
    }

    public final void a(t tVar) {
        this.p = tVar;
    }

    public final com.d.i.aa G() {
        return this.q;
    }

    public final void a(com.d.i.aa aaVar) {
        this.q = aaVar;
    }

    public final boolean H() {
        return this.r;
    }

    public final void b(boolean z) {
        this.r = z;
    }

    public final i I() {
        return this.s;
    }

    public final void a(i iVar) {
        this.s = iVar;
    }
}
