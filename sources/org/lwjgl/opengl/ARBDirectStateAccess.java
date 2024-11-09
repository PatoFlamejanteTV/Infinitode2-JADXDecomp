package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBDirectStateAccess.class */
public class ARBDirectStateAccess {
    public static final int GL_TEXTURE_TARGET = 4102;
    public static final int GL_QUERY_TARGET = 33514;

    static {
        GL.initialize();
    }

    protected ARBDirectStateAccess() {
        throw new UnsupportedOperationException();
    }

    public static void nglCreateTransformFeedbacks(int i, long j) {
        GL45C.nglCreateTransformFeedbacks(i, j);
    }

    public static void glCreateTransformFeedbacks(@NativeType("GLuint *") IntBuffer intBuffer) {
        GL45C.glCreateTransformFeedbacks(intBuffer);
    }

    @NativeType("void")
    public static int glCreateTransformFeedbacks() {
        return GL45C.glCreateTransformFeedbacks();
    }

    public static void glTransformFeedbackBufferBase(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3) {
        GL45C.glTransformFeedbackBufferBase(i, i2, i3);
    }

    public static void glTransformFeedbackBufferRange(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2) {
        GL45C.glTransformFeedbackBufferRange(i, i2, i3, j, j2);
    }

    public static void nglGetTransformFeedbackiv(int i, int i2, long j) {
        GL45C.nglGetTransformFeedbackiv(i, i2, j);
    }

    public static void glGetTransformFeedbackiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL45C.glGetTransformFeedbackiv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetTransformFeedbacki(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL45C.glGetTransformFeedbacki(i, i2);
    }

    public static void nglGetTransformFeedbacki_v(int i, int i2, int i3, long j) {
        GL45C.nglGetTransformFeedbacki_v(i, i2, i3, j);
    }

