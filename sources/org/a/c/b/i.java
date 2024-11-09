package org.a.c.b;

import java.io.OutputStream;

/* loaded from: infinitode-2.jar:org/a/c/b/i.class */
public final class i extends l {
    private static final i[] c = new i[357];

    /* renamed from: a, reason: collision with root package name */
    public static final i f4366a = a(0);

    /* renamed from: b, reason: collision with root package name */
    public static final i f4367b = a(1);
    private final long d;

    static {
        a(2L);
        a(3L);
    }

    public static i a(long j) {
        if (-100 <= j && j <= 256) {
            int i = ((int) j) - (-100);
            if (c[i] == null) {
                c[i] = new i(j);
            }
            return c[i];
        }
        return new i(j);
    }

    private i(long j) {
        this.d = j;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof i) && ((i) obj).c() == c();
    }

    public final int hashCode() {
        return (int) (this.d ^ (this.d >> 32));
    }

    public final String toString() {
        return "COSInt{" + this.d + "}";
    }

    @Override // org.a.c.b.l
    public final float a() {
        return (float) this.d;
    }

    @Override // org.a.c.b.l
    public final int c() {
        return (int) this.d;
    }

    @Override // org.a.c.b.l
    public final long b() {
        return this.d;
    }

    @Override // org.a.c.b.b
    public final Object a(u uVar) {
        return uVar.a(this);
    }

    public final void a(OutputStream outputStream) {
        outputStream.write(String.valueOf(this.d).getBytes("ISO-8859-1"));
    }
}
