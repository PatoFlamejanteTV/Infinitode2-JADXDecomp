package org.a.c.d;

import java.io.OutputStream;

/* loaded from: infinitode-2.jar:org/a/c/d/d.class */
public final class d extends OutputStream {

    /* renamed from: a, reason: collision with root package name */
    private final f f4420a;

    public d(f fVar) {
        this.f4420a = fVar;
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) {
        this.f4420a.b(bArr, i, i2);
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) {
        this.f4420a.a(bArr);
    }

    @Override // java.io.OutputStream
    public final void write(int i) {
        this.f4420a.a(i);
    }
}
