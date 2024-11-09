package nonapi.io.github.classgraph.fileslice;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicBoolean;
import nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler;
import nonapi.io.github.classgraph.fileslice.reader.RandomAccessByteBufferReader;
import nonapi.io.github.classgraph.fileslice.reader.RandomAccessFileChannelReader;
import nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader;
import nonapi.io.github.classgraph.utils.FileUtils;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/fileslice/FileSlice.class */
public class FileSlice extends Slice {
    public final File file;
    public RandomAccessFile raf;
    private final long fileLength;
    private FileChannel fileChannel;
    private ByteBuffer backingByteBuffer;
    private final boolean isTopLevelFileSlice;
    private final AtomicBoolean isClosed;

    private FileSlice(FileSlice fileSlice, long j, long j2, boolean z, long j3, NestedJarHandler nestedJarHandler) {
        super(fileSlice, j, j2, z, j3, nestedJarHandler);
        this.isClosed = new AtomicBoolean();
        this.file = fileSlice.file;
        this.raf = fileSlice.raf;
        this.fileChannel = fileSlice.fileChannel;
        this.fileLength = fileSlice.fileLength;
        this.isTopLevelFileSlice = false;
        if (fileSlice.backingByteBuffer != null) {
            this.backingByteBuffer = fileSlice.backingByteBuffer.duplicate();
            this.backingByteBuffer.position((int) this.sliceStartPos);
            this.backingByteBuffer.limit((int) (this.sliceStartPos + this.sliceLength));
        }
    }

    public FileSlice(File file, boolean z, long j, NestedJarHandler nestedJarHandler, LogNode logNode) {
        super(file.length(), z, j, nestedJarHandler);
        this.isClosed = new AtomicBoolean();
        FileUtils.checkCanReadAndIsFile(file);
        this.file = file;
        this.raf = new RandomAccessFile(file, "r");
        this.fileChannel = this.raf.getChannel();
        this.fileLength = file.length();
        this.isTopLevelFileSlice = true;
        if (nestedJarHandler.scanSpec.enableMemoryMapping) {
            try {
                this.backingByteBuffer = this.fileChannel.map(FileChannel.MapMode.READ_ONLY, 0L, this.fileLength);
            } catch (IOException | OutOfMemoryError unused) {
                System.gc();
                nestedJarHandler.runFinalizationMethod();
                try {
                    this.backingByteBuffer = this.fileChannel.map(FileChannel.MapMode.READ_ONLY, 0L, this.fileLength);
                } catch (IOException | OutOfMemoryError e) {
                    if (logNode != null) {
                        logNode.log("File " + file + " cannot be memory mapped: " + e + " (using RandomAccessFile API instead)");
                    }
                }
            }
        }
        nestedJarHandler.markSliceAsOpen(this);
    }

    public FileSlice(File file, NestedJarHandler nestedJarHandler, LogNode logNode) {
        this(file, false, 0L, nestedJarHandler, logNode);
    }

    @Override // nonapi.io.github.classgraph.fileslice.Slice
    public Slice slice(long j, long j2, boolean z, long j3) {
        if (this.isDeflatedZipEntry) {
            throw new IllegalArgumentException("Cannot slice a deflated zip entry");
        }
        return new FileSlice(this, j, j2, z, j3, this.nestedJarHandler);
    }

    @Override // nonapi.io.github.classgraph.fileslice.Slice
    public RandomAccessReader randomAccessReader() {
        if (this.backingByteBuffer == null) {
            return new RandomAccessFileChannelReader(this.fileChannel, this.sliceStartPos, this.sliceLength);
        }
        return new RandomAccessByteBufferReader(this.backingByteBuffer, this.sliceStartPos, this.sliceLength);
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
        if (this.backingByteBuffer != null) {
            return this.backingByteBuffer.duplicate();
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
            if (this.isTopLevelFileSlice && this.backingByteBuffer != null) {
                this.nestedJarHandler.closeDirectByteBuffer(this.backingByteBuffer);
            }
            this.backingByteBuffer = null;
            this.fileChannel = null;
            try {
                this.raf.close();
            } catch (IOException unused) {
            }
            this.raf = null;
            this.nestedJarHandler.markSliceAsClosed(this);
        }
    }
}
