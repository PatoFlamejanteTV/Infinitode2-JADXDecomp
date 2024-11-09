package org.lwjgl.opengl;

import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLXSGISwapControl.class */
public class GLXSGISwapControl {
    protected GLXSGISwapControl() {
        throw new UnsupportedOperationException();
    }

    @NativeType("GLint")
    public static int glXSwapIntervalSGI(int i) {
        long j = GL.getCapabilitiesGLXClient().glXSwapIntervalSGI;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callI(i, j);
    }
}
