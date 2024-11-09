package org.lwjgl.opengl;

import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/WGLARBBufferRegion.class */
public class WGLARBBufferRegion {
    public static final int WGL_FRONT_COLOR_BUFFER_BIT_ARB = 1;
    public static final int WGL_BACK_COLOR_BUFFER_BIT_ARB = 2;
    public static final int WGL_DEPTH_BUFFER_BIT_ARB = 4;
    public static final int WGL_STENCIL_BUFFER_BIT_ARB = 8;

    protected WGLARBBufferRegion() {
        throw new UnsupportedOperationException();
    }

    @NativeType("HANDLE")
    public static long wglCreateBufferRegionARB(@NativeType("HDC") long j, int i, @NativeType("UINT") int i2) {
        long j2 = GL.getCapabilitiesWGL().wglCreateBufferRegionARB;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPP(j, i, i2, j2);
    }

    @NativeType("VOID")
    public static void wglDeleteBufferRegionARB(@NativeType("HANDLE") long j) {
        long j2 = GL.getCapabilitiesWGL().wglDeleteBufferRegionARB;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        JNI.callPV(j, j2);
    }

    @NativeType("BOOL")
    public static boolean wglSaveBufferRegionARB(@NativeType("HANDLE") long j, int i, int i2, int i3, int i4) {
        long j2 = GL.getCapabilitiesWGL().wglSaveBufferRegionARB;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPI(j, i, i2, i3, i4, j2) != 0;
    }

    @NativeType("BOOL")
    public static boolean wglRestoreBufferRegionARB(@NativeType("HANDLE") long j, int i, int i2, int i3, int i4, int i5, int i6) {
        long j2 = GL.getCapabilitiesWGL().wglRestoreBufferRegionARB;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPI(j, i, i2, i3, i4, i5, i6, j2) != 0;
    }
}
