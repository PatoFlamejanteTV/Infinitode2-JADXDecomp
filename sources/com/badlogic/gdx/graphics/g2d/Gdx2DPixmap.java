package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/Gdx2DPixmap.class */
public class Gdx2DPixmap implements Disposable {
    public static final int GDX2D_FORMAT_ALPHA = 1;
    public static final int GDX2D_FORMAT_LUMINANCE_ALPHA = 2;
    public static final int GDX2D_FORMAT_RGB888 = 3;
    public static final int GDX2D_FORMAT_RGBA8888 = 4;
    public static final int GDX2D_FORMAT_RGB565 = 5;
    public static final int GDX2D_FORMAT_RGBA4444 = 6;
    public static final int GDX2D_SCALE_NEAREST = 0;
    public static final int GDX2D_SCALE_LINEAR = 1;
    public static final int GDX2D_BLEND_NONE = 0;
    public static final int GDX2D_BLEND_SRC_OVER = 1;
    long basePtr;
    int width;
    int height;
    int format;
    ByteBuffer pixelPtr;
    long[] nativeData;

    private static native ByteBuffer load(long[] jArr, byte[] bArr, int i, int i2);

    private static native ByteBuffer loadByteBuffer(long[] jArr, ByteBuffer byteBuffer, int i, int i2);

    private static native ByteBuffer newPixmap(long[] jArr, int i, int i2, int i3);

    private static native void free(long j);

    private static native void clear(long j, int i);

    private static native void setPixel(long j, int i, int i2, int i3);

    private static native int getPixel(long j, int i, int i2);

    private static native void drawLine(long j, int i, int i2, int i3, int i4, int i5);

    private static native void drawRect(long j, int i, int i2, int i3, int i4, int i5);

    private static native void drawCircle(long j, int i, int i2, int i3, int i4);

    private static native void fillRect(long j, int i, int i2, int i3, int i4, int i5);

    private static native void fillCircle(long j, int i, int i2, int i3, int i4);

    private static native void fillTriangle(long j, int i, int i2, int i3, int i4, int i5, int i6, int i7);

    private static native void drawPixmap(long j, long j2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8);

    private static native void setBlend(long j, int i);

    private static native void setScale(long j, int i);

    public static native String getFailureReason();

    public static int toGlFormat(int i) {
        switch (i) {
            case 1:
                return 6406;
            case 2:
                return 6410;
            case 3:
            case 5:
                return 6407;
            case 4:
            case 6:
                return 6408;
            default:
                throw new GdxRuntimeException("unknown format: " + i);
        }
    }

