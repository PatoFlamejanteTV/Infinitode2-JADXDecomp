package org.jsoup.internal;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import org.jsoup.helper.Validate;

/* loaded from: infinitode-2.jar:org/jsoup/internal/ControllableInputStream.class */
public class ControllableInputStream extends FilterInputStream {
    private final BufferedInputStream buff;
    private final boolean capped;
    private final int maxSize;
    private long startTime;
    private long timeout;
    private int remaining;
    private int markPos;
    private boolean interrupted;

    private ControllableInputStream(BufferedInputStream bufferedInputStream, int i) {
        super(bufferedInputStream);
        this.timeout = 0L;
        Validate.isTrue(i >= 0);
        this.buff = bufferedInputStream;
        this.capped = i != 0;
        this.maxSize = i;
        this.remaining = i;
        this.markPos = -1;
        this.startTime = System.nanoTime();
    }

    public static ControllableInputStream wrap(InputStream inputStream, int i, int i2) {
        if (inputStream instanceof ControllableInputStream) {
            return (ControllableInputStream) inputStream;
        }
        if (inputStream instanceof BufferedInputStream) {
            return new ControllableInputStream((BufferedInputStream) inputStream, i2);
        }
        return new ControllableInputStream(new BufferedInputStream(inputStream, i), i2);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        if (this.interrupted) {
            return -1;
        }
        if (this.capped && this.remaining <= 0) {
            return -1;
        }
        if (Thread.currentThread().isInterrupted()) {
            this.interrupted = true;
            return -1;
        }
        if (expired()) {
            throw new SocketTimeoutException("Read timeout");
        }
        if (this.capped && i2 > this.remaining) {
            i2 = this.remaining;
        }
        try {
            int read = super.read(bArr, i, i2);
            this.remaining -= read;
            return read;
        } catch (SocketTimeoutException unused) {
            return 0;
        }
    }

    public static ByteBuffer readToByteBuffer(InputStream inputStream, int i) {
        Validate.isTrue(i >= 0, "maxSize must be 0 (unlimited) or larger");
        Validate.notNull(inputStream);
        boolean z = i > 0;
        boolean z2 = z;
        int i2 = (!z || i >= 32768) ? 32768 : i;
        int i3 = i2;
        byte[] bArr = new byte[i2];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i3);
        int i4 = i;
        while (true) {
            int read = inputStream.read(bArr, 0, z2 ? Math.min(i4, i3) : i3);
            if (read == -1) {
                break;
            }
            if (z2) {
                if (read < i4) {
                    i4 -= read;
                } else {
                    byteArrayOutputStream.write(bArr, 0, i4);
                    break;
                }
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
        return ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() {
        super.reset();
        this.remaining = this.maxSize - this.markPos;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int i) {
        super.mark(i);
        this.markPos = this.maxSize - this.remaining;
    }

    public ControllableInputStream timeout(long j, long j2) {
        this.startTime = j;
        this.timeout = j2 * 1000000;
        return this;
    }

    private boolean expired() {
        return this.timeout != 0 && System.nanoTime() - this.startTime > this.timeout;
    }

    public BufferedInputStream inputStream() {
        return this.buff;
    }
}
