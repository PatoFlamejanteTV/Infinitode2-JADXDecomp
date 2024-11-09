package org.lwjgl.opengl;

import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLX12.class */
public class GLX12 extends GLX11 {
    /* JADX INFO: Access modifiers changed from: protected */
    public GLX12() {
        throw new UnsupportedOperationException();
    }

    @NativeType("Display *")
    public static long glXGetCurrentDisplay() {
        long j = GL.getCapabilitiesGLXClient().glXGetCurrentDisplay;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callP(j);
    }
}
