package org.lwjgl.opengl;

import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/WGLNVDelayBeforeSwap.class */
public class WGLNVDelayBeforeSwap {
    protected WGLNVDelayBeforeSwap() {
        throw new UnsupportedOperationException();
    }

    @NativeType("BOOL")
    public static boolean wglDelayBeforeSwapNV(@NativeType("HDC") long j, @NativeType("GLfloat") float f) {
        long j2 = GL.getCapabilitiesWGL().wglDelayBeforeSwapNV;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPI(j, f, j2) != 0;
    }
}
