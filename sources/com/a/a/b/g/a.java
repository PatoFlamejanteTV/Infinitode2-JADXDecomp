package com.a.a.b.g;

import java.util.concurrent.atomic.AtomicReferenceArray;

/* loaded from: infinitode-2.jar:com/a/a/b/g/a.class */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f150a = {8000, 8000, 2000, 2000};

    /* renamed from: b, reason: collision with root package name */
    private static final int[] f151b = {4000, 4000, 200, 200};
    private AtomicReferenceArray<byte[]> c;
    private AtomicReferenceArray<char[]> d;

    public a() {
        this(4, 4);
    }

    private a(int i, int i2) {
        this.c = new AtomicReferenceArray<>(4);
        this.d = new AtomicReferenceArray<>(4);
    }

    public final byte[] a(int i) {
        return b(i, 0);
    }

    private byte[] b(int i, int i2) {
        int c = c(i);
        if (c > 0) {
            i2 = c;
        }
        byte[] andSet = this.c.getAndSet(i, null);
        byte[] bArr = andSet;
        if (andSet == null || bArr.length < i2) {
            bArr = e(i2);
        }
        return bArr;
    }

    public final void a(int i, byte[] bArr) {
        this.c.set(i, bArr);
    }

    public final char[] b(int i) {
        return a(i, 0);
    }

    public final char[] a(int i, int i2) {
        int d = d(i);
        if (i2 < d) {
            i2 = d;
        }
        char[] andSet = this.d.getAndSet(i, null);
        char[] cArr = andSet;
        if (andSet == null || cArr.length < i2) {
            cArr = f(i2);
        }
        return cArr;
    }

    public final void a(int i, char[] cArr) {
        this.d.set(i, cArr);
    }

    private static int c(int i) {
        return f150a[i];
    }

    private static int d(int i) {
        return f151b[i];
    }

    private static byte[] e(int i) {
        return new byte[i];
    }

    private static char[] f(int i) {
        return new char[i];
    }
}
