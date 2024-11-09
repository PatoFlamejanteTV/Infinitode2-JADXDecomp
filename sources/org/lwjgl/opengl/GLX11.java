package org.lwjgl.opengl;

import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLX11.class */
public class GLX11 extends GLX {
    public static final int GLX_VENDOR = 1;
    public static final int GLX_VERSION = 2;
    public static final int GLX_EXTENSIONS = 3;

    /* JADX INFO: Access modifiers changed from: protected */
    public GLX11() {
        throw new UnsupportedOperationException();
    }

    public static long nglXQueryExtensionsString(long j, int i) {
        long j2 = GL.getCapabilitiesGLXClient().glXQueryExtensionsString;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPP(j, i, j2);
    }

    @NativeType("char const *")
    public static String glXQueryExtensionsString(@NativeType("Display *") long j, int i) {
        return MemoryUtil.memASCIISafe(nglXQueryExtensionsString(j, i));
    }

    public static long nglXGetClientString(long j, int i) {
        long j2 = GL.getCapabilitiesGLXClient().glXGetClientString;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPP(j, i, j2);
    }

    @NativeType("char const *")
    public static String glXGetClientString(@NativeType("Display *") long j, int i) {
        return MemoryUtil.memASCIISafe(nglXGetClientString(j, i));
    }

    public static long nglXQueryServerString(long j, int i, int i2) {
        long j2 = GL.getCapabilitiesGLXClient().glXQueryServerString;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPP(j, i, i2, j2);
    }

    @NativeType("char const *")
    public static String glXQueryServerString(@NativeType("Display *") long j, int i, int i2) {
        return MemoryUtil.memASCIISafe(nglXQueryServerString(j, i, i2));
    }
}
