package nonapi.io.github.classgraph.fileslice.reader;

import io.github.classgraph.Resource;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.util.Arrays;
import nonapi.io.github.classgraph.fileslice.ArraySlice;
import nonapi.io.github.classgraph.fileslice.Slice;
import nonapi.io.github.classgraph.utils.StringUtils;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/fileslice/reader/ClassfileReader.class */
public class ClassfileReader implements Closeable, RandomAccessReader, SequentialReader {
    private Resource resourceToClose;
    private InputStream inflaterInputStream;
    private RandomAccessReader randomAccessReader;
    private byte[] arr;
    private int arrUsed;
    private int currIdx;
    private int classfileLengthHint;
    private static final int INITIAL_BUF_SIZE = 16384;
    private static final int BUF_CHUNK_SIZE = 8184;

    public ClassfileReader(Slice slice, Resource resource) {
        this.classfileLengthHint = -1;
        this.classfileLengthHint = (int) slice.sliceLength;
        this.resourceToClose = resource;
        if (slice.isDeflatedZipEntry) {
            this.inflaterInputStream = slice.open();
            this.arr = new byte[16384];
            this.classfileLengthHint = (int) Math.min(slice.inflatedLengthHint, 2147483639L);
        } else {
            if (slice instanceof ArraySlice) {
                ArraySlice arraySlice = (ArraySlice) slice;
                if (arraySlice.sliceStartPos == 0 && arraySlice.sliceLength == arraySlice.arr.length) {
                    this.arr = arraySlice.arr;
                } else {
                    this.arr = Arrays.copyOfRange(arraySlice.arr, (int) arraySlice.sliceStartPos, (int) (arraySlice.sliceStartPos + arraySlice.sliceLength));
                }
                this.arrUsed = this.arr.length;
                this.classfileLengthHint = this.arr.length;
                return;
            }
            this.randomAccessReader = slice.randomAccessReader();
            this.arr = new byte[16384];
            this.classfileLengthHint = (int) Math.min(slice.sliceLength, 2147483639L);
        }
    }

    public ClassfileReader(InputStream inputStream, Resource resource) {
        this.classfileLengthHint = -1;
        this.inflaterInputStream = inputStream;
        this.arr = new byte[16384];
        this.resourceToClose = resource;
    }

    public int currPos() {
        return this.currIdx;
    }

    public byte[] buf() {
        return this.arr;
    }

    private void readTo(int i) {
        long j;
        int i2 = this.classfileLengthHint == -1 ? 2147483639 : this.classfileLengthHint;
        if (this.inflaterInputStream == null && this.randomAccessReader == null) {
            throw new IOException("Tried to read past end of fixed array buffer");
        }
        if (i > 2147483639 || i < 0 || this.arrUsed == i2) {
            throw new IOException("Hit 2GB limit while trying to grow buffer array");
        }
        int min = (int) Math.min(Math.max(i, this.arrUsed + BUF_CHUNK_SIZE), i2);
        long length = this.arr.length;
        while (true) {
            j = length;
            if (j >= min) {
                break;
            } else {
                length = Math.min(min, j << 1);
            }
        }
        if (j > 2147483639) {
            throw new IOException("Hit 2GB limit while trying to grow buffer array");
        }
        this.arr = Arrays.copyOf(this.arr, (int) Math.min(j, i2));
        int length2 = this.arr.length - this.arrUsed;
        if (this.inflaterInputStream != null) {
            int read = this.inflaterInputStream.read(this.arr, this.arrUsed, length2);
            if (read > 0) {
                this.arrUsed += read;
            }
        } else {
            int read2 = this.randomAccessReader.read(this.arrUsed, this.arr, this.arrUsed, Math.min(length2, i2 - this.arrUsed));
            if (read2 > 0) {
                this.arrUsed += read2;
            }
        }
        if (this.arrUsed < i) {
            throw new IOException("Buffer underflow");
        }
    }

