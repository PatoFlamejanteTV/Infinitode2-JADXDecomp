package org.lwjgl.opengl;

import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVInternalformatSampleQuery.class */
public class NVInternalformatSampleQuery {
    public static final int GL_MULTISAMPLES_NV = 37745;
    public static final int GL_SUPERSAMPLE_SCALE_X_NV = 37746;
    public static final int GL_SUPERSAMPLE_SCALE_Y_NV = 37747;
    public static final int GL_CONFORMANT_NV = 37748;

    public static native void nglGetInternalformatSampleivNV(int i, int i2, int i3, int i4, int i5, long j);

    static {
        GL.initialize();
    }

    protected NVInternalformatSampleQuery() {
        throw new UnsupportedOperationException();
    }

    public static void glGetInternalformatSampleivNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLenum") int i4, @NativeType("GLint *") IntBuffer intBuffer) {
        nglGetInternalformatSampleivNV(i, i2, i3, i4, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetInternalformatSampleivNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLenum") int i4, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetInternalformatSampleivNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, iArr.length, iArr, j);
    }
}
