package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/WGLNVSwapGroup.class */
public class WGLNVSwapGroup {
    protected WGLNVSwapGroup() {
        throw new UnsupportedOperationException();
    }

    @NativeType("BOOL")
    public static boolean wglJoinSwapGroupNV(@NativeType("HDC") long j, @NativeType("GLuint") int i) {
        long j2 = GL.getCapabilitiesWGL().wglJoinSwapGroupNV;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPI(j, i, j2) != 0;
    }

    @NativeType("BOOL")
    public static boolean wglBindSwapBarrierNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2) {
        long j = GL.getCapabilitiesWGL().wglBindSwapBarrierNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callI(i, i2, j) != 0;
    }

    public static int nwglQuerySwapGroupNV(long j, long j2, long j3) {
        long j4 = GL.getCapabilitiesWGL().wglQuerySwapGroupNV;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
        }
        return JNI.callPPPI(j, j2, j3, j4);
    }

    @NativeType("BOOL")
    public static boolean wglQuerySwapGroupNV(@NativeType("HDC") long j, @NativeType("GLuint *") IntBuffer intBuffer, @NativeType("GLuint *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
        }
        return nwglQuerySwapGroupNV(j, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2)) != 0;
    }

    public static int nwglQueryMaxSwapGroupsNV(long j, long j2, long j3) {
        long j4 = GL.getCapabilitiesWGL().wglQueryMaxSwapGroupsNV;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
        }
        return JNI.callPPPI(j, j2, j3, j4);
    }

    @NativeType("BOOL")
    public static boolean wglQueryMaxSwapGroupsNV(@NativeType("HDC") long j, @NativeType("GLuint *") IntBuffer intBuffer, @NativeType("GLuint *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
        }
        return nwglQueryMaxSwapGroupsNV(j, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2)) != 0;
    }

    public static int nwglQueryFrameCountNV(long j, long j2) {
        long j3 = GL.getCapabilitiesWGL().wglQueryFrameCountNV;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
        }
        return JNI.callPPI(j, j2, j3);
    }

    @NativeType("BOOL")
    public static boolean wglQueryFrameCountNV(@NativeType("HDC") long j, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        return nwglQueryFrameCountNV(j, MemoryUtil.memAddress(intBuffer)) != 0;
    }

    @NativeType("BOOL")
    public static boolean wglResetFrameCountNV(@NativeType("HDC") long j) {
        long j2 = GL.getCapabilitiesWGL().wglResetFrameCountNV;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPI(j, j2) != 0;
    }

    @NativeType("BOOL")
    public static boolean wglQuerySwapGroupNV(@NativeType("HDC") long j, @NativeType("GLuint *") int[] iArr, @NativeType("GLuint *") int[] iArr2) {
        long j2 = GL.getCapabilitiesWGL().wglQuerySwapGroupNV;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
        }
        return JNI.callPPPI(j, iArr, iArr2, j2) != 0;
    }

    @NativeType("BOOL")
    public static boolean wglQueryMaxSwapGroupsNV(@NativeType("HDC") long j, @NativeType("GLuint *") int[] iArr, @NativeType("GLuint *") int[] iArr2) {
        long j2 = GL.getCapabilitiesWGL().wglQueryMaxSwapGroupsNV;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
        }
        return JNI.callPPPI(j, iArr, iArr2, j2) != 0;
    }

    @NativeType("BOOL")
    public static boolean wglQueryFrameCountNV(@NativeType("HDC") long j, @NativeType("GLuint *") int[] iArr) {
        long j2 = GL.getCapabilitiesWGL().wglQueryFrameCountNV;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        return JNI.callPPI(j, iArr, j2) != 0;
    }
}
