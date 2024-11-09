package org.lwjgl.opengl;

import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTEGLImageStorage.class */
public class EXTEGLImageStorage {
    public static native void nglEGLImageTargetTexStorageEXT(int i, long j, long j2);

    public static native void nglEGLImageTargetTextureStorageEXT(int i, long j, long j2);

    static {
        GL.initialize();
    }

    protected EXTEGLImageStorage() {
        throw new UnsupportedOperationException();
    }

    public static void glEGLImageTargetTexStorageEXT(@NativeType("GLenum") int i, @NativeType("GLeglImageOES") long j, @NativeType("int const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkNTSafe(intBuffer);
        }
        nglEGLImageTargetTexStorageEXT(i, j, MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glEGLImageTargetTextureStorageEXT(@NativeType("GLuint") int i, @NativeType("GLeglImageOES") long j, @NativeType("int const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkNTSafe(intBuffer);
        }
        nglEGLImageTargetTextureStorageEXT(i, j, MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glEGLImageTargetTexStorageEXT(@NativeType("GLenum") int i, @NativeType("GLeglImageOES") long j, @NativeType("int const *") int[] iArr) {
        long j2 = GL.getICD().glEGLImageTargetTexStorageEXT;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
            Checks.checkNTSafe(iArr);
        }
        JNI.callPPV(i, j, iArr, j2);
    }

    public static void glEGLImageTargetTextureStorageEXT(@NativeType("GLuint") int i, @NativeType("GLeglImageOES") long j, @NativeType("int const *") int[] iArr) {
        long j2 = GL.getICD().glEGLImageTargetTextureStorageEXT;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
            Checks.checkNTSafe(iArr);
        }
        JNI.callPPV(i, j, iArr, j2);
    }
}
