package com.esotericsoftware.kryo.io;

import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.util.Pool;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/io/Input.class */
public class Input extends InputStream implements Pool.Poolable {
    protected byte[] buffer;
    protected int position;
    protected int capacity;
    protected int limit;
    protected long total;
    protected char[] chars;
    protected InputStream inputStream;
    protected boolean varEncoding;

    public Input() {
        this.chars = new char[32];
        this.varEncoding = true;
    }

    public Input(int i) {
        this.chars = new char[32];
        this.varEncoding = true;
        this.capacity = i;
        this.buffer = new byte[i];
    }

    public Input(byte[] bArr) {
        this.chars = new char[32];
        this.varEncoding = true;
        setBuffer(bArr, 0, bArr.length);
    }

    public Input(byte[] bArr, int i, int i2) {
        this.chars = new char[32];
        this.varEncoding = true;
        setBuffer(bArr, i, i2);
    }

    public Input(InputStream inputStream) {
        this(4096);
        if (inputStream == null) {
            throw new IllegalArgumentException("inputStream cannot be null.");
        }
        this.inputStream = inputStream;
    }

    public Input(InputStream inputStream, int i) {
        this(i);
        if (inputStream == null) {
            throw new IllegalArgumentException("inputStream cannot be null.");
        }
        this.inputStream = inputStream;
    }

    public void setBuffer(byte[] bArr) {
        setBuffer(bArr, 0, bArr.length);
    }

    public void setBuffer(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
        this.buffer = bArr;
        this.position = i;
        this.limit = i + i2;
        this.capacity = bArr.length;
        this.total = 0L;
        this.inputStream = null;
    }

    public byte[] getBuffer() {
        return this.buffer;
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        this.limit = 0;
        reset();
    }

    public boolean getVariableLengthEncoding() {
        return this.varEncoding;
    }

    public void setVariableLengthEncoding(boolean z) {
        this.varEncoding = z;
    }

    public long total() {
        return this.total + this.position;
    }

    public void setTotal(long j) {
        this.total = j;
    }

    public int position() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public int limit() {
        return this.limit;
    }

    public void setLimit(int i) {
        this.limit = i;
    }

    @Override // java.io.InputStream, com.esotericsoftware.kryo.util.Pool.Poolable
    public void reset() {
        this.position = 0;
        this.total = 0L;
    }

