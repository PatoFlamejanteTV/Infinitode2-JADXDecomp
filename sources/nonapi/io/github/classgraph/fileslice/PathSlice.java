package nonapi.io.github.classgraph.fileslice;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.atomic.AtomicBoolean;
import nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler;
import nonapi.io.github.classgraph.fileslice.reader.RandomAccessFileChannelReader;
import nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader;
import nonapi.io.github.classgraph.utils.FileUtils;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/fileslice/PathSlice.class */
public class PathSlice extends Slice {
    public final Path path;
    private final long fileLength;
    private FileChannel fileChannel;
    private final boolean isTopLevelFileSlice;
    private final AtomicBoolean isClosed;

    private PathSlice(PathSlice pathSlice, long j, long j2, boolean z, long j3, NestedJarHandler nestedJarHandler) {
        super(pathSlice, j, j2, z, j3, nestedJarHandler);
        this.isClosed = new AtomicBoolean();
        this.path = pathSlice.path;
        this.fileChannel = pathSlice.fileChannel;
        this.fileLength = pathSlice.fileLength;
        this.isTopLevelFileSlice = false;
    }

    public PathSlice(Path path, boolean z, long j, NestedJarHandler nestedJarHandler) {
        this(path, z, j, nestedJarHandler, true);
    }

    public PathSlice(Path path, boolean z, long j, NestedJarHandler nestedJarHandler, boolean z2) {
        super(0L, z, j, nestedJarHandler);
        this.isClosed = new AtomicBoolean();
        if (z2) {
            FileUtils.checkCanReadAndIsFile(path);
        }
        this.path = path;
        this.fileChannel = FileChannel.open(path, StandardOpenOption.READ);
        this.fileLength = this.fileChannel.size();
        this.isTopLevelFileSlice = true;
        this.sliceLength = this.fileLength;
        nestedJarHandler.markSliceAsOpen(this);
    }

    public PathSlice(Path path, NestedJarHandler nestedJarHandler) {
        this(path, false, 0L, nestedJarHandler);
    }

    @Override // nonapi.io.github.classgraph.fileslice.Slice
    public Slice slice(long j, long j2, boolean z, long j3) {
        if (this.isDeflatedZipEntry) {
            throw new IllegalArgumentException("Cannot slice a deflated zip entry");
        }
        return new PathSlice(this, j, j2, z, j3, this.nestedJarHandler);
    }

    @Override // nonapi.io.github.classgraph.fileslice.Slice
    public RandomAccessReader randomAccessReader() {
        return new RandomAccessFileChannelReader(this.fileChannel, this.sliceStartPos, this.sliceLength);
    }

    @Override // nonapi.io.github.classgraph.fileslice.Slice
    public byte[] load() {
        if (!this.isDeflatedZipEntry) {
            if (this.sliceLength > 2147483639) {
                throw new IOException("File is larger than 2GB");
            }
            RandomAccessReader randomAccessReader = randomAccessReader();
            byte[] bArr = new byte[(int) this.sliceLength];
            if (randomAccessReader.read(0L, bArr, 0, bArr.length) < bArr.length) {
                throw new IOException("File is truncated");
            }
            return bArr;
        }
        if (this.inflatedLengthHint > 2147483639) {
            throw new IOException("Uncompressed size is larger than 2GB");
        }
        InputStream open = open();
        try {
            try {
                byte[] readAllBytesAsArray = NestedJarHandler.readAllBytesAsArray(open, this.inflatedLengthHint);
                if (open != null) {
                    if (r9 != null) {
                        try {
                            open.close();
                        } catch (Throwable th) {
                            r9.addSuppressed(th);
                        }
                    } else {
                        open.close();
                    }
                }
                return readAllBytesAsArray;
            } catch (Throwable th2) {
                if (open != null) {
                    if (r9 != null) {
                        try {
                            open.close();
                        } catch (Throwable th3) {
                            r9.addSuppressed(th3);
                        }
                    } else {
                        open.close();
                    }
                }
                throw th2;
            }
        } finally {
            r9 = null;
        }
    }

    @Override // nonapi.io.github.classgraph.fileslice.Slice
    public ByteBuffer read() {
        if (this.isDeflatedZipEntry) {
            if (this.inflatedLengthHint > 2147483639) {
                throw new IOException("Uncompressed size is larger than 2GB");
            }
            return ByteBuffer.wrap(load());
        }
        if (this.sliceLength > 2147483639) {
            throw new IOException("File is larger than 2GB");
        }
        return ByteBuffer.wrap(load());
    }

    @Override // nonapi.io.github.classgraph.fileslice.Slice
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // nonapi.io.github.classgraph.fileslice.Slice
    public int hashCode() {
        return super.hashCode();
    }

    @Override // nonapi.io.github.classgraph.fileslice.Slice, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (!this.isClosed.getAndSet(true)) {
            if (this.isTopLevelFileSlice && this.fileChannel != null) {
                try {
                    this.fileChannel.close();
                } catch (IOException unused) {
                }
                this.fileChannel = null;
            }
            this.fileChannel = null;
            this.nestedJarHandler.markSliceAsClosed(this);
        }
    }
}
