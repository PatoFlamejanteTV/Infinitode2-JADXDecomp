package com.prineside.tdi2.utils;

import java.io.OutputStream;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MultiOutputStreamSync.class */
public class MultiOutputStreamSync extends OutputStream {

    /* renamed from: a, reason: collision with root package name */
    private OutputStream[] f3869a;
    public final Object synchronizer = new Object();

    public MultiOutputStreamSync(OutputStream... outputStreamArr) {
        this.f3869a = outputStreamArr;
    }

    @Override // java.io.OutputStream
    @IgnoreMethodOverloadLuaDefWarning
    public void write(int i) {
        synchronized (this.synchronizer) {
            for (OutputStream outputStream : this.f3869a) {
                outputStream.write(i);
            }
        }
    }

    @Override // java.io.OutputStream
    @IgnoreMethodOverloadLuaDefWarning
    public void write(byte[] bArr) {
        synchronized (this.synchronizer) {
            for (OutputStream outputStream : this.f3869a) {
                outputStream.write(bArr);
            }
        }
    }

    @Override // java.io.OutputStream
    @IgnoreMethodOverloadLuaDefWarning
    public void write(byte[] bArr, int i, int i2) {
        synchronized (this.synchronizer) {
            for (OutputStream outputStream : this.f3869a) {
                outputStream.write(bArr, i, i2);
            }
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        synchronized (this.synchronizer) {
            for (OutputStream outputStream : this.f3869a) {
                outputStream.flush();
            }
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this.synchronizer) {
            for (OutputStream outputStream : this.f3869a) {
                outputStream.close();
            }
        }
    }
}
