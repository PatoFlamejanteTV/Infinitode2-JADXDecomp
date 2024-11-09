package com.badlogic.gdx.backends.lwjgl3;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.GL31;
import com.badlogic.gdx.graphics.GL32;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Os;
import com.badlogic.gdx.utils.SharedLibraryLoader;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWDropCallback;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.glfw.GLFWWindowCloseCallback;
import org.lwjgl.glfw.GLFWWindowFocusCallback;
import org.lwjgl.glfw.GLFWWindowIconifyCallback;
import org.lwjgl.glfw.GLFWWindowMaximizeCallback;
import org.lwjgl.glfw.GLFWWindowRefreshCallback;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/Lwjgl3Window.class */
public class Lwjgl3Window implements Disposable {
    private long windowHandle;
    final ApplicationListener listener;
    private final Array<LifecycleListener> lifecycleListeners;
    final Lwjgl3ApplicationBase application;
    Lwjgl3WindowListener windowListener;
    private Lwjgl3Graphics graphics;
    private Lwjgl3Input input;
    private final Lwjgl3ApplicationConfiguration config;
    private boolean listenerInitialized = false;
    private final Array<Runnable> runnables = new Array<>();
    private final Array<Runnable> executedRunnables = new Array<>();
    boolean iconified = false;
    boolean focused = false;
    boolean asyncResized = false;
    private boolean requestRendering = false;
    private final GLFWWindowFocusCallback focusCallback = new GLFWWindowFocusCallback() { // from class: com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window.1
        @Override // org.lwjgl.glfw.GLFWWindowFocusCallbackI
        public void invoke(long j, final boolean z) {
            Lwjgl3Window.this.postRunnable(new Runnable() { // from class: com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window.1.1
                @Override // java.lang.Runnable
                public void run() {
                    if (Lwjgl3Window.this.windowListener != null) {
                        if (z) {
                            if (Lwjgl3Window.this.config.pauseWhenLostFocus) {
                                synchronized (Lwjgl3Window.this.lifecycleListeners) {
                                    Array.ArrayIterator it = Lwjgl3Window.this.lifecycleListeners.iterator();
                                    while (it.hasNext()) {
                                        ((LifecycleListener) it.next()).resume();
                                    }
                                }
                            }
                            Lwjgl3Window.this.windowListener.focusGained();
                        } else {
                            Lwjgl3Window.this.windowListener.focusLost();
                            if (Lwjgl3Window.this.config.pauseWhenLostFocus) {
                                synchronized (Lwjgl3Window.this.lifecycleListeners) {
                                    Array.ArrayIterator it2 = Lwjgl3Window.this.lifecycleListeners.iterator();
                                    while (it2.hasNext()) {
                                        ((LifecycleListener) it2.next()).pause();
                                    }
                                }
                                Lwjgl3Window.this.listener.pause();
                            }
                        }
                        Lwjgl3Window.this.focused = z;
                    }
                }
            });
        }
    };
    private final GLFWWindowIconifyCallback iconifyCallback = new GLFWWindowIconifyCallback() { // from class: com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window.2
        @Override // org.lwjgl.glfw.GLFWWindowIconifyCallbackI
        public void invoke(long j, final boolean z) {
            Lwjgl3Window.this.postRunnable(new Runnable() { // from class: com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window.2.1
                @Override // java.lang.Runnable
                public void run() {
                    if (Lwjgl3Window.this.windowListener != null) {
                        Lwjgl3Window.this.windowListener.iconified(z);
                    }
                    Lwjgl3Window.this.iconified = z;
                    if (z) {
                        if (Lwjgl3Window.this.config.pauseWhenMinimized) {
                            synchronized (Lwjgl3Window.this.lifecycleListeners) {
                                Array.ArrayIterator it = Lwjgl3Window.this.lifecycleListeners.iterator();
                                while (it.hasNext()) {
                                    ((LifecycleListener) it.next()).pause();
                                }
                            }
                            Lwjgl3Window.this.listener.pause();
                            return;
                        }
                        return;
                    }
                    if (Lwjgl3Window.this.config.pauseWhenMinimized) {
                        synchronized (Lwjgl3Window.this.lifecycleListeners) {
                            Array.ArrayIterator it2 = Lwjgl3Window.this.lifecycleListeners.iterator();
                            while (it2.hasNext()) {
                                ((LifecycleListener) it2.next()).resume();
                            }
                        }
                        Lwjgl3Window.this.listener.resume();
                    }
                }
            });
        }
    };
    private final GLFWWindowMaximizeCallback maximizeCallback = new GLFWWindowMaximizeCallback() { // from class: com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window.3
        @Override // org.lwjgl.glfw.GLFWWindowMaximizeCallbackI
        public void invoke(long j, final boolean z) {
            Lwjgl3Window.this.postRunnable(new Runnable() { // from class: com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window.3.1
                @Override // java.lang.Runnable
                public void run() {
                    if (Lwjgl3Window.this.windowListener != null) {
                        Lwjgl3Window.this.windowListener.maximized(z);
                    }
                }
            });
        }
    };
    private final GLFWWindowCloseCallback closeCallback = new GLFWWindowCloseCallback() { // from class: com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window.4
        @Override // org.lwjgl.glfw.GLFWWindowCloseCallbackI
        public void invoke(final long j) {
            Lwjgl3Window.this.postRunnable(new Runnable() { // from class: com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window.4.1
                @Override // java.lang.Runnable
                public void run() {
                    if (Lwjgl3Window.this.windowListener != null && !Lwjgl3Window.this.windowListener.closeRequested()) {
                        GLFW.glfwSetWindowShouldClose(j, false);
                    }
                }
            });
        }
    };
    private final GLFWDropCallback dropCallback = new GLFWDropCallback() { // from class: com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window.5
        @Override // org.lwjgl.glfw.GLFWDropCallbackI
        public void invoke(long j, int i, long j2) {
            final String[] strArr = new String[i];
            for (int i2 = 0; i2 < i; i2++) {
                strArr[i2] = getName(j2, i2);
            }
            Lwjgl3Window.this.postRunnable(new Runnable() { // from class: com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window.5.1
                @Override // java.lang.Runnable
                public void run() {
                    if (Lwjgl3Window.this.windowListener != null) {
                        Lwjgl3Window.this.windowListener.filesDropped(strArr);
                    }
                }
            });
        }
    };
    private final GLFWWindowRefreshCallback refreshCallback = new GLFWWindowRefreshCallback() { // from class: com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window.6
        @Override // org.lwjgl.glfw.GLFWWindowRefreshCallbackI
        public void invoke(long j) {
            Lwjgl3Window.this.postRunnable(new Runnable() { // from class: com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window.6.1
                @Override // java.lang.Runnable
                public void run() {
                    if (Lwjgl3Window.this.windowListener != null) {
                        Lwjgl3Window.this.windowListener.refreshRequested();
                    }
                }
            });
        }
    };
    private final IntBuffer tmpBuffer = BufferUtils.createIntBuffer(1);
    private final IntBuffer tmpBuffer2 = BufferUtils.createIntBuffer(1);

