package com.d.c.d;

import com.d.c.d.a.m;
import com.d.i.a.r;
import com.d.i.v;
import com.d.m.l;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

/* loaded from: infinitode-2.jar:com/d/c/d/c.class */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    private static final Set f1050a;

    /* renamed from: b, reason: collision with root package name */
    private static final Set f1051b;
    private k c;
    private com.d.c.d.a e;
    private String f;
    private boolean h;
    private Map g = new HashMap();
    private i d = new i(new StringReader(""));

    static {
        HashSet hashSet = new HashSet();
        f1050a = hashSet;
        hashSet.add("first-line");
        f1050a.add("first-letter");
        f1050a.add("before");
        f1050a.add("after");
        HashSet hashSet2 = new HashSet();
        f1051b = hashSet2;
        hashSet2.add("first-line");
        f1051b.add("first-letter");
        f1051b.add("before");
        f1051b.add("after");
    }

    public c(com.d.c.d.a aVar) {
        this.e = aVar;
    }

    public final r a(String str, int i, Reader reader) {
        this.f = str;
        a(reader);
        r rVar = new r(str, i);
        a(rVar);
        return rVar;
    }

    public final com.d.c.e.d a(int i, String str) {
        try {
            this.f = com.d.m.i.a().a().p();
            a(new StringReader(str));
            k();
            com.d.c.e.d dVar = new com.d.c.e.d(i);
            try {
                a(dVar, true, false, false);
            } catch (b unused) {
            }
            return dVar;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v8, types: [com.d.c.d.a.m] */
    public final j a(com.d.c.a.a aVar, int i, String str) {
        this.f = aVar + " property value";
        try {
            a(new StringReader(str));
            List c = c(aVar == com.d.c.a.a.O || aVar == com.d.c.a.a.bg || aVar == com.d.c.a.a.s);
            b e = com.d.c.a.a.e(aVar);
            try {
                e = e.a(aVar, c, 0, false);
                if (e.size() != 1) {
                    throw new b("Builder created " + e.size() + "properties, expected 1", p());
                }
                return (j) ((v) e.get(0)).e();
            } catch (b e2) {
                e.a(p());
                throw e2;
            }
        } catch (b e3) {
            a(e3, "property value", false);
            return null;
        } catch (IOException e4) {
            throw new RuntimeException(e4.getMessage(), e4);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.d.c.d.k] */
    /* JADX WARN: Type inference failed for: r0v5 */
    private void a(r rVar) {
        b n = n();
        try {
            n = n;
            if (n == k.t) {
                try {
                    m();
                    k();
                    k m = m();
                    if (m == k.m) {
                        k();
                        k m2 = m();
                        if (m2 != k.P) {
                            c(m2);
                            throw new b(m2, k.P, p());
                        }
                    } else {
                        c(m);
                        throw new b(m, k.m, p());
                    }
                } catch (b e) {
                    a(e, "@charset rule", true);
                    a(false, false);
                }
            }
            while (true) {
                l();
                if (n() != k.q) {
                    break;
                } else {
                    b(rVar);
                }
            }
            while (n() == k.u) {
                a();
                l();
            }
            while (true) {
                k n2 = n();
                if (n2 != k.aa) {
                    switch (n2.a()) {
                        case 17:
                            m();
                            a(new b("@import not allowed here", p()), "@import rule", true);
                            a(false, false);
                            continue;
                        case 18:
                            e(rVar);
                            continue;
                        case 19:
                            c(rVar);
                            continue;
                        case 21:
                            m();
                            a(new b("@namespace not allowed here", p()), "@namespace rule", true);
                            a(false, false);
                            continue;
                        case 22:
                            d(rVar);
                            continue;
                        case 23:
                            m();
                            a(new b("Invalid at-rule", p()), "at-rule", true);
                            a(false, false);
                            break;
                    }
                    a((com.d.c.e.e) rVar);
                    l();
                } else {
                    return;
                }
            }
        } catch (b e2) {
            if (!n.b()) {
                a(e2, "stylesheet", false);
            }
        }
    }

    private void b(r rVar) {
        try {
            k m = m();
            if (m == k.q) {
                com.d.l.b bVar = new com.d.l.b();
                bVar.a(rVar.a());
                k();
                k m2 = m();
                switch (m2.a()) {
                    case 13:
                    case 39:
                        String d = d(m2);
                        String a2 = com.d.m.i.a().a().n().a(rVar.b(), d);
                        if (a2 == null) {
                            l.e(Level.INFO, "URI resolver rejected resolving CSS import at (" + d + ")");
                        }
                        bVar.b(a2);
                        k();
                        if (n() == k.o) {
                            bVar.d(b());
                            while (n() == k.l) {
                                m();
                                k();
                                k n = n();
                                if (n != k.o) {
                                    throw new b(n, k.o, p());
                                }
                                bVar.d(b());
                            }
                        }
                        k m3 = m();
                        if (m3 == k.P) {
                            k();
                            if (bVar.b().size() == 0) {
                                bVar.d("all");
                            }
                            rVar.a(bVar);
                            return;
                        }
                        c(m3);
                        throw new b(m3, k.P, p());
                    default:
                        c(m2);
                        throw new b(m2, new k[]{k.m, k.M}, p());
                }
            }
            c(m);
            throw new b(m, k.q, p());
        } catch (b e) {
            a(e, "@import rule", true);
            a(false, false);
        }
    }

    private void a() {
        try {
            k m = m();
            if (m != k.u) {
                throw new b(m, k.u, p());
            }
            String str = null;
            k();
            k m2 = m();
            k kVar = m2;
            if (m2 == k.o) {
                str = d(kVar);
                k();
                kVar = m();
            }
            if (kVar == k.m || kVar == k.M) {
                String d = d(kVar);
                k();
                k m3 = m();
                if (m3 != k.P) {
                    throw new b(m3, k.P, p());
                }
                k();
                this.g.put(str, d);
                return;
            }
            throw new b(kVar, new k[]{k.m, k.M}, p());
        } catch (b e) {
            a(e, "@namespace rule", true);
            a(false, false);
        }
    }

    private void c(r rVar) {
        k m = m();
        try {
            if (m == k.s) {
                com.d.c.e.b bVar = new com.d.c.e.b(rVar.a());
                k();
                k n = n();
                if (n != k.o) {
                    throw new b(n, k.o, p());
                }
                bVar.a(b());
                while (n() == k.l) {
                    m();
                    k();
                    k n2 = n();
                    if (n2 != k.o) {
                        throw new b(n2, k.o, p());
                    }
                    bVar.a(b());
                }
                k m2 = m();
                if (m2 == k.i) {
                    k();
                    while (true) {
                        k n3 = n();
                        if (n3 != null) {
                            switch (n3.a()) {
                                case 42:
                                    m();
                                    break;
                                default:
                                    a(bVar);
                            }
                        }
                    }
                    k();
                    rVar.a(bVar);
                    return;
                }
                c(m2);
                throw new b(m2, k.i, p());
            }
            c(m);
            throw new b(m, k.s, p());
        } catch (b e) {
            a(e, "@media rule", true);
            a(false, false);
        }
    }

    private String b() {
        k m = m();
        if (m == k.o) {
            String d = d(m);
            k();
            return d;
        }
        c(m);
        throw new b(m, k.o, p());
    }

    private void d(r rVar) {
        k m = m();
        try {
            com.d.c.e.a aVar = new com.d.c.e.a(rVar.a());
            if (m == k.v) {
                k();
                com.d.c.e.d dVar = new com.d.c.e.d(rVar.a());
                k();
                k m2 = m();
                k kVar = m2;
                if (m2 == k.i) {
                    int i = 0;
                    while (true) {
                        i++;
                        if (i >= 1048576) {
                            throw new b(kVar, k.O, p());
                        }
                        k();
                        k n = n();
                        kVar = n;
                        if (n == k.O) {
                            m();
                            k();
                            aVar.a(dVar);
                            rVar.a(aVar);
                            return;
                        }
                        a(dVar, false, true, true);
                    }
                } else {
                    c(kVar);
                    throw new b(kVar, k.i, p());
                }
            } else {
                c(m);
                throw new b(m, k.v, p());
            }
        } catch (b e) {
            a(e, "@font-face rule", true);
            a(false, false);
        }
    }

    private void e(r rVar) {
        k m = m();
        try {
            com.d.c.e.c cVar = new com.d.c.e.c(rVar.a());
            if (m == k.r) {
                k();
                k n = n();
                k kVar = n;
                if (n == k.o) {
                    String d = d(kVar);
                    if (d.equals("auto")) {
                        throw new b("page name may not be auto", p());
                    }
                    m();
                    cVar.b(d);
                    kVar = n();
                }
                if (kVar == k.R) {
                    cVar.a(c());
                }
                com.d.c.e.d dVar = new com.d.c.e.d(rVar.a());
                k();
                k m2 = m();
                if (m2 != k.i) {
                    c(m2);
                    throw new b(m2, k.i, p());
                }
                while (true) {
                    k();
                    k n2 = n();
                    if (n2 == k.O) {
                        m();
                        k();
                        cVar.a(dVar);
                        rVar.a(cVar);
                        return;
                    }
                    if (n2 == k.w) {
                        a(rVar, cVar);
                    } else {
                        a(dVar, false, true, false);
                    }
                }
            } else {
                c(m);
                throw new b(m, k.r, p());
            }
        } catch (b e) {
            a(e, "@page rule", true);
            a(false, false);
        }
    }

    private void a(r rVar, com.d.c.e.c cVar) {
        k m = m();
        if (m != k.w) {
            a(new b(m, k.w, p()), "at rule", true);
            a(true, false);
            return;
        }
        String d = d(m);
        com.d.c.a.d a2 = com.d.c.a.d.a(d);
        if (a2 == null) {
            a(new b(d + " is not a valid margin box name", p()), "at rule", true);
            a(true, false);
            return;
        }
        k();
        try {
            k m2 = m();
            if (m2 == k.i) {
                k();
                com.d.c.e.d dVar = new com.d.c.e.d(rVar.a());
                a(dVar, false, false, false);
                k m3 = m();
                if (m3 != k.O) {
                    c(m3);
                    throw new b(m3, k.O, p());
                }
                cVar.a(a2, dVar.a());
                return;
            }
            c(m2);
            throw new b(m2, k.i, p());
        } catch (b e) {
            a(e, "margin box", true);
            a(false, false);
        }
    }

    private String c() {
        k m = m();
        if (m == k.R) {
            k m2 = m();
            if (m2 == k.o) {
                String d = d(m2);
                if (!d.equals("first") && !d.equals("left") && !d.equals("right")) {
                    throw new b("Pseudo page must be one of first, left, or right", p());
                }
                return d;
            }
            c(m2);
            throw new b(m2, k.o, p());
        }
        c(m);
        throw new b(m, k.R, p());
    }

    private void d() {
        switch (n().a()) {
            case 12:
            case 44:
                m();
                k();
                return;
            default:
                return;
        }
    }

    private k e() {
        k m = m();
        if (m == k.j || m == k.k) {
            k();
        } else if (m != k.f1062a) {
            c(m);
            throw new b(m, new k[]{k.j, k.k, k.f1062a}, p());
        }
        return m;
    }

    private int f() {
        k m = m();
        if (m != k.S && m != k.j) {
            c(m);
            throw new b(m, new k[]{k.S, k.j}, p());
        }
        if (m == k.S) {
            return -1;
        }
        return 1;
    }

    private String g() {
        k m = m();
        if (m == k.o) {
            String d = d(m);
            k();
            return d;
        }
        c(m);
        throw new b(m, k.o, p());
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x000a. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:15:0x005a A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(com.d.c.e.d r5, boolean r6, boolean r7, boolean r8) {
        /*
            r4 = this;
        L0:
            r0 = r4
            com.d.c.d.k r0 = r0.n()
            r1 = r0
            r9 = r1
            int r0 = r0.a()
            switch(r0) {
                case 23: goto L41;
                case 42: goto L40;
                case 43: goto L34;
                case 54: goto L4c;
                default: goto L50;
            }
        L34:
            r0 = r4
            com.d.c.d.k r0 = r0.m()
            r0 = r4
            r0.k()
            goto L0
        L40:
            return
        L41:
            r0 = r7
            if (r0 != 0) goto L5a
            r0 = r4
            r1 = r5
            r2 = r8
            r0.a(r1, r2)
        L4c:
            r0 = r6
            if (r0 != 0) goto L5a
        L50:
            r0 = r4
            r1 = r5
            r2 = r8
            r0.a(r1, r2)
            goto L0
        L5a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.d.c.d.c.a(com.d.c.e.d, boolean, boolean, boolean):void");
    }

    private void a(com.d.c.e.e eVar) {
        try {
            com.d.c.e.d dVar = new com.d.c.e.d(eVar.a());
            a(dVar);
            while (n() == k.l) {
                m();
                k();
                a(dVar);
            }
            k m = m();
            if (m == k.i) {
                k();
                a(dVar, false, false, false);
                k m2 = m();
                if (m2 == k.O) {
                    k();
                    if (dVar.a().size() > 0) {
                        eVar.a(dVar);
                        return;
                    }
                    return;
                }
                c(m2);
                throw new b(m2, k.O, p());
            }
            c(m);
            throw new b(m, new k[]{k.l, k.i}, p());
        } catch (b e) {
            a(e, "ruleset", true);
            a(true, false);
        }
    }

    private void a(com.d.c.e.d dVar) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList.add(b(dVar));
        while (true) {
            switch (n().a()) {
                case 1:
                case 10:
                case 11:
                    arrayList2.add(e());
                    k n = n();
                    switch (n.a()) {
                        case 15:
                        case 16:
                        case 45:
                        case 48:
                        case 50:
                        case 52:
                            arrayList.add(b(dVar));
                        default:
                            throw new b(n, new k[]{k.o, k.Y, k.p, k.W, k.U, k.R}, p());
                    }
                default:
                    dVar.a(a(arrayList, arrayList2));
                    return;
            }
        }
    }

    private com.d.c.c.f a(List list, List list2) {
        int size = list.size();
        if (size == 1) {
            return (com.d.c.c.f) list.get(0);
        }
        int i = 0;
        com.d.c.c.f fVar = null;
        for (int i2 = 0; i2 < size - 1; i2++) {
            com.d.c.c.f fVar2 = (com.d.c.c.f) list.get(i2);
            com.d.c.c.f fVar3 = (com.d.c.c.f) list.get(i2 + 1);
            k kVar = (k) list2.get(i2);
            if (fVar2.f() != null) {
                throw new b("A simple selector with a pseudo element cannot be combined with another simple selector", p());
            }
            boolean z = false;
            if (kVar == k.f1062a) {
                fVar3.d(0);
                i = 0;
            } else if (kVar == k.k) {
                fVar3.d(1);
                i = 1;
            } else if (kVar == k.j) {
                fVar2.d(2);
                z = true;
            }
            fVar3.e(fVar3.j() + fVar2.j());
            fVar3.f(fVar3.l() + fVar2.l());
            fVar3.g(fVar3.k() + fVar2.k());
            if (!z) {
                if (fVar == null) {
                    fVar = fVar2;
                }
                fVar2.a(fVar3);
            } else {
                fVar3.b(fVar2);
                if (fVar == null || fVar == fVar2) {
                    fVar = fVar3;
                }
                if (i2 > 0) {
                    int i3 = i2 - 1;
                    while (true) {
                        if (i3 >= 0) {
                            com.d.c.c.f fVar4 = (com.d.c.c.f) list.get(i3);
                            if (fVar4.g() == fVar2) {
                                fVar4.a(fVar3);
                                fVar3.d(i);
                                break;
                            }
                            i3--;
                        }
                    }
                }
            }
        }
        return fVar;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:25:0x00b8. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0057. Please report as an issue. */
    private com.d.c.c.f b(com.d.c.e.d dVar) {
        com.d.c.c.f fVar = new com.d.c.c.f();
        fVar.a(dVar);
        switch (n().a()) {
            case 15:
            case 52:
            case 53:
                a b2 = b(false);
                fVar.g(b2.a());
                fVar.f(b2.b());
                while (true) {
                    switch (n().a()) {
                        case 16:
                            fVar.c(a(m(), true));
                        case 45:
                            c(fVar);
                        case 48:
                            b(fVar);
                        case 50:
                            a(fVar);
                    }
                    break;
                }
            default:
                boolean z = false;
                while (true) {
                    boolean z2 = z;
                    k n = n();
                    switch (n.a()) {
                        case 16:
                            fVar.c(a(m(), true));
                            z = true;
                        case 45:
                            c(fVar);
                            z = true;
                        case 48:
                            b(fVar);
                            z = true;
                        case 50:
                            a(fVar);
                            z = true;
                    }
                    if (!z2) {
                        throw new b(n, new k[]{k.p, k.W, k.U, k.R}, p());
                    }
                }
                break;
        }
        return fVar;
    }

    private a b(boolean z) {
        String str = null;
        String str2 = null;
        k n = n();
        k kVar = n;
        if (n == k.Y || kVar == k.o) {
            m();
            if (kVar == k.o) {
                str2 = a(kVar, true);
            }
            kVar = n();
        } else if (kVar == k.Z) {
            str = "";
        } else {
            throw new b(kVar, new k[]{k.Y, k.o, k.Z}, p());
        }
        if (kVar == k.Z) {
            m();
            k m = m();
            if (m != k.Y && m != k.o) {
                throw new b(m, new k[]{k.Y, k.o}, p());
            }
            if (str == null) {
                str = str2;
            }
            if (m == k.o) {
                str2 = a(m, true);
            }
        }
        String str3 = null;
        if (str != null && str != "") {
            String str4 = (String) this.g.get(str.toLowerCase());
            str3 = str4;
            if (str4 == null) {
                throw new b("There is no namespace with prefix " + str + " defined", p());
            }
        } else if (str == null && !z) {
            str3 = (String) this.g.get(null);
        }
        if (z && str2 == null) {
            throw new b("An attribute name is required", p());
        }
        return new a(str3, str2);
    }

    private void a(com.d.c.c.f fVar) {
        k m = m();
        if (m == k.W) {
            k m2 = m();
            if (m2 == k.o) {
                fVar.d(a(m2, true));
                return;
            } else {
                c(m2);
                throw new b(m2, k.o, p());
            }
        }
        c(m);
        throw new b(m, k.W, p());
    }

    private void b(com.d.c.c.f fVar) {
        k m = m();
        if (m == k.U) {
            k();
            k n = n();
            if (n != k.o && n != k.Y && n != k.Z) {
                throw new b(n, new k[]{k.o, k.Y}, p());
            }
            boolean z = true;
            a b2 = b(true);
            String a2 = b2.a();
            String b3 = b2.b();
            k();
            k n2 = n();
            k kVar = n2;
            switch (n2.a()) {
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 51:
                    z = false;
                    k m2 = m();
                    k();
                    k m3 = m();
                    if (m3 == k.o || m3 == k.m) {
                        String a3 = a(m3, true);
                        switch (m2.a()) {
                            case 4:
                                fVar.e(a2, b3, a3);
                                break;
                            case 5:
                                fVar.f(a2, b3, a3);
                                break;
                            case 6:
                                fVar.b(a2, b3, a3);
                                break;
                            case 7:
                                fVar.c(a2, b3, a3);
                                break;
                            case 8:
                                fVar.d(a2, b3, a3);
                                break;
                            case 51:
                                fVar.a(a2, b3, a3);
                                break;
                        }
                        k();
                        k();
                        kVar = n();
                        break;
                    } else {
                        c(m3);
                        throw new b(m3, new k[]{k.o, k.m}, p());
                    }
            }
            if (z) {
                fVar.a(a2, b3);
            }
            if (kVar == k.V) {
                m();
                return;
            }
            throw new b(kVar, new k[]{k.X, k.d, k.e, k.f, k.g, k.h, k.V}, p());
        }
        c(m);
        throw new b(m, k.U, p());
    }

    private void a(k kVar, com.d.c.c.f fVar) {
        String d = d(kVar);
        if (d.equals("link")) {
            fVar.a();
            return;
        }
        if (d.equals("visited")) {
            fVar.a(2);
            return;
        }
        if (d.equals("hover")) {
            fVar.a(4);
            return;
        }
        if (d.equals("focus")) {
            fVar.a(16);
            return;
        }
        if (d.equals("active")) {
            fVar.a(8);
            return;
        }
        if (d.equals("first-child")) {
            fVar.b();
            return;
        }
        if (d.equals("even")) {
            fVar.d();
            return;
        }
        if (d.equals("odd")) {
            fVar.e();
        } else {
            if (!d.equals("last-child")) {
                if (f1051b.contains(d)) {
                    fVar.e(d);
                    return;
                }
                throw new b(d + " is not a recognized pseudo-class", p());
            }
            fVar.c();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v15, types: [com.d.c.c.f] */
    /* JADX WARN: Type inference failed for: r0v38 */
    /* JADX WARN: Type inference failed for: r0v39 */
    private void b(k kVar, com.d.c.c.f fVar) {
        k kVar2;
        String d = d(kVar);
        String substring = d.substring(0, d.length() - 1);
        if (substring.equals("lang")) {
            k();
            k m = m();
            if (m == k.o) {
                fVar.b(d(m));
                k();
                kVar2 = m();
            } else {
                c(m);
                throw new b(m, k.o, p());
            }
        } else {
            if (substring.equals("nth-child")) {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    k m2 = m();
                    kVar2 = m2;
                    b bVar = m2;
                    if (m2 != null) {
                        if (kVar2 != k.o && kVar2 != k.f1062a && kVar2 != k.L && kVar2 != k.J && kVar2 != k.j) {
                            k kVar3 = kVar2;
                            bVar = kVar3;
                            if (kVar3 == k.S) {
                            }
                        }
                        sb.append(d(kVar2));
                    }
                    try {
                        bVar = fVar;
                        bVar.a(sb.toString());
                        break;
                    } catch (b e) {
                        bVar.a(p());
                        c(kVar2);
                        throw e;
                    }
                }
            }
            c(kVar);
            throw new b(substring + " is not a valid function in this context", p());
        }
        if (kVar2 != k.T) {
            c(kVar2);
            throw new b(kVar2, k.T, p());
        }
    }

    private void c(k kVar, com.d.c.c.f fVar) {
        String d = d(kVar);
        if (f1050a.contains(d)) {
            fVar.e(d);
            return;
        }
        throw new b(d + " is not a recognized psuedo-element", p());
    }

    private void c(com.d.c.c.f fVar) {
        k m = m();
        if (m == k.R) {
            k m2 = m();
            switch (m2.a()) {
                case 15:
                    a(m2, fVar);
                    return;
                case 40:
                    b(m2, fVar);
                    return;
                case 45:
                    c(m(), fVar);
                    return;
                default:
                    c(m2);
                    throw new b(m2, new k[]{k.o, k.N}, p());
            }
        }
        c(m);
        throw new b(m, k.R, p());
    }

    private boolean a(com.d.c.a.a aVar, String str) {
        if (aVar == null) {
            this.e.a(this.f, str + " is an unrecognized CSS property at line " + p() + ". Ignoring declaration.");
            return false;
        }
        if (!com.d.c.a.a.d(aVar)) {
            this.e.a(this.f, str + " is not implemented at line " + p() + ". Ignoring declaration.");
            return false;
        }
        if (com.d.c.a.a.e(aVar) == null) {
            this.e.a(this.f, "(bug) No property builder defined for " + str + " at line " + p() + ". Ignoring declaration.");
            return false;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v27, types: [com.d.c.e.d] */
    private void a(com.d.c.e.d dVar, boolean z) {
        try {
            k n = n();
            if (n != k.o) {
                throw new b(n, k.o, p());
            }
            String g = g();
            com.d.c.a.a a2 = com.d.c.a.a.a(g);
            boolean a3 = a(a2, g);
            k m = m();
            if (m == k.R) {
                k();
                List c = c(a2 == com.d.c.a.a.O || a2 == com.d.c.a.a.bg || a2 == com.d.c.a.a.s);
                boolean z2 = false;
                if (n() == k.x) {
                    h();
                    z2 = true;
                }
                k n2 = n();
                if (n2 != k.P && n2 != k.O && n2 != k.aa) {
                    throw new b(n2, new k[]{k.P, k.O}, p());
                }
                b bVar = a3;
                if (bVar != 0) {
                    try {
                        m e = com.d.c.a.a.e(a2);
                        bVar = dVar;
                        bVar.a(e.a(a2, c, dVar.c(), z2, !z));
                        return;
                    } catch (b e2) {
                        bVar.a(p());
                        a(e2, "declaration", true);
                    }
                }
                return;
            }
            c(m);
            throw new b(m, k.R, p());
        } catch (b e3) {
            a(e3, "declaration", true);
            a(false, true);
        }
    }

    private void h() {
        k m = m();
        if (m == k.x) {
            k();
        } else {
            c(m);
            throw new b(m, k.x, p());
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.RegionMaker.calcSwitchOut(RegionMaker.java:923)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:797)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:157)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.processFallThroughCases(RegionMaker.java:841)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:800)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:157)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:411)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:201)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:135)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    private java.util.List c(boolean r9) {
        /*
            Method dump skipped, instructions count: 431
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.d.c.d.c.c(boolean):java.util.List");
    }

    private String a(k kVar) {
        char c;
        char c2;
        String d = d(kVar);
        int i = 0;
        char[] charArray = d.toCharArray();
        for (int i2 = 0; i2 < charArray.length && (c2 = charArray[i2]) >= '0' && c2 <= '9'; i2++) {
            i++;
        }
        if (charArray[i] == '.') {
            i++;
            for (int i3 = i; i3 < charArray.length && (c = charArray[i3]) >= '0' && c <= '9'; i3++) {
                i++;
            }
        }
        return d.substring(0, i);
    }

    private String b(k kVar) {
        return d(kVar).substring(a(kVar).length());
    }

    private static String a(float f) {
        return f == -1.0f ? "-" : "";
    }

    private j d(boolean z) {
        j i;
        float f = 1.0f;
        k n = n();
        k kVar = n;
        if (n == k.j || kVar == k.S) {
            f = f();
            kVar = n();
        }
        switch (kVar.a()) {
            case 13:
                i = new j((short) 19, d(kVar), o());
                m();
                k();
                break;
            case 14:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            default:
                throw new b(kVar, new k[]{k.L, k.K, k.A, k.y, k.z, k.F, k.C, k.B, k.D, k.E, k.G, k.H, k.I, k.m, k.o, k.M, k.p, k.N}, p());
            case 15:
                String a2 = a(kVar, z);
                i = new j((short) 21, a2, a2);
                m();
                k();
                break;
            case 16:
                i = j();
                break;
            case 25:
                i = new j((short) 3, f * Float.parseFloat(a(kVar)), a(f) + d(kVar));
                m();
                k();
                break;
            case 26:
                i = new j((short) 4, f * Float.parseFloat(a(kVar)), a(f) + d(kVar));
                m();
                k();
                break;
            case 27:
                i = new j((short) 5, f * Float.parseFloat(a(kVar)), a(f) + d(kVar));
                m();
                k();
                break;
            case 28:
                i = new j((short) 6, f * Float.parseFloat(a(kVar)), a(f) + d(kVar));
                m();
                k();
                break;
            case 29:
                i = new j((short) 7, f * Float.parseFloat(a(kVar)), a(f) + d(kVar));
                m();
                k();
                break;
            case 30:
                i = new j((short) 8, f * Float.parseFloat(a(kVar)), a(f) + d(kVar));
                m();
                k();
                break;
            case 31:
                i = new j((short) 9, f * Float.parseFloat(a(kVar)), a(f) + d(kVar));
                m();
                k();
                break;
            case 32:
                i = new j((short) 10, f * Float.parseFloat(a(kVar)), a(f) + d(kVar));
                m();
                k();
                break;
            case 33:
                String b2 = b(kVar);
                short s = 0;
                if ("deg".equals(b2)) {
                    s = 11;
                } else if ("rad".equals(b2)) {
                    s = 12;
                } else if ("grad".equals(b2)) {
                    s = 13;
                }
                i = new j(s, f * Float.parseFloat(a(kVar)), a(f) + d(kVar));
                m();
                k();
                break;
            case 34:
            case 35:
            case 36:
                throw new b("Unsupported CSS unit " + b(kVar), p());
            case 37:
                i = new j((short) 2, f * Float.parseFloat(a(kVar)), a(f) + d(kVar));
                m();
                k();
                break;
            case 38:
                i = new j((short) 1, f * Float.parseFloat(d(kVar)), a(f) + d(kVar));
                m();
                k();
                break;
            case 39:
                i = new j((short) 20, d(kVar), o());
                m();
                k();
                break;
            case 40:
                i = i();
                break;
        }
        return i;
    }

    private j i() {
        j jVar;
        k m = m();
        if (m == k.N) {
            String d = d(m);
            k();
            List c = c(false);
            k m2 = m();
            if (m2 != k.T) {
                c(m2);
                throw new b(m2, k.T, p());
            }
            if (d.equals("rgb(")) {
                jVar = new j(b(c));
            } else if (d.equals("cmyk(")) {
                if (!q()) {
                    throw new b("The current output device does not support CMYK colors", p());
                }
                jVar = new j(a(c));
            } else {
                jVar = new j(new com.d.i.e(d.substring(0, d.length() - 1), c));
            }
            k();
            return jVar;
        }
        c(m);
        throw new b(m, k.N, p());
    }

    private f a(List list) {
        if (list.size() != 4) {
            throw new b("The cmyk() function must have exactly four parameters", p());
        }
        float[] fArr = new float[4];
        for (int i = 0; i < list.size(); i++) {
            fArr[i] = a((j) list.get(i), i + 1);
        }
        return new f(fArr[0], fArr[1], fArr[2], fArr[3]);
    }

    private float a(j jVar, int i) {
        float f;
        short a2 = jVar.a();
        if (a2 == 1) {
            f = jVar.f();
        } else if (a2 == 2) {
            f = jVar.f() / 100.0f;
        } else {
            throw new b("Parameter " + i + " to the cmyk() function is not a number or a percentage", p());
        }
        if (f < 0.0f || f > 1.0f) {
            throw new b("Parameter " + i + " to the cmyk() function must be between zero and one", p());
        }
        return f;
    }

    private h b(List list) {
        if (list.size() != 3) {
            throw new b("The rgb() function must have exactly three parameters", p());
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            j jVar = (j) list.get(i4);
            short a2 = jVar.a();
            if (a2 != 2 && a2 != 1) {
                throw new b("Parameter " + (i4 + 1) + " to the rgb() function is not a number or percentage", p());
            }
            float f = jVar.f();
            if (a2 == 2) {
                f = (f / 100.0f) * 255.0f;
            }
            if (f < 0.0f) {
                f = 0.0f;
            } else if (f > 255.0f) {
                f = 255.0f;
            }
            switch (i4) {
                case 0:
                    i = (int) f;
                    break;
                case 1:
                    i2 = (int) f;
                    break;
                case 2:
                    i3 = (int) f;
                    break;
            }
        }
        return new h(i, i2, i3);
    }

    private j j() {
        h hVar;
        k m = m();
        if (m == k.p) {
            String d = d(m);
            if ((d.length() != 3 && d.length() != 6) || !a(d)) {
                c(m);
                throw new b("#" + d + " is not a valid color definition", p());
            }
            if (d.length() == 3) {
                hVar = new h(a(d.charAt(0), d.charAt(0)), a(d.charAt(1), d.charAt(1)), a(d.charAt(2), d.charAt(2)));
            } else {
                hVar = new h(a(d.charAt(0), d.charAt(1)), a(d.charAt(2), d.charAt(3)), a(d.charAt(4), d.charAt(5)));
            }
            j jVar = new j(hVar);
            k();
            return jVar;
        }
        c(m);
        throw new b(m, k.p, p());
    }

    private static boolean a(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!b(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private int a(char c, char c2) {
        return (a(c) << 4) | a(c2);
    }

    private static int a(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 'a') + 10;
        }
        return (c - 'A') + 10;
    }

    private void k() {
        k m;
        do {
            m = m();
        } while (m == k.f1062a);
        c(m);
    }

    private void l() {
        while (true) {
            k m = m();
            if (m != k.f1062a && m != k.f1063b && m != k.c) {
                c(m);
                return;
            }
        }
    }

    private k m() {
        if (this.c != null) {
            k kVar = this.c;
            this.c = null;
            return kVar;
        }
        return this.d.d();
    }

    private void c(k kVar) {
        if (this.c != null) {
            throw new RuntimeException("saved must be null");
        }
        this.c = kVar;
    }

    private k n() {
        k m = m();
        c(m);
        return m;
    }

    private void a(b bVar, String str, boolean z) {
        if (!bVar.b()) {
            this.e.a(this.f, bVar.getMessage() + " Skipping " + str + ".");
        }
        bVar.a(true);
        if (bVar.a() && z) {
            throw bVar;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0018. Please report as an issue. */
    private void a(boolean z, boolean z2) {
        int i = 0;
        boolean z3 = false;
        while (true) {
            k m = m();
            if (m == k.aa) {
                return;
            }
            switch (m.a()) {
                case 9:
                    z3 = true;
                    i++;
                case 42:
                    if (i == 0) {
                        if (z2) {
                            c(m);
                            break;
                        }
                    } else {
                        i--;
                        if (i == 0) {
                            break;
                        }
                    }
                case 43:
                    if (i != 0) {
                        continue;
                    } else if (z && !z3) {
                    }
                    break;
            }
        }
        k();
    }

    private void a(Reader reader) {
        this.c = null;
        this.g.clear();
        this.d.a(reader);
        this.d.a(0);
    }

    private String o() {
        return this.d.b();
    }

    private String d(k kVar) {
        return a(kVar, false);
    }

    private String a(k kVar, boolean z) {
        switch (kVar.a()) {
            case 13:
                return a(this.d.b().toCharArray(), 1, this.d.c() - 1);
            case 15:
            case 23:
            case 40:
                int i = 0;
                int c = this.d.c();
                if (kVar.a() == 23) {
                    i = 0 + 1;
                }
                String a2 = a(this.d.b().toCharArray(), i, c);
                if (!z) {
                    a2 = a2.toLowerCase();
                }
                return a2;
            case 16:
                return a(this.d.b().toCharArray(), 1, this.d.c());
            case 39:
                char[] charArray = this.d.b().toCharArray();
                int i2 = 4;
                while (true) {
                    if (charArray[i2] == '\t' || charArray[i2] == '\r' || charArray[i2] == '\n' || charArray[i2] == '\f') {
                        i2++;
                    } else {
                        if (charArray[i2] == '\'' || charArray[i2] == '\"') {
                            i2++;
                        }
                        int length = charArray.length - 2;
                        while (true) {
                            if (charArray[length] == '\t' || charArray[length] == '\r' || charArray[length] == '\n' || charArray[length] == '\f') {
                                length--;
                            } else {
                                if (charArray[length] == '\'' || charArray[length] == '\"') {
                                    length--;
                                }
                                String a3 = a(charArray, i2, length + 1);
                                String a4 = com.d.m.i.a().a().n().a(this.f, a3);
                                if (a4 == null) {
                                    l.e(Level.INFO, "URI resolver rejected resolving URI at (" + a3 + ") in CSS stylehseet");
                                }
                                return a4 == null ? "" : a4;
                            }
                        }
                    }
                }
                break;
            default:
                return this.d.b();
        }
    }

    private int p() {
        return this.d.a();
    }

    private static boolean b(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        if (c < 'A' || c > 'F') {
            return c >= 'a' && c <= 'f';
        }
        return true;
    }

    private static String a(char[] cArr, int i, int i2) {
        StringBuilder sb = new StringBuilder(cArr.length + 10);
        int i3 = i;
        while (i3 < i2) {
            char c = cArr[i3];
            if (c == '\\') {
                if (i3 < i2 - 2 && cArr[i3 + 1] == '\r' && cArr[i3 + 2] == '\n') {
                    i3 += 2;
                } else if (i3 + 1 < cArr.length && (cArr[i3 + 1] == '\n' || cArr[i3 + 1] == '\r' || cArr[i3 + 1] == '\f')) {
                    i3++;
                } else if (i3 + 1 >= cArr.length) {
                    sb.append(c);
                } else if (b(cArr[i3 + 1])) {
                    int i4 = i3 + 1;
                    while (i4 < i2 && b(cArr[i4]) && i4 - i4 < 6) {
                        i4++;
                    }
                    int parseInt = Integer.parseInt(new String(cArr, i4, i4 - i4), 16);
                    if (parseInt < 65535) {
                        sb.append((char) parseInt);
                    }
                    i3 = i4 - 1;
                    if (i3 < i2 - 2 && cArr[i3 + 1] == '\r' && cArr[i3 + 2] == '\n') {
                        i3 += 2;
                    } else if (i3 < i2 - 1 && (cArr[i3 + 1] == ' ' || cArr[i3 + 1] == '\t' || cArr[i3 + 1] == '\n' || cArr[i3 + 1] == '\r' || cArr[i3 + 1] == '\f')) {
                        i3++;
                    }
                }
            } else {
                sb.append(c);
            }
            i3++;
        }
        return sb.toString();
    }

    private boolean q() {
        return this.h;
    }

    public final void a(boolean z) {
        this.h = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/c/d/c$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private final String f1052a;

        /* renamed from: b, reason: collision with root package name */
        private final String f1053b;

        public a(String str, String str2) {
            this.f1052a = str;
            this.f1053b = str2;
        }

        public final String a() {
            return this.f1052a;
        }

        public final String b() {
            return this.f1053b;
        }
    }
}
