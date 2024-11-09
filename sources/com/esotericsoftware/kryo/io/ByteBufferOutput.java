package com.esotericsoftware.kryo.io;

import com.esotericsoftware.kryo.KryoException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/io/ByteBufferOutput.class */
public class ByteBufferOutput extends Output {
    private static final ByteOrder nativeOrder = ByteOrder.nativeOrder();
    protected ByteBuffer byteBuffer;

    public ByteBufferOutput() {
    }

    public ByteBufferOutput(int i) {
        this(i, i);
    }

    public ByteBufferOutput(int i, int i2) {
        if (i2 < -1) {
            throw new IllegalArgumentException("maxBufferSize cannot be < -1: " + i2);
        }
        this.capacity = i;
        this.maxCapacity = i2 == -1 ? 2147483639 : i2;
        this.byteBuffer = ByteBuffer.allocateDirect(i);
    }

    public ByteBufferOutput(ByteBuffer byteBuffer) {
        setBuffer(byteBuffer);
    }

    public ByteBufferOutput(ByteBuffer byteBuffer, int i) {
        setBuffer(byteBuffer, i);
    }

    public ByteBufferOutput(OutputStream outputStream) {
        this(4096, 4096);
        if (outputStream == null) {
            throw new IllegalArgumentException("outputStream cannot be null.");
        }
        this.outputStream = outputStream;
    }

