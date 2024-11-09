package com.d.e;

import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/d/e/j.class */
public final class j {

    /* renamed from: a, reason: collision with root package name */
    private static c f1139a = new a(0);

    /* renamed from: b, reason: collision with root package name */
    private static c f1140b = new b(0);

    /* loaded from: infinitode-2.jar:com/d/e/j$c.class */
    public interface c {
        com.d.d.g a(String str, aa aaVar);
    }

    public static void a(v vVar, x xVar, int i, com.d.c.f.c cVar) {
        float f;
        com.d.i.k d = cVar.d(vVar);
        if (cVar.aA()) {
            f = cVar.b(com.d.c.a.a.T, 0.0f, vVar);
        } else {
            f = 0.0f;
        }
        xVar.a(a(xVar.d(), xVar.e()));
        com.d.d.r d2 = vVar.d();
        vVar.w();
        xVar.c(d2.a(d, xVar.g()) + ((int) f));
        if (xVar.j() > i) {
            xVar.b(true);
            xVar.a(true);
        }
    }

    private static int a(String str, int i) {
        boolean z = false;
        int length = str.length();
        int i2 = i;
        while (true) {
            int i3 = i2;
            if (i3 < length) {
                int codePointAt = str.codePointAt(i3);
                if (!ad.a(codePointAt)) {
                    if (z) {
                        return i3;
                    }
                    z = true;
                }
                i2 = i3 + Character.charCount(codePointAt);
            } else {
                return length;
            }
        }
    }

    public static void b(v vVar, x xVar, int i, com.d.c.f.c cVar) {
        com.d.i.k d = cVar.d(vVar);
        com.d.c.a.c i2 = cVar.i();
        if (i2 == com.d.c.a.c.ar) {
            xVar.a(xVar.a());
            com.d.d.r d2 = vVar.d();
            vVar.w();
            xVar.c(d2.a(d, xVar.g()));
            return;
        }
        if (i2 == com.d.c.a.c.aB || i2 == com.d.c.a.c.aD || i2 == com.d.c.a.c.aC) {
            int indexOf = xVar.f().indexOf(SequenceUtils.EOL);
            if (indexOf >= 0) {
                xVar.a(xVar.e() + indexOf + 1);
                com.d.d.r d3 = vVar.d();
                vVar.w();
                xVar.c(d3.a(d, xVar.g()));
                xVar.b(true);
            } else if (i2 == com.d.c.a.c.aB) {
                xVar.a(xVar.a());
                com.d.d.r d4 = vVar.d();
                vVar.w();
                xVar.c(d4.a(d, xVar.g()));
            }
        }
        if (i2 != com.d.c.a.c.aB) {
            if (xVar.i() && xVar.j() <= i) {
                return;
            }
            a(vVar, xVar, i, cVar, false);
        }
    }

    private static void a(v vVar, x xVar, int i, com.d.c.f.c cVar, boolean z) {
        a(vVar, xVar, i, cVar, f1139a, f1140b, false);
    }

    private static void a(v vVar, x xVar, int i, com.d.c.f.c cVar, c cVar2, c cVar3, boolean z) {
        float f;
        com.d.d.g a2;
        com.d.i.k d = cVar.d(vVar);
        if (cVar.aA()) {
            f = cVar.b(com.d.c.a.a.T, 0.0f, vVar);
        } else {
            f = 0.0f;
        }
        float f2 = f;
        String f3 = xVar.f();
        if (z) {
            a2 = cVar2.a(f3, vVar.y());
        } else {
            a2 = cVar3.a(f3, vVar.y());
        }
        com.d.d.g gVar = a2;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (int a3 = gVar.a(); a3 > 0 && i4 <= i; a3 = gVar.a()) {
            i5 = i4;
            com.d.d.r d2 = vVar.d();
            vVar.w();
            i4 = (int) (i4 + d2.a(d, f3.substring(i2, a3)) + ((a3 - i2) * f2));
            i3 = i2;
            i2 = a3;
        }
        if (i4 <= i) {
            i3 = i2;
            i5 = i4;
            com.d.d.r d3 = vVar.d();
            vVar.w();
            i4 += d3.a(d, f3.substring(i2));
        }
        if (i4 <= i) {
            xVar.c(i4);
            xVar.a(xVar.d().length());
            return;
        }
        xVar.b(true);
        if (i3 == 0 && cVar.j() == com.d.c.a.c.as && !z) {
            a(vVar, xVar, i, cVar, cVar2, cVar3, true);
            return;
        }
        if (i3 != 0) {
            xVar.a(xVar.e() + i3);
            xVar.c(i5);
            return;
        }
        if (i2 == 0) {
            i2 = f3.length();
        }
        xVar.a(xVar.e() + i2);
        xVar.a(true);
        if (i2 == f3.length()) {
            com.d.d.r d4 = vVar.d();
            vVar.w();
            xVar.c(d4.a(d, xVar.g()));
            return;
        }
        xVar.c(i4);
    }

    /* loaded from: infinitode-2.jar:com/d/e/j$a.class */
    static class a implements c {
        private a() {
        }

        /* synthetic */ a(byte b2) {
            this();
        }

        @Override // com.d.e.j.c
        public final com.d.d.g a(String str, aa aaVar) {
            return j.a(str, aaVar);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/e/j$b.class */
    static class b implements c {
        private b() {
        }

        /* synthetic */ b(byte b2) {
            this();
        }

        @Override // com.d.e.j.c
        public final com.d.d.g a(String str, aa aaVar) {
            return j.b(str, aaVar);
        }
    }

    public static com.d.d.g a(String str, aa aaVar) {
        com.d.d.g z = aaVar.z();
        z.a(str);
        return z;
    }

    public static com.d.d.g b(String str, aa aaVar) {
        com.d.d.g y = aaVar.y();
        y.a(str);
        return y;
    }
}