    public void skip(int i) {
        int min = Math.min(this.limit - this.position, i);
        while (true) {
            this.position += min;
            int i2 = i - min;
            i = i2;
            if (i2 != 0) {
                min = Math.min(i, this.capacity);
                require(min);
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int fill(byte[] bArr, int i, int i2) {
        if (this.inputStream == null) {
            return -1;
        }
        try {
            return this.inputStream.read(bArr, i, i2);
        } catch (IOException e) {
            throw new KryoException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int require(int i) {
        int i2 = this.limit - this.position;
        int i3 = i2;
        if (i2 >= i) {
            return i3;
        }
        if (i > this.capacity) {
            throw new KryoException("Buffer too small: capacity: " + this.capacity + ", required: " + i);
        }
        if (i3 > 0) {
            int fill = fill(this.buffer, this.limit, this.capacity - this.limit);
            if (fill == -1) {
                throw new KryoBufferUnderflowException("Buffer underflow.");
            }
            int i4 = i3 + fill;
            i3 = i4;
            if (i4 >= i) {
                this.limit += fill;
                return i3;
            }
        }
        System.arraycopy(this.buffer, this.position, this.buffer, 0, i3);
        this.total += this.position;
        this.position = 0;
        while (true) {
            int fill2 = fill(this.buffer, i3, this.capacity - i3);
            if (fill2 != -1) {
                int i5 = i3 + fill2;
                i3 = i5;
                if (i5 >= i) {
                    break;
                }
            } else if (i3 < i) {
                throw new KryoBufferUnderflowException("Buffer underflow.");
            }
        }
        this.limit = i3;
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int optional(int i) {
        int i2;
        int i3 = this.limit - this.position;
        if (i3 >= i) {
            return i;
        }
        int min = Math.min(i, this.capacity);
        int fill = fill(this.buffer, this.limit, this.capacity - this.limit);
        if (fill == -1) {
            if (i3 == 0) {
                return -1;
            }
            return Math.min(i3, min);
        }
        int i4 = i3 + fill;
        int i5 = i4;
        if (i4 >= min) {
            this.limit += fill;
            return min;
        }
        System.arraycopy(this.buffer, this.position, this.buffer, 0, i5);
        this.total += this.position;
        this.position = 0;
        do {
            int fill2 = fill(this.buffer, i5, this.capacity - i5);
            if (fill2 == -1) {
                break;
            }
            i2 = i5 + fill2;
            i5 = i2;
        } while (i2 < min);
        this.limit = i5;
        if (i5 == 0) {
            return -1;
        }
        return Math.min(i5, min);
    }

    public boolean end() {
        return optional(1) <= 0;
    }

    @Override // java.io.InputStream
    public int available() {
        return (this.limit - this.position) + (this.inputStream != null ? this.inputStream.available() : 0);
    }

    @Override // java.io.InputStream
    public int read() {
        if (optional(1) <= 0) {
            return -1;
        }
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        return bArr[i] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
        int min = Math.min(this.limit - this.position, i2);
        while (true) {
            System.arraycopy(this.buffer, this.position, bArr, i, min);
            this.position += min;
            int i3 = i2 - min;
            i2 = i3;
            if (i3 == 0) {
                break;
            }
            i += min;
            int optional = optional(i2);
            min = optional;
            if (optional == -1) {
                if (i2 == i2) {
                    return -1;
                }
            } else if (this.position == this.limit) {
                break;
            }
        }
        return i2 - i2;
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        long j2 = j;
        while (true) {
            long j3 = j2;
            if (j3 > 0) {
                int min = (int) Math.min(2147483639L, j3);
                skip(min);
                j2 = j3 - min;
            } else {
                return j;
            }
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.inputStream != null) {
            try {
                this.inputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    public byte readByte() {
        if (this.position == this.limit) {
            require(1);
        }
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        return bArr[i];
    }

    public int readByteUnsigned() {
        if (this.position == this.limit) {
            require(1);
        }
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        return bArr[i] & 255;
    }

    public byte[] readBytes(int i) {
        byte[] bArr = new byte[i];
        readBytes(bArr, 0, i);
        return bArr;
    }

    public void readBytes(byte[] bArr) {
        readBytes(bArr, 0, bArr.length);
    }

    public void readBytes(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
        int min = Math.min(this.limit - this.position, i2);
        while (true) {
            System.arraycopy(this.buffer, this.position, bArr, i, min);
            this.position += min;
            int i3 = i2 - min;
            i2 = i3;
            if (i3 != 0) {
                i += min;
                min = Math.min(i2, this.capacity);
                require(min);
            } else {
                return;
            }
        }
    }

    public int readInt() {
        require(4);
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 4;
        return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24);
    }

    public int readInt(boolean z) {
        return this.varEncoding ? readVarInt(z) : readInt();
    }

    public boolean canReadInt() {
        return this.varEncoding ? canReadVarInt() : this.limit - this.position >= 4 || optional(4) == 4;
    }

    public int readVarInt(boolean z) {
        if (require(1) < 5) {
            return readVarInt_slow(z);
        }
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        byte b2 = bArr[i];
        int i2 = b2 & Byte.MAX_VALUE;
        if ((b2 & 128) != 0) {
            byte[] bArr2 = this.buffer;
            int i3 = this.position;
            int i4 = i3 + 1;
            byte b3 = bArr2[i3];
            i2 |= (b3 & Byte.MAX_VALUE) << 7;
            if ((b3 & 128) != 0) {
                i4++;
                byte b4 = bArr2[i4];
                i2 |= (b4 & Byte.MAX_VALUE) << 14;
                if ((b4 & 128) != 0) {
                    i4++;
                    byte b5 = bArr2[i4];
                    i2 |= (b5 & Byte.MAX_VALUE) << 21;
                    if ((b5 & 128) != 0) {
                        i4++;
                        i2 |= (bArr2[i4] & Byte.MAX_VALUE) << 28;
                    }
                }
            }
            this.position = i4;
        }
        return z ? i2 : (i2 >>> 1) ^ (-(i2 & 1));
    }

    private int readVarInt_slow(boolean z) {
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        byte b2 = bArr[i];
        int i2 = b2 & Byte.MAX_VALUE;
        if ((b2 & 128) != 0) {
            if (this.position == this.limit) {
                require(1);
            }
            byte[] bArr2 = this.buffer;
            int i3 = this.position;
            this.position = i3 + 1;
            byte b3 = bArr2[i3];
            i2 |= (b3 & Byte.MAX_VALUE) << 7;
            if ((b3 & 128) != 0) {
                if (this.position == this.limit) {
                    require(1);
                }
                int i4 = this.position;
                this.position = i4 + 1;
                byte b4 = bArr2[i4];
                i2 |= (b4 & Byte.MAX_VALUE) << 14;
                if ((b4 & 128) != 0) {
                    if (this.position == this.limit) {
                        require(1);
                    }
                    int i5 = this.position;
                    this.position = i5 + 1;
                    byte b5 = bArr2[i5];
                    i2 |= (b5 & Byte.MAX_VALUE) << 21;
                    if ((b5 & 128) != 0) {
                        if (this.position == this.limit) {
                            require(1);
                        }
                        int i6 = this.position;
                        this.position = i6 + 1;
                        i2 |= (bArr2[i6] & Byte.MAX_VALUE) << 28;
                    }
                }
            }
        }
        return z ? i2 : (i2 >>> 1) ^ (-(i2 & 1));
    }

    public boolean canReadVarInt() {
        if (this.limit - this.position >= 5) {
            return true;
        }
        if (optional(5) <= 0) {
            return false;
        }
        int i = this.position;
        int i2 = this.limit;
        byte[] bArr = this.buffer;
        int i3 = i + 1;
        if ((bArr[i] & 128) == 0) {
            return true;
        }
        if (i3 == i2) {
            return false;
        }
        int i4 = i3 + 1;
        if ((bArr[i3] & 128) == 0) {
            return true;
        }
        if (i4 == i2) {
            return false;
        }
        int i5 = i4 + 1;
        if ((bArr[i4] & 128) == 0) {
            return true;
        }
        if (i5 == i2) {
            return false;
        }
        return (bArr[i5] & 128) == 0 || i5 + 1 != i2;
    }

    public boolean readVarIntFlag() {
        if (this.position == this.limit) {
            require(1);
        }
        return (this.buffer[this.position] & 128) != 0;
    }

    public int readVarIntFlag(boolean z) {
        if (require(1) < 5) {
            return readVarIntFlag_slow(z);
        }
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        byte b2 = bArr[i];
        int i2 = b2 & 63;
        if ((b2 & 64) != 0) {
            byte[] bArr2 = this.buffer;
            int i3 = this.position;
            int i4 = i3 + 1;
            byte b3 = bArr2[i3];
            i2 |= (b3 & Byte.MAX_VALUE) << 6;
            if ((b3 & 128) != 0) {
                i4++;
                byte b4 = bArr2[i4];
                i2 |= (b4 & Byte.MAX_VALUE) << 13;
                if ((b4 & 128) != 0) {
                    i4++;
                    byte b5 = bArr2[i4];
                    i2 |= (b5 & Byte.MAX_VALUE) << 20;
                    if ((b5 & 128) != 0) {
                        i4++;
                        i2 |= (bArr2[i4] & Byte.MAX_VALUE) << 27;
                    }
                }
            }
            this.position = i4;
        }
        return z ? i2 : (i2 >>> 1) ^ (-(i2 & 1));
    }

    private int readVarIntFlag_slow(boolean z) {
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        byte b2 = bArr[i];
        int i2 = b2 & 63;
        if ((b2 & 64) != 0) {
            if (this.position == this.limit) {
                require(1);
            }
            byte[] bArr2 = this.buffer;
            int i3 = this.position;
            this.position = i3 + 1;
            byte b3 = bArr2[i3];
            i2 |= (b3 & Byte.MAX_VALUE) << 6;
            if ((b3 & 128) != 0) {
                if (this.position == this.limit) {
                    require(1);
                }
                int i4 = this.position;
                this.position = i4 + 1;
                byte b4 = bArr2[i4];
                i2 |= (b4 & Byte.MAX_VALUE) << 13;
                if ((b4 & 128) != 0) {
                    if (this.position == this.limit) {
                        require(1);
                    }
                    int i5 = this.position;
                    this.position = i5 + 1;
                    byte b5 = bArr2[i5];
                    i2 |= (b5 & Byte.MAX_VALUE) << 20;
                    if ((b5 & 128) != 0) {
                        if (this.position == this.limit) {
                            require(1);
                        }
                        int i6 = this.position;
                        this.position = i6 + 1;
                        i2 |= (bArr2[i6] & Byte.MAX_VALUE) << 27;
                    }
                }
            }
        }
        return z ? i2 : (i2 >>> 1) ^ (-(i2 & 1));
    }

    public long readLong() {
        require(8);
        byte[] bArr = this.buffer;
        this.position = this.position + 8;
        return (bArr[r0] & 255) | ((bArr[r0 + 1] & 255) << 8) | ((bArr[r0 + 2] & 255) << 16) | ((bArr[r0 + 3] & 255) << 24) | ((bArr[r0 + 4] & 255) << 32) | ((bArr[r0 + 5] & 255) << 40) | ((bArr[r0 + 6] & 255) << 48) | (bArr[r0 + 7] << 56);
    }

    public long readLong(boolean z) {
        return this.varEncoding ? readVarLong(z) : readLong();
    }

    public long readVarLong(boolean z) {
        if (require(1) < 9) {
            return readVarLong_slow(z);
        }
        int i = this.position;
        int i2 = i + 1;
        byte b2 = this.buffer[i];
        long j = b2 & Byte.MAX_VALUE;
        if ((b2 & 128) != 0) {
            byte[] bArr = this.buffer;
            i2++;
            j |= (r0 & Byte.MAX_VALUE) << 7;
            if ((bArr[i2] & 128) != 0) {
                i2++;
                j |= (r0 & Byte.MAX_VALUE) << 14;
                if ((bArr[i2] & 128) != 0) {
                    i2++;
                    j |= (r0 & Byte.MAX_VALUE) << 21;
                    if ((bArr[i2] & 128) != 0) {
                        i2++;
                        j |= (r0 & Byte.MAX_VALUE) << 28;
                        if ((bArr[i2] & 128) != 0) {
                            i2++;
                            j |= (r0 & Byte.MAX_VALUE) << 35;
                            if ((bArr[i2] & 128) != 0) {
                                i2++;
                                j |= (r0 & Byte.MAX_VALUE) << 42;
                                if ((bArr[i2] & 128) != 0) {
                                    i2++;
                                    j |= (r0 & Byte.MAX_VALUE) << 49;
                                    if ((bArr[i2] & 128) != 0) {
                                        i2++;
                                        j |= bArr[i2] << 56;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        this.position = i2;
        return z ? j : (j >>> 1) ^ (-(j & 1));
    }

    private long readVarLong_slow(boolean z) {
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        byte b2 = bArr[i];
        long j = b2 & Byte.MAX_VALUE;
        if ((b2 & 128) != 0) {
            if (this.position == this.limit) {
                require(1);
            }
            byte[] bArr2 = this.buffer;
            int i2 = this.position;
            this.position = i2 + 1;
            j |= (r0 & Byte.MAX_VALUE) << 7;
            if ((bArr2[i2] & 128) != 0) {
                if (this.position == this.limit) {
                    require(1);
                }
                int i3 = this.position;
                this.position = i3 + 1;
                j |= (r0 & Byte.MAX_VALUE) << 14;
                if ((bArr2[i3] & 128) != 0) {
                    if (this.position == this.limit) {
                        require(1);
                    }
                    int i4 = this.position;
                    this.position = i4 + 1;
                    j |= (r0 & Byte.MAX_VALUE) << 21;
                    if ((bArr2[i4] & 128) != 0) {
                        if (this.position == this.limit) {
                            require(1);
                        }
                        int i5 = this.position;
                        this.position = i5 + 1;
                        j |= (r0 & Byte.MAX_VALUE) << 28;
                        if ((bArr2[i5] & 128) != 0) {
                            if (this.position == this.limit) {
                                require(1);
                            }
                            int i6 = this.position;
                            this.position = i6 + 1;
                            j |= (r0 & Byte.MAX_VALUE) << 35;
                            if ((bArr2[i6] & 128) != 0) {
                                if (this.position == this.limit) {
                                    require(1);
                                }
                                int i7 = this.position;
                                this.position = i7 + 1;
                                j |= (r0 & Byte.MAX_VALUE) << 42;
                                if ((bArr2[i7] & 128) != 0) {
                                    if (this.position == this.limit) {
                                        require(1);
                                    }
                                    int i8 = this.position;
                                    this.position = i8 + 1;
                                    j |= (r0 & Byte.MAX_VALUE) << 49;
                                    if ((bArr2[i8] & 128) != 0) {
                                        if (this.position == this.limit) {
                                            require(1);
                                        }
                                        this.position = this.position + 1;
                                        j |= bArr2[r2] << 56;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return z ? j : (j >>> 1) ^ (-(j & 1));
    }

    public boolean canReadLong() {
        return this.varEncoding ? canReadVarLong() : this.limit - this.position >= 8 || optional(8) == 8;
    }

    public boolean canReadVarLong() {
        if (this.limit - this.position >= 9) {
            return true;
        }
        if (optional(5) <= 0) {
            return false;
        }
        int i = this.position;
        int i2 = this.limit;
        byte[] bArr = this.buffer;
        int i3 = i + 1;
        if ((bArr[i] & 128) == 0) {
            return true;
        }
        if (i3 == i2) {
            return false;
        }
        int i4 = i3 + 1;
        if ((bArr[i3] & 128) == 0) {
            return true;
        }
        if (i4 == i2) {
            return false;
        }
        int i5 = i4 + 1;
        if ((bArr[i4] & 128) == 0) {
            return true;
        }
        if (i5 == i2) {
            return false;
        }
        int i6 = i5 + 1;
        if ((bArr[i5] & 128) == 0) {
            return true;
        }
        if (i6 == i2) {
            return false;
        }
        int i7 = i6 + 1;
        if ((bArr[i6] & 128) == 0) {
            return true;
        }
        if (i7 == i2) {
            return false;
        }
        int i8 = i7 + 1;
        if ((bArr[i7] & 128) == 0) {
            return true;
        }
        if (i8 == i2) {
            return false;
        }
        int i9 = i8 + 1;
        if ((bArr[i8] & 128) == 0) {
            return true;
        }
        if (i9 == i2) {
            return false;
        }
        return (bArr[i9] & 128) == 0 || i9 + 1 != i2;
    }

    public float readFloat() {
        require(4);
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 4;
        return Float.intBitsToFloat((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24));
    }

    public float readVarFloat(float f, boolean z) {
        return readVarInt(z) / f;
    }

    public double readDouble() {
        require(8);
        byte[] bArr = this.buffer;
        this.position = this.position + 8;
        return Double.longBitsToDouble((bArr[r0] & 255) | ((bArr[r0 + 1] & 255) << 8) | ((bArr[r0 + 2] & 255) << 16) | ((bArr[r0 + 3] & 255) << 24) | ((bArr[r0 + 4] & 255) << 32) | ((bArr[r0 + 5] & 255) << 40) | ((bArr[r0 + 6] & 255) << 48) | (bArr[r0 + 7] << 56));
    }

    public double readVarDouble(double d, boolean z) {
        return readVarLong(z) / d;
    }

    public short readShort() {
        require(2);
        int i = this.position;
        this.position = i + 2;
        return (short) ((this.buffer[i] & 255) | ((this.buffer[i + 1] & 255) << 8));
    }

    public int readShortUnsigned() {
        require(2);
        int i = this.position;
        this.position = i + 2;
        return (this.buffer[i] & 255) | ((this.buffer[i + 1] & 255) << 8);
    }

    public char readChar() {
        require(2);
        int i = this.position;
        this.position = i + 2;
        return (char) ((this.buffer[i] & 255) | ((this.buffer[i + 1] & 255) << 8));
    }

    public boolean readBoolean() {
        if (this.position == this.limit) {
            require(1);
        }
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        return bArr[i] == 1;
    }

    public String readString() {
        if (!readVarIntFlag()) {
            return readAsciiString();
        }
        int readVarIntFlag = readVarIntFlag(true);
        switch (readVarIntFlag) {
            case 0:
                return null;
            case 1:
                return "";
            default:
                int i = readVarIntFlag - 1;
                readUtf8Chars(i);
                return new String(this.chars, 0, i);
        }
    }

    public StringBuilder readStringBuilder() {
        if (!readVarIntFlag()) {
            return new StringBuilder(readAsciiString());
        }
        int readVarIntFlag = readVarIntFlag(true);
        switch (readVarIntFlag) {
            case 0:
                return null;
            case 1:
                return new StringBuilder(0);
            default:
                int i = readVarIntFlag - 1;
                readUtf8Chars(i);
                StringBuilder sb = new StringBuilder(i);
                sb.append(this.chars, 0, i);
                return sb;
        }
    }

    private void readUtf8Chars(int i) {
        if (this.chars.length < i) {
            this.chars = new char[i];
        }
        byte[] bArr = this.buffer;
        char[] cArr = this.chars;
        int i2 = 0;
        int min = Math.min(require(1), i);
        int i3 = this.position;
        while (true) {
            if (i2 >= min) {
                break;
            }
            int i4 = i3;
            i3++;
            byte b2 = bArr[i4];
            if (b2 < 0) {
                i3--;
                break;
            } else {
                int i5 = i2;
                i2++;
                cArr[i5] = (char) b2;
            }
        }
        this.position = i3;
        if (i2 < i) {
            readUtf8Chars_slow(i, i2);
        }
    }

    private void readUtf8Chars_slow(int i, int i2) {
        char[] cArr = this.chars;
        byte[] bArr = this.buffer;
        while (i2 < i) {
            if (this.position == this.limit) {
                require(1);
            }
            int i3 = this.position;
            this.position = i3 + 1;
            int i4 = bArr[i3] & 255;
            switch (i4 >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    cArr[i2] = (char) i4;
                    break;
                case 12:
                case 13:
                    if (this.position == this.limit) {
                        require(1);
                    }
                    int i5 = this.position;
                    this.position = i5 + 1;
                    cArr[i2] = (char) (((i4 & 31) << 6) | (bArr[i5] & 63));
                    break;
                case 14:
                    require(2);
                    int i6 = this.position;
                    this.position = i6 + 2;
                    cArr[i2] = (char) (((i4 & 15) << 12) | ((bArr[i6] & 63) << 6) | (bArr[i6 + 1] & 63));
                    break;
            }
            i2++;
        }
    }

    private String readAsciiString() {
        char[] cArr = this.chars;
        byte[] bArr = this.buffer;
        int i = this.position;
        int i2 = 0;
        int min = Math.min(cArr.length, this.limit - this.position);
        while (i2 < min) {
            byte b2 = bArr[i];
            if ((b2 & 128) == 128) {
                this.position = i + 1;
                cArr[i2] = (char) (b2 & Byte.MAX_VALUE);
                return new String(cArr, 0, i2 + 1);
            }
            cArr[i2] = (char) b2;
            i2++;
            i++;
        }
        this.position = i;
        return readAscii_slow(i2);
    }

    private String readAscii_slow(int i) {
        char[] cArr = this.chars;
        byte[] bArr = this.buffer;
        while (true) {
            if (this.position == this.limit) {
                require(1);
            }
            int i2 = this.position;
            this.position = i2 + 1;
            byte b2 = bArr[i2];
            if (i == cArr.length) {
                char[] cArr2 = new char[i << 1];
                System.arraycopy(cArr, 0, cArr2, 0, i);
                cArr = cArr2;
                this.chars = cArr2;
            }
            if ((b2 & 128) == 128) {
                cArr[i] = (char) (b2 & Byte.MAX_VALUE);
                return new String(cArr, 0, i + 1);
            }
            int i3 = i;
            i++;
            cArr[i3] = (char) b2;
        }
    }

    public int[] readInts(int i) {
        int[] iArr = new int[i];
        if (optional(i << 2) == (i << 2)) {
            byte[] bArr = this.buffer;
            int i2 = this.position;
            int i3 = 0;
            while (i3 < i) {
                iArr[i3] = (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16) | ((bArr[i2 + 3] & 255) << 24);
                i3++;
                i2 += 4;
            }
            this.position = i2;
        } else {
            for (int i4 = 0; i4 < i; i4++) {
                iArr[i4] = readInt();
            }
        }
        return iArr;
    }

    public int[] readInts(int i, boolean z) {
        if (this.varEncoding) {
            int[] iArr = new int[i];
            for (int i2 = 0; i2 < i; i2++) {
                iArr[i2] = readVarInt(z);
            }
            return iArr;
        }
        return readInts(i);
    }

    public long[] readLongs(int i) {
        long[] jArr = new long[i];
        if (optional(i << 3) == (i << 3)) {
            byte[] bArr = this.buffer;
            int i2 = this.position;
            int i3 = 0;
            while (i3 < i) {
                jArr[i3] = (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16) | ((bArr[i2 + 3] & 255) << 24) | ((bArr[i2 + 4] & 255) << 32) | ((bArr[i2 + 5] & 255) << 40) | ((bArr[i2 + 6] & 255) << 48) | (bArr[i2 + 7] << 56);
                i3++;
                i2 += 8;
            }
            this.position = i2;
        } else {
            for (int i4 = 0; i4 < i; i4++) {
                jArr[i4] = readLong();
            }
        }
        return jArr;
    }

    public long[] readLongs(int i, boolean z) {
        if (this.varEncoding) {
            long[] jArr = new long[i];
            for (int i2 = 0; i2 < i; i2++) {
                jArr[i2] = readVarLong(z);
            }
            return jArr;
        }
        return readLongs(i);
    }

    public float[] readFloats(int i) {
        float[] fArr = new float[i];
        if (optional(i << 2) == (i << 2)) {
            byte[] bArr = this.buffer;
            int i2 = this.position;
            int i3 = 0;
            while (i3 < i) {
                fArr[i3] = Float.intBitsToFloat((bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16) | ((bArr[i2 + 3] & 255) << 24));
                i3++;
                i2 += 4;
            }
            this.position = i2;
        } else {
            for (int i4 = 0; i4 < i; i4++) {
                fArr[i4] = readFloat();
            }
        }
        return fArr;
    }

    public double[] readDoubles(int i) {
        double[] dArr = new double[i];
        if (optional(i << 3) == (i << 3)) {
            byte[] bArr = this.buffer;
            int i2 = this.position;
            int i3 = 0;
            while (i3 < i) {
                dArr[i3] = Double.longBitsToDouble((bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16) | ((bArr[i2 + 3] & 255) << 24) | ((bArr[i2 + 4] & 255) << 32) | ((bArr[i2 + 5] & 255) << 40) | ((bArr[i2 + 6] & 255) << 48) | (bArr[i2 + 7] << 56));
                i3++;
                i2 += 8;
            }
            this.position = i2;
        } else {
            for (int i4 = 0; i4 < i; i4++) {
                dArr[i4] = readDouble();
            }
        }
        return dArr;
    }

    public short[] readShorts(int i) {
        short[] sArr = new short[i];
        if (optional(i << 1) == (i << 1)) {
            byte[] bArr = this.buffer;
            int i2 = this.position;
            int i3 = 0;
            while (i3 < i) {
                sArr[i3] = (short) ((bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8));
                i3++;
                i2 += 2;
            }
            this.position = i2;
        } else {
            for (int i4 = 0; i4 < i; i4++) {
                sArr[i4] = readShort();
            }
        }
        return sArr;
    }

    public char[] readChars(int i) {
        char[] cArr = new char[i];
        if (optional(i << 1) == (i << 1)) {
            byte[] bArr = this.buffer;
            int i2 = this.position;
            int i3 = 0;
            while (i3 < i) {
                cArr[i3] = (char) ((bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8));
                i3++;
                i2 += 2;
            }
            this.position = i2;
        } else {
            for (int i4 = 0; i4 < i; i4++) {
                cArr[i4] = readChar();
            }
        }
        return cArr;
    }

    public boolean[] readBooleans(int i) {
        boolean[] zArr = new boolean[i];
        if (optional(i) == i) {
            byte[] bArr = this.buffer;
            int i2 = this.position;
            int i3 = 0;
            while (i3 < i) {
                zArr[i3] = bArr[i2] != 0;
                i3++;
                i2++;
            }
            this.position = i2;
        } else {
            for (int i4 = 0; i4 < i; i4++) {
                zArr[i4] = readBoolean();
            }
        }
        return zArr;
    }
}
