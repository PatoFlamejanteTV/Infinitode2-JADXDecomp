package org.lwjgl.opengl;

import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/WGLARBMakeCurrentRead.class */
public class WGLARBMakeCurrentRead {
    public static final int ERROR_INVALID_PIXEL_TYPE_ARB = 8259;
    public static final int ERROR_INCOMPATIBLE_DEVICE_CONTEXTS_ARB = 8276;

    protected WGLARBMakeCurrentRead() {
        throw new UnsupportedOperationException();
    }

    @NativeType("BOOL")
    public static boolean wglMakeContextCurrentARB(@NativeType("HDC") long j, @NativeType("HDC") long j2, @NativeType("HGLRC") long j3) {
        long j4 = GL.getCapabilitiesWGL().wglMakeContextCurrentARB;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
            Checks.check(j2);
            Checks.check(j3);
        }
        return JNI.callPPPI(j, j2, j3, j4) != 0;
    }

    @NativeType("HDC")
    public static long wglGetCurrentReadDCARB() {
        long j = GL.getCapabilitiesWGL().wglGetCurrentReadDCARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callP(j);
    }
}
