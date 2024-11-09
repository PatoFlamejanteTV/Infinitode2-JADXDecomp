package com.d.i;

import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/i/j.class */
public final class j {

    /* renamed from: a, reason: collision with root package name */
    private j f1358a;

    /* renamed from: b, reason: collision with root package name */
    private int f1359b;
    private List c = new ArrayList();
    private aa d;

    public j(com.d.e.v vVar, int i) {
        this.f1359b = c(vVar, i).i();
    }

    public final int a() {
        return this.f1359b;
    }

    public final int b() {
        return (this.f1359b + this.c.size()) - 1;
    }

    public final i a(int i) {
        return a(i, false);
    }

    private i a(int i, boolean z) {
        if (z) {
            while (this.c.size() < (i - this.f1359b) + 1) {
                this.c.add(new i());
            }
        }
        int i2 = i - this.f1359b;
        if (i2 >= 0 && i2 < this.c.size()) {
            return (i) this.c.get(i - this.f1359b);
        }
        return null;
    }

    public final void a(com.d.e.v vVar, int i) {
        a(c(vVar, i).i(), true).a(i);
        j e = e();
        if (e != null) {
            e.a(vVar, i);
        }
    }

    public final void b(com.d.e.v vVar, int i) {
        a(c(vVar, i).i(), true).b(i);
        j e = e();
        if (e != null) {
            e.b(vVar, i);
        }
    }

    private aa c(com.d.e.v vVar, int i) {
        aa a2;
        aa d = d();
        if (d != null && i >= d.b() && i < d.a()) {
            a2 = d;
        } else {
            a2 = vVar.p().a(vVar, i);
            a(a2);
        }
        return a2;
    }

    private aa d() {
        j jVar = this;
        while (true) {
            j jVar2 = jVar;
            if (jVar2.e() != null) {
                jVar = jVar2.e();
            } else {
                return jVar2.d;
            }
        }
    }

    private void a(aa aaVar) {
        j jVar = this;
        while (true) {
            j jVar2 = jVar;
            if (jVar2.e() != null) {
                jVar = jVar2.e();
            } else {
                jVar2.d = aaVar;
                return;
            }
        }
    }

    private j e() {
        return this.f1358a;
    }

    public final void a(j jVar) {
        this.f1358a = jVar;
    }

    public final boolean c() {
        return this.c.size() > 1;
    }

    public final String toString() {
        return "[initialPageNo=" + this.f1359b + ", limits=" + this.c + "]";
    }
}
