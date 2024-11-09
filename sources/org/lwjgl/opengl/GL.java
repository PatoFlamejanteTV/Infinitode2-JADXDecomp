package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.function.IntFunction;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.Configuration;
import org.lwjgl.system.FunctionProvider;
import org.lwjgl.system.JNI;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.Platform;
import org.lwjgl.system.SharedLibrary;
import org.lwjgl.system.ThreadLocalUtil;
import org.lwjgl.system.linux.X11;
import org.lwjgl.system.windows.GDI32;
import org.lwjgl.system.windows.PIXELFORMATDESCRIPTOR;
import org.lwjgl.system.windows.User32;
import org.lwjgl.system.windows.WNDCLASSEX;
import org.lwjgl.system.windows.WindowsLibrary;
import org.lwjgl.system.windows.WindowsUtil;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL.class */
public final class GL {
    private static final APIUtil.APIVersion MAX_VERSION;
    private static FunctionProvider functionProvider;
    private static final ThreadLocal<GLCapabilities> capabilitiesTLS = new ThreadLocal<>();
    private static ICD icd = new ICDStatic();
    private static WGLCapabilities capabilitiesWGL;
    private static GLXCapabilities capabilitiesGLXClient;
    private static GLXCapabilities capabilitiesGLX;

    static {
        Library.loadSystem(System::load, System::loadLibrary, GL.class, "org.lwjgl.opengl", Platform.mapLibraryNameBundled("lwjgl_opengl"));
        MAX_VERSION = APIUtil.apiParseVersion((Configuration<?>) Configuration.OPENGL_MAXVERSION);
        if (!Configuration.OPENGL_EXPLICIT_INIT.get(Boolean.FALSE).booleanValue()) {
            create();
        }
    }

    private GL() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void initialize() {
    }

    public static void create() {
        SharedLibrary loadNative;
        switch (Platform.get()) {
            case FREEBSD:
            case LINUX:
                loadNative = Library.loadNative((Class<?>) GL.class, "org.lwjgl.opengl", Configuration.OPENGL_LIBRARY_NAME, "libGLX.so.0", "libGL.so.1", "libGL.so");
                break;
            case MACOSX:
                loadNative = Library.loadNative((Class<?>) GL.class, "org.lwjgl.opengl", Configuration.OPENGL_LIBRARY_NAME, "/System/Library/Frameworks/OpenGL.framework/Versions/Current/OpenGL");
                break;
            case WINDOWS:
                loadNative = Library.loadNative((Class<?>) GL.class, "org.lwjgl.opengl", Configuration.OPENGL_LIBRARY_NAME, "opengl32");
                break;
            default:
                throw new IllegalStateException();
        }
        create(loadNative);
    }

    public static void create(String str) {
        create(Library.loadNative(GL.class, "org.lwjgl.opengl", str));
    }

    private static void create(SharedLibrary sharedLibrary) {
        try {
            create((FunctionProvider) new SharedLibrary.Delegate(sharedLibrary) { // from class: org.lwjgl.opengl.GL.1
                private final long GetProcAddress;

                {
                    long j = 0;
                    switch (AnonymousClass2.$SwitchMap$org$lwjgl$system$Platform[Platform.get().ordinal()]) {
                        case 1:
                        case 2:
                            long functionAddress = this.library.getFunctionAddress("glXGetProcAddress");
                            j = functionAddress;
                            if (functionAddress == 0) {
                                j = this.library.getFunctionAddress("glXGetProcAddressARB");
                                break;
                            }
                            break;
                        case 4:
                            j = this.library.getFunctionAddress("wglGetProcAddress");
                            break;
                    }
                    this.GetProcAddress = j == 0 ? this.library.getFunctionAddress("OSMesaGetProcAddress") : j;
                }

                @Override // org.lwjgl.system.FunctionProvider
                public final long getFunctionAddress(ByteBuffer byteBuffer) {
                    long callPP;
                    if (this.GetProcAddress == 0) {
                        callPP = 0;
                    } else if (Platform.get() == Platform.WINDOWS) {
                        callPP = WGL.nwglGetProcAddress(MemoryUtil.memAddress(byteBuffer), this.GetProcAddress);
                    } else {
                        callPP = JNI.callPP(MemoryUtil.memAddress(byteBuffer), this.GetProcAddress);
                    }
                    long j = callPP;
                    if (callPP == 0) {
                        long functionAddress = this.library.getFunctionAddress(byteBuffer);
                        j = functionAddress;
                        if (functionAddress == 0 && Checks.DEBUG_FUNCTIONS) {
                            APIUtil.apiLogMissing("GL", byteBuffer);
                        }
                    }
                    return j;
                }
            });
        } catch (RuntimeException e) {
            sharedLibrary.free();
            throw e;
        }
    }

