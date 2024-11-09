package org.a.b.c;

/* loaded from: infinitode-2.jar:org/a/b/c/d.class */
public final class d {

    /* renamed from: a, reason: collision with root package name */
    private byte[] f4261a;

    /* renamed from: b, reason: collision with root package name */
    private int f4262b;
    private int c;
    private int d = 0;

    public final int a() {
        return this.d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(byte[] bArr) {
        this.c = a.a(bArr, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(byte[] bArr) {
        this.f4261a = bArr;
        this.d = this.f4261a.length;
        this.f4262b = a.a(bArr, bArr.length);
    }

    public final boolean a(byte[] bArr, int i) {
        int a2;
        if (i == this.d && (a2 = a.a(bArr, i)) >= this.f4262b && a2 <= this.c) {
            return true;
        }
        return false;
    }
}
