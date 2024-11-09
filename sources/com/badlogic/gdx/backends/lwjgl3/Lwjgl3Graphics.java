package com.badlogic.gdx.backends.lwjgl3;

import com.badlogic.gdx.AbstractGraphics;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.GL31;
import com.badlogic.gdx.graphics.GL32;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.GLVersion;
import com.badlogic.gdx.graphics.glutils.HdpiMode;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.system.Configuration;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/Lwjgl3Graphics.class */
public class Lwjgl3Graphics extends AbstractGraphics implements Disposable {
    final Lwjgl3Window window;
    GL20 gl20;
    private GL30 gl30;
    private GL31 gl31;
    private GL32 gl32;
    private GLVersion glVersion;
    private volatile int backBufferWidth;
    private volatile int backBufferHeight;
    private volatile int logicalWidth;
    private volatile int logicalHeight;
    private Graphics.BufferFormat bufferFormat;
    private float deltaTime;
    private long frameId;
    private int frames;
    private int fps;
    private int windowPosXBeforeFullscreen;
    private int windowPosYBeforeFullscreen;
    private int windowWidthBeforeFullscreen;
    private int windowHeightBeforeFullscreen;
    private volatile boolean isContinuous = true;
    private long lastFrameTime = -1;
    private boolean resetDeltaTime = false;
    private long frameCounterStart = 0;
    private Graphics.DisplayMode displayModeBeforeFullscreen = null;
    IntBuffer tmpBuffer = BufferUtils.createIntBuffer(1);
    IntBuffer tmpBuffer2 = BufferUtils.createIntBuffer(1);
    GLFWFramebufferSizeCallback resizeCallback = new GLFWFramebufferSizeCallback() { // from class: com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics.1
        @Override // org.lwjgl.glfw.GLFWFramebufferSizeCallbackI
        public void invoke(long j, int i, int i2) {
            if (!"glfw_async".equals(Configuration.GLFW_LIBRARY_NAME.get())) {
                Lwjgl3Graphics.this.updateFramebufferInfo();
                if (!Lwjgl3Graphics.this.window.isListenerInitialized()) {
                    return;
                }
                Lwjgl3Graphics.this.window.makeCurrent();
                Lwjgl3Graphics.this.gl20.glViewport(0, 0, Lwjgl3Graphics.this.backBufferWidth, Lwjgl3Graphics.this.backBufferHeight);
                Lwjgl3Graphics.this.window.getListener().resize(Lwjgl3Graphics.this.getWidth(), Lwjgl3Graphics.this.getHeight());
                Lwjgl3Graphics.this.update();
                Lwjgl3Graphics.this.window.getListener().render();
                GLFW.glfwSwapBuffers(j);
                return;
            }
            Lwjgl3Graphics.this.window.asyncResized = true;
        }
    };

    public Lwjgl3Graphics(Lwjgl3Window lwjgl3Window) {
        this.window = lwjgl3Window;
        if (lwjgl3Window.getConfig().glEmulation == Lwjgl3ApplicationConfiguration.GLEmulation.GL32) {
            Lwjgl3GL32 lwjgl3GL32 = new Lwjgl3GL32();
            this.gl32 = lwjgl3GL32;
            this.gl31 = lwjgl3GL32;
            this.gl30 = lwjgl3GL32;
            this.gl20 = lwjgl3GL32;
        } else if (lwjgl3Window.getConfig().glEmulation == Lwjgl3ApplicationConfiguration.GLEmulation.GL31) {
            Lwjgl3GL31 lwjgl3GL31 = new Lwjgl3GL31();
            this.gl31 = lwjgl3GL31;
            this.gl30 = lwjgl3GL31;
            this.gl20 = lwjgl3GL31;
        } else if (lwjgl3Window.getConfig().glEmulation == Lwjgl3ApplicationConfiguration.GLEmulation.GL30) {
            Lwjgl3GL30 lwjgl3GL30 = new Lwjgl3GL30();
            this.gl30 = lwjgl3GL30;
            this.gl20 = lwjgl3GL30;
        } else {
            try {
                this.gl20 = lwjgl3Window.getConfig().glEmulation == Lwjgl3ApplicationConfiguration.GLEmulation.GL20 ? new Lwjgl3GL20() : (GL20) Class.forName("com.badlogic.gdx.backends.lwjgl3.angle.Lwjgl3GLES20").newInstance();
                this.gl30 = null;
            } catch (Throwable th) {
                throw new GdxRuntimeException("Couldn't instantiate GLES20.", th);
            }
        }
        updateFramebufferInfo();
        initiateGL();
        GLFW.glfwSetFramebufferSizeCallback(lwjgl3Window.getWindowHandle(), this.resizeCallback);
    }

