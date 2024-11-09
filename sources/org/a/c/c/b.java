package org.a.c.c;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: infinitode-2.jar:org/a/c/c/b.class */
final class b extends FilterInputStream {

    /* renamed from: a, reason: collision with root package name */
    private int f4385a;

    /* renamed from: b, reason: collision with root package name */
    private int f4386b;
    private boolean c;
    private byte[] d;
    private byte[] e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(InputStream inputStream) {
        super(inputStream);
        this.f4385a = 0;
        this.f4386b = 0;
        this.c = false;
        this.d = new byte[5];
        this.e = new byte[4];
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00c1, code lost:            r11.d[r12] = r0;     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00cb, code lost:            if (r0 != 126) goto L42;     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00ce, code lost:            r11.d[r12] = 117;     */
    @Override // java.io.FilterInputStream, java.io.InputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int read() {
        /*
            Method dump skipped, instructions count: 412
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.c.c.b.read():int");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) {
        if (this.c && this.f4385a >= this.f4386b) {
            return -1;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.f4385a < this.f4386b) {
                byte[] bArr2 = this.e;
                int i4 = this.f4385a;
                this.f4385a = i4 + 1;
                bArr[i3 + i] = bArr2[i4];
            } else {
                int read = read();
                if (read == -1) {
                    return i3;
                }
                bArr[i3 + i] = (byte) read;
            }
        }
        return i2;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.d = null;
        this.c = true;
        this.e = null;
        super.close();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final long skip(long j) {
        return 0L;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int available() {
        return 0;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final void mark(int i) {
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final void reset() {
        throw new IOException("Reset is not supported");
    }
}
