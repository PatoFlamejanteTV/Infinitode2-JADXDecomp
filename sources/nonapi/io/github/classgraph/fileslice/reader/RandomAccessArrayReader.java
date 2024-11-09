package nonapi.io.github.classgraph.fileslice.reader;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import nonapi.io.github.classgraph.utils.StringUtils;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/fileslice/reader/RandomAccessArrayReader.class */
public class RandomAccessArrayReader implements RandomAccessReader {
    private final byte[] arr;
    private final int sliceStartPos;
    private final int sliceLength;

    public RandomAccessArrayReader(byte[] bArr, int i, int i2) {
        this.arr = bArr;
        this.sliceStartPos = i;
        this.sliceLength = i2;
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
            System.arraycopy(this.arr, (int) (this.sliceStartPos + j), bArr, i, max);
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
            byteBuffer.position(i);
            byteBuffer.limit(i + max);
            byteBuffer.put(this.arr, (int) (this.sliceStartPos + j), max);
            return max;
        } catch (IndexOutOfBoundsException | BufferUnderflowException | ReadOnlyBufferException unused) {
            throw new IOException("Read index out of bounds");
        }
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public byte readByte(long j) {
        return this.arr[this.sliceStartPos + ((int) j)];
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public int readUnsignedByte(long j) {
        return this.arr[this.sliceStartPos + ((int) j)] & 255;
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public short readShort(long j) {
        return (short) readUnsignedShort(j);
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public int readUnsignedShort(long j) {
        int i = this.sliceStartPos + ((int) j);
        return ((this.arr[i + 1] & 255) << 8) | (this.arr[i] & 255);
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public int readInt(long j) {
        int i = this.sliceStartPos + ((int) j);
        return ((this.arr[i + 3] & 255) << 24) | ((this.arr[i + 2] & 255) << 16) | ((this.arr[i + 1] & 255) << 8) | (this.arr[i] & 255);
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public long readUnsignedInt(long j) {
        return readInt(j) & 4294967295L;
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public long readLong(long j) {
        int i = this.sliceStartPos + ((int) j);
        return ((this.arr[i + 7] & 255) << 56) | ((this.arr[i + 6] & 255) << 48) | ((this.arr[i + 5] & 255) << 40) | ((this.arr[i + 4] & 255) << 32) | ((this.arr[i + 3] & 255) << 24) | ((this.arr[i + 2] & 255) << 16) | ((this.arr[i + 1] & 255) << 8) | (this.arr[i] & 255);
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public String readString(long j, int i, boolean z, boolean z2) {
        return StringUtils.readString(this.arr, this.sliceStartPos + ((int) j), i, z, z2);
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public String readString(long j, int i) {
        return readString(j, i, false, false);
    }
}
