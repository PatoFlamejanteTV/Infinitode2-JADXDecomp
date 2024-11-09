package org.lwjgl.opengl;

import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLXNVDelayBeforeSwap.class */
public class GLXNVDelayBeforeSwap {
    protected GLXNVDelayBeforeSwap() {
        throw new UnsupportedOperationException();
    }

    @NativeType("Bool")
    public static boolean glXDelayBeforeSwapNV(@NativeType("Display *") long j, @NativeType("GLXDrawable") long j2, @NativeType("GLfloat") float f) {
        long j3 = GL.getCapabilitiesGLXClient().glXDelayBeforeSwapNV;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.callPPI(j, j2, f, j3) != 0;
    }
}
