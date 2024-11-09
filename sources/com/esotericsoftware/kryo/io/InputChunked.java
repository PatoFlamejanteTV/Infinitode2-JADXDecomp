package com.esotericsoftware.kryo.io;

import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.minlog.Log;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/io/InputChunked.class */
public class InputChunked extends Input {
    private int chunkSize;

    public InputChunked() {
        this.chunkSize = -1;
    }

    public InputChunked(int i) {
        super(i);
        this.chunkSize = -1;
    }

    public InputChunked(InputStream inputStream) {
        super(inputStream);
        this.chunkSize = -1;
    }

    public InputChunked(InputStream inputStream, int i) {
        super(inputStream, i);
        this.chunkSize = -1;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public void setInputStream(InputStream inputStream) {
        super.setInputStream(inputStream);
        this.chunkSize = -1;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public void setBuffer(byte[] bArr, int i, int i2) {
        super.setBuffer(bArr, i, i2);
        this.chunkSize = -1;
    }

    @Override // com.esotericsoftware.kryo.io.Input, java.io.InputStream, com.esotericsoftware.kryo.util.Pool.Poolable
    public void reset() {
        super.reset();
        this.chunkSize = -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.esotericsoftware.kryo.io.Input
    public int fill(byte[] bArr, int i, int i2) {
        if (this.chunkSize == -1) {
            if (!readChunkSize()) {
                return -1;
            }
        } else if (this.chunkSize == 0) {
            return -1;
        }
        int fill = super.fill(bArr, i, Math.min(this.chunkSize, i2));
        this.chunkSize -= fill;
        if (this.chunkSize != 0 || readChunkSize()) {
            return fill;
        }
        return -1;
    }

    private boolean readChunkSize() {
        try {
            InputStream inputStream = getInputStream();
            int i = 0;
            for (int i2 = 0; i2 < 32; i2 += 7) {
                int read = inputStream.read();
                if (read == -1) {
                    return false;
                }
                i |= (read & 127) << i2;
                if ((read & 128) == 0) {
                    this.chunkSize = i;
                    if (!Log.TRACE || this.chunkSize <= 0) {
                        return true;
                    }
                    Log.trace("kryo", "Read chunk: " + this.chunkSize);
                    return true;
                }
            }
            throw new KryoException("Unable to read chunk size: malformed integer");
        } catch (IOException e) {
            throw new KryoException("Unable to read chunk size.", e);
        }
    }

    public void nextChunk() {
        this.position = this.limit;
        if (this.chunkSize == -1) {
            readChunkSize();
        }
        while (this.chunkSize > 0) {
            skip(this.chunkSize);
        }
        this.chunkSize = -1;
        if (Log.TRACE) {
            Log.trace("kryo", "Next chunk.");
        }
    }
}