    /* JADX INFO: Access modifiers changed from: package-private */
    public Lwjgl3Window(ApplicationListener applicationListener, Array<LifecycleListener> array, Lwjgl3ApplicationConfiguration lwjgl3ApplicationConfiguration, Lwjgl3ApplicationBase lwjgl3ApplicationBase) {
        this.listener = applicationListener;
        this.lifecycleListeners = array;
        this.windowListener = lwjgl3ApplicationConfiguration.windowListener;
        this.config = lwjgl3ApplicationConfiguration;
        this.application = lwjgl3ApplicationBase;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void create(long j) {
        this.windowHandle = j;
        this.input = this.application.createInput(this);
        this.graphics = new Lwjgl3Graphics(this);
        GLFW.glfwSetWindowFocusCallback(j, this.focusCallback);
        GLFW.glfwSetWindowIconifyCallback(j, this.iconifyCallback);
        GLFW.glfwSetWindowMaximizeCallback(j, this.maximizeCallback);
        GLFW.glfwSetWindowCloseCallback(j, this.closeCallback);
        GLFW.glfwSetDropCallback(j, this.dropCallback);
        GLFW.glfwSetWindowRefreshCallback(j, this.refreshCallback);
        if (this.windowListener != null) {
            this.windowListener.created(this);
        }
    }

    public ApplicationListener getListener() {
        return this.listener;
    }

    public Lwjgl3WindowListener getWindowListener() {
        return this.windowListener;
    }

    public void setWindowListener(Lwjgl3WindowListener lwjgl3WindowListener) {
        this.windowListener = lwjgl3WindowListener;
    }

    public void postRunnable(Runnable runnable) {
        synchronized (this.runnables) {
            this.runnables.add(runnable);
        }
    }

    public void setPosition(int i, int i2) {
        GLFW.glfwSetWindowPos(this.windowHandle, i, i2);
    }

    public int getPositionX() {
        GLFW.glfwGetWindowPos(this.windowHandle, this.tmpBuffer, this.tmpBuffer2);
        return this.tmpBuffer.get(0);
    }

    public int getPositionY() {
        GLFW.glfwGetWindowPos(this.windowHandle, this.tmpBuffer, this.tmpBuffer2);
        return this.tmpBuffer2.get(0);
    }

    public void setVisible(boolean z) {
        if (z) {
            GLFW.glfwShowWindow(this.windowHandle);
        } else {
            GLFW.glfwHideWindow(this.windowHandle);
        }
    }

    public void closeWindow() {
        GLFW.glfwSetWindowShouldClose(this.windowHandle, true);
    }

    public void iconifyWindow() {
        GLFW.glfwIconifyWindow(this.windowHandle);
    }

    public boolean isIconified() {
        return this.iconified;
    }

    public void restoreWindow() {
        GLFW.glfwRestoreWindow(this.windowHandle);
    }

    public void maximizeWindow() {
        GLFW.glfwMaximizeWindow(this.windowHandle);
    }

    public void focusWindow() {
        GLFW.glfwFocusWindow(this.windowHandle);
    }

    public boolean isFocused() {
        return this.focused;
    }

    public void setIcon(Pixmap... pixmapArr) {
        setIcon(this.windowHandle, pixmapArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setIcon(long j, String[] strArr, Files.FileType fileType) {
        if (SharedLibraryLoader.os == Os.MacOsX) {
            return;
        }
        Pixmap[] pixmapArr = new Pixmap[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            pixmapArr[i] = new Pixmap(Gdx.files.getFileHandle(strArr[i], fileType));
        }
        setIcon(j, pixmapArr);
        for (Pixmap pixmap : pixmapArr) {
            pixmap.dispose();
        }
    }

    static void setIcon(long j, Pixmap[] pixmapArr) {
        if (SharedLibraryLoader.os == Os.MacOsX) {
            return;
        }
        GLFWImage.Buffer malloc = GLFWImage.malloc(pixmapArr.length);
        Pixmap[] pixmapArr2 = new Pixmap[pixmapArr.length];
        for (int i = 0; i < pixmapArr.length; i++) {
            Pixmap pixmap = pixmapArr[i];
            Pixmap pixmap2 = pixmap;
            if (pixmap.getFormat() != Pixmap.Format.RGBA8888) {
                Pixmap pixmap3 = new Pixmap(pixmap2.getWidth(), pixmap2.getHeight(), Pixmap.Format.RGBA8888);
                pixmap3.setBlending(Pixmap.Blending.None);
                pixmap3.drawPixmap(pixmap2, 0, 0);
                pixmapArr2[i] = pixmap3;
                pixmap2 = pixmap3;
            }
            GLFWImage malloc2 = GLFWImage.malloc();
            malloc2.set(pixmap2.getWidth(), pixmap2.getHeight(), pixmap2.getPixels());
            malloc.put((GLFWImage.Buffer) malloc2);
            malloc2.free();
        }
        malloc.position(0);
        GLFW.glfwSetWindowIcon(j, malloc);
        malloc.free();
        for (Pixmap pixmap4 : pixmapArr2) {
            if (pixmap4 != null) {
                pixmap4.dispose();
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        GLFW.glfwSetWindowTitle(this.windowHandle, charSequence);
    }

    public void setSizeLimits(int i, int i2, int i3, int i4) {
        setSizeLimits(this.windowHandle, i, i2, i3, i4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setSizeLimits(long j, int i, int i2, int i3, int i4) {
        GLFW.glfwSetWindowSizeLimits(j, i >= 0 ? i : -1, i2 >= 0 ? i2 : -1, i3 >= 0 ? i3 : -1, i4 >= 0 ? i4 : -1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Lwjgl3Graphics getGraphics() {
        return this.graphics;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Lwjgl3Input getInput() {
        return this.input;
    }

    public long getWindowHandle() {
        return this.windowHandle;
    }

    void windowHandleChanged(long j) {
        this.windowHandle = j;
        this.input.windowHandleChanged(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean update() {
        boolean z;
        if (!this.listenerInitialized) {
            initializeListener();
        }
        synchronized (this.runnables) {
            this.executedRunnables.addAll(this.runnables);
            this.runnables.clear();
        }
        Array.ArrayIterator<Runnable> it = this.executedRunnables.iterator();
        while (it.hasNext()) {
            it.next().run();
        }
        boolean z2 = this.executedRunnables.size > 0 || this.graphics.isContinuousRendering();
        this.executedRunnables.clear();
        if (!this.iconified) {
            this.input.update();
        }
        synchronized (this) {
            z = z2 | (this.requestRendering && !this.iconified);
            this.requestRendering = false;
        }
        if (this.asyncResized) {
            this.asyncResized = false;
            this.graphics.updateFramebufferInfo();
            this.graphics.gl20.glViewport(0, 0, this.graphics.getBackBufferWidth(), this.graphics.getBackBufferHeight());
            this.listener.resize(this.graphics.getWidth(), this.graphics.getHeight());
            this.graphics.update();
            this.listener.render();
            GLFW.glfwSwapBuffers(this.windowHandle);
            return true;
        }
        if (z) {
            this.graphics.update();
            this.listener.render();
            GLFW.glfwSwapBuffers(this.windowHandle);
        }
        if (!this.iconified) {
            this.input.prepareNext();
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void requestRendering() {
        synchronized (this) {
            this.requestRendering = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(this.windowHandle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Lwjgl3ApplicationConfiguration getConfig() {
        return this.config;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isListenerInitialized() {
        return this.listenerInitialized;
    }

    void initializeListener() {
        if (!this.listenerInitialized) {
            this.listener.create();
            this.listener.resize(this.graphics.getWidth(), this.graphics.getHeight());
            this.listenerInitialized = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void makeCurrent() {
        Gdx.graphics = this.graphics;
        GL32 gl32 = this.graphics.getGL32();
        Gdx.gl32 = gl32;
        GL31 gl31 = gl32 != null ? Gdx.gl32 : this.graphics.getGL31();
        Gdx.gl31 = gl31;
        GL30 gl30 = gl31 != null ? Gdx.gl31 : this.graphics.getGL30();
        Gdx.gl30 = gl30;
        GL20 gl20 = gl30 != null ? Gdx.gl30 : this.graphics.getGL20();
        Gdx.gl20 = gl20;
        Gdx.gl = gl20;
        Gdx.input = this.input;
        GLFW.glfwMakeContextCurrent(this.windowHandle);
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.listener.pause();
        this.listener.dispose();
        Lwjgl3Cursor.dispose(this);
        this.graphics.dispose();
        this.input.dispose();
        GLFW.glfwSetWindowFocusCallback(this.windowHandle, null);
        GLFW.glfwSetWindowIconifyCallback(this.windowHandle, null);
        GLFW.glfwSetWindowCloseCallback(this.windowHandle, null);
        GLFW.glfwSetDropCallback(this.windowHandle, null);
        GLFW.glfwDestroyWindow(this.windowHandle);
        this.focusCallback.free();
        this.iconifyCallback.free();
        this.maximizeCallback.free();
        this.closeCallback.free();
        this.dropCallback.free();
        this.refreshCallback.free();
    }

    public int hashCode() {
        return 31 + ((int) (this.windowHandle ^ (this.windowHandle >>> 32)));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.windowHandle == ((Lwjgl3Window) obj).windowHandle;
    }

    public void flash() {
        GLFW.glfwRequestWindowAttention(this.windowHandle);
    }
}