    public static void create(FunctionProvider functionProvider2) {
        if (functionProvider != null) {
            throw new IllegalStateException("OpenGL library has already been loaded.");
        }
        functionProvider = functionProvider2;
        ThreadLocalUtil.setFunctionMissingAddresses(2228);
    }

    public static void destroy() {
        if (functionProvider == null) {
            return;
        }
        ThreadLocalUtil.setFunctionMissingAddresses(0);
        capabilitiesWGL = null;
        capabilitiesGLX = null;
        if (functionProvider instanceof NativeResource) {
            ((NativeResource) functionProvider).free();
        }
        functionProvider = null;
    }

    public static FunctionProvider getFunctionProvider() {
        return functionProvider;
    }

    public static void setCapabilities(GLCapabilities gLCapabilities) {
        capabilitiesTLS.set(gLCapabilities);
        ThreadLocalUtil.setCapabilities(gLCapabilities == null ? 0L : MemoryUtil.memAddress(gLCapabilities.addresses));
        icd.set(gLCapabilities);
    }

    public static GLCapabilities getCapabilities() {
        return checkCapabilities(capabilitiesTLS.get());
    }

    private static GLCapabilities checkCapabilities(GLCapabilities gLCapabilities) {
        if (Checks.CHECKS && gLCapabilities == null) {
            throw new IllegalStateException("No GLCapabilities instance set for the current thread. Possible solutions:\n\ta) Call GL.createCapabilities() after making a context current in the current thread.\n\tb) Call GL.setCapabilities() if a GLCapabilities instance already exists for the current context.");
        }
        return gLCapabilities;
    }

