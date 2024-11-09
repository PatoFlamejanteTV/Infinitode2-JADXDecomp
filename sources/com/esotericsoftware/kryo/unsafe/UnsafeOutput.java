package com.esotericsoftware.kryo.unsafe;

import com.esotericsoftware.kryo.io.Output;
import java.io.OutputStream;
import sun.misc.Unsafe;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/unsafe/UnsafeOutput.class */
public class UnsafeOutput extends Output {
    public UnsafeOutput() {
    }

    public UnsafeOutput(int i) {
        this(i, i);
    }

    public UnsafeOutput(int i, int i2) {
        super(i, i2);
    }

    public UnsafeOutput(byte[] bArr) {
        this(bArr, bArr.length);
    }

    public UnsafeOutput(byte[] bArr, int i) {
        super(bArr, i);
    }

    public UnsafeOutput(OutputStream outputStream) {
        super(outputStream);
    }

    public UnsafeOutput(OutputStream outputStream, int i) {
        super(outputStream, i);
    }

    @Override // com.esotericsoftware.kryo.io.Output, java.io.OutputStream
    public void write(int i) {
        if (this.position == this.capacity) {
            require(1);
        }
        Unsafe unsafe = UnsafeUtil.unsafe;
        byte[] bArr = this.buffer;
        long j = UnsafeUtil.byteArrayBaseOffset;
        int i2 = this.position;
        this.position = i2 + 1;
        unsafe.putByte(bArr, j + i2, (byte) i);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeByte(byte b2) {
        if (this.position == this.capacity) {
            require(1);
        }
        Unsafe unsafe = UnsafeUtil.unsafe;
        byte[] bArr = this.buffer;
        long j = UnsafeUtil.byteArrayBaseOffset;
        int i = this.position;
        this.position = i + 1;
        unsafe.putByte(bArr, j + i, b2);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeByte(int i) {
        if (this.position == this.capacity) {
            require(1);
        }
        Unsafe unsafe = UnsafeUtil.unsafe;
        byte[] bArr = this.buffer;
        long j = UnsafeUtil.byteArrayBaseOffset;
        int i2 = this.position;
        this.position = i2 + 1;
        unsafe.putByte(bArr, j + i2, (byte) i);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeInt(int i) {
        require(4);
        UnsafeUtil.unsafe.putInt(this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position, i);
        this.position += 4;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeLong(long j) {
        require(8);
        UnsafeUtil.unsafe.putLong(this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position, j);
        this.position += 8;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeFloat(float f) {
        require(4);
        UnsafeUtil.unsafe.putFloat(this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position, f);
        this.position += 4;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeDouble(double d) {
        require(8);
        UnsafeUtil.unsafe.putDouble(this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position, d);
        this.position += 8;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeShort(int i) {
        require(2);
        UnsafeUtil.unsafe.putShort(this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position, (short) i);
        this.position += 2;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeChar(char c) {
        require(2);
        UnsafeUtil.unsafe.putChar(this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position, c);
        this.position += 2;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeBoolean(boolean z) {
        if (this.position == this.capacity) {
            require(1);
        }
        Unsafe unsafe = UnsafeUtil.unsafe;
        byte[] bArr = this.buffer;
        long j = UnsafeUtil.byteArrayBaseOffset;
        int i = this.position;
        this.position = i + 1;
        unsafe.putByte(bArr, j + i, z ? (byte) 1 : (byte) 0);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeInts(int[] iArr, int i, int i2) {
        writeBytes(iArr, UnsafeUtil.intArrayBaseOffset, iArr.length << 2);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeLongs(long[] jArr, int i, int i2) {
        writeBytes(jArr, UnsafeUtil.longArrayBaseOffset, jArr.length << 3);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeFloats(float[] fArr, int i, int i2) {
        writeBytes(fArr, UnsafeUtil.floatArrayBaseOffset, fArr.length << 2);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeDoubles(double[] dArr, int i, int i2) {
        writeBytes(dArr, UnsafeUtil.doubleArrayBaseOffset, dArr.length << 3);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeShorts(short[] sArr, int i, int i2) {
        writeBytes(sArr, UnsafeUtil.shortArrayBaseOffset, sArr.length << 1);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeChars(char[] cArr, int i, int i2) {
        writeBytes(cArr, UnsafeUtil.charArrayBaseOffset, cArr.length << 1);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeBooleans(boolean[] zArr, int i, int i2) {
        writeBytes(zArr, UnsafeUtil.booleanArrayBaseOffset, zArr.length);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeBytes(byte[] bArr, int i, int i2) {
        writeBytes(bArr, UnsafeUtil.byteArrayBaseOffset + i, i2);
    }

    public void writeBytes(Object obj, long j, int i) {
        int min = Math.min(this.capacity - this.position, i);
        while (true) {
            UnsafeUtil.unsafe.copyMemory(obj, j, this.buffer, UnsafeUtil.byteArrayBaseOffset + this.position, min);
            this.position += min;
            int i2 = i - min;
            i = i2;
            if (i2 != 0) {
                j += min;
                min = Math.min(this.capacity, i);
                require(min);
            } else {
                return;
            }
        }
    }
}
