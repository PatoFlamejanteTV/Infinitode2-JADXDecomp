package nonapi.io.github.classgraph.fileslice.reader;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import nonapi.io.github.classgraph.utils.StringUtils;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/fileslice/reader/RandomAccessFileChannelReader.class */
public class RandomAccessFileChannelReader implements RandomAccessReader {
    private final FileChannel fileChannel;
    private final long sliceStartPos;
    private final long sliceLength;
    private ByteBuffer reusableByteBuffer;
    private final byte[] scratchArr = new byte[8];
    private final ByteBuffer scratchByteBuf = ByteBuffer.wrap(this.scratchArr);
    private byte[] utf8Bytes;

    public RandomAccessFileChannelReader(FileChannel fileChannel, long j, long j2) {
        this.fileChannel = fileChannel;
        this.sliceStartPos = j;
        this.sliceLength = j2;
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public int read(long j, ByteBuffer byteBuffer, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        if (j >= 0 && i2 >= 0) {
            try {
                if (i2 <= this.sliceLength - j) {
                    long j2 = this.sliceStartPos + j;
                    byteBuffer.position(i);
                    byteBuffer.limit(i + i2);
                    int read = this.fileChannel.read(byteBuffer, j2);
                    if (read == 0) {
                        return -1;
                    }
                    return read;
                }
            } catch (IndexOutOfBoundsException | BufferUnderflowException unused) {
                throw new IOException("Read index out of bounds");
            }
        }
        throw new IOException("Read index out of bounds");
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public int read(long j, byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        if (j >= 0 && i2 >= 0) {
            try {
                if (i2 <= this.sliceLength - j) {
                    if (this.reusableByteBuffer == null || this.reusableByteBuffer.array() != bArr) {
                        this.reusableByteBuffer = ByteBuffer.wrap(bArr);
                    }
                    return read(j, this.reusableByteBuffer, i, i2);
                }
            } catch (IndexOutOfBoundsException | BufferUnderflowException unused) {
                throw new IOException("Read index out of bounds");
            }
        }
        throw new IOException("Read index out of bounds");
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public byte readByte(long j) {
        if (read(j, this.scratchByteBuf, 0, 1) <= 0) {
            throw new IOException("Premature EOF");
        }
        return this.scratchArr[0];
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public int readUnsignedByte(long j) {
        if (read(j, this.scratchByteBuf, 0, 1) <= 0) {
            throw new IOException("Premature EOF");
        }
        return this.scratchArr[0] & 255;
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public short readShort(long j) {
        return (short) readUnsignedShort(j);
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public int readUnsignedShort(long j) {
        if (read(j, this.scratchByteBuf, 0, 2) < 2) {
            throw new IOException("Premature EOF");
        }
        return ((this.scratchArr[1] & 255) << 8) | (this.scratchArr[0] & 255);
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public int readInt(long j) {
        if (read(j, this.scratchByteBuf, 0, 4) < 4) {
            throw new IOException("Premature EOF");
        }
        return ((this.scratchArr[3] & 255) << 24) | ((this.scratchArr[2] & 255) << 16) | ((this.scratchArr[1] & 255) << 8) | (this.scratchArr[0] & 255);
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public long readUnsignedInt(long j) {
        return readInt(j) & 4294967295L;
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public long readLong(long j) {
        if (read(j, this.scratchByteBuf, 0, 8) < 8) {
            throw new IOException("Premature EOF");
        }
        return ((this.scratchArr[7] & 255) << 56) | ((this.scratchArr[6] & 255) << 48) | ((this.scratchArr[5] & 255) << 40) | ((this.scratchArr[4] & 255) << 32) | ((this.scratchArr[3] & 255) << 24) | ((this.scratchArr[2] & 255) << 16) | ((this.scratchArr[1] & 255) << 8) | (this.scratchArr[0] & 255);
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public String readString(long j, int i, boolean z, boolean z2) {
        if (this.utf8Bytes == null || this.utf8Bytes.length < i) {
            this.utf8Bytes = new byte[i];
        }
        if (read(j, this.utf8Bytes, 0, i) < i) {
            throw new IOException("Premature EOF");
        }
        return StringUtils.readString(this.utf8Bytes, 0, i, z, z2);
    }

    @Override // nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader
    public String readString(long j, int i) {
        return readString(j, i, false, false);
    }
}
