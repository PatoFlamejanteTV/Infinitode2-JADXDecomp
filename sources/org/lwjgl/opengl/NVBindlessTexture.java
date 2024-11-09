package org.lwjgl.opengl;

import java.nio.LongBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVBindlessTexture.class */
public class NVBindlessTexture {
    @NativeType("GLuint64")
    public static native long glGetTextureHandleNV(@NativeType("GLuint") int i);

    @NativeType("GLuint64")
    public static native long glGetTextureSamplerHandleNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2);

    public static native void glMakeTextureHandleResidentNV(@NativeType("GLuint64") long j);

    public static native void glMakeTextureHandleNonResidentNV(@NativeType("GLuint64") long j);

    @NativeType("GLuint64")
    public static native long glGetImageHandleNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLint") int i3, @NativeType("GLenum") int i4);

    public static native void glMakeImageHandleResidentNV(@NativeType("GLuint64") long j, @NativeType("GLenum") int i);

    public static native void glMakeImageHandleNonResidentNV(@NativeType("GLuint64") long j);

    public static native void glUniformHandleui64NV(@NativeType("GLint") int i, @NativeType("GLuint64") long j);

    public static native void nglUniformHandleui64vNV(int i, int i2, long j);

    public static native void glProgramUniformHandleui64NV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64") long j);

    public static native void nglProgramUniformHandleui64vNV(int i, int i2, int i3, long j);

    @NativeType("GLboolean")
    public static native boolean glIsTextureHandleResidentNV(@NativeType("GLuint64") long j);

    @NativeType("GLboolean")
    public static native boolean glIsImageHandleResidentNV(@NativeType("GLuint64") long j);

    static {
        GL.initialize();
    }

    protected NVBindlessTexture() {
        throw new UnsupportedOperationException();
    }

    public static void glUniformHandleui64vNV(@NativeType("GLint") int i, @NativeType("GLuint64 const *") LongBuffer longBuffer) {
        nglUniformHandleui64vNV(i, longBuffer.remaining(), MemoryUtil.memAddress(longBuffer));
    }

    public static void glProgramUniformHandleui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64 const *") LongBuffer longBuffer) {
        nglProgramUniformHandleui64vNV(i, i2, longBuffer.remaining(), MemoryUtil.memAddress(longBuffer));
    }

    public static void glUniformHandleui64vNV(@NativeType("GLint") int i, @NativeType("GLuint64 const *") long[] jArr) {
        long j = GL.getICD().glUniformHandleui64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, jArr.length, jArr, j);
    }

    public static void glProgramUniformHandleui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64 const *") long[] jArr) {
        long j = GL.getICD().glProgramUniformHandleui64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, jArr.length, jArr, j);
    }
}
