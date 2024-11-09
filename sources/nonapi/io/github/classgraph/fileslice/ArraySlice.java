package nonapi.io.github.classgraph.fileslice;

import java.io.InputStream;
import java.util.Arrays;
import nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler;
import nonapi.io.github.classgraph.fileslice.reader.RandomAccessArrayReader;
import nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/fileslice/ArraySlice.class */
public class ArraySlice extends Slice {
    public byte[] arr;

    private ArraySlice(ArraySlice arraySlice, long j, long j2, boolean z, long j3, NestedJarHandler nestedJarHandler) {
        super(arraySlice, j, j2, z, j3, nestedJarHandler);
        this.arr = arraySlice.arr;
    }

    public ArraySlice(byte[] bArr, boolean z, long j, NestedJarHandler nestedJarHandler) {
        super(bArr.length, z, j, nestedJarHandler);
        this.arr = bArr;
    }

    @Override // nonapi.io.github.classgraph.fileslice.Slice
    public Slice slice(long j, long j2, boolean z, long j3) {
        if (this.isDeflatedZipEntry) {
            throw new IllegalArgumentException("Cannot slice a deflated zip entry");
        }
        return new ArraySlice(this, j, j2, z, j3, this.nestedJarHandler);
    }

    @Override // nonapi.io.github.classgraph.fileslice.Slice
    public byte[] load() {
        if (!this.isDeflatedZipEntry) {
            if (this.sliceStartPos == 0 && this.sliceLength == this.arr.length) {
                return this.arr;
            }
            return Arrays.copyOfRange(this.arr, (int) this.sliceStartPos, (int) (this.sliceStartPos + this.sliceLength));
        }
        InputStream open = open();
        Throwable th = null;
        try {
            byte[] readAllBytesAsArray = NestedJarHandler.readAllBytesAsArray(open, this.inflatedLengthHint);
            if (open != null) {
                if (0 != 0) {
                    try {
                        open.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                } else {
                    open.close();
                }
            }
            return readAllBytesAsArray;
        } catch (Throwable th3) {
            if (open != null) {
                if (0 != 0) {
                    try {
                        open.close();
                    } catch (Throwable th4) {
                        th.addSuppressed(th4);
                    }
                } else {
                    open.close();
                }
            }
            throw th3;
        }
    }

    @Override // nonapi.io.github.classgraph.fileslice.Slice
    public RandomAccessReader randomAccessReader() {
        return new RandomAccessArrayReader(this.arr, (int) this.sliceStartPos, (int) this.sliceLength);
    }

    @Override // nonapi.io.github.classgraph.fileslice.Slice
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // nonapi.io.github.classgraph.fileslice.Slice
    public int hashCode() {
        return super.hashCode();
    }
}
