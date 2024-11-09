package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLXNVSwapGroup.class */
public class GLXNVSwapGroup {
    protected GLXNVSwapGroup() {
        throw new UnsupportedOperationException();
    }

    @NativeType("Bool")
    public static boolean glXJoinSwapGroupNV(@NativeType("Display *") long j, @NativeType("GLXDrawable") long j2, @NativeType("GLuint") int i) {
        long j3 = GL.getCapabilitiesGLXClient().glXJoinSwapGroupNV;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.callPPI(j, j2, i, j3) != 0;
    }

    @NativeType("Bool")
    public static boolean glXBindSwapBarrierNV(@NativeType("Display *") long j, @NativeType("GLuint") int i, @NativeType("GLuint") int i2) {
        long j2 = GL.getCapabilitiesGLXClient().glXBindSwapBarrierNV;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPI(j, i, i2, j2) != 0;
    }

    public static int nglXQuerySwapGroupNV(long j, long j2, long j3, long j4) {
        long j5 = GL.getCapabilitiesGLXClient().glXQuerySwapGroupNV;
        if (Checks.CHECKS) {
            Checks.check(j5);
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.callPPPPI(j, j2, j3, j4, j5);
    }

    @NativeType("Bool")
    public static boolean glXQuerySwapGroupNV(@NativeType("Display *") long j, @NativeType("GLXDrawable") long j2, @NativeType("GLuint *") IntBuffer intBuffer, @NativeType("GLuint *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
        }
        return nglXQuerySwapGroupNV(j, j2, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2)) != 0;
    }

    public static int nglXQueryMaxSwapGroupsNV(long j, int i, long j2, long j3) {
        long j4 = GL.getCapabilitiesGLXClient().glXQueryMaxSwapGroupsNV;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
        }
        return JNI.callPPPI(j, i, j2, j3, j4);
    }

    @NativeType("Bool")
    public static boolean glXQueryMaxSwapGroupsNV(@NativeType("Display *") long j, int i, @NativeType("GLuint *") IntBuffer intBuffer, @NativeType("GLuint *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
        }
        return nglXQueryMaxSwapGroupsNV(j, i, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2)) != 0;
    }

    public static int nglXQueryFrameCountNV(long j, int i, long j2) {
        long j3 = GL.getCapabilitiesGLXClient().glXQueryFrameCountNV;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
        }
        return JNI.callPPI(j, i, j2, j3);
    }

    @NativeType("Bool")
    public static boolean glXQueryFrameCountNV(@NativeType("Display *") long j, int i, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        return nglXQueryFrameCountNV(j, i, MemoryUtil.memAddress(intBuffer)) != 0;
    }

    @NativeType("Bool")
    public static boolean glXResetFrameCountNV(@NativeType("Display *") long j, int i) {
        long j2 = GL.getCapabilitiesGLXClient().glXResetFrameCountNV;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPI(j, i, j2) != 0;
    }

    @NativeType("Bool")
    public static boolean glXQuerySwapGroupNV(@NativeType("Display *") long j, @NativeType("GLXDrawable") long j2, @NativeType("GLuint *") int[] iArr, @NativeType("GLuint *") int[] iArr2) {
        long j3 = GL.getCapabilitiesGLXClient().glXQuerySwapGroupNV;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
            Checks.check(j2);
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
        }
        return JNI.callPPPPI(j, j2, iArr, iArr2, j3) != 0;
    }

    @NativeType("Bool")
    public static boolean glXQueryMaxSwapGroupsNV(@NativeType("Display *") long j, int i, @NativeType("GLuint *") int[] iArr, @NativeType("GLuint *") int[] iArr2) {
        long j2 = GL.getCapabilitiesGLXClient().glXQueryMaxSwapGroupsNV;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
        }
        return JNI.callPPPI(j, i, iArr, iArr2, j2) != 0;
    }

    @NativeType("Bool")
    public static boolean glXQueryFrameCountNV(@NativeType("Display *") long j, int i, @NativeType("GLuint *") int[] iArr) {
        long j2 = GL.getCapabilitiesGLXClient().glXQueryFrameCountNV;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        return JNI.callPPI(j, i, iArr, j2) != 0;
    }
}
