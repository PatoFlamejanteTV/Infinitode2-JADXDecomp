package com.prineside.tdi2.utils;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/LimitedBAOS.class */
public final class LimitedBAOS extends ByteArrayOutputStream {

    /* renamed from: a, reason: collision with root package name */
    private final int f3857a;

    /* renamed from: b, reason: collision with root package name */
    private final float f3858b;

    public LimitedBAOS(int i, int i2, float f) {
        if (i < 0) {
            throw new IllegalArgumentException("Negative initial size: " + i);
        }
        if (f <= 0.0f || f >= 1.0f) {
            throw new IllegalArgumentException("Invalid cleanupMultiplier, must be (0..1): " + f);
        }
        if (i2 < i) {
            throw new IllegalArgumentException("Max size can not be " + i2);
        }
        this.buf = new byte[i];
        this.f3857a = i2;
        this.f3858b = f;
    }

    private void a(int i) {
        if (i - this.buf.length > 0) {
            b(i);
        }
    }

    private void b(int i) {
        int length = this.buf.length << 1;
        int i2 = length;
        if (length - i < 0) {
            i2 = i;
        }
        if (i2 - 2147483639 > 0) {
            i2 = c(i);
        }
        if (i2 > this.f3857a) {
            int i3 = (int) (this.count * (1.0f - this.f3858b));
            int i4 = this.count - i3;
            byte[] bArr = this.buf;
            System.arraycopy(bArr, i4, bArr, 0, i3);
            this.count = i3;
            return;
        }
        this.buf = Arrays.copyOf(this.buf, i2);
    }

    private static int c(int i) {
        if (i < 0) {
            throw new OutOfMemoryError();
        }
        if (i > 2147483639) {
            return Integer.MAX_VALUE;
        }
        return 2147483639;
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream
    @IgnoreMethodOverloadLuaDefWarning
    public final synchronized void write(int i) {
        a(this.count + 1);
        this.buf[this.count] = (byte) i;
        this.count++;
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream
    @IgnoreMethodOverloadLuaDefWarning
    public final synchronized void write(byte[] bArr, int i, int i2) {
        if (i < 0 || i > bArr.length || i2 < 0 || (i + i2) - bArr.length > 0) {
            throw new IndexOutOfBoundsException();
        }
        a(this.count + i2);
        System.arraycopy(bArr, i, this.buf, this.count, i2);
        this.count += i2;
    }

    @Override // java.io.ByteArrayOutputStream
    public final synchronized void writeTo(OutputStream outputStream) {
        outputStream.write(this.buf, 0, this.count);
    }

    @Override // java.io.ByteArrayOutputStream
    public final synchronized void reset() {
        this.count = 0;
    }

    @Override // java.io.ByteArrayOutputStream
    public final synchronized byte[] toByteArray() {
        return Arrays.copyOf(this.buf, this.count);
    }

    @Override // java.io.ByteArrayOutputStream
    public final synchronized int size() {
        return this.count;
    }

    @Override // java.io.ByteArrayOutputStream
    @IgnoreMethodOverloadLuaDefWarning
    public final synchronized String toString() {
        return new String(this.buf, 0, this.count);
    }

    @Override // java.io.ByteArrayOutputStream
    @IgnoreMethodOverloadLuaDefWarning
    public final synchronized String toString(String str) {
        return new String(this.buf, 0, this.count, str);
    }

    @Override // java.io.ByteArrayOutputStream
    @Deprecated
    public final synchronized String toString(int i) {
        return new String(this.buf, i, 0, this.count);
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }
}
