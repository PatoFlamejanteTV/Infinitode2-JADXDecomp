package com.d.e;

import java.util.LinkedList;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/e/g.class */
public final class g {

    /* renamed from: b, reason: collision with root package name */
    private com.d.d.m f1134b;
    private List c;
    private f e;

    /* renamed from: a, reason: collision with root package name */
    private LinkedList<f> f1133a = new LinkedList<>();
    private int d = 0;

    public g(com.d.d.m mVar, List list) {
        this.e = null;
        this.f1134b = mVar;
        this.c = list;
        if (list.size() > 0) {
            this.e = (f) list.get(0);
        }
    }

    public final void a(com.d.i.ab abVar, int i) {
        while (this.e != null && this.e.b().a() == i) {
            this.e.a(this.f1134b.c());
            this.f1133a.add(this.e);
            this.f1134b.d(this.e.a().k(abVar));
            if (this.d == this.c.size() - 1) {
                this.e = null;
            } else {
                List list = this.c;
                int i2 = this.d + 1;
                this.d = i2;
                this.e = (f) list.get(i2);
            }
        }
    }

    public final void a(int i) {
        while (this.f1133a.size() > 0) {
            f last = this.f1133a.getLast();
            if (last.b().b() == i) {
                this.f1134b.e(last.c());
                this.f1133a.removeLast();
            } else {
                return;
            }
        }
    }
}
