package com.d.b;

import com.d.c.d.j;
import com.d.e.l;
import com.d.e.q;
import com.d.e.v;
import com.d.i.ab;
import com.d.i.r;
import com.d.i.s;
import com.d.i.u;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.utility.JavaConstant;

/* loaded from: infinitode-2.jar:com/d/b/a.class */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private final List<com.d.c.b.b> f959a = new ArrayList();

    public a() {
        this.f959a.add(new c((byte) 0));
        this.f959a.add(new e((byte) 0));
        this.f959a.add(new f((byte) 0));
        this.f959a.add(new b((byte) 0));
        this.f959a.add(new C0017a((byte) 0));
    }

    public final com.d.c.b.b a(v vVar, com.d.i.e eVar) {
        return this.f959a.stream().filter(bVar -> {
            return bVar.a(vVar, eVar);
        }).findFirst().orElse(null);
    }

    /* renamed from: com.d.b.a$a, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:com/d/b/a$a.class */
    static class C0017a implements com.d.c.b.b {
        private C0017a() {
        }

        /* synthetic */ C0017a(byte b2) {
            this();
        }

        @Override // com.d.c.b.b
        public final String a(ab abVar, com.d.i.e eVar, s sVar) {
            if (abVar.w() >= 0) {
                return eVar.b().get(0).c();
            }
            return "";
        }

        @Override // com.d.c.b.b
        public final String a() {
            return "cont";
        }

        @Override // com.d.c.b.b
        public final boolean a(v vVar, com.d.i.e eVar) {
            return eVar.a().equals("-fs-if-cut-off") && eVar.b().size() == 1 && eVar.b().get(0).a() == 19;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/b/a$d.class */
    static abstract class d implements com.d.c.b.b {
        private d() {
        }

        /* synthetic */ d(byte b2) {
            this();
        }

        @Override // com.d.c.b.b
        public final String a() {
            return "999";
        }

        protected static com.d.c.a.c a(com.d.i.e eVar) {
            com.d.c.a.c b2;
            com.d.c.a.c cVar = com.d.c.a.c.v;
            List<j> b3 = eVar.b();
            if (b3.size() == 2 && (b2 = com.d.c.a.c.b(b3.get(1).c())) != null) {
                cVar = b2;
            }
            return cVar;
        }

        protected static boolean a(com.d.i.e eVar, String str) {
            if (eVar.a().equals("counter")) {
                List<j> b2 = eVar.b();
                if (b2.size() == 1 || b2.size() == 2) {
                    j jVar = b2.get(0);
                    if (jVar.a() == 21 && jVar.c().equals(str)) {
                        return b2.size() != 2 || b2.get(1).a() == 21;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/b/a$c.class */
    static class c extends d {
        private c() {
            super((byte) 0);
        }

        /* synthetic */ c(byte b2) {
            this();
        }

        @Override // com.d.c.b.b
        public final String a(ab abVar, com.d.i.e eVar, s sVar) {
            return l.a(a(eVar), abVar.u().c(abVar) + 1);
        }

        @Override // com.d.c.b.b
        public final boolean a(v vVar, com.d.i.e eVar) {
            return vVar.r() && a(eVar, "page");
        }
    }

    /* loaded from: infinitode-2.jar:com/d/b/a$e.class */
    static class e extends d {
        private e() {
            super((byte) 0);
        }

        /* synthetic */ e(byte b2) {
            this();
        }

        @Override // com.d.c.b.b
        public final String a(ab abVar, com.d.i.e eVar, s sVar) {
            return l.a(a(eVar), abVar.u().d(abVar));
        }

        @Override // com.d.c.b.b
        public final boolean a(v vVar, com.d.i.e eVar) {
            return vVar.r() && a(eVar, "pages");
        }
    }

    /* loaded from: infinitode-2.jar:com/d/b/a$f.class */
    static class f implements com.d.c.b.b {
        private f() {
        }

        /* synthetic */ f(byte b2) {
            this();
        }

        @Override // com.d.c.b.b
        public final String a(ab abVar, com.d.i.e eVar, s sVar) {
            com.d.i.f a2;
            String attribute = sVar.g().ai().getAttribute("href");
            if (attribute != null && attribute.startsWith("#") && (a2 = abVar.a(attribute.substring(1))) != null) {
                return l.a(com.d.c.a.c.v, abVar.u().a(abVar, a2.aa()) + 1);
            }
            return "";
        }

        @Override // com.d.c.b.b
        public final String a() {
            return "999";
        }

        @Override // com.d.c.b.b
        public final boolean a(v vVar, com.d.i.e eVar) {
            com.d.i.e n;
            if (vVar.r() && eVar.a().equals("target-counter")) {
                List<j> b2 = eVar.b();
                if ((b2.size() != 2 && b2.size() != 3) || (n = b2.get(0).n()) == null || n.b().size() != 1 || n.b().get(0).a() != 21 || !n.b().get(0).c().equals("href")) {
                    return false;
                }
                j jVar = b2.get(1);
                return jVar.a() == 21 && jVar.c().equals("page");
            }
            return false;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/b/a$b.class */
    static class b implements com.d.c.b.b {
        private b() {
        }

        /* synthetic */ b(byte b2) {
            this();
        }

        @Override // com.d.c.b.b
        public final String a(ab abVar, com.d.i.e eVar, s sVar) {
            r g = sVar.g();
            u k = g.k();
            boolean z = false;
            Iterator<com.d.i.f> W = k.W();
            while (W.hasNext()) {
                com.d.i.f next = W.next();
                if (next == g) {
                    z = true;
                } else if (z && (next instanceof r)) {
                    ((r) next).d(abVar);
                }
            }
            if (z) {
                k.u(q.a(abVar, k, 0));
            }
            j jVar = eVar.b().get(0);
            String c = jVar.c();
            if (jVar.a() == 21) {
                if (c.equals("dotted")) {
                    c = ". ";
                } else if (c.equals("solid")) {
                    c = JavaConstant.Dynamic.DEFAULT_NAME;
                } else if (c.equals("space")) {
                    c = SequenceUtils.SPACE;
                }
            }
            StringBuilder sb = new StringBuilder(100 * c.length());
            for (int i = 0; i < 100; i++) {
                sb.append(c);
            }
            com.d.d.r f = abVar.f();
            abVar.q();
            float a2 = f.a(g.a().d(abVar), sb.toString()) / 100.0f;
            com.d.d.r f2 = abVar.f();
            abVar.q();
            int a3 = f2.a(g.a().d(abVar), SequenceUtils.SPACE);
            int aj = (g.aj() - g.k().Q()) + sVar.f();
            int i2 = (int) ((aj - (2 * a3)) / a2);
            StringBuilder sb2 = new StringBuilder((i2 * c.length()) + 2);
            sb2.append(' ');
            for (int i3 = 0; i3 < i2; i3++) {
                sb2.append(c);
            }
            sb2.append(' ');
            String sb3 = sb2.toString();
            com.d.d.r f3 = abVar.f();
            abVar.q();
            g.e(abVar, aj - f3.a(g.a().d(abVar), sb3));
            return sb3;
        }

        @Override // com.d.c.b.b
        public final String a() {
            return " . ";
        }

        @Override // com.d.c.b.b
        public final boolean a(v vVar, com.d.i.e eVar) {
            if (vVar.r() && eVar.a().equals("leader")) {
                List<j> b2 = eVar.b();
                if (b2.size() == 1) {
                    j jVar = b2.get(0);
                    if (jVar.a() == 19) {
                        return true;
                    }
                    if (jVar.a() == 21) {
                        return jVar.c().equals("dotted") || jVar.c().equals("solid") || jVar.c().equals("space");
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
    }
}
