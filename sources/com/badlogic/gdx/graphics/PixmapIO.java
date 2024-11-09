package com.badlogic.gdx.graphics;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.ByteArray;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/PixmapIO.class */
public class PixmapIO {
    public static void writeCIM(FileHandle fileHandle, Pixmap pixmap) {
        CIM.write(fileHandle, pixmap);
    }

    public static Pixmap readCIM(FileHandle fileHandle) {
        return CIM.read(fileHandle);
    }

    public static void writePNG(FileHandle fileHandle, Pixmap pixmap, int i, boolean z) {
        try {
            PNG png = new PNG((int) (pixmap.getWidth() * pixmap.getHeight() * 1.5f));
            try {
                png.setFlipY(z);
                png.setCompression(i);
                png.write(fileHandle, pixmap);
                png.dispose();
            } catch (Throwable th) {
                png.dispose();
                throw th;
            }
        } catch (IOException e) {
            throw new GdxRuntimeException("Error writing PNG: " + fileHandle, e);
        }
    }

    public static void writePNG(FileHandle fileHandle, Pixmap pixmap) {
        writePNG(fileHandle, pixmap, -1, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/PixmapIO$CIM.class */
    public static class CIM {
        private static final int BUFFER_SIZE = 32000;
        private static final byte[] writeBuffer = new byte[BUFFER_SIZE];
        private static final byte[] readBuffer = new byte[BUFFER_SIZE];

        private CIM() {
        }

        public static void write(FileHandle fileHandle, Pixmap pixmap) {
            try {
                try {
                    DataOutputStream dataOutputStream = new DataOutputStream(new DeflaterOutputStream(fileHandle.write(false)));
                    dataOutputStream.writeInt(pixmap.getWidth());
                    dataOutputStream.writeInt(pixmap.getHeight());
                    dataOutputStream.writeInt(Pixmap.Format.toGdx2DPixmapFormat(pixmap.getFormat()));
                    ByteBuffer pixels = pixmap.getPixels();
                    pixels.position(0);
                    pixels.limit(pixels.capacity());
                    int capacity = pixels.capacity() % BUFFER_SIZE;
                    int capacity2 = pixels.capacity() / BUFFER_SIZE;
                    synchronized (writeBuffer) {
                        for (int i = 0; i < capacity2; i++) {
                            pixels.get(writeBuffer);
                            dataOutputStream.write(writeBuffer);
                        }
                        pixels.get(writeBuffer, 0, capacity);
                        dataOutputStream.write(writeBuffer, 0, capacity);
                    }
                    pixels.position(0);
                    pixels.limit(pixels.capacity());
                    StreamUtils.closeQuietly(dataOutputStream);
                } catch (Exception e) {
                    throw new GdxRuntimeException("Couldn't write Pixmap to file '" + fileHandle + "'", e);
                }
            } catch (Throwable th) {
                StreamUtils.closeQuietly(null);
                throw th;
            }
        }

        public static Pixmap read(FileHandle fileHandle) {
            try {
                try {
                    DataInputStream dataInputStream = new DataInputStream(new InflaterInputStream(new BufferedInputStream(fileHandle.read())));
                    Pixmap pixmap = new Pixmap(dataInputStream.readInt(), dataInputStream.readInt(), Pixmap.Format.fromGdx2DPixmapFormat(dataInputStream.readInt()));
                    ByteBuffer pixels = pixmap.getPixels();
                    pixels.position(0);
                    pixels.limit(pixels.capacity());
                    synchronized (readBuffer) {
                        while (true) {
                            int read = dataInputStream.read(readBuffer);
                            if (read > 0) {
                                pixels.put(readBuffer, 0, read);
                            }
                        }
                    }
                    pixels.position(0);
                    pixels.limit(pixels.capacity());
                    StreamUtils.closeQuietly(dataInputStream);
                    return pixmap;
                } catch (Exception e) {
                    throw new GdxRuntimeException("Couldn't read Pixmap from file '" + fileHandle + "'", e);
                }
            } catch (Throwable th) {
                StreamUtils.closeQuietly(null);
                throw th;
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/PixmapIO$PNG.class */
    public static class PNG implements Disposable {
        private static final byte[] SIGNATURE = {-119, 80, 78, 71, 13, 10, 26, 10};
        private static final int IHDR = 1229472850;
        private static final int IDAT = 1229209940;
        private static final int IEND = 1229278788;
        private static final byte COLOR_ARGB = 6;
        private static final byte COMPRESSION_DEFLATE = 0;
        private static final byte FILTER_NONE = 0;
        private static final byte INTERLACE_NONE = 0;
        private static final byte PAETH = 4;
        private final ChunkBuffer buffer;
        private final Deflater deflater;
        private ByteArray lineOutBytes;
        private ByteArray curLineBytes;
        private ByteArray prevLineBytes;
        private boolean flipY;
        private int lastLineLen;

        public PNG() {
            this(16384);
        }

        public PNG(int i) {
            this.flipY = true;
            this.buffer = new ChunkBuffer(i);
            this.deflater = new Deflater();
        }

        public void setFlipY(boolean z) {
            this.flipY = z;
        }

        public void setCompression(int i) {
            this.deflater.setLevel(i);
        }

        public void write(FileHandle fileHandle, Pixmap pixmap) {
            OutputStream write = fileHandle.write(false);
            try {
                write(write, pixmap);
            } finally {
                StreamUtils.closeQuietly(write);
            }
        }

        /*  JADX ERROR: Type inference failed
            jadx.core.utils.exceptions.JadxOverflowException: Type update terminated with stack overflow, arg: (r0v95 ?? I:int), method size: 741
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
            */
        public void write(java.io.OutputStream r7, com.badlogic.gdx.graphics.Pixmap r8) {
            /*
                Method dump skipped, instructions count: 741
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.graphics.PixmapIO.PNG.write(java.io.OutputStream, com.badlogic.gdx.graphics.Pixmap):void");
        }

        @Override // com.badlogic.gdx.utils.Disposable
        public void dispose() {
            this.deflater.end();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/PixmapIO$PNG$ChunkBuffer.class */
        public static class ChunkBuffer extends DataOutputStream {
            final ByteArrayOutputStream buffer;
            final CRC32 crc;

            ChunkBuffer(int i) {
                this(new ByteArrayOutputStream(i), new CRC32());
            }

            private ChunkBuffer(ByteArrayOutputStream byteArrayOutputStream, CRC32 crc32) {
                super(new CheckedOutputStream(byteArrayOutputStream, crc32));
                this.buffer = byteArrayOutputStream;
                this.crc = crc32;
            }

            public void endChunk(DataOutputStream dataOutputStream) {
                flush();
                dataOutputStream.writeInt(this.buffer.size() - 4);
                this.buffer.writeTo(dataOutputStream);
                dataOutputStream.writeInt((int) this.crc.getValue());
                this.buffer.reset();
                this.crc.reset();
            }
        }
    }
}
