package com.a.a.c;

import com.a.a.c.n;
import java.util.Iterator;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/m.class */
public abstract class m extends n.a implements Iterable<m> {
    public abstract m a(int i);

    public abstract m b(String str);

    public abstract com.a.a.c.j.m d();

    public abstract String i();

    public int a() {
        return 0;
    }

    public boolean b() {
        return false;
    }

    public boolean c() {
        return false;
    }

    public m a(String str) {
        return null;
    }

    public final boolean e() {
        return d() == com.a.a.c.j.m.STRING;
    }

    public final boolean f() {
        return d() == com.a.a.c.j.m.NULL;
    }

    public int g() {
        return 0;
    }

    public double h() {
        return 0.0d;
    }

    public String c(String str) {
        String i = i();
        return i == null ? str : i;
    }

    public int j() {
        return b(0);
    }

    public int b(int i) {
        return 0;
    }

    public double k() {
        return a(0.0d);
    }

    public double a(double d) {
        return 0.0d;
    }

    public boolean l() {
        return a(false);
    }

    public boolean a(boolean z) {
        return false;
    }

    @Override // java.lang.Iterable
    public final Iterator<m> iterator() {
        return m();
    }

    public Iterator<m> m() {
        return com.a.a.c.m.i.a();
    }

    public Iterator<Map.Entry<String, m>> n() {
        return com.a.a.c.m.i.a();
    }
}
