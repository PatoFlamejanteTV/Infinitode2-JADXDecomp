package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL45C.class */
public class GL45C extends GL44C {
    public static final int GL_NEGATIVE_ONE_TO_ONE = 37726;
    public static final int GL_ZERO_TO_ONE = 37727;
    public static final int GL_CLIP_ORIGIN = 37724;
    public static final int GL_CLIP_DEPTH_MODE = 37725;
    public static final int GL_QUERY_WAIT_INVERTED = 36375;
    public static final int GL_QUERY_NO_WAIT_INVERTED = 36376;
    public static final int GL_QUERY_BY_REGION_WAIT_INVERTED = 36377;
    public static final int GL_QUERY_BY_REGION_NO_WAIT_INVERTED = 36378;
    public static final int GL_MAX_CULL_DISTANCES = 33529;
    public static final int GL_MAX_COMBINED_CLIP_AND_CULL_DISTANCES = 33530;
    public static final int GL_TEXTURE_TARGET = 4102;
    public static final int GL_QUERY_TARGET = 33514;
    public static final int GL_CONTEXT_RELEASE_BEHAVIOR = 33531;
    public static final int GL_CONTEXT_RELEASE_BEHAVIOR_FLUSH = 33532;
    public static final int GL_GUILTY_CONTEXT_RESET = 33363;
    public static final int GL_INNOCENT_CONTEXT_RESET = 33364;
    public static final int GL_UNKNOWN_CONTEXT_RESET = 33365;
    public static final int GL_RESET_NOTIFICATION_STRATEGY = 33366;
    public static final int GL_LOSE_CONTEXT_ON_RESET = 33362;
    public static final int GL_NO_RESET_NOTIFICATION = 33377;
    public static final int GL_CONTEXT_FLAG_ROBUST_ACCESS_BIT = 4;
    public static final int GL_CONTEXT_LOST = 1287;

    public static native void glClipControl(@NativeType("GLenum") int i, @NativeType("GLenum") int i2);

    public static native void nglCreateTransformFeedbacks(int i, long j);

    public static native void glTransformFeedbackBufferBase(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3);

    public static native void glTransformFeedbackBufferRange(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2);

    public static native void nglGetTransformFeedbackiv(int i, int i2, long j);

    public static native void nglGetTransformFeedbacki_v(int i, int i2, int i3, long j);

    public static native void nglGetTransformFeedbacki64_v(int i, int i2, int i3, long j);

    public static native void nglCreateBuffers(int i, long j);

    public static native void nglNamedBufferStorage(int i, long j, long j2, int i2);

    public static native void nglNamedBufferData(int i, long j, long j2, int i2);

    public static native void nglNamedBufferSubData(int i, long j, long j2, long j3);

    public static native void glCopyNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLintptr") long j, @NativeType("GLintptr") long j2, @NativeType("GLsizeiptr") long j3);

    public static native void nglClearNamedBufferData(int i, int i2, int i3, int i4, long j);

    public static native void nglClearNamedBufferSubData(int i, int i2, long j, long j2, int i3, int i4, long j3);

    public static native long nglMapNamedBuffer(int i, int i2);

    public static native long nglMapNamedBufferRange(int i, long j, long j2, int i2);

    @NativeType("GLboolean")
    public static native boolean glUnmapNamedBuffer(@NativeType("GLuint") int i);

    public static native void glFlushMappedNamedBufferRange(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2);

    public static native void nglGetNamedBufferParameteriv(int i, int i2, long j);

    public static native void nglGetNamedBufferParameteri64v(int i, int i2, long j);

    public static native void nglGetNamedBufferPointerv(int i, int i2, long j);

    public static native void nglGetNamedBufferSubData(int i, long j, long j2, long j3);

    public static native void nglCreateFramebuffers(int i, long j);

    public static native void glNamedFramebufferRenderbuffer(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4);

    public static native void glNamedFramebufferParameteri(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3);

    public static native void glNamedFramebufferTexture(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint") int i4);

    public static native void glNamedFramebufferTextureLayer(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5);

    public static native void glNamedFramebufferDrawBuffer(@NativeType("GLuint") int i, @NativeType("GLenum") int i2);

    public static native void nglNamedFramebufferDrawBuffers(int i, int i2, long j);

    public static native void glNamedFramebufferReadBuffer(@NativeType("GLuint") int i, @NativeType("GLenum") int i2);

    public static native void nglInvalidateNamedFramebufferData(int i, int i2, long j);

    public static native void nglInvalidateNamedFramebufferSubData(int i, int i2, long j, int i3, int i4, int i5, int i6);

    public static native void nglClearNamedFramebufferiv(int i, int i2, int i3, long j);

    public static native void nglClearNamedFramebufferuiv(int i, int i2, int i3, long j);

    public static native void nglClearNamedFramebufferfv(int i, int i2, int i3, long j);

    public static native void glClearNamedFramebufferfi(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLfloat") float f, @NativeType("GLint") int i4);

    public static native void glBlitNamedFramebuffer(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLint") int i7, @NativeType("GLint") int i8, @NativeType("GLint") int i9, @NativeType("GLint") int i10, @NativeType("GLbitfield") int i11, @NativeType("GLenum") int i12);

    @NativeType("GLenum")
    public static native int glCheckNamedFramebufferStatus(@NativeType("GLuint") int i, @NativeType("GLenum") int i2);

    public static native void nglGetNamedFramebufferParameteriv(int i, int i2, long j);

    public static native void nglGetNamedFramebufferAttachmentParameteriv(int i, int i2, int i3, long j);

    public static native void nglCreateRenderbuffers(int i, long j);

    public static native void glNamedRenderbufferStorage(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4);

    public static native void glNamedRenderbufferStorageMultisample(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5);

    public static native void nglGetNamedRenderbufferParameteriv(int i, int i2, long j);

    public static native void nglCreateTextures(int i, int i2, long j);

    public static native void glTextureBuffer(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3);

    public static native void glTextureBufferRange(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2);

    public static native void glTextureStorage1D(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4);

    public static native void glTextureStorage2D(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5);

    public static native void glTextureStorage3D(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6);

    public static native void glTextureStorage2DMultisample(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLboolean") boolean z);

    public static native void glTextureStorage3DMultisample(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLboolean") boolean z);

    public static native void nglTextureSubImage1D(int i, int i2, int i3, int i4, int i5, int i6, long j);

    public static native void nglTextureSubImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j);

    public static native void nglTextureSubImage3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, long j);

    public static native void nglCompressedTextureSubImage1D(int i, int i2, int i3, int i4, int i5, int i6, long j);

    public static native void nglCompressedTextureSubImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j);

    public static native void nglCompressedTextureSubImage3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, long j);

    public static native void glCopyTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6);