    public ByteBufferOutput(OutputStream outputStream, int i) {
        this(i, i);
        if (outputStream == null) {
            throw new IllegalArgumentException("outputStream cannot be null.");
        }
        this.outputStream = outputStream;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public OutputStream getOutputStream() {
        return this.outputStream;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public byte[] getBuffer() {
        throw new UnsupportedOperationException("This buffer does not used a byte[], see #getByteBuffer().");
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void setBuffer(byte[] bArr) {
        throw new UnsupportedOperationException("This buffer does not used a byte[], see #setByteBuffer(ByteBuffer).");
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void setBuffer(byte[] bArr, int i) {
        throw new UnsupportedOperationException("This buffer does not used a byte[], see #setByteBuffer(ByteBuffer).");
    }

    public void setBuffer(byte[] bArr, int i, int i2) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(bArr.length);
        allocateDirect.put(bArr, i, i2);
        setBufferPosition(allocateDirect, 0);
        setBufferLimit(allocateDirect, bArr.length);
        setBuffer(allocateDirect);
    }

    public void setBuffer(ByteBuffer byteBuffer) {
        setBuffer(byteBuffer, byteBuffer.capacity());
    }

    public void setBuffer(ByteBuffer byteBuffer, int i) {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("buffer cannot be null.");
        }
        if (i < -1) {
            throw new IllegalArgumentException("maxBufferSize cannot be < -1: " + i);
        }
        this.byteBuffer = byteBuffer;
        this.maxCapacity = i == -1 ? 2147483639 : i;
        this.capacity = byteBuffer.capacity();
        this.position = byteBuffer.position();
        this.total = 0L;
        this.outputStream = null;
    }

    public ByteBuffer getByteBuffer() {
        return this.byteBuffer;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public byte[] toBytes() {
        byte[] bArr = new byte[this.position];
        setBufferPosition(this.byteBuffer, 0);
        this.byteBuffer.get(bArr, 0, this.position);
        return bArr;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void setPosition(int i) {
        this.position = i;
        setBufferPosition(this.byteBuffer, i);
    }

    @Override // com.esotericsoftware.kryo.io.Output, com.esotericsoftware.kryo.util.Pool.Poolable
    public void reset() {
        super.reset();
        setBufferPosition(this.byteBuffer, 0);
    }

    private int getBufferPosition(Buffer buffer) {
        return buffer.position();
    }

    private void setBufferPosition(Buffer buffer, int i) {
        buffer.position(i);
    }

    private void setBufferLimit(Buffer buffer, int i) {
        buffer.limit(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.esotericsoftware.kryo.io.Output
    public boolean require(int i) {
        if (this.capacity - this.position >= i) {
            return false;
        }
        flush();
        if (this.capacity - this.position >= i) {
            return true;
        }
        if (i > this.maxCapacity - this.position) {
            if (i > this.maxCapacity) {
                throw new KryoBufferOverflowException("Buffer overflow. Max capacity: " + this.maxCapacity + ", required: " + i);
            }
            throw new KryoBufferOverflowException("Buffer overflow. Available: " + (this.maxCapacity - this.position) + ", required: " + i);
        }
        if (this.capacity == 0) {
            this.capacity = 16;
        }
        do {
            this.capacity = Math.min(this.capacity << 1, this.maxCapacity);
        } while (this.capacity - this.position < i);
        ByteBuffer allocate = !this.byteBuffer.isDirect() ? ByteBuffer.allocate(this.capacity) : ByteBuffer.allocateDirect(this.capacity);
        setBufferPosition(this.byteBuffer, 0);
        setBufferLimit(this.byteBuffer, this.position);
        allocate.put(this.byteBuffer);
        allocate.order(this.byteBuffer.order());
        this.byteBuffer = allocate;
        return true;
    }

    @Override // com.esotericsoftware.kryo.io.Output, java.io.OutputStream, java.io.Flushable
    public void flush() {
        if (this.outputStream == null) {
            return;
        }
        try {
            byte[] bArr = new byte[this.position];
            setBufferPosition(this.byteBuffer, 0);
            this.byteBuffer.get(bArr);
            setBufferPosition(this.byteBuffer, 0);
            this.outputStream.write(bArr, 0, this.position);
            this.total += this.position;
            this.position = 0;
        } catch (IOException e) {
            throw new KryoException(e);
        }
    }

    @Override // com.esotericsoftware.kryo.io.Output, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        flush();
        if (this.outputStream != null) {
            try {
                this.outputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    @Override // com.esotericsoftware.kryo.io.Output, java.io.OutputStream
    public void write(int i) {
        if (this.position == this.capacity) {
            require(1);
        }
        this.byteBuffer.put((byte) i);
        this.position++;
    }

    @Override // com.esotericsoftware.kryo.io.Output, java.io.OutputStream
    public void write(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
        writeBytes(bArr, 0, bArr.length);
    }

    @Override // com.esotericsoftware.kryo.io.Output, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        writeBytes(bArr, i, i2);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeByte(byte b2) {
        if (this.position == this.capacity) {
            require(1);
        }
        this.byteBuffer.put(b2);
        this.position++;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeByte(int i) {
        if (this.position == this.capacity) {
            require(1);
        }
        this.byteBuffer.put((byte) i);
        this.position++;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeBytes(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
        writeBytes(bArr, 0, bArr.length);
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeBytes(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
        int min = Math.min(this.capacity - this.position, i2);
        while (true) {
            this.byteBuffer.put(bArr, i, min);
            this.position += min;
            int i3 = i2 - min;
            i2 = i3;
            if (i3 == 0) {
                return;
            }
            i += min;
            min = Math.min(this.capacity, i2);
            require(min);
        }
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeInt(int i) {
        require(4);
        this.position += 4;
        ByteBuffer byteBuffer = this.byteBuffer;
        byteBuffer.put((byte) i);
        byteBuffer.put((byte) (i >> 8));
        byteBuffer.put((byte) (i >> 16));
        byteBuffer.put((byte) (i >> 24));
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public int writeVarInt(int i, boolean z) {
        if (!z) {
            i = (i << 1) ^ (i >> 31);
        }
        if ((i >>> 7) == 0) {
            if (this.position == this.capacity) {
                require(1);
            }
            this.position++;
            this.byteBuffer.put((byte) i);
            return 1;
        }
        if ((i >>> 14) == 0) {
            require(2);
            this.position += 2;
            this.byteBuffer.put((byte) ((i & 127) | 128));
            this.byteBuffer.put((byte) (i >>> 7));
            return 2;
        }
        if ((i >>> 21) == 0) {
            require(3);
            this.position += 3;
            ByteBuffer byteBuffer = this.byteBuffer;
            byteBuffer.put((byte) ((i & 127) | 128));
            byteBuffer.put((byte) ((i >>> 7) | 128));
            byteBuffer.put((byte) (i >>> 14));
            return 3;
        }
        if ((i >>> 28) == 0) {
            require(4);
            this.position += 4;
            ByteBuffer byteBuffer2 = this.byteBuffer;
            byteBuffer2.put((byte) ((i & 127) | 128));
            byteBuffer2.put((byte) ((i >>> 7) | 128));
            byteBuffer2.put((byte) ((i >>> 14) | 128));
            byteBuffer2.put((byte) (i >>> 21));
            return 4;
        }
        require(5);
        this.position += 5;
        ByteBuffer byteBuffer3 = this.byteBuffer;
        byteBuffer3.put((byte) ((i & 127) | 128));
        byteBuffer3.put((byte) ((i >>> 7) | 128));
        byteBuffer3.put((byte) ((i >>> 14) | 128));
        byteBuffer3.put((byte) ((i >>> 21) | 128));
        byteBuffer3.put((byte) (i >>> 28));
        return 5;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public int writeVarIntFlag(boolean z, int i, boolean z2) {
        if (!z2) {
            i = (i << 1) ^ (i >> 31);
        }
        int i2 = (i & 63) | (z ? 128 : 0);
        if ((i >>> 6) == 0) {
            if (this.position == this.capacity) {
                require(1);
            }
            this.byteBuffer.put((byte) i2);
            this.position++;
            return 1;
        }
        if ((i >>> 13) == 0) {
            require(2);
            this.position += 2;
            this.byteBuffer.put((byte) (i2 | 64));
            this.byteBuffer.put((byte) (i >>> 6));
            return 2;
        }
        if ((i >>> 20) == 0) {
            require(3);
            this.position += 3;
            ByteBuffer byteBuffer = this.byteBuffer;
            byteBuffer.put((byte) (i2 | 64));
            byteBuffer.put((byte) ((i >>> 6) | 128));
            byteBuffer.put((byte) (i >>> 13));
            return 3;
        }
        if ((i >>> 27) == 0) {
            require(4);
            this.position += 4;
            ByteBuffer byteBuffer2 = this.byteBuffer;
            byteBuffer2.put((byte) (i2 | 64));
            byteBuffer2.put((byte) ((i >>> 6) | 128));
            byteBuffer2.put((byte) ((i >>> 13) | 128));
            byteBuffer2.put((byte) (i >>> 20));
            return 4;
        }
        require(5);
        this.position += 5;
        ByteBuffer byteBuffer3 = this.byteBuffer;
        byteBuffer3.put((byte) (i2 | 64));
        byteBuffer3.put((byte) ((i >>> 6) | 128));
        byteBuffer3.put((byte) ((i >>> 13) | 128));
        byteBuffer3.put((byte) ((i >>> 20) | 128));
        byteBuffer3.put((byte) (i >>> 27));
        return 5;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeLong(long j) {
        require(8);
        this.position += 8;
        ByteBuffer byteBuffer = this.byteBuffer;
        byteBuffer.put((byte) j);
        byteBuffer.put((byte) (j >>> 8));
        byteBuffer.put((byte) (j >>> 16));
        byteBuffer.put((byte) (j >>> 24));
        byteBuffer.put((byte) (j >>> 32));
        byteBuffer.put((byte) (j >>> 40));
        byteBuffer.put((byte) (j >>> 48));
        byteBuffer.put((byte) (j >>> 56));
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public int writeVarLong(long j, boolean z) {
        if (!z) {
            j = (j << 1) ^ (j >> 63);
        }
        if ((j >>> 7) == 0) {
            if (this.position == this.capacity) {
                require(1);
            }
            this.position++;
            this.byteBuffer.put((byte) j);
            return 1;
        }
        if ((j >>> 14) == 0) {
            require(2);
            this.position += 2;
            this.byteBuffer.put((byte) ((j & 127) | 128));
            this.byteBuffer.put((byte) (j >>> 7));
            return 2;
        }
        if ((j >>> 21) == 0) {
            require(3);
            this.position += 3;
            ByteBuffer byteBuffer = this.byteBuffer;
            byteBuffer.put((byte) ((j & 127) | 128));
            byteBuffer.put((byte) ((j >>> 7) | 128));
            byteBuffer.put((byte) (j >>> 14));
            return 3;
        }
        if ((j >>> 28) == 0) {
            require(4);
            this.position += 4;
            ByteBuffer byteBuffer2 = this.byteBuffer;
            byteBuffer2.put((byte) ((j & 127) | 128));
            byteBuffer2.put((byte) ((j >>> 7) | 128));
            byteBuffer2.put((byte) ((j >>> 14) | 128));
            byteBuffer2.put((byte) (j >>> 21));
            return 4;
        }
        if ((j >>> 35) == 0) {
            require(5);
            this.position += 5;
            ByteBuffer byteBuffer3 = this.byteBuffer;
            byteBuffer3.put((byte) ((j & 127) | 128));
            byteBuffer3.put((byte) ((j >>> 7) | 128));
            byteBuffer3.put((byte) ((j >>> 14) | 128));
            byteBuffer3.put((byte) ((j >>> 21) | 128));
            byteBuffer3.put((byte) (j >>> 28));
            return 5;
        }
        if ((j >>> 42) == 0) {
            require(6);
            this.position += 6;
            ByteBuffer byteBuffer4 = this.byteBuffer;
            byteBuffer4.put((byte) ((j & 127) | 128));
            byteBuffer4.put((byte) ((j >>> 7) | 128));
            byteBuffer4.put((byte) ((j >>> 14) | 128));
            byteBuffer4.put((byte) ((j >>> 21) | 128));
            byteBuffer4.put((byte) ((j >>> 28) | 128));
            byteBuffer4.put((byte) (j >>> 35));
            return 6;
        }
        if ((j >>> 49) == 0) {
            require(7);
            this.position += 7;
            ByteBuffer byteBuffer5 = this.byteBuffer;
            byteBuffer5.put((byte) ((j & 127) | 128));
            byteBuffer5.put((byte) ((j >>> 7) | 128));
            byteBuffer5.put((byte) ((j >>> 14) | 128));
            byteBuffer5.put((byte) ((j >>> 21) | 128));
            byteBuffer5.put((byte) ((j >>> 28) | 128));
            byteBuffer5.put((byte) ((j >>> 35) | 128));
            byteBuffer5.put((byte) (j >>> 42));
            return 7;
        }
        if ((j >>> 56) == 0) {
            require(8);
            this.position += 8;
            ByteBuffer byteBuffer6 = this.byteBuffer;
            byteBuffer6.put((byte) ((j & 127) | 128));
            byteBuffer6.put((byte) ((j >>> 7) | 128));
            byteBuffer6.put((byte) ((j >>> 14) | 128));
            byteBuffer6.put((byte) ((j >>> 21) | 128));
            byteBuffer6.put((byte) ((j >>> 28) | 128));
            byteBuffer6.put((byte) ((j >>> 35) | 128));
            byteBuffer6.put((byte) ((j >>> 42) | 128));
            byteBuffer6.put((byte) (j >>> 49));
            return 8;
        }
        require(9);
        this.position += 9;
        ByteBuffer byteBuffer7 = this.byteBuffer;
        byteBuffer7.put((byte) ((j & 127) | 128));
        byteBuffer7.put((byte) ((j >>> 7) | 128));
        byteBuffer7.put((byte) ((j >>> 14) | 128));
        byteBuffer7.put((byte) ((j >>> 21) | 128));
        byteBuffer7.put((byte) ((j >>> 28) | 128));
        byteBuffer7.put((byte) ((j >>> 35) | 128));
        byteBuffer7.put((byte) ((j >>> 42) | 128));
        byteBuffer7.put((byte) ((j >>> 49) | 128));
        byteBuffer7.put((byte) (j >>> 56));
        return 9;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeFloat(float f) {
        require(4);
        ByteBuffer byteBuffer = this.byteBuffer;
        this.position += 4;
        int floatToIntBits = Float.floatToIntBits(f);
        byteBuffer.put((byte) floatToIntBits);
        byteBuffer.put((byte) (floatToIntBits >> 8));
        byteBuffer.put((byte) (floatToIntBits >> 16));
        byteBuffer.put((byte) (floatToIntBits >> 24));
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeDouble(double d) {
        require(8);
        this.position += 8;
        ByteBuffer byteBuffer = this.byteBuffer;
        byteBuffer.put((byte) Double.doubleToLongBits(d));
        byteBuffer.put((byte) (r0 >>> 8));
        byteBuffer.put((byte) (r0 >>> 16));
        byteBuffer.put((byte) (r0 >>> 24));
        byteBuffer.put((byte) (r0 >>> 32));
        byteBuffer.put((byte) (r0 >>> 40));
        byteBuffer.put((byte) (r0 >>> 48));
        byteBuffer.put((byte) (r0 >>> 56));
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeShort(int i) {
        require(2);
        this.position += 2;
        this.byteBuffer.put((byte) i);
        this.byteBuffer.put((byte) (i >>> 8));
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeChar(char c) {
        require(2);
        this.position += 2;
        this.byteBuffer.put((byte) c);
        this.byteBuffer.put((byte) (c >>> '\b'));
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeBoolean(boolean z) {
        if (this.position == this.capacity) {
            require(1);
        }
        this.byteBuffer.put((byte) (z ? 1 : 0));
        this.position++;
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeString(String str) {
        if (str == null) {
            writeByte(128);
            return;
        }
        int length = str.length();
        if (length == 0) {
            writeByte(129);
            return;
        }
        if (length > 1 && length <= 32) {
            for (int i = 0; i < length; i++) {
                if (str.charAt(i) <= 127) {
                }
            }
            if (this.capacity - this.position < length) {
                writeAscii_slow(str, length);
            } else {
                int length2 = str.length();
                for (int i2 = 0; i2 < length2; i2++) {
                    this.byteBuffer.put((byte) str.charAt(i2));
                }
                this.position += length;
            }
            this.byteBuffer.put(this.position - 1, (byte) (this.byteBuffer.get(this.position - 1) | 128));
            return;
        }
        writeVarIntFlag(true, length + 1, true);
        int i3 = 0;
        if (this.capacity - this.position >= length) {
            ByteBuffer byteBuffer = this.byteBuffer;
            do {
                char charAt = str.charAt(i3);
                if (charAt <= 127) {
                    byteBuffer.put((byte) charAt);
                    i3++;
                } else {
                    this.position = getBufferPosition(byteBuffer);
                }
            } while (i3 != length);
            this.position = getBufferPosition(byteBuffer);
            return;
        }
        if (i3 < length) {
            writeUtf8_slow(str, length, i3);
        }
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeAscii(String str) {
        if (str == null) {
            writeByte(128);
            return;
        }
        int length = str.length();
        switch (length) {
            case 0:
                writeByte(129);
                return;
            case 1:
                require(2);
                this.byteBuffer.put((byte) -126);
                this.byteBuffer.put((byte) str.charAt(0));
                this.position += 2;
                return;
            default:
                if (this.capacity - this.position < length) {
                    writeAscii_slow(str, length);
                } else {
                    ByteBuffer byteBuffer = this.byteBuffer;
                    int length2 = str.length();
                    for (int i = 0; i < length2; i++) {
                        byteBuffer.put((byte) str.charAt(i));
                    }
                    this.position += length;
                }
                this.byteBuffer.put(this.position - 1, (byte) (this.byteBuffer.get(this.position - 1) | 128));
                return;
        }
    }

    private void writeUtf8_slow(String str, int i, int i2) {
        while (i2 < i) {
            if (this.position == this.capacity) {
                require(Math.min(this.capacity, i - i2));
            }
            this.position++;
            char charAt = str.charAt(i2);
            if (charAt <= 127) {
                this.byteBuffer.put((byte) charAt);
            } else if (charAt > 2047) {
                this.byteBuffer.put((byte) (224 | ((charAt >> '\f') & 15)));
                require(2);
                this.position += 2;
                this.byteBuffer.put((byte) (128 | ((charAt >> 6) & 63)));
                this.byteBuffer.put((byte) (128 | (charAt & '?')));
            } else {
                this.byteBuffer.put((byte) (192 | ((charAt >> 6) & 31)));
                if (this.position == this.capacity) {
                    require(1);
                }
                this.position++;
                this.byteBuffer.put((byte) (128 | (charAt & '?')));
            }
            i2++;
        }
    }

    private void writeAscii_slow(String str, int i) {
        ByteBuffer byteBuffer = this.byteBuffer;
        int i2 = 0;
        int min = Math.min(i, this.capacity - this.position);
        while (i2 < i) {
            byte[] bArr = new byte[i];
            int i3 = i2;
            str.getBytes(i3, i3 + min, bArr, 0);
            byteBuffer.put(bArr, 0, min);
            i2 += min;
            this.position += min;
            min = Math.min(i - i2, this.capacity);
            if (require(min)) {
                byteBuffer = this.byteBuffer;
            }
        }
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeInts(int[] iArr, int i, int i2) {
        if (this.capacity >= (i2 << 2)) {
            require(i2 << 2);
            ByteBuffer byteBuffer = this.byteBuffer;
            int i3 = i + i2;
            while (i < i3) {
                int i4 = iArr[i];
                byteBuffer.put((byte) i4);
                byteBuffer.put((byte) (i4 >> 8));
                byteBuffer.put((byte) (i4 >> 16));
                byteBuffer.put((byte) (i4 >> 24));
                i++;
            }
            this.position = getBufferPosition(byteBuffer);
            return;
        }
        int i5 = i + i2;
        while (i < i5) {
            writeInt(iArr[i]);
            i++;
        }
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeLongs(long[] jArr, int i, int i2) {
        if (this.capacity >= (i2 << 3)) {
            require(i2 << 3);
            ByteBuffer byteBuffer = this.byteBuffer;
            int i3 = i + i2;
            while (i < i3) {
                byteBuffer.put((byte) jArr[i]);
                byteBuffer.put((byte) (r0 >>> 8));
                byteBuffer.put((byte) (r0 >>> 16));
                byteBuffer.put((byte) (r0 >>> 24));
                byteBuffer.put((byte) (r0 >>> 32));
                byteBuffer.put((byte) (r0 >>> 40));
                byteBuffer.put((byte) (r0 >>> 48));
                byteBuffer.put((byte) (r0 >>> 56));
                i++;
            }
            this.position = getBufferPosition(byteBuffer);
            return;
        }
        int i4 = i + i2;
        while (i < i4) {
            writeLong(jArr[i]);
            i++;
        }
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeFloats(float[] fArr, int i, int i2) {
        if (this.capacity >= (i2 << 2)) {
            require(i2 << 2);
            ByteBuffer byteBuffer = this.byteBuffer;
            int i3 = i + i2;
            while (i < i3) {
                int floatToIntBits = Float.floatToIntBits(fArr[i]);
                byteBuffer.put((byte) floatToIntBits);
                byteBuffer.put((byte) (floatToIntBits >> 8));
                byteBuffer.put((byte) (floatToIntBits >> 16));
                byteBuffer.put((byte) (floatToIntBits >> 24));
                i++;
            }
            this.position = getBufferPosition(byteBuffer);
            return;
        }
        int i4 = i + i2;
        while (i < i4) {
            writeFloat(fArr[i]);
            i++;
        }
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeDoubles(double[] dArr, int i, int i2) {
        if (this.capacity >= (i2 << 3)) {
            require(i2 << 3);
            ByteBuffer byteBuffer = this.byteBuffer;
            int i3 = i + i2;
            while (i < i3) {
                byteBuffer.put((byte) Double.doubleToLongBits(dArr[i]));
                byteBuffer.put((byte) (r0 >>> 8));
                byteBuffer.put((byte) (r0 >>> 16));
                byteBuffer.put((byte) (r0 >>> 24));
                byteBuffer.put((byte) (r0 >>> 32));
                byteBuffer.put((byte) (r0 >>> 40));
                byteBuffer.put((byte) (r0 >>> 48));
                byteBuffer.put((byte) (r0 >>> 56));
                i++;
            }
            this.position = getBufferPosition(byteBuffer);
            return;
        }
        int i4 = i + i2;
        while (i < i4) {
            writeDouble(dArr[i]);
            i++;
        }
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeShorts(short[] sArr, int i, int i2) {
        if (this.capacity >= (i2 << 1)) {
            require(i2 << 1);
            int i3 = i + i2;
            while (i < i3) {
                short s = sArr[i];
                this.byteBuffer.put((byte) s);
                this.byteBuffer.put((byte) (s >>> 8));
                i++;
            }
            this.position = getBufferPosition(this.byteBuffer);
            return;
        }
        int i4 = i + i2;
        while (i < i4) {
            writeShort(sArr[i]);
            i++;
        }
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeChars(char[] cArr, int i, int i2) {
        if (this.capacity >= (i2 << 1)) {
            require(i2 << 1);
            int i3 = i + i2;
            while (i < i3) {
                char c = cArr[i];
                this.byteBuffer.put((byte) c);
                this.byteBuffer.put((byte) (c >>> '\b'));
                i++;
            }
            this.position = getBufferPosition(this.byteBuffer);
            return;
        }
        int i4 = i + i2;
        while (i < i4) {
            writeChar(cArr[i]);
            i++;
        }
    }

    @Override // com.esotericsoftware.kryo.io.Output
    public void writeBooleans(boolean[] zArr, int i, int i2) {
        if (this.capacity >= i2) {
            require(i2);
            int i3 = i + i2;
            while (i < i3) {
                this.byteBuffer.put(zArr[i] ? (byte) 1 : (byte) 0);
                i++;
            }
            this.position = getBufferPosition(this.byteBuffer);
            return;
        }
        int i4 = i + i2;
        while (i < i4) {
            writeBoolean(zArr[i]);
            i++;
        }
    }
}
