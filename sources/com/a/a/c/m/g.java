package com.a.a.c.m;

import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/a/a/c/m/g.class */
public final class g extends InputStream {

    /* renamed from: a, reason: collision with root package name */
    private ByteBuffer f721a;

    public g(ByteBuffer byteBuffer) {
        this.f721a = byteBuffer;
    }

    @Override // java.io.InputStream
    public final int available() {
        return this.f721a.remaining();
    }

    @Override // java.io.InputStream
    public final int read() {
        if (this.f721a.hasRemaining()) {
            return this.f721a.get() & 255;
        }
        return -1;
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) {
        if (!this.f721a.hasRemaining()) {
            return -1;
        }
        int min = Math.min(i2, this.f721a.remaining());
        this.f721a.get(bArr, i, min);
        return min;
    }
}
