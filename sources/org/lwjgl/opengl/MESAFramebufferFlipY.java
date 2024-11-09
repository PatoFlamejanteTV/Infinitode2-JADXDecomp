package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/MESAFramebufferFlipY.class */
public class MESAFramebufferFlipY {
    public static final int GL_FRAMEBUFFER_FLIP_Y_MESA = 35771;

    public static native void glFramebufferParameteriMESA(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3);

    public static native void nglGetFramebufferParameterivMESA(int i, int i2, long j);

    static {
        GL.initialize();
    }

    protected MESAFramebufferFlipY() {
        throw new UnsupportedOperationException();
    }

    public static void glGetFramebufferParameterivMESA(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetFramebufferParameterivMESA(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetFramebufferParameterivMESA(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetFramebufferParameterivMESA;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }
}
