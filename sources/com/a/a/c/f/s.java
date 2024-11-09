package com.a.a.c.f;

import com.a.a.a.s;
import com.a.a.c.a;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/a/a/c/f/s.class */
public abstract class s implements com.a.a.c.m.v {

    /* renamed from: a, reason: collision with root package name */
    protected static final s.b f472a = s.b.a();

    @Override // com.a.a.c.m.v
    public abstract String a();

    public abstract com.a.a.c.w b();

    public abstract com.a.a.c.w c();

    public abstract boolean d();

    public abstract com.a.a.c.j f();

    public abstract Class<?> g();

    public abstract com.a.a.c.v h();

    public abstract boolean k();

    public abstract boolean l();

    public abstract boolean m();

    public abstract k n();

    public abstract k o();

    public abstract h p();

    public abstract n q();

    public abstract j v();

    public abstract s.b B();

    public boolean a(com.a.a.c.w wVar) {
        return b().equals(wVar);
    }

    public boolean e() {
        return d();
    }

    public boolean i() {
        return t() != null;
    }

    public boolean j() {
        return s() != null;
    }

    public Iterator<n> r() {
        return com.a.a.c.m.i.a();
    }

    public final j s() {
        k n = n();
        k kVar = n;
        if (n == null) {
            kVar = p();
        }
        return kVar;
    }

    public final j t() {
        n q = q();
        n nVar = q;
        if (q == null) {
            k o = o();
            nVar = o;
            if (o == null) {
                nVar = p();
            }
        }
        return nVar;
    }

    public final j u() {
        k o = o();
        k kVar = o;
        if (o == null) {
            kVar = p();
        }
        return kVar;
    }

    public Class<?>[] w() {
        return null;
    }

    public a.C0004a x() {
        return null;
    }

    public final String y() {
        a.C0004a x = x();
        if (x == null) {
            return null;
        }
        return x.a();
    }

    public boolean z() {
        return false;
    }

    public ad A() {
        return null;
    }
}
