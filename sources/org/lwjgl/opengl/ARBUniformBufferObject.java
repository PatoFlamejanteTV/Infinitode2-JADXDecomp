package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBUniformBufferObject.class */
public class ARBUniformBufferObject {
    public static final int GL_UNIFORM_BUFFER = 35345;
    public static final int GL_UNIFORM_BUFFER_BINDING = 35368;
    public static final int GL_UNIFORM_BUFFER_START = 35369;
    public static final int GL_UNIFORM_BUFFER_SIZE = 35370;
    public static final int GL_MAX_VERTEX_UNIFORM_BLOCKS = 35371;
    public static final int GL_MAX_GEOMETRY_UNIFORM_BLOCKS = 35372;
    public static final int GL_MAX_FRAGMENT_UNIFORM_BLOCKS = 35373;
    public static final int GL_MAX_COMBINED_UNIFORM_BLOCKS = 35374;
    public static final int GL_MAX_UNIFORM_BUFFER_BINDINGS = 35375;
    public static final int GL_MAX_UNIFORM_BLOCK_SIZE = 35376;
    public static final int GL_MAX_COMBINED_VERTEX_UNIFORM_COMPONENTS = 35377;
    public static final int GL_MAX_COMBINED_GEOMETRY_UNIFORM_COMPONENTS = 35378;
    public static final int GL_MAX_COMBINED_FRAGMENT_UNIFORM_COMPONENTS = 35379;
    public static final int GL_UNIFORM_BUFFER_OFFSET_ALIGNMENT = 35380;
    public static final int GL_ACTIVE_UNIFORM_BLOCK_MAX_NAME_LENGTH = 35381;
    public static final int GL_ACTIVE_UNIFORM_BLOCKS = 35382;
    public static final int GL_UNIFORM_TYPE = 35383;
    public static final int GL_UNIFORM_SIZE = 35384;
    public static final int GL_UNIFORM_NAME_LENGTH = 35385;
    public static final int GL_UNIFORM_BLOCK_INDEX = 35386;
    public static final int GL_UNIFORM_OFFSET = 35387;
    public static final int GL_UNIFORM_ARRAY_STRIDE = 35388;
    public static final int GL_UNIFORM_MATRIX_STRIDE = 35389;
    public static final int GL_UNIFORM_IS_ROW_MAJOR = 35390;
    public static final int GL_UNIFORM_BLOCK_BINDING = 35391;
    public static final int GL_UNIFORM_BLOCK_DATA_SIZE = 35392;
    public static final int GL_UNIFORM_BLOCK_NAME_LENGTH = 35393;
    public static final int GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS = 35394;
    public static final int GL_UNIFORM_BLOCK_ACTIVE_UNIFORM_INDICES = 35395;
    public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_VERTEX_SHADER = 35396;
    public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_GEOMETRY_SHADER = 35397;
    public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_FRAGMENT_SHADER = 35398;
    public static final int GL_INVALID_INDEX = -1;

    static {
        GL.initialize();
    }

    protected ARBUniformBufferObject() {
        throw new UnsupportedOperationException();
    }

    public static void nglGetUniformIndices(int i, int i2, long j, long j2) {
        GL31C.nglGetUniformIndices(i, i2, j, j2);
    }

    public static void glGetUniformIndices(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") PointerBuffer pointerBuffer, @NativeType("GLuint *") IntBuffer intBuffer) {
        GL31C.glGetUniformIndices(i, pointerBuffer, intBuffer);
    }

    public static void glGetUniformIndices(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") CharSequence[] charSequenceArr, @NativeType("GLuint *") IntBuffer intBuffer) {
        GL31C.glGetUniformIndices(i, charSequenceArr, intBuffer);
    }

    @NativeType("void")
    public static int glGetUniformIndices(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") CharSequence charSequence) {
        return GL31C.glGetUniformIndices(i, charSequence);
    }

    public static void nglGetActiveUniformsiv(int i, int i2, long j, int i3, long j2) {
        GL31C.nglGetActiveUniformsiv(i, i2, j, i3, j2);
    }

