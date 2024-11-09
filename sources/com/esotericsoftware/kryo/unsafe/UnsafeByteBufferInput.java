package com.esotericsoftware.kryo.unsafe;

import com.esotericsoftware.kryo.io.ByteBufferInput;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import sun.misc.Unsafe;
import sun.nio.ch.DirectBuffer;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/unsafe/UnsafeByteBufferInput.class */
public class UnsafeByteBufferInput extends ByteBufferInput {
    private long bufferAddress;

    public UnsafeByteBufferInput() {
    }

    public UnsafeByteBufferInput(int i) {
        super(i);
        updateBufferAddress();
    }

    public UnsafeByteBufferInput(byte[] bArr) {
        super(bArr);
        updateBufferAddress();
    }

    public UnsafeByteBufferInput(byte[] bArr, int i, int i2) {
        super(bArr, i, i2);
        updateBufferAddress();
    }

    public UnsafeByteBufferInput(ByteBuffer byteBuffer) {
        super(byteBuffer);
        updateBufferAddress();
    }

    public UnsafeByteBufferInput(long j, int i) {
        super(UnsafeUtil.newDirectBuffer(j, i));
        updateBufferAddress();
    }

    public UnsafeByteBufferInput(InputStream inputStream) {
        super(inputStream);
        updateBufferAddress();
    }

