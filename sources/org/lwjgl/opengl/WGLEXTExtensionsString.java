package org.lwjgl.opengl;

import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/WGLEXTExtensionsString.class */
public class WGLEXTExtensionsString {
    protected WGLEXTExtensionsString() {
        throw new UnsupportedOperationException();
    }

    public static long nwglGetExtensionsStringEXT() {
        long j = GL.getCapabilitiesWGL().wglGetExtensionsStringEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callP(j);
    }

    @NativeType("char const *")
    public static String wglGetExtensionsStringEXT() {
        return MemoryUtil.memASCIISafe(nwglGetExtensionsStringEXT());
    }
}
