package org.a.b.f;

import java.io.File;
import java.io.RandomAccessFile;

/* loaded from: infinitode-2.jar:org/a/b/f/a.class */
public final class a extends RandomAccessFile {

    /* renamed from: a, reason: collision with root package name */
    private final byte[] f4269a;

    /* renamed from: b, reason: collision with root package name */
    private int f4270b;
    private int c;
    private long d;
    private final int e;

    public a(File file, String str, int i) {
        super(file, str);
        this.f4270b = 0;
        this.c = 0;
        this.d = 0L;
        this.e = 16384;
        this.f4269a = new byte[this.e];
    }

    @Override // java.io.RandomAccessFile
    public final int read() {
        if ((this.c >= this.f4270b && a() < 0) || this.f4270b == 0) {
            return -1;
        }
        byte[] bArr = this.f4269a;
        int i = this.c;
        this.c = i + 1;
        return (bArr[i] + 256) & 255;
    }

    private int a() {
        int read = super.read(this.f4269a, 0, this.e);
        if (read >= 0) {
            this.d += read;
            this.f4270b = read;
            this.c = 0;
        }
        return read;
    }

    private void b() {
        this.f4270b = 0;
        this.c = 0;
        this.d = super.getFilePointer();
    }

    @Override // java.io.RandomAccessFile
    public final int read(byte[] bArr, int i, int i2) {
        int read;
        int i3 = this.f4270b - this.c;
        if (i2 <= i3) {
            System.arraycopy(this.f4269a, this.c, bArr, i, i2);
            this.c += i2;
            return i2;
        }
        System.arraycopy(this.f4269a, this.c, bArr, i, i3);
        this.c += i3;
        if (a() > 0 && (read = read(bArr, i + i3, i2 - i3)) > 0) {
            i3 += read;
        }
        if (i3 > 0) {
            return i3;
        }
        return -1;
    }

    @Override // java.io.RandomAccessFile
    public final long getFilePointer() {
        return (this.d - this.f4270b) + this.c;
    }

    @Override // java.io.RandomAccessFile
    public final void seek(long j) {
        int i = (int) (this.d - j);
        if (i >= 0 && i <= this.f4270b) {
            this.c = this.f4270b - i;
        } else {
            super.seek(j);
            b();
        }
    }
}
