package org.a.c.h.c;

import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: infinitode-2.jar:org/a/c/h/c/i.class */
final class i {

    /* renamed from: a, reason: collision with root package name */
    private final int[] f4502a = new int[256];

    /* renamed from: b, reason: collision with root package name */
    private int f4503b;
    private int c;

    public final void a(byte[] bArr) {
        this.f4503b = 0;
        this.c = 0;
        if (bArr.length <= 0 || bArr.length > 32) {
            throw new IllegalArgumentException("number of bytes must be between 1 and 32");
        }
        for (int i = 0; i < this.f4502a.length; i++) {
            int i2 = i;
            this.f4502a[i2] = i2;
        }
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < this.f4502a.length; i5++) {
            i4 = ((a(bArr[i3]) + this.f4502a[i5]) + i4) % 256;
            a(this.f4502a, i5, i4);
            i3 = (i3 + 1) % bArr.length;
        }
    }

    private static int a(byte b2) {
        return b2 < 0 ? b2 + 256 : b2;
    }

    private static void a(int[] iArr, int i, int i2) {
        int i3 = iArr[i];
        iArr[i] = iArr[i2];
        iArr[i2] = i3;
    }

    private void a(byte b2, OutputStream outputStream) {
        this.f4503b = (this.f4503b + 1) % 256;
        this.c = (this.f4502a[this.f4503b] + this.c) % 256;
        a(this.f4502a, this.f4503b, this.c);
        outputStream.write(b2 ^ ((byte) this.f4502a[(this.f4502a[this.f4503b] + this.f4502a[this.c]) % 256]));
    }

    public final void a(byte[] bArr, OutputStream outputStream) {
        for (byte b2 : bArr) {
            a(b2, outputStream);
        }
    }

    public final void a(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                a(bArr, 0, read, outputStream);
            } else {
                return;
            }
        }
    }

    private void a(byte[] bArr, int i, int i2, OutputStream outputStream) {
        for (int i3 = 0; i3 < i2 + 0; i3++) {
            a(bArr[i3], outputStream);
        }
    }
}
