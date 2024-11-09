package com.esotericsoftware.kryo.unsafe;

import com.esotericsoftware.kryo.io.ByteBufferOutput;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import sun.misc.Unsafe;
import sun.nio.ch.DirectBuffer;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/unsafe/UnsafeByteBufferOutput.class */
public class UnsafeByteBufferOutput extends ByteBufferOutput {
    private long bufferAddress;

    public UnsafeByteBufferOutput() {
    }

    public UnsafeByteBufferOutput(int i) {
        super(i);
        updateBufferAddress();
    }

    public UnsafeByteBufferOutput(int i, int i2) {
        super(i, i2);
        updateBufferAddress();
    }

    public UnsafeByteBufferOutput(OutputStream outputStream) {
        super(outputStream);
        updateBufferAddress();
    }

    public UnsafeByteBufferOutput(OutputStream outputStream, int i) {
        super(outputStream, i);
        updateBufferAddress();
    }

    public UnsafeByteBufferOutput(long j, int i) {
        super(UnsafeUtil.newDirectBuffer(j, i));
        updateBufferAddress();
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput
    public void setBuffer(ByteBuffer byteBuffer, int i) {
        if (!(byteBuffer instanceof DirectBuffer)) {
            throw new IllegalArgumentException("buffer must be direct.");
        }
        if (byteBuffer != this.byteBuffer) {
            UnsafeUtil.dispose(this.byteBuffer);
        }
        super.setBuffer(byteBuffer, i);
        updateBufferAddress();
    }

    private void updateBufferAddress() {
        this.bufferAddress = this.byteBuffer.address();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public boolean require(int i) {
        ByteBuffer byteBuffer = this.byteBuffer;
        boolean require = super.require(i);
        if (this.byteBuffer != byteBuffer) {
            UnsafeUtil.dispose(byteBuffer);
            updateBufferAddress();
        }
        return require;
    }

    public void dispose() {
        UnsafeUtil.dispose(this.byteBuffer);
        this.byteBuffer = null;
        this.bufferAddress = 0L;
    }

    private void setBufferPosition(Buffer buffer, int i) {
        buffer.position(i);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output, java.io.OutputStream
    public void write(int i) {
        if (this.position == this.capacity) {
            require(1);
        }
        Unsafe unsafe = UnsafeUtil.unsafe;
        long j = this.bufferAddress;
        int i2 = this.position;
        this.position = i2 + 1;
        unsafe.putByte(j + i2, (byte) i);
        setBufferPosition(this.byteBuffer, this.position);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public void writeByte(byte b2) {
        if (this.position == this.capacity) {
            require(1);
        }
        Unsafe unsafe = UnsafeUtil.unsafe;
        long j = this.bufferAddress;
        int i = this.position;
        this.position = i + 1;
        unsafe.putByte(j + i, b2);
        setBufferPosition(this.byteBuffer, this.position);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public void writeByte(int i) {
        if (this.position == this.capacity) {
            require(1);
        }
        Unsafe unsafe = UnsafeUtil.unsafe;
        long j = this.bufferAddress;
        int i2 = this.position;
        this.position = i2 + 1;
        unsafe.putByte(j + i2, (byte) i);
        setBufferPosition(this.byteBuffer, this.position);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public void writeInt(int i) {
        require(4);
        UnsafeUtil.unsafe.putInt(this.bufferAddress + this.position, i);
        this.position += 4;
        setBufferPosition(this.byteBuffer, this.position);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public void writeLong(long j) {
        require(8);
        UnsafeUtil.unsafe.putLong(this.bufferAddress + this.position, j);
        this.position += 8;
        setBufferPosition(this.byteBuffer, this.position);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public void writeFloat(float f) {
        require(4);
        UnsafeUtil.unsafe.putFloat(this.bufferAddress + this.position, f);
        this.position += 4;
        setBufferPosition(this.byteBuffer, this.position);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public void writeDouble(double d) {
        require(8);
        UnsafeUtil.unsafe.putDouble(this.bufferAddress + this.position, d);
        this.position += 8;
        setBufferPosition(this.byteBuffer, this.position);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public void writeShort(int i) {
        require(2);
        UnsafeUtil.unsafe.putShort(this.bufferAddress + this.position, (short) i);
        this.position += 2;
        setBufferPosition(this.byteBuffer, this.position);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public void writeChar(char c) {
        require(2);
        UnsafeUtil.unsafe.putChar(this.bufferAddress + this.position, c);
        this.position += 2;
        setBufferPosition(this.byteBuffer, this.position);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public void writeBoolean(boolean z) {
        if (this.position == this.capacity) {
            require(1);
        }
        Unsafe unsafe = UnsafeUtil.unsafe;
        long j = this.bufferAddress;
        int i = this.position;
        this.position = i + 1;
        unsafe.putByte(j + i, z ? (byte) 1 : (byte) 0);
        setBufferPosition(this.byteBuffer, this.position);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public void writeInts(int[] iArr, int i, int i2) {
        writeBytes(iArr, UnsafeUtil.intArrayBaseOffset, iArr.length << 2);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public void writeLongs(long[] jArr, int i, int i2) {
        writeBytes(jArr, UnsafeUtil.longArrayBaseOffset, jArr.length << 3);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public void writeFloats(float[] fArr, int i, int i2) {
        writeBytes(fArr, UnsafeUtil.floatArrayBaseOffset, fArr.length << 2);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public void writeDoubles(double[] dArr, int i, int i2) {
        writeBytes(dArr, UnsafeUtil.doubleArrayBaseOffset, dArr.length << 3);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public void writeShorts(short[] sArr, int i, int i2) {
        writeBytes(sArr, UnsafeUtil.shortArrayBaseOffset, sArr.length << 1);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public void writeChars(char[] cArr, int i, int i2) {
        writeBytes(cArr, UnsafeUtil.charArrayBaseOffset, cArr.length << 1);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public void writeBooleans(boolean[] zArr, int i, int i2) {
        writeBytes(zArr, UnsafeUtil.booleanArrayBaseOffset, zArr.length);
    }

    @Override // com.esotericsoftware.kryo.io.ByteBufferOutput, com.esotericsoftware.kryo.io.Output
    public void writeBytes(byte[] bArr, int i, int i2) {
        writeBytes(bArr, UnsafeUtil.byteArrayBaseOffset + i, i2);
    }

    public void writeBytes(Object obj, long j, int i) {
        int min = Math.min(this.capacity - this.position, i);
        while (true) {
            UnsafeUtil.unsafe.copyMemory(obj, j, (Object) null, this.bufferAddress + this.position, min);
            this.position += min;
            int i2 = i - min;
            i = i2;
            if (i2 != 0) {
                j += min;
                min = Math.min(this.capacity, i);
                require(min);
            } else {
                setBufferPosition(this.byteBuffer, this.position);
                return;
            }
        }
    }
}
