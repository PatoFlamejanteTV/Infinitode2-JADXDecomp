package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL33.class */
public class GL33 extends GL32 {
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

    public static native void glVertexP2ui(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void glVertexP3ui(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void glVertexP4ui(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void nglVertexP2uiv(int i, long j);

    public static native void nglVertexP3uiv(int i, long j);

    public static native void nglVertexP4uiv(int i, long j);

    public static native void glTexCoordP1ui(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void glTexCoordP2ui(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void glTexCoordP3ui(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void glTexCoordP4ui(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void nglTexCoordP1uiv(int i, long j);

    public static native void nglTexCoordP2uiv(int i, long j);

    public static native void nglTexCoordP3uiv(int i, long j);

    public static native void nglTexCoordP4uiv(int i, long j);

    public static native void glMultiTexCoordP1ui(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3);

    public static native void glMultiTexCoordP2ui(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3);

    public static native void glMultiTexCoordP3ui(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3);

    public static native void glMultiTexCoordP4ui(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3);

    public static native void nglMultiTexCoordP1uiv(int i, int i2, long j);

    public static native void nglMultiTexCoordP2uiv(int i, int i2, long j);

    public static native void nglMultiTexCoordP3uiv(int i, int i2, long j);

    public static native void nglMultiTexCoordP4uiv(int i, int i2, long j);

    public static native void glNormalP3ui(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void nglNormalP3uiv(int i, long j);

    public static native void glColorP3ui(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void glColorP4ui(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void nglColorP3uiv(int i, long j);

    public static native void nglColorP4uiv(int i, long j);

    public static native void glSecondaryColorP3ui(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void nglSecondaryColorP3uiv(int i, long j);

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL33() {
        throw new UnsupportedOperationException();
    }

    public static void nglBindFragDataLocationIndexed(int i, int i2, int i3, long j) {
        GL33C.nglBindFragDataLocationIndexed(i, i2, i3, j);
    }

    public static void glBindFragDataLocationIndexed(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        GL33C.glBindFragDataLocationIndexed(i, i2, i3, byteBuffer);
    }

    public static void glBindFragDataLocationIndexed(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLchar const *") CharSequence charSequence) {
        GL33C.glBindFragDataLocationIndexed(i, i2, i3, charSequence);
    }

    public static int nglGetFragDataIndex(int i, long j) {
        return GL33C.nglGetFragDataIndex(i, j);
    }

    @NativeType("GLint")
    public static int glGetFragDataIndex(@NativeType("GLuint") int i, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        return GL33C.glGetFragDataIndex(i, byteBuffer);
    }

    @NativeType("GLint")
    public static int glGetFragDataIndex(@NativeType("GLuint") int i, @NativeType("GLchar const *") CharSequence charSequence) {
        return GL33C.glGetFragDataIndex(i, charSequence);
    }

    public static void nglGenSamplers(int i, long j) {
        GL33C.nglGenSamplers(i, j);
    }

    public static void glGenSamplers(@NativeType("GLuint *") IntBuffer intBuffer) {
        GL33C.glGenSamplers(intBuffer);
    }

    @NativeType("void")
    public static int glGenSamplers() {
        return GL33C.glGenSamplers();
    }

    public static void nglDeleteSamplers(int i, long j) {
        GL33C.nglDeleteSamplers(i, j);
    }

    public static void glDeleteSamplers(@NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33C.glDeleteSamplers(intBuffer);
    }

    public static void glDeleteSamplers(@NativeType("GLuint const *") int i) {
        GL33C.glDeleteSamplers(i);
    }

    @NativeType("GLboolean")
    public static boolean glIsSampler(@NativeType("GLuint") int i) {
        return GL33C.glIsSampler(i);
    }

    public static void glBindSampler(@NativeType("GLuint") int i, @NativeType("GLuint") int i2) {
        GL33C.glBindSampler(i, i2);
    }

    public static void glSamplerParameteri(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3) {
        GL33C.glSamplerParameteri(i, i2, i3);
    }

    public static void glSamplerParameterf(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat") float f) {
        GL33C.glSamplerParameterf(i, i2, f);
    }

    public static void nglSamplerParameteriv(int i, int i2, long j) {
        GL33C.nglSamplerParameteriv(i, i2, j);
    }

    public static void glSamplerParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL33C.glSamplerParameteriv(i, i2, intBuffer);
    }

    public static void nglSamplerParameterfv(int i, int i2, long j) {
        GL33C.nglSamplerParameterfv(i, i2, j);
    }

    public static void glSamplerParameterfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL33C.glSamplerParameterfv(i, i2, floatBuffer);
    }

    public static void nglSamplerParameterIiv(int i, int i2, long j) {
        GL33C.nglSamplerParameterIiv(i, i2, j);
    }

    public static void glSamplerParameterIiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL33C.glSamplerParameterIiv(i, i2, intBuffer);
    }

    public static void nglSamplerParameterIuiv(int i, int i2, long j) {
        GL33C.nglSamplerParameterIuiv(i, i2, j);
    }

    public static void glSamplerParameterIuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33C.glSamplerParameterIuiv(i, i2, intBuffer);
    }

    public static void nglGetSamplerParameteriv(int i, int i2, long j) {
        GL33C.nglGetSamplerParameteriv(i, i2, j);
    }

    public static void glGetSamplerParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL33C.glGetSamplerParameteriv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetSamplerParameteri(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL33C.glGetSamplerParameteri(i, i2);
    }

    public static void nglGetSamplerParameterfv(int i, int i2, long j) {
        GL33C.nglGetSamplerParameterfv(i, i2, j);
    }

    public static void glGetSamplerParameterfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        GL33C.glGetSamplerParameterfv(i, i2, floatBuffer);
    }

    @NativeType("void")
    public static float glGetSamplerParameterf(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL33C.glGetSamplerParameterf(i, i2);
    }

    public static void nglGetSamplerParameterIiv(int i, int i2, long j) {
        GL33C.nglGetSamplerParameterIiv(i, i2, j);
    }

    public static void glGetSamplerParameterIiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL33C.glGetSamplerParameterIiv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetSamplerParameterIi(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL33C.glGetSamplerParameterIi(i, i2);
    }

    public static void nglGetSamplerParameterIuiv(int i, int i2, long j) {
        GL33C.nglGetSamplerParameterIuiv(i, i2, j);
    }

    public static void glGetSamplerParameterIuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") IntBuffer intBuffer) {
        GL33C.glGetSamplerParameterIuiv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetSamplerParameterIui(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL33C.glGetSamplerParameterIui(i, i2);
    }

    public static void glQueryCounter(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        GL33C.glQueryCounter(i, i2);
    }

    public static void nglGetQueryObjecti64v(int i, int i2, long j) {
        GL33C.nglGetQueryObjecti64v(i, i2, j);
    }

    public static void glGetQueryObjecti64v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint64 *") LongBuffer longBuffer) {
        GL33C.glGetQueryObjecti64v(i, i2, longBuffer);
    }

    public static void glGetQueryObjecti64v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint64 *") long j) {
        GL33C.glGetQueryObjecti64v(i, i2, j);
    }

    @NativeType("void")
    public static long glGetQueryObjecti64(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL33C.glGetQueryObjecti64(i, i2);
    }

    public static void nglGetQueryObjectui64v(int i, int i2, long j) {
        GL33C.nglGetQueryObjectui64v(i, i2, j);
    }

    public static void glGetQueryObjectui64v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint64 *") LongBuffer longBuffer) {
        GL33C.glGetQueryObjectui64v(i, i2, longBuffer);
    }

    public static void glGetQueryObjectui64v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint64 *") long j) {
        GL33C.glGetQueryObjectui64v(i, i2, j);
    }

    @NativeType("void")
    public static long glGetQueryObjectui64(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL33C.glGetQueryObjectui64(i, i2);
    }

    public static void glVertexAttribDivisor(@NativeType("GLuint") int i, @NativeType("GLuint") int i2) {
        GL33C.glVertexAttribDivisor(i, i2);
    }

    public static void glVertexP2uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglVertexP2uiv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexP3uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglVertexP3uiv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexP4uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglVertexP4uiv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTexCoordP1uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglTexCoordP1uiv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTexCoordP2uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglTexCoordP2uiv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTexCoordP3uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglTexCoordP3uiv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTexCoordP4uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglTexCoordP4uiv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glMultiTexCoordP1uiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglMultiTexCoordP1uiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glMultiTexCoordP2uiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglMultiTexCoordP2uiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glMultiTexCoordP3uiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglMultiTexCoordP3uiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glMultiTexCoordP4uiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglMultiTexCoordP4uiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glNormalP3uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglNormalP3uiv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glColorP3uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglColorP3uiv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glColorP4uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglColorP4uiv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glSecondaryColorP3uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglSecondaryColorP3uiv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttribP1ui(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint") int i3) {
        GL33C.glVertexAttribP1ui(i, i2, z, i3);
    }

    public static void glVertexAttribP2ui(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint") int i3) {
        GL33C.glVertexAttribP2ui(i, i2, z, i3);
    }

    public static void glVertexAttribP3ui(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint") int i3) {
        GL33C.glVertexAttribP3ui(i, i2, z, i3);
    }

    public static void glVertexAttribP4ui(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint") int i3) {
        GL33C.glVertexAttribP4ui(i, i2, z, i3);
    }

    public static void nglVertexAttribP1uiv(int i, int i2, boolean z, long j) {
        GL33C.nglVertexAttribP1uiv(i, i2, z, j);
    }

    public static void glVertexAttribP1uiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33C.glVertexAttribP1uiv(i, i2, z, intBuffer);
    }

    public static void nglVertexAttribP2uiv(int i, int i2, boolean z, long j) {
        GL33C.nglVertexAttribP2uiv(i, i2, z, j);
    }

    public static void glVertexAttribP2uiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33C.glVertexAttribP2uiv(i, i2, z, intBuffer);
    }

