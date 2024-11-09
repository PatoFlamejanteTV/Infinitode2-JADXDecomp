package org.lwjgl.opengl;

import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLXEXTSwapControl.class */
public class GLXEXTSwapControl {
    public static final int GLX_SWAP_INTERVAL_EXT = 8433;
    public static final int GLX_MAX_SWAP_INTERVAL_EXT = 8434;

    protected GLXEXTSwapControl() {
        throw new UnsupportedOperationException();
    }

    public static void glXSwapIntervalEXT(@NativeType("Display *") long j, @NativeType("GLXDrawable") long j2, int i) {
        long j3 = GL.getCapabilitiesGLXClient().glXSwapIntervalEXT;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
            Checks.check(j2);
        }
        JNI.callPPV(j, j2, i, j3);
    }
}
