package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/WGLARBPbuffer.class */
public class WGLARBPbuffer {
    public static final int WGL_DRAW_TO_PBUFFER_ARB = 8237;
    public static final int WGL_MAX_PBUFFER_PIXELS_ARB = 8238;
    public static final int WGL_MAX_PBUFFER_WIDTH_ARB = 8239;
    public static final int WGL_MAX_PBUFFER_HEIGHT_ARB = 8240;
    public static final int WGL_PBUFFER_LARGEST_ARB = 8243;
    public static final int WGL_PBUFFER_WIDTH_ARB = 8244;
    public static final int WGL_PBUFFER_HEIGHT_ARB = 8245;
    public static final int WGL_PBUFFER_LOST_ARB = 8246;

    protected WGLARBPbuffer() {
        throw new UnsupportedOperationException();
    }

    public static long nwglCreatePbufferARB(long j, int i, int i2, int i3, long j2) {
        long j3 = GL.getCapabilitiesWGL().wglCreatePbufferARB;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
        }
        return JNI.callPPP(j, i, i2, i3, j2, j3);
    }

    @NativeType("HPBUFFERARB")
    public static long wglCreatePbufferARB(@NativeType("HDC") long j, int i, int i2, int i3, @NativeType("int const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNTSafe(intBuffer);
        }
        return nwglCreatePbufferARB(j, i, i2, i3, MemoryUtil.memAddressSafe(intBuffer));
    }

    @NativeType("HDC")
    public static long wglGetPbufferDCARB(@NativeType("HPBUFFERARB") long j) {
        long j2 = GL.getCapabilitiesWGL().wglGetPbufferDCARB;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPP(j, j2);
    }

    public static int wglReleasePbufferDCARB(@NativeType("HPBUFFERARB") long j, @NativeType("HDC") long j2) {
        long j3 = GL.getCapabilitiesWGL().wglReleasePbufferDCARB;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.callPPI(j, j2, j3);
    }

    @NativeType("BOOL")
    public static boolean wglDestroyPbufferARB(@NativeType("HPBUFFERARB") long j) {
        long j2 = GL.getCapabilitiesWGL().wglDestroyPbufferARB;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPI(j, j2) != 0;
    }

    public static int nwglQueryPbufferARB(long j, int i, long j2) {
        long j3 = GL.getCapabilitiesWGL().wglQueryPbufferARB;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
        }
        return JNI.callPPI(j, i, j2, j3);
    }

    @NativeType("BOOL")
    public static boolean wglQueryPbufferARB(@NativeType("HPBUFFERARB") long j, int i, @NativeType("int *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        return nwglQueryPbufferARB(j, i, MemoryUtil.memAddress(intBuffer)) != 0;
    }

    @NativeType("HPBUFFERARB")
    public static long wglCreatePbufferARB(@NativeType("HDC") long j, int i, int i2, int i3, @NativeType("int const *") int[] iArr) {
        long j2 = GL.getCapabilitiesWGL().wglCreatePbufferARB;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
            Checks.checkNTSafe(iArr);
        }
        return JNI.callPPP(j, i, i2, i3, iArr, j2);
    }

    @NativeType("BOOL")
    public static boolean wglQueryPbufferARB(@NativeType("HPBUFFERARB") long j, int i, @NativeType("int *") int[] iArr) {
        long j2 = GL.getCapabilitiesWGL().wglQueryPbufferARB;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        return JNI.callPPI(j, i, iArr, j2) != 0;
    }
}
