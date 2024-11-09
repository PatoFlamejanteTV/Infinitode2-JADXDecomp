package com.esotericsoftware.kryo.io;

import com.esotericsoftware.kryo.KryoException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/io/ByteBufferInput.class */
public class ByteBufferInput extends Input {
    private static final ByteOrder nativeOrder = ByteOrder.nativeOrder();
    protected ByteBuffer byteBuffer;
    private byte[] tempBuffer;

    public ByteBufferInput() {
    }

    public ByteBufferInput(int i) {
        this.capacity = i;
        this.byteBuffer = ByteBuffer.allocateDirect(i);
    }

    public ByteBufferInput(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public ByteBufferInput(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(bArr.length);
        allocateDirect.put(bArr);
        flipBuffer(allocateDirect);
        setBuffer(allocateDirect);
    }

    public ByteBufferInput(ByteBuffer byteBuffer) {
        setBuffer(byteBuffer);
    }

    public ByteBufferInput(InputStream inputStream) {
        this(4096);
        if (inputStream == null) {
            throw new IllegalArgumentException("inputStream cannot be null.");
        }
        this.inputStream = inputStream;
    }

    public ByteBufferInput(InputStream inputStream, int i) {
        this(i);
        if (inputStream == null) {
            throw new IllegalArgumentException("inputStream cannot be null.");
        }
        this.inputStream = inputStream;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public byte[] getBuffer() {
        throw new UnsupportedOperationException("This input does not used a byte[], see #getByteBuffer().");
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public void setBuffer(byte[] bArr) {
        throw new UnsupportedOperationException("This input does not used a byte[], see #setByteBuffer(ByteBuffer).");
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public void setBuffer(byte[] bArr, int i, int i2) {
        throw new UnsupportedOperationException("This input does not used a byte[], see #setByteBufferByteBuffer().");
    }

    public void setBuffer(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("buffer cannot be null.");
        }
        this.byteBuffer = byteBuffer;
        this.position = byteBuffer.position();
        this.limit = byteBuffer.limit();
        this.capacity = byteBuffer.capacity();
        this.total = 0L;
        this.inputStream = null;
    }

    public ByteBuffer getByteBuffer() {
        return this.byteBuffer;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        this.limit = 0;
        reset();
    }

    @Override // com.esotericsoftware.kryo.io.Input, java.io.InputStream, com.esotericsoftware.kryo.util.Pool.Poolable
    public void reset() {
        super.reset();
        setBufferPosition(this.byteBuffer, 0);
    }

    protected int fill(ByteBuffer byteBuffer, int i, int i2) {
        if (this.inputStream == null) {
            return -1;
        }
        try {
            if (this.tempBuffer == null) {
                this.tempBuffer = new byte[2048];
            }
            setBufferPosition(byteBuffer, i);
            int i3 = 0;
            while (true) {
                if (i2 <= 0) {
                    break;
                }
                int read = this.inputStream.read(this.tempBuffer, 0, Math.min(this.tempBuffer.length, i2));
                if (read == -1) {
                    if (i3 == 0) {
                        return -1;
                    }
                } else {
                    byteBuffer.put(this.tempBuffer, 0, read);
                    i2 -= read;
                    i3 += read;
                }
            }
            return i3;
        } catch (IOException e) {
            throw new KryoException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.esotericsoftware.kryo.io.Input
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
            int fill = fill(this.byteBuffer, this.limit, this.capacity - this.limit);
            if (fill == -1) {
                throw new KryoBufferUnderflowException("Buffer underflow.");
            }
            setBufferPosition(this.byteBuffer, this.position);
            int i4 = i3 + fill;
            i3 = i4;
            if (i4 >= i) {
                this.limit += fill;
                return i3;
            }
        }
        this.byteBuffer.compact();
        this.total += this.position;
        this.position = 0;
        while (true) {
            int fill2 = fill(this.byteBuffer, i3, this.capacity - i3);
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
        setBufferPosition(this.byteBuffer, 0);
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.esotericsoftware.kryo.io.Input
    public int optional(int i) {
        int i2;
        int i3 = this.limit - this.position;
        if (i3 >= i) {
            return i;
        }
        int min = Math.min(i, this.capacity);
        int fill = fill(this.byteBuffer, this.limit, this.capacity - this.limit);
        setBufferPosition(this.byteBuffer, this.position);
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
        this.byteBuffer.compact();
        this.total += this.position;
        this.position = 0;
        do {
            int fill2 = fill(this.byteBuffer, i5, this.capacity - i5);
            if (fill2 == -1) {
                break;
            }
            i2 = i5 + fill2;
            i5 = i2;
        } while (i2 < min);
        this.limit = i5;
        setBufferPosition(this.byteBuffer, 0);
        if (i5 == 0) {
            return -1;
        }
        return Math.min(i5, min);
    }

    @Override // com.esotericsoftware.kryo.io.Input, java.io.InputStream
    public int read() {
        if (optional(1) <= 0) {
            return -1;
        }
        this.position++;
        return this.byteBuffer.get() & 255;
    }

    @Override // com.esotericsoftware.kryo.io.Input, java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // com.esotericsoftware.kryo.io.Input, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
        int min = Math.min(this.limit - this.position, i2);
        while (true) {
            this.byteBuffer.get(bArr, i, min);
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

    @Override // com.esotericsoftware.kryo.io.Input
    public void setPosition(int i) {
        this.position = i;
        setBufferPosition(this.byteBuffer, i);
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public void setLimit(int i) {
        this.limit = i;
        setBufferLimit(this.byteBuffer, i);
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public void skip(int i) {
        super.skip(i);
        setBufferPosition(this.byteBuffer, this.position);
    }

    @Override // com.esotericsoftware.kryo.io.Input, java.io.InputStream
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

    @Override // com.esotericsoftware.kryo.io.Input, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.inputStream != null) {
            try {
                this.inputStream.close();
            } catch (IOException unused) {
            }
        }
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

    private void flipBuffer(Buffer buffer) {
        buffer.flip();
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public byte readByte() {
        if (this.position == this.limit) {
            require(1);
        }
        this.position++;
        return this.byteBuffer.get();
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public int readByteUnsigned() {
        if (this.position == this.limit) {
            require(1);
        }
        this.position++;
        return this.byteBuffer.get() & 255;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public byte[] readBytes(int i) {
        byte[] bArr = new byte[i];
        readBytes(bArr, 0, i);
        return bArr;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public void readBytes(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("bytes cannot be null.");
        }
        int min = Math.min(this.limit - this.position, i2);
        while (true) {
            this.byteBuffer.get(bArr, i, min);
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

    @Override // com.esotericsoftware.kryo.io.Input
    public int readInt() {
        require(4);
        this.position += 4;
        ByteBuffer byteBuffer = this.byteBuffer;
        return (byteBuffer.get() & 255) | ((byteBuffer.get() & 255) << 8) | ((byteBuffer.get() & 255) << 16) | ((byteBuffer.get() & 255) << 24);
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public int readVarInt(boolean z) {
        if (require(1) < 5) {
            return readVarInt_slow(z);
        }
        byte b2 = this.byteBuffer.get();
        int i = b2 & Byte.MAX_VALUE;
        if ((b2 & 128) != 0) {
            ByteBuffer byteBuffer = this.byteBuffer;
            byte b3 = byteBuffer.get();
            i |= (b3 & Byte.MAX_VALUE) << 7;
            if ((b3 & 128) != 0) {
                byte b4 = byteBuffer.get();
                i |= (b4 & Byte.MAX_VALUE) << 14;
                if ((b4 & 128) != 0) {
                    byte b5 = byteBuffer.get();
                    i |= (b5 & Byte.MAX_VALUE) << 21;
                    if ((b5 & 128) != 0) {
                        i |= (byteBuffer.get() & Byte.MAX_VALUE) << 28;
                    }
                }
            }
        }
        this.position = getBufferPosition(this.byteBuffer);
        return z ? i : (i >>> 1) ^ (-(i & 1));
    }

    private int readVarInt_slow(boolean z) {
        this.position++;
        byte b2 = this.byteBuffer.get();
        int i = b2 & Byte.MAX_VALUE;
        if ((b2 & 128) != 0) {
            if (this.position == this.limit) {
                require(1);
            }
            ByteBuffer byteBuffer = this.byteBuffer;
            this.position++;
            byte b3 = byteBuffer.get();
            i |= (b3 & Byte.MAX_VALUE) << 7;
            if ((b3 & 128) != 0) {
                if (this.position == this.limit) {
                    require(1);
                }
                this.position++;
                byte b4 = byteBuffer.get();
                i |= (b4 & Byte.MAX_VALUE) << 14;
                if ((b4 & 128) != 0) {
                    if (this.position == this.limit) {
                        require(1);
                    }
                    this.position++;
                    byte b5 = byteBuffer.get();
                    i |= (b5 & Byte.MAX_VALUE) << 21;
                    if ((b5 & 128) != 0) {
                        if (this.position == this.limit) {
                            require(1);
                        }
                        this.position++;
                        i |= (byteBuffer.get() & Byte.MAX_VALUE) << 28;
                    }
                }
            }
        }
        return z ? i : (i >>> 1) ^ (-(i & 1));
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public boolean canReadVarInt() {
        if (this.limit - this.position >= 5) {
            return true;
        }
        if (optional(5) <= 0) {
            return false;
        }
        int i = this.position;
        int i2 = this.limit;
        ByteBuffer byteBuffer = this.byteBuffer;
        int i3 = i + 1;
        if ((byteBuffer.get(i) & 128) == 0) {
            return true;
        }
        if (i3 == i2) {
            return false;
        }
        int i4 = i3 + 1;
        if ((byteBuffer.get(i3) & 128) == 0) {
            return true;
        }
        if (i4 == i2) {
            return false;
        }
        int i5 = i4 + 1;
        if ((byteBuffer.get(i4) & 128) == 0) {
            return true;
        }
        if (i5 == i2) {
            return false;
        }
        return (byteBuffer.get(i5) & 128) == 0 || i5 + 1 != i2;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public boolean readVarIntFlag() {
        if (this.position == this.limit) {
            require(1);
        }
        return (this.byteBuffer.get(this.position) & 128) != 0;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public int readVarIntFlag(boolean z) {
        if (require(1) < 5) {
            return readVarIntFlag_slow(z);
        }
        byte b2 = this.byteBuffer.get();
        int i = b2 & 63;
        if ((b2 & 64) != 0) {
            ByteBuffer byteBuffer = this.byteBuffer;
            byte b3 = byteBuffer.get();
            i |= (b3 & Byte.MAX_VALUE) << 6;
            if ((b3 & 128) != 0) {
                byte b4 = byteBuffer.get();
                i |= (b4 & Byte.MAX_VALUE) << 13;
                if ((b4 & 128) != 0) {
                    byte b5 = byteBuffer.get();
                    i |= (b5 & Byte.MAX_VALUE) << 20;
                    if ((b5 & 128) != 0) {
                        i |= (byteBuffer.get() & Byte.MAX_VALUE) << 27;
                    }
                }
            }
        }
        this.position = getBufferPosition(this.byteBuffer);
        return z ? i : (i >>> 1) ^ (-(i & 1));
    }

    private int readVarIntFlag_slow(boolean z) {
        this.position++;
        byte b2 = this.byteBuffer.get();
        int i = b2 & 63;
        if ((b2 & 64) != 0) {
            if (this.position == this.limit) {
                require(1);
            }
            this.position++;
            ByteBuffer byteBuffer = this.byteBuffer;
            byte b3 = byteBuffer.get();
            i |= (b3 & Byte.MAX_VALUE) << 6;
            if ((b3 & 128) != 0) {
                if (this.position == this.limit) {
                    require(1);
                }
                this.position++;
                byte b4 = byteBuffer.get();
                i |= (b4 & Byte.MAX_VALUE) << 13;
                if ((b4 & 128) != 0) {
                    if (this.position == this.limit) {
                        require(1);
                    }
                    this.position++;
                    byte b5 = byteBuffer.get();
                    i |= (b5 & Byte.MAX_VALUE) << 20;
                    if ((b5 & 128) != 0) {
                        if (this.position == this.limit) {
                            require(1);
                        }
                        this.position++;
                        i |= (byteBuffer.get() & Byte.MAX_VALUE) << 27;
                    }
                }
            }
        }
        return z ? i : (i >>> 1) ^ (-(i & 1));
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public long readLong() {
        require(8);
        this.position += 8;
        ByteBuffer byteBuffer = this.byteBuffer;
        return (byteBuffer.get() & 255) | ((byteBuffer.get() & 255) << 8) | ((byteBuffer.get() & 255) << 16) | ((byteBuffer.get() & 255) << 24) | ((byteBuffer.get() & 255) << 32) | ((byteBuffer.get() & 255) << 40) | ((byteBuffer.get() & 255) << 48) | (byteBuffer.get() << 56);
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public long readVarLong(boolean z) {
        if (require(1) < 9) {
            return readVarLong_slow(z);
        }
        byte b2 = this.byteBuffer.get();
        long j = b2 & Byte.MAX_VALUE;
        if ((b2 & 128) != 0) {
            ByteBuffer byteBuffer = this.byteBuffer;
            j |= (r0 & Byte.MAX_VALUE) << 7;
            if ((byteBuffer.get() & 128) != 0) {
                j |= (r0 & Byte.MAX_VALUE) << 14;
                if ((byteBuffer.get() & 128) != 0) {
                    j |= (r0 & Byte.MAX_VALUE) << 21;
                    if ((byteBuffer.get() & 128) != 0) {
                        j |= (r0 & Byte.MAX_VALUE) << 28;
                        if ((byteBuffer.get() & 128) != 0) {
                            j |= (r0 & Byte.MAX_VALUE) << 35;
                            if ((byteBuffer.get() & 128) != 0) {
                                j |= (r0 & Byte.MAX_VALUE) << 42;
                                if ((byteBuffer.get() & 128) != 0) {
                                    j |= (r0 & Byte.MAX_VALUE) << 49;
                                    if ((byteBuffer.get() & 128) != 0) {
                                        j |= byteBuffer.get() << 56;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        this.position = getBufferPosition(this.byteBuffer);
        return z ? j : (j >>> 1) ^ (-(j & 1));
    }

    private long readVarLong_slow(boolean z) {
        this.position++;
        byte b2 = this.byteBuffer.get();
        long j = b2 & Byte.MAX_VALUE;
        if ((b2 & 128) != 0) {
            if (this.position == this.limit) {
                require(1);
            }
            ByteBuffer byteBuffer = this.byteBuffer;
            this.position++;
            j |= (r0 & Byte.MAX_VALUE) << 7;
            if ((byteBuffer.get() & 128) != 0) {
                if (this.position == this.limit) {
                    require(1);
                }
                this.position++;
                j |= (r0 & Byte.MAX_VALUE) << 14;
                if ((byteBuffer.get() & 128) != 0) {
                    if (this.position == this.limit) {
                        require(1);
                    }
                    this.position++;
                    j |= (r0 & Byte.MAX_VALUE) << 21;
                    if ((byteBuffer.get() & 128) != 0) {
                        if (this.position == this.limit) {
                            require(1);
                        }
                        this.position++;
                        j |= (r0 & Byte.MAX_VALUE) << 28;
                        if ((byteBuffer.get() & 128) != 0) {
                            if (this.position == this.limit) {
                                require(1);
                            }
                            this.position++;
                            j |= (r0 & Byte.MAX_VALUE) << 35;
                            if ((byteBuffer.get() & 128) != 0) {
                                if (this.position == this.limit) {
                                    require(1);
                                }
                                this.position++;
                                j |= (r0 & Byte.MAX_VALUE) << 42;
                                if ((byteBuffer.get() & 128) != 0) {
                                    if (this.position == this.limit) {
                                        require(1);
                                    }
                                    this.position++;
                                    j |= (r0 & Byte.MAX_VALUE) << 49;
                                    if ((byteBuffer.get() & 128) != 0) {
                                        if (this.position == this.limit) {
                                            require(1);
                                        }
                                        this.position++;
                                        j |= byteBuffer.get() << 56;
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

    @Override // com.esotericsoftware.kryo.io.Input
    public boolean canReadVarLong() {
        if (this.limit - this.position >= 9) {
            return true;
        }
        if (optional(5) <= 0) {
            return false;
        }
        int i = this.position;
        int i2 = this.limit;
        ByteBuffer byteBuffer = this.byteBuffer;
        int i3 = i + 1;
        if ((byteBuffer.get(i) & 128) == 0) {
            return true;
        }
        if (i3 == i2) {
            return false;
        }
        int i4 = i3 + 1;
        if ((byteBuffer.get(i3) & 128) == 0) {
            return true;
        }
        if (i4 == i2) {
            return false;
        }
        int i5 = i4 + 1;
        if ((byteBuffer.get(i4) & 128) == 0) {
            return true;
        }
        if (i5 == i2) {
            return false;
        }
        int i6 = i5 + 1;
        if ((byteBuffer.get(i5) & 128) == 0) {
            return true;
        }
        if (i6 == i2) {
            return false;
        }
        int i7 = i6 + 1;
        if ((byteBuffer.get(i6) & 128) == 0) {
            return true;
        }
        if (i7 == i2) {
            return false;
        }
        int i8 = i7 + 1;
        if ((byteBuffer.get(i7) & 128) == 0) {
            return true;
        }
        if (i8 == i2) {
            return false;
        }
        int i9 = i8 + 1;
        if ((byteBuffer.get(i8) & 128) == 0) {
            return true;
        }
        if (i9 == i2) {
            return false;
        }
        return (byteBuffer.get(i9) & 128) == 0 || i9 + 1 != i2;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public float readFloat() {
        require(4);
        ByteBuffer byteBuffer = this.byteBuffer;
        this.position += 4;
        return Float.intBitsToFloat((byteBuffer.get() & 255) | ((byteBuffer.get() & 255) << 8) | ((byteBuffer.get() & 255) << 16) | ((byteBuffer.get() & 255) << 24));
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public double readDouble() {
        require(8);
        ByteBuffer byteBuffer = this.byteBuffer;
        this.position += 8;
        return Double.longBitsToDouble((byteBuffer.get() & 255) | ((byteBuffer.get() & 255) << 8) | ((byteBuffer.get() & 255) << 16) | ((byteBuffer.get() & 255) << 24) | ((byteBuffer.get() & 255) << 32) | ((byteBuffer.get() & 255) << 40) | ((byteBuffer.get() & 255) << 48) | (byteBuffer.get() << 56));
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public boolean readBoolean() {
        if (this.position == this.limit) {
            require(1);
        }
        this.position++;
        return this.byteBuffer.get() == 1;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public short readShort() {
        require(2);
        this.position += 2;
        return (short) ((this.byteBuffer.get() & 255) | ((this.byteBuffer.get() & 255) << 8));
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public int readShortUnsigned() {
        require(2);
        this.position += 2;
        return (this.byteBuffer.get() & 255) | ((this.byteBuffer.get() & 255) << 8);
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public char readChar() {
        require(2);
        this.position += 2;
        return (char) ((this.byteBuffer.get() & 255) | ((this.byteBuffer.get() & 255) << 8));
    }

    @Override // com.esotericsoftware.kryo.io.Input
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

    @Override // com.esotericsoftware.kryo.io.Input
    public StringBuilder readStringBuilder() {
        if (!readVarIntFlag()) {
            return new StringBuilder(readAsciiString());
        }
        int readVarIntFlag = readVarIntFlag(true);
        switch (readVarIntFlag) {
            case 0:
                return null;
            case 1:
                return new StringBuilder("");
            default:
                int i = readVarIntFlag - 1;
                readUtf8Chars(i);
                StringBuilder sb = new StringBuilder(i);
                sb.append(this.chars, 0, i);
                return sb;
        }
    }

    private void readUtf8Chars(int i) {
        byte b2;
        if (this.chars.length < i) {
            this.chars = new char[i];
        }
        char[] cArr = this.chars;
        ByteBuffer byteBuffer = this.byteBuffer;
        int i2 = 0;
        int min = Math.min(require(1), i);
        while (i2 < min && (b2 = byteBuffer.get()) >= 0) {
            int i3 = i2;
            i2++;
            cArr[i3] = (char) b2;
        }
        this.position += i2;
        if (i2 < i) {
            setBufferPosition(byteBuffer, this.position);
            readUtf8Chars_slow(i, i2);
        }
    }

    private void readUtf8Chars_slow(int i, int i2) {
        ByteBuffer byteBuffer = this.byteBuffer;
        char[] cArr = this.chars;
        while (i2 < i) {
            if (this.position == this.limit) {
                require(1);
            }
            this.position++;
            int i3 = byteBuffer.get() & 255;
            switch (i3 >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    cArr[i2] = (char) i3;
                    break;
                case 12:
                case 13:
                    if (this.position == this.limit) {
                        require(1);
                    }
                    this.position++;
                    cArr[i2] = (char) (((i3 & 31) << 6) | (byteBuffer.get() & 63));
                    break;
                case 14:
                    require(2);
                    this.position += 2;
                    cArr[i2] = (char) (((i3 & 15) << 12) | ((byteBuffer.get() & 63) << 6) | (byteBuffer.get() & 63));
                    break;
            }
            i2++;
        }
    }

    private String readAsciiString() {
        char[] cArr = this.chars;
        ByteBuffer byteBuffer = this.byteBuffer;
        int i = 0;
        int min = Math.min(cArr.length, this.limit - this.position);
        while (i < min) {
            byte b2 = byteBuffer.get();
            if ((b2 & 128) == 128) {
                this.position = getBufferPosition(byteBuffer);
                cArr[i] = (char) (b2 & Byte.MAX_VALUE);
                return new String(cArr, 0, i + 1);
            }
            cArr[i] = (char) b2;
            i++;
        }
        this.position = getBufferPosition(byteBuffer);
        return readAscii_slow(i);
    }

    private String readAscii_slow(int i) {
        char[] cArr = this.chars;
        ByteBuffer byteBuffer = this.byteBuffer;
        while (true) {
            if (this.position == this.limit) {
                require(1);
            }
            this.position++;
            byte b2 = byteBuffer.get();
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
            int i2 = i;
            i++;
            cArr[i2] = (char) b2;
        }
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public int[] readInts(int i) {
        int[] iArr = new int[i];
        if (optional(i << 2) == (i << 2)) {
            ByteBuffer byteBuffer = this.byteBuffer;
            for (int i2 = 0; i2 < i; i2++) {
                iArr[i2] = (byteBuffer.get() & 255) | ((byteBuffer.get() & 255) << 8) | ((byteBuffer.get() & 255) << 16) | ((byteBuffer.get() & 255) << 24);
            }
            this.position = getBufferPosition(byteBuffer);
        } else {
            for (int i3 = 0; i3 < i; i3++) {
                iArr[i3] = readInt();
            }
        }
        return iArr;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public long[] readLongs(int i) {
        long[] jArr = new long[i];
        if (optional(i << 3) == (i << 3)) {
            ByteBuffer byteBuffer = this.byteBuffer;
            for (int i2 = 0; i2 < i; i2++) {
                jArr[i2] = (byteBuffer.get() & 255) | ((byteBuffer.get() & 255) << 8) | ((byteBuffer.get() & 255) << 16) | ((byteBuffer.get() & 255) << 24) | ((byteBuffer.get() & 255) << 32) | ((byteBuffer.get() & 255) << 40) | ((byteBuffer.get() & 255) << 48) | (byteBuffer.get() << 56);
            }
            this.position = getBufferPosition(byteBuffer);
        } else {
            for (int i3 = 0; i3 < i; i3++) {
                jArr[i3] = readLong();
            }
        }
        return jArr;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public float[] readFloats(int i) {
        float[] fArr = new float[i];
        if (optional(i << 2) == (i << 2)) {
            ByteBuffer byteBuffer = this.byteBuffer;
            for (int i2 = 0; i2 < i; i2++) {
                fArr[i2] = Float.intBitsToFloat((byteBuffer.get() & 255) | ((byteBuffer.get() & 255) << 8) | ((byteBuffer.get() & 255) << 16) | ((byteBuffer.get() & 255) << 24));
            }
            this.position = getBufferPosition(byteBuffer);
        } else {
            for (int i3 = 0; i3 < i; i3++) {
                fArr[i3] = readFloat();
            }
        }
        return fArr;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public double[] readDoubles(int i) {
        double[] dArr = new double[i];
        if (optional(i << 3) == (i << 3)) {
            ByteBuffer byteBuffer = this.byteBuffer;
            for (int i2 = 0; i2 < i; i2++) {
                dArr[i2] = Double.longBitsToDouble((byteBuffer.get() & 255) | ((byteBuffer.get() & 255) << 8) | ((byteBuffer.get() & 255) << 16) | ((byteBuffer.get() & 255) << 24) | ((byteBuffer.get() & 255) << 32) | ((byteBuffer.get() & 255) << 40) | ((byteBuffer.get() & 255) << 48) | (byteBuffer.get() << 56));
            }
            this.position = getBufferPosition(byteBuffer);
        } else {
            for (int i3 = 0; i3 < i; i3++) {
                dArr[i3] = readDouble();
            }
        }
        return dArr;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public short[] readShorts(int i) {
        short[] sArr = new short[i];
        if (optional(i << 1) == (i << 1)) {
            ByteBuffer byteBuffer = this.byteBuffer;
            for (int i2 = 0; i2 < i; i2++) {
                sArr[i2] = (short) ((byteBuffer.get() & 255) | ((byteBuffer.get() & 255) << 8));
            }
            this.position = getBufferPosition(byteBuffer);
        } else {
            for (int i3 = 0; i3 < i; i3++) {
                sArr[i3] = readShort();
            }
        }
        return sArr;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public char[] readChars(int i) {
        char[] cArr = new char[i];
        if (optional(i << 1) == (i << 1)) {
            ByteBuffer byteBuffer = this.byteBuffer;
            for (int i2 = 0; i2 < i; i2++) {
                cArr[i2] = (char) ((byteBuffer.get() & 255) | ((byteBuffer.get() & 255) << 8));
            }
            this.position = getBufferPosition(byteBuffer);
        } else {
            for (int i3 = 0; i3 < i; i3++) {
                cArr[i3] = readChar();
            }
        }
        return cArr;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public boolean[] readBooleans(int i) {
        boolean[] zArr = new boolean[i];
        if (optional(i) == i) {
            ByteBuffer byteBuffer = this.byteBuffer;
            for (int i2 = 0; i2 < i; i2++) {
                zArr[i2] = byteBuffer.get() != 0;
            }
            this.position = getBufferPosition(byteBuffer);
        } else {
            for (int i3 = 0; i3 < i; i3++) {
                zArr[i3] = readBoolean();
            }
        }
        return zArr;
    }
}
