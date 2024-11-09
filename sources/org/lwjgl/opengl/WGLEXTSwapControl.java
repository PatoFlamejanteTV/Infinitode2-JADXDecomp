package org.lwjgl.opengl;

import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/WGLEXTSwapControl.class */
public class WGLEXTSwapControl {
    protected WGLEXTSwapControl() {
        throw new UnsupportedOperationException();
    }

    @NativeType("BOOL")
    public static boolean wglSwapIntervalEXT(int i) {
        long j = GL.getCapabilitiesWGL().wglSwapIntervalEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callI(i, j) != 0;
    }

    public static int wglGetSwapIntervalEXT() {
        long j = GL.getCapabilitiesWGL().wglGetSwapIntervalEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callI(j);
    }
}
