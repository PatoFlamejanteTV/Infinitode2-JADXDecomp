package com.d.e;

import com.d.d.l;
import com.d.d.s;
import com.d.d.t;
import java.awt.Dimension;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import java.util.logging.Level;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/* loaded from: infinitode-2.jar:com/d/e/a.class */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private aa f1099a;

    /* renamed from: b, reason: collision with root package name */
    private com.d.d.l f1100b;
    private Document c;
    private final com.d.b.c d;
    private com.d.c.c.c e;
    private com.d.d.s f;

    public static void a(v vVar, com.d.i.c cVar, int i) {
        int i2 = -1;
        List<com.d.i.f> X = cVar.X();
        if (vVar.r() && !(X instanceof RandomAccess)) {
            X = new ArrayList(X);
        }
        int as = cVar.as() + i;
        b bVar = null;
        if (vVar.r()) {
            bVar = new b(X.size());
        }
        int i3 = -1;
        com.d.i.c cVar2 = null;
        Iterator<com.d.i.f> it = X.iterator();
        while (it.hasNext()) {
            com.d.i.c cVar3 = (com.d.i.c) it.next();
            i2++;
            C0022a c0022a = null;
            boolean z = false;
            if (vVar.r()) {
                C0022a a2 = bVar.a(i2);
                c0022a = a2;
                a2.a(vVar.k());
                c0022a.a(as);
                i3 = vVar.p().k().size();
                cVar3.e(false);
                if ((cVar3.a().ad() || cVar3.a().av()) && vVar.H()) {
                    z = true;
                    vVar.b(false);
                }
            }
            a(vVar, cVar, cVar3, false, as, -1, c0022a == null ? null : c0022a.c());
            if (vVar.r()) {
                boolean D = cVar3.D();
                if (D || z) {
                    vVar.b(z);
                    boolean z2 = cVar3.a().ad() && cVar3.u(vVar);
                    boolean p = cVar3.p(vVar);
                    if (z2 || D || p) {
                        vVar.b(c0022a.c());
                        cVar3.c(vVar);
                        a(vVar, cVar, cVar3, true, as, i3, c0022a.c());
                        if (z2 && cVar3.u(vVar) && !p) {
                            vVar.b(c0022a.c());
                            cVar3.c(vVar);
                            a(vVar, cVar, cVar3, false, as, i3, c0022a.c());
                        }
                    }
                }
                vVar.p().c(vVar, cVar3);
            }
            Dimension ag = cVar3.ag();
            if (ag != null) {
                as = (cVar3.an() - ag.height) + cVar3.as();
            } else {
                as = cVar3.an() + cVar3.as();
            }
            if (as > cVar.as()) {
                cVar.t(as);
            }
            if (vVar.r()) {
                if (cVar3.a().ac()) {
                    cVar.a(vVar, cVar3.a().e(com.d.c.a.a.ae));
                    as = cVar.as();
                }
                if (cVar2 != null) {
                    bVar.a(i2, cVar2, cVar3);
                }
                c a3 = a(vVar, cVar, X, i2, bVar, c0022a);
                if (a3.a()) {
                    int b2 = a3.b();
                    as = b2;
                    if (b2 > cVar.as()) {
                        cVar.t(as);
                    }
                }
            }
            cVar2 = cVar3;
        }
    }

    private static c a(v vVar, com.d.i.c cVar, List list, int i, b bVar, C0022a c0022a) {
        c cVar2 = new c((byte) 0);
        if (i > 0) {
            boolean z = false;
            int i2 = 0;
            if (i == list.size() - 1 && c0022a.a()) {
                z = true;
                i2 = i;
            } else if (i > 0 && bVar.a(i - 1).a()) {
                z = true;
                i2 = i - 1;
            }
            if (z) {
                int b2 = bVar.b(i2);
                if (a(b2, i2, vVar, cVar)) {
                    cVar2.a(true);
                    cVar.a(vVar, b2, i);
                    cVar2.a(a(vVar, list, cVar, bVar, b2, i, true));
                    if (a(b2, i2, vVar, cVar)) {
                        cVar.a(vVar, b2, i);
                        cVar2.a(a(vVar, list, cVar, bVar, b2, i, false));
                    }
                }
            }
        }
        return cVar2;
    }

    private static boolean a(int i, int i2, v vVar, com.d.i.c cVar) {
        for (int i3 = i; i3 < i2; i3++) {
            com.d.i.f k = cVar.k(i3);
            com.d.i.f k2 = cVar.k(i3 + 1);
            com.d.i.f a2 = a(k2) == null ? k2 : a(k2);
            if (vVar.p().a(vVar, k.aa() + k.as(), a2.aa() + a2.as())) {
                return true;
            }
        }
        return false;
    }

    private static com.d.i.u a(com.d.i.f fVar) {
        com.d.i.f fVar2 = fVar;
        while (true) {
            com.d.i.f fVar3 = fVar2;
            if (fVar3.V() > 0) {
                if (!(fVar3 instanceof com.d.i.u)) {
                    fVar2 = fVar3.k(0);
                } else {
                    return (com.d.i.u) fVar3;
                }
            } else {
                return null;
            }
        }
    }

    private static int a(v vVar, List list, com.d.i.c cVar, b bVar, int i, int i2, boolean z) {
        int e = bVar.a(i).e();
        if (z) {
            com.d.i.f fVar = (com.d.i.f) list.get(i);
            e += vVar.p().a(vVar, fVar).a() - fVar.aa();
        }
        cVar.t(e);
        for (int i3 = i; i3 <= i2; i3++) {
            com.d.i.c cVar2 = (com.d.i.c) list.get(i3);
            C0022a a2 = bVar.a(i3);
            int size = vVar.p().k().size();
            vVar.b(a2.c());
            a2.a(e);
            boolean z2 = false;
            if ((cVar2.a().ad() || cVar2.a().av()) && vVar.H()) {
                z2 = true;
                vVar.b(false);
            }
            a(vVar, cVar, cVar2, false, e, -1, a2.c());
            if (z2) {
                vVar.b(true);
                boolean z3 = cVar2.a().ad() && cVar2.u(vVar);
                boolean D = cVar2.D();
                boolean p = cVar2.p(vVar);
                if (z3 || D || p) {
                    vVar.b(a2.c());
                    cVar2.c(vVar);
                    a(vVar, cVar, cVar2, true, e, size, a2.c());
                    if (z3 && cVar2.u(vVar) && !p) {
                        vVar.b(a2.c());
                        cVar2.c(vVar);
                        a(vVar, cVar, cVar2, false, e, size, a2.c());
                    }
                }
            }
            vVar.p().c(vVar, cVar2);
            Dimension ag = cVar2.ag();
            if (ag != null) {
                e = (cVar2.an() - ag.height) + cVar2.as();
            } else {
                e = cVar2.an() + cVar2.as();
            }
            if (e > cVar.as()) {
                cVar.t(e);
            }
            if (cVar2.a().ac()) {
                cVar.a(vVar, cVar2.a().e(com.d.c.a.a.ae));
                e = cVar.as();
            }
        }
        return e;
    }

    private static void a(v vVar, com.d.i.c cVar, com.d.i.c cVar2, boolean z, int i, int i2, w wVar) {
        a(vVar, cVar, cVar2, z, i, i2);
        i o = cVar2.o(vVar);
        if (o != null) {
            vVar.a(o);
            vVar.b(wVar);
            cVar2.c(vVar);
            a(vVar, cVar, cVar2, z, i, i2);
            vVar.a((i) null);
        }
    }

    private static void a(v vVar, com.d.i.c cVar, com.d.i.c cVar2, boolean z, int i, int i2) {
        cVar2.e(z);
        cVar2.a(vVar, cVar, i);
        cVar2.r(vVar);
        cVar2.B();
        vVar.a(0, i);
        b(vVar, cVar2, i2);
        cVar2.b(vVar);
        vVar.a(-cVar2.am(), -cVar2.an());
    }

    private static void b(v vVar, com.d.i.c cVar, int i) {
        boolean z = false;
        if (cVar.a().F()) {
            Dimension m = cVar.m((com.d.c.f.d) vVar);
            vVar.a(m.width, m.height);
            z = true;
        }
        if (vVar.r()) {
            boolean z2 = cVar.D() || cVar.a().ab() || cVar.i(vVar);
            boolean q = cVar.q(vVar);
            if (q && i != -1) {
                vVar.p().d(i);
            }
            if (z2 || q) {
                vVar.a(0, cVar.a(vVar, cVar.a().e(com.d.c.a.a.af), q));
                z = true;
                cVar.e(false);
            }
        }
        if (z) {
            cVar.B();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/e/a$b.class */
    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private List f1103a;

        public b(int i) {
            this.f1103a = new ArrayList(i);
            for (int i2 = 0; i2 < i; i2++) {
                this.f1103a.add(new C0022a());
            }
        }

        public final C0022a a(int i) {
            return (C0022a) this.f1103a.get(i);
        }

        public final void a(int i, com.d.i.c cVar, com.d.i.c cVar2) {
            C0022a a2 = a(i - 1);
            C0022a a3 = a(i);
            com.d.c.a.c e = cVar.a().e(com.d.c.a.a.ae);
            com.d.c.a.c e2 = cVar2.a().e(com.d.c.a.a.af);
            if ((e == com.d.c.a.c.f && e2 == com.d.c.a.c.e) || ((e == com.d.c.a.c.e && e2 == com.d.c.a.c.f) || (e == com.d.c.a.c.f && e2 == com.d.c.a.c.f))) {
                if (!a2.b()) {
                    a2.c(true);
                }
                a2.b(true);
                a3.b(true);
                if (i == this.f1103a.size() - 1) {
                    a3.a(true);
                    return;
                }
                return;
            }
            if (a2.b()) {
                a2.a(true);
            }
        }

        public final int b(int i) {
            int i2 = i;
            C0022a a2 = a(i2);
            C0022a c0022a = a2;
            if (!a2.a()) {
                throw new RuntimeException("Not the end of a run");
            }
            while (!c0022a.d()) {
                i2--;
                c0022a = a(i2);
            }
            return i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/e/a$c.class */
    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private boolean f1104a;

        /* renamed from: b, reason: collision with root package name */
        private int f1105b;

        private c() {
        }

        /* synthetic */ c(byte b2) {
            this();
        }

        public final boolean a() {
            return this.f1104a;
        }

        public final void a(boolean z) {
            this.f1104a = true;
        }

        public final int b() {
            return this.f1105b;
        }

        public final void a(int i) {
            this.f1105b = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.d.e.a$a, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:com/d/e/a$a.class */
    public static class C0022a {

        /* renamed from: a, reason: collision with root package name */
        private w f1101a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f1102b;
        private boolean c;
        private boolean d;
        private int e;

        public final boolean a() {
            return this.c;
        }

        public final void a(boolean z) {
            this.c = true;
        }

        public final boolean b() {
            return this.d;
        }

        public final void b(boolean z) {
            this.d = true;
        }

        public final w c() {
            return this.f1101a;
        }

        public final void a(w wVar) {
            this.f1101a = wVar;
        }

        public final boolean d() {
            return this.f1102b;
        }

        public final void c(boolean z) {
            this.f1102b = true;
        }

        public final int e() {
            return this.e;
        }

        public final void a(int i) {
            this.e = i;
        }
    }

    public a(com.d.d.s sVar) {
        this.f = sVar;
        this.d = new com.d.b.c(sVar);
    }

    public void a(aa aaVar, com.d.d.l lVar, Document document, final com.d.d.t tVar) {
        this.f1099a = aaVar;
        this.f1100b = lVar;
        this.c = document;
        final com.d.d.l lVar2 = this.f1100b;
        final com.d.d.s sVar = this.f;
        com.d.c.b.a aVar = new com.d.c.b.a(lVar2, sVar, tVar) { // from class: com.d.b.b

            /* renamed from: a, reason: collision with root package name */
            private l f960a;

            /* renamed from: b, reason: collision with root package name */
            private s f961b;
            private t c;

            {
                this.f960a = lVar2;
                this.f961b = sVar;
                this.c = tVar;
            }

            @Override // com.d.c.b.a
            public String a(Object obj, String str, String str2) {
                return this.f960a.a((Element) obj, str, str2);
            }

            @Override // com.d.c.b.a
            public String a(Object obj) {
                return this.f960a.a((Element) obj);
            }

            @Override // com.d.c.b.a
            public String b(Object obj) {
                return this.f960a.b((Element) obj);
            }

            @Override // com.d.c.b.a
            public String c(Object obj) {
                return this.f960a.d((Element) obj);
            }

            @Override // com.d.c.b.a
            public String d(Object obj) {
                return this.f960a.c((Element) obj);
            }

            @Override // com.d.c.b.a
            public String e(Object obj) {
                return this.f960a.e((Element) obj);
            }

            @Override // com.d.c.b.a
            public boolean f(Object obj) {
                return this.f960a.f((Element) obj) != null;
            }

            @Override // com.d.c.b.a
            public boolean g(Object obj) {
                if (!f(obj)) {
                    return false;
                }
                this.f960a.f((Element) obj);
                return false;
            }

            @Override // com.d.c.b.a
            public boolean h(Object obj) {
                return false;
            }

            @Override // com.d.c.b.a
            public boolean i(Object obj) {
                return false;
            }

            @Override // com.d.c.b.a
            public boolean j(Object obj) {
                return false;
            }
        };
        List<com.d.l.b> d = d();
        com.d.m.l.f("media = " + this.f1099a.d());
        this.e = new com.d.c.c.c(new q(), aVar, this.d, a(d, this.f1099a.d()), this.f1099a.d());
    }

    private List<com.d.i.a.r> a(List<com.d.l.b> list, String str) {
        ArrayList arrayList = new ArrayList(list.size() + 15);
        for (com.d.l.b bVar : list) {
            if (bVar.a(str)) {
                com.d.i.a.r d = bVar.d();
                com.d.i.a.r rVar = d;
                if (d == null) {
                    rVar = this.d.a(bVar);
                }
                if (rVar == null) {
                    com.d.m.l.e(Level.WARNING, "Unable to load CSS from " + bVar.a());
                } else {
                    if (rVar.d().size() > 0) {
                        arrayList.addAll(a(rVar.d(), str));
                    }
                    arrayList.add(rVar);
                }
            }
        }
        return arrayList;
    }

    public com.d.c.c.a a(Node node, String str) {
        Element element;
        if (node.getNodeType() == 1) {
            element = (Element) node;
        } else {
            element = (Element) node.getParentNode();
        }
        return this.e.a(element, str);
    }

    public com.d.c.c.a a(Element element, boolean z) {
        return element == null ? com.d.c.c.a.f977a : this.e.a(element, z);
    }

    public com.d.c.c.e a(String str, String str2) {
        return this.e.a(str, str2);
    }

    @Deprecated
    public void a() {
        String a2 = this.f.a();
        com.d.l.b bVar = new com.d.l.b();
        bVar.b(a2);
        bVar.a(2);
        if (this.d.a(a2)) {
            this.d.b(a2);
            com.d.m.l.a("Removing stylesheet '" + a2 + "' from cache by request.");
        } else {
            com.d.m.l.a("Requested removing stylesheet '" + a2 + "', but it's not in cache.");
        }
    }

    @Deprecated
    public void b() {
        this.d.a();
    }

    private List<com.d.l.b> d() {
        ArrayList arrayList = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        com.d.l.b a2 = this.f1100b.a(this.d);
        if (a2 != null) {
            arrayList.add(a2);
        }
        com.d.l.b[] a3 = this.f1100b.a(this.c);
        int i = 0;
        if (a3 != null) {
            for (com.d.l.b bVar : a3) {
                if (!bVar.f()) {
                    bVar.b(this.f.f(bVar.a()));
                } else {
                    i++;
                    bVar.b(this.f.a() + "#inline_style_" + i);
                    bVar.a(this.d.a(new StringReader(bVar.e()), bVar));
                    bVar.b(null);
                }
            }
            arrayList.addAll(Arrays.asList(a3));
        }
        com.d.m.l.e("TIME: parse stylesheets  " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
        return arrayList;
    }

    public List<com.d.c.e.a> c() {
        return this.e.a();
    }

    public void a(com.d.d.s sVar) {
        this.f = sVar;
        this.d.a(sVar);
    }

    public void a(boolean z) {
        this.d.a(true);
    }
}
