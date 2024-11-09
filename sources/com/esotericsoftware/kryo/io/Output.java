package com.esotericsoftware.kryo.io;

import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.util.Pool;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/io/Output.class */
public class Output extends OutputStream implements Pool.Poolable, AutoCloseable {
    protected int maxCapacity;
    protected long total;
    protected int position;
    protected int capacity;
    protected byte[] buffer;
    protected OutputStream outputStream;
    protected boolean varEncoding;

    public Output() {
        this.varEncoding = true;
    }

    public Output(int i) {
        this(i, i);
    }

    public Output(int i, int i2) {
        this.varEncoding = true;
        if (i > i2 && i2 != -1) {
            throw new IllegalArgumentException("bufferSize: " + i + " cannot be greater than maxBufferSize: " + i2);
        }
        if (i2 < -1) {
            throw new IllegalArgumentException("maxBufferSize cannot be < -1: " + i2);
        }
        this.capacity = i;
        this.maxCapacity = i2 == -1 ? 2147483639 : i2;
        this.buffer = new byte[i];
    }

    public Output(byte[] bArr) {
        this(bArr, bArr.length);
    }

    public Output(byte[] bArr, int i) {
        this.varEncoding = true;
        if (bArr == null) {
            throw new IllegalArgumentException("buffer cannot be null.");
        }
        setBuffer(bArr, i);
    }

    public Output(OutputStream outputStream) {
        this(4096, 4096);
        if (outputStream == null) {
            throw new IllegalArgumentException("outputStream cannot be null.");
        }
        this.outputStream = outputStream;
    }

    public Output(OutputStream outputStream, int i) {
        this(i, i);
        if (outputStream == null) {
            throw new IllegalArgumentException("outputStream cannot be null.");
        }
        this.outputStream = outputStream;
    }

    public OutputStream getOutputStream() {
        return this.outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
        reset();
    }

    public void setBuffer(byte[] bArr) {
        setBuffer(bArr, bArr.length);
    }

    public void setBuffer(byte[] bArr, int i) {
        if (bArr == null) {
            throw new IllegalArgumentException("buffer cannot be null.");
        }
        if (bArr.length > i && i != -1) {
            throw new IllegalArgumentException("buffer has length: " + bArr.length + " cannot be greater than maxBufferSize: " + i);
        }
        if (i < -1) {
            throw new IllegalArgumentException("maxBufferSize cannot be < -1: " + i);
        }
        this.buffer = bArr;
        this.maxCapacity = i == -1 ? 2147483639 : i;
        this.capacity = bArr.length;
        this.position = 0;
        this.total = 0L;
        this.outputStream = null;
    }

    public byte[] getBuffer() {
        return this.buffer;
    }

    public byte[] toBytes() {
        byte[] bArr = new byte[this.position];
        System.arraycopy(this.buffer, 0, bArr, 0, this.position);
        return bArr;
    }

    public boolean getVariableLengthEncoding() {
        return this.varEncoding;
    }

    public void setVariableLengthEncoding(boolean z) {
        this.varEncoding = z;
    }

    public int position() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public long total() {
        return this.total + this.position;
    }

    public int getMaxCapacity() {
        return this.maxCapacity;
    }

    public void reset() {
        this.position = 0;
        this.total = 0L;
    }

