package org.lwjgl.opengl;

import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLXARBCreateContext.class */
public class GLXARBCreateContext {
    public static final int GLX_CONTEXT_MAJOR_VERSION_ARB = 8337;
    public static final int GLX_CONTEXT_MINOR_VERSION_ARB = 8338;
    public static final int GLX_CONTEXT_FLAGS_ARB = 8340;
    public static final int GLX_CONTEXT_DEBUG_BIT_ARB = 1;
    public static final int GLX_CONTEXT_FORWARD_COMPATIBLE_BIT_ARB = 2;

    protected GLXARBCreateContext() {
        throw new UnsupportedOperationException();
    }

    public static long nglXCreateContextAttribsARB(long j, long j2, long j3, int i, long j4) {
        long j5 = GL.getCapabilitiesGLXClient().glXCreateContextAttribsARB;
        if (Checks.CHECKS) {
            Checks.check(j5);
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.callPPPPP(j, j2, j3, i, j4, j5);
    }

    @NativeType("GLXContext")
    public static long glXCreateContextAttribsARB(@NativeType("Display *") long j, @NativeType("GLXFBConfig") long j2, @NativeType("GLXContext") long j3, @NativeType("Bool") boolean z, @NativeType("int const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNTSafe(intBuffer);
        }
        return nglXCreateContextAttribsARB(j, j2, j3, z ? 1 : 0, MemoryUtil.memAddressSafe(intBuffer));
    }

    @NativeType("GLXContext")
    public static long glXCreateContextAttribsARB(@NativeType("Display *") long j, @NativeType("GLXFBConfig") long j2, @NativeType("GLXContext") long j3, @NativeType("Bool") boolean z, @NativeType("int const *") int[] iArr) {
        long j4 = GL.getCapabilitiesGLXClient().glXCreateContextAttribsARB;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
            Checks.check(j2);
            Checks.checkNTSafe(iArr);
        }
        return JNI.callPPPPP(j, j2, j3, z ? 1 : 0, iArr, j4);
    }
}
