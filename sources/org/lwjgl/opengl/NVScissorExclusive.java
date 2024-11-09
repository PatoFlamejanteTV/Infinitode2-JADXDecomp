package org.lwjgl.opengl;

import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVScissorExclusive.class */
public class NVScissorExclusive {
    public static final int GL_SCISSOR_TEST_EXCLUSIVE_NV = 38229;
    public static final int GL_SCISSOR_BOX_EXCLUSIVE_NV = 38230;

    public static native void nglScissorExclusiveArrayvNV(int i, int i2, long j);

    public static native void glScissorExclusiveNV(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4);

    static {
        GL.initialize();
    }

    protected NVScissorExclusive() {
        throw new UnsupportedOperationException();
    }

    public static void glScissorExclusiveArrayvNV(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        nglScissorExclusiveArrayvNV(i, intBuffer.remaining() >> 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glScissorExclusiveArrayvNV(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glScissorExclusiveArrayvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length >> 2, iArr, j);
    }
}