    /* JADX INFO: Access modifiers changed from: protected */
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
        byte[] bArr = new byte[this.capacity];
        System.arraycopy(this.buffer, 0, bArr, 0, this.position);
        this.buffer = bArr;
        return true;
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        if (this.outputStream == null) {
            return;
        }
        try {
            this.outputStream.write(this.buffer, 0, this.position);
            this.outputStream.flush();
            this.total += this.position;
            this.position = 0;
        } catch (IOException e) {
            throw new KryoException(e);
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        flush();
        if (this.outputStream != null) {
            try {
                this.outputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        if (this.position == this.capacity) {
            require(1);
        }
        byte[] bArr = this.buffer;
        int i2 = this.position;
        this.position = i2 + 1;
        bArr[i2] = (byte) i;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
        writeBytes(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        writeBytes(bArr, i, i2);
    }

    public void writeByte(byte b2) {
        if (this.position == this.capacity) {
            require(1);
        }
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = b2;
    }

    public void writeByte(int i) {
        if (this.position == this.capacity) {
            require(1);
        }
        byte[] bArr = this.buffer;
        int i2 = this.position;
        this.position = i2 + 1;
        bArr[i2] = (byte) i;
    }

    public void writeBytes(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
        writeBytes(bArr, 0, bArr.length);
    }

    public void writeBytes(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
        int min = Math.min(this.capacity - this.position, i2);
        while (true) {
            System.arraycopy(bArr, i, this.buffer, this.position, min);
            this.position += min;
            int i3 = i2 - min;
            i2 = i3;
            if (i3 == 0) {
                return;
            }
            i += min;
            min = Math.min(Math.max(this.capacity, 1), i2);
            require(min);
        }
    }

    public void writeInt(int i) {
        require(4);
        byte[] bArr = this.buffer;
        int i2 = this.position;
        this.position = i2 + 4;
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >> 8);
        bArr[i2 + 2] = (byte) (i >> 16);
        bArr[i2 + 3] = (byte) (i >> 24);
    }

    public int writeInt(int i, boolean z) {
        if (this.varEncoding) {
            return writeVarInt(i, z);
        }
        writeInt(i);
        return 4;
    }

    public int writeVarInt(int i, boolean z) {
        if (!z) {
            i = (i << 1) ^ (i >> 31);
        }
        if ((i >>> 7) == 0) {
            if (this.position == this.capacity) {
                require(1);
            }
            byte[] bArr = this.buffer;
            int i2 = this.position;
            this.position = i2 + 1;
            bArr[i2] = (byte) i;
            return 1;
        }
        if ((i >>> 14) == 0) {
            require(2);
            int i3 = this.position;
            this.position = i3 + 2;
            this.buffer[i3] = (byte) ((i & 127) | 128);
            this.buffer[i3 + 1] = (byte) (i >>> 7);
            return 2;
        }
        if ((i >>> 21) == 0) {
            require(3);
            int i4 = this.position;
            this.position = i4 + 3;
            byte[] bArr2 = this.buffer;
            bArr2[i4] = (byte) ((i & 127) | 128);
            bArr2[i4 + 1] = (byte) ((i >>> 7) | 128);
            bArr2[i4 + 2] = (byte) (i >>> 14);
            return 3;
        }
        if ((i >>> 28) == 0) {
            require(4);
            int i5 = this.position;
            this.position = i5 + 4;
            byte[] bArr3 = this.buffer;
            bArr3[i5] = (byte) ((i & 127) | 128);
            bArr3[i5 + 1] = (byte) ((i >>> 7) | 128);
            bArr3[i5 + 2] = (byte) ((i >>> 14) | 128);
            bArr3[i5 + 3] = (byte) (i >>> 21);
            return 4;
        }
        require(5);
        int i6 = this.position;
        this.position = i6 + 5;
        byte[] bArr4 = this.buffer;
        bArr4[i6] = (byte) ((i & 127) | 128);
        bArr4[i6 + 1] = (byte) ((i >>> 7) | 128);
        bArr4[i6 + 2] = (byte) ((i >>> 14) | 128);
        bArr4[i6 + 3] = (byte) ((i >>> 21) | 128);
        bArr4[i6 + 4] = (byte) (i >>> 28);
        return 5;
    }

    public int writeVarIntFlag(boolean z, int i, boolean z2) {
        if (!z2) {
            i = (i << 1) ^ (i >> 31);
        }
        int i2 = (i & 63) | (z ? 128 : 0);
        if ((i >>> 6) == 0) {
            if (this.position == this.capacity) {
                require(1);
            }
            byte[] bArr = this.buffer;
            int i3 = this.position;
            this.position = i3 + 1;
            bArr[i3] = (byte) i2;
            return 1;
        }
        if ((i >>> 13) == 0) {
            require(2);
            int i4 = this.position;
            this.position = i4 + 2;
            this.buffer[i4] = (byte) (i2 | 64);
            this.buffer[i4 + 1] = (byte) (i >>> 6);
            return 2;
        }
        if ((i >>> 20) == 0) {
            require(3);
            byte[] bArr2 = this.buffer;
            int i5 = this.position;
            this.position = i5 + 3;
            bArr2[i5] = (byte) (i2 | 64);
            bArr2[i5 + 1] = (byte) ((i >>> 6) | 128);
            bArr2[i5 + 2] = (byte) (i >>> 13);
            return 3;
        }
        if ((i >>> 27) == 0) {
            require(4);
            byte[] bArr3 = this.buffer;
            int i6 = this.position;
            this.position = i6 + 4;
            bArr3[i6] = (byte) (i2 | 64);
            bArr3[i6 + 1] = (byte) ((i >>> 6) | 128);
            bArr3[i6 + 2] = (byte) ((i >>> 13) | 128);
            bArr3[i6 + 3] = (byte) (i >>> 20);
            return 4;
        }
        require(5);
        byte[] bArr4 = this.buffer;
        int i7 = this.position;
        this.position = i7 + 5;
        bArr4[i7] = (byte) (i2 | 64);
        bArr4[i7 + 1] = (byte) ((i >>> 6) | 128);
        bArr4[i7 + 2] = (byte) ((i >>> 13) | 128);
        bArr4[i7 + 3] = (byte) ((i >>> 20) | 128);
        bArr4[i7 + 4] = (byte) (i >>> 27);
        return 5;
    }

    public int intLength(int i, boolean z) {
        if (this.varEncoding) {
            return varIntLength(i, z);
        }
        return 4;
    }

    public void writeLong(long j) {
        require(8);
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 8;
        bArr[i] = (byte) j;
        bArr[i + 1] = (byte) (j >>> 8);
        bArr[i + 2] = (byte) (j >>> 16);
        bArr[i + 3] = (byte) (j >>> 24);
        bArr[i + 4] = (byte) (j >>> 32);
        bArr[i + 5] = (byte) (j >>> 40);
        bArr[i + 6] = (byte) (j >>> 48);
        bArr[i + 7] = (byte) (j >>> 56);
    }

    public int writeLong(long j, boolean z) {
        if (this.varEncoding) {
            return writeVarLong(j, z);
        }
        writeLong(j);
        return 8;
    }

    public int writeVarLong(long j, boolean z) {
        if (!z) {
            j = (j << 1) ^ (j >> 63);
        }
        if ((j >>> 7) == 0) {
            if (this.position == this.capacity) {
                require(1);
            }
            byte[] bArr = this.buffer;
            int i = this.position;
            this.position = i + 1;
            bArr[i] = (byte) j;
            return 1;
        }
        if ((j >>> 14) == 0) {
            require(2);
            int i2 = this.position;
            this.position = i2 + 2;
            this.buffer[i2] = (byte) ((j & 127) | 128);
            this.buffer[i2 + 1] = (byte) (j >>> 7);
            return 2;
        }
        if ((j >>> 21) == 0) {
            require(3);
            int i3 = this.position;
            this.position = i3 + 3;
            byte[] bArr2 = this.buffer;
            bArr2[i3] = (byte) ((j & 127) | 128);
            bArr2[i3 + 1] = (byte) ((j >>> 7) | 128);
            bArr2[i3 + 2] = (byte) (j >>> 14);
            return 3;
        }
        if ((j >>> 28) == 0) {
            require(4);
            int i4 = this.position;
            this.position = i4 + 4;
            byte[] bArr3 = this.buffer;
            bArr3[i4] = (byte) ((j & 127) | 128);
            bArr3[i4 + 1] = (byte) ((j >>> 7) | 128);
            bArr3[i4 + 2] = (byte) ((j >>> 14) | 128);
            bArr3[i4 + 3] = (byte) (j >>> 21);
            return 4;
        }
        if ((j >>> 35) == 0) {
            require(5);
            int i5 = this.position;
            this.position = i5 + 5;
            byte[] bArr4 = this.buffer;
            bArr4[i5] = (byte) ((j & 127) | 128);
            bArr4[i5 + 1] = (byte) ((j >>> 7) | 128);
            bArr4[i5 + 2] = (byte) ((j >>> 14) | 128);
            bArr4[i5 + 3] = (byte) ((j >>> 21) | 128);
            bArr4[i5 + 4] = (byte) (j >>> 28);
            return 5;
        }
        if ((j >>> 42) == 0) {
            require(6);
            int i6 = this.position;
            this.position = i6 + 6;
            byte[] bArr5 = this.buffer;
            bArr5[i6] = (byte) ((j & 127) | 128);
            bArr5[i6 + 1] = (byte) ((j >>> 7) | 128);
            bArr5[i6 + 2] = (byte) ((j >>> 14) | 128);
            bArr5[i6 + 3] = (byte) ((j >>> 21) | 128);
            bArr5[i6 + 4] = (byte) ((j >>> 28) | 128);
            bArr5[i6 + 5] = (byte) (j >>> 35);
            return 6;
        }
        if ((j >>> 49) == 0) {
            require(7);
            int i7 = this.position;
            this.position = i7 + 7;
            byte[] bArr6 = this.buffer;
            bArr6[i7] = (byte) ((j & 127) | 128);
            bArr6[i7 + 1] = (byte) ((j >>> 7) | 128);
            bArr6[i7 + 2] = (byte) ((j >>> 14) | 128);
            bArr6[i7 + 3] = (byte) ((j >>> 21) | 128);
            bArr6[i7 + 4] = (byte) ((j >>> 28) | 128);
            bArr6[i7 + 5] = (byte) ((j >>> 35) | 128);
            bArr6[i7 + 6] = (byte) (j >>> 42);
            return 7;
        }
        if ((j >>> 56) == 0) {
            require(8);
            int i8 = this.position;
            this.position = i8 + 8;
            byte[] bArr7 = this.buffer;
            bArr7[i8] = (byte) ((j & 127) | 128);
            bArr7[i8 + 1] = (byte) ((j >>> 7) | 128);
            bArr7[i8 + 2] = (byte) ((j >>> 14) | 128);
            bArr7[i8 + 3] = (byte) ((j >>> 21) | 128);
            bArr7[i8 + 4] = (byte) ((j >>> 28) | 128);
            bArr7[i8 + 5] = (byte) ((j >>> 35) | 128);
            bArr7[i8 + 6] = (byte) ((j >>> 42) | 128);
            bArr7[i8 + 7] = (byte) (j >>> 49);
            return 8;
        }
        require(9);
        int i9 = this.position;
        this.position = i9 + 9;
        byte[] bArr8 = this.buffer;
        bArr8[i9] = (byte) ((j & 127) | 128);
        bArr8[i9 + 1] = (byte) ((j >>> 7) | 128);
        bArr8[i9 + 2] = (byte) ((j >>> 14) | 128);
        bArr8[i9 + 3] = (byte) ((j >>> 21) | 128);
        bArr8[i9 + 4] = (byte) ((j >>> 28) | 128);
        bArr8[i9 + 5] = (byte) ((j >>> 35) | 128);
        bArr8[i9 + 6] = (byte) ((j >>> 42) | 128);
        bArr8[i9 + 7] = (byte) ((j >>> 49) | 128);
        bArr8[i9 + 8] = (byte) (j >>> 56);
        return 9;
    }

    public int longLength(int i, boolean z) {
        if (this.varEncoding) {
            return varLongLength(i, z);
        }
        return 8;
    }

    public void writeFloat(float f) {
        require(4);
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 4;
        int floatToIntBits = Float.floatToIntBits(f);
        bArr[i] = (byte) floatToIntBits;
        bArr[i + 1] = (byte) (floatToIntBits >> 8);
        bArr[i + 2] = (byte) (floatToIntBits >> 16);
        bArr[i + 3] = (byte) (floatToIntBits >> 24);
    }

    public int writeVarFloat(float f, float f2, boolean z) {
        return writeVarInt((int) (f * f2), z);
    }

    public void writeDouble(double d) {
        require(8);
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 8;
        bArr[i] = (byte) Double.doubleToLongBits(d);
        bArr[i + 1] = (byte) (r0 >>> 8);
        bArr[i + 2] = (byte) (r0 >>> 16);
        bArr[i + 3] = (byte) (r0 >>> 24);
        bArr[i + 4] = (byte) (r0 >>> 32);
        bArr[i + 5] = (byte) (r0 >>> 40);
        bArr[i + 6] = (byte) (r0 >>> 48);
        bArr[i + 7] = (byte) (r0 >>> 56);
    }

    public int writeVarDouble(double d, double d2, boolean z) {
        return writeVarLong((long) (d * d2), z);
    }

    public void writeShort(int i) {
        require(2);
        int i2 = this.position;
        this.position = i2 + 2;
        this.buffer[i2] = (byte) i;
        this.buffer[i2 + 1] = (byte) (i >>> 8);
    }

    public void writeChar(char c) {
        require(2);
        int i = this.position;
        this.position = i + 2;
        this.buffer[i] = (byte) c;
        this.buffer[i + 1] = (byte) (c >>> '\b');
    }

    public void writeBoolean(boolean z) {
        if (this.position == this.capacity) {
            require(1);
        }
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = z ? (byte) 1 : (byte) 0;
    }

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
                str.getBytes(0, length, this.buffer, this.position);
                this.position += length;
            }
            byte[] bArr = this.buffer;
            int i2 = this.position - 1;
            bArr[i2] = (byte) (bArr[i2] | 128);
            return;
        }
        writeVarIntFlag(true, length + 1, true);
        int i3 = 0;
        if (this.capacity - this.position >= length) {
            byte[] bArr2 = this.buffer;
            int i4 = this.position;
            do {
                char charAt = str.charAt(i3);
                if (charAt <= 127) {
                    int i5 = i4;
                    i4++;
                    bArr2[i5] = (byte) charAt;
                    i3++;
                } else {
                    this.position = i4;
                }
            } while (i3 != length);
            this.position = i4;
            return;
        }
        if (i3 < length) {
            writeUtf8_slow(str, length, i3);
        }
    }

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
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = -126;
                byte[] bArr2 = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr2[i2] = (byte) str.charAt(0);
                return;
            default:
                if (this.capacity - this.position < length) {
                    writeAscii_slow(str, length);
                } else {
                    str.getBytes(0, length, this.buffer, this.position);
                    this.position += length;
                }
                byte[] bArr3 = this.buffer;
                int i3 = this.position - 1;
                bArr3[i3] = (byte) (bArr3[i3] | 128);
                return;
        }
    }

    private void writeUtf8_slow(String str, int i, int i2) {
        while (i2 < i) {
            if (this.position == this.capacity) {
                require(Math.min(this.capacity, i - i2));
            }
            char charAt = str.charAt(i2);
            if (charAt <= 127) {
                byte[] bArr = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr[i3] = (byte) charAt;
            } else {
                if (charAt > 2047) {
                    byte[] bArr2 = this.buffer;
                    int i4 = this.position;
                    this.position = i4 + 1;
                    bArr2[i4] = (byte) (224 | ((charAt >> '\f') & 15));
                    require(2);
                    byte[] bArr3 = this.buffer;
                    int i5 = this.position;
                    this.position = i5 + 1;
                    bArr3[i5] = (byte) (128 | ((charAt >> 6) & 63));
                } else {
                    byte[] bArr4 = this.buffer;
                    int i6 = this.position;
                    this.position = i6 + 1;
                    bArr4[i6] = (byte) (192 | ((charAt >> 6) & 31));
                    if (this.position == this.capacity) {
                        require(1);
                    }
                }
                byte[] bArr5 = this.buffer;
                int i7 = this.position;
                this.position = i7 + 1;
                bArr5[i7] = (byte) (128 | (charAt & '?'));
            }
            i2++;
        }
    }

    private void writeAscii_slow(String str, int i) {
        if (i == 0) {
            return;
        }
        if (this.position == this.capacity) {
            require(1);
        }
        int i2 = 0;
        byte[] bArr = this.buffer;
        int min = Math.min(i, this.capacity - this.position);
        while (i2 < i) {
            int i3 = i2;
            str.getBytes(i3, i3 + min, bArr, this.position);
            i2 += min;
            this.position += min;
            min = Math.min(i - i2, this.capacity);
            if (require(min)) {
                bArr = this.buffer;
            }
        }
    }

    public void writeInts(int[] iArr, int i, int i2) {
        if (this.capacity >= (i2 << 2)) {
            require(i2 << 2);
            byte[] bArr = this.buffer;
            int i3 = this.position;
            int i4 = i + i2;
            while (i < i4) {
                int i5 = iArr[i];
                bArr[i3] = (byte) i5;
                bArr[i3 + 1] = (byte) (i5 >> 8);
                bArr[i3 + 2] = (byte) (i5 >> 16);
                bArr[i3 + 3] = (byte) (i5 >> 24);
                i++;
                i3 += 4;
            }
            this.position = i3;
            return;
        }
        int i6 = i + i2;
        while (i < i6) {
            writeInt(iArr[i]);
            i++;
        }
    }

    public void writeInts(int[] iArr, int i, int i2, boolean z) {
        if (this.varEncoding) {
            int i3 = i + i2;
            while (i < i3) {
                writeVarInt(iArr[i], z);
                i++;
            }
            return;
        }
        writeInts(iArr, i, i2);
    }

    public void writeLongs(long[] jArr, int i, int i2) {
        if (this.capacity >= (i2 << 3)) {
            require(i2 << 3);
            byte[] bArr = this.buffer;
            int i3 = this.position;
            int i4 = i + i2;
            while (i < i4) {
                bArr[i3] = (byte) jArr[i];
                bArr[i3 + 1] = (byte) (r0 >>> 8);
                bArr[i3 + 2] = (byte) (r0 >>> 16);
                bArr[i3 + 3] = (byte) (r0 >>> 24);
                bArr[i3 + 4] = (byte) (r0 >>> 32);
                bArr[i3 + 5] = (byte) (r0 >>> 40);
                bArr[i3 + 6] = (byte) (r0 >>> 48);
                bArr[i3 + 7] = (byte) (r0 >>> 56);
                i++;
                i3 += 8;
            }
            this.position = i3;
            return;
        }
        int i5 = i + i2;
        while (i < i5) {
            writeLong(jArr[i]);
            i++;
        }
    }

    public void writeLongs(long[] jArr, int i, int i2, boolean z) {
        if (this.varEncoding) {
            int i3 = i + i2;
            while (i < i3) {
                writeVarLong(jArr[i], z);
                i++;
            }
            return;
        }
        writeLongs(jArr, i, i2);
    }

    public void writeFloats(float[] fArr, int i, int i2) {
        if (this.capacity >= (i2 << 2)) {
            require(i2 << 2);
            byte[] bArr = this.buffer;
            int i3 = this.position;
            int i4 = i + i2;
            while (i < i4) {
                int floatToIntBits = Float.floatToIntBits(fArr[i]);
                bArr[i3] = (byte) floatToIntBits;
                bArr[i3 + 1] = (byte) (floatToIntBits >> 8);
                bArr[i3 + 2] = (byte) (floatToIntBits >> 16);
                bArr[i3 + 3] = (byte) (floatToIntBits >> 24);
                i++;
                i3 += 4;
            }
            this.position = i3;
            return;
        }
        int i5 = i + i2;
        while (i < i5) {
            writeFloat(fArr[i]);
            i++;
        }
    }

    public void writeDoubles(double[] dArr, int i, int i2) {
        if (this.capacity >= (i2 << 3)) {
            require(i2 << 3);
            byte[] bArr = this.buffer;
            int i3 = this.position;
            int i4 = i + i2;
            while (i < i4) {
                bArr[i3] = (byte) Double.doubleToLongBits(dArr[i]);
                bArr[i3 + 1] = (byte) (r0 >>> 8);
                bArr[i3 + 2] = (byte) (r0 >>> 16);
                bArr[i3 + 3] = (byte) (r0 >>> 24);
                bArr[i3 + 4] = (byte) (r0 >>> 32);
                bArr[i3 + 5] = (byte) (r0 >>> 40);
                bArr[i3 + 6] = (byte) (r0 >>> 48);
                bArr[i3 + 7] = (byte) (r0 >>> 56);
                i++;
                i3 += 8;
            }
            this.position = i3;
            return;
        }
        int i5 = i + i2;
        while (i < i5) {
            writeDouble(dArr[i]);
            i++;
        }
    }

    public void writeShorts(short[] sArr, int i, int i2) {
        if (this.capacity >= (i2 << 1)) {
            require(i2 << 1);
            byte[] bArr = this.buffer;
            int i3 = this.position;
            int i4 = i + i2;
            while (i < i4) {
                short s = sArr[i];
                bArr[i3] = (byte) s;
                bArr[i3 + 1] = (byte) (s >>> 8);
                i++;
                i3 += 2;
            }
            this.position = i3;
            return;
        }
        int i5 = i + i2;
        while (i < i5) {
            writeShort(sArr[i]);
            i++;
        }
    }

    public void writeChars(char[] cArr, int i, int i2) {
        if (this.capacity >= (i2 << 1)) {
            require(i2 << 1);
            byte[] bArr = this.buffer;
            int i3 = this.position;
            int i4 = i + i2;
            while (i < i4) {
                char c = cArr[i];
                bArr[i3] = (byte) c;
                bArr[i3 + 1] = (byte) (c >>> '\b');
                i++;
                i3 += 2;
            }
            this.position = i3;
            return;
        }
        int i5 = i + i2;
        while (i < i5) {
            writeChar(cArr[i]);
            i++;
        }
    }

    public void writeBooleans(boolean[] zArr, int i, int i2) {
        if (this.capacity >= i2) {
            require(i2);
            byte[] bArr = this.buffer;
            int i3 = this.position;
            int i4 = i + i2;
            while (i < i4) {
                bArr[i3] = zArr[i] ? (byte) 1 : (byte) 0;
                i++;
                i3++;
            }
            this.position = i3;
            return;
        }
        int i5 = i + i2;
        while (i < i5) {
            writeBoolean(zArr[i]);
            i++;
        }
    }

    public static int varIntLength(int i, boolean z) {
        if (!z) {
            i = (i << 1) ^ (i >> 31);
        }
        if ((i >>> 7) == 0) {
            return 1;
        }
        if ((i >>> 14) == 0) {
            return 2;
        }
        if ((i >>> 21) == 0) {
            return 3;
        }
        return (i >>> 28) == 0 ? 4 : 5;
    }

    public static int varLongLength(long j, boolean z) {
        if (!z) {
            j = (j << 1) ^ (j >> 63);
        }
        if ((j >>> 7) == 0) {
            return 1;
        }
        if ((j >>> 14) == 0) {
            return 2;
        }
        if ((j >>> 21) == 0) {
            return 3;
        }
        if ((j >>> 28) == 0) {
            return 4;
        }
        if ((j >>> 35) == 0) {
            return 5;
        }
        if ((j >>> 42) == 0) {
            return 6;
        }
        if ((j >>> 49) == 0) {
            return 7;
        }
        return (j >>> 56) == 0 ? 8 : 9;
    }
}
