package com.badlogic.gdx.utils;

import com.badlogic.gdx.utils.StreamUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/DataBuffer.class */
public class DataBuffer extends DataOutput {
    private final StreamUtils.OptimizedByteArrayOutputStream outStream;

    public DataBuffer() {
        this(32);
    }

    public DataBuffer(int i) {
        super(new StreamUtils.OptimizedByteArrayOutputStream(i));
        this.outStream = (StreamUtils.OptimizedByteArrayOutputStream) this.out;
    }

    public byte[] getBuffer() {
        return this.outStream.getBuffer();
    }

    public byte[] toArray() {
        return this.outStream.toByteArray();
    }
}