    public static WGLCapabilities getCapabilitiesWGL() {
        if (capabilitiesWGL == null) {
            capabilitiesWGL = createCapabilitiesWGLDummy();
        }
        return capabilitiesWGL;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GLXCapabilities getCapabilitiesGLXClient() {
        if (capabilitiesGLXClient == null) {
            capabilitiesGLXClient = initCapabilitiesGLX(true);
        }
        return capabilitiesGLXClient;
    }

    public static GLXCapabilities getCapabilitiesGLX() {
        if (capabilitiesGLX == null) {
            capabilitiesGLX = initCapabilitiesGLX(false);
        }
        return capabilitiesGLX;
    }

    private static GLXCapabilities initCapabilitiesGLX(boolean z) {
        int XDefaultScreen;
        long nXOpenDisplay = X11.nXOpenDisplay(0L);
        if (z) {
            XDefaultScreen = -1;
        } else {
            try {
                XDefaultScreen = X11.XDefaultScreen(nXOpenDisplay);
            } finally {
                X11.XCloseDisplay(nXOpenDisplay);
            }
        }
        return createCapabilitiesGLX(nXOpenDisplay, XDefaultScreen);
    }

    public static GLCapabilities createCapabilities() {
        return createCapabilities((IntFunction<PointerBuffer>) null);
    }

    public static GLCapabilities createCapabilities(IntFunction<PointerBuffer> intFunction) {
        return createCapabilities(false, intFunction);
    }

    public static GLCapabilities createCapabilities(boolean z) {
        return createCapabilities(z, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00eb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.lwjgl.opengl.GLCapabilities createCapabilities(boolean r7, java.util.function.IntFunction<org.lwjgl.PointerBuffer> r8) {
        /*
            Method dump skipped, instructions count: 888
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.lwjgl.opengl.GL.createCapabilities(boolean, java.util.function.IntFunction):org.lwjgl.opengl.GLCapabilities");
    }

    private static WGLCapabilities createCapabilitiesWGLDummy() {
        long wglGetCurrentDC = WGL.wglGetCurrentDC();
        if (wglGetCurrentDC != 0) {
            return createCapabilitiesWGL(wglGetCurrentDC);
        }
        short s = 0;
        long j = 0;
        long j2 = 0;
        try {
            MemoryStack stackPush = MemoryStack.stackPush();
            Throwable th = null;
            try {
                WNDCLASSEX lpszClassName = WNDCLASSEX.calloc(stackPush).cbSize(WNDCLASSEX.SIZEOF).style(3).hInstance(WindowsLibrary.HINSTANCE).lpszClassName(stackPush.UTF16("WGL"));
                MemoryUtil.memPutAddress(lpszClassName.address() + WNDCLASSEX.LPFNWNDPROC, User32.Functions.DefWindowProc);
                short RegisterClassEx = User32.RegisterClassEx(lpszClassName);
                s = RegisterClassEx;
                if (RegisterClassEx == 0) {
                    throw new IllegalStateException("Failed to register WGL window class");
                }
                j = Checks.check(User32.nCreateWindowEx(0, s & 65535, 0L, 114229248, 0, 0, 1, 1, 0L, 0L, 0L, 0L));
                long check = Checks.check(User32.GetDC(0L));
                PIXELFORMATDESCRIPTOR dwFlags = PIXELFORMATDESCRIPTOR.calloc(stackPush).nSize((short) PIXELFORMATDESCRIPTOR.SIZEOF).nVersion((short) 1).dwFlags(32);
                int ChoosePixelFormat = GDI32.ChoosePixelFormat(check, dwFlags);
                if (ChoosePixelFormat == 0) {
                    WindowsUtil.windowsThrowException("Failed to choose an OpenGL-compatible pixel format");
                }
                if (GDI32.DescribePixelFormat(check, ChoosePixelFormat, dwFlags) == 0) {
                    WindowsUtil.windowsThrowException("Failed to obtain pixel format information");
                }
                if (!GDI32.SetPixelFormat(check, ChoosePixelFormat, dwFlags)) {
                    WindowsUtil.windowsThrowException("Failed to set the pixel format");
                }
                j2 = Checks.check(WGL.wglCreateContext(check));
                WGL.wglMakeCurrent(check, j2);
                WGLCapabilities createCapabilitiesWGL = createCapabilitiesWGL(check);
                if (stackPush != null) {
                    if (0 != 0) {
                        try {
                            stackPush.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    } else {
                        stackPush.close();
                    }
                }
                if (j2 != 0) {
                    WGL.wglMakeCurrent(0L, 0L);
                    WGL.wglDeleteContext(j2);
                }
                if (j != 0) {
                    User32.DestroyWindow(j);
                }
                if (s != 0) {
                    User32.nUnregisterClass(s & 65535, WindowsLibrary.HINSTANCE);
                }
                return createCapabilitiesWGL;
            } finally {
            }
        } catch (Throwable th3) {
            if (j2 != 0) {
                WGL.wglMakeCurrent(0L, 0L);
                WGL.wglDeleteContext(j2);
            }
            if (j != 0) {
                User32.DestroyWindow(j);
            }
            if (s != 0) {
                User32.nUnregisterClass(s & 65535, WindowsLibrary.HINSTANCE);
            }
            throw th3;
        }
    }

    public static WGLCapabilities createCapabilitiesWGL() {
        long wglGetCurrentDC = WGL.wglGetCurrentDC();
        if (wglGetCurrentDC == 0) {
            throw new IllegalStateException("Failed to retrieve the device context of the current OpenGL context");
        }
        return createCapabilitiesWGL(wglGetCurrentDC);
    }

    private static WGLCapabilities createCapabilitiesWGL(long j) {
        FunctionProvider functionProvider2 = functionProvider;
        if (functionProvider2 == null) {
            throw new IllegalStateException("OpenGL library has not been loaded.");
        }
        String str = null;
        long functionAddress = functionProvider2.getFunctionAddress("wglGetExtensionsStringARB");
        if (functionAddress != 0) {
            str = MemoryUtil.memASCII(JNI.callPP(j, functionAddress));
        } else {
            long functionAddress2 = functionProvider2.getFunctionAddress("wglGetExtensionsStringEXT");
            if (functionAddress2 != 0) {
                str = MemoryUtil.memASCII(JNI.callP(functionAddress2));
            }
        }
        HashSet hashSet = new HashSet(32);
        if (str != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(str);
            while (stringTokenizer.hasMoreTokens()) {
                hashSet.add(stringTokenizer.nextToken());
            }
        }
        APIUtil.apiFilterExtensions(hashSet, Configuration.OPENGL_EXTENSION_FILTER);
        return new WGLCapabilities(functionProvider2, hashSet);
    }

    public static GLXCapabilities createCapabilitiesGLX(long j) {
        return createCapabilitiesGLX(j, X11.XDefaultScreen(j));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static GLXCapabilities createCapabilitiesGLX(long j, int i) {
        String memASCIISafe;
        FunctionProvider functionProvider2 = functionProvider;
        if (functionProvider2 == null) {
            throw new IllegalStateException("OpenGL library has not been loaded.");
        }
        MemoryStack stackPush = MemoryStack.stackPush();
        Throwable th = null;
        try {
            IntBuffer ints = stackPush.ints(0);
            IntBuffer ints2 = stackPush.ints(0);
            if (!GLX11.glXQueryVersion(j, ints, ints2)) {
                throw new IllegalStateException("Failed to query GLX version");
            }
            int i2 = ints.get(0);
            int i3 = ints2.get(0);
            if (i2 != 1) {
                throw new IllegalStateException("Invalid GLX major version: " + i2);
            }
            HashSet hashSet = new HashSet(32);
            int[] iArr = {new int[]{1, 2, 3, 4}};
            for (int i4 = 1; i4 <= 1; i4++) {
                for (byte b2 : iArr[i4 - 1]) {
                    if (i4 < i2 || (i4 == i2 && b2 <= i3)) {
                        hashSet.add("GLX" + i4 + ((int) b2));
                    }
                }
            }
            if (i3 > 0) {
                if (i == -1) {
                    memASCIISafe = MemoryUtil.memASCIISafe(JNI.callPP(j, 3, functionProvider2.getFunctionAddress("glXGetClientString")));
                } else {
                    memASCIISafe = MemoryUtil.memASCIISafe(JNI.callPP(j, i, functionProvider2.getFunctionAddress("glXQueryExtensionsString")));
                }
                if (memASCIISafe != null) {
                    StringTokenizer stringTokenizer = new StringTokenizer(memASCIISafe);
                    while (stringTokenizer.hasMoreTokens()) {
                        hashSet.add(stringTokenizer.nextToken());
                    }
                }
            }
            APIUtil.apiFilterExtensions(hashSet, Configuration.OPENGL_EXTENSION_FILTER);
            return new GLXCapabilities(functionProvider2, hashSet);
        } finally {
            if (stackPush != null) {
                if (0 != 0) {
                    try {
                        stackPush.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                } else {
                    stackPush.close();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GLCapabilities getICD() {
        return checkCapabilities(icd.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL$ICD.class */
    public interface ICD {
        GLCapabilities get();

        default void set(GLCapabilities gLCapabilities) {
        }
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL$ICDStatic.class */
    private static class ICDStatic implements ICD {
        private static GLCapabilities tempCaps;

        private ICDStatic() {
        }

        @Override // org.lwjgl.opengl.GL.ICD
        public void set(GLCapabilities gLCapabilities) {
            if (tempCaps == null) {
                tempCaps = gLCapabilities;
            } else if (gLCapabilities != null && gLCapabilities != tempCaps && ThreadLocalUtil.areCapabilitiesDifferent(tempCaps.addresses, gLCapabilities.addresses)) {
                APIUtil.apiLog("[WARNING] Incompatible context detected. Falling back to thread-local lookup for GL contexts.");
                ICD unused = GL.icd = GL::getCapabilities;
            }
        }

        @Override // org.lwjgl.opengl.GL.ICD
        public GLCapabilities get() {
            return WriteOnce.caps;
        }

        /* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL$ICDStatic$WriteOnce.class */
        private static final class WriteOnce {
            static final GLCapabilities caps;

            private WriteOnce() {
            }

            static {
                GLCapabilities gLCapabilities = ICDStatic.tempCaps;
                if (gLCapabilities == null) {
                    throw new IllegalStateException("No GLCapabilities instance has been set");
                }
                caps = gLCapabilities;
            }
        }
    }
}
