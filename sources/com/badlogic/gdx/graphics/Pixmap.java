package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/Pixmap.class */
public class Pixmap implements Disposable {
    private Blending blending;
    private Filter filter;
    final Gdx2DPixmap pixmap;
    int color;
    private boolean disposed;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/Pixmap$Blending.class */
    public enum Blending {
        None,
        SourceOver
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/Pixmap$DownloadPixmapResponseListener.class */
    public interface DownloadPixmapResponseListener {
        void downloadComplete(Pixmap pixmap);

        void downloadFailed(Throwable th);
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/Pixmap$Filter.class */
    public enum Filter {
        NearestNeighbour,
        BiLinear
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/Pixmap$Format.class */
    public enum Format {
        Alpha,
        Intensity,
        LuminanceAlpha,
        RGB565,
        RGBA4444,
        RGB888,
        RGBA8888;

        public static int toGdx2DPixmapFormat(Format format) {
            if (format == Alpha || format == Intensity) {
                return 1;
            }
            if (format == LuminanceAlpha) {
                return 2;
            }
            if (format == RGB565) {
                return 5;
            }
            if (format == RGBA4444) {
                return 6;
            }
            if (format == RGB888) {
                return 3;
            }
            if (format == RGBA8888) {
                return 4;
            }
            throw new GdxRuntimeException("Unknown Format: " + format);
        }

        public static Format fromGdx2DPixmapFormat(int i) {
            if (i == 1) {
                return Alpha;
            }
            if (i == 2) {
                return LuminanceAlpha;
            }
            if (i == 5) {
                return RGB565;
            }
            if (i == 6) {
                return RGBA4444;
            }
            if (i == 3) {
                return RGB888;
            }
            if (i == 4) {
                return RGBA8888;
            }
            throw new GdxRuntimeException("Unknown Gdx2DPixmap Format: " + i);
        }

        public static int toGlFormat(Format format) {
            return Gdx2DPixmap.toGlFormat(toGdx2DPixmapFormat(format));
        }

        public static int toGlType(Format format) {
            return Gdx2DPixmap.toGlType(toGdx2DPixmapFormat(format));
        }
    }

    public static Pixmap createFromFrameBuffer(int i, int i2, int i3, int i4) {
        Gdx.gl.glPixelStorei(3333, 1);
        Pixmap pixmap = new Pixmap(i3, i4, Format.RGBA8888);
        Gdx.gl.glReadPixels(i, i2, i3, i4, 6408, 5121, pixmap.getPixels());
        return pixmap;
    }

    public void setBlending(Blending blending) {
        this.blending = blending;
        this.pixmap.setBlend(blending == Blending.None ? 0 : 1);
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
        this.pixmap.setScale(filter == Filter.NearestNeighbour ? 0 : 1);
    }

    public Pixmap(int i, int i2, Format format) {
        this.blending = Blending.SourceOver;
        this.filter = Filter.BiLinear;
        this.color = 0;
        this.pixmap = new Gdx2DPixmap(i, i2, Format.toGdx2DPixmapFormat(format));
        setColor(0.0f, 0.0f, 0.0f, 0.0f);
        fill();
    }

    public Pixmap(byte[] bArr, int i, int i2) {
        this.blending = Blending.SourceOver;
        this.filter = Filter.BiLinear;
        this.color = 0;
        try {
            this.pixmap = new Gdx2DPixmap(bArr, i, i2, 0);
        } catch (IOException e) {
            throw new GdxRuntimeException("Couldn't load pixmap from image data", e);
        }
    }

    public Pixmap(ByteBuffer byteBuffer, int i, int i2) {
        this.blending = Blending.SourceOver;
        this.filter = Filter.BiLinear;
        this.color = 0;
        if (!byteBuffer.isDirect()) {
            throw new GdxRuntimeException("Couldn't load pixmap from non-direct ByteBuffer");
        }
        try {
            this.pixmap = new Gdx2DPixmap(byteBuffer, i, i2, 0);
        } catch (IOException e) {
            throw new GdxRuntimeException("Couldn't load pixmap from image data", e);
        }
    }

    public Pixmap(ByteBuffer byteBuffer) {
        this(byteBuffer, byteBuffer.position(), byteBuffer.remaining());
    }

    public Pixmap(FileHandle fileHandle) {
        this.blending = Blending.SourceOver;
        this.filter = Filter.BiLinear;
        this.color = 0;
        try {
            byte[] readBytes = fileHandle.readBytes();
            this.pixmap = new Gdx2DPixmap(readBytes, 0, readBytes.length, 0);
        } catch (Exception e) {
            throw new GdxRuntimeException("Couldn't load file: " + fileHandle, e);
        }
    }

    public Pixmap(Gdx2DPixmap gdx2DPixmap) {
        this.blending = Blending.SourceOver;
        this.filter = Filter.BiLinear;
        this.color = 0;
        this.pixmap = gdx2DPixmap;
    }

