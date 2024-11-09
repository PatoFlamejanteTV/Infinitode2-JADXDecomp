package com.a.a.b.g;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: infinitode-2.jar:com/a/a/b/g/c.class */
public final class c extends OutputStream {

    /* renamed from: a, reason: collision with root package name */
    private static byte[] f154a = new byte[0];

    /* renamed from: b, reason: collision with root package name */
    private final LinkedList<byte[]> f155b;
    private int c;
    private byte[] d;
    private int e;

    public c() {
        this((a) null);
    }

    private c(a aVar) {
        this(aVar, 500);
    }

    public c(int i) {
        this(null, 100);
    }

    private c(a aVar, int i) {
        this.f155b = new LinkedList<>();
        this.d = aVar == null ? new byte[i > 131072 ? 131072 : i] : aVar.a(2);
    }

    public final void a() {
        this.c = 0;
        this.e = 0;
        if (!this.f155b.isEmpty()) {
            this.f155b.clear();
        }
    }

    public final void a(int i) {
        if (this.e >= this.d.length) {
            c();
        }
        byte[] bArr = this.d;
        int i2 = this.e;
        this.e = i2 + 1;
        bArr[i2] = (byte) i;
    }

    public final void b(int i) {
        if (this.e + 1 < this.d.length) {
            byte[] bArr = this.d;
            int i2 = this.e;
            this.e = i2 + 1;
            bArr[i2] = (byte) (i >> 8);
            byte[] bArr2 = this.d;
            int i3 = this.e;
            this.e = i3 + 1;
            bArr2[i3] = (byte) i;
            return;
        }
        a(i >> 8);
        a(i);
    }

    public final void c(int i) {
        if (this.e + 2 < this.d.length) {
            byte[] bArr = this.d;
            int i2 = this.e;
            this.e = i2 + 1;
            bArr[i2] = (byte) (i >> 16);
            byte[] bArr2 = this.d;
            int i3 = this.e;
            this.e = i3 + 1;
            bArr2[i3] = (byte) (i >> 8);
            byte[] bArr3 = this.d;
            int i4 = this.e;
            this.e = i4 + 1;
            bArr3[i4] = (byte) i;
            return;
        }
        a(i >> 16);
        a(i >> 8);
        a(i);
    }

    public final byte[] b() {
        int i = this.c + this.e;
        if (i == 0) {
            return f154a;
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        Iterator<byte[]> it = this.f155b.iterator();
        while (it.hasNext()) {
            byte[] next = it.next();
            int length = next.length;
            System.arraycopy(next, 0, bArr, i2, length);
            i2 += length;
        }
        System.arraycopy(this.d, 0, bArr, i2, this.e);
        int i3 = i2 + this.e;
        if (i3 != i) {
            throw new RuntimeException("Internal error: total len assumed to be " + i + ", copied " + i3 + " bytes");
        }
        if (!this.f155b.isEmpty()) {
            a();
        }
        return bArr;
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) {
        while (true) {
            int min = Math.min(this.d.length - this.e, i2);
            if (min > 0) {
                System.arraycopy(bArr, i, this.d, this.e, min);
                i += min;
                this.e += min;
                i2 -= min;
            }
            if (i2 > 0) {
                c();
            } else {
                return;
            }
        }
    }

    @Override // java.io.OutputStream
    public final void write(int i) {
        a(i);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public final void flush() {
    }

    private void c() {
        int length = this.c + this.d.length;
        if (length < 0) {
            throw new IllegalStateException("Maximum Java array size (2GB) exceeded by `ByteArrayBuilder`");
        }
        this.c = length;
        int max = Math.max(this.c >> 1, 1000);
        int i = max;
        if (max > 131072) {
            i = 131072;
        }
        this.f155b.add(this.d);
        this.d = new byte[i];
        this.e = 0;
    }
}
