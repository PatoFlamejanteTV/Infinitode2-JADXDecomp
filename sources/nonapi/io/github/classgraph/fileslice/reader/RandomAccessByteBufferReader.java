package nonapi.io.github.classgraph.fileslice.reader;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;
import nonapi.io.github.classgraph.utils.StringUtils;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/fileslice/reader/RandomAccessByteBufferReader.class */
public class RandomAccessByteBufferReader implements RandomAccessReader {
    private final ByteBuffer byteBuffer;
    private final int sliceStartPos;
    private final int sliceLength;

    public RandomAccessByteBufferReader(ByteBuffer byteBuffer, long j, long j2) {
        this.byteBuffer = byteBuffer.duplicate();
        this.byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        this.sliceStartPos = (int) j;
        this.sliceLength = (int) j2;
        this.byteBuffer.position(this.sliceStartPos);
        this.byteBuffer.limit(this.sliceStartPos + this.sliceLength);
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public int read(long j, byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        if (j < 0 || i2 < 0 || i2 > this.sliceLength - j) {
            throw new IOException("Read index out of bounds");
        }
        try {
            int max = Math.max(Math.min(i2, bArr.length - i), 0);
            if (max == 0) {
                return -1;
            }
            this.byteBuffer.position(this.sliceStartPos + ((int) j));
            this.byteBuffer.get(bArr, i, max);
            this.byteBuffer.position(this.sliceStartPos);
            return max;
        } catch (IndexOutOfBoundsException unused) {
            throw new IOException("Read index out of bounds");
        }
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public int read(long j, ByteBuffer byteBuffer, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        if (j < 0 || i2 < 0 || i2 > this.sliceLength - j) {
            throw new IOException("Read index out of bounds");
        }
        try {
            int max = Math.max(Math.min(i2, byteBuffer.capacity() - i), 0);
            if (max == 0) {
                return -1;
            }
            this.byteBuffer.position((int) (this.sliceStartPos + j));
            byteBuffer.position(i);
            byteBuffer.limit(i + max);
            byteBuffer.put(this.byteBuffer);
            this.byteBuffer.limit(this.sliceStartPos + this.sliceLength);
            this.byteBuffer.position(this.sliceStartPos);
            return max;
        } catch (IndexOutOfBoundsException | BufferUnderflowException | ReadOnlyBufferException unused) {
            throw new IOException("Read index out of bounds");
        }
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public byte readByte(long j) {
        return this.byteBuffer.get((int) (this.sliceStartPos + j));
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public int readUnsignedByte(long j) {
        return this.byteBuffer.get((int) (this.sliceStartPos + j)) & 255;
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public int readUnsignedShort(long j) {
        return this.byteBuffer.getShort((int) (this.sliceStartPos + j)) & 255;
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public short readShort(long j) {
        return (short) readUnsignedShort(j);
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public int readInt(long j) {
        return this.byteBuffer.getInt((int) (this.sliceStartPos + j));
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public long readUnsignedInt(long j) {
        return readInt(j) & 4294967295L;
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public long readLong(long j) {
        return this.byteBuffer.getLong((int) (this.sliceStartPos + j));
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public String readString(long j, int i, boolean z, boolean z2) {
        int i2 = (int) (this.sliceStartPos + j);
        byte[] bArr = new byte[i];
        if (read(j, bArr, 0, i) < i) {
            throw new IOException("Premature EOF while reading string");
        }
        return StringUtils.readString(bArr, i2, i, z, z2);
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public String readString(long j, int i) {
        return readString(j, i, false, false);
    }
}