    public static native void glCopyTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8);

    public static native void glCopyTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLint") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9);

    public static native void glTextureParameterf(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat") float f);

    public static native void nglTextureParameterfv(int i, int i2, long j);

    public static native void glTextureParameteri(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3);

    public static native void nglTextureParameterIiv(int i, int i2, long j);

    public static native void nglTextureParameterIuiv(int i, int i2, long j);

    public static native void nglTextureParameteriv(int i, int i2, long j);

    public static native void glGenerateTextureMipmap(@NativeType("GLuint") int i);

    public static native void glBindTextureUnit(@NativeType("GLuint") int i, @NativeType("GLuint") int i2);

    public static native void nglGetTextureImage(int i, int i2, int i3, int i4, int i5, long j);

    public static native void nglGetCompressedTextureImage(int i, int i2, int i3, long j);

    public static native void nglGetTextureLevelParameterfv(int i, int i2, int i3, long j);

    public static native void nglGetTextureLevelParameteriv(int i, int i2, int i3, long j);

    public static native void nglGetTextureParameterfv(int i, int i2, long j);

    public static native void nglGetTextureParameterIiv(int i, int i2, long j);

    public static native void nglGetTextureParameterIuiv(int i, int i2, long j);

    public static native void nglGetTextureParameteriv(int i, int i2, long j);

    public static native void nglCreateVertexArrays(int i, long j);

    public static native void glDisableVertexArrayAttrib(@NativeType("GLuint") int i, @NativeType("GLuint") int i2);

    public static native void glEnableVertexArrayAttrib(@NativeType("GLuint") int i, @NativeType("GLuint") int i2);

    public static native void glVertexArrayElementBuffer(@NativeType("GLuint") int i, @NativeType("GLuint") int i2);

    public static native void glVertexArrayVertexBuffer(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i4);

    public static native void nglVertexArrayVertexBuffers(int i, int i2, int i3, long j, long j2, long j3);

    public static native void glVertexArrayAttribFormat(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLboolean") boolean z, @NativeType("GLuint") int i5);

    public static native void glVertexArrayAttribIFormat(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLuint") int i5);

    public static native void glVertexArrayAttribLFormat(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLuint") int i5);

    public static native void glVertexArrayAttribBinding(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3);

    public static native void glVertexArrayBindingDivisor(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3);

    public static native void nglGetVertexArrayiv(int i, int i2, long j);

    public static native void nglGetVertexArrayIndexediv(int i, int i2, int i3, long j);

    public static native void nglGetVertexArrayIndexed64iv(int i, int i2, int i3, long j);

    public static native void nglCreateSamplers(int i, long j);

    public static native void nglCreateProgramPipelines(int i, long j);

    public static native void nglCreateQueries(int i, int i2, long j);

    public static native void glGetQueryBufferObjectiv(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLintptr") long j);

    public static native void glGetQueryBufferObjectuiv(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLintptr") long j);

    public static native void glGetQueryBufferObjecti64v(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLintptr") long j);

    public static native void glGetQueryBufferObjectui64v(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLintptr") long j);

    public static native void glMemoryBarrierByRegion(@NativeType("GLbitfield") int i);

    public static native void nglGetTextureSubImage(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, long j);

    public static native void nglGetCompressedTextureSubImage(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, long j);

    public static native void glTextureBarrier();

    @NativeType("GLenum")
    public static native int glGetGraphicsResetStatus();

    public static native void nglGetnTexImage(int i, int i2, int i3, int i4, int i5, long j);

    public static native void nglReadnPixels(int i, int i2, int i3, int i4, int i5, int i6, int i7, long j);

    public static native void nglGetnCompressedTexImage(int i, int i2, int i3, long j);

    public static native void nglGetnUniformfv(int i, int i2, int i3, long j);

    public static native void nglGetnUniformdv(int i, int i2, int i3, long j);

    public static native void nglGetnUniformiv(int i, int i2, int i3, long j);

    public static native void nglGetnUniformuiv(int i, int i2, int i3, long j);

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL45C() {
        throw new UnsupportedOperationException();
    }

    public static void glCreateTransformFeedbacks(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglCreateTransformFeedbacks(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glCreateTransformFeedbacks() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglCreateTransformFeedbacks(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTransformFeedbackiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetTransformFeedbackiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetTransformFeedbacki(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetTransformFeedbackiv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTransformFeedbacki_v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetTransformFeedbacki_v(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetTransformFeedbacki(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetTransformFeedbacki_v(i, i2, i3, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTransformFeedbacki64_v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint64 *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglGetTransformFeedbacki64_v(i, i2, i3, MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("void")
    public static long glGetTransformFeedbacki64(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglGetTransformFeedbacki64_v(i, i2, i3, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glCreateBuffers(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglCreateBuffers(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glCreateBuffers() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglCreateBuffers(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glNamedBufferStorage(@NativeType("GLuint") int i, @NativeType("GLsizeiptr") long j, @NativeType("GLbitfield") int i2) {
        nglNamedBufferStorage(i, j, 0L, i2);
    }

    public static void glNamedBufferStorage(@NativeType("GLuint") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLbitfield") int i2) {
        nglNamedBufferStorage(i, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), i2);
    }

    public static void glNamedBufferStorage(@NativeType("GLuint") int i, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLbitfield") int i2) {
        nglNamedBufferStorage(i, Integer.toUnsignedLong(shortBuffer.remaining()) << 1, MemoryUtil.memAddress(shortBuffer), i2);
    }

    public static void glNamedBufferStorage(@NativeType("GLuint") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLbitfield") int i2) {
        nglNamedBufferStorage(i, Integer.toUnsignedLong(intBuffer.remaining()) << 2, MemoryUtil.memAddress(intBuffer), i2);
    }

    public static void glNamedBufferStorage(@NativeType("GLuint") int i, @NativeType("void const *") FloatBuffer floatBuffer, @NativeType("GLbitfield") int i2) {
        nglNamedBufferStorage(i, Integer.toUnsignedLong(floatBuffer.remaining()) << 2, MemoryUtil.memAddress(floatBuffer), i2);
    }

    public static void glNamedBufferStorage(@NativeType("GLuint") int i, @NativeType("void const *") DoubleBuffer doubleBuffer, @NativeType("GLbitfield") int i2) {
        nglNamedBufferStorage(i, Integer.toUnsignedLong(doubleBuffer.remaining()) << 3, MemoryUtil.memAddress(doubleBuffer), i2);
    }

    public static void glNamedBufferData(@NativeType("GLuint") int i, @NativeType("GLsizeiptr") long j, @NativeType("GLenum") int i2) {
        nglNamedBufferData(i, j, 0L, i2);
    }

    public static void glNamedBufferData(@NativeType("GLuint") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLenum") int i2) {
        nglNamedBufferData(i, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), i2);
    }

    public static void glNamedBufferData(@NativeType("GLuint") int i, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLenum") int i2) {
        nglNamedBufferData(i, Integer.toUnsignedLong(shortBuffer.remaining()) << 1, MemoryUtil.memAddress(shortBuffer), i2);
    }

    public static void glNamedBufferData(@NativeType("GLuint") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLenum") int i2) {
        nglNamedBufferData(i, Integer.toUnsignedLong(intBuffer.remaining()) << 2, MemoryUtil.memAddress(intBuffer), i2);
    }

    public static void glNamedBufferData(@NativeType("GLuint") int i, @NativeType("void const *") LongBuffer longBuffer, @NativeType("GLenum") int i2) {
        nglNamedBufferData(i, Integer.toUnsignedLong(longBuffer.remaining()) << 3, MemoryUtil.memAddress(longBuffer), i2);
    }

    public static void glNamedBufferData(@NativeType("GLuint") int i, @NativeType("void const *") FloatBuffer floatBuffer, @NativeType("GLenum") int i2) {
        nglNamedBufferData(i, Integer.toUnsignedLong(floatBuffer.remaining()) << 2, MemoryUtil.memAddress(floatBuffer), i2);
    }

    public static void glNamedBufferData(@NativeType("GLuint") int i, @NativeType("void const *") DoubleBuffer doubleBuffer, @NativeType("GLenum") int i2) {
        nglNamedBufferData(i, Integer.toUnsignedLong(doubleBuffer.remaining()) << 3, MemoryUtil.memAddress(doubleBuffer), i2);
    }

    public static void glNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglNamedBufferSubData(i, j, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglNamedBufferSubData(i, j, Integer.toUnsignedLong(shortBuffer.remaining()) << 1, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") IntBuffer intBuffer) {
        nglNamedBufferSubData(i, j, Integer.toUnsignedLong(intBuffer.remaining()) << 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") LongBuffer longBuffer) {
        nglNamedBufferSubData(i, j, Integer.toUnsignedLong(longBuffer.remaining()) << 3, MemoryUtil.memAddress(longBuffer));
    }

    public static void glNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglNamedBufferSubData(i, j, Integer.toUnsignedLong(floatBuffer.remaining()) << 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglNamedBufferSubData(i, j, Integer.toUnsignedLong(doubleBuffer.remaining()) << 3, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glClearNamedBufferData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglClearNamedBufferData(i, i2, i3, i4, MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glClearNamedBufferData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglClearNamedBufferData(i, i2, i3, i4, MemoryUtil.memAddressSafe(shortBuffer));
    }

    public static void glClearNamedBufferData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") IntBuffer intBuffer) {
        nglClearNamedBufferData(i, i2, i3, i4, MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glClearNamedBufferData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglClearNamedBufferData(i, i2, i3, i4, MemoryUtil.memAddressSafe(floatBuffer));
    }

    public static void glClearNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglClearNamedBufferSubData(i, i2, j, j2, i3, i4, MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glClearNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglClearNamedBufferSubData(i, i2, j, j2, i3, i4, MemoryUtil.memAddressSafe(shortBuffer));
    }

    public static void glClearNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") IntBuffer intBuffer) {
        nglClearNamedBufferSubData(i, i2, j, j2, i3, i4, MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glClearNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglClearNamedBufferSubData(i, i2, j, j2, i3, i4, MemoryUtil.memAddressSafe(floatBuffer));
    }

    @NativeType("void *")
    public static ByteBuffer glMapNamedBuffer(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return MemoryUtil.memByteBufferSafe(nglMapNamedBuffer(i, i2), glGetNamedBufferParameteri(i, 34660));
    }

    @NativeType("void *")
    public static ByteBuffer glMapNamedBuffer(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, ByteBuffer byteBuffer) {
        return APIUtil.apiGetMappedBuffer(byteBuffer, nglMapNamedBuffer(i, i2), glGetNamedBufferParameteri(i, 34660));
    }

    @NativeType("void *")
    public static ByteBuffer glMapNamedBuffer(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, long j, ByteBuffer byteBuffer) {
        return APIUtil.apiGetMappedBuffer(byteBuffer, nglMapNamedBuffer(i, i2), (int) j);
    }

    @NativeType("void *")
    public static ByteBuffer glMapNamedBufferRange(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLbitfield") int i2) {
        nglMapNamedBufferRange(i, j, j2, i2);
        return MemoryUtil.memByteBufferSafe(j2, (int) j2);
    }

    @NativeType("void *")
    public static ByteBuffer glMapNamedBufferRange(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLbitfield") int i2, ByteBuffer byteBuffer) {
        return APIUtil.apiGetMappedBuffer(byteBuffer, nglMapNamedBufferRange(i, j, j2, i2), (int) j2);
    }

    public static void glGetNamedBufferParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetNamedBufferParameteriv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetNamedBufferParameteri(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetNamedBufferParameteriv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetNamedBufferParameteri64v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint64 *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglGetNamedBufferParameteri64v(i, i2, MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("void")
    public static long glGetNamedBufferParameteri64(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglGetNamedBufferParameteri64v(i, i2, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetNamedBufferPointerv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("void **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        nglGetNamedBufferPointerv(i, i2, MemoryUtil.memAddress(pointerBuffer));
    }

    @NativeType("void")
    public static long glGetNamedBufferPointer(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            PointerBuffer callocPointer = stackGet.callocPointer(1);
            nglGetNamedBufferPointerv(i, i2, MemoryUtil.memAddress(callocPointer));
            return callocPointer.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") ByteBuffer byteBuffer) {
        nglGetNamedBufferSubData(i, j, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") ShortBuffer shortBuffer) {
        nglGetNamedBufferSubData(i, j, Integer.toUnsignedLong(shortBuffer.remaining()) << 1, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glGetNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") IntBuffer intBuffer) {
        nglGetNamedBufferSubData(i, j, Integer.toUnsignedLong(intBuffer.remaining()) << 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") LongBuffer longBuffer) {
        nglGetNamedBufferSubData(i, j, Integer.toUnsignedLong(longBuffer.remaining()) << 3, MemoryUtil.memAddress(longBuffer));
    }

    public static void glGetNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") FloatBuffer floatBuffer) {
        nglGetNamedBufferSubData(i, j, Integer.toUnsignedLong(floatBuffer.remaining()) << 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") DoubleBuffer doubleBuffer) {
        nglGetNamedBufferSubData(i, j, Integer.toUnsignedLong(doubleBuffer.remaining()) << 3, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glCreateFramebuffers(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglCreateFramebuffers(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glCreateFramebuffers() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglCreateFramebuffers(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glNamedFramebufferDrawBuffers(@NativeType("GLuint") int i, @NativeType("GLenum const *") IntBuffer intBuffer) {
        nglNamedFramebufferDrawBuffers(i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glNamedFramebufferDrawBuffers(@NativeType("GLuint") int i, @NativeType("GLenum const *") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglNamedFramebufferDrawBuffers(i, 1, MemoryUtil.memAddress(stackGet.ints(i2)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glInvalidateNamedFramebufferData(@NativeType("GLuint") int i, @NativeType("GLenum const *") IntBuffer intBuffer) {
        nglInvalidateNamedFramebufferData(i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glInvalidateNamedFramebufferData(@NativeType("GLuint") int i, @NativeType("GLenum const *") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglInvalidateNamedFramebufferData(i, 1, MemoryUtil.memAddress(stackGet.ints(i2)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glInvalidateNamedFramebufferSubData(@NativeType("GLuint") int i, @NativeType("GLenum const *") IntBuffer intBuffer, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5) {
        nglInvalidateNamedFramebufferSubData(i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), i2, i3, i4, i5);
    }

    public static void glInvalidateNamedFramebufferSubData(@NativeType("GLuint") int i, @NativeType("GLenum const *") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglInvalidateNamedFramebufferSubData(i, 1, MemoryUtil.memAddress(stackGet.ints(i2)), i3, i4, i5, i6);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glClearNamedFramebufferiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglClearNamedFramebufferiv(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glClearNamedFramebufferuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglClearNamedFramebufferuiv(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glClearNamedFramebufferfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglClearNamedFramebufferfv(i, i2, i3, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetNamedFramebufferParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetNamedFramebufferParameteriv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetNamedFramebufferParameteri(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetNamedFramebufferParameteriv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetNamedFramebufferAttachmentParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetNamedFramebufferAttachmentParameteriv(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetNamedFramebufferAttachmentParameteri(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetNamedFramebufferAttachmentParameteriv(i, i2, i3, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glCreateRenderbuffers(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglCreateRenderbuffers(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glCreateRenderbuffers() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglCreateRenderbuffers(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetNamedRenderbufferParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetNamedRenderbufferParameteriv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetNamedRenderbufferParameteri(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetNamedRenderbufferParameteriv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glCreateTextures(@NativeType("GLenum") int i, @NativeType("GLuint *") IntBuffer intBuffer) {
        nglCreateTextures(i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glCreateTextures(@NativeType("GLenum") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglCreateTextures(i, 1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglTextureSubImage1D(i, i2, i3, i4, i5, i6, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") long j) {
        nglTextureSubImage1D(i, i2, i3, i4, i5, i6, j);
    }

    public static void glTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglTextureSubImage1D(i, i2, i3, i4, i5, i6, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") IntBuffer intBuffer) {
        nglTextureSubImage1D(i, i2, i3, i4, i5, i6, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglTextureSubImage1D(i, i2, i3, i4, i5, i6, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglTextureSubImage1D(i, i2, i3, i4, i5, i6, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglTextureSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") long j) {
        nglTextureSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, j);
    }

    public static void glTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglTextureSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") IntBuffer intBuffer) {
        nglTextureSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglTextureSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglTextureSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglTextureSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") long j) {
        nglTextureSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, j);
    }

    public static void glTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglTextureSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") IntBuffer intBuffer) {
        nglTextureSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglTextureSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglTextureSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glCompressedTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLsizei") int i6, @NativeType("void const *") long j) {
        nglCompressedTextureSubImage1D(i, i2, i3, i4, i5, i6, j);
    }

    public static void glCompressedTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedTextureSubImage1D(i, i2, i3, i4, i5, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glCompressedTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLsizei") int i8, @NativeType("void const *") long j) {
        nglCompressedTextureSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, j);
    }

    public static void glCompressedTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedTextureSubImage2D(i, i2, i3, i4, i5, i6, i7, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glCompressedTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLsizei") int i10, @NativeType("void const *") long j) {
        nglCompressedTextureSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, j);
    }

    public static void glCompressedTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedTextureSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glTextureParameterfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglTextureParameterfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glTextureParameterIiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglTextureParameterIiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTextureParameterIi(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglTextureParameterIiv(i, i2, MemoryUtil.memAddress(stackGet.ints(i3)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glTextureParameterIuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglTextureParameterIuiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTextureParameterIui(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglTextureParameterIuiv(i, i2, MemoryUtil.memAddress(stackGet.ints(i3)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glTextureParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglTextureParameteriv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetTextureImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("void *") long j) {
        nglGetTextureImage(i, i2, i3, i4, i5, j);
    }

    public static void glGetTextureImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") ByteBuffer byteBuffer) {
        nglGetTextureImage(i, i2, i3, i4, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetTextureImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") ShortBuffer shortBuffer) {
        nglGetTextureImage(i, i2, i3, i4, shortBuffer.remaining() << 1, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glGetTextureImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") IntBuffer intBuffer) {
        nglGetTextureImage(i, i2, i3, i4, intBuffer.remaining() << 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetTextureImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") FloatBuffer floatBuffer) {
        nglGetTextureImage(i, i2, i3, i4, floatBuffer.remaining() << 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetTextureImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") DoubleBuffer doubleBuffer) {
        nglGetTextureImage(i, i2, i3, i4, doubleBuffer.remaining() << 3, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glGetCompressedTextureImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("void *") long j) {
        nglGetCompressedTextureImage(i, i2, i3, j);
    }

    public static void glGetCompressedTextureImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("void *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS && Checks.DEBUG) {
            Checks.check((Buffer) byteBuffer, glGetTextureLevelParameteri(i, i2, 34464));
        }
        nglGetCompressedTextureImage(i, i2, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetTextureLevelParameterfv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetTextureLevelParameterfv(i, i2, i3, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetTextureLevelParameterf(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetTextureLevelParameterfv(i, i2, i3, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTextureLevelParameteriv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetTextureLevelParameteriv(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetTextureLevelParameteri(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetTextureLevelParameteriv(i, i2, i3, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTextureParameterfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetTextureParameterfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetTextureParameterf(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetTextureParameterfv(i, i2, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTextureParameterIiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetTextureParameterIiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetTextureParameterIi(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetTextureParameterIiv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTextureParameterIuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetTextureParameterIuiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetTextureParameterIui(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetTextureParameterIuiv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTextureParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetTextureParameteriv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetTextureParameteri(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetTextureParameteriv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glCreateVertexArrays(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglCreateVertexArrays(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glCreateVertexArrays() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglCreateVertexArrays(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glVertexArrayVertexBuffers(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLintptr const *") PointerBuffer pointerBuffer, @NativeType("GLsizei const *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkSafe((CustomBuffer<?>) pointerBuffer, Checks.remainingSafe(intBuffer));
            Checks.checkSafe((Buffer) intBuffer2, Checks.remainingSafe(intBuffer));
        }
        nglVertexArrayVertexBuffers(i, i2, Checks.remainingSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(pointerBuffer), MemoryUtil.memAddressSafe(intBuffer2));
    }

    public static void glGetVertexArrayiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetVertexArrayiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetVertexArrayi(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetVertexArrayiv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetVertexArrayIndexediv(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetVertexArrayIndexediv(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetVertexArrayIndexedi(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetVertexArrayIndexediv(i, i2, i3, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetVertexArrayIndexed64iv(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint64 *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglGetVertexArrayIndexed64iv(i, i2, i3, MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("void")
    public static long glGetVertexArrayIndexed64i(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglGetVertexArrayIndexed64iv(i, i2, i3, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glCreateSamplers(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglCreateSamplers(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glCreateSamplers() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglCreateSamplers(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glCreateProgramPipelines(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglCreateProgramPipelines(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glCreateProgramPipelines() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglCreateProgramPipelines(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glCreateQueries(@NativeType("GLenum") int i, @NativeType("GLuint *") IntBuffer intBuffer) {
        nglCreateQueries(i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glCreateQueries(@NativeType("GLenum") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglCreateQueries(i, 1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("GLsizei") int i11, @NativeType("void *") long j) {
        nglGetTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, j);
    }

    public static void glGetTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void *") ByteBuffer byteBuffer) {
        nglGetTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void *") ShortBuffer shortBuffer) {
        nglGetTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, shortBuffer.remaining() << 1, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glGetTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void *") IntBuffer intBuffer) {
        nglGetTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, intBuffer.remaining() << 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void *") FloatBuffer floatBuffer) {
        nglGetTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, floatBuffer.remaining() << 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void *") DoubleBuffer doubleBuffer) {
        nglGetTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, doubleBuffer.remaining() << 3, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glGetCompressedTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("void *") long j) {
        nglGetCompressedTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, j);
    }

    public static void glGetCompressedTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("void *") ByteBuffer byteBuffer) {
        nglGetCompressedTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetCompressedTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("void *") ShortBuffer shortBuffer) {
        nglGetCompressedTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, shortBuffer.remaining() << 1, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glGetCompressedTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("void *") IntBuffer intBuffer) {
        nglGetCompressedTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, intBuffer.remaining() << 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetCompressedTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("void *") FloatBuffer floatBuffer) {
        nglGetCompressedTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, floatBuffer.remaining() << 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetCompressedTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("void *") DoubleBuffer doubleBuffer) {
        nglGetCompressedTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, doubleBuffer.remaining() << 3, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glGetnTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("void *") long j) {
        nglGetnTexImage(i, i2, i3, i4, i5, j);
    }

    public static void glGetnTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") ByteBuffer byteBuffer) {
        nglGetnTexImage(i, i2, i3, i4, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetnTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") ShortBuffer shortBuffer) {
        nglGetnTexImage(i, i2, i3, i4, shortBuffer.remaining() << 1, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glGetnTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") IntBuffer intBuffer) {
        nglGetnTexImage(i, i2, i3, i4, intBuffer.remaining() << 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetnTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") FloatBuffer floatBuffer) {
        nglGetnTexImage(i, i2, i3, i4, floatBuffer.remaining() << 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetnTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") DoubleBuffer doubleBuffer) {
        nglGetnTexImage(i, i2, i3, i4, doubleBuffer.remaining() << 3, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glReadnPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("GLsizei") int i7, @NativeType("void *") long j) {
        nglReadnPixels(i, i2, i3, i4, i5, i6, i7, j);
    }

    public static void glReadnPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") ByteBuffer byteBuffer) {
        nglReadnPixels(i, i2, i3, i4, i5, i6, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glReadnPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") ShortBuffer shortBuffer) {
        nglReadnPixels(i, i2, i3, i4, i5, i6, shortBuffer.remaining() << 1, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glReadnPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") IntBuffer intBuffer) {
        nglReadnPixels(i, i2, i3, i4, i5, i6, intBuffer.remaining() << 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glReadnPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") FloatBuffer floatBuffer) {
        nglReadnPixels(i, i2, i3, i4, i5, i6, floatBuffer.remaining() << 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetnCompressedTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("void *") long j) {
        nglGetnCompressedTexImage(i, i2, i3, j);
    }

    public static void glGetnCompressedTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("void *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS && Checks.DEBUG) {
            Checks.check((Buffer) byteBuffer, GL11.glGetTexLevelParameteri(i, i2, 34464));
        }
        nglGetnCompressedTexImage(i, i2, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetnUniformfv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        nglGetnUniformfv(i, i2, floatBuffer.remaining(), MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetnUniformf(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetnUniformfv(i, i2, 1, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetnUniformdv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        nglGetnUniformdv(i, i2, doubleBuffer.remaining(), MemoryUtil.memAddress(doubleBuffer));
    }

    @NativeType("void")
    public static double glGetnUniformd(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            DoubleBuffer callocDouble = stackGet.callocDouble(1);
            nglGetnUniformdv(i, i2, 1, MemoryUtil.memAddress(callocDouble));
            return callocDouble.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetnUniformiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        nglGetnUniformiv(i, i2, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetnUniformi(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetnUniformiv(i, i2, 1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetnUniformuiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint *") IntBuffer intBuffer) {
        nglGetnUniformuiv(i, i2, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetnUniformui(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetnUniformuiv(i, i2, 1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glCreateTransformFeedbacks(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glCreateTransformFeedbacks;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glGetTransformFeedbackiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetTransformFeedbackiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetTransformFeedbacki_v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetTransformFeedbacki_v;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glGetTransformFeedbacki64_v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint64 *") long[] jArr) {
        long j = GL.getICD().glGetTransformFeedbacki64_v;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, i2, i3, jArr, j);
    }

    public static void glCreateBuffers(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glCreateBuffers;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glNamedBufferStorage(@NativeType("GLuint") int i, @NativeType("void const *") short[] sArr, @NativeType("GLbitfield") int i2) {
        long j = GL.getICD().glNamedBufferStorage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(sArr.length) << 1, sArr, i2, j);
    }

    public static void glNamedBufferStorage(@NativeType("GLuint") int i, @NativeType("void const *") int[] iArr, @NativeType("GLbitfield") int i2) {
        long j = GL.getICD().glNamedBufferStorage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(iArr.length) << 2, iArr, i2, j);
    }

    public static void glNamedBufferStorage(@NativeType("GLuint") int i, @NativeType("void const *") float[] fArr, @NativeType("GLbitfield") int i2) {
        long j = GL.getICD().glNamedBufferStorage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(fArr.length) << 2, fArr, i2, j);
    }

    public static void glNamedBufferStorage(@NativeType("GLuint") int i, @NativeType("void const *") double[] dArr, @NativeType("GLbitfield") int i2) {
        long j = GL.getICD().glNamedBufferStorage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(dArr.length) << 3, dArr, i2, j);
    }

    public static void glNamedBufferData(@NativeType("GLuint") int i, @NativeType("void const *") short[] sArr, @NativeType("GLenum") int i2) {
        long j = GL.getICD().glNamedBufferData;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(sArr.length) << 1, sArr, i2, j);
    }

    public static void glNamedBufferData(@NativeType("GLuint") int i, @NativeType("void const *") int[] iArr, @NativeType("GLenum") int i2) {
        long j = GL.getICD().glNamedBufferData;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(iArr.length) << 2, iArr, i2, j);
    }

    public static void glNamedBufferData(@NativeType("GLuint") int i, @NativeType("void const *") long[] jArr, @NativeType("GLenum") int i2) {
        long j = GL.getICD().glNamedBufferData;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(jArr.length) << 3, jArr, i2, j);
    }

    public static void glNamedBufferData(@NativeType("GLuint") int i, @NativeType("void const *") float[] fArr, @NativeType("GLenum") int i2) {
        long j = GL.getICD().glNamedBufferData;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(fArr.length) << 2, fArr, i2, j);
    }

    public static void glNamedBufferData(@NativeType("GLuint") int i, @NativeType("void const *") double[] dArr, @NativeType("GLenum") int i2) {
        long j = GL.getICD().glNamedBufferData;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(dArr.length) << 3, dArr, i2, j);
    }

    public static void glNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") short[] sArr) {
        long j2 = GL.getICD().glNamedBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(sArr.length) << 1, sArr, j2);
    }

    public static void glNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") int[] iArr) {
        long j2 = GL.getICD().glNamedBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(iArr.length) << 2, iArr, j2);
    }

    public static void glNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") long[] jArr) {
        long j2 = GL.getICD().glNamedBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(jArr.length) << 3, jArr, j2);
    }

    public static void glNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") float[] fArr) {
        long j2 = GL.getICD().glNamedBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(fArr.length) << 2, fArr, j2);
    }

    public static void glNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") double[] dArr) {
        long j2 = GL.getICD().glNamedBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(dArr.length) << 3, dArr, j2);
    }

    public static void glClearNamedBufferData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glClearNamedBufferData;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, sArr, j);
    }

    public static void glClearNamedBufferData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glClearNamedBufferData;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, iArr, j);
    }

    public static void glClearNamedBufferData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glClearNamedBufferData;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, fArr, j);
    }

    public static void glClearNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") short[] sArr) {
        long j3 = GL.getICD().glClearNamedBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j3);
        }
        JNI.callPPPV(i, i2, j, j2, i3, i4, sArr, j3);
    }

    public static void glClearNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") int[] iArr) {
        long j3 = GL.getICD().glClearNamedBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j3);
        }
        JNI.callPPPV(i, i2, j, j2, i3, i4, iArr, j3);
    }

    public static void glClearNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") float[] fArr) {
        long j3 = GL.getICD().glClearNamedBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j3);
        }
        JNI.callPPPV(i, i2, j, j2, i3, i4, fArr, j3);
    }

    public static void glGetNamedBufferParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetNamedBufferParameteriv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetNamedBufferParameteri64v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint64 *") long[] jArr) {
        long j = GL.getICD().glGetNamedBufferParameteri64v;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, i2, jArr, j);
    }

    public static void glGetNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") short[] sArr) {
        long j2 = GL.getICD().glGetNamedBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(sArr.length) << 1, sArr, j2);
    }

    public static void glGetNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") int[] iArr) {
        long j2 = GL.getICD().glGetNamedBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(iArr.length) << 2, iArr, j2);
    }

    public static void glGetNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") long[] jArr) {
        long j2 = GL.getICD().glGetNamedBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(jArr.length) << 3, jArr, j2);
    }

    public static void glGetNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") float[] fArr) {
        long j2 = GL.getICD().glGetNamedBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(fArr.length) << 2, fArr, j2);
    }

    public static void glGetNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") double[] dArr) {
        long j2 = GL.getICD().glGetNamedBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(dArr.length) << 3, dArr, j2);
    }

    public static void glCreateFramebuffers(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glCreateFramebuffers;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glNamedFramebufferDrawBuffers(@NativeType("GLuint") int i, @NativeType("GLenum const *") int[] iArr) {
        long j = GL.getICD().glNamedFramebufferDrawBuffers;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length, iArr, j);
    }

    public static void glInvalidateNamedFramebufferData(@NativeType("GLuint") int i, @NativeType("GLenum const *") int[] iArr) {
        long j = GL.getICD().glInvalidateNamedFramebufferData;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length, iArr, j);
    }

    public static void glInvalidateNamedFramebufferSubData(@NativeType("GLuint") int i, @NativeType("GLenum const *") int[] iArr, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5) {
        long j = GL.getICD().glInvalidateNamedFramebufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length, iArr, i2, i3, i4, i5, j);
    }

    public static void glClearNamedFramebufferiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glClearNamedFramebufferiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glClearNamedFramebufferuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glClearNamedFramebufferuiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glClearNamedFramebufferfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glClearNamedFramebufferfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, i3, fArr, j);
    }

    public static void glGetNamedFramebufferParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetNamedFramebufferParameteriv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetNamedFramebufferAttachmentParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetNamedFramebufferAttachmentParameteriv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glCreateRenderbuffers(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glCreateRenderbuffers;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glGetNamedRenderbufferParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetNamedRenderbufferParameteriv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glCreateTextures(@NativeType("GLenum") int i, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glCreateTextures;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length, iArr, j);
    }

    public static void glTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glTextureSubImage1D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, sArr, j);
    }

    public static void glTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glTextureSubImage1D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, iArr, j);
    }

    public static void glTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glTextureSubImage1D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, fArr, j);
    }

    public static void glTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") double[] dArr) {
        long j = GL.getICD().glTextureSubImage1D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, dArr, j);
    }

    public static void glTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glTextureSubImage2D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, sArr, j);
    }

    public static void glTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glTextureSubImage2D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, iArr, j);
    }

    public static void glTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glTextureSubImage2D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, fArr, j);
    }

    public static void glTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") double[] dArr) {
        long j = GL.getICD().glTextureSubImage2D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, dArr, j);
    }

    public static void glTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glTextureSubImage3D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, sArr, j);
    }

    public static void glTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glTextureSubImage3D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, iArr, j);
    }

    public static void glTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glTextureSubImage3D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, fArr, j);
    }

    public static void glTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") double[] dArr) {
        long j = GL.getICD().glTextureSubImage3D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, dArr, j);
    }

    public static void glTextureParameterfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glTextureParameterfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glTextureParameterIiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glTextureParameterIiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glTextureParameterIuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glTextureParameterIuiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glTextureParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glTextureParameteriv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetTextureImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") short[] sArr) {
        long j = GL.getICD().glGetTextureImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, sArr.length << 1, sArr, j);
    }

    public static void glGetTextureImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") int[] iArr) {
        long j = GL.getICD().glGetTextureImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, iArr.length << 2, iArr, j);
    }

    public static void glGetTextureImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") float[] fArr) {
        long j = GL.getICD().glGetTextureImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, fArr.length << 2, fArr, j);
    }

    public static void glGetTextureImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") double[] dArr) {
        long j = GL.getICD().glGetTextureImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, dArr.length << 3, dArr, j);
    }

    public static void glGetTextureLevelParameterfv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetTextureLevelParameterfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, i3, fArr, j);
    }

    public static void glGetTextureLevelParameteriv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetTextureLevelParameteriv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glGetTextureParameterfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetTextureParameterfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glGetTextureParameterIiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetTextureParameterIiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetTextureParameterIuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGetTextureParameterIuiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetTextureParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetTextureParameteriv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glCreateVertexArrays(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glCreateVertexArrays;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glVertexArrayVertexBuffers(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint const *") int[] iArr, @NativeType("GLintptr const *") PointerBuffer pointerBuffer, @NativeType("GLsizei const *") int[] iArr2) {
        long j = GL.getICD().glVertexArrayVertexBuffers;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe((CustomBuffer<?>) pointerBuffer, Checks.lengthSafe(iArr));
            Checks.checkSafe(iArr2, Checks.lengthSafe(iArr));
        }
        JNI.callPPPV(i, i2, Checks.lengthSafe(iArr), iArr, MemoryUtil.memAddressSafe(pointerBuffer), iArr2, j);
    }

    public static void glGetVertexArrayiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetVertexArrayiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetVertexArrayIndexediv(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetVertexArrayIndexediv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glGetVertexArrayIndexed64iv(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint64 *") long[] jArr) {
        long j = GL.getICD().glGetVertexArrayIndexed64iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, i2, i3, jArr, j);
    }

    public static void glCreateSamplers(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glCreateSamplers;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glCreateProgramPipelines(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glCreateProgramPipelines;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glCreateQueries(@NativeType("GLenum") int i, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glCreateQueries;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length, iArr, j);
    }

    public static void glGetTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void *") short[] sArr) {
        long j = GL.getICD().glGetTextureSubImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, sArr.length << 1, sArr, j);
    }

    public static void glGetTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void *") int[] iArr) {
        long j = GL.getICD().glGetTextureSubImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, iArr.length << 2, iArr, j);
    }

    public static void glGetTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void *") float[] fArr) {
        long j = GL.getICD().glGetTextureSubImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, fArr.length << 2, fArr, j);
    }

    public static void glGetTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void *") double[] dArr) {
        long j = GL.getICD().glGetTextureSubImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, dArr.length << 3, dArr, j);
    }

    public static void glGetCompressedTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("void *") short[] sArr) {
        long j = GL.getICD().glGetCompressedTextureSubImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, sArr.length << 1, sArr, j);
    }

    public static void glGetCompressedTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("void *") int[] iArr) {
        long j = GL.getICD().glGetCompressedTextureSubImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, iArr.length << 2, iArr, j);
    }

    public static void glGetCompressedTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("void *") float[] fArr) {
        long j = GL.getICD().glGetCompressedTextureSubImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, fArr.length << 2, fArr, j);
    }

    public static void glGetCompressedTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("void *") double[] dArr) {
        long j = GL.getICD().glGetCompressedTextureSubImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, dArr.length << 3, dArr, j);
    }

    public static void glGetnTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") short[] sArr) {
        long j = GL.getICD().glGetnTexImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, sArr.length << 1, sArr, j);
    }

    public static void glGetnTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") int[] iArr) {
        long j = GL.getICD().glGetnTexImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, iArr.length << 2, iArr, j);
    }

    public static void glGetnTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") float[] fArr) {
        long j = GL.getICD().glGetnTexImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, fArr.length << 2, fArr, j);
    }

    public static void glGetnTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") double[] dArr) {
        long j = GL.getICD().glGetnTexImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, dArr.length << 3, dArr, j);
    }

    public static void glReadnPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") short[] sArr) {
        long j = GL.getICD().glReadnPixels;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, sArr.length << 1, sArr, j);
    }

    public static void glReadnPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") int[] iArr) {
        long j = GL.getICD().glReadnPixels;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, iArr.length << 2, iArr, j);
    }

    public static void glReadnPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") float[] fArr) {
        long j = GL.getICD().glReadnPixels;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, fArr.length << 2, fArr, j);
    }

    public static void glGetnUniformfv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetnUniformfv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length, fArr, j);
    }

    public static void glGetnUniformdv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble *") double[] dArr) {
        long j = GL.getICD().glGetnUniformdv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length, dArr, j);
    }

    public static void glGetnUniformiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetnUniformiv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, iArr.length, iArr, j);
    }

    public static void glGetnUniformuiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGetnUniformuiv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, iArr.length, iArr, j);
    }
}
