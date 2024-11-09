package org.a.b.b;

import java.io.EOFException;

/* loaded from: infinitode-2.jar:org/a/b/b/b.class */
public /* synthetic */ class b {

    /* renamed from: a, reason: collision with root package name */
    private byte[] f4194a;

    /* renamed from: b, reason: collision with root package name */
    private int f4195b = 0;

    public b(byte[] bArr) {
        this.f4194a = null;
        this.f4194a = bArr;
    }

    public boolean a() {
        return this.f4195b < this.f4194a.length;
    }

    public int b() {
        return this.f4195b;
    }

    public void a(int i) {
        this.f4195b = i;
    }

    public byte c() {
        try {
            byte b2 = this.f4194a[this.f4195b];
            this.f4195b++;
            return b2;
        } catch (RuntimeException unused) {
            return (byte) -1;
        }
    }

    public int d() {
        int i = i();
        if (i < 0) {
            throw new EOFException();
        }
        return i;
    }

    public int b(int i) {
        int d = d(i);
        if (d < 0) {
            throw new EOFException();
        }
        return d;
    }

    public short e() {
        return (short) f();
    }

    public int f() {
        int i = i();
        int i2 = i();
        if ((i | i2) < 0) {
            throw new EOFException();
        }
        return (i << 8) | i2;
    }

    public int g() {
        int i = i();
        int i2 = i();
        int i3 = i();
        int i4 = i();
        if ((i | i2 | i3 | i4) < 0) {
            throw new EOFException();
        }
        return (i << 24) | (i2 << 16) | (i3 << 8) | i4;
    }

    public byte[] c(int i) {
        if (this.f4194a.length - this.f4195b < i) {
            throw new EOFException();
        }
        byte[] bArr = new byte[i];
        System.arraycopy(this.f4194a, this.f4195b, bArr, 0, i);
        this.f4195b += i;
        return bArr;
    }

    private int i() {
        try {
            int i = this.f4194a[this.f4195b] & 255;
            this.f4195b++;
            return i;
        } catch (RuntimeException unused) {
            return -1;
        }
    }

    private int d(int i) {
        try {
            return this.f4194a[this.f4195b + i] & 255;
        } catch (RuntimeException unused) {
            return -1;
        }
    }

    public int h() {
        return this.f4194a.length;
    }
}