    public static void glGetTransformFeedbacki_v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        GL45C.glGetTransformFeedbacki_v(i, i2, i3, intBuffer);
    }

    @NativeType("void")
    public static int glGetTransformFeedbacki(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3) {
        return GL45C.glGetTransformFeedbacki(i, i2, i3);
    }

    public static void nglGetTransformFeedbacki64_v(int i, int i2, int i3, long j) {
        GL45C.nglGetTransformFeedbacki64_v(i, i2, i3, j);
    }

    public static void glGetTransformFeedbacki64_v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint64 *") LongBuffer longBuffer) {
        GL45C.glGetTransformFeedbacki64_v(i, i2, i3, longBuffer);
    }

    @NativeType("void")
    public static long glGetTransformFeedbacki64(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3) {
        return GL45C.glGetTransformFeedbacki64(i, i2, i3);
    }

    public static void nglCreateBuffers(int i, long j) {
        GL45C.nglCreateBuffers(i, j);
    }

    public static void glCreateBuffers(@NativeType("GLuint *") IntBuffer intBuffer) {
        GL45C.glCreateBuffers(intBuffer);
    }

    @NativeType("void")
    public static int glCreateBuffers() {
        return GL45C.glCreateBuffers();
    }

    public static void nglNamedBufferStorage(int i, long j, long j2, int i2) {
        GL45C.nglNamedBufferStorage(i, j, j2, i2);
    }

    public static void glNamedBufferStorage(@NativeType("GLuint") int i, @NativeType("GLsizeiptr") long j, @NativeType("GLbitfield") int i2) {
        GL45C.glNamedBufferStorage(i, j, i2);
    }

    public static void glNamedBufferStorage(@NativeType("GLuint") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLbitfield") int i2) {
        GL45C.glNamedBufferStorage(i, byteBuffer, i2);
    }

    public static void glNamedBufferStorage(@NativeType("GLuint") int i, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLbitfield") int i2) {
        GL45C.glNamedBufferStorage(i, shortBuffer, i2);
    }

    public static void glNamedBufferStorage(@NativeType("GLuint") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLbitfield") int i2) {
        GL45C.glNamedBufferStorage(i, intBuffer, i2);
    }

    public static void glNamedBufferStorage(@NativeType("GLuint") int i, @NativeType("void const *") FloatBuffer floatBuffer, @NativeType("GLbitfield") int i2) {
        GL45C.glNamedBufferStorage(i, floatBuffer, i2);
    }

    public static void glNamedBufferStorage(@NativeType("GLuint") int i, @NativeType("void const *") DoubleBuffer doubleBuffer, @NativeType("GLbitfield") int i2) {
        GL45C.glNamedBufferStorage(i, doubleBuffer, i2);
    }

    public static void nglNamedBufferData(int i, long j, long j2, int i2) {
        GL45C.nglNamedBufferData(i, j, j2, i2);
    }

    public static void glNamedBufferData(@NativeType("GLuint") int i, @NativeType("GLsizeiptr") long j, @NativeType("GLenum") int i2) {
        GL45C.glNamedBufferData(i, j, i2);
    }

    public static void glNamedBufferData(@NativeType("GLuint") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLenum") int i2) {
        GL45C.glNamedBufferData(i, byteBuffer, i2);
    }

    public static void glNamedBufferData(@NativeType("GLuint") int i, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLenum") int i2) {
        GL45C.glNamedBufferData(i, shortBuffer, i2);
    }

    public static void glNamedBufferData(@NativeType("GLuint") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLenum") int i2) {
        GL45C.glNamedBufferData(i, intBuffer, i2);
    }

    public static void glNamedBufferData(@NativeType("GLuint") int i, @NativeType("void const *") LongBuffer longBuffer, @NativeType("GLenum") int i2) {
        GL45C.glNamedBufferData(i, longBuffer, i2);
    }

    public static void glNamedBufferData(@NativeType("GLuint") int i, @NativeType("void const *") FloatBuffer floatBuffer, @NativeType("GLenum") int i2) {
        GL45C.glNamedBufferData(i, floatBuffer, i2);
    }

    public static void glNamedBufferData(@NativeType("GLuint") int i, @NativeType("void const *") DoubleBuffer doubleBuffer, @NativeType("GLenum") int i2) {
        GL45C.glNamedBufferData(i, doubleBuffer, i2);
    }

    public static void nglNamedBufferSubData(int i, long j, long j2, long j3) {
        GL45C.nglNamedBufferSubData(i, j, j2, j3);
    }

    public static void glNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL45C.glNamedBufferSubData(i, j, byteBuffer);
    }

    public static void glNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") ShortBuffer shortBuffer) {
        GL45C.glNamedBufferSubData(i, j, shortBuffer);
    }

    public static void glNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") IntBuffer intBuffer) {
        GL45C.glNamedBufferSubData(i, j, intBuffer);
    }

    public static void glNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") LongBuffer longBuffer) {
        GL45C.glNamedBufferSubData(i, j, longBuffer);
    }

    public static void glNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") FloatBuffer floatBuffer) {
        GL45C.glNamedBufferSubData(i, j, floatBuffer);
    }

    public static void glNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        GL45C.glNamedBufferSubData(i, j, doubleBuffer);
    }

    public static void glCopyNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLintptr") long j, @NativeType("GLintptr") long j2, @NativeType("GLsizeiptr") long j3) {
        GL45C.glCopyNamedBufferSubData(i, i2, j, j2, j3);
    }

    public static void nglClearNamedBufferData(int i, int i2, int i3, int i4, long j) {
        GL45C.nglClearNamedBufferData(i, i2, i3, i4, j);
    }

    public static void glClearNamedBufferData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL45C.glClearNamedBufferData(i, i2, i3, i4, byteBuffer);
    }

    public static void glClearNamedBufferData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ShortBuffer shortBuffer) {
        GL45C.glClearNamedBufferData(i, i2, i3, i4, shortBuffer);
    }

    public static void glClearNamedBufferData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") IntBuffer intBuffer) {
        GL45C.glClearNamedBufferData(i, i2, i3, i4, intBuffer);
    }

    public static void glClearNamedBufferData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") FloatBuffer floatBuffer) {
        GL45C.glClearNamedBufferData(i, i2, i3, i4, floatBuffer);
    }

    public static void nglClearNamedBufferSubData(int i, int i2, long j, long j2, int i3, int i4, long j3) {
        GL45C.nglClearNamedBufferSubData(i, i2, j, j2, i3, i4, j3);
    }

    public static void glClearNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL45C.glClearNamedBufferSubData(i, i2, j, j2, i3, i4, byteBuffer);
    }

    public static void glClearNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ShortBuffer shortBuffer) {
        GL45C.glClearNamedBufferSubData(i, i2, j, j2, i3, i4, shortBuffer);
    }

    public static void glClearNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") IntBuffer intBuffer) {
        GL45C.glClearNamedBufferSubData(i, i2, j, j2, i3, i4, intBuffer);
    }

    public static void glClearNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") FloatBuffer floatBuffer) {
        GL45C.glClearNamedBufferSubData(i, i2, j, j2, i3, i4, floatBuffer);
    }

    public static long nglMapNamedBuffer(int i, int i2) {
        return GL45C.nglMapNamedBuffer(i, i2);
    }

    @NativeType("void *")
    public static ByteBuffer glMapNamedBuffer(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL45C.glMapNamedBuffer(i, i2);
    }

    @NativeType("void *")
    public static ByteBuffer glMapNamedBuffer(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, ByteBuffer byteBuffer) {
        return GL45C.glMapNamedBuffer(i, i2, byteBuffer);
    }

    @NativeType("void *")
    public static ByteBuffer glMapNamedBuffer(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, long j, ByteBuffer byteBuffer) {
        return GL45C.glMapNamedBuffer(i, i2, j, byteBuffer);
    }

    public static long nglMapNamedBufferRange(int i, long j, long j2, int i2) {
        return GL45C.nglMapNamedBufferRange(i, j, j2, i2);
    }

    @NativeType("void *")
    public static ByteBuffer glMapNamedBufferRange(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLbitfield") int i2) {
        return GL45C.glMapNamedBufferRange(i, j, j2, i2);
    }

    @NativeType("void *")
    public static ByteBuffer glMapNamedBufferRange(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLbitfield") int i2, ByteBuffer byteBuffer) {
        return GL45C.glMapNamedBufferRange(i, j, j2, i2, byteBuffer);
    }

    @NativeType("GLboolean")
    public static boolean glUnmapNamedBuffer(@NativeType("GLuint") int i) {
        return GL45C.glUnmapNamedBuffer(i);
    }

    public static void glFlushMappedNamedBufferRange(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2) {
        GL45C.glFlushMappedNamedBufferRange(i, j, j2);
    }

    public static void nglGetNamedBufferParameteriv(int i, int i2, long j) {
        GL45C.nglGetNamedBufferParameteriv(i, i2, j);
    }

    public static void glGetNamedBufferParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL45C.glGetNamedBufferParameteriv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetNamedBufferParameteri(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL45C.glGetNamedBufferParameteri(i, i2);
    }

    public static void nglGetNamedBufferParameteri64v(int i, int i2, long j) {
        GL45C.nglGetNamedBufferParameteri64v(i, i2, j);
    }

    public static void glGetNamedBufferParameteri64v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint64 *") LongBuffer longBuffer) {
        GL45C.glGetNamedBufferParameteri64v(i, i2, longBuffer);
    }

    @NativeType("void")
    public static long glGetNamedBufferParameteri64(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL45C.glGetNamedBufferParameteri64(i, i2);
    }

    public static void nglGetNamedBufferPointerv(int i, int i2, long j) {
        GL45C.nglGetNamedBufferPointerv(i, i2, j);
    }

    public static void glGetNamedBufferPointerv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("void **") PointerBuffer pointerBuffer) {
        GL45C.glGetNamedBufferPointerv(i, i2, pointerBuffer);
    }

    @NativeType("void")
    public static long glGetNamedBufferPointer(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL45C.glGetNamedBufferPointer(i, i2);
    }

    public static void nglGetNamedBufferSubData(int i, long j, long j2, long j3) {
        GL45C.nglGetNamedBufferSubData(i, j, j2, j3);
    }

    public static void glGetNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") ByteBuffer byteBuffer) {
        GL45C.glGetNamedBufferSubData(i, j, byteBuffer);
    }

    public static void glGetNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") ShortBuffer shortBuffer) {
        GL45C.glGetNamedBufferSubData(i, j, shortBuffer);
    }

    public static void glGetNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") IntBuffer intBuffer) {
        GL45C.glGetNamedBufferSubData(i, j, intBuffer);
    }

    public static void glGetNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") LongBuffer longBuffer) {
        GL45C.glGetNamedBufferSubData(i, j, longBuffer);
    }

    public static void glGetNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") FloatBuffer floatBuffer) {
        GL45C.glGetNamedBufferSubData(i, j, floatBuffer);
    }

    public static void glGetNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") DoubleBuffer doubleBuffer) {
        GL45C.glGetNamedBufferSubData(i, j, doubleBuffer);
    }

    public static void nglCreateFramebuffers(int i, long j) {
        GL45C.nglCreateFramebuffers(i, j);
    }

    public static void glCreateFramebuffers(@NativeType("GLuint *") IntBuffer intBuffer) {
        GL45C.glCreateFramebuffers(intBuffer);
    }

    @NativeType("void")
    public static int glCreateFramebuffers() {
        return GL45C.glCreateFramebuffers();
    }

    public static void glNamedFramebufferRenderbuffer(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4) {
        GL45C.glNamedFramebufferRenderbuffer(i, i2, i3, i4);
    }

    public static void glNamedFramebufferParameteri(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3) {
        GL45C.glNamedFramebufferParameteri(i, i2, i3);
    }

    public static void glNamedFramebufferTexture(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint") int i4) {
        GL45C.glNamedFramebufferTexture(i, i2, i3, i4);
    }

    public static void glNamedFramebufferTextureLayer(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5) {
        GL45C.glNamedFramebufferTextureLayer(i, i2, i3, i4, i5);
    }

    public static void glNamedFramebufferDrawBuffer(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        GL45C.glNamedFramebufferDrawBuffer(i, i2);
    }

    public static void nglNamedFramebufferDrawBuffers(int i, int i2, long j) {
        GL45C.nglNamedFramebufferDrawBuffers(i, i2, j);
    }

    public static void glNamedFramebufferDrawBuffers(@NativeType("GLuint") int i, @NativeType("GLenum const *") IntBuffer intBuffer) {
        GL45C.glNamedFramebufferDrawBuffers(i, intBuffer);
    }

    public static void glNamedFramebufferDrawBuffers(@NativeType("GLuint") int i, @NativeType("GLenum const *") int i2) {
        GL45C.glNamedFramebufferDrawBuffers(i, i2);
    }

    public static void glNamedFramebufferReadBuffer(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        GL45C.glNamedFramebufferReadBuffer(i, i2);
    }

    public static void nglInvalidateNamedFramebufferData(int i, int i2, long j) {
        GL45C.nglInvalidateNamedFramebufferData(i, i2, j);
    }

    public static void glInvalidateNamedFramebufferData(@NativeType("GLuint") int i, @NativeType("GLenum const *") IntBuffer intBuffer) {
        GL45C.glInvalidateNamedFramebufferData(i, intBuffer);
    }

    public static void glInvalidateNamedFramebufferData(@NativeType("GLuint") int i, @NativeType("GLenum const *") int i2) {
        GL45C.glInvalidateNamedFramebufferData(i, i2);
    }

    public static void nglInvalidateNamedFramebufferSubData(int i, int i2, long j, int i3, int i4, int i5, int i6) {
        GL45C.nglInvalidateNamedFramebufferSubData(i, i2, j, i3, i4, i5, i6);
    }

    public static void glInvalidateNamedFramebufferSubData(@NativeType("GLuint") int i, @NativeType("GLenum const *") IntBuffer intBuffer, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5) {
        GL45C.glInvalidateNamedFramebufferSubData(i, intBuffer, i2, i3, i4, i5);
    }

    public static void glInvalidateNamedFramebufferSubData(@NativeType("GLuint") int i, @NativeType("GLenum const *") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6) {
        GL45C.glInvalidateNamedFramebufferSubData(i, i2, i3, i4, i5, i6);
    }

    public static void nglClearNamedFramebufferiv(int i, int i2, int i3, long j) {
        GL45C.nglClearNamedFramebufferiv(i, i2, i3, j);
    }

    public static void glClearNamedFramebufferiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL45C.glClearNamedFramebufferiv(i, i2, i3, intBuffer);
    }

    public static void nglClearNamedFramebufferuiv(int i, int i2, int i3, long j) {
        GL45C.nglClearNamedFramebufferuiv(i, i2, i3, j);
    }

    public static void glClearNamedFramebufferuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL45C.glClearNamedFramebufferuiv(i, i2, i3, intBuffer);
    }

    public static void nglClearNamedFramebufferfv(int i, int i2, int i3, long j) {
        GL45C.nglClearNamedFramebufferfv(i, i2, i3, j);
    }

    public static void glClearNamedFramebufferfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL45C.glClearNamedFramebufferfv(i, i2, i3, floatBuffer);
    }

    public static void glClearNamedFramebufferfi(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLfloat") float f, @NativeType("GLint") int i4) {
        GL45C.glClearNamedFramebufferfi(i, i2, i3, f, i4);
    }

    public static void glBlitNamedFramebuffer(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLint") int i7, @NativeType("GLint") int i8, @NativeType("GLint") int i9, @NativeType("GLint") int i10, @NativeType("GLbitfield") int i11, @NativeType("GLenum") int i12) {
        GL45C.glBlitNamedFramebuffer(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12);
    }

    @NativeType("GLenum")
    public static int glCheckNamedFramebufferStatus(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL45C.glCheckNamedFramebufferStatus(i, i2);
    }

    public static void nglGetNamedFramebufferParameteriv(int i, int i2, long j) {
        GL45C.nglGetNamedFramebufferParameteriv(i, i2, j);
    }

    public static void glGetNamedFramebufferParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL45C.glGetNamedFramebufferParameteriv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetNamedFramebufferParameteri(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL45C.glGetNamedFramebufferParameteri(i, i2);
    }

    public static void nglGetNamedFramebufferAttachmentParameteriv(int i, int i2, int i3, long j) {
        GL45C.nglGetNamedFramebufferAttachmentParameteriv(i, i2, i3, j);
    }

    public static void glGetNamedFramebufferAttachmentParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        GL45C.glGetNamedFramebufferAttachmentParameteriv(i, i2, i3, intBuffer);
    }

    @NativeType("void")
    public static int glGetNamedFramebufferAttachmentParameteri(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        return GL45C.glGetNamedFramebufferAttachmentParameteri(i, i2, i3);
    }

    public static void nglCreateRenderbuffers(int i, long j) {
        GL45C.nglCreateRenderbuffers(i, j);
    }

    public static void glCreateRenderbuffers(@NativeType("GLuint *") IntBuffer intBuffer) {
        GL45C.glCreateRenderbuffers(intBuffer);
    }

    @NativeType("void")
    public static int glCreateRenderbuffers() {
        return GL45C.glCreateRenderbuffers();
    }

    public static void glNamedRenderbufferStorage(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        GL45C.glNamedRenderbufferStorage(i, i2, i3, i4);
    }

    public static void glNamedRenderbufferStorageMultisample(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5) {
        GL45C.glNamedRenderbufferStorageMultisample(i, i2, i3, i4, i5);
    }

    public static void nglGetNamedRenderbufferParameteriv(int i, int i2, long j) {
        GL45C.nglGetNamedRenderbufferParameteriv(i, i2, j);
    }

    public static void glGetNamedRenderbufferParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL45C.glGetNamedRenderbufferParameteriv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetNamedRenderbufferParameteri(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL45C.glGetNamedRenderbufferParameteri(i, i2);
    }

    public static void nglCreateTextures(int i, int i2, long j) {
        GL45C.nglCreateTextures(i, i2, j);
    }

    public static void glCreateTextures(@NativeType("GLenum") int i, @NativeType("GLuint *") IntBuffer intBuffer) {
        GL45C.glCreateTextures(i, intBuffer);
    }

    @NativeType("void")
    public static int glCreateTextures(@NativeType("GLenum") int i) {
        return GL45C.glCreateTextures(i);
    }

    public static void glTextureBuffer(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3) {
        GL45C.glTextureBuffer(i, i2, i3);
    }

    public static void glTextureBufferRange(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2) {
        GL45C.glTextureBufferRange(i, i2, i3, j, j2);
    }

    public static void glTextureStorage1D(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4) {
        GL45C.glTextureStorage1D(i, i2, i3, i4);
    }

    public static void glTextureStorage2D(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5) {
        GL45C.glTextureStorage2D(i, i2, i3, i4, i5);
    }

    public static void glTextureStorage3D(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6) {
        GL45C.glTextureStorage3D(i, i2, i3, i4, i5, i6);
    }

    public static void glTextureStorage2DMultisample(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLboolean") boolean z) {
        GL45C.glTextureStorage2DMultisample(i, i2, i3, i4, i5, z);
    }

    public static void glTextureStorage3DMultisample(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLboolean") boolean z) {
        GL45C.glTextureStorage3DMultisample(i, i2, i3, i4, i5, i6, z);
    }

    public static void nglTextureSubImage1D(int i, int i2, int i3, int i4, int i5, int i6, long j) {
        GL45C.nglTextureSubImage1D(i, i2, i3, i4, i5, i6, j);
    }

    public static void glTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL45C.glTextureSubImage1D(i, i2, i3, i4, i5, i6, byteBuffer);
    }

    public static void glTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") long j) {
        GL45C.glTextureSubImage1D(i, i2, i3, i4, i5, i6, j);
    }

    public static void glTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") ShortBuffer shortBuffer) {
        GL45C.glTextureSubImage1D(i, i2, i3, i4, i5, i6, shortBuffer);
    }

    public static void glTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") IntBuffer intBuffer) {
        GL45C.glTextureSubImage1D(i, i2, i3, i4, i5, i6, intBuffer);
    }

    public static void glTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") FloatBuffer floatBuffer) {
        GL45C.glTextureSubImage1D(i, i2, i3, i4, i5, i6, floatBuffer);
    }

    public static void glTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        GL45C.glTextureSubImage1D(i, i2, i3, i4, i5, i6, doubleBuffer);
    }

    public static void nglTextureSubImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j) {
        GL45C.nglTextureSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, j);
    }

    public static void glTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL45C.glTextureSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, byteBuffer);
    }

    public static void glTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") long j) {
        GL45C.glTextureSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, j);
    }

    public static void glTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") ShortBuffer shortBuffer) {
        GL45C.glTextureSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, shortBuffer);
    }

    public static void glTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") IntBuffer intBuffer) {
        GL45C.glTextureSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, intBuffer);
    }

    public static void glTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") FloatBuffer floatBuffer) {
        GL45C.glTextureSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, floatBuffer);
    }

    public static void glTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        GL45C.glTextureSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, doubleBuffer);
    }

    public static void nglTextureSubImage3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, long j) {
        GL45C.nglTextureSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, j);
    }

    public static void glTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL45C.glTextureSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, byteBuffer);
    }

    public static void glTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") long j) {
        GL45C.glTextureSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, j);
    }

    public static void glTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") ShortBuffer shortBuffer) {
        GL45C.glTextureSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, shortBuffer);
    }

    public static void glTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") IntBuffer intBuffer) {
        GL45C.glTextureSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, intBuffer);
    }

    public static void glTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") FloatBuffer floatBuffer) {
        GL45C.glTextureSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, floatBuffer);
    }

    public static void glTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        GL45C.glTextureSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, doubleBuffer);
    }

    public static void nglCompressedTextureSubImage1D(int i, int i2, int i3, int i4, int i5, int i6, long j) {
        GL45C.nglCompressedTextureSubImage1D(i, i2, i3, i4, i5, i6, j);
    }

    public static void glCompressedTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLsizei") int i6, @NativeType("void const *") long j) {
        GL45C.glCompressedTextureSubImage1D(i, i2, i3, i4, i5, i6, j);
    }

    public static void glCompressedTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL45C.glCompressedTextureSubImage1D(i, i2, i3, i4, i5, byteBuffer);
    }

    public static void nglCompressedTextureSubImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j) {
        GL45C.nglCompressedTextureSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, j);
    }

    public static void glCompressedTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLsizei") int i8, @NativeType("void const *") long j) {
        GL45C.glCompressedTextureSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, j);
    }

    public static void glCompressedTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL45C.glCompressedTextureSubImage2D(i, i2, i3, i4, i5, i6, i7, byteBuffer);
    }

    public static void nglCompressedTextureSubImage3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, long j) {
        GL45C.nglCompressedTextureSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, j);
    }

    public static void glCompressedTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLsizei") int i10, @NativeType("void const *") long j) {
        GL45C.glCompressedTextureSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, j);
    }

    public static void glCompressedTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL45C.glCompressedTextureSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, byteBuffer);
    }

    public static void glCopyTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6) {
        GL45C.glCopyTextureSubImage1D(i, i2, i3, i4, i5, i6);
    }

    public static void glCopyTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8) {
        GL45C.glCopyTextureSubImage2D(i, i2, i3, i4, i5, i6, i7, i8);
    }

    public static void glCopyTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLint") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9) {
        GL45C.glCopyTextureSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9);
    }

    public static void glTextureParameterf(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat") float f) {
        GL45C.glTextureParameterf(i, i2, f);
    }

    public static void nglTextureParameterfv(int i, int i2, long j) {
        GL45C.nglTextureParameterfv(i, i2, j);
    }

    public static void glTextureParameterfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL45C.glTextureParameterfv(i, i2, floatBuffer);
    }

    public static void glTextureParameteri(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3) {
        GL45C.glTextureParameteri(i, i2, i3);
    }

    public static void nglTextureParameterIiv(int i, int i2, long j) {
        GL45C.nglTextureParameterIiv(i, i2, j);
    }

    public static void glTextureParameterIiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL45C.glTextureParameterIiv(i, i2, intBuffer);
    }

    public static void glTextureParameterIi(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int i3) {
        GL45C.glTextureParameterIi(i, i2, i3);
    }

    public static void nglTextureParameterIuiv(int i, int i2, long j) {
        GL45C.nglTextureParameterIuiv(i, i2, j);
    }

    public static void glTextureParameterIuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL45C.glTextureParameterIuiv(i, i2, intBuffer);
    }

    public static void glTextureParameterIui(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") int i3) {
        GL45C.glTextureParameterIui(i, i2, i3);
    }

    public static void nglTextureParameteriv(int i, int i2, long j) {
        GL45C.nglTextureParameteriv(i, i2, j);
    }

    public static void glTextureParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL45C.glTextureParameteriv(i, i2, intBuffer);
    }

    public static void glGenerateTextureMipmap(@NativeType("GLuint") int i) {
        GL45C.glGenerateTextureMipmap(i);
    }

    public static void glBindTextureUnit(@NativeType("GLuint") int i, @NativeType("GLuint") int i2) {
        GL45C.glBindTextureUnit(i, i2);
    }

    public static void nglGetTextureImage(int i, int i2, int i3, int i4, int i5, long j) {
        GL45C.nglGetTextureImage(i, i2, i3, i4, i5, j);
    }

    public static void glGetTextureImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("void *") long j) {
        GL45C.glGetTextureImage(i, i2, i3, i4, i5, j);
    }

    public static void glGetTextureImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") ByteBuffer byteBuffer) {
        GL45C.glGetTextureImage(i, i2, i3, i4, byteBuffer);
    }

    public static void glGetTextureImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") ShortBuffer shortBuffer) {
        GL45C.glGetTextureImage(i, i2, i3, i4, shortBuffer);
    }

    public static void glGetTextureImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") IntBuffer intBuffer) {
        GL45C.glGetTextureImage(i, i2, i3, i4, intBuffer);
    }

    public static void glGetTextureImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") FloatBuffer floatBuffer) {
        GL45C.glGetTextureImage(i, i2, i3, i4, floatBuffer);
    }

    public static void glGetTextureImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") DoubleBuffer doubleBuffer) {
        GL45C.glGetTextureImage(i, i2, i3, i4, doubleBuffer);
    }

    public static void nglGetCompressedTextureImage(int i, int i2, int i3, long j) {
        GL45C.nglGetCompressedTextureImage(i, i2, i3, j);
    }

    public static void glGetCompressedTextureImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("void *") long j) {
        GL45C.glGetCompressedTextureImage(i, i2, i3, j);
    }

    public static void glGetCompressedTextureImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("void *") ByteBuffer byteBuffer) {
        GL45C.glGetCompressedTextureImage(i, i2, byteBuffer);
    }

    public static void nglGetTextureLevelParameterfv(int i, int i2, int i3, long j) {
        GL45C.nglGetTextureLevelParameterfv(i, i2, i3, j);
    }

    public static void glGetTextureLevelParameterfv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        GL45C.glGetTextureLevelParameterfv(i, i2, i3, floatBuffer);
    }

    @NativeType("void")
    public static float glGetTextureLevelParameterf(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3) {
        return GL45C.glGetTextureLevelParameterf(i, i2, i3);
    }

    public static void nglGetTextureLevelParameteriv(int i, int i2, int i3, long j) {
        GL45C.nglGetTextureLevelParameteriv(i, i2, i3, j);
    }

    public static void glGetTextureLevelParameteriv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        GL45C.glGetTextureLevelParameteriv(i, i2, i3, intBuffer);
    }

    @NativeType("void")
    public static int glGetTextureLevelParameteri(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3) {
        return GL45C.glGetTextureLevelParameteri(i, i2, i3);
    }

    public static void nglGetTextureParameterfv(int i, int i2, long j) {
        GL45C.nglGetTextureParameterfv(i, i2, j);
    }

    public static void glGetTextureParameterfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        GL45C.glGetTextureParameterfv(i, i2, floatBuffer);
    }

    @NativeType("void")
    public static float glGetTextureParameterf(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL45C.glGetTextureParameterf(i, i2);
    }

    public static void nglGetTextureParameterIiv(int i, int i2, long j) {
        GL45C.nglGetTextureParameterIiv(i, i2, j);
    }

    public static void glGetTextureParameterIiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL45C.glGetTextureParameterIiv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetTextureParameterIi(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL45C.glGetTextureParameterIi(i, i2);
    }

    public static void nglGetTextureParameterIuiv(int i, int i2, long j) {
        GL45C.nglGetTextureParameterIuiv(i, i2, j);
    }

    public static void glGetTextureParameterIuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") IntBuffer intBuffer) {
        GL45C.glGetTextureParameterIuiv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetTextureParameterIui(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL45C.glGetTextureParameterIui(i, i2);
    }

    public static void nglGetTextureParameteriv(int i, int i2, long j) {
        GL45C.nglGetTextureParameteriv(i, i2, j);
    }

    public static void glGetTextureParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL45C.glGetTextureParameteriv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetTextureParameteri(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL45C.glGetTextureParameteri(i, i2);
    }

    public static void nglCreateVertexArrays(int i, long j) {
        GL45C.nglCreateVertexArrays(i, j);
    }

    public static void glCreateVertexArrays(@NativeType("GLuint *") IntBuffer intBuffer) {
        GL45C.glCreateVertexArrays(intBuffer);
    }

    @NativeType("void")
    public static int glCreateVertexArrays() {
        return GL45C.glCreateVertexArrays();
    }

    public static void glDisableVertexArrayAttrib(@NativeType("GLuint") int i, @NativeType("GLuint") int i2) {
        GL45C.glDisableVertexArrayAttrib(i, i2);
    }

    public static void glEnableVertexArrayAttrib(@NativeType("GLuint") int i, @NativeType("GLuint") int i2) {
        GL45C.glEnableVertexArrayAttrib(i, i2);
    }

    public static void glVertexArrayElementBuffer(@NativeType("GLuint") int i, @NativeType("GLuint") int i2) {
        GL45C.glVertexArrayElementBuffer(i, i2);
    }

    public static void glVertexArrayVertexBuffer(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i4) {
        GL45C.glVertexArrayVertexBuffer(i, i2, i3, j, i4);
    }

    public static void nglVertexArrayVertexBuffers(int i, int i2, int i3, long j, long j2, long j3) {
        GL45C.nglVertexArrayVertexBuffers(i, i2, i3, j, j2, j3);
    }

    public static void glVertexArrayVertexBuffers(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLintptr const *") PointerBuffer pointerBuffer, @NativeType("GLsizei const *") IntBuffer intBuffer2) {
        GL45C.glVertexArrayVertexBuffers(i, i2, intBuffer, pointerBuffer, intBuffer2);
    }

    public static void glVertexArrayAttribFormat(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLboolean") boolean z, @NativeType("GLuint") int i5) {
        GL45C.glVertexArrayAttribFormat(i, i2, i3, i4, z, i5);
    }

    public static void glVertexArrayAttribIFormat(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLuint") int i5) {
        GL45C.glVertexArrayAttribIFormat(i, i2, i3, i4, i5);
    }

    public static void glVertexArrayAttribLFormat(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLuint") int i5) {
        GL45C.glVertexArrayAttribLFormat(i, i2, i3, i4, i5);
    }

    public static void glVertexArrayAttribBinding(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3) {
        GL45C.glVertexArrayAttribBinding(i, i2, i3);
    }

    public static void glVertexArrayBindingDivisor(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3) {
        GL45C.glVertexArrayBindingDivisor(i, i2, i3);
    }

    public static void nglGetVertexArrayiv(int i, int i2, long j) {
        GL45C.nglGetVertexArrayiv(i, i2, j);
    }

    public static void glGetVertexArrayiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL45C.glGetVertexArrayiv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetVertexArrayi(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL45C.glGetVertexArrayi(i, i2);
    }

    public static void nglGetVertexArrayIndexediv(int i, int i2, int i3, long j) {
        GL45C.nglGetVertexArrayIndexediv(i, i2, i3, j);
    }

    public static void glGetVertexArrayIndexediv(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        GL45C.glGetVertexArrayIndexediv(i, i2, i3, intBuffer);
    }

    @NativeType("void")
    public static int glGetVertexArrayIndexedi(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3) {
        return GL45C.glGetVertexArrayIndexedi(i, i2, i3);
    }

    public static void nglGetVertexArrayIndexed64iv(int i, int i2, int i3, long j) {
        GL45C.nglGetVertexArrayIndexed64iv(i, i2, i3, j);
    }

    public static void glGetVertexArrayIndexed64iv(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint64 *") LongBuffer longBuffer) {
        GL45C.glGetVertexArrayIndexed64iv(i, i2, i3, longBuffer);
    }

    @NativeType("void")
    public static long glGetVertexArrayIndexed64i(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3) {
        return GL45C.glGetVertexArrayIndexed64i(i, i2, i3);
    }

    public static void nglCreateSamplers(int i, long j) {
        GL45C.nglCreateSamplers(i, j);
    }

    public static void glCreateSamplers(@NativeType("GLuint *") IntBuffer intBuffer) {
        GL45C.glCreateSamplers(intBuffer);
    }

    @NativeType("void")
    public static int glCreateSamplers() {
        return GL45C.glCreateSamplers();
    }

    public static void nglCreateProgramPipelines(int i, long j) {
        GL45C.nglCreateProgramPipelines(i, j);
    }

    public static void glCreateProgramPipelines(@NativeType("GLuint *") IntBuffer intBuffer) {
        GL45C.glCreateProgramPipelines(intBuffer);
    }

    @NativeType("void")
    public static int glCreateProgramPipelines() {
        return GL45C.glCreateProgramPipelines();
    }

    public static void nglCreateQueries(int i, int i2, long j) {
        GL45C.nglCreateQueries(i, i2, j);
    }

    public static void glCreateQueries(@NativeType("GLenum") int i, @NativeType("GLuint *") IntBuffer intBuffer) {
        GL45C.glCreateQueries(i, intBuffer);
    }

    @NativeType("void")
    public static int glCreateQueries(@NativeType("GLenum") int i) {
        return GL45C.glCreateQueries(i);
    }

    public static void glGetQueryBufferObjecti64v(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLintptr") long j) {
        GL45C.glGetQueryBufferObjecti64v(i, i2, i3, j);
    }

    public static void glGetQueryBufferObjectiv(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLintptr") long j) {
        GL45C.glGetQueryBufferObjectiv(i, i2, i3, j);
    }

    public static void glGetQueryBufferObjectui64v(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLintptr") long j) {
        GL45C.glGetQueryBufferObjectui64v(i, i2, i3, j);
    }

    public static void glGetQueryBufferObjectuiv(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLintptr") long j) {
        GL45C.glGetQueryBufferObjectuiv(i, i2, i3, j);
    }

    public static void glCreateTransformFeedbacks(@NativeType("GLuint *") int[] iArr) {
        GL45C.glCreateTransformFeedbacks(iArr);
    }

    public static void glGetTransformFeedbackiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        GL45C.glGetTransformFeedbackiv(i, i2, iArr);
    }

    public static void glGetTransformFeedbacki_v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint *") int[] iArr) {
        GL45C.glGetTransformFeedbacki_v(i, i2, i3, iArr);
    }

    public static void glGetTransformFeedbacki64_v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint64 *") long[] jArr) {
        GL45C.glGetTransformFeedbacki64_v(i, i2, i3, jArr);
    }

    public static void glCreateBuffers(@NativeType("GLuint *") int[] iArr) {
        GL45C.glCreateBuffers(iArr);
    }

    public static void glNamedBufferStorage(@NativeType("GLuint") int i, @NativeType("void const *") short[] sArr, @NativeType("GLbitfield") int i2) {
        GL45C.glNamedBufferStorage(i, sArr, i2);
    }

    public static void glNamedBufferStorage(@NativeType("GLuint") int i, @NativeType("void const *") int[] iArr, @NativeType("GLbitfield") int i2) {
        GL45C.glNamedBufferStorage(i, iArr, i2);
    }

    public static void glNamedBufferStorage(@NativeType("GLuint") int i, @NativeType("void const *") float[] fArr, @NativeType("GLbitfield") int i2) {
        GL45C.glNamedBufferStorage(i, fArr, i2);
    }

    public static void glNamedBufferStorage(@NativeType("GLuint") int i, @NativeType("void const *") double[] dArr, @NativeType("GLbitfield") int i2) {
        GL45C.glNamedBufferStorage(i, dArr, i2);
    }

    public static void glNamedBufferData(@NativeType("GLuint") int i, @NativeType("void const *") short[] sArr, @NativeType("GLenum") int i2) {
        GL45C.glNamedBufferData(i, sArr, i2);
    }

    public static void glNamedBufferData(@NativeType("GLuint") int i, @NativeType("void const *") int[] iArr, @NativeType("GLenum") int i2) {
        GL45C.glNamedBufferData(i, iArr, i2);
    }

    public static void glNamedBufferData(@NativeType("GLuint") int i, @NativeType("void const *") long[] jArr, @NativeType("GLenum") int i2) {
        GL45C.glNamedBufferData(i, jArr, i2);
    }

    public static void glNamedBufferData(@NativeType("GLuint") int i, @NativeType("void const *") float[] fArr, @NativeType("GLenum") int i2) {
        GL45C.glNamedBufferData(i, fArr, i2);
    }

    public static void glNamedBufferData(@NativeType("GLuint") int i, @NativeType("void const *") double[] dArr, @NativeType("GLenum") int i2) {
        GL45C.glNamedBufferData(i, dArr, i2);
    }

    public static void glNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") short[] sArr) {
        GL45C.glNamedBufferSubData(i, j, sArr);
    }

    public static void glNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") int[] iArr) {
        GL45C.glNamedBufferSubData(i, j, iArr);
    }

    public static void glNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") long[] jArr) {
        GL45C.glNamedBufferSubData(i, j, jArr);
    }

    public static void glNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") float[] fArr) {
        GL45C.glNamedBufferSubData(i, j, fArr);
    }

    public static void glNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") double[] dArr) {
        GL45C.glNamedBufferSubData(i, j, dArr);
    }

    public static void glClearNamedBufferData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") short[] sArr) {
        GL45C.glClearNamedBufferData(i, i2, i3, i4, sArr);
    }

    public static void glClearNamedBufferData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") int[] iArr) {
        GL45C.glClearNamedBufferData(i, i2, i3, i4, iArr);
    }

    public static void glClearNamedBufferData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") float[] fArr) {
        GL45C.glClearNamedBufferData(i, i2, i3, i4, fArr);
    }

    public static void glClearNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") short[] sArr) {
        GL45C.glClearNamedBufferSubData(i, i2, j, j2, i3, i4, sArr);
    }

    public static void glClearNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") int[] iArr) {
        GL45C.glClearNamedBufferSubData(i, i2, j, j2, i3, i4, iArr);
    }

    public static void glClearNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") float[] fArr) {
        GL45C.glClearNamedBufferSubData(i, i2, j, j2, i3, i4, fArr);
    }

    public static void glGetNamedBufferParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        GL45C.glGetNamedBufferParameteriv(i, i2, iArr);
    }

    public static void glGetNamedBufferParameteri64v(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint64 *") long[] jArr) {
        GL45C.glGetNamedBufferParameteri64v(i, i2, jArr);
    }

    public static void glGetNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") short[] sArr) {
        GL45C.glGetNamedBufferSubData(i, j, sArr);
    }

    public static void glGetNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") int[] iArr) {
        GL45C.glGetNamedBufferSubData(i, j, iArr);
    }

    public static void glGetNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") long[] jArr) {
        GL45C.glGetNamedBufferSubData(i, j, jArr);
    }

    public static void glGetNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") float[] fArr) {
        GL45C.glGetNamedBufferSubData(i, j, fArr);
    }

    public static void glGetNamedBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") double[] dArr) {
        GL45C.glGetNamedBufferSubData(i, j, dArr);
    }

    public static void glCreateFramebuffers(@NativeType("GLuint *") int[] iArr) {
        GL45C.glCreateFramebuffers(iArr);
    }

    public static void glNamedFramebufferDrawBuffers(@NativeType("GLuint") int i, @NativeType("GLenum const *") int[] iArr) {
        GL45C.glNamedFramebufferDrawBuffers(i, iArr);
    }

    public static void glInvalidateNamedFramebufferData(@NativeType("GLuint") int i, @NativeType("GLenum const *") int[] iArr) {
        GL45C.glInvalidateNamedFramebufferData(i, iArr);
    }

    public static void glInvalidateNamedFramebufferSubData(@NativeType("GLuint") int i, @NativeType("GLenum const *") int[] iArr, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5) {
        GL45C.glInvalidateNamedFramebufferSubData(i, iArr, i2, i3, i4, i5);
    }

    public static void glClearNamedFramebufferiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint const *") int[] iArr) {
        GL45C.glClearNamedFramebufferiv(i, i2, i3, iArr);
    }

    public static void glClearNamedFramebufferuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint const *") int[] iArr) {
        GL45C.glClearNamedFramebufferuiv(i, i2, i3, iArr);
    }

    public static void glClearNamedFramebufferfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLfloat const *") float[] fArr) {
        GL45C.glClearNamedFramebufferfv(i, i2, i3, fArr);
    }

    public static void glGetNamedFramebufferParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        GL45C.glGetNamedFramebufferParameteriv(i, i2, iArr);
    }

    public static void glGetNamedFramebufferAttachmentParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        GL45C.glGetNamedFramebufferAttachmentParameteriv(i, i2, i3, iArr);
    }

    public static void glCreateRenderbuffers(@NativeType("GLuint *") int[] iArr) {
        GL45C.glCreateRenderbuffers(iArr);
    }

    public static void glGetNamedRenderbufferParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        GL45C.glGetNamedRenderbufferParameteriv(i, i2, iArr);
    }

    public static void glCreateTextures(@NativeType("GLenum") int i, @NativeType("GLuint *") int[] iArr) {
        GL45C.glCreateTextures(i, iArr);
    }

    public static void glTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") short[] sArr) {
        GL45C.glTextureSubImage1D(i, i2, i3, i4, i5, i6, sArr);
    }

    public static void glTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") int[] iArr) {
        GL45C.glTextureSubImage1D(i, i2, i3, i4, i5, i6, iArr);
    }

    public static void glTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") float[] fArr) {
        GL45C.glTextureSubImage1D(i, i2, i3, i4, i5, i6, fArr);
    }

    public static void glTextureSubImage1D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") double[] dArr) {
        GL45C.glTextureSubImage1D(i, i2, i3, i4, i5, i6, dArr);
    }

    public static void glTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") short[] sArr) {
        GL45C.glTextureSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, sArr);
    }

    public static void glTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") int[] iArr) {
        GL45C.glTextureSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, iArr);
    }

    public static void glTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") float[] fArr) {
        GL45C.glTextureSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, fArr);
    }

    public static void glTextureSubImage2D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") double[] dArr) {
        GL45C.glTextureSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, dArr);
    }

    public static void glTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") short[] sArr) {
        GL45C.glTextureSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, sArr);
    }

    public static void glTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") int[] iArr) {
        GL45C.glTextureSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, iArr);
    }

    public static void glTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") float[] fArr) {
        GL45C.glTextureSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, fArr);
    }

    public static void glTextureSubImage3D(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") double[] dArr) {
        GL45C.glTextureSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, dArr);
    }

    public static void glTextureParameterfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") float[] fArr) {
        GL45C.glTextureParameterfv(i, i2, fArr);
    }

    public static void glTextureParameterIiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int[] iArr) {
        GL45C.glTextureParameterIiv(i, i2, iArr);
    }

    public static void glTextureParameterIuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") int[] iArr) {
        GL45C.glTextureParameterIuiv(i, i2, iArr);
    }

    public static void glTextureParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int[] iArr) {
        GL45C.glTextureParameteriv(i, i2, iArr);
    }

    public static void glGetTextureImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") short[] sArr) {
        GL45C.glGetTextureImage(i, i2, i3, i4, sArr);
    }

    public static void glGetTextureImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") int[] iArr) {
        GL45C.glGetTextureImage(i, i2, i3, i4, iArr);
    }

    public static void glGetTextureImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") float[] fArr) {
        GL45C.glGetTextureImage(i, i2, i3, i4, fArr);
    }

    public static void glGetTextureImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") double[] dArr) {
        GL45C.glGetTextureImage(i, i2, i3, i4, dArr);
    }

    public static void glGetTextureLevelParameterfv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat *") float[] fArr) {
        GL45C.glGetTextureLevelParameterfv(i, i2, i3, fArr);
    }

    public static void glGetTextureLevelParameteriv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        GL45C.glGetTextureLevelParameteriv(i, i2, i3, iArr);
    }

    public static void glGetTextureParameterfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") float[] fArr) {
        GL45C.glGetTextureParameterfv(i, i2, fArr);
    }

    public static void glGetTextureParameterIiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        GL45C.glGetTextureParameterIiv(i, i2, iArr);
    }

    public static void glGetTextureParameterIuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") int[] iArr) {
        GL45C.glGetTextureParameterIuiv(i, i2, iArr);
    }

    public static void glGetTextureParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        GL45C.glGetTextureParameteriv(i, i2, iArr);
    }

    public static void glCreateVertexArrays(@NativeType("GLuint *") int[] iArr) {
        GL45C.glCreateVertexArrays(iArr);
    }

    public static void glVertexArrayVertexBuffers(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint const *") int[] iArr, @NativeType("GLintptr const *") PointerBuffer pointerBuffer, @NativeType("GLsizei const *") int[] iArr2) {
        GL45C.glVertexArrayVertexBuffers(i, i2, iArr, pointerBuffer, iArr2);
    }

    public static void glGetVertexArrayiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        GL45C.glGetVertexArrayiv(i, i2, iArr);
    }

    public static void glGetVertexArrayIndexediv(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        GL45C.glGetVertexArrayIndexediv(i, i2, i3, iArr);
    }

    public static void glGetVertexArrayIndexed64iv(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint64 *") long[] jArr) {
        GL45C.glGetVertexArrayIndexed64iv(i, i2, i3, jArr);
    }

    public static void glCreateSamplers(@NativeType("GLuint *") int[] iArr) {
        GL45C.glCreateSamplers(iArr);
    }

    public static void glCreateProgramPipelines(@NativeType("GLuint *") int[] iArr) {
        GL45C.glCreateProgramPipelines(iArr);
    }

    public static void glCreateQueries(@NativeType("GLenum") int i, @NativeType("GLuint *") int[] iArr) {
        GL45C.glCreateQueries(i, iArr);
    }
}
