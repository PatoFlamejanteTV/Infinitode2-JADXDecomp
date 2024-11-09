package nonapi.io.github.classgraph.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/utils/ProxyingInputStream.class */
public class ProxyingInputStream extends InputStream {
    private InputStream inputStream;
    private static Method readAllBytes;
    private static Method readNBytes1;
    private static Method readNBytes3;
    private static Method skipNBytes;
    private static Method transferTo;

    static {
        try {
            readAllBytes = InputStream.class.getDeclaredMethod("readAllBytes", new Class[0]);
        } catch (NoSuchMethodException | SecurityException unused) {
        }
        try {
            readNBytes1 = InputStream.class.getDeclaredMethod("readNBytes", Integer.TYPE);
        } catch (NoSuchMethodException | SecurityException unused2) {
        }
        try {
            readNBytes3 = InputStream.class.getDeclaredMethod("readNBytes", byte[].class, Integer.TYPE, Integer.TYPE);
        } catch (NoSuchMethodException | SecurityException unused3) {
        }
        try {
            skipNBytes = InputStream.class.getDeclaredMethod("skipNBytes", Long.TYPE);
        } catch (NoSuchMethodException | SecurityException unused4) {
        }
        try {
            transferTo = InputStream.class.getDeclaredMethod("transferTo", OutputStream.class);
        } catch (NoSuchMethodException | SecurityException unused5) {
        }
    }

    public ProxyingInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override // java.io.InputStream
    public int read() {
        return this.inputStream.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return this.inputStream.read(bArr);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        return this.inputStream.read(bArr, i, i2);
    }

    @Override // java.io.InputStream
    public byte[] readAllBytes() {
        if (readAllBytes == null) {
            throw new UnsupportedOperationException();
        }
        try {
            return (byte[]) readAllBytes.invoke(this.inputStream, new Object[0]);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override // java.io.InputStream
    public byte[] readNBytes(int i) {
        if (readNBytes1 == null) {
            throw new UnsupportedOperationException();
        }
        try {
            return (byte[]) readNBytes1.invoke(this.inputStream, Integer.valueOf(i));
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override // java.io.InputStream
    public int readNBytes(byte[] bArr, int i, int i2) {
        if (readNBytes3 == null) {
            throw new UnsupportedOperationException();
        }
        try {
            return ((Integer) readNBytes3.invoke(this.inputStream, bArr, Integer.valueOf(i), Integer.valueOf(i2))).intValue();
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override // java.io.InputStream
    public int available() {
        return this.inputStream.available();
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.inputStream.markSupported();
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i) {
        this.inputStream.mark(i);
    }

    @Override // java.io.InputStream
    public synchronized void reset() {
        this.inputStream.reset();
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        return this.inputStream.skip(j);
    }

    @Override // java.io.InputStream
    public void skipNBytes(long j) {
        if (skipNBytes == null) {
            throw new UnsupportedOperationException();
        }
        try {
            skipNBytes.invoke(this.inputStream, Long.valueOf(j));
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override // java.io.InputStream
    public long transferTo(OutputStream outputStream) {
        if (transferTo == null) {
            throw new UnsupportedOperationException();
        }
        try {
            return ((Long) transferTo.invoke(this.inputStream, outputStream)).longValue();
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public String toString() {
        return this.inputStream.toString();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.inputStream != null) {
            try {
                this.inputStream.close();
            } finally {
                this.inputStream = null;
            }
        }
    }
}
