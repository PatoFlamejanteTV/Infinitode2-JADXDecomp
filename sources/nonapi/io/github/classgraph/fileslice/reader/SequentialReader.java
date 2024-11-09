package nonapi.io.github.classgraph.fileslice.reader;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/fileslice/reader/SequentialReader.class */
public interface SequentialReader {
    byte readByte();

    int readUnsignedByte();

    short readShort();

    int readUnsignedShort();

    int readInt();

    long readUnsignedInt();

    long readLong();

    void skip(int i);

    String readString(int i, boolean z, boolean z2);

    String readString(int i);
}