    public static void glGetActiveUniformsiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer2) {
        GL31C.glGetActiveUniformsiv(i, intBuffer, i2, intBuffer2);
    }

    @NativeType("void")
    public static int glGetActiveUniformsi(@NativeType("GLuint") int i, @NativeType("GLuint const *") int i2, @NativeType("GLenum") int i3) {
        return GL31C.glGetActiveUniformsi(i, i2, i3);
    }

    public static void nglGetActiveUniformName(int i, int i2, int i3, long j, long j2) {
        GL31C.nglGetActiveUniformName(i, i2, i3, j, j2);
    }

    public static void glGetActiveUniformName(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL31C.glGetActiveUniformName(i, i2, intBuffer, byteBuffer);
    }

    @NativeType("void")
    public static String glGetActiveUniformName(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei") int i3) {
        return GL31C.glGetActiveUniformName(i, i2, i3);
    }

    @NativeType("void")
    public static String glGetActiveUniformName(@NativeType("GLuint") int i, @NativeType("GLuint") int i2) {
        return glGetActiveUniformName(i, i2, glGetActiveUniformsi(i, i2, 35385));
    }

    public static int nglGetUniformBlockIndex(int i, long j) {
        return GL31C.nglGetUniformBlockIndex(i, j);
    }

    @NativeType("GLuint")
    public static int glGetUniformBlockIndex(@NativeType("GLuint") int i, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        return GL31C.glGetUniformBlockIndex(i, byteBuffer);
    }

    @NativeType("GLuint")
    public static int glGetUniformBlockIndex(@NativeType("GLuint") int i, @NativeType("GLchar const *") CharSequence charSequence) {
        return GL31C.glGetUniformBlockIndex(i, charSequence);
    }

    public static void nglGetActiveUniformBlockiv(int i, int i2, int i3, long j) {
        GL31C.nglGetActiveUniformBlockiv(i, i2, i3, j);
    }

    public static void glGetActiveUniformBlockiv(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        GL31C.glGetActiveUniformBlockiv(i, i2, i3, intBuffer);
    }

    @NativeType("void")
    public static int glGetActiveUniformBlocki(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3) {
        return GL31C.glGetActiveUniformBlocki(i, i2, i3);
    }

    public static void nglGetActiveUniformBlockName(int i, int i2, int i3, long j, long j2) {
        GL31C.nglGetActiveUniformBlockName(i, i2, i3, j, j2);
    }

    public static void glGetActiveUniformBlockName(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL31C.glGetActiveUniformBlockName(i, i2, intBuffer, byteBuffer);
    }

    @NativeType("void")
    public static String glGetActiveUniformBlockName(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei") int i3) {
        return GL31C.glGetActiveUniformBlockName(i, i2, i3);
    }

    @NativeType("void")
    public static String glGetActiveUniformBlockName(@NativeType("GLuint") int i, @NativeType("GLuint") int i2) {
        return glGetActiveUniformBlockName(i, i2, glGetActiveUniformBlocki(i, i2, 35393));
    }

    public static void glBindBufferRange(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2) {
        GL30C.glBindBufferRange(i, i2, i3, j, j2);
    }

    public static void glBindBufferBase(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3) {
        GL30C.glBindBufferBase(i, i2, i3);
    }

    public static void nglGetIntegeri_v(int i, int i2, long j) {
        GL30C.nglGetIntegeri_v(i, i2, j);
    }

    public static void glGetIntegeri_v(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL30C.glGetIntegeri_v(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetIntegeri(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        return GL30C.glGetIntegeri(i, i2);
    }

    public static void glUniformBlockBinding(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3) {
        GL31C.glUniformBlockBinding(i, i2, i3);
    }

    public static void glGetUniformIndices(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") PointerBuffer pointerBuffer, @NativeType("GLuint *") int[] iArr) {
        GL31C.glGetUniformIndices(i, pointerBuffer, iArr);
    }

    public static void glGetActiveUniformsiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr2) {
        GL31C.glGetActiveUniformsiv(i, iArr, i2, iArr2);
    }

    public static void glGetActiveUniformName(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL31C.glGetActiveUniformName(i, i2, iArr, byteBuffer);
    }

    public static void glGetActiveUniformBlockiv(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        GL31C.glGetActiveUniformBlockiv(i, i2, i3, iArr);
    }

    public static void glGetActiveUniformBlockName(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL31C.glGetActiveUniformBlockName(i, i2, iArr, byteBuffer);
    }

    public static void glGetIntegeri_v(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLint *") int[] iArr) {
        GL30C.glGetIntegeri_v(i, i2, iArr);
    }
}
