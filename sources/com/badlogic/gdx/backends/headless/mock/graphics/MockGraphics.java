package com.badlogic.gdx.backends.headless.mock.graphics;

import com.badlogic.gdx.AbstractGraphics;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.GL31;
import com.badlogic.gdx.graphics.GL32;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.GLVersion;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/headless/mock/graphics/MockGraphics.class */
public class MockGraphics extends AbstractGraphics {
    int fps;
    long targetRenderInterval;
    long frameId = -1;
    float deltaTime = 0.0f;
    long frameStart = 0;
    int frames = 0;
    long lastTime = System.nanoTime();
    GLVersion glVersion = new GLVersion(Application.ApplicationType.HeadlessDesktop, "", "", "");

    @Override // com.badlogic.gdx.Graphics
    public boolean isGL30Available() {
        return false;
    }

    @Override // com.badlogic.gdx.Graphics
    public boolean isGL31Available() {
        return false;
    }

    @Override // com.badlogic.gdx.Graphics
    public boolean isGL32Available() {
        return false;
    }

    @Override // com.badlogic.gdx.Graphics
    public GL20 getGL20() {
        return null;
    }

    @Override // com.badlogic.gdx.Graphics
    public void setGL20(GL20 gl20) {
    }

    @Override // com.badlogic.gdx.Graphics
    public GL30 getGL30() {
        return null;
    }

    @Override // com.badlogic.gdx.Graphics
    public void setGL30(GL30 gl30) {
    }

    @Override // com.badlogic.gdx.Graphics
    public GL31 getGL31() {
        return null;
    }

    @Override // com.badlogic.gdx.Graphics
    public void setGL31(GL31 gl31) {
    }

    @Override // com.badlogic.gdx.Graphics
    public GL32 getGL32() {
        return null;
    }

    @Override // com.badlogic.gdx.Graphics
    public void setGL32(GL32 gl32) {
    }

    @Override // com.badlogic.gdx.Graphics
    public int getWidth() {
        return 0;
    }

    @Override // com.badlogic.gdx.Graphics
    public int getHeight() {
        return 0;
    }

    @Override // com.badlogic.gdx.Graphics
    public int getBackBufferWidth() {
        return 0;
    }

    @Override // com.badlogic.gdx.Graphics
    public int getBackBufferHeight() {
        return 0;
    }

    @Override // com.badlogic.gdx.Graphics
    public long getFrameId() {
        return this.frameId;
    }

    @Override // com.badlogic.gdx.Graphics
    public float getDeltaTime() {
        return this.deltaTime;
    }

    @Override // com.badlogic.gdx.Graphics
    public int getFramesPerSecond() {
        return this.fps;
    }

    @Override // com.badlogic.gdx.Graphics
    public Graphics.GraphicsType getType() {
        return Graphics.GraphicsType.Mock;
    }

    @Override // com.badlogic.gdx.Graphics
    public GLVersion getGLVersion() {
        return this.glVersion;
    }

    @Override // com.badlogic.gdx.Graphics
    public float getPpiX() {
        return 0.0f;
    }

    @Override // com.badlogic.gdx.Graphics
    public float getPpiY() {
        return 0.0f;
    }

    @Override // com.badlogic.gdx.Graphics
    public float getPpcX() {
        return 0.0f;
    }

    @Override // com.badlogic.gdx.Graphics
    public float getPpcY() {
        return 0.0f;
    }

    @Override // com.badlogic.gdx.Graphics
    public boolean supportsDisplayModeChange() {
        return false;
    }

    @Override // com.badlogic.gdx.Graphics
    public Graphics.DisplayMode[] getDisplayModes() {
        return new Graphics.DisplayMode[0];
    }

    @Override // com.badlogic.gdx.Graphics
    public Graphics.DisplayMode getDisplayMode() {
        return null;
    }

    @Override // com.badlogic.gdx.Graphics
    public int getSafeInsetLeft() {
        return 0;
    }

    @Override // com.badlogic.gdx.Graphics
    public int getSafeInsetTop() {
        return 0;
    }

    @Override // com.badlogic.gdx.Graphics
    public int getSafeInsetBottom() {
        return 0;
    }

    @Override // com.badlogic.gdx.Graphics
    public int getSafeInsetRight() {
        return 0;
    }

    @Override // com.badlogic.gdx.Graphics
    public boolean setFullscreenMode(Graphics.DisplayMode displayMode) {
        return false;
    }

    @Override // com.badlogic.gdx.Graphics
    public boolean setWindowedMode(int i, int i2) {
        return false;
    }

    @Override // com.badlogic.gdx.Graphics
    public void setTitle(String str) {
    }

    @Override // com.badlogic.gdx.Graphics
    public void setVSync(boolean z) {
    }

    @Override // com.badlogic.gdx.Graphics
    public void setForegroundFPS(int i) {
        float f;
        if (i <= 0) {
            f = i == 0 ? 0 : -1;
        } else {
            f = (1.0f / i) * 1.0E9f;
        }
        this.targetRenderInterval = f;
    }

    public long getTargetRenderInterval() {
        return this.targetRenderInterval;
    }

    @Override // com.badlogic.gdx.Graphics
    public Graphics.BufferFormat getBufferFormat() {
        return null;
    }

    @Override // com.badlogic.gdx.Graphics
    public boolean supportsExtension(String str) {
        return false;
    }

    @Override // com.badlogic.gdx.Graphics
    public void setContinuousRendering(boolean z) {
    }

    @Override // com.badlogic.gdx.Graphics
    public boolean isContinuousRendering() {
        return false;
    }

    @Override // com.badlogic.gdx.Graphics
    public void requestRendering() {
    }

    @Override // com.badlogic.gdx.Graphics
    public boolean isFullscreen() {
        return false;
    }

    public void updateTime() {
        long nanoTime = System.nanoTime();
        this.deltaTime = ((float) (nanoTime - this.lastTime)) / 1.0E9f;
        this.lastTime = nanoTime;
        if (nanoTime - this.frameStart >= 1000000000) {
            this.fps = this.frames;
            this.frames = 0;
            this.frameStart = nanoTime;
        }
        this.frames++;
    }

    public void incrementFrameId() {
        this.frameId++;
    }

    @Override // com.badlogic.gdx.Graphics
    public Cursor newCursor(Pixmap pixmap, int i, int i2) {
        return null;
    }

    @Override // com.badlogic.gdx.Graphics
    public void setCursor(Cursor cursor) {
    }

    @Override // com.badlogic.gdx.Graphics
    public void setSystemCursor(Cursor.SystemCursor systemCursor) {
    }

    @Override // com.badlogic.gdx.Graphics
    public Graphics.Monitor getPrimaryMonitor() {
        return null;
    }

    @Override // com.badlogic.gdx.Graphics
    public Graphics.Monitor getMonitor() {
        return null;
    }

    @Override // com.badlogic.gdx.Graphics
    public Graphics.Monitor[] getMonitors() {
        return null;
    }

    @Override // com.badlogic.gdx.Graphics
    public Graphics.DisplayMode[] getDisplayModes(Graphics.Monitor monitor) {
        return null;
    }

    @Override // com.badlogic.gdx.Graphics
    public Graphics.DisplayMode getDisplayMode(Graphics.Monitor monitor) {
        return null;
    }

    @Override // com.badlogic.gdx.Graphics
    public void setUndecorated(boolean z) {
    }

    @Override // com.badlogic.gdx.Graphics
    public void setResizable(boolean z) {
    }
}
