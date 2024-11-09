package com.d.e;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/e/af.class */
public final class af {

    /* renamed from: b, reason: collision with root package name */
    private int f1119b;
    private int d;
    private int f;
    private int h;

    /* renamed from: a, reason: collision with root package name */
    private List f1118a = new ArrayList();
    private boolean c = false;
    private boolean e = false;
    private boolean g = false;
    private boolean i = false;
    private List j = new ArrayList();
    private af k = null;

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        if (this.c) {
            this.f1119b += i;
        }
        if (this.e) {
            this.d += i;
        }
        if (this.g) {
            this.f += i;
        }
        if (this.i) {
            this.h += i;
        }
    }

    public final int a() {
        return this.d;
    }

    public final int b() {
        return this.f1119b;
    }

    public final void a(int i) {
        if (!this.c || i < this.f1119b) {
            this.f1119b = i;
            this.c = true;
        }
    }

    public final void b(int i) {
        if (!this.g || i < this.f) {
            this.f = i;
            this.g = true;
        }
    }

    public final void c(int i) {
        if (!this.e || i > this.d) {
            this.d = i;
            this.e = true;
        }
    }

    public final void d(int i) {
        if (!this.i || i > this.h) {
            this.h = i;
            this.i = true;
        }
    }

    public final int c() {
        return this.d - this.f1119b;
    }

    public final void a(p pVar) {
        this.f1118a.add(pVar);
        a(pVar.c());
        c(pVar.b());
        b(pVar.g());
        d(pVar.f());
    }

    public final p d() {
        return (p) this.f1118a.get(this.f1118a.size() - 1);
    }

    public final void e() {
        this.f1118a.remove(this.f1118a.size() - 1);
    }

    public final int f() {
        return this.h;
    }

    public final int g() {
        return this.f;
    }

    public final af a(com.d.i.f fVar) {
        af afVar = new af();
        af k = k();
        afVar.b(k);
        afVar.a((p) k.f1118a.get(0));
        if (k.j == null) {
            k.j = new ArrayList();
        }
        k.j.add(new a(fVar, afVar));
        return afVar;
    }

    private List i() {
        return this.j == null ? Collections.EMPTY_LIST : this.j;
    }

    private af j() {
        return this.k;
    }

    private void b(af afVar) {
        this.k = afVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public af k() {
        return j() != null ? j() : this;
    }

    private void c(af afVar) {
        c(afVar.a());
        a(afVar.b());
        d(afVar.f());
        b(afVar.g());
    }

    public final void h() {
        List i = i();
        for (int i2 = 0; i2 < i.size(); i2++) {
            a aVar = (a) i.get(i2);
            aVar.b();
            c(aVar.a());
        }
    }

    public final void b(p pVar) {
        this.f1118a.add(pVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/e/af$a.class */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private com.d.i.f f1120a;

        /* renamed from: b, reason: collision with root package name */
        private af f1121b;

        public a() {
        }

        public a(com.d.i.f fVar, af afVar) {
            this.f1120a = fVar;
            this.f1121b = afVar;
        }

        public final af a() {
            return this.f1121b;
        }

        private void a(int i) {
            a(this.f1120a, i);
        }

        private void a(com.d.i.f fVar, int i) {
            if (a(fVar)) {
                fVar.o(fVar.an() + i);
                if (fVar instanceof com.d.i.r) {
                    com.d.i.r rVar = (com.d.i.r) fVar;
                    for (int i2 = 0; i2 < rVar.e(); i2++) {
                        Object b2 = rVar.b(i2);
                        if (b2 instanceof com.d.i.f) {
                            a((com.d.i.f) b2, i);
                        }
                    }
                }
            }
        }

        private boolean a(com.d.i.f fVar) {
            com.d.c.a.c e = fVar.a().e(com.d.c.a.a.as);
            if (fVar != this.f1120a) {
                return (e == com.d.c.a.c.bi || e == com.d.c.a.c.l) ? false : true;
            }
            return true;
        }

        public final void b() {
            int a2;
            com.d.c.a.c e = this.f1120a.a().e(com.d.c.a.a.as);
            if (e == com.d.c.a.c.bi) {
                a2 = this.f1121b.k().b() - this.f1121b.b();
            } else if (e == com.d.c.a.c.l) {
                a2 = this.f1121b.k().a() - this.f1121b.a();
            } else {
                throw new RuntimeException("internal error");
            }
            this.f1121b.e(a2);
            a(a2);
        }
    }
}