    private void initiateGL() {
        this.glVersion = new GLVersion(Application.ApplicationType.Desktop, this.gl20.glGetString(7938), this.gl20.glGetString(7936), this.gl20.glGetString(7937));
        if (supportsCubeMapSeamless()) {
            enableCubeMapSeamless(true);
        }
    }

    public boolean supportsCubeMapSeamless() {
        return this.glVersion.isVersionEqualToOrHigher(3, 2) || supportsExtension("GL_ARB_seamless_cube_map");
    }

    public void enableCubeMapSeamless(boolean z) {
        if (z) {
            this.gl20.glEnable(34895);
        } else {
            this.gl20.glDisable(34895);
        }
    }

    public Lwjgl3Window getWindow() {
        return this.window;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateFramebufferInfo() {
        GLFW.glfwGetFramebufferSize(this.window.getWindowHandle(), this.tmpBuffer, this.tmpBuffer2);
        this.backBufferWidth = this.tmpBuffer.get(0);
        this.backBufferHeight = this.tmpBuffer2.get(0);
        GLFW.glfwGetWindowSize(this.window.getWindowHandle(), this.tmpBuffer, this.tmpBuffer2);
        this.logicalWidth = this.tmpBuffer.get(0);
        this.logicalHeight = this.tmpBuffer2.get(0);
        Lwjgl3ApplicationConfiguration config = this.window.getConfig();
        this.bufferFormat = new Graphics.BufferFormat(config.r, config.g, config.f886b, config.f887a, config.depth, config.stencil, config.samples, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void update() {
        long nanoTime = System.nanoTime();
        if (this.lastFrameTime == -1) {
            this.lastFrameTime = nanoTime;
        }
        if (this.resetDeltaTime) {
            this.resetDeltaTime = false;
            this.deltaTime = 0.0f;
        } else {
            this.deltaTime = ((float) (nanoTime - this.lastFrameTime)) / 1.0E9f;
        }
        this.lastFrameTime = nanoTime;
        if (nanoTime - this.frameCounterStart >= 1000000000) {
            this.fps = this.frames;
            this.frames = 0;
            this.frameCounterStart = nanoTime;
        }
        this.frames++;
        this.frameId++;
    }

    @Override // com.badlogic.gdx.Graphics
    public boolean isGL30Available() {
        return this.gl30 != null;
    }

    @Override // com.badlogic.gdx.Graphics
    public boolean isGL31Available() {
        return this.gl31 != null;
    }

    @Override // com.badlogic.gdx.Graphics
    public boolean isGL32Available() {
        return this.gl32 != null;
    }

    @Override // com.badlogic.gdx.Graphics
    public GL20 getGL20() {
        return this.gl20;
    }

    @Override // com.badlogic.gdx.Graphics
    public GL30 getGL30() {
        return this.gl30;
    }

    @Override // com.badlogic.gdx.Graphics
    public GL31 getGL31() {
        return this.gl31;
    }

    @Override // com.badlogic.gdx.Graphics
    public GL32 getGL32() {
        return this.gl32;
    }

    @Override // com.badlogic.gdx.Graphics
    public void setGL20(GL20 gl20) {
        this.gl20 = gl20;
    }

    @Override // com.badlogic.gdx.Graphics
    public void setGL30(GL30 gl30) {
        this.gl30 = gl30;
    }

    @Override // com.badlogic.gdx.Graphics
    public void setGL31(GL31 gl31) {
        this.gl31 = gl31;
    }

    @Override // com.badlogic.gdx.Graphics
    public void setGL32(GL32 gl32) {
        this.gl32 = gl32;
    }

    @Override // com.badlogic.gdx.Graphics
    public int getWidth() {
        if (this.window.getConfig().hdpiMode == HdpiMode.Pixels) {
            return this.backBufferWidth;
        }
        return this.logicalWidth;
    }

    @Override // com.badlogic.gdx.Graphics
    public int getHeight() {
        if (this.window.getConfig().hdpiMode == HdpiMode.Pixels) {
            return this.backBufferHeight;
        }
        return this.logicalHeight;
    }

    @Override // com.badlogic.gdx.Graphics
    public int getBackBufferWidth() {
        return this.backBufferWidth;
    }

    @Override // com.badlogic.gdx.Graphics
    public int getBackBufferHeight() {
        return this.backBufferHeight;
    }

    public int getLogicalWidth() {
        return this.logicalWidth;
    }

    public int getLogicalHeight() {
        return this.logicalHeight;
    }

    @Override // com.badlogic.gdx.Graphics
    public long getFrameId() {
        return this.frameId;
    }

    @Override // com.badlogic.gdx.Graphics
    public float getDeltaTime() {
        return this.deltaTime;
    }

    public void resetDeltaTime() {
        this.resetDeltaTime = true;
    }

    @Override // com.badlogic.gdx.Graphics
    public int getFramesPerSecond() {
        return this.fps;
    }

    @Override // com.badlogic.gdx.Graphics
    public Graphics.GraphicsType getType() {
        return Graphics.GraphicsType.LWJGL3;
    }

    @Override // com.badlogic.gdx.Graphics
    public GLVersion getGLVersion() {
        return this.glVersion;
    }

    @Override // com.badlogic.gdx.Graphics
    public float getPpiX() {
        return getPpcX() * 2.54f;
    }

    @Override // com.badlogic.gdx.Graphics
    public float getPpiY() {
        return getPpcY() * 2.54f;
    }

    @Override // com.badlogic.gdx.Graphics
    public float getPpcX() {
        GLFW.glfwGetMonitorPhysicalSize(((Lwjgl3Monitor) getMonitor()).monitorHandle, this.tmpBuffer, this.tmpBuffer2);
        return (getDisplayMode().width / this.tmpBuffer.get(0)) * 10.0f;
    }

    @Override // com.badlogic.gdx.Graphics
    public float getPpcY() {
        GLFW.glfwGetMonitorPhysicalSize(((Lwjgl3Monitor) getMonitor()).monitorHandle, this.tmpBuffer, this.tmpBuffer2);
        return (getDisplayMode().height / this.tmpBuffer2.get(0)) * 10.0f;
    }

    @Override // com.badlogic.gdx.Graphics
    public boolean supportsDisplayModeChange() {
        return true;
    }

    @Override // com.badlogic.gdx.Graphics
    public Graphics.Monitor getPrimaryMonitor() {
        return Lwjgl3ApplicationConfiguration.toLwjgl3Monitor(GLFW.glfwGetPrimaryMonitor());
    }

    @Override // com.badlogic.gdx.Graphics
    public Graphics.Monitor getMonitor() {
        Graphics.Monitor[] monitors = getMonitors();
        Graphics.Monitor monitor = monitors[0];
        GLFW.glfwGetWindowPos(this.window.getWindowHandle(), this.tmpBuffer, this.tmpBuffer2);
        int i = this.tmpBuffer.get(0);
        int i2 = this.tmpBuffer2.get(0);
        GLFW.glfwGetWindowSize(this.window.getWindowHandle(), this.tmpBuffer, this.tmpBuffer2);
        int i3 = this.tmpBuffer.get(0);
        int i4 = this.tmpBuffer2.get(0);
        int i5 = 0;
        for (Graphics.Monitor monitor2 : monitors) {
            Graphics.DisplayMode displayMode = getDisplayMode(monitor2);
            int max = Math.max(0, Math.min(i + i3, monitor2.virtualX + displayMode.width) - Math.max(i, monitor2.virtualX)) * Math.max(0, Math.min(i2 + i4, monitor2.virtualY + displayMode.height) - Math.max(i2, monitor2.virtualY));
            if (i5 < max) {
                i5 = max;
                monitor = monitor2;
            }
        }
        return monitor;
    }

    @Override // com.badlogic.gdx.Graphics
    public Graphics.Monitor[] getMonitors() {
        PointerBuffer glfwGetMonitors = GLFW.glfwGetMonitors();
        Graphics.Monitor[] monitorArr = new Graphics.Monitor[glfwGetMonitors.limit()];
        for (int i = 0; i < glfwGetMonitors.limit(); i++) {
            monitorArr[i] = Lwjgl3ApplicationConfiguration.toLwjgl3Monitor(glfwGetMonitors.get(i));
        }
        return monitorArr;
    }

    @Override // com.badlogic.gdx.Graphics
    public Graphics.DisplayMode[] getDisplayModes() {
        return Lwjgl3ApplicationConfiguration.getDisplayModes(getMonitor());
    }

    @Override // com.badlogic.gdx.Graphics
    public Graphics.DisplayMode[] getDisplayModes(Graphics.Monitor monitor) {
        return Lwjgl3ApplicationConfiguration.getDisplayModes(monitor);
    }

    @Override // com.badlogic.gdx.Graphics
    public Graphics.DisplayMode getDisplayMode() {
        return Lwjgl3ApplicationConfiguration.getDisplayMode(getMonitor());
    }

    @Override // com.badlogic.gdx.Graphics
    public Graphics.DisplayMode getDisplayMode(Graphics.Monitor monitor) {
        return Lwjgl3ApplicationConfiguration.getDisplayMode(monitor);
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
        this.window.getInput().resetPollingStates();
        Lwjgl3DisplayMode lwjgl3DisplayMode = (Lwjgl3DisplayMode) displayMode;
        if (isFullscreen()) {
            Lwjgl3DisplayMode lwjgl3DisplayMode2 = (Lwjgl3DisplayMode) getDisplayMode();
            if (lwjgl3DisplayMode2.getMonitor() == lwjgl3DisplayMode.getMonitor() && lwjgl3DisplayMode2.refreshRate == lwjgl3DisplayMode.refreshRate) {
                GLFW.glfwSetWindowSize(this.window.getWindowHandle(), lwjgl3DisplayMode.width, lwjgl3DisplayMode.height);
            } else {
                GLFW.glfwSetWindowMonitor(this.window.getWindowHandle(), lwjgl3DisplayMode.getMonitor(), 0, 0, lwjgl3DisplayMode.width, lwjgl3DisplayMode.height, lwjgl3DisplayMode.refreshRate);
            }
        } else {
            storeCurrentWindowPositionAndDisplayMode();
            GLFW.glfwSetWindowMonitor(this.window.getWindowHandle(), lwjgl3DisplayMode.getMonitor(), 0, 0, lwjgl3DisplayMode.width, lwjgl3DisplayMode.height, lwjgl3DisplayMode.refreshRate);
        }
        updateFramebufferInfo();
        setVSync(this.window.getConfig().vSyncEnabled);
        return true;
    }

    private void storeCurrentWindowPositionAndDisplayMode() {
        this.windowPosXBeforeFullscreen = this.window.getPositionX();
        this.windowPosYBeforeFullscreen = this.window.getPositionY();
        this.windowWidthBeforeFullscreen = this.logicalWidth;
        this.windowHeightBeforeFullscreen = this.logicalHeight;
        this.displayModeBeforeFullscreen = getDisplayMode();
    }

    @Override // com.badlogic.gdx.Graphics
    public boolean setWindowedMode(int i, int i2) {
        this.window.getInput().resetPollingStates();
        if (!isFullscreen()) {
            GridPoint2 gridPoint2 = null;
            boolean z = false;
            if (i != this.logicalWidth || i2 != this.logicalHeight) {
                z = true;
                gridPoint2 = Lwjgl3ApplicationConfiguration.calculateCenteredWindowPosition((Lwjgl3Monitor) getMonitor(), i, i2);
            }
            GLFW.glfwSetWindowSize(this.window.getWindowHandle(), i, i2);
            if (z) {
                this.window.setPosition(gridPoint2.x, gridPoint2.y);
            }
        } else {
            if (this.displayModeBeforeFullscreen == null) {
                storeCurrentWindowPositionAndDisplayMode();
            }
            if (i != this.windowWidthBeforeFullscreen || i2 != this.windowHeightBeforeFullscreen) {
                GridPoint2 calculateCenteredWindowPosition = Lwjgl3ApplicationConfiguration.calculateCenteredWindowPosition((Lwjgl3Monitor) getMonitor(), i, i2);
                GLFW.glfwSetWindowMonitor(this.window.getWindowHandle(), 0L, calculateCenteredWindowPosition.x, calculateCenteredWindowPosition.y, i, i2, this.displayModeBeforeFullscreen.refreshRate);
            } else {
                GLFW.glfwSetWindowMonitor(this.window.getWindowHandle(), 0L, this.windowPosXBeforeFullscreen, this.windowPosYBeforeFullscreen, i, i2, this.displayModeBeforeFullscreen.refreshRate);
            }
        }
        updateFramebufferInfo();
        return true;
    }

    @Override // com.badlogic.gdx.Graphics
    public void setTitle(String str) {
        if (str == null) {
            str = "";
        }
        GLFW.glfwSetWindowTitle(this.window.getWindowHandle(), str);
    }

    @Override // com.badlogic.gdx.Graphics
    public void setUndecorated(boolean z) {
        getWindow().getConfig().setDecorated(!z);
        GLFW.glfwSetWindowAttrib(this.window.getWindowHandle(), 131077, z ? 0 : 1);
    }

    @Override // com.badlogic.gdx.Graphics
    public void setResizable(boolean z) {
        getWindow().getConfig().setResizable(z);
        GLFW.glfwSetWindowAttrib(this.window.getWindowHandle(), 131075, z ? 1 : 0);
    }

    @Override // com.badlogic.gdx.Graphics
    public void setVSync(boolean z) {
        getWindow().getConfig().vSyncEnabled = z;
        GLFW.glfwSwapInterval(z ? 1 : 0);
    }

    @Override // com.badlogic.gdx.Graphics
    public void setForegroundFPS(int i) {
        getWindow().getConfig().foregroundFPS = i;
    }

    @Override // com.badlogic.gdx.Graphics
    public Graphics.BufferFormat getBufferFormat() {
        return this.bufferFormat;
    }

    @Override // com.badlogic.gdx.Graphics
    public boolean supportsExtension(String str) {
        return GLFW.glfwExtensionSupported(str);
    }

    @Override // com.badlogic.gdx.Graphics
    public void setContinuousRendering(boolean z) {
        this.isContinuous = z;
    }

    @Override // com.badlogic.gdx.Graphics
    public boolean isContinuousRendering() {
        return this.isContinuous;
    }

    @Override // com.badlogic.gdx.Graphics
    public void requestRendering() {
        this.window.requestRendering();
    }

    @Override // com.badlogic.gdx.Graphics
    public boolean isFullscreen() {
        return GLFW.glfwGetWindowMonitor(this.window.getWindowHandle()) != 0;
    }

    @Override // com.badlogic.gdx.Graphics
    public Cursor newCursor(Pixmap pixmap, int i, int i2) {
        return new Lwjgl3Cursor(getWindow(), pixmap, i, i2);
    }

    @Override // com.badlogic.gdx.Graphics
    public void setCursor(Cursor cursor) {
        GLFW.glfwSetCursor(getWindow().getWindowHandle(), ((Lwjgl3Cursor) cursor).glfwCursor);
    }

    @Override // com.badlogic.gdx.Graphics
    public void setSystemCursor(Cursor.SystemCursor systemCursor) {
        Lwjgl3Cursor.setSystemCursor(getWindow().getWindowHandle(), systemCursor);
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.resizeCallback.free();
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/Lwjgl3Graphics$Lwjgl3DisplayMode.class */
    public static class Lwjgl3DisplayMode extends Graphics.DisplayMode {
        final long monitorHandle;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Lwjgl3DisplayMode(long j, int i, int i2, int i3, int i4) {
            super(i, i2, i3, i4);
            this.monitorHandle = j;
        }

        public long getMonitor() {
            return this.monitorHandle;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/Lwjgl3Graphics$Lwjgl3Monitor.class */
    public static class Lwjgl3Monitor extends Graphics.Monitor {
        final long monitorHandle;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Lwjgl3Monitor(long j, int i, int i2, String str) {
            super(i, i2, str);
            this.monitorHandle = j;
        }

        public long getMonitorHandle() {
            return this.monitorHandle;
        }
    }
}
