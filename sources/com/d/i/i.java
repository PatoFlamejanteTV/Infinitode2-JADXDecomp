package com.d.i;

/* loaded from: infinitode-2.jar:com/d/i/i.class */
public final class i {

    /* renamed from: a, reason: collision with root package name */
    private int f1356a = -1;

    /* renamed from: b, reason: collision with root package name */
    private int f1357b = -1;

    public final int a() {
        return this.f1356a;
    }

    public final void a(int i) {
        if (this.f1356a == -1 || i < this.f1356a) {
            this.f1356a = i;
        }
    }

    public final int b() {
        return this.f1357b;
    }

    public final void b(int i) {
        if (this.f1357b == -1 || i > this.f1357b) {
            this.f1357b = i;
        }
    }

    public final String toString() {
        return "[top=" + this.f1356a + ", bottom=" + this.f1357b + "]";
    }
}
