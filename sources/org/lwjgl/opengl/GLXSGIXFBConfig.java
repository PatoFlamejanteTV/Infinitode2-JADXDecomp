package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.IntBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.linux.XVisualInfo;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLXSGIXFBConfig.class */
public class GLXSGIXFBConfig {
    public static final int GLX_DRAWABLE_TYPE_SGIX = 32784;
    public static final int GLX_RENDER_TYPE_SGIX = 32785;
    public static final int GLX_X_RENDERABLE_SGIX = 32786;
    public static final int GLX_FBCONFIG_ID_SGIX = 32787;
    public static final int GLX_SCREEN_EXT = 32780;
    public static final int GLX_WINDOW_BIT_SGIX = 1;
    public static final int GLX_PIXMAP_BIT_SGIX = 2;
    public static final int GLX_RGBA_BIT_SGIX = 1;
    public static final int GLX_COLOR_INDEX_BIT_SGIX = 2;
    public static final int GLX_RGBA_TYPE_SGIX = 32788;
    public static final int GLX_COLOR_INDEX_TYPE_SGIX = 32789;

    protected GLXSGIXFBConfig() {
        throw new UnsupportedOperationException();
    }

    public static int nglXGetFBConfigAttribSGIX(long j, long j2, int i, long j3) {
        long j4 = GL.getCapabilitiesGLXClient().glXGetFBConfigAttribSGIX;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.callPPPI(j, j2, i, j3, j4);
    }

    public static int glXGetFBConfigAttribSGIX(@NativeType("Display *") long j, @NativeType("GLXFBConfigSGIX") long j2, int i, @NativeType("int *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        return nglXGetFBConfigAttribSGIX(j, j2, i, MemoryUtil.memAddress(intBuffer));
    }

    public static long nglXChooseFBConfigSGIX(long j, int i, long j2, long j3) {
        long j4 = GL.getCapabilitiesGLXClient().glXChooseFBConfigSGIX;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
        }
        return JNI.callPPPP(j, i, j2, j3, j4);
    }

    @NativeType("GLXFBConfigSGIX *")
    public static PointerBuffer glXChooseFBConfigSGIX(@NativeType("Display *") long j, int i, @NativeType("int const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNTSafe(intBuffer);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return MemoryUtil.memPointerBufferSafe(nglXChooseFBConfigSGIX(j, i, MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("GLXPixmap")
    public static long glXCreateGLXPixmapWithConfigSGIX(@NativeType("Display *") long j, @NativeType("GLXFBConfig") long j2, @NativeType("Pixmap") long j3) {
        long j4 = GL.getCapabilitiesGLXClient().glXCreateGLXPixmapWithConfigSGIX;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.callPPNP(j, j2, j3, j4);
    }

    @NativeType("GLXContext")
    public static long glXCreateContextWithConfigSGIX(@NativeType("Display *") long j, @NativeType("GLXFBConfig") long j2, int i, @NativeType("GLXContext") long j3, @NativeType("Bool") boolean z) {
        long j4 = GL.getCapabilitiesGLXClient().glXCreateContextWithConfigSGIX;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
            Checks.check(j2);
            Checks.check(j3);
        }
        return JNI.callPPPP(j, j2, i, j3, z ? 1 : 0, j4);
    }

    public static long nglXGetVisualFromFBConfigSGIX(long j, long j2) {
        long j3 = GL.getCapabilitiesGLXClient().glXGetVisualFromFBConfigSGIX;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.callPPP(j, j2, j3);
    }

    @NativeType("XVisualInfo *")
    public static XVisualInfo glXGetVisualFromFBConfigSGIX(@NativeType("Display *") long j, @NativeType("GLXFBConfig") long j2) {
        return XVisualInfo.createSafe(nglXGetVisualFromFBConfigSGIX(j, j2));
    }

    public static long nglXGetFBConfigFromVisualSGIX(long j, long j2) {
        long j3 = GL.getCapabilitiesGLXClient().glXGetFBConfigFromVisualSGIX;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
            XVisualInfo.validate(j2);
        }
        return JNI.callPPP(j, j2, j3);
    }

    @NativeType("GLXFBConfigSGIX")
    public static long glXGetFBConfigFromVisualSGIX(@NativeType("Display *") long j, @NativeType("XVisualInfo *") XVisualInfo xVisualInfo) {
        return nglXGetFBConfigFromVisualSGIX(j, xVisualInfo.address());
    }

    public static int glXGetFBConfigAttribSGIX(@NativeType("Display *") long j, @NativeType("GLXFBConfigSGIX") long j2, int i, @NativeType("int *") int[] iArr) {
        long j3 = GL.getCapabilitiesGLXClient().glXGetFBConfigAttribSGIX;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
            Checks.check(j2);
            Checks.check(iArr, 1);
        }
        return JNI.callPPPI(j, j2, i, iArr, j3);
    }

    @NativeType("GLXFBConfigSGIX *")
    public static PointerBuffer glXChooseFBConfigSGIX(@NativeType("Display *") long j, int i, @NativeType("int const *") int[] iArr) {
        long j2 = GL.getCapabilitiesGLXClient().glXChooseFBConfigSGIX;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
            Checks.checkNTSafe(iArr);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return MemoryUtil.memPointerBufferSafe(JNI.callPPPP(j, i, iArr, MemoryUtil.memAddress(callocInt), j2), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }
}
