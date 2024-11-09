package com.badlogic.gdx;

import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.GL31;
import com.badlogic.gdx.graphics.GL32;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.GLVersion;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/Graphics.class */
public interface Graphics {

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/Graphics$GraphicsType.class */
    public enum GraphicsType {
        AndroidGL,
        LWJGL,
        WebGL,
        iOSGL,
        JGLFW,
        Mock,
        LWJGL3
    }

    boolean isGL30Available();

    boolean isGL31Available();

    boolean isGL32Available();

    GL20 getGL20();

    GL30 getGL30();

    GL31 getGL31();

    GL32 getGL32();

    void setGL20(GL20 gl20);

    void setGL30(GL30 gl30);

    void setGL31(GL31 gl31);

    void setGL32(GL32 gl32);

    int getWidth();

    int getHeight();

    int getBackBufferWidth();

    int getBackBufferHeight();

    float getBackBufferScale();

    int getSafeInsetLeft();

    int getSafeInsetTop();

    int getSafeInsetBottom();

    int getSafeInsetRight();

    long getFrameId();

    float getDeltaTime();

    @Deprecated
    float getRawDeltaTime();

    int getFramesPerSecond();

    GraphicsType getType();

    GLVersion getGLVersion();

    float getPpiX();

    float getPpiY();

    float getPpcX();

    float getPpcY();

    float getDensity();

    boolean supportsDisplayModeChange();

    Monitor getPrimaryMonitor();

    Monitor getMonitor();

    Monitor[] getMonitors();

    DisplayMode[] getDisplayModes();

    DisplayMode[] getDisplayModes(Monitor monitor);

    DisplayMode getDisplayMode();

    DisplayMode getDisplayMode(Monitor monitor);

    boolean setFullscreenMode(DisplayMode displayMode);

    boolean setWindowedMode(int i, int i2);

    void setTitle(String str);

    void setUndecorated(boolean z);

    void setResizable(boolean z);

    void setVSync(boolean z);

    void setForegroundFPS(int i);

    BufferFormat getBufferFormat();

    boolean supportsExtension(String str);

    void setContinuousRendering(boolean z);

    boolean isContinuousRendering();

    void requestRendering();

    boolean isFullscreen();

    Cursor newCursor(Pixmap pixmap, int i, int i2);

    void setCursor(Cursor cursor);

    void setSystemCursor(Cursor.SystemCursor systemCursor);

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/Graphics$DisplayMode.class */
    public static class DisplayMode {
        public final int width;
        public final int height;
        public final int refreshRate;
        public final int bitsPerPixel;

        /* JADX INFO: Access modifiers changed from: protected */
        public DisplayMode(int i, int i2, int i3, int i4) {
            this.width = i;
            this.height = i2;
            this.refreshRate = i3;
            this.bitsPerPixel = i4;
        }

        public String toString() {
            return this.width + "x" + this.height + ", bpp: " + this.bitsPerPixel + ", hz: " + this.refreshRate;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/Graphics$Monitor.class */
    public static class Monitor {
        public final int virtualX;
        public final int virtualY;
        public final String name;

        /* JADX INFO: Access modifiers changed from: protected */
        public Monitor(int i, int i2, String str) {
            this.virtualX = i;
            this.virtualY = i2;
            this.name = str;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/Graphics$BufferFormat.class */
    public static class BufferFormat {
        public final int r;
        public final int g;

        /* renamed from: b, reason: collision with root package name */
        public final int f882b;

        /* renamed from: a, reason: collision with root package name */
        public final int f883a;
        public final int depth;
        public final int stencil;
        public final int samples;
        public final boolean coverageSampling;

        public BufferFormat(int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z) {
            this.r = i;
            this.g = i2;
            this.f882b = i3;
            this.f883a = i4;
            this.depth = i5;
            this.stencil = i6;
            this.samples = i7;
            this.coverageSampling = z;
        }

        public String toString() {
            return "r: " + this.r + ", g: " + this.g + ", b: " + this.f882b + ", a: " + this.f883a + ", depth: " + this.depth + ", stencil: " + this.stencil + ", num samples: " + this.samples + ", coverage sampling: " + this.coverageSampling;
        }
    }
}
