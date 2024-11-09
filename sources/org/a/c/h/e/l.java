package org.a.c.h.e;

import com.b.a.a.o;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/c/h/e/l.class */
public class l {

    /* renamed from: a, reason: collision with root package name */
    private static j f4547a;

    /* renamed from: b, reason: collision with root package name */
    private String f4548b;
    private String c;
    private org.a.b.h.a d;
    private String e;
    private String f;
    private float g;
    private float h;
    private float i;
    private float j;
    private float l;
    private final List<String> k = new ArrayList();
    private List<o.d> m = new ArrayList();
    private Map<String, o.d> n = new HashMap();
    private List<org.a.b.a.b> o = new ArrayList();
    private List<com.b.a.b.a> p = new ArrayList();
    private List<org.a.c.h.g.e.c> q = new ArrayList();
    private List<org.a.c.h.g.e.c> r = new ArrayList();
    private List<org.a.c.h.g.e.c> s = new ArrayList();

    /* loaded from: infinitode-2.jar:org/a/c/h/e/l$a.class */
    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final j f4549a = new k();
    }

    public static j a() {
        if (f4547a == null) {
            f4547a = a.f4549a;
        }
        return f4547a;
    }

    public float a(String str) {
        float f = 0.0f;
        o.d dVar = this.n.get(str);
        if (dVar != null) {
            f = dVar.c();
        }
        return f;
    }

    public float b() {
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        for (o.d dVar : this.m) {
            if (dVar.c() > 0.0f) {
                f2 += dVar.c();
                f3 += 1.0f;
            }
        }
        if (f2 > 0.0f) {
            f = f2 / f3;
        }
        return f;
    }

    public void b(String str) {
        this.k.add(str);
    }

    public String c() {
        return this.f4548b;
    }

    public void c(String str) {
        this.f4548b = str;
    }

    public String d() {
        return this.c;
    }

    public void d(String str) {
        this.c = str;
    }

    public org.a.b.h.a e() {
        return this.d;
    }

    public void a(org.a.b.h.a aVar) {
        this.d = aVar;
    }

    public String f() {
        return this.e;
    }

    public void e(String str) {
        this.e = str;
    }

    public String g() {
        return this.f;
    }

    public void f(String str) {
        this.f = str;
    }

    public float h() {
        return this.g;
    }

    public void a(float f) {
        this.g = f;
    }

    public float i() {
        return this.h;
    }

    public void b(float f) {
        this.h = f;
    }

    public float j() {
        return this.i;
    }

    public void c(float f) {
        this.i = f;
    }

    public float k() {
        return this.j;
    }

    public void d(float f) {
        this.j = f;
    }

    public float l() {
        return this.l;
    }

    public void e(float f) {
        this.l = f;
    }

    public List<o.d> m() {
        return Collections.unmodifiableList(this.m);
    }

    public void a(List<o.d> list) {
        this.m = list;
        this.n = new HashMap(this.m.size());
        for (o.d dVar : list) {
            this.n.put(dVar.b(), dVar);
        }
    }

    public void a(org.a.b.a.b bVar) {
        this.o.add(bVar);
    }

    public void a(com.b.a.b.a aVar) {
        this.p.add(aVar);
    }

    public void a(org.a.c.h.g.e.c cVar) {
        this.q.add(cVar);
    }

    public void b(org.a.c.h.g.e.c cVar) {
        this.r.add(cVar);
    }

    public void c(org.a.c.h.g.e.c cVar) {
        this.s.add(cVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static v a(l lVar) {
        boolean equals = lVar.f().equals("FontSpecific");
        v vVar = new v();
        vVar.a(lVar.c());
        vVar.b(lVar.d());
        vVar.e(!equals);
        vVar.c(equals);
        vVar.a(new org.a.c.h.a.h(lVar.e()));
        vVar.b(lVar.l());
        vVar.c(lVar.j());
        vVar.d(lVar.k());
        vVar.e(lVar.h());
        vVar.f(lVar.i());
        vVar.h(lVar.b());
        vVar.c(lVar.g());
        vVar.g(0.0f);
        return vVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(int i) {
        String upperCase = Integer.toString(i, 16).toUpperCase(Locale.US);
        switch (upperCase.length()) {
            case 1:
                return "uni000" + upperCase;
            case 2:
                return "uni00" + upperCase;
            case 3:
                return "uni0" + upperCase;
            default:
                return "uni" + upperCase;
        }
    }
}
