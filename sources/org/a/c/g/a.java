package org.a.c.g;

import java.io.FilterOutputStream;
import java.io.OutputStream;

/* loaded from: infinitode-2.jar:org/a/c/g/a.class */
public final class a extends FilterOutputStream {

    /* renamed from: a, reason: collision with root package name */
    private static byte[] f4448a = {13, 10};

    /* renamed from: b, reason: collision with root package name */
    private static byte[] f4449b = {10};
    private long c;
    private boolean d;

    public a(OutputStream outputStream) {
        super(outputStream);
        this.c = 0L;
        this.d = false;
    }

    public final long a() {
        return this.c;
    }

    private boolean d() {
        return this.d;
    }

    private void a(boolean z) {
        this.d = z;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) {
        a(false);
        this.out.write(bArr, i, i2);
        this.c += i2;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public final void write(int i) {
        a(false);
        this.out.write(i);
        this.c++;
    }

    public final void b() {
        write(f4448a);
    }

    public final void c() {
        if (!d()) {
            write(f4449b);
            a(true);
        }
    }
}
