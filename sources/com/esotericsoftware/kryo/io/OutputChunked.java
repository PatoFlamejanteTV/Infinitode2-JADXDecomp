package com.esotericsoftware.kryo.io;

import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.util.Util;
import com.esotericsoftware.minlog.Log;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/io/OutputChunked.class */
public class OutputChunked extends Output {
    public OutputChunked() {
    }

    public OutputChunked(int i) {
        super(i);
    }

    public OutputChunked(OutputStream outputStream) {
        super(outputStream);
    }

    public OutputChunked(OutputStream outputStream, int i) {
        super(outputStream, i);
    }

    @Override // com.esotericsoftware.kryo.io.Output, java.io.OutputStream, java.io.Flushable
    public void flush() {
        if (position() > 0) {
            try {
                writeChunkSize();
                super.flush();
                return;
            } catch (IOException e) {
                throw new KryoException(e);
            }
        }
        super.flush();
    }

    private void writeChunkSize() {
        int position = position();
        if (Log.TRACE) {
            Log.trace("kryo", "Write chunk: " + position + Util.pos(position));
        }
        OutputStream outputStream = getOutputStream();
        if ((position & (-128)) == 0) {
            outputStream.write(position);
            return;
        }
        outputStream.write((position & 127) | 128);
        int i = position >>> 7;
        if ((i & (-128)) == 0) {
            outputStream.write(i);
            return;
        }
        outputStream.write((i & 127) | 128);
        int i2 = i >>> 7;
        if ((i2 & (-128)) == 0) {
            outputStream.write(i2);
            return;
        }
        outputStream.write((i2 & 127) | 128);
        int i3 = i2 >>> 7;
        if ((i3 & (-128)) == 0) {
            outputStream.write(i3);
        } else {
            outputStream.write((i3 & 127) | 128);
            outputStream.write(i3 >>> 7);
        }
    }

    public void endChunk() {
        flush();
        if (Log.TRACE) {
            Log.trace("kryo", "End chunk.");
        }
        try {
            getOutputStream().write(0);
        } catch (IOException e) {
            throw new KryoException(e);
        }
    }
}
