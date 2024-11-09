package com.esotericsoftware.kryo.unsafe;

import com.esotericsoftware.kryo.io.Input;
import java.io.InputStream;
import sun.misc.Unsafe;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/unsafe/UnsafeInput.class */
public class UnsafeInput extends Input {
    public UnsafeInput() {
    }

    public UnsafeInput(int i) {
        super(i);
    }

    public UnsafeInput(byte[] bArr) {
        super(bArr);
    }

    public UnsafeInput(byte[] bArr, int i, int i2) {
        super(bArr, i, i2);
    }

    public UnsafeInput(InputStream inputStream) {
        super(inputStream);
    }

    public UnsafeInput(InputStream inputStream, int i) {
        super(inputStream, i);
    }

    @Override // com.esotericsoftware.kryo.io.Input, java.io.InputStream
    public int read() {
        if (optional(1) <= 0) {
            return -1;
        }
        Unsafe unsafe = UnsafeUtil.unsafe;
        byte[] bArr = this.buffer;
        long j = UnsafeUtil.byteArrayBaseOffset;
        int i = this.position;
        this.position = i + 1;
        return unsafe.getByte(bArr, j + i) & 255;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public byte readByte() {
        if (this.position == this.limit) {
            require(1);
        }
        Unsafe unsafe = UnsafeUtil.unsafe;
        byte[] bArr = this.buffer;
        long j = UnsafeUtil.byteArrayBaseOffset;
        int i = this.position;
        this.position = i + 1;
        return unsafe.getByte(bArr, j + i);
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public int readByteUnsigned() {
        if (this.position == this.limit) {
            require(1);
        }
        Unsafe unsafe = UnsafeUtil.unsafe;
        byte[] bArr = this.buffer;
        long j = UnsafeUtil.byteArrayBaseOffset;
        int i = this.position;
        this.position = i + 1;
        return unsafe.getByte(bArr, j + i) & 255;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public int readInt() {
        require(4);
        int i = UnsafeUtil.unsafe.getInt(this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position);
        this.position += 4;
        return i;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public long readLong() {
        require(8);
        long j = UnsafeUtil.unsafe.getLong(this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position);
        this.position += 8;
        return j;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public float readFloat() {
        require(4);
        float f = UnsafeUtil.unsafe.getFloat(this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position);
        this.position += 4;
        return f;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public double readDouble() {
        require(8);
        double d = UnsafeUtil.unsafe.getDouble(this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position);
        this.position += 8;
        return d;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public short readShort() {
        require(2);
        short s = UnsafeUtil.unsafe.getShort(this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position);
        this.position += 2;
        return s;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public char readChar() {
        require(2);
        char c = UnsafeUtil.unsafe.getChar(this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position);
        this.position += 2;
        return c;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public boolean readBoolean() {
        if (this.position == this.limit) {
            require(1);
        }
        Unsafe unsafe = UnsafeUtil.unsafe;
        byte[] bArr = this.buffer;
        long j = UnsafeUtil.byteArrayBaseOffset;
        int i = this.position;
        this.position = i + 1;
        return unsafe.getByte(bArr, j + ((long) i)) != 0;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public int[] readInts(int i) {
        int[] iArr = new int[i];
        readBytes(iArr, UnsafeUtil.intArrayBaseOffset, i << 2);
        return iArr;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public long[] readLongs(int i) {
        long[] jArr = new long[i];
        readBytes(jArr, UnsafeUtil.longArrayBaseOffset, i << 3);
        return jArr;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public float[] readFloats(int i) {
        float[] fArr = new float[i];
        readBytes(fArr, UnsafeUtil.floatArrayBaseOffset, i << 2);
        return fArr;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public double[] readDoubles(int i) {
        double[] dArr = new double[i];
        readBytes(dArr, UnsafeUtil.doubleArrayBaseOffset, i << 3);
        return dArr;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public short[] readShorts(int i) {
        short[] sArr = new short[i];
        readBytes(sArr, UnsafeUtil.shortArrayBaseOffset, i << 1);
        return sArr;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public char[] readChars(int i) {
        char[] cArr = new char[i];
        readBytes(cArr, UnsafeUtil.charArrayBaseOffset, i << 1);
        return cArr;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public boolean[] readBooleans(int i) {
        boolean[] zArr = new boolean[i];
        readBytes(zArr, UnsafeUtil.booleanArrayBaseOffset, i);
        return zArr;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public void readBytes(byte[] bArr, int i, int i2) {
        readBytes(bArr, UnsafeUtil.byteArrayBaseOffset + i, i2);
    }

    public void readBytes(Object obj, long j, int i) {
        int min = Math.min(this.limit - this.position, i);
        while (true) {
            UnsafeUtil.unsafe.copyMemory(this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position, obj, j, min);
            this.position += min;
            int i2 = i - min;
            i = i2;
            if (i2 != 0) {
                j += min;
                min = Math.min(i, this.capacity);
                require(min);
            } else {
                return;
            }
        }
    }
}
