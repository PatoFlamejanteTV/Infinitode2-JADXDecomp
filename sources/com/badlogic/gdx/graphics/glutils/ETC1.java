package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import com.crashinvaders.basisu.gdx.BasisuGdxUtils;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.nio.ByteBuffer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/ETC1.class */
public class ETC1 {
    public static int PKM_HEADER_SIZE = 16;
    public static int ETC1_RGB8_OES = BasisuGdxUtils.GL_TEX_ETC1_RGB8;

    public static native int getCompressedDataSize(int i, int i2);

    public static native void formatHeader(ByteBuffer byteBuffer, int i, int i2, int i3);

    static native int getWidthPKM(ByteBuffer byteBuffer, int i);

    static native int getHeightPKM(ByteBuffer byteBuffer, int i);

    static native boolean isValidPKM(ByteBuffer byteBuffer, int i);

    private static native void decodeImage(ByteBuffer byteBuffer, int i, ByteBuffer byteBuffer2, int i2, int i3, int i4, int i5);

    private static native ByteBuffer encodeImage(ByteBuffer byteBuffer, int i, int i2, int i3, int i4);

    private static native ByteBuffer encodeImagePKM(ByteBuffer byteBuffer, int i, int i2, int i3, int i4);

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/ETC1$ETC1Data.class */
    public static final class ETC1Data implements Disposable {
        public final int width;
        public final int height;
        public final ByteBuffer compressedData;
        public final int dataOffset;

        public ETC1Data(int i, int i2, ByteBuffer byteBuffer, int i3) {
            this.width = i;
            this.height = i2;
            this.compressedData = byteBuffer;
            this.dataOffset = i3;
            checkNPOT();
        }

        public ETC1Data(FileHandle fileHandle) {
            byte[] bArr = new byte[10240];
            DataInputStream dataInputStream = null;
            try {
                try {
                    DataInputStream dataInputStream2 = new DataInputStream(new BufferedInputStream(new GZIPInputStream(fileHandle.read())));
                    dataInputStream = dataInputStream2;
                    this.compressedData = BufferUtils.newUnsafeByteBuffer(dataInputStream2.readInt());
                    while (true) {
                        int read = dataInputStream.read(bArr);
                        if (read != -1) {
                            this.compressedData.put(bArr, 0, read);
                        } else {
                            this.compressedData.position(0);
                            this.compressedData.limit(this.compressedData.capacity());
                            StreamUtils.closeQuietly(dataInputStream);
                            this.width = ETC1.getWidthPKM(this.compressedData, 0);
                            this.height = ETC1.getHeightPKM(this.compressedData, 0);
                            this.dataOffset = ETC1.PKM_HEADER_SIZE;
                            this.compressedData.position(this.dataOffset);
                            checkNPOT();
                            return;
                        }
                    }
                } catch (Exception e) {
                    throw new GdxRuntimeException("Couldn't load pkm file '" + fileHandle + "'", e);
                }
            } catch (Throwable th) {
                StreamUtils.closeQuietly(dataInputStream);
                throw th;
            }
        }

        private void checkNPOT() {
            if (!MathUtils.isPowerOfTwo(this.width) || !MathUtils.isPowerOfTwo(this.height)) {
                System.out.println("ETC1Data warning: non-power-of-two ETC1 textures may crash the driver of PowerVR GPUs");
            }
        }

        public final boolean hasPKMHeader() {
            return this.dataOffset == 16;
        }

        public final void write(FileHandle fileHandle) {
            DataOutputStream dataOutputStream = null;
            byte[] bArr = new byte[10240];
            int i = 0;
            this.compressedData.position(0);
            this.compressedData.limit(this.compressedData.capacity());
            try {
                try {
                    DataOutputStream dataOutputStream2 = new DataOutputStream(new GZIPOutputStream(fileHandle.write(false)));
                    dataOutputStream = dataOutputStream2;
                    dataOutputStream2.writeInt(this.compressedData.capacity());
                    while (i != this.compressedData.capacity()) {
                        int min = Math.min(this.compressedData.remaining(), 10240);
                        this.compressedData.get(bArr, 0, min);
                        dataOutputStream.write(bArr, 0, min);
                        i += min;
                    }
                    StreamUtils.closeQuietly(dataOutputStream);
                    this.compressedData.position(this.dataOffset);
                    this.compressedData.limit(this.compressedData.capacity());
                } catch (Exception e) {
                    throw new GdxRuntimeException("Couldn't write PKM file to '" + fileHandle + "'", e);
                }
            } catch (Throwable th) {
                StreamUtils.closeQuietly(dataOutputStream);
                throw th;
            }
        }

        @Override // com.badlogic.gdx.utils.Disposable
        public final void dispose() {
            BufferUtils.disposeUnsafeByteBuffer(this.compressedData);
        }

        public final String toString() {
            if (hasPKMHeader()) {
                return (ETC1.isValidPKM(this.compressedData, 0) ? "valid" : "invalid") + " pkm [" + ETC1.getWidthPKM(this.compressedData, 0) + "x" + ETC1.getHeightPKM(this.compressedData, 0) + "], compressed: " + (this.compressedData.capacity() - ETC1.PKM_HEADER_SIZE);
            }
            return "raw [" + this.width + "x" + this.height + "], compressed: " + (this.compressedData.capacity() - ETC1.PKM_HEADER_SIZE);
        }
    }

    private static int getPixelSize(Pixmap.Format format) {
        if (format == Pixmap.Format.RGB565) {
            return 2;
        }
        if (format == Pixmap.Format.RGB888) {
            return 3;
        }
        throw new GdxRuntimeException("Can only handle RGB565 or RGB888 images");
    }

    public static ETC1Data encodeImage(Pixmap pixmap) {
        ByteBuffer encodeImage = encodeImage(pixmap.getPixels(), 0, pixmap.getWidth(), pixmap.getHeight(), getPixelSize(pixmap.getFormat()));
        BufferUtils.newUnsafeByteBuffer(encodeImage);
        return new ETC1Data(pixmap.getWidth(), pixmap.getHeight(), encodeImage, 0);
    }

    public static ETC1Data encodeImagePKM(Pixmap pixmap) {
        ByteBuffer encodeImagePKM = encodeImagePKM(pixmap.getPixels(), 0, pixmap.getWidth(), pixmap.getHeight(), getPixelSize(pixmap.getFormat()));
        BufferUtils.newUnsafeByteBuffer(encodeImagePKM);
        return new ETC1Data(pixmap.getWidth(), pixmap.getHeight(), encodeImagePKM, 16);
    }

    public static Pixmap decodeImage(ETC1Data eTC1Data, Pixmap.Format format) {
        int i;
        int i2;
        int i3;
        if (eTC1Data.hasPKMHeader()) {
            i = 16;
            i2 = getWidthPKM(eTC1Data.compressedData, 0);
            i3 = getHeightPKM(eTC1Data.compressedData, 0);
        } else {
            i = 0;
            i2 = eTC1Data.width;
            i3 = eTC1Data.height;
        }
        int pixelSize = getPixelSize(format);
        Pixmap pixmap = new Pixmap(i2, i3, format);
        decodeImage(eTC1Data.compressedData, i, pixmap.getPixels(), 0, i2, i3, pixelSize);
        return pixmap;
    }
}
