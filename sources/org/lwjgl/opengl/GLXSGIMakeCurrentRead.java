package org.lwjgl.opengl;

import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLXSGIMakeCurrentRead.class */
public class GLXSGIMakeCurrentRead {
    protected GLXSGIMakeCurrentRead() {
        throw new UnsupportedOperationException();
    }

    @NativeType("Bool")
    public static boolean glXMakeCurrentReadSGI(@NativeType("Display *") long j, @NativeType("GLXDrawable") long j2, @NativeType("GLXDrawable") long j3, @NativeType("GLXContext") long j4) {
        long j5 = GL.getCapabilitiesGLXClient().glXMakeCurrentReadSGI;
        if (Checks.CHECKS) {
            Checks.check(j5);
            Checks.check(j);
        }
        return JNI.callPPPPI(j, j2, j3, j4, j5) != 0;
    }

    @NativeType("GLXDrawable")
    public static long glXGetCurrentReadDrawableSGI() {
        long j = GL.getCapabilitiesGLXClient().glXGetCurrentReadDrawableSGI;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callP(j);
    }
}