    public static void nglVertexAttribP3uiv(int i, int i2, boolean z, long j) {
        GL33C.nglVertexAttribP3uiv(i, i2, z, j);
    }

    public static void glVertexAttribP3uiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33C.glVertexAttribP3uiv(i, i2, z, intBuffer);
    }

    public static void nglVertexAttribP4uiv(int i, int i2, boolean z, long j) {
        GL33C.nglVertexAttribP4uiv(i, i2, z, j);
    }

    public static void glVertexAttribP4uiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33C.glVertexAttribP4uiv(i, i2, z, intBuffer);
    }

    public static void glGenSamplers(@NativeType("GLuint *") int[] iArr) {
        GL33C.glGenSamplers(iArr);
    }

    public static void glDeleteSamplers(@NativeType("GLuint const *") int[] iArr) {
        GL33C.glDeleteSamplers(iArr);
    }

    public static void glSamplerParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int[] iArr) {
        GL33C.glSamplerParameteriv(i, i2, iArr);
    }

    public static void glSamplerParameterfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") float[] fArr) {
        GL33C.glSamplerParameterfv(i, i2, fArr);
    }

    public static void glSamplerParameterIiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int[] iArr) {
        GL33C.glSamplerParameterIiv(i, i2, iArr);
    }

    public static void glSamplerParameterIuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") int[] iArr) {
        GL33C.glSamplerParameterIuiv(i, i2, iArr);
    }

    public static void glGetSamplerParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        GL33C.glGetSamplerParameteriv(i, i2, iArr);
    }

    public static void glGetSamplerParameterfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") float[] fArr) {
        GL33C.glGetSamplerParameterfv(i, i2, fArr);
    }

    public static void glGetSamplerParameterIiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        GL33C.glGetSamplerParameterIiv(i, i2, iArr);
    }

    public static void glGetSamplerParameterIuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") int[] iArr) {
        GL33C.glGetSamplerParameterIuiv(i, i2, iArr);
    }

    public static void glGetQueryObjecti64v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint64 *") long[] jArr) {
        GL33C.glGetQueryObjecti64v(i, i2, jArr);
    }

    public static void glGetQueryObjectui64v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint64 *") long[] jArr) {
        GL33C.glGetQueryObjectui64v(i, i2, jArr);
    }

    public static void glVertexP2uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glVertexP2uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexP3uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glVertexP3uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexP4uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glVertexP4uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glTexCoordP1uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glTexCoordP1uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glTexCoordP2uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glTexCoordP2uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glTexCoordP3uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glTexCoordP3uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glTexCoordP4uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glTexCoordP4uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glMultiTexCoordP1uiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glMultiTexCoordP1uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glMultiTexCoordP2uiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glMultiTexCoordP2uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glMultiTexCoordP3uiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glMultiTexCoordP3uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glMultiTexCoordP4uiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glMultiTexCoordP4uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glNormalP3uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glNormalP3uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glColorP3uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glColorP3uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glColorP4uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glColorP4uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glSecondaryColorP3uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glSecondaryColorP3uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexAttribP1uiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint const *") int[] iArr) {
        GL33C.glVertexAttribP1uiv(i, i2, z, iArr);
    }

    public static void glVertexAttribP2uiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint const *") int[] iArr) {
        GL33C.glVertexAttribP2uiv(i, i2, z, iArr);
    }

    public static void glVertexAttribP3uiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint const *") int[] iArr) {
        GL33C.glVertexAttribP3uiv(i, i2, z, iArr);
    }

    public static void glVertexAttribP4uiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint const *") int[] iArr) {
        GL33C.glVertexAttribP4uiv(i, i2, z, iArr);
    }
}
