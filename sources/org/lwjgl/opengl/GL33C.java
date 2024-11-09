package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL33C.class */
public class GL33C extends GL32C {
    public static final int GL_SRC1_COLOR = 35065;
    public static final int GL_ONE_MINUS_SRC1_COLOR = 35066;
    public static final int GL_ONE_MINUS_SRC1_ALPHA = 35067;
    public static final int GL_MAX_DUAL_SOURCE_DRAW_BUFFERS = 35068;
    public static final int GL_ANY_SAMPLES_PASSED = 35887;
    public static final int GL_SAMPLER_BINDING = 35097;
    public static final int GL_RGB10_A2UI = 36975;
    public static final int GL_TEXTURE_SWIZZLE_R = 36418;
    public static final int GL_TEXTURE_SWIZZLE_G = 36419;
    public static final int GL_TEXTURE_SWIZZLE_B = 36420;
    public static final int GL_TEXTURE_SWIZZLE_A = 36421;
    public static final int GL_TEXTURE_SWIZZLE_RGBA = 36422;
    public static final int GL_TIME_ELAPSED = 35007;
    public static final int GL_TIMESTAMP = 36392;
    public static final int GL_VERTEX_ATTRIB_ARRAY_DIVISOR = 35070;
    public static final int GL_INT_2_10_10_10_REV = 36255;

    public static native void nglBindFragDataLocationIndexed(int i, int i2, int i3, long j);

    public static native int nglGetFragDataIndex(int i, long j);

    public static native void nglGenSamplers(int i, long j);

    public static native void nglDeleteSamplers(int i, long j);

    @NativeType("GLboolean")
    public static native boolean glIsSampler(@NativeType("GLuint") int i);

    public static native void glBindSampler(@NativeType("GLuint") int i, @NativeType("GLuint") int i2);

    public static native void glSamplerParameteri(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3);

    public static native void glSamplerParameterf(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat") float f);

    public static native void nglSamplerParameteriv(int i, int i2, long j);

    public static native void nglSamplerParameterfv(int i, int i2, long j);

    public static native void nglSamplerParameterIiv(int i, int i2, long j);

    public static native void nglSamplerParameterIuiv(int i, int i2, long j);

    public static native void nglGetSamplerParameteriv(int i, int i2, long j);

    public static native void nglGetSamplerParameterfv(int i, int i2, long j);

    public static native void nglGetSamplerParameterIiv(int i, int i2, long j);

    public static native void nglGetSamplerParameterIuiv(int i, int i2, long j);

    public static native void glQueryCounter(@NativeType("GLuint") int i, @NativeType("GLenum") int i2);

    public static native void nglGetQueryObjecti64v(int i, int i2, long j);

    public static native void nglGetQueryObjectui64v(int i, int i2, long j);

    public static native void glVertexAttribDivisor(@NativeType("GLuint") int i, @NativeType("GLuint") int i2);

    public static native void glVertexAttribP1ui(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint") int i3);

    public static native void glVertexAttribP2ui(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint") int i3);

    public static native void glVertexAttribP3ui(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint") int i3);

    public static native void glVertexAttribP4ui(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint") int i3);

    public static native void nglVertexAttribP1uiv(int i, int i2, boolean z, long j);

    public static native void nglVertexAttribP2uiv(int i, int i2, boolean z, long j);

    public static native void nglVertexAttribP3uiv(int i, int i2, boolean z, long j);

    public static native void nglVertexAttribP4uiv(int i, int i2, boolean z, long j);

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL33C() {
        throw new UnsupportedOperationException();
    }

    public static void glBindFragDataLocationIndexed(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nglBindFragDataLocationIndexed(i, i2, i3, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glBindFragDataLocationIndexed(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            nglBindFragDataLocationIndexed(i, i2, i3, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("GLint")
    public static int glGetFragDataIndex(@NativeType("GLuint") int i, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nglGetFragDataIndex(i, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("GLint")
    public static int glGetFragDataIndex(@NativeType("GLuint") int i, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nglGetFragDataIndex(i, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGenSamplers(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglGenSamplers(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGenSamplers() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGenSamplers(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDeleteSamplers(@NativeType("GLuint const *") IntBuffer intBuffer) {
        nglDeleteSamplers(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glDeleteSamplers(@NativeType("GLuint const *") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDeleteSamplers(1, MemoryUtil.memAddress(stackGet.ints(i)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glSamplerParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglSamplerParameteriv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glSamplerParameterfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglSamplerParameterfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glSamplerParameterIiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglSamplerParameterIiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glSamplerParameterIuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglSamplerParameterIuiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetSamplerParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetSamplerParameteriv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetSamplerParameteri(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetSamplerParameteriv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetSamplerParameterfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetSamplerParameterfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetSamplerParameterf(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetSamplerParameterfv(i, i2, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetSamplerParameterIiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetSamplerParameterIiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetSamplerParameterIi(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetSamplerParameterIiv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetSamplerParameterIuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetSamplerParameterIuiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetSamplerParameterIui(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetSamplerParameterIuiv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetQueryObjecti64v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint64 *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglGetQueryObjecti64v(i, i2, MemoryUtil.memAddress(longBuffer));
    }

    public static void glGetQueryObjecti64v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint64 *") long j) {
        nglGetQueryObjecti64v(i, i2, j);
    }

    @NativeType("void")
    public static long glGetQueryObjecti64(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglGetQueryObjecti64v(i, i2, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetQueryObjectui64v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint64 *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglGetQueryObjectui64v(i, i2, MemoryUtil.memAddress(longBuffer));
    }

    public static void glGetQueryObjectui64v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint64 *") long j) {
        nglGetQueryObjectui64v(i, i2, j);
    }

    @NativeType("void")
    public static long glGetQueryObjectui64(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglGetQueryObjectui64v(i, i2, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glVertexAttribP1uiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglVertexAttribP1uiv(i, i2, z, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttribP2uiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglVertexAttribP2uiv(i, i2, z, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttribP3uiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglVertexAttribP3uiv(i, i2, z, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttribP4uiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglVertexAttribP4uiv(i, i2, z, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGenSamplers(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGenSamplers;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glDeleteSamplers(@NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glDeleteSamplers;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glSamplerParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glSamplerParameteriv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glSamplerParameterfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glSamplerParameterfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glSamplerParameterIiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glSamplerParameterIiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glSamplerParameterIuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glSamplerParameterIuiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetSamplerParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetSamplerParameteriv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetSamplerParameterfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetSamplerParameterfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glGetSamplerParameterIiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetSamplerParameterIiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetSamplerParameterIuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGetSamplerParameterIuiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetQueryObjecti64v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint64 *") long[] jArr) {
        long j = GL.getICD().glGetQueryObjecti64v;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, i2, jArr, j);
    }

    public static void glGetQueryObjectui64v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint64 *") long[] jArr) {
        long j = GL.getICD().glGetQueryObjectui64v;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, i2, jArr, j);
    }

    public static void glVertexAttribP1uiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttribP1uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, z, iArr, j);
    }

    public static void glVertexAttribP2uiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttribP2uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, z, iArr, j);
    }

    public static void glVertexAttribP3uiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttribP3uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, z, iArr, j);
    }

    public static void glVertexAttribP4uiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttribP4uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, z, iArr, j);
    }
}
