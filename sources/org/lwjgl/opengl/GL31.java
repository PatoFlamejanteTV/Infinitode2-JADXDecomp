package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL31.class */
public class GL31 extends GL30 {
    public static final int GL_R8_SNORM = 36756;
    public static final int GL_RG8_SNORM = 36757;
    public static final int GL_RGB8_SNORM = 36758;
    public static final int GL_RGBA8_SNORM = 36759;
    public static final int GL_R16_SNORM = 36760;
    public static final int GL_RG16_SNORM = 36761;
    public static final int GL_RGB16_SNORM = 36762;
    public static final int GL_RGBA16_SNORM = 36763;
    public static final int GL_SIGNED_NORMALIZED = 36764;
    public static final int GL_SAMPLER_BUFFER = 36290;
    public static final int GL_INT_SAMPLER_2D_RECT = 36301;
    public static final int GL_INT_SAMPLER_BUFFER = 36304;
    public static final int GL_UNSIGNED_INT_SAMPLER_2D_RECT = 36309;
    public static final int GL_UNSIGNED_INT_SAMPLER_BUFFER = 36312;
    public static final int GL_COPY_READ_BUFFER = 36662;
    public static final int GL_COPY_WRITE_BUFFER = 36663;
    public static final int GL_PRIMITIVE_RESTART = 36765;
    public static final int GL_PRIMITIVE_RESTART_INDEX = 36766;
    public static final int GL_TEXTURE_BUFFER = 35882;
    public static final int GL_MAX_TEXTURE_BUFFER_SIZE = 35883;
    public static final int GL_TEXTURE_BINDING_BUFFER = 35884;
    public static final int GL_TEXTURE_BUFFER_DATA_STORE_BINDING = 35885;
    public static final int GL_TEXTURE_RECTANGLE = 34037;
    public static final int GL_TEXTURE_BINDING_RECTANGLE = 34038;
    public static final int GL_PROXY_TEXTURE_RECTANGLE = 34039;
    public static final int GL_MAX_RECTANGLE_TEXTURE_SIZE = 34040;
    public static final int GL_SAMPLER_2D_RECT = 35683;
    public static final int GL_SAMPLER_2D_RECT_SHADOW = 35684;
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

    /* JADX INFO: Access modifiers changed from: protected */
    public GL31() {
        throw new UnsupportedOperationException();
    }

    public static void glDrawArraysInstanced(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        GL31C.glDrawArraysInstanced(i, i2, i3, i4);
    }

    public static void nglDrawElementsInstanced(int i, int i2, int i3, long j, int i4) {
        GL31C.nglDrawElementsInstanced(i, i2, i3, j, i4);
    }

    public static void glDrawElementsInstanced(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("void const *") long j, @NativeType("GLsizei") int i4) {
        GL31C.glDrawElementsInstanced(i, i2, i3, j, i4);
    }

    public static void glDrawElementsInstanced(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i3) {
        GL31C.glDrawElementsInstanced(i, i2, byteBuffer, i3);
    }

    public static void glDrawElementsInstanced(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i2) {
        GL31C.glDrawElementsInstanced(i, byteBuffer, i2);
    }

    public static void glDrawElementsInstanced(@NativeType("GLenum") int i, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLsizei") int i2) {
        GL31C.glDrawElementsInstanced(i, shortBuffer, i2);
    }

    public static void glDrawElementsInstanced(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLsizei") int i2) {
        GL31C.glDrawElementsInstanced(i, intBuffer, i2);
    }

    public static void glCopyBufferSubData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLintptr") long j2, @NativeType("GLsizeiptr") long j3) {
        GL31C.glCopyBufferSubData(i, i2, j, j2, j3);
    }

    public static void glPrimitiveRestartIndex(@NativeType("GLuint") int i) {
        GL31C.glPrimitiveRestartIndex(i);
    }

    public static void glTexBuffer(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3) {
        GL31C.glTexBuffer(i, i2, i3);
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
}
