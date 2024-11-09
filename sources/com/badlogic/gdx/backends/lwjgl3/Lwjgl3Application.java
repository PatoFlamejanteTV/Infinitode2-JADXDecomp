package com.badlogic.gdx.backends.lwjgl3;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.ApplicationLogger;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.audio.Lwjgl3Audio;
import com.badlogic.gdx.backends.lwjgl3.audio.OpenALLwjgl3Audio;
import com.badlogic.gdx.backends.lwjgl3.audio.mock.MockAudio;
import com.badlogic.gdx.graphics.glutils.GLVersion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Clipboard;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Os;
import com.badlogic.gdx.utils.SharedLibraryLoader;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.File;
import java.lang.reflect.Method;
import java.nio.IntBuffer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.AMDDebugOutput;
import org.lwjgl.opengl.ARBDebugOutput;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL43;
import org.lwjgl.opengl.GLCapabilities;
import org.lwjgl.opengl.GLUtil;
import org.lwjgl.opengl.KHRDebug;
import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/Lwjgl3Application.class */
public class Lwjgl3Application implements Lwjgl3ApplicationBase {
    private final Lwjgl3ApplicationConfiguration config;
    final Array<Lwjgl3Window> windows;
    private volatile Lwjgl3Window currentWindow;
    private Lwjgl3Audio audio;
    private final Files files;

    /* renamed from: net, reason: collision with root package name */
    private final Net f885net;
    private final ObjectMap<String, Preferences> preferences;
    private final Lwjgl3Clipboard clipboard;
    private int logLevel;
    private ApplicationLogger applicationLogger;
    private volatile boolean running;
    private final Array<Runnable> runnables;
    private final Array<Runnable> executedRunnables;
    private final Array<LifecycleListener> lifecycleListeners;
    private static GLFWErrorCallback errorCallback;
    private static GLVersion glVersion;
    private static Callback glDebugCallback;
    private final Sync sync;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void initializeGlfw() {
        if (errorCallback == null) {
            Lwjgl3NativesLoader.load();
            GLFWErrorCallback createPrint = GLFWErrorCallback.createPrint(Lwjgl3ApplicationConfiguration.errorStream);
            errorCallback = createPrint;
            GLFW.glfwSetErrorCallback(createPrint);
            if (SharedLibraryLoader.os == Os.MacOsX) {
                GLFW.glfwInitHint(GLFW.GLFW_ANGLE_PLATFORM_TYPE, GLFW.GLFW_ANGLE_PLATFORM_TYPE_METAL);
            }
            GLFW.glfwInitHint(GLFW.GLFW_JOYSTICK_HAT_BUTTONS, 0);
            if (!GLFW.glfwInit()) {
                throw new GdxRuntimeException("Unable to initialize GLFW");
            }
        }
    }

    static void loadANGLE() {
        try {
            Class<?> cls = Class.forName("com.badlogic.gdx.backends.lwjgl3.angle.ANGLELoader");
            cls.getMethod("load", new Class[0]).invoke(cls, new Object[0]);
        } catch (ClassNotFoundException unused) {
        } catch (Throwable th) {
            throw new GdxRuntimeException("Couldn't load ANGLE.", th);
        }
    }

    static void postLoadANGLE() {
        try {
            Class<?> cls = Class.forName("com.badlogic.gdx.backends.lwjgl3.angle.ANGLELoader");
            cls.getMethod("postGlfwInit", new Class[0]).invoke(cls, new Object[0]);
        } catch (ClassNotFoundException unused) {
        } catch (Throwable th) {
            throw new GdxRuntimeException("Couldn't load ANGLE.", th);
        }
    }

    public Lwjgl3Application(ApplicationListener applicationListener) {
        this(applicationListener, new Lwjgl3ApplicationConfiguration());
    }

