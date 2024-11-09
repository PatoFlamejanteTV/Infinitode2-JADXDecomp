package nonapi.io.github.classgraph.fileslice.reader;

import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/fileslice/reader/RandomAccessReader.class */
public interface RandomAccessReader {
    int read(long j, ByteBuffer byteBuffer, int i, int i2);

    int read(long j, byte[] bArr, int i, int i2);

    byte readByte(long j);

    int readUnsignedByte(long j);

    short readShort(long j);

    int readUnsignedShort(long j);

    int readInt(long j);

    long readUnsignedInt(long j);

    long readLong(long j);

    String readString(long j, int i, boolean z, boolean z2);

    String readString(long j, int i);
}