    public UnsafeByteBufferInput(InputStream inputStream, int i) {
        super(inputStream, i);
        updateBufferAddress();
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferInput
    public void setBuffer(ByteBuffer byteBuffer) {
        if (!(byteBuffer instanceof DirectBuffer)) {
            throw new IllegalArgumentException("buffer must be direct.");
        }
        if (byteBuffer != this.byteBuffer) {
            UnsafeUtil.dispose(this.byteBuffer);
        }
        super.setBuffer(byteBuffer);
        updateBufferAddress();
    }

    private void updateBufferAddress() {
        this.bufferAddress = this.byteBuffer.address();
    }

    private void setBufferPosition(Buffer buffer, int i) {
        buffer.position(i);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferInput, com.esotericsoftware.kryo.io.Input, java.io.InputStream
    public int read() {
        if (optional(1) <= 0) {
            return -1;
        }
        Unsafe unsafe = UnsafeUtil.unsafe;
        long j = this.bufferAddress;
        int i = this.position;
        this.position = i + 1;
        int i2 = unsafe.getByte(j + i) & 255;
        setBufferPosition(this.byteBuffer, this.position);
        return i2;
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferInput, com.esotericsoftware.kryo.io.Input
    public byte readByte() {
        if (this.position == this.limit) {
            require(1);
        }
        Unsafe unsafe = UnsafeUtil.unsafe;
        long j = this.bufferAddress;
        int i = this.position;
        this.position = i + 1;
        byte b2 = unsafe.getByte(j + i);
        setBufferPosition(this.byteBuffer, this.position);
        return b2;
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferInput, com.esotericsoftware.kryo.io.Input
    public int readByteUnsigned() {
        if (this.position == this.limit) {
            require(1);
        }
        Unsafe unsafe = UnsafeUtil.unsafe;
        long j = this.bufferAddress;
        int i = this.position;
        this.position = i + 1;
        int i2 = unsafe.getByte(j + i) & 255;
        setBufferPosition(this.byteBuffer, this.position);
        return i2;
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferInput, com.esotericsoftware.kryo.io.Input
    public int readInt() {
        require(4);
        int i = UnsafeUtil.unsafe.getInt(this.bufferAddress + this.position);
        this.position += 4;
        setBufferPosition(this.byteBuffer, this.position);
        return i;
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferInput, com.esotericsoftware.kryo.io.Input
    public long readLong() {
        require(8);
        long j = UnsafeUtil.unsafe.getLong(this.bufferAddress + this.position);
        this.position += 8;
        setBufferPosition(this.byteBuffer, this.position);
        return j;
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferInput, com.esotericsoftware.kryo.io.Input
    public float readFloat() {
        require(4);
        float f = UnsafeUtil.unsafe.getFloat(this.bufferAddress + this.position);
        this.position += 4;
        setBufferPosition(this.byteBuffer, this.position);
        return f;
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferInput, com.esotericsoftware.kryo.io.Input
    public double readDouble() {
        require(8);
        double d = UnsafeUtil.unsafe.getDouble(this.bufferAddress + this.position);
        this.position += 8;
        setBufferPosition(this.byteBuffer, this.position);
        return d;
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferInput, com.esotericsoftware.kryo.io.Input
    public short readShort() {
        require(2);
        short s = UnsafeUtil.unsafe.getShort(this.bufferAddress + this.position);
        this.position += 2;
        setBufferPosition(this.byteBuffer, this.position);
        return s;
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferInput, com.esotericsoftware.kryo.io.Input
    public char readChar() {
        require(2);
        char c = UnsafeUtil.unsafe.getChar(this.bufferAddress + this.position);
        this.position += 2;
        setBufferPosition(this.byteBuffer, this.position);
        return c;
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferInput, com.esotericsoftware.kryo.io.Input
    public boolean readBoolean() {
        if (this.position == this.limit) {
            require(1);
        }
        Unsafe unsafe = UnsafeUtil.unsafe;
        long j = this.bufferAddress;
        int i = this.position;
        this.position = i + 1;
        boolean z = unsafe.getByte(j + ((long) i)) != 0;
        setBufferPosition(this.byteBuffer, this.position);
        return z;
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferInput, com.esotericsoftware.kryo.io.Input
    public int[] readInts(int i) {
        int[] iArr = new int[i];
        readBytes(iArr, UnsafeUtil.intArrayBaseOffset, i << 2);
        return iArr;
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferInput, com.esotericsoftware.kryo.io.Input
    public long[] readLongs(int i) {
        long[] jArr = new long[i];
        readBytes(jArr, UnsafeUtil.longArrayBaseOffset, i << 3);
        return jArr;
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferInput, com.esotericsoftware.kryo.io.Input
    public float[] readFloats(int i) {
        float[] fArr = new float[i];
        readBytes(fArr, UnsafeUtil.floatArrayBaseOffset, i << 2);
        return fArr;
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferInput, com.esotericsoftware.kryo.io.Input
    public double[] readDoubles(int i) {
        double[] dArr = new double[i];
        readBytes(dArr, UnsafeUtil.doubleArrayBaseOffset, i << 3);
        return dArr;
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferInput, com.esotericsoftware.kryo.io.Input
    public short[] readShorts(int i) {
        short[] sArr = new short[i];
        readBytes(sArr, UnsafeUtil.shortArrayBaseOffset, i << 1);
        return sArr;
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferInput, com.esotericsoftware.kryo.io.Input
    public char[] readChars(int i) {
        char[] cArr = new char[i];
        readBytes(cArr, UnsafeUtil.charArrayBaseOffset, i << 1);
        return cArr;
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferInput, com.esotericsoftware.kryo.io.Input
    public boolean[] readBooleans(int i) {
        boolean[] zArr = new boolean[i];
        readBytes(zArr, UnsafeUtil.booleanArrayBaseOffset, i);
        return zArr;
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferInput, com.esotericsoftware.kryo.io.Input
    public void readBytes(byte[] bArr, int i, int i2) {
        readBytes(bArr, UnsafeUtil.byteArrayBaseOffset + i, i2);
    }

    public void readBytes(Object obj, long j, int i) {
        int min = Math.min(this.limit - this.position, i);
        while (true) {
            UnsafeUtil.unsafe.copyMemory((Object) null, this.bufferAddress + this.position, obj, j, min);
            this.position += min;
            int i2 = i - min;
            i = i2;
            if (i2 != 0) {
                j += min;
                min = Math.min(i, this.capacity);
                require(min);
            } else {
                setBufferPosition(this.byteBuffer, this.position);
                return;
            }
        }
    }
}