    public Lwjgl3Application(ApplicationListener applicationListener, Lwjgl3ApplicationConfiguration lwjgl3ApplicationConfiguration) {
        this.windows = new Array<>();
        this.preferences = new ObjectMap<>();
        this.logLevel = 2;
        this.running = true;
        this.runnables = new Array<>();
        this.executedRunnables = new Array<>();
        this.lifecycleListeners = new Array<>();
        if (lwjgl3ApplicationConfiguration.glEmulation == Lwjgl3ApplicationConfiguration.GLEmulation.ANGLE_GLES20) {
            loadANGLE();
        }
        initializeGlfw();
        setApplicationLogger(new Lwjgl3ApplicationLogger());
        Lwjgl3ApplicationConfiguration copy = Lwjgl3ApplicationConfiguration.copy(lwjgl3ApplicationConfiguration);
        this.config = copy;
        if (copy.title == null) {
            copy.title = applicationListener.getClass().getSimpleName();
        }
        Gdx.app = this;
        if (!copy.disableAudio) {
            try {
                this.audio = createAudio(copy);
            } catch (Throwable th) {
                log("Lwjgl3Application", "Couldn't initialize audio, disabling audio", th);
                this.audio = new MockAudio();
            }
        } else {
            this.audio = new MockAudio();
        }
        Gdx.audio = this.audio;
        Files createFiles = createFiles();
        Gdx.files = createFiles;
        this.files = createFiles;
        Lwjgl3Net lwjgl3Net = new Lwjgl3Net(copy);
        Gdx.f881net = lwjgl3Net;
        this.f885net = lwjgl3Net;
        this.clipboard = new Lwjgl3Clipboard();
        this.sync = new Sync();
        Lwjgl3Window createWindow = createWindow(copy, applicationListener, 0L);
        if (copy.glEmulation == Lwjgl3ApplicationConfiguration.GLEmulation.ANGLE_GLES20) {
            postLoadANGLE();
        }
        Array<Lwjgl3Window> array = this.windows;
        array.add(createWindow);
        try {
            try {
                loop();
                cleanupWindows();
                cleanup();
            } catch (Throwable th2) {
                if (!(array instanceof RuntimeException)) {
                    throw new GdxRuntimeException(th2);
                }
                throw ((RuntimeException) th2);
            }
        } catch (Throwable th3) {
            cleanup();
            throw th3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void loop() {
        boolean z;
        Array array = new Array();
        while (this.running && this.windows.size > 0) {
            this.audio.update();
            boolean z2 = false;
            array.clear();
            int i = -2;
            Array.ArrayIterator<Lwjgl3Window> it = this.windows.iterator();
            while (it.hasNext()) {
                Lwjgl3Window next = it.next();
                if (this.currentWindow != next) {
                    next.makeCurrent();
                    this.currentWindow = next;
                }
                if (i == -2) {
                    i = next.getConfig().foregroundFPS;
                }
                synchronized (this.lifecycleListeners) {
                    z2 |= next.update();
                }
                if (next.shouldClose()) {
                    array.add(next);
                }
            }
            GLFW.glfwPollEvents();
            synchronized (this.runnables) {
                z = this.runnables.size > 0;
                this.executedRunnables.clear();
                this.executedRunnables.addAll(this.runnables);
                this.runnables.clear();
            }
            Array.ArrayIterator<Runnable> it2 = this.executedRunnables.iterator();
            while (it2.hasNext()) {
                it2.next().run();
            }
            if (z) {
                Array.ArrayIterator<Lwjgl3Window> it3 = this.windows.iterator();
                while (it3.hasNext()) {
                    Lwjgl3Window next2 = it3.next();
                    if (!next2.getGraphics().isContinuousRendering()) {
                        next2.requestRendering();
                    }
                }
            }
            Array.ArrayIterator it4 = array.iterator();
            while (it4.hasNext()) {
                Lwjgl3Window lwjgl3Window = (Lwjgl3Window) it4.next();
                if (this.windows.size == 1) {
                    for (int i2 = this.lifecycleListeners.size - 1; i2 >= 0; i2--) {
                        LifecycleListener lifecycleListener = this.lifecycleListeners.get(i2);
                        lifecycleListener.pause();
                        lifecycleListener.dispose();
                    }
                    this.lifecycleListeners.clear();
                }
                lwjgl3Window.dispose();
                this.windows.removeValue(lwjgl3Window, false);
            }
            if (!z2) {
                try {
                    Thread.sleep(1000 / this.config.idleFPS);
                } catch (InterruptedException unused) {
                }
            } else if (i > 0) {
                this.sync.sync(i);
            }
        }
    }

    protected void cleanupWindows() {
        synchronized (this.lifecycleListeners) {
            Array.ArrayIterator<LifecycleListener> it = this.lifecycleListeners.iterator();
            while (it.hasNext()) {
                LifecycleListener next = it.next();
                next.pause();
                next.dispose();
            }
        }
        Array.ArrayIterator<Lwjgl3Window> it2 = this.windows.iterator();
        while (it2.hasNext()) {
            it2.next().dispose();
        }
        this.windows.clear();
    }

    protected void cleanup() {
        Lwjgl3Cursor.disposeSystemCursors();
        this.audio.dispose();
        errorCallback.free();
        errorCallback = null;
        if (glDebugCallback != null) {
            glDebugCallback.free();
            glDebugCallback = null;
        }
        GLFW.glfwTerminate();
    }

    @Override // com.badlogic.gdx.Application
    public ApplicationListener getApplicationListener() {
        return this.currentWindow.getListener();
    }

    @Override // com.badlogic.gdx.Application
    public Graphics getGraphics() {
        return this.currentWindow.getGraphics();
    }

    @Override // com.badlogic.gdx.Application
    public Audio getAudio() {
        return this.audio;
    }

    @Override // com.badlogic.gdx.Application
    public Input getInput() {
        return this.currentWindow.getInput();
    }

    @Override // com.badlogic.gdx.Application
    public Files getFiles() {
        return this.files;
    }

    @Override // com.badlogic.gdx.Application
    public Net getNet() {
        return this.f885net;
    }

    @Override // com.badlogic.gdx.Application
    public void debug(String str, String str2) {
        if (this.logLevel >= 3) {
            getApplicationLogger().debug(str, str2);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void debug(String str, String str2, Throwable th) {
        if (this.logLevel >= 3) {
            getApplicationLogger().debug(str, str2, th);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void log(String str, String str2) {
        if (this.logLevel >= 2) {
            getApplicationLogger().log(str, str2);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void log(String str, String str2, Throwable th) {
        if (this.logLevel >= 2) {
            getApplicationLogger().log(str, str2, th);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void error(String str, String str2) {
        if (this.logLevel > 0) {
            getApplicationLogger().error(str, str2);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void error(String str, String str2, Throwable th) {
        if (this.logLevel > 0) {
            getApplicationLogger().error(str, str2, th);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void setLogLevel(int i) {
        this.logLevel = i;
    }

    @Override // com.badlogic.gdx.Application
    public int getLogLevel() {
        return this.logLevel;
    }

    @Override // com.badlogic.gdx.Application
    public void setApplicationLogger(ApplicationLogger applicationLogger) {
        this.applicationLogger = applicationLogger;
    }

    @Override // com.badlogic.gdx.Application
    public ApplicationLogger getApplicationLogger() {
        return this.applicationLogger;
    }

    @Override // com.badlogic.gdx.Application
    public Application.ApplicationType getType() {
        return Application.ApplicationType.Desktop;
    }

    @Override // com.badlogic.gdx.Application
    public int getVersion() {
        return 0;
    }

    @Override // com.badlogic.gdx.Application
    public long getJavaHeap() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    @Override // com.badlogic.gdx.Application
    public long getNativeHeap() {
        return getJavaHeap();
    }

    @Override // com.badlogic.gdx.Application
    public Preferences getPreferences(String str) {
        if (this.preferences.containsKey(str)) {
            return this.preferences.get(str);
        }
        Lwjgl3Preferences lwjgl3Preferences = new Lwjgl3Preferences(new Lwjgl3FileHandle(new File(this.config.preferencesDirectory, str), this.config.preferencesFileType));
        this.preferences.put(str, lwjgl3Preferences);
        return lwjgl3Preferences;
    }

    @Override // com.badlogic.gdx.Application
    public Clipboard getClipboard() {
        return this.clipboard;
    }

    @Override // com.badlogic.gdx.Application
    public void postRunnable(Runnable runnable) {
        synchronized (this.runnables) {
            this.runnables.add(runnable);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void exit() {
        this.running = false;
    }

    @Override // com.badlogic.gdx.Application
    public void addLifecycleListener(LifecycleListener lifecycleListener) {
        synchronized (this.lifecycleListeners) {
            this.lifecycleListeners.add(lifecycleListener);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void removeLifecycleListener(LifecycleListener lifecycleListener) {
        synchronized (this.lifecycleListeners) {
            this.lifecycleListeners.removeValue(lifecycleListener, true);
        }
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationBase
    public Lwjgl3Audio createAudio(Lwjgl3ApplicationConfiguration lwjgl3ApplicationConfiguration) {
        return new OpenALLwjgl3Audio(lwjgl3ApplicationConfiguration.audioDeviceSimultaneousSources, lwjgl3ApplicationConfiguration.audioDeviceBufferCount, lwjgl3ApplicationConfiguration.audioDeviceBufferSize);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationBase
    public Lwjgl3Input createInput(Lwjgl3Window lwjgl3Window) {
        return new DefaultLwjgl3Input(lwjgl3Window);
    }

    protected Files createFiles() {
        return new Lwjgl3Files();
    }

    public Lwjgl3Window newWindow(ApplicationListener applicationListener, Lwjgl3WindowConfiguration lwjgl3WindowConfiguration) {
        Lwjgl3ApplicationConfiguration copy = Lwjgl3ApplicationConfiguration.copy(this.config);
        copy.setWindowConfiguration(lwjgl3WindowConfiguration);
        if (copy.title == null) {
            copy.title = applicationListener.getClass().getSimpleName();
        }
        return createWindow(copy, applicationListener, this.windows.get(0).getWindowHandle());
    }

    private Lwjgl3Window createWindow(final Lwjgl3ApplicationConfiguration lwjgl3ApplicationConfiguration, ApplicationListener applicationListener, final long j) {
        final Lwjgl3Window lwjgl3Window = new Lwjgl3Window(applicationListener, this.lifecycleListeners, lwjgl3ApplicationConfiguration, this);
        if (j == 0) {
            createWindow(lwjgl3Window, lwjgl3ApplicationConfiguration, j);
        } else {
            postRunnable(new Runnable() { // from class: com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application.1
                @Override // java.lang.Runnable
                public void run() {
                    Lwjgl3Application.this.createWindow(lwjgl3Window, lwjgl3ApplicationConfiguration, j);
                    Lwjgl3Application.this.windows.add(lwjgl3Window);
                }
            });
        }
        return lwjgl3Window;
    }

    void createWindow(Lwjgl3Window lwjgl3Window, Lwjgl3ApplicationConfiguration lwjgl3ApplicationConfiguration, long j) {
        long createGlfwWindow = createGlfwWindow(lwjgl3ApplicationConfiguration, j);
        lwjgl3Window.create(createGlfwWindow);
        lwjgl3Window.setVisible(lwjgl3ApplicationConfiguration.initialVisible);
        for (int i = 0; i < 2; i++) {
            lwjgl3Window.getGraphics().gl20.glClearColor(lwjgl3ApplicationConfiguration.initialBackgroundColor.r, lwjgl3ApplicationConfiguration.initialBackgroundColor.g, lwjgl3ApplicationConfiguration.initialBackgroundColor.f888b, lwjgl3ApplicationConfiguration.initialBackgroundColor.f889a);
            lwjgl3Window.getGraphics().gl20.glClear(16384);
            GLFW.glfwSwapBuffers(createGlfwWindow);
        }
        if (this.currentWindow != null) {
            this.currentWindow.makeCurrent();
        }
    }

    static long createGlfwWindow(Lwjgl3ApplicationConfiguration lwjgl3ApplicationConfiguration, long j) {
        long glfwCreateWindow;
        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(131076, 0);
        GLFW.glfwWindowHint(131075, lwjgl3ApplicationConfiguration.windowResizable ? 1 : 0);
        GLFW.glfwWindowHint(131080, lwjgl3ApplicationConfiguration.windowMaximized ? 1 : 0);
        GLFW.glfwWindowHint(131078, lwjgl3ApplicationConfiguration.autoIconify ? 1 : 0);
        GLFW.glfwWindowHint(GLFW.GLFW_RED_BITS, lwjgl3ApplicationConfiguration.r);
        GLFW.glfwWindowHint(GLFW.GLFW_GREEN_BITS, lwjgl3ApplicationConfiguration.g);
        GLFW.glfwWindowHint(GLFW.GLFW_BLUE_BITS, lwjgl3ApplicationConfiguration.f886b);
        GLFW.glfwWindowHint(GLFW.GLFW_ALPHA_BITS, lwjgl3ApplicationConfiguration.f887a);
        GLFW.glfwWindowHint(GLFW.GLFW_STENCIL_BITS, lwjgl3ApplicationConfiguration.stencil);
        GLFW.glfwWindowHint(GLFW.GLFW_DEPTH_BITS, lwjgl3ApplicationConfiguration.depth);
        GLFW.glfwWindowHint(GLFW.GLFW_SAMPLES, lwjgl3ApplicationConfiguration.samples);
        if (lwjgl3ApplicationConfiguration.glEmulation == Lwjgl3ApplicationConfiguration.GLEmulation.GL30 || lwjgl3ApplicationConfiguration.glEmulation == Lwjgl3ApplicationConfiguration.GLEmulation.GL31 || lwjgl3ApplicationConfiguration.glEmulation == Lwjgl3ApplicationConfiguration.GLEmulation.GL32) {
            GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, lwjgl3ApplicationConfiguration.gles30ContextMajorVersion);
            GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, lwjgl3ApplicationConfiguration.gles30ContextMinorVersion);
            if (SharedLibraryLoader.os == Os.MacOsX) {
                GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, 1);
                GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
            }
        } else if (lwjgl3ApplicationConfiguration.glEmulation == Lwjgl3ApplicationConfiguration.GLEmulation.ANGLE_GLES20) {
            GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_CREATION_API, 221186);
            GLFW.glfwWindowHint(GLFW.GLFW_CLIENT_API, GLFW.GLFW_OPENGL_ES_API);
            GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 2);
            GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 0);
        }
        if (lwjgl3ApplicationConfiguration.transparentFramebuffer) {
            GLFW.glfwWindowHint(131082, 1);
        }
        if (lwjgl3ApplicationConfiguration.debug) {
            GLFW.glfwWindowHint(139271, 1);
        }
        if (lwjgl3ApplicationConfiguration.fullscreenMode != null) {
            GLFW.glfwWindowHint(GLFW.GLFW_REFRESH_RATE, lwjgl3ApplicationConfiguration.fullscreenMode.refreshRate);
            glfwCreateWindow = GLFW.glfwCreateWindow(lwjgl3ApplicationConfiguration.fullscreenMode.width, lwjgl3ApplicationConfiguration.fullscreenMode.height, lwjgl3ApplicationConfiguration.title, lwjgl3ApplicationConfiguration.fullscreenMode.getMonitor(), j);
        } else {
            GLFW.glfwWindowHint(131077, lwjgl3ApplicationConfiguration.windowDecorated ? 1 : 0);
            glfwCreateWindow = GLFW.glfwCreateWindow(lwjgl3ApplicationConfiguration.windowWidth, lwjgl3ApplicationConfiguration.windowHeight, lwjgl3ApplicationConfiguration.title, 0L, j);
        }
        if (glfwCreateWindow == 0) {
            throw new GdxRuntimeException("Couldn't create window");
        }
        Lwjgl3Window.setSizeLimits(glfwCreateWindow, lwjgl3ApplicationConfiguration.windowMinWidth, lwjgl3ApplicationConfiguration.windowMinHeight, lwjgl3ApplicationConfiguration.windowMaxWidth, lwjgl3ApplicationConfiguration.windowMaxHeight);
        if (lwjgl3ApplicationConfiguration.fullscreenMode == null) {
            if (lwjgl3ApplicationConfiguration.windowX == -1 && lwjgl3ApplicationConfiguration.windowY == -1) {
                int max = Math.max(lwjgl3ApplicationConfiguration.windowWidth, lwjgl3ApplicationConfiguration.windowMinWidth);
                int max2 = Math.max(lwjgl3ApplicationConfiguration.windowHeight, lwjgl3ApplicationConfiguration.windowMinHeight);
                if (lwjgl3ApplicationConfiguration.windowMaxWidth >= 0) {
                    max = Math.min(max, lwjgl3ApplicationConfiguration.windowMaxWidth);
                }
                if (lwjgl3ApplicationConfiguration.windowMaxHeight >= 0) {
                    max2 = Math.min(max2, lwjgl3ApplicationConfiguration.windowMaxHeight);
                }
                long glfwGetPrimaryMonitor = GLFW.glfwGetPrimaryMonitor();
                if (lwjgl3ApplicationConfiguration.windowMaximized && lwjgl3ApplicationConfiguration.maximizedMonitor != null) {
                    glfwGetPrimaryMonitor = lwjgl3ApplicationConfiguration.maximizedMonitor.monitorHandle;
                }
                GridPoint2 calculateCenteredWindowPosition = Lwjgl3ApplicationConfiguration.calculateCenteredWindowPosition(Lwjgl3ApplicationConfiguration.toLwjgl3Monitor(glfwGetPrimaryMonitor), max, max2);
                GLFW.glfwSetWindowPos(glfwCreateWindow, calculateCenteredWindowPosition.x, calculateCenteredWindowPosition.y);
            } else {
                GLFW.glfwSetWindowPos(glfwCreateWindow, lwjgl3ApplicationConfiguration.windowX, lwjgl3ApplicationConfiguration.windowY);
            }
            if (lwjgl3ApplicationConfiguration.windowMaximized) {
                GLFW.glfwMaximizeWindow(glfwCreateWindow);
            }
        }
        if (lwjgl3ApplicationConfiguration.windowIconPaths != null) {
            Lwjgl3Window.setIcon(glfwCreateWindow, lwjgl3ApplicationConfiguration.windowIconPaths, lwjgl3ApplicationConfiguration.windowIconFileType);
        }
        GLFW.glfwMakeContextCurrent(glfwCreateWindow);
        GLFW.glfwSwapInterval(lwjgl3ApplicationConfiguration.vSyncEnabled ? 1 : 0);
        if (lwjgl3ApplicationConfiguration.glEmulation == Lwjgl3ApplicationConfiguration.GLEmulation.ANGLE_GLES20) {
            try {
                Class<?> cls = Class.forName("org.lwjgl.opengles.GLES");
                cls.getMethod("createCapabilities", new Class[0]).invoke(cls, new Object[0]);
            } catch (Throwable th) {
                throw new GdxRuntimeException("Couldn't initialize GLES", th);
            }
        } else {
            GL.createCapabilities();
        }
        initiateGL(lwjgl3ApplicationConfiguration.glEmulation == Lwjgl3ApplicationConfiguration.GLEmulation.ANGLE_GLES20);
        if (!glVersion.isVersionEqualToOrHigher(2, 0)) {
            throw new GdxRuntimeException("OpenGL 2.0 or higher with the FBO extension is required. OpenGL version: " + glVersion.getVersionString() + SequenceUtils.EOL + glVersion.getDebugVersionString());
        }
        if (lwjgl3ApplicationConfiguration.glEmulation != Lwjgl3ApplicationConfiguration.GLEmulation.ANGLE_GLES20 && !supportsFBO()) {
            throw new GdxRuntimeException("OpenGL 2.0 or higher with the FBO extension is required. OpenGL version: " + glVersion.getVersionString() + ", FBO extension: false\n" + glVersion.getDebugVersionString());
        }
        if (lwjgl3ApplicationConfiguration.debug) {
            if (lwjgl3ApplicationConfiguration.glEmulation == Lwjgl3ApplicationConfiguration.GLEmulation.ANGLE_GLES20) {
                throw new IllegalStateException("ANGLE currently can't be used with with Lwjgl3ApplicationConfiguration#enableGLDebugOutput");
            }
            glDebugCallback = GLUtil.setupDebugMessageCallback(lwjgl3ApplicationConfiguration.debugStream);
            setGLDebugMessageControl(GLDebugMessageSeverity.NOTIFICATION, false);
        }
        return glfwCreateWindow;
    }

    private static void initiateGL(boolean z) {
        if (!z) {
            glVersion = new GLVersion(Application.ApplicationType.Desktop, GL11.glGetString(7938), GL11.glGetString(7936), GL11.glGetString(7937));
            return;
        }
        try {
            Class<?> cls = Class.forName("org.lwjgl.opengles.GLES20");
            Method method = cls.getMethod("glGetString", Integer.TYPE);
            glVersion = new GLVersion(Application.ApplicationType.Desktop, (String) method.invoke(cls, 7938), (String) method.invoke(cls, 7936), (String) method.invoke(cls, 7937));
        } catch (Throwable th) {
            throw new GdxRuntimeException("Couldn't get GLES version string.", th);
        }
    }

    private static boolean supportsFBO() {
        return glVersion.isVersionEqualToOrHigher(3, 0) || GLFW.glfwExtensionSupported("GL_EXT_framebuffer_object") || GLFW.glfwExtensionSupported("GL_ARB_framebuffer_object");
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/Lwjgl3Application$GLDebugMessageSeverity.class */
    public enum GLDebugMessageSeverity {
        HIGH(37190, 37190, 37190, 37190),
        MEDIUM(37191, 37191, 37191, 37191),
        LOW(37192, 37192, 37192, 37192),
        NOTIFICATION(33387, 33387, -1, -1);

        final int gl43;
        final int khr;
        final int arb;
        final int amd;

        GLDebugMessageSeverity(int i, int i2, int i3, int i4) {
            this.gl43 = i;
            this.khr = i2;
            this.arb = i3;
            this.amd = i4;
        }
    }

    public static boolean setGLDebugMessageControl(GLDebugMessageSeverity gLDebugMessageSeverity, boolean z) {
        GLCapabilities capabilities = GL.getCapabilities();
        if (capabilities.OpenGL43) {
            GL43.glDebugMessageControl(4352, 4352, gLDebugMessageSeverity.gl43, (IntBuffer) null, z);
            return true;
        }
        if (capabilities.GL_KHR_debug) {
            KHRDebug.glDebugMessageControl(4352, 4352, gLDebugMessageSeverity.khr, (IntBuffer) null, z);
            return true;
        }
        if (capabilities.GL_ARB_debug_output && gLDebugMessageSeverity.arb != -1) {
            ARBDebugOutput.glDebugMessageControlARB(4352, 4352, gLDebugMessageSeverity.arb, (IntBuffer) null, z);
            return true;
        }
        if (capabilities.GL_AMD_debug_output && gLDebugMessageSeverity.amd != -1) {
            AMDDebugOutput.glDebugMessageEnableAMD(4352, gLDebugMessageSeverity.amd, (IntBuffer) null, z);
            return true;
        }
        return false;
    }
}