    public void bufferTo(int i) {
        if (i > this.arrUsed) {
            readTo(i);
        }
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public int read(long j, byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        int i3 = (int) j;
        if (i3 + i2 > this.arrUsed) {
            readTo(i3 + i2);
        }
        int max = Math.max(Math.min(i2, bArr.length - i), 0);
        if (max != 0) {
            try {
                System.arraycopy(this.arr, i3, bArr, i, max);
                return max;
            } catch (IndexOutOfBoundsException unused) {
                throw new IOException("Read index out of bounds");
            }
        }
        return -1;
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public int read(long j, ByteBuffer byteBuffer, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        int i3 = (int) j;
        if (i3 + i2 > this.arrUsed) {
            readTo(i3 + i2);
        }
        int max = Math.max(Math.min(i2, byteBuffer.capacity() - i), 0);
        if (max == 0) {
            return -1;
        }
        try {
            byteBuffer.position(i);
            byteBuffer.limit(i + max);
            byteBuffer.put(this.arr, i3, max);
            return max;
        } catch (IndexOutOfBoundsException | BufferUnderflowException | ReadOnlyBufferException unused) {
            throw new IOException("Read index out of bounds");
        }
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public byte readByte(long j) {
        int i = (int) j;
        if (i + 1 > this.arrUsed) {
            readTo(i + 1);
        }
        return this.arr[i];
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public int readUnsignedByte(long j) {
        int i = (int) j;
        if (i + 1 > this.arrUsed) {
            readTo(i + 1);
        }
        return this.arr[i] & 255;
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public short readShort(long j) {
        return (short) readUnsignedShort(j);
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public int readUnsignedShort(long j) {
        int i = (int) j;
        if (i + 2 > this.arrUsed) {
            readTo(i + 2);
        }
        return ((this.arr[i] & 255) << 8) | (this.arr[i + 1] & 255);
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public int readInt(long j) {
        int i = (int) j;
        if (i + 4 > this.arrUsed) {
            readTo(i + 4);
        }
        return ((this.arr[i] & 255) << 24) | ((this.arr[i + 1] & 255) << 16) | ((this.arr[i + 2] & 255) << 8) | (this.arr[i + 3] & 255);
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public long readUnsignedInt(long j) {
        return readInt(j) & 4294967295L;
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public long readLong(long j) {
        int i = (int) j;
        if (i + 8 > this.arrUsed) {
            readTo(i + 8);
        }
        return ((this.arr[i] & 255) << 56) | ((this.arr[i + 1] & 255) << 48) | ((this.arr[i + 2] & 255) << 40) | ((this.arr[i + 3] & 255) << 32) | ((this.arr[i + 4] & 255) << 24) | ((this.arr[i + 5] & 255) << 16) | ((this.arr[i + 6] & 255) << 8) | (this.arr[i + 7] & 255);
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.SequentialReader
    public byte readByte() {
        byte readByte = readByte(this.currIdx);
        this.currIdx++;
        return readByte;
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.SequentialReader
    public int readUnsignedByte() {
        int readUnsignedByte = readUnsignedByte(this.currIdx);
        this.currIdx++;
        return readUnsignedByte;
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.SequentialReader
    public short readShort() {
        short readShort = readShort(this.currIdx);
        this.currIdx += 2;
        return readShort;
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.SequentialReader
    public int readUnsignedShort() {
        int readUnsignedShort = readUnsignedShort(this.currIdx);
        this.currIdx += 2;
        return readUnsignedShort;
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.SequentialReader
    public int readInt() {
        int readInt = readInt(this.currIdx);
        this.currIdx += 4;
        return readInt;
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.SequentialReader
    public long readUnsignedInt() {
        long readUnsignedInt = readUnsignedInt(this.currIdx);
        this.currIdx += 4;
        return readUnsignedInt;
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.SequentialReader
    public long readLong() {
        long readLong = readLong(this.currIdx);
        this.currIdx += 8;
        return readLong;
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.SequentialReader
    public void skip(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Tried to skip a negative number of bytes");
        }
        int i2 = this.currIdx;
        if (i2 + i > this.arrUsed) {
            readTo(i2 + i);
        }
        this.currIdx += i;
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public String readString(long j, int i, boolean z, boolean z2) {
        int i2 = (int) j;
        if (i2 + i > this.arrUsed) {
            readTo(i2 + i);
        }
        return StringUtils.readString(this.arr, i2, i, z, z2);
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.SequentialReader
    public String readString(int i, boolean z, boolean z2) {
        String readString = StringUtils.readString(this.arr, this.currIdx, i, z, z2);
        this.currIdx += i;
        return readString;
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public String readString(long j, int i) {
        return readString(j, i, false, false);
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.SequentialReader
    public String readString(int i) {
        return readString(i, false, false);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            if (this.inflaterInputStream != null) {
                this.inflaterInputStream.close();
                this.inflaterInputStream = null;
            }
            if (this.resourceToClose != null) {
                this.resourceToClose.close();
                this.resourceToClose = null;
            }
        } catch (Exception unused) {
        }
    }
}
