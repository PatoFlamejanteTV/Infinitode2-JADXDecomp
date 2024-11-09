package org.lwjgl.opengl;

import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLXSGIXSwapGroup.class */
public class GLXSGIXSwapGroup {
    protected GLXSGIXSwapGroup() {
        throw new UnsupportedOperationException();
    }

    public static void glXJoinSwapGroupSGIX(@NativeType("Display *") long j, @NativeType("GLXDrawable") long j2, @NativeType("GLXDrawable") long j3) {
        long j4 = GL.getCapabilitiesGLXClient().glXJoinSwapGroupSGIX;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
            Checks.check(j2);
        }
        JNI.callPPPV(j, j2, j3, j4);
    }
}
