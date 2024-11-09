package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.LongBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBBindlessTexture.class */
public class ARBBindlessTexture {
    public static final int GL_UNSIGNED_INT64_ARB = 5135;

    @NativeType("GLuint64")
    public static native long glGetTextureHandleARB(@NativeType("GLuint") int i);

    @NativeType("GLuint64")
    public static native long glGetTextureSamplerHandleARB(@NativeType("GLuint") int i, @NativeType("GLuint") int i2);

    public static native void glMakeTextureHandleResidentARB(@NativeType("GLuint64") long j);

    public static native void glMakeTextureHandleNonResidentARB(@NativeType("GLuint64") long j);

    @NativeType("GLuint64")
    public static native long glGetImageHandleARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLint") int i3, @NativeType("GLenum") int i4);

    public static native void glMakeImageHandleResidentARB(@NativeType("GLuint64") long j, @NativeType("GLenum") int i);

    public static native void glMakeImageHandleNonResidentARB(@NativeType("GLuint64") long j);

    public static native void glUniformHandleui64ARB(@NativeType("GLint") int i, @NativeType("GLuint64") long j);

    public static native void nglUniformHandleui64vARB(int i, int i2, long j);

    public static native void glProgramUniformHandleui64ARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64") long j);

    public static native void nglProgramUniformHandleui64vARB(int i, int i2, int i3, long j);

    @NativeType("GLboolean")
    public static native boolean glIsTextureHandleResidentARB(@NativeType("GLuint64") long j);

    @NativeType("GLboolean")
    public static native boolean glIsImageHandleResidentARB(@NativeType("GLuint64") long j);

    public static native void glVertexAttribL1ui64ARB(@NativeType("GLuint") int i, @NativeType("GLuint64") long j);

    public static native void nglVertexAttribL1ui64vARB(int i, long j);

    public static native void nglGetVertexAttribLui64vARB(int i, int i2, long j);

    static {
        GL.initialize();
    }

    protected ARBBindlessTexture() {
        throw new UnsupportedOperationException();
    }

    public static void glUniformHandleui64vARB(@NativeType("GLint") int i, @NativeType("GLuint64 const *") LongBuffer longBuffer) {
        nglUniformHandleui64vARB(i, longBuffer.remaining(), MemoryUtil.memAddress(longBuffer));
    }

    public static void glProgramUniformHandleui64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64 const *") LongBuffer longBuffer) {
        nglProgramUniformHandleui64vARB(i, i2, longBuffer.remaining(), MemoryUtil.memAddress(longBuffer));
    }

    public static void glVertexAttribL1ui64vARB(@NativeType("GLuint") int i, @NativeType("GLuint64 const *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglVertexAttribL1ui64vARB(i, MemoryUtil.memAddress(longBuffer));
    }

    public static void glGetVertexAttribLui64vARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint64 *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglGetVertexAttribLui64vARB(i, i2, MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("void")
    public static long glGetVertexAttribLui64ARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglGetVertexAttribLui64vARB(i, i2, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glUniformHandleui64vARB(@NativeType("GLint") int i, @NativeType("GLuint64 const *") long[] jArr) {
        long j = GL.getICD().glUniformHandleui64vARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, jArr.length, jArr, j);
    }

    public static void glProgramUniformHandleui64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64 const *") long[] jArr) {
        long j = GL.getICD().glProgramUniformHandleui64vARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, jArr.length, jArr, j);
    }

    public static void glVertexAttribL1ui64vARB(@NativeType("GLuint") int i, @NativeType("GLuint64 const *") long[] jArr) {
        long j = GL.getICD().glVertexAttribL1ui64vARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, jArr, j);
    }

    public static void glGetVertexAttribLui64vARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint64 *") long[] jArr) {
        long j = GL.getICD().glGetVertexAttribLui64vARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, i2, jArr, j);
    }
}
