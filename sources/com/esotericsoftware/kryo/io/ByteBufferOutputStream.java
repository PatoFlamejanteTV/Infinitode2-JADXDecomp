package com.esotericsoftware.kryo.io;

import java.io.OutputStream;
import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/io/ByteBufferOutputStream.class */
public class ByteBufferOutputStream extends OutputStream {
    private ByteBuffer byteBuffer;

    public ByteBufferOutputStream() {
    }

    public ByteBufferOutputStream(int i) {
        this(ByteBuffer.allocate(i));
    }

    public ByteBufferOutputStream(ByteBuffer byteBuffer) {
        this.byteBuffer = byteBuffer;
    }

    public ByteBuffer getByteBuffer() {
        return this.byteBuffer;
    }

    public void setByteBuffer(ByteBuffer byteBuffer) {
        this.byteBuffer = byteBuffer;
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        if (!this.byteBuffer.hasRemaining()) {
            flush();
        }
        this.byteBuffer.put((byte) i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        if (this.byteBuffer.remaining() < i2) {
            flush();
        }
        this.byteBuffer.put(bArr, i, i2);
    }
}
