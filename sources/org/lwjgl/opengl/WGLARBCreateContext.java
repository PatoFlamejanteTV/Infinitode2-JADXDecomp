package org.lwjgl.opengl;

import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/WGLARBCreateContext.class */
public class WGLARBCreateContext {
    public static final int WGL_CONTEXT_MAJOR_VERSION_ARB = 8337;
    public static final int WGL_CONTEXT_MINOR_VERSION_ARB = 8338;
    public static final int WGL_CONTEXT_LAYER_PLANE_ARB = 8339;
    public static final int WGL_CONTEXT_FLAGS_ARB = 8340;
    public static final int WGL_CONTEXT_DEBUG_BIT_ARB = 1;
    public static final int WGL_CONTEXT_FORWARD_COMPATIBLE_BIT_ARB = 2;
    public static final int ERROR_INVALID_VERSION_ARB = 8341;

    protected WGLARBCreateContext() {
        throw new UnsupportedOperationException();
    }

    public static long nwglCreateContextAttribsARB(long j, long j2, long j3) {
        long j4 = GL.getCapabilitiesWGL().wglCreateContextAttribsARB;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
        }
        return JNI.callPPPP(j, j2, j3, j4);
    }

    @NativeType("HGLRC")
    public static long wglCreateContextAttribsARB(@NativeType("HDC") long j, @NativeType("HGLRC") long j2, @NativeType("int const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNTSafe(intBuffer);
        }
        return nwglCreateContextAttribsARB(j, j2, MemoryUtil.memAddressSafe(intBuffer));
    }

    @NativeType("HGLRC")
    public static long wglCreateContextAttribsARB(@NativeType("HDC") long j, @NativeType("HGLRC") long j2, @NativeType("int const *") int[] iArr) {
        long j3 = GL.getCapabilitiesWGL().wglCreateContextAttribsARB;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
            Checks.checkNTSafe(iArr);
        }
        return JNI.callPPPP(j, j2, iArr, j3);
    }
}
