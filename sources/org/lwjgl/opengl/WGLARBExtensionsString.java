package org.lwjgl.opengl;

import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/WGLARBExtensionsString.class */
public class WGLARBExtensionsString {
    protected WGLARBExtensionsString() {
        throw new UnsupportedOperationException();
    }

    public static long nwglGetExtensionsStringARB(long j) {
        long j2 = GL.getCapabilitiesWGL().wglGetExtensionsStringARB;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPP(j, j2);
    }

    @NativeType("char const *")
    public static String wglGetExtensionsStringARB(@NativeType("HDC") long j) {
        return MemoryUtil.memASCIISafe(nwglGetExtensionsStringARB(j));
    }
}
