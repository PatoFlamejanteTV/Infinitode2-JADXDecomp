package com.badlogic.gdx.backends.lwjgl3;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics;
import com.badlogic.gdx.graphics.glutils.HdpiMode;
import com.badlogic.gdx.math.GridPoint2;
import java.io.PrintStream;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/Lwjgl3ApplicationConfiguration.class */
public class Lwjgl3ApplicationConfiguration extends Lwjgl3WindowConfiguration {
    public static PrintStream errorStream = System.err;
    boolean transparentFramebuffer;
    boolean disableAudio = false;
    int maxNetThreads = Integer.MAX_VALUE;
    int audioDeviceSimultaneousSources = 16;
    int audioDeviceBufferSize = 512;
    int audioDeviceBufferCount = 9;
    GLEmulation glEmulation = GLEmulation.GL20;
    int gles30ContextMajorVersion = 3;
    int gles30ContextMinorVersion = 2;
    int r = 8;
    int g = 8;

    /* renamed from: b, reason: collision with root package name */
    int f886b = 8;

    /* renamed from: a, reason: collision with root package name */
    int f887a = 8;
    int depth = 16;
    int stencil = 0;
    int samples = 0;
    int idleFPS = 60;
    int foregroundFPS = 0;
    boolean pauseWhenMinimized = true;
    boolean pauseWhenLostFocus = false;
    String preferencesDirectory = ".prefs/";
    Files.FileType preferencesFileType = Files.FileType.External;
    HdpiMode hdpiMode = HdpiMode.Logical;
    boolean debug = false;
    PrintStream debugStream = System.err;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/Lwjgl3ApplicationConfiguration$GLEmulation.class */
    public enum GLEmulation {
        ANGLE_GLES20,
        GL20,
        GL30,
        GL31,
        GL32
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Lwjgl3ApplicationConfiguration copy(Lwjgl3ApplicationConfiguration lwjgl3ApplicationConfiguration) {
        Lwjgl3ApplicationConfiguration lwjgl3ApplicationConfiguration2 = new Lwjgl3ApplicationConfiguration();
        lwjgl3ApplicationConfiguration2.set(lwjgl3ApplicationConfiguration);
        return lwjgl3ApplicationConfiguration2;
    }

