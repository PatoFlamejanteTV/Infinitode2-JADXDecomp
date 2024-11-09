package nonapi.io.github.classgraph.fileslice;

import io.github.classgraph.Resource;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;
import nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler;
import nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/fileslice/Slice.class */
public abstract class Slice implements Closeable {
    protected final NestedJarHandler nestedJarHandler;
    protected final Slice parentSlice;
    public final long sliceStartPos;
    public long sliceLength;
    public final boolean isDeflatedZipEntry;
    public final long inflatedLengthHint;
    private int hashCode;

    public abstract Slice slice(long j, long j2, boolean z, long j3);

    public abstract RandomAccessReader randomAccessReader();

    public abstract byte[] load();

    /* JADX INFO: Access modifiers changed from: protected */
    public Slice(Slice slice, long j, long j2, boolean z, long j3, NestedJarHandler nestedJarHandler) {
        this.parentSlice = slice;
        long j4 = slice == null ? 0L : slice.sliceStartPos;
        this.sliceStartPos = j4 + j;
        this.sliceLength = j2;
        this.isDeflatedZipEntry = z;
        this.inflatedLengthHint = j3;
        this.nestedJarHandler = nestedJarHandler;
        if (this.sliceStartPos < 0) {
            throw new IllegalArgumentException("Invalid startPos");
        }
        if (j2 < 0) {
            throw new IllegalArgumentException("Invalid length");
        }
        if (slice != null) {
            if (this.sliceStartPos < j4 || this.sliceStartPos + j2 > j4 + slice.sliceLength) {
                throw new IllegalArgumentException("Child slice is not completely contained within parent slice");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Slice(long j, boolean z, long j2, NestedJarHandler nestedJarHandler) {
        this(null, 0L, j, z, j2, nestedJarHandler);
    }

    public InputStream open() {
        return open(null);
    }

    public InputStream open(final Resource resource) {
        InputStream inputStream = new InputStream() { // from class: nonapi.io.github.classgraph.fileslice.Slice.1
            RandomAccessReader randomAccessReader;
            private long currOff;
            private long markOff;
            private final byte[] byteBuf = new byte[1];
            private final AtomicBoolean closed = new AtomicBoolean();

            {
                this.randomAccessReader = Slice.this.randomAccessReader();
            }

            @Override // java.io.InputStream
            public int read() {
                if (this.closed.get()) {
                    throw new IOException("Already closed");
                }
                return read(this.byteBuf, 0, 1);
            }

            @Override // java.io.InputStream
            public int read(byte[] bArr, int i, int i2) {
                if (this.closed.get()) {
                    throw new IOException("Already closed");
                }
                if (i2 == 0) {
                    return 0;
                }
                int min = Math.min(i2, available());
                if (min > 0) {
                    int read = this.randomAccessReader.read(this.currOff, bArr, i, min);
                    if (read > 0) {
                        this.currOff += read;
                    }
                    return read;
                }
                return -1;
            }

            @Override // java.io.InputStream
            public long skip(long j) {
                if (this.closed.get()) {
                    throw new IOException("Already closed");
                }
                long min = Math.min(this.currOff + j, Slice.this.sliceLength);
                long j2 = min - this.currOff;
                this.currOff = min;
                return j2;
            }

            @Override // java.io.InputStream
            public int available() {
                return (int) Math.min(Math.max(Slice.this.sliceLength - this.currOff, 0L), 2147483639L);
            }

            @Override // java.io.InputStream
            public synchronized void mark(int i) {
                this.markOff = this.currOff;
            }

            @Override // java.io.InputStream
            public synchronized void reset() {
                this.currOff = this.markOff;
            }

            @Override // java.io.InputStream
            public boolean markSupported() {
                return true;
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                if (resource != null) {
                    try {
                        resource.close();
                    } catch (Exception unused) {
                    }
                }
                this.closed.getAndSet(true);
            }
        };
        return this.isDeflatedZipEntry ? this.nestedJarHandler.openInflaterInputStream(inputStream) : inputStream;
    }

    public String loadAsString() {
        return new String(load(), StandardCharsets.UTF_8);
    }

    public ByteBuffer read() {
        return ByteBuffer.wrap(load());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = ((this.parentSlice == null ? 1 : this.parentSlice.hashCode()) ^ (((int) this.sliceStartPos) * 7)) ^ (((int) this.sliceLength) * 15);
            if (this.hashCode == 0) {
                this.hashCode = 1;
            }
        }
        return this.hashCode;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Slice)) {
            return false;
        }
        Slice slice = (Slice) obj;
        return this.parentSlice == slice.parentSlice && this.sliceStartPos == slice.sliceStartPos && this.sliceLength == slice.sliceLength;
    }
}