    public static void downloadFromUrl(String str, DownloadPixmapResponseListener downloadPixmapResponseListener) {
        Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.GET);
        httpRequest.setUrl(str);
        Gdx.f881net.sendHttpRequest(httpRequest, new AnonymousClass1(downloadPixmapResponseListener));
    }

    /* renamed from: com.badlogic.gdx.graphics.Pixmap$1, reason: invalid class name */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/Pixmap$1.class */
    class AnonymousClass1 implements Net.HttpResponseListener {
        final /* synthetic */ DownloadPixmapResponseListener val$responseListener;

        AnonymousClass1(DownloadPixmapResponseListener downloadPixmapResponseListener) {
            this.val$responseListener = downloadPixmapResponseListener;
        }

        @Override // com.badlogic.gdx.Net.HttpResponseListener
        public void handleHttpResponse(Net.HttpResponse httpResponse) {
            final byte[] result = httpResponse.getResult();
            Gdx.app.postRunnable(new Runnable() { // from class: com.badlogic.gdx.graphics.Pixmap.1.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        AnonymousClass1.this.val$responseListener.downloadComplete(new Pixmap(result, 0, result.length));
                    } catch (Throwable th) {
                        AnonymousClass1.this.failed(th);
                    }
                }
            });
        }

        @Override // com.badlogic.gdx.Net.HttpResponseListener
        public void failed(Throwable th) {
            this.val$responseListener.downloadFailed(th);
        }

        @Override // com.badlogic.gdx.Net.HttpResponseListener
        public void cancelled() {
        }
    }

    public void setColor(int i) {
        this.color = i;
    }

    public void setColor(float f, float f2, float f3, float f4) {
        this.color = Color.rgba8888(f, f2, f3, f4);
    }

    public void setColor(Color color) {
        this.color = Color.rgba8888(color.r, color.g, color.f888b, color.f889a);
    }

    public void fill() {
        this.pixmap.clear(this.color);
    }

    public void drawLine(int i, int i2, int i3, int i4) {
        this.pixmap.drawLine(i, i2, i3, i4, this.color);
    }

    public void drawRectangle(int i, int i2, int i3, int i4) {
        this.pixmap.drawRect(i, i2, i3, i4, this.color);
    }

    public void drawPixmap(Pixmap pixmap, int i, int i2) {
        drawPixmap(pixmap, i, i2, 0, 0, pixmap.getWidth(), pixmap.getHeight());
    }

    public void drawPixmap(Pixmap pixmap, int i, int i2, int i3, int i4, int i5, int i6) {
        this.pixmap.drawPixmap(pixmap.pixmap, i3, i4, i, i2, i5, i6);
    }

    public void drawPixmap(Pixmap pixmap, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.pixmap.drawPixmap(pixmap.pixmap, i, i2, i3, i4, i5, i6, i7, i8);
    }

    public void fillRectangle(int i, int i2, int i3, int i4) {
        this.pixmap.fillRect(i, i2, i3, i4, this.color);
    }

    public void drawCircle(int i, int i2, int i3) {
        this.pixmap.drawCircle(i, i2, i3, this.color);
    }

    public void fillCircle(int i, int i2, int i3) {
        this.pixmap.fillCircle(i, i2, i3, this.color);
    }

    public void fillTriangle(int i, int i2, int i3, int i4, int i5, int i6) {
        this.pixmap.fillTriangle(i, i2, i3, i4, i5, i6, this.color);
    }

    public int getPixel(int i, int i2) {
        return this.pixmap.getPixel(i, i2);
    }

    public int getWidth() {
        return this.pixmap.getWidth();
    }

    public int getHeight() {
        return this.pixmap.getHeight();
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if (this.disposed) {
            Gdx.app.error("Pixmap", "Pixmap already disposed!");
        } else {
            this.pixmap.dispose();
            this.disposed = true;
        }
    }

    public boolean isDisposed() {
        return this.disposed;
    }

    public void drawPixel(int i, int i2) {
        this.pixmap.setPixel(i, i2, this.color);
    }

    public void drawPixel(int i, int i2, int i3) {
        this.pixmap.setPixel(i, i2, i3);
    }

    public int getGLFormat() {
        return this.pixmap.getGLFormat();
    }

    public int getGLInternalFormat() {
        return this.pixmap.getGLInternalFormat();
    }

    public int getGLType() {
        return this.pixmap.getGLType();
    }

    public ByteBuffer getPixels() {
        if (this.disposed) {
            throw new GdxRuntimeException("Pixmap already disposed");
        }
        return this.pixmap.getPixels();
    }

    public void setPixels(ByteBuffer byteBuffer) {
        if (!byteBuffer.isDirect()) {
            throw new GdxRuntimeException("Couldn't setPixels from non-direct ByteBuffer");
        }
        ByteBuffer pixels = this.pixmap.getPixels();
        BufferUtils.copy(byteBuffer, pixels, pixels.limit());
    }

    public Format getFormat() {
        return Format.fromGdx2DPixmapFormat(this.pixmap.getFormat());
    }

    public Blending getBlending() {
        return this.blending;
    }

    public Filter getFilter() {
        return this.filter;
    }
}
