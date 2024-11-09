package com.b.a.c;

/* loaded from: infinitode-2.jar:com/b/a/c/f.class */
public class f {

    /* renamed from: a, reason: collision with root package name */
    int f865a;

    /* renamed from: b, reason: collision with root package name */
    int f866b;
    int c;
    byte d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f() {
        this(0, 0, (byte) 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(int i, int i2, byte b2) {
        this.f865a = i;
        this.f866b = i2;
        this.d = b2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(f fVar) {
        this.f865a = fVar.f865a;
        this.f866b = fVar.f866b;
        this.d = fVar.d;
        this.c = fVar.c;
    }

    public final int a() {
        return this.f865a;
    }

    public final int b() {
        return this.f866b - this.f865a;
    }

    public final boolean c() {
        return (this.d & 1) == 0;
    }

    public final byte d() {
        return (byte) (this.d & 1);
    }

    public String toString() {
        return "BidiRun " + this.f865a + " - " + this.f866b + " @ " + ((int) this.d);
    }
}
