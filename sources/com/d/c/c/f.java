package com.d.c.c;

import com.d.m.l;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

/* loaded from: infinitode-2.jar:com/d/c/c/f.class */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    private com.d.c.e.d f991a;
    private int d;
    private String e;
    private String f;
    private String h;
    private int i;
    private int j;
    private int k;
    private int l;
    private List<b> m;
    private int n;
    private static int o = 0;

    /* renamed from: b, reason: collision with root package name */
    private f f992b = null;
    private f c = null;
    private int g = 0;

    public f() {
        int i = o;
        o = i + 1;
        this.n = i;
    }

    public final boolean a(Object obj, com.d.c.b.a aVar, com.d.c.b.d dVar) {
        Object a2;
        if (this.c != null && ((a2 = this.c.a(obj, dVar)) == null || !this.c.a(a2, aVar, dVar))) {
            return false;
        }
        if (this.e == null || dVar.a(obj, this.f, this.e)) {
            if (this.m != null) {
                Iterator<b> it = this.m.iterator();
                while (it.hasNext()) {
                    if (!it.next().a(obj, aVar, dVar)) {
                        return false;
                    }
                }
                return true;
            }
            return true;
        }
        return false;
    }

    public final boolean b(Object obj, com.d.c.b.a aVar, com.d.c.b.d dVar) {
        Object a2;
        if (this.c != null && ((a2 = this.c.a(obj, dVar)) == null || !this.c.b(a2, aVar, dVar))) {
            return false;
        }
        if (b(2) && (aVar == null || !aVar.g(obj))) {
            return false;
        }
        if (b(8) && (aVar == null || !aVar.i(obj))) {
            return false;
        }
        if (b(4) && (aVar == null || !aVar.h(obj))) {
            return false;
        }
        if (b(16)) {
            if (aVar == null || !aVar.j(obj)) {
                return false;
            }
            return true;
        }
        return true;
    }

    private void o() {
        a(b.f());
    }

    public final void a() {
        this.j++;
        a(b.e());
    }

    public final void b() {
        this.j++;
        a(b.a());
    }

    public final void c() {
        this.j++;
        a(b.b());
    }

    public final void a(String str) {
        this.j++;
        a(b.d(str));
    }

    public final void d() {
        this.j++;
        a(b.c());
    }

    public final void e() {
        this.j++;
        a(b.d());
    }

    public final void b(String str) {
        this.j++;
        a(b.c(str));
    }

    public final void c(String str) {
        this.i++;
        a(b.b(str));
    }

    public final void d(String str) {
        this.j++;
        a(b.a(str));
    }

    public final void a(String str, String str2) {
        this.j++;
        a(b.a(str, str2));
    }

    public final void a(String str, String str2, String str3) {
        this.j++;
        a(b.d(str, str2, str3));
    }

    public final void b(String str, String str2, String str3) {
        this.j++;
        a(b.a(str, str2, str3));
    }

    public final void c(String str, String str2, String str3) {
        this.j++;
        a(b.b(str, str2, str3));
    }

    public final void d(String str, String str2, String str3) {
        this.j++;
        a(b.c(str, str2, str3));
    }

    public final void e(String str, String str2, String str3) {
        this.j++;
        a(b.e(str, str2, str3));
    }

    public final void f(String str, String str2, String str3) {
        this.j++;
        a(b.f(str, str2, str3));
    }

    public final void a(int i) {
        if (!b(i)) {
            this.j++;
        }
        this.g |= i;
    }

    public final void e(String str) {
        if (this.h != null) {
            o();
            l.f(Level.WARNING, "Trying to set more than one pseudo-element");
        } else {
            this.k++;
            this.h = str;
        }
    }

    public final boolean b(int i) {
        return (this.g & i) != 0;
    }

    public final String f() {
        return this.h;
    }

    public final f g() {
        return this.f992b;
    }

    public final com.d.c.e.d h() {
        return this.f991a;
    }

    public final int i() {
        return this.d;
    }

    public final int j() {
        return this.i;
    }

    public final int k() {
        return this.k;
    }

    public final int l() {
        return this.j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String m() {
        if (this.f992b != null) {
            return this.f992b.m();
        }
        String str = "000" + j();
        String str2 = "000" + l();
        String str3 = "000" + k();
        String str4 = "00000" + this.l;
        return "0" + str.substring(str.length() - 3) + str2.substring(str2.length() - 3) + str3.substring(str3.length() - 3) + str4.substring(str4.length() - 5);
    }

    private Object a(Object obj, com.d.c.b.d dVar) {
        Object obj2 = null;
        switch (this.d) {
            case 2:
                obj2 = dVar.b(obj);
                break;
            default:
                l.c("Bad sibling axis");
                break;
        }
        return obj2;
    }

    private void a(b bVar) {
        if (this.m == null) {
            this.m = new ArrayList();
        }
        if (this.h != null) {
            this.m.add(b.f());
            l.f(Level.WARNING, "Trying to append conditions to pseudoElement " + this.h);
        }
        this.m.add(bVar);
    }

    public final int n() {
        return this.n;
    }

    public final void f(String str) {
        this.e = str;
        this.k++;
    }

    public final void c(int i) {
        this.l = i;
        if (this.c != null) {
            this.c.c(i);
        }
        if (this.f992b != null) {
            this.f992b.c(i);
        }
    }

    public final void a(com.d.c.e.d dVar) {
        this.f991a = dVar;
    }

    public final void d(int i) {
        this.d = i;
    }

    public final void e(int i) {
        this.i = i;
    }

    public final void f(int i) {
        this.j = i;
    }

    public final void g(int i) {
        this.k = i;
    }

    public final void a(f fVar) {
        this.f992b = fVar;
    }

    public final void b(f fVar) {
        this.c = fVar;
    }

    public final void g(String str) {
        this.f = str;
    }
}
