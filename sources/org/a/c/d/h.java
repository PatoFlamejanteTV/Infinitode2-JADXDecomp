package org.a.c.d;

import java.io.EOFException;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/a/c/d/h.class */
public class h implements e {

    /* renamed from: a, reason: collision with root package name */
    private final int f4423a;

    /* renamed from: b, reason: collision with root package name */
    private g f4424b;
    private int d;
    private long e;
    private byte[] f;
    private int g;
    private static final org.a.a.a.a k = org.a.a.a.c.a(h.class);
    private long c = 0;
    private boolean h = false;
    private int[] i = new int[16];
    private int j = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(g gVar) {
        gVar.c();
        this.f4424b = gVar;
        this.f4423a = 4096;
        h();
    }

    private void g() {
        if (this.f4424b == null) {
            throw new IOException("Buffer already closed");
        }
        this.f4424b.c();
    }

    private void h() {
        if (this.j + 1 >= this.i.length) {
            int length = this.i.length << 1;
            int i = length;
            if (length < this.i.length) {
                if (this.i.length == Integer.MAX_VALUE) {
                    throw new IOException("Maximum buffer size reached.");
                }
                i = Integer.MAX_VALUE;
            }
            int[] iArr = new int[i];
            System.arraycopy(this.i, 0, iArr, 0, this.j);
            this.i = iArr;
        }
        this.i[this.j] = this.f4424b.b();
        this.d = this.j;
        this.e = this.j * this.f4423a;
        this.j++;
        this.f = new byte[this.f4423a];
        this.g = 0;
    }

    @Override // org.a.c.d.e
    public final long c() {
        return this.c;
    }

    private boolean a(boolean z) {
        if (this.g >= this.f4423a) {
            if (this.h) {
                this.f4424b.a(this.i[this.d], this.f);
                this.h = false;
            }
            if (this.d + 1 < this.j) {
                g gVar = this.f4424b;
                int[] iArr = this.i;
                int i = this.d + 1;
                this.d = i;
                this.f = gVar.a(iArr[i]);
                this.e = this.d * this.f4423a;
                this.g = 0;
                return true;
            }
            if (z) {
                h();
                return true;
            }
            return false;
        }
        return true;
    }

    @Override // org.a.c.d.f
    public final void a(int i) {
        g();
        a(true);
        byte[] bArr = this.f;
        int i2 = this.g;
        this.g = i2 + 1;
        bArr[i2] = (byte) i;
        this.h = true;
        if (this.e + this.g > this.c) {
            this.c = this.e + this.g;
        }
    }

    @Override // org.a.c.d.f
    public final void a(byte[] bArr) {
        b(bArr, 0, bArr.length);
    }

    @Override // org.a.c.d.f
    public final void b(byte[] bArr, int i, int i2) {
        g();
        int i3 = i2;
        int i4 = i;
        while (i3 > 0) {
            a(true);
            int min = Math.min(i3, this.f4423a - this.g);
            System.arraycopy(bArr, i4, this.f, this.g, min);
            this.g += min;
            this.h = true;
            i4 += min;
            i3 -= min;
        }
        if (this.e + this.g > this.c) {
            this.c = this.e + this.g;
        }
    }

    @Override // org.a.c.d.e
    public final long a() {
        g();
        return this.e + this.g;
    }

    @Override // org.a.c.d.e
    public final void a(long j) {
        g();
        if (j > this.c) {
            throw new EOFException();
        }
        if (j < 0) {
            throw new IOException("Negative seek offset: " + j);
        }
        if (j >= this.e && j <= this.e + this.f4423a) {
            this.g = (int) (j - this.e);
            return;
        }
        if (this.h) {
            this.f4424b.a(this.i[this.d], this.f);
            this.h = false;
        }
        int i = (int) (j / this.f4423a);
        this.f = this.f4424b.a(this.i[i]);
        this.d = i;
        this.e = this.d * this.f4423a;
        this.g = (int) (j - this.e);
    }

    @Override // org.a.c.d.e
    public final boolean d() {
        return this.f4424b == null;
    }

    @Override // org.a.c.d.e
    public final int f() {
        int b2 = b();
        if (b2 != -1) {
            b(1);
        }
        return b2;
    }

    @Override // org.a.c.d.e
    public final void b(int i) {
        a((this.e + this.g) - i);
    }

    @Override // org.a.c.d.e
    public final byte[] c(int i) {
        int i2;
        byte[] bArr = new byte[i];
        int i3 = 0;
        do {
            int a2 = a(bArr, i3, i - i3);
            if (a2 >= 0) {
                i2 = i3 + a2;
                i3 = i2;
            } else {
                throw new EOFException();
            }
        } while (i2 < i);
        return bArr;
    }

    @Override // org.a.c.d.e
    public final boolean e() {
        g();
        return this.e + ((long) this.g) >= this.c;
    }

    @Override // org.a.c.d.e
    public final int b() {
        g();
        if (this.e + this.g >= this.c) {
            return -1;
        }
        if (!a(false)) {
            throw new IOException("Unexpectedly no bytes available for read in buffer.");
        }
        byte[] bArr = this.f;
        int i = this.g;
        this.g = i + 1;
        return bArr[i] & 255;
    }

    @Override // org.a.c.d.e
    public final int b(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    @Override // org.a.c.d.e
    public final int a(byte[] bArr, int i, int i2) {
        g();
        if (this.e + this.g >= this.c) {
            return -1;
        }
        int min = (int) Math.min(i2, this.c - (this.e + this.g));
        int i3 = 0;
        int i4 = i;
        while (min > 0) {
            if (!a(false)) {
                throw new IOException("Unexpectedly no bytes available for read in buffer.");
            }
            int min2 = Math.min(min, this.f4423a - this.g);
            System.arraycopy(this.f, this.g, bArr, i4, min2);
            this.g += min2;
            i3 += min2;
            i4 += min2;
            min -= min2;
        }
        return i3;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f4424b != null) {
            this.f4424b.a(this.i, 0, this.j);
            this.f4424b = null;
            this.i = null;
            this.f = null;
            this.e = 0L;
            this.d = -1;
            this.g = 0;
            this.c = 0L;
        }
    }

    protected void finalize() {
        try {
            close();
        } finally {
            super.finalize();
        }
    }
}
