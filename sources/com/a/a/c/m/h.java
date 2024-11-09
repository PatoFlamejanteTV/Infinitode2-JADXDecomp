package com.a.a.c.m;

import java.io.OutputStream;
import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/a/a/c/m/h.class */
public final class h extends OutputStream {

    /* renamed from: a, reason: collision with root package name */
    private ByteBuffer f722a;

    public h(ByteBuffer byteBuffer) {
        this.f722a = byteBuffer;
    }

    @Override // java.io.OutputStream
    public final void write(int i) {
        this.f722a.put((byte) i);
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) {
        this.f722a.put(bArr, i, i2);
    }
}