    public static int toGlType(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
                return 5121;
            case 5:
                return 33635;
            case 6:
                return 32819;
            default:
                throw new GdxRuntimeException("unknown format: " + i);
        }
    }

    public Gdx2DPixmap(byte[] bArr, int i, int i2, int i3) {
        this.nativeData = new long[4];
        this.pixelPtr = load(this.nativeData, bArr, i, i2);
        if (this.pixelPtr == null) {
            throw new IOException("Error loading pixmap: " + getFailureReason());
        }
        this.basePtr = this.nativeData[0];
        this.width = (int) this.nativeData[1];
        this.height = (int) this.nativeData[2];
        this.format = (int) this.nativeData[3];
        if (i3 != 0 && i3 != this.format) {
            convert(i3);
        }
    }

    public Gdx2DPixmap(ByteBuffer byteBuffer, int i, int i2, int i3) {
        this.nativeData = new long[4];
        if (!byteBuffer.isDirect()) {
            throw new IOException("Couldn't load pixmap from non-direct ByteBuffer");
        }
        this.pixelPtr = loadByteBuffer(this.nativeData, byteBuffer, i, i2);
        if (this.pixelPtr == null) {
            throw new IOException("Error loading pixmap: " + getFailureReason());
        }
        this.basePtr = this.nativeData[0];
        this.width = (int) this.nativeData[1];
        this.height = (int) this.nativeData[2];
        this.format = (int) this.nativeData[3];
        if (i3 != 0 && i3 != this.format) {
            convert(i3);
        }
    }

    public Gdx2DPixmap(InputStream inputStream, int i) {
        this.nativeData = new long[4];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                break;
            } else {
                byteArrayOutputStream.write(bArr, 0, read);
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        this.pixelPtr = load(this.nativeData, byteArray, 0, byteArray.length);
        if (this.pixelPtr == null) {
            throw new IOException("Error loading pixmap: " + getFailureReason());
        }
        this.basePtr = this.nativeData[0];
        this.width = (int) this.nativeData[1];
        this.height = (int) this.nativeData[2];
        this.format = (int) this.nativeData[3];
        if (i != 0 && i != this.format) {
            convert(i);
        }
    }

    public Gdx2DPixmap(int i, int i2, int i3) {
        this.nativeData = new long[4];
        this.pixelPtr = newPixmap(this.nativeData, i, i2, i3);
        if (this.pixelPtr == null) {
            throw new GdxRuntimeException("Unable to allocate memory for pixmap: " + i + "x" + i2 + ", " + getFormatString(i3));
        }
        this.basePtr = this.nativeData[0];
        this.width = (int) this.nativeData[1];
        this.height = (int) this.nativeData[2];
        this.format = (int) this.nativeData[3];
    }

    public Gdx2DPixmap(ByteBuffer byteBuffer, long[] jArr) {
        this.nativeData = new long[4];
        this.pixelPtr = byteBuffer;
        this.basePtr = jArr[0];
        this.width = (int) jArr[1];
        this.height = (int) jArr[2];
        this.format = (int) jArr[3];
    }

    private void convert(int i) {
        Gdx2DPixmap gdx2DPixmap = new Gdx2DPixmap(this.width, this.height, i);
        gdx2DPixmap.setBlend(0);
        gdx2DPixmap.drawPixmap(this, 0, 0, 0, 0, this.width, this.height);
        dispose();
        this.basePtr = gdx2DPixmap.basePtr;
        this.format = gdx2DPixmap.format;
        this.height = gdx2DPixmap.height;
        this.nativeData = gdx2DPixmap.nativeData;
        this.pixelPtr = gdx2DPixmap.pixelPtr;
        this.width = gdx2DPixmap.width;
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        free(this.basePtr);
    }

    public void clear(int i) {
        clear(this.basePtr, i);
    }

    public void setPixel(int i, int i2, int i3) {
        setPixel(this.basePtr, i, i2, i3);
    }

    public int getPixel(int i, int i2) {
        return getPixel(this.basePtr, i, i2);
    }

    public void drawLine(int i, int i2, int i3, int i4, int i5) {
        drawLine(this.basePtr, i, i2, i3, i4, i5);
    }

    public void drawRect(int i, int i2, int i3, int i4, int i5) {
        drawRect(this.basePtr, i, i2, i3, i4, i5);
    }

    public void drawCircle(int i, int i2, int i3, int i4) {
        drawCircle(this.basePtr, i, i2, i3, i4);
    }

    public void fillRect(int i, int i2, int i3, int i4, int i5) {
        fillRect(this.basePtr, i, i2, i3, i4, i5);
    }

    public void fillCircle(int i, int i2, int i3, int i4) {
        fillCircle(this.basePtr, i, i2, i3, i4);
    }

    public void fillTriangle(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        fillTriangle(this.basePtr, i, i2, i3, i4, i5, i6, i7);
    }

    public void drawPixmap(Gdx2DPixmap gdx2DPixmap, int i, int i2, int i3, int i4, int i5, int i6) {
        drawPixmap(gdx2DPixmap.basePtr, this.basePtr, i, i2, i5, i6, i3, i4, i5, i6);
    }

    public void drawPixmap(Gdx2DPixmap gdx2DPixmap, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        drawPixmap(gdx2DPixmap.basePtr, this.basePtr, i, i2, i3, i4, i5, i6, i7, i8);
    }

    public void setBlend(int i) {
        setBlend(this.basePtr, i);
    }

    public void setScale(int i) {
        setScale(this.basePtr, i);
    }

    public static Gdx2DPixmap newPixmap(InputStream inputStream, int i) {
        try {
            return new Gdx2DPixmap(inputStream, i);
        } catch (IOException unused) {
            return null;
        }
    }

    public static Gdx2DPixmap newPixmap(int i, int i2, int i3) {
        try {
            return new Gdx2DPixmap(i, i2, i3);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public ByteBuffer getPixels() {
        return this.pixelPtr;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getFormat() {
        return this.format;
    }

    public int getGLInternalFormat() {
        return toGlFormat(this.format);
    }

    public int getGLFormat() {
        return getGLInternalFormat();
    }

    public int getGLType() {
        return toGlType(this.format);
    }

    public String getFormatString() {
        return getFormatString(this.format);
    }

    private static String getFormatString(int i) {
        switch (i) {
            case 1:
                return "alpha";
            case 2:
                return "luminance alpha";
            case 3:
                return "rgb888";
            case 4:
                return "rgba8888";
            case 5:
                return "rgb565";
            case 6:
                return "rgba4444";
            default:
                return "unknown";
        }
    }
}