    void set(Lwjgl3ApplicationConfiguration lwjgl3ApplicationConfiguration) {
        super.setWindowConfiguration(lwjgl3ApplicationConfiguration);
        this.disableAudio = lwjgl3ApplicationConfiguration.disableAudio;
        this.audioDeviceSimultaneousSources = lwjgl3ApplicationConfiguration.audioDeviceSimultaneousSources;
        this.audioDeviceBufferSize = lwjgl3ApplicationConfiguration.audioDeviceBufferSize;
        this.audioDeviceBufferCount = lwjgl3ApplicationConfiguration.audioDeviceBufferCount;
        this.glEmulation = lwjgl3ApplicationConfiguration.glEmulation;
        this.gles30ContextMajorVersion = lwjgl3ApplicationConfiguration.gles30ContextMajorVersion;
        this.gles30ContextMinorVersion = lwjgl3ApplicationConfiguration.gles30ContextMinorVersion;
        this.r = lwjgl3ApplicationConfiguration.r;
        this.g = lwjgl3ApplicationConfiguration.g;
        this.f886b = lwjgl3ApplicationConfiguration.f886b;
        this.f887a = lwjgl3ApplicationConfiguration.f887a;
        this.depth = lwjgl3ApplicationConfiguration.depth;
        this.stencil = lwjgl3ApplicationConfiguration.stencil;
        this.samples = lwjgl3ApplicationConfiguration.samples;
        this.transparentFramebuffer = lwjgl3ApplicationConfiguration.transparentFramebuffer;
        this.idleFPS = lwjgl3ApplicationConfiguration.idleFPS;
        this.foregroundFPS = lwjgl3ApplicationConfiguration.foregroundFPS;
        this.pauseWhenMinimized = lwjgl3ApplicationConfiguration.pauseWhenMinimized;
        this.pauseWhenLostFocus = lwjgl3ApplicationConfiguration.pauseWhenLostFocus;
        this.preferencesDirectory = lwjgl3ApplicationConfiguration.preferencesDirectory;
        this.preferencesFileType = lwjgl3ApplicationConfiguration.preferencesFileType;
        this.hdpiMode = lwjgl3ApplicationConfiguration.hdpiMode;
        this.debug = lwjgl3ApplicationConfiguration.debug;
        this.debugStream = lwjgl3ApplicationConfiguration.debugStream;
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3WindowConfiguration
    public void setInitialVisible(boolean z) {
        this.initialVisible = z;
    }

    public void disableAudio(boolean z) {
        this.disableAudio = z;
    }

    public void setMaxNetThreads(int i) {
        this.maxNetThreads = i;
    }

    public void setAudioConfig(int i, int i2, int i3) {
        this.audioDeviceSimultaneousSources = i;
        this.audioDeviceBufferSize = i2;
        this.audioDeviceBufferCount = i3;
    }

    public void setOpenGLEmulation(GLEmulation gLEmulation, int i, int i2) {
        this.glEmulation = gLEmulation;
        this.gles30ContextMajorVersion = i;
        this.gles30ContextMinorVersion = i2;
    }

    public void setBackBufferConfig(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.r = i;
        this.g = i2;
        this.f886b = i3;
        this.f887a = i4;
        this.depth = i5;
        this.stencil = i6;
        this.samples = i7;
    }

    public void setTransparentFramebuffer(boolean z) {
        this.transparentFramebuffer = z;
    }

    public void setIdleFPS(int i) {
        this.idleFPS = i;
    }

    public void setForegroundFPS(int i) {
        this.foregroundFPS = i;
    }

    public void setPauseWhenMinimized(boolean z) {
        this.pauseWhenMinimized = z;
    }

    public void setPauseWhenLostFocus(boolean z) {
        this.pauseWhenLostFocus = z;
    }

    public void setPreferencesConfig(String str, Files.FileType fileType) {
        this.preferencesDirectory = str;
        this.preferencesFileType = fileType;
    }

    public void setHdpiMode(HdpiMode hdpiMode) {
        this.hdpiMode = hdpiMode;
    }

    public void enableGLDebugOutput(boolean z, PrintStream printStream) {
        this.debug = z;
        this.debugStream = printStream;
    }

    public static Graphics.DisplayMode getDisplayMode() {
        Lwjgl3Application.initializeGlfw();
        GLFWVidMode glfwGetVideoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        return new Lwjgl3Graphics.Lwjgl3DisplayMode(GLFW.glfwGetPrimaryMonitor(), glfwGetVideoMode.width(), glfwGetVideoMode.height(), glfwGetVideoMode.refreshRate(), glfwGetVideoMode.redBits() + glfwGetVideoMode.greenBits() + glfwGetVideoMode.blueBits());
    }

    public static Graphics.DisplayMode getDisplayMode(Graphics.Monitor monitor) {
        Lwjgl3Application.initializeGlfw();
        GLFWVidMode glfwGetVideoMode = GLFW.glfwGetVideoMode(((Lwjgl3Graphics.Lwjgl3Monitor) monitor).monitorHandle);
        return new Lwjgl3Graphics.Lwjgl3DisplayMode(((Lwjgl3Graphics.Lwjgl3Monitor) monitor).monitorHandle, glfwGetVideoMode.width(), glfwGetVideoMode.height(), glfwGetVideoMode.refreshRate(), glfwGetVideoMode.redBits() + glfwGetVideoMode.greenBits() + glfwGetVideoMode.blueBits());
    }

    public static Graphics.DisplayMode[] getDisplayModes() {
        Lwjgl3Application.initializeGlfw();
        GLFWVidMode.Buffer glfwGetVideoModes = GLFW.glfwGetVideoModes(GLFW.glfwGetPrimaryMonitor());
        Graphics.DisplayMode[] displayModeArr = new Graphics.DisplayMode[glfwGetVideoModes.limit()];
        for (int i = 0; i < displayModeArr.length; i++) {
            GLFWVidMode gLFWVidMode = glfwGetVideoModes.get(i);
            displayModeArr[i] = new Lwjgl3Graphics.Lwjgl3DisplayMode(GLFW.glfwGetPrimaryMonitor(), gLFWVidMode.width(), gLFWVidMode.height(), gLFWVidMode.refreshRate(), gLFWVidMode.redBits() + gLFWVidMode.greenBits() + gLFWVidMode.blueBits());
        }
        return displayModeArr;
    }

    public static Graphics.DisplayMode[] getDisplayModes(Graphics.Monitor monitor) {
        Lwjgl3Application.initializeGlfw();
        GLFWVidMode.Buffer glfwGetVideoModes = GLFW.glfwGetVideoModes(((Lwjgl3Graphics.Lwjgl3Monitor) monitor).monitorHandle);
        Graphics.DisplayMode[] displayModeArr = new Graphics.DisplayMode[glfwGetVideoModes.limit()];
        for (int i = 0; i < displayModeArr.length; i++) {
            GLFWVidMode gLFWVidMode = glfwGetVideoModes.get(i);
            displayModeArr[i] = new Lwjgl3Graphics.Lwjgl3DisplayMode(((Lwjgl3Graphics.Lwjgl3Monitor) monitor).monitorHandle, gLFWVidMode.width(), gLFWVidMode.height(), gLFWVidMode.refreshRate(), gLFWVidMode.redBits() + gLFWVidMode.greenBits() + gLFWVidMode.blueBits());
        }
        return displayModeArr;
    }

    public static Graphics.Monitor getPrimaryMonitor() {
        Lwjgl3Application.initializeGlfw();
        return toLwjgl3Monitor(GLFW.glfwGetPrimaryMonitor());
    }

    public static Graphics.Monitor[] getMonitors() {
        Lwjgl3Application.initializeGlfw();
        PointerBuffer glfwGetMonitors = GLFW.glfwGetMonitors();
        Graphics.Monitor[] monitorArr = new Graphics.Monitor[glfwGetMonitors.limit()];
        for (int i = 0; i < glfwGetMonitors.limit(); i++) {
            monitorArr[i] = toLwjgl3Monitor(glfwGetMonitors.get(i));
        }
        return monitorArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Lwjgl3Graphics.Lwjgl3Monitor toLwjgl3Monitor(long j) {
        IntBuffer createIntBuffer = BufferUtils.createIntBuffer(1);
        IntBuffer createIntBuffer2 = BufferUtils.createIntBuffer(1);
        GLFW.glfwGetMonitorPos(j, createIntBuffer, createIntBuffer2);
        return new Lwjgl3Graphics.Lwjgl3Monitor(j, createIntBuffer.get(0), createIntBuffer2.get(0), GLFW.glfwGetMonitorName(j));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GridPoint2 calculateCenteredWindowPosition(Lwjgl3Graphics.Lwjgl3Monitor lwjgl3Monitor, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        IntBuffer createIntBuffer = BufferUtils.createIntBuffer(1);
        IntBuffer createIntBuffer2 = BufferUtils.createIntBuffer(1);
        IntBuffer createIntBuffer3 = BufferUtils.createIntBuffer(1);
        IntBuffer createIntBuffer4 = BufferUtils.createIntBuffer(1);
        Graphics.DisplayMode displayMode = getDisplayMode(lwjgl3Monitor);
        GLFW.glfwGetMonitorWorkarea(lwjgl3Monitor.monitorHandle, createIntBuffer, createIntBuffer2, createIntBuffer3, createIntBuffer4);
        int i7 = createIntBuffer3.get(0);
        int i8 = createIntBuffer4.get(0);
        if (i > i7) {
            i3 = lwjgl3Monitor.virtualX;
            i4 = displayMode.width;
        } else {
            i3 = createIntBuffer.get(0);
            i4 = i7;
        }
        if (i2 > i8) {
            i5 = lwjgl3Monitor.virtualY;
            i6 = displayMode.height;
        } else {
            i5 = createIntBuffer2.get(0);
            i6 = i8;
        }
        int i9 = i3;
        int i10 = i5;
        return new GridPoint2(Math.max(i9, i9 + ((i4 - i) / 2)), Math.max(i10, i10 + ((i6 - i2) / 2)));
    }
}
