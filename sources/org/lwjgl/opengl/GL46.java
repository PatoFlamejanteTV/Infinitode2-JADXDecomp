package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL46.class */
public class GL46 extends GL45 {
    public static final int GL_PARAMETER_BUFFER = 33006;
    public static final int GL_PARAMETER_BUFFER_BINDING = 33007;
    public static final int GL_VERTICES_SUBMITTED = 33518;
    public static final int GL_PRIMITIVES_SUBMITTED = 33519;
    public static final int GL_VERTEX_SHADER_INVOCATIONS = 33520;
    public static final int GL_TESS_CONTROL_SHADER_PATCHES = 33521;
    public static final int GL_TESS_EVALUATION_SHADER_INVOCATIONS = 33522;
    public static final int GL_GEOMETRY_SHADER_PRIMITIVES_EMITTED = 33523;
    public static final int GL_FRAGMENT_SHADER_INVOCATIONS = 33524;
    public static final int GL_COMPUTE_SHADER_INVOCATIONS = 33525;
    public static final int GL_CLIPPING_INPUT_PRIMITIVES = 33526;
    public static final int GL_CLIPPING_OUTPUT_PRIMITIVES = 33527;
    public static final int GL_POLYGON_OFFSET_CLAMP = 36379;
    public static final int GL_CONTEXT_FLAG_NO_ERROR_BIT = 8;
    public static final int GL_SHADER_BINARY_FORMAT_SPIR_V = 38225;
    public static final int GL_SPIR_V_BINARY = 38226;
    public static final int GL_SPIR_V_EXTENSIONS = 38227;
    public static final int GL_NUM_SPIR_V_EXTENSIONS = 38228;
    public static final int GL_TEXTURE_MAX_ANISOTROPY = 34046;
    public static final int GL_MAX_TEXTURE_MAX_ANISOTROPY = 34047;
    public static final int GL_TRANSFORM_FEEDBACK_OVERFLOW = 33516;
    public static final int GL_TRANSFORM_FEEDBACK_STREAM_OVERFLOW = 33517;

    static {
        GL.initialize();
    }

    protected GL46() {
        throw new UnsupportedOperationException();
    }

    public static void nglMultiDrawArraysIndirectCount(int i, long j, long j2, int i2, int i3) {
        GL46C.nglMultiDrawArraysIndirectCount(i, j, j2, i2, i3);
    }

    public static void glMultiDrawArraysIndirectCount(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3) {
        GL46C.glMultiDrawArraysIndirectCount(i, byteBuffer, j, i2, i3);
    }

    public static void glMultiDrawArraysIndirectCount(@NativeType("GLenum") int i, @NativeType("void const *") long j, @NativeType("GLintptr") long j2, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3) {
        GL46C.glMultiDrawArraysIndirectCount(i, j, j2, i2, i3);
    }

    public static void glMultiDrawArraysIndirectCount(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3) {
        GL46C.glMultiDrawArraysIndirectCount(i, intBuffer, j, i2, i3);
    }

    public static void nglMultiDrawElementsIndirectCount(int i, int i2, long j, long j2, int i3, int i4) {
        GL46C.nglMultiDrawElementsIndirectCount(i, i2, j, j2, i3, i4);
    }

    public static void glMultiDrawElementsIndirectCount(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        GL46C.glMultiDrawElementsIndirectCount(i, i2, byteBuffer, j, i3, i4);
    }

    public static void glMultiDrawElementsIndirectCount(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") long j, @NativeType("GLintptr") long j2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        GL46C.glMultiDrawElementsIndirectCount(i, i2, j, j2, i3, i4);
    }

    public static void glMultiDrawElementsIndirectCount(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        GL46C.glMultiDrawElementsIndirectCount(i, i2, intBuffer, j, i3, i4);
    }

    public static void glPolygonOffsetClamp(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3) {
        GL46C.glPolygonOffsetClamp(f, f2, f3);
    }

    public static void nglSpecializeShader(int i, long j, int i2, long j2, long j3) {
        GL46C.nglSpecializeShader(i, j, i2, j2, j3);
    }

    public static void glSpecializeShader(@NativeType("GLuint") int i, @NativeType("GLchar const *") ByteBuffer byteBuffer, @NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLuint const *") IntBuffer intBuffer2) {
        GL46C.glSpecializeShader(i, byteBuffer, intBuffer, intBuffer2);
    }

    public static void glSpecializeShader(@NativeType("GLuint") int i, @NativeType("GLchar const *") CharSequence charSequence, @NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLuint const *") IntBuffer intBuffer2) {
        GL46C.glSpecializeShader(i, charSequence, intBuffer, intBuffer2);
    }

    public static void glMultiDrawArraysIndirectCount(@NativeType("GLenum") int i, @NativeType("void const *") int[] iArr, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3) {
        GL46C.glMultiDrawArraysIndirectCount(i, iArr, j, i2, i3);
    }

    public static void glMultiDrawElementsIndirectCount(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") int[] iArr, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        GL46C.glMultiDrawElementsIndirectCount(i, i2, iArr, j, i3, i4);
    }

    public static void glSpecializeShader(@NativeType("GLuint") int i, @NativeType("GLchar const *") ByteBuffer byteBuffer, @NativeType("GLuint const *") int[] iArr, @NativeType("GLuint const *") int[] iArr2) {
        GL46C.glSpecializeShader(i, byteBuffer, iArr, iArr2);
    }

    public static void glSpecializeShader(@NativeType("GLuint") int i, @NativeType("GLchar const *") CharSequence charSequence, @NativeType("GLuint const *") int[] iArr, @NativeType("GLuint const *") int[] iArr2) {
        GL46C.glSpecializeShader(i, charSequence, iArr, iArr2);
    }
}
