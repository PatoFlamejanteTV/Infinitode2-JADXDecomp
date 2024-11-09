package io.github.classgraph;

import java.io.Closeable;
import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:io/github/classgraph/CloseableByteBuffer.class */
public class CloseableByteBuffer implements Closeable {
    private ByteBuffer byteBuffer;
    private Runnable onClose;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CloseableByteBuffer(ByteBuffer byteBuffer, Runnable runnable) {
        this.byteBuffer = byteBuffer;
        this.onClose = runnable;
    }

    public ByteBuffer getByteBuffer() {
        return this.byteBuffer;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.onClose != null) {
            try {
                this.onClose.run();
            } catch (Exception unused) {
            }
            this.onClose = null;
        }
        this.byteBuffer = null;
    }
}
