package org.jsoup.internal;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import org.jsoup.helper.DataUtil;
import org.jsoup.helper.Validate;

@Deprecated
/* loaded from: infinitode-2.jar:org/jsoup/internal/ConstrainableInputStream.class */
public final class ConstrainableInputStream extends BufferedInputStream {
    private final boolean capped;
    private final int maxSize;
    private long startTime;
    private long timeout;
    private int remaining;
    private boolean interrupted;

    private ConstrainableInputStream(InputStream inputStream, int i, int i2) {
        super(inputStream, i);
        this.timeout = 0L;
        Validate.isTrue(i2 >= 0);
        this.maxSize = i2;
        this.remaining = i2;
        this.capped = i2 != 0;
        this.startTime = System.nanoTime();
    }

    public static ConstrainableInputStream wrap(InputStream inputStream, int i, int i2) {
        if (inputStream instanceof ConstrainableInputStream) {
            return (ConstrainableInputStream) inputStream;
        }
        return new ConstrainableInputStream(inputStream, i, i2);
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) {
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

    public final ByteBuffer readToByteBuffer(int i) {
        return DataUtil.readToByteBuffer(this, i);
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public final void reset() {
        super.reset();
        this.remaining = this.maxSize - this.markpos;
    }

    public final ConstrainableInputStream timeout(long j, long j2) {
        this.startTime = j;
        this.timeout = j2 * 1000000;
        return this;
    }

    private boolean expired() {
        return this.timeout != 0 && System.nanoTime() - this.startTime > this.timeout;
    }
}
