package com.badlogic.gdx.utils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.Buffer;
import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/StreamUtils.class */
public final class StreamUtils {
    public static final int DEFAULT_BUFFER_SIZE = 4096;
    public static final byte[] EMPTY_BYTES = new byte[0];

    public static void copyStream(InputStream inputStream, OutputStream outputStream) {
        copyStream(inputStream, outputStream, new byte[4096]);
    }

    public static void copyStream(InputStream inputStream, OutputStream outputStream, int i) {
        copyStream(inputStream, outputStream, new byte[i]);
    }

    public static void copyStream(InputStream inputStream, OutputStream outputStream, byte[] bArr) {
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static void copyStream(InputStream inputStream, ByteBuffer byteBuffer) {
        copyStream(inputStream, byteBuffer, new byte[4096]);
    }

    public static void copyStream(InputStream inputStream, ByteBuffer byteBuffer, int i) {
        copyStream(inputStream, byteBuffer, new byte[i]);
    }

    public static int copyStream(InputStream inputStream, ByteBuffer byteBuffer, byte[] bArr) {
        int position = byteBuffer.position();
        int i = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                BufferUtils.copy(bArr, 0, (Buffer) byteBuffer, read);
                i += read;
                byteBuffer.position(position + i);
            } else {
                byteBuffer.position(position);
                return i;
            }
        }
    }

    public static byte[] copyStreamToByteArray(InputStream inputStream) {
        return copyStreamToByteArray(inputStream, inputStream.available());
    }

    public static byte[] copyStreamToByteArray(InputStream inputStream, int i) {
        OptimizedByteArrayOutputStream optimizedByteArrayOutputStream = new OptimizedByteArrayOutputStream(Math.max(0, i));
        copyStream(inputStream, optimizedByteArrayOutputStream);
        return optimizedByteArrayOutputStream.toByteArray();
    }

    public static String copyStreamToString(InputStream inputStream) {
        return copyStreamToString(inputStream, inputStream.available(), null);
    }

    public static String copyStreamToString(InputStream inputStream, int i) {
        return copyStreamToString(inputStream, i, null);
    }

    public static String copyStreamToString(InputStream inputStream, int i, @Null String str) {
        InputStreamReader inputStreamReader = str == null ? new InputStreamReader(inputStream) : new InputStreamReader(inputStream, str);
        StringWriter stringWriter = new StringWriter(Math.max(0, i));
        char[] cArr = new char[4096];
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (read != -1) {
                stringWriter.write(cArr, 0, read);
            } else {
                return stringWriter.toString();
            }
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/StreamUtils$OptimizedByteArrayOutputStream.class */
    public static class OptimizedByteArrayOutputStream extends ByteArrayOutputStream {
        public OptimizedByteArrayOutputStream(int i) {
            super(i);
        }

        @Override // java.io.ByteArrayOutputStream
        public synchronized byte[] toByteArray() {
            return this.count == this.buf.length ? this.buf : super.toByteArray();
        }

        public byte[] getBuffer() {
            return this.buf;
        }
    }
}
