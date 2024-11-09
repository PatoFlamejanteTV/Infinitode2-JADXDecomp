package com.d.e;

import java.awt.Shape;

/* loaded from: infinitode-2.jar:com/d/e/f.class */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    private com.d.i.c f1131a;

    /* renamed from: b, reason: collision with root package name */
    private e f1132b;
    private Shape c;

    public f() {
    }

    public f(com.d.i.c cVar, e eVar) {
        this.f1131a = cVar;
        this.f1132b = eVar;
    }

    public final com.d.i.c a() {
        return this.f1131a;
    }

    public final e b() {
        return this.f1132b;
    }

    public final Shape c() {
        return this.c;
    }

    public final void a(Shape shape) {
        this.c = shape;
    }

    public final String toString() {
        return "[range= " + this.f1132b + ", box=" + this.f1131a + ", clip=" + this.c + "]";
    }
}
