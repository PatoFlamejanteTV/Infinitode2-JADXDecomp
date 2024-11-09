package com.prineside.tdi2.utils;

import java.io.OutputStream;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MultiOutputStream.class */
public class MultiOutputStream extends OutputStream {

    /* renamed from: a, reason: collision with root package name */
    private OutputStream[] f3868a;

    public MultiOutputStream(OutputStream... outputStreamArr) {
        this.f3868a = outputStreamArr;
    }

    @Override // java.io.OutputStream
    @IgnoreMethodOverloadLuaDefWarning
    public void write(int i) {
        for (OutputStream outputStream : this.f3868a) {
            outputStream.write(i);
        }
    }

    @Override // java.io.OutputStream
    @IgnoreMethodOverloadLuaDefWarning
    public void write(byte[] bArr) {
        for (OutputStream outputStream : this.f3868a) {
            outputStream.write(bArr);
        }
    }

    @Override // java.io.OutputStream
    @IgnoreMethodOverloadLuaDefWarning
    public void write(byte[] bArr, int i, int i2) {
        for (OutputStream outputStream : this.f3868a) {
            outputStream.write(bArr, i, i2);
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        for (OutputStream outputStream : this.f3868a) {
            outputStream.flush();
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        for (OutputStream outputStream : this.f3868a) {
            outputStream.close();
        }
    }
}
